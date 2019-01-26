package novo;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import leitorArquivo.CoordCartesiana;
import leitorArquivo.CoordCartograficaUTM;
import utils.MyMathUtils;

public class CalculadoraGEOparaUTM {

    static BigDecimal UM = BigDecimal.ONE;
    static BigDecimal DOIS = MyMathUtils.createBigDecimal("2");
    static BigDecimal TRES = MyMathUtils.createBigDecimal("3");
    static BigDecimal QUATRO = MyMathUtils.createBigDecimal("4");
    static BigDecimal CINCO = MyMathUtils.createBigDecimal("5");
    static BigDecimal SEIS = MyMathUtils.createBigDecimal("6");

    private BigDecimal kZero = MyMathUtils.createBigDecimal("0.9996");
    private BigDecimal falsoNorteHN = MyMathUtils.createBigDecimal("0");
    private BigDecimal falsoNorteHS = MyMathUtils.createBigDecimal("10000000");
    private BigDecimal falsoLeste = MyMathUtils.createBigDecimal("500000");
    private BigDecimal nZero;

    private String hemisferioEscolhido;
    private BigDecimal coeficiente_a = MyMathUtils.createBigDecimal("6378137");
    private BigDecimal coeficiente_f = MyMathUtils.createBigDecimal(1 / 298.257222101);

    private BigDecimal termoQuatro(BigDecimal latitude, BigDecimal longitude) {
        BigDecimal parentesesUm = MyMathUtils.dividir(MyMathUtils.pow(getCalculoCoeficienteW(longitude), 2), DOIS);
        BigDecimal termoQuatro = (parentesesUm.multiply(getCalculoV(latitude)).multiply(MyMathUtils.senoRAD(latitude)).multiply(MyMathUtils.cossenoRAD(latitude)));

        MyMathUtils.printNum("termoQuatro", termoQuatro);
        return termoQuatro;
    }

    private BigDecimal termoCinco(BigDecimal latitude, BigDecimal longitude) {
        BigDecimal parentesesUm = MyMathUtils.dividir(MyMathUtils.pow(getCalculoCoeficienteW(longitude), 4), MyMathUtils.createBigDecimal(24));
        BigDecimal parentesesDois = (QUATRO.multiply(MyMathUtils.pow(getCalculoPsi(latitude), 2))).add(getCalculoPsi(latitude)).subtract(MyMathUtils.pow(tMinusculo(latitude), 2));
        BigDecimal cosCuboLatitude = MyMathUtils.cubo(MyMathUtils.cossenoRAD(latitude));
        BigDecimal senoLatitude = MyMathUtils.senoRAD(latitude);

        BigDecimal termoCinco = parentesesUm.multiply(getCalculoV(latitude)).multiply(senoLatitude).multiply(cosCuboLatitude).multiply(parentesesDois);
        
        MyMathUtils.printNum("termoCinco", termoCinco);
        return termoCinco;
    }

    private BigDecimal termoSeis(BigDecimal latitude, BigDecimal longitude) {
        BigDecimal parentesesUm = MyMathUtils.dividir(MyMathUtils.pow(getCalculoCoeficienteW(longitude), 6), MyMathUtils.createBigDecimal(720));
        BigDecimal parentesesDois = MyMathUtils.createBigDecimal(11).subtract(MyMathUtils.createBigDecimal(24).multiply(MyMathUtils.pow(tMinusculo(latitude), 2)));
        BigDecimal parentesesTres = MyMathUtils.createBigDecimal(1).subtract(MyMathUtils.createBigDecimal(6).multiply(MyMathUtils.pow(tMinusculo(latitude), 2)));
        BigDecimal parentesesQuatro = MyMathUtils.createBigDecimal(1).subtract(MyMathUtils.createBigDecimal(32).multiply(MyMathUtils.pow(tMinusculo(latitude), 2)));
        BigDecimal parentesesCinco = MyMathUtils.createBigDecimal(2).multiply(MyMathUtils.pow(tMinusculo(latitude), 2));
        BigDecimal cosQuintaLatitude = MyMathUtils.pow(MyMathUtils.cossenoRAD(latitude), 5);
        BigDecimal senoLatitude = MyMathUtils.senoRAD(latitude);
        BigDecimal constanteUm = MyMathUtils.createBigDecimal(8).multiply(MyMathUtils.pow(getCalculoPsi(latitude), 4));
        BigDecimal constanteDois = MyMathUtils.createBigDecimal(28).multiply(MyMathUtils.pow(getCalculoPsi(latitude), 3));
        BigDecimal constanteTres = MyMathUtils.pow(getCalculoPsi(latitude), 2);
        BigDecimal constanteQuatro = getCalculoPsi(latitude);
        BigDecimal constanteCinco = MyMathUtils.pow(tMinusculo(latitude), 4);
        BigDecimal parcelaUm = constanteUm.multiply(parentesesDois);
        BigDecimal parcelaDois = constanteDois.multiply(parentesesTres);
        BigDecimal parcelaTres = constanteTres.multiply(parentesesQuatro);
        BigDecimal parcelaQuatro = constanteQuatro.multiply(parentesesCinco);
        BigDecimal parcelaCinco = constanteCinco;

        BigDecimal termoSeis = parentesesUm
                .multiply(getCalculoV(latitude))
                .multiply(senoLatitude)
                .multiply(cosQuintaLatitude)
                .multiply(
                        parcelaUm
                                .subtract(parcelaDois)
                                .add(parcelaTres)
                                .subtract(parcelaQuatro)
                                .add(parcelaCinco)
                );
        MyMathUtils.printNum("termoSeis", termoSeis);
        return termoSeis;
    }

    private BigDecimal termoSete(BigDecimal latitude, BigDecimal longitude) {

        BigDecimal parentesesUm = MyMathUtils.dividir((MyMathUtils.pow(getCalculoCoeficienteW(longitude), 8)), MyMathUtils.createBigDecimal(40320));
        BigDecimal parcelaUm = MyMathUtils.createBigDecimal(1385);
        BigDecimal parcelaDois = MyMathUtils.createBigDecimal(3111).multiply(MyMathUtils.pow(tMinusculo(latitude), 2));
        BigDecimal parcelaTres = MyMathUtils.createBigDecimal(543).multiply(MyMathUtils.pow(tMinusculo(latitude), 4));
        BigDecimal parcelaQuatro = MyMathUtils.pow(tMinusculo(latitude), 6);
        BigDecimal parentesesDois = parcelaUm.subtract(parcelaDois).add(parcelaTres).subtract(parcelaQuatro);
        BigDecimal cosSetimaLatitude = MyMathUtils.pow(MyMathUtils.cossenoRAD(latitude), 7);
        BigDecimal senoLatitude = MyMathUtils.senoRAD(latitude);

        BigDecimal termoSete = parentesesUm.multiply(getCalculoV(latitude)).multiply(senoLatitude).multiply(cosSetimaLatitude).multiply(parentesesDois);

        MyMathUtils.printNum("termoSete", termoSete);
        return termoSete;
    }

    private BigDecimal aZero() {
        BigDecimal parentesesUm = MyMathUtils.dividir(getCalculoCoeficienteE(), MyMathUtils.createBigDecimal(4));
        BigDecimal parentesesDois = MyMathUtils.dividir((TRES.multiply(MyMathUtils.pow(getCalculoCoeficienteE(), 2))), MyMathUtils.createBigDecimal(64));
        BigDecimal parentesesTres = MyMathUtils.dividir((CINCO.multiply(MyMathUtils.pow(getCalculoCoeficienteE(), 3))), MyMathUtils.createBigDecimal(256));

        BigDecimal aZero = UM.subtract(parentesesUm).subtract(parentesesDois).subtract(parentesesTres);

        MyMathUtils.printNum("aZero", aZero);
        return aZero;
    }

    private BigDecimal aDois() {
        BigDecimal parentesesUm = MyMathUtils.dividir(TRES, MyMathUtils.createBigDecimal(8));
        BigDecimal divisaoUm = MyMathUtils.dividir(MyMathUtils.pow(getCalculoCoeficienteE(), 2), QUATRO);
        BigDecimal divisaoDois = MyMathUtils.dividir((MyMathUtils.createBigDecimal(15).multiply(MyMathUtils.pow(getCalculoCoeficienteE(), 3))), MyMathUtils.createBigDecimal(128));
        BigDecimal parentesesDois = getCalculoCoeficienteE().add(divisaoUm).add(divisaoDois);

        BigDecimal aDois = parentesesUm.multiply(parentesesDois);
        
        MyMathUtils.printNum("aDois", aDois);
        return aDois;
    }

    private BigDecimal aQuatro() {
        BigDecimal parentesesUm = MyMathUtils.dividir(MyMathUtils.createBigDecimal(15), MyMathUtils.createBigDecimal(256));
        BigDecimal parentesesDois = MyMathUtils.pow(getCalculoCoeficienteE(), 2)
                .add(
                        MyMathUtils.dividir(
                                TRES.multiply(MyMathUtils.pow(getCalculoCoeficienteE(), 3)),
                                QUATRO
                        )
                );
        BigDecimal aQuatro = parentesesUm.multiply(parentesesDois);
        
        MyMathUtils.printNum("aQuatro", aQuatro);
        return aQuatro;
    }

    private BigDecimal aSeis() {
        BigDecimal aSeis = MyMathUtils.dividir(
                MyMathUtils.createBigDecimal(35).multiply(MyMathUtils.pow(getCalculoCoeficienteE(), 3)),
                MyMathUtils.createBigDecimal(3072)
        );
        MyMathUtils.printNum("aSeis", aSeis);
        return aSeis;
    }

    private BigDecimal mMinusculo(BigDecimal latitude) {
        BigDecimal senoDoisLatitude = MyMathUtils.senoRAD(DOIS.multiply(latitude));
        BigDecimal senoQuatroLatitude = MyMathUtils.senoRAD(QUATRO.multiply(latitude));
        BigDecimal senoSeisLatitude = MyMathUtils.senoRAD(SEIS.multiply(latitude));
        BigDecimal parcelaUm = aZero().multiply(latitude).multiply(MyMathUtils.createBigDecimal(Math.PI/180));
        BigDecimal parcelaDois = aDois().multiply(senoDoisLatitude);
        BigDecimal parcelaTres = aQuatro().multiply(senoQuatroLatitude);
        BigDecimal parcelaQuatro = aSeis().multiply(senoSeisLatitude);

        BigDecimal mMinusculo = coeficiente_a.multiply(
                parcelaUm
                        .subtract(parcelaDois)
                        .add(parcelaTres)
                        .subtract(parcelaQuatro)
        );
        MyMathUtils.printNum("senoQuatroLatitude", senoQuatroLatitude);
        MyMathUtils.printNum("mMinusculo", mMinusculo);
        return mMinusculo;
    }

    private BigDecimal termoUm(BigDecimal latitude, BigDecimal longitude) {
        BigDecimal divisao = MyMathUtils.dividir(MyMathUtils.quadrado(getCalculoCoeficienteW(longitude)), MyMathUtils.createBigDecimal(6));
        BigDecimal cossenoQuadradoLatitude = MyMathUtils.quadrado(MyMathUtils.cossenoRAD(latitude));
        BigDecimal subtracao = getCalculoPsi(latitude).subtract(MyMathUtils.quadrado(tMinusculo(latitude)));

        BigDecimal termoUm = divisao.multiply(cossenoQuadradoLatitude).multiply(subtracao);
        
        MyMathUtils.printNum("termoUm", termoUm);
        return termoUm;
    }

    private BigDecimal termoDois(BigDecimal latitude, BigDecimal longitude) {
        BigDecimal divisao = MyMathUtils.dividir(MyMathUtils.pow(getCalculoCoeficienteW(longitude), 4), MyMathUtils.createBigDecimal(120));
        BigDecimal cossenoQuartaLatitude = MyMathUtils.pow(MyMathUtils.cossenoRAD(latitude), 4);
        BigDecimal parcelaUm = MyMathUtils.createBigDecimal(4).multiply(MyMathUtils.pow(getCalculoPsi(latitude), 3)).multiply(MyMathUtils.createBigDecimal(1).subtract(MyMathUtils.createBigDecimal(6).multiply(MyMathUtils.quadrado(tMinusculo(latitude)))));
        BigDecimal parcelaDois = MyMathUtils.quadrado(getCalculoPsi(latitude)).multiply(UM.add(MyMathUtils.createBigDecimal(8).multiply(MyMathUtils.quadrado(tMinusculo(latitude)))));
        BigDecimal parcelaTres = getCalculoPsi(latitude).multiply(DOIS.multiply(MyMathUtils.quadrado(tMinusculo(latitude))));
        BigDecimal parcelaQuatro = MyMathUtils.pow(tMinusculo(latitude), 4);

        BigDecimal termoDois = divisao
                .multiply(cossenoQuartaLatitude)
                .multiply(
                        parcelaUm
                                .add(parcelaDois)
                                .subtract(parcelaTres)
                                .add(parcelaQuatro)
                );
        MyMathUtils.printNum("termoDois", termoDois);
        return termoDois;
    }

    private BigDecimal termoTres(BigDecimal latitude, BigDecimal longitude) {
        BigDecimal divisao = MyMathUtils.dividir(MyMathUtils.pow(getCalculoCoeficienteW(longitude), 6), MyMathUtils.createBigDecimal(5040));
        BigDecimal cossenoSextaLatitude = MyMathUtils.pow(MyMathUtils.cossenoRAD(latitude), 6);

        BigDecimal parenteses = MyMathUtils.createBigDecimal(61)
                .subtract(
                        MyMathUtils.createBigDecimal(479)
                                .multiply(MyMathUtils.quadrado(tMinusculo(latitude)))
                )
                .add(
                        MyMathUtils.createBigDecimal(179)
                                .multiply(MyMathUtils.pow(tMinusculo(latitude), 4))
                                .subtract(MyMathUtils.pow(tMinusculo(latitude), 6))
                );

        BigDecimal termoTres = divisao.multiply(cossenoSextaLatitude).multiply(parenteses);
        
        MyMathUtils.printNum("termoTres", termoTres);
        return termoTres;
    }

    private BigDecimal tMinusculo(BigDecimal latitude) {
        BigDecimal tMinusculo = MyMathUtils.tgRAD(latitude);
        
        MyMathUtils.printNum("tMinusculo", tMinusculo);
        return tMinusculo;
    }

    private BigDecimal getCalculoLongitudeMeridianoCentral(BigDecimal longitude) {

        BigDecimal divisao = MyMathUtils.dividir(longitude, SEIS, MyMathUtils.CTX_0_DOWN);
        BigDecimal multiplicacao = SEIS.multiply(divisao);
        // inserir condição!!!!!!
        BigDecimal longitudeMeridianoCentral = multiplicacao.subtract(TRES);
        
        MyMathUtils.printNum("LongitudeMeridianoCentral", longitudeMeridianoCentral);
        return longitudeMeridianoCentral;
    }

    private BigDecimal getCalculoCoeficienteW(BigDecimal longitude) {
       BigDecimal coeficiente_w = (longitude.subtract(getCalculoLongitudeMeridianoCentral(longitude))).multiply(MyMathUtils.createBigDecimal(Math.PI/180));
//       BigDecimal coeficiente_w = (longitude.subtract(MyMathUtils.createBigDecimal(-45))).multiply(MyMathUtils.createBigDecimal(Math.PI/180));
        
        MyMathUtils.printNum("getCalculoCoeficienteW", coeficiente_w);
        return coeficiente_w;
    }

    private BigDecimal getCalculoCoeficienteE() {
        BigDecimal coeficiente_e = (coeficiente_f.multiply(DOIS))
                .subtract(MyMathUtils.quadrado(coeficiente_f));
        
        MyMathUtils.printNum("getCalculoCoeficienteE", coeficiente_e);
        return coeficiente_e;
    }

    private BigDecimal getCalculoPsi(BigDecimal latitude) {
        BigDecimal psi = MyMathUtils.dividir(getCalculoV(latitude), getCalculoP(latitude));
        
        MyMathUtils.printNum("getCalculoPsi", psi);
        return psi;
    }

    private BigDecimal getCalculoP(BigDecimal latitude) {
        BigDecimal eQuadrado = (getCalculoCoeficienteE());
        BigDecimal senoLatitude = MyMathUtils.senoRAD(latitude);
        BigDecimal senoQuadradoLatitude = MyMathUtils.quadrado(senoLatitude);
        BigDecimal eQuadradoVezesSenoQuadradoLatitude = eQuadrado.multiply(senoQuadradoLatitude);
        BigDecimal conta = BigDecimal.ONE.subtract(eQuadradoVezesSenoQuadradoLatitude);
        //BigDecimal tresDivididoDois = MyMathUtils.dividir(TRES, DOIS);

        BigDecimal numerador = coeficiente_a.multiply(UM.subtract(eQuadrado));
        BigDecimal denominador = MyMathUtils.pow(conta, 1.5); 
        BigDecimal resposta = MyMathUtils.dividir(numerador, denominador);
        
        MyMathUtils.printNum("getCalculoP", resposta);
        return resposta;
    }

    private BigDecimal getCalculoV(BigDecimal latitude) {
        BigDecimal senoLatitude = MyMathUtils.senoRAD(latitude);
        BigDecimal eQuadrado = (getCalculoCoeficienteE());
        BigDecimal senoQuadradoLatitude = MyMathUtils.quadrado(senoLatitude);
        BigDecimal eQuadradoVezesSenoQuadradoLatitude = eQuadrado.multiply(senoQuadradoLatitude);
        BigDecimal conta = MyMathUtils.umMenos(eQuadradoVezesSenoQuadradoLatitude);
        BigDecimal contaElevadoaMeio = MyMathUtils.sqrt(conta);
        
        BigDecimal numerador = coeficiente_a;
        BigDecimal denominador = contaElevadoaMeio;
        BigDecimal resposta = MyMathUtils.dividir(numerador, denominador);
        
        BigDecimal senoDois = MyMathUtils.senoRAD(DOIS);
        BigDecimal senoDoisRAD = MyMathUtils.seno(DOIS);
        BigDecimal cosDois = MyMathUtils.cossenoRAD(DOIS);
        BigDecimal tgDois = MyMathUtils.tg(DOIS);
        MyMathUtils.printNum("SENODOIS", senoDois);
        MyMathUtils.printNum("SENODOISRAD", senoDoisRAD);
       
   
        MyMathUtils.printNum("senoQuadradoLatitude", senoQuadradoLatitude);
        MyMathUtils.printNum("getCalculoV", resposta);
        return resposta;
    }

    private BigDecimal getCalculoNlinha(BigDecimal latitude, BigDecimal longitude) {
        BigDecimal resposta = kZero.multiply(mMinusculo(latitude)
                .add(termoQuatro(latitude, longitude))
                .add(termoCinco(latitude, longitude))
                .add(termoSeis(latitude, longitude))
                .add(termoSete(latitude, longitude)));
        
        MyMathUtils.printNum("getCalculoNlinha", resposta);
        return resposta;
    }

    private BigDecimal getCalculoElinha(BigDecimal latitude, BigDecimal longitude) {
        BigDecimal termoUm = termoUm(latitude, longitude);
        BigDecimal termoDois = termoDois(latitude, longitude);
        BigDecimal termoTres = termoTres(latitude, longitude);

        BigDecimal resposta = (kZero.multiply(getCalculoV(latitude))
                .multiply(getCalculoCoeficienteW(longitude))
                .multiply(MyMathUtils.cossenoRAD(latitude))
                .multiply(UM.add(termoUm).add(termoDois).add(termoTres)));
        
        MyMathUtils.printNum("getCalculoElinha", resposta);
        return resposta;
    }

    public void atribuirHemisferioEscolhido(String hemisferioEscolhido) {
        this.hemisferioEscolhido = hemisferioEscolhido;
    }

    private void atribuirValorNZero(BigDecimal nZero) {
        this.nZero = nZero;
    }

    private BigDecimal getCalculoNorteUTM(BigDecimal latitude, BigDecimal longitude) {

        BigDecimal norteRetorno = BigDecimal.ZERO;
        if (latitude.compareTo(latitude) > 0) {

            atribuirValorNZero(MyMathUtils.createBigDecimal("0"));
            norteRetorno = nZero.add(getCalculoNlinha(latitude, longitude));
        }
        else {
        
            atribuirValorNZero(MyMathUtils.createBigDecimal("10000000"));
            norteRetorno = nZero.add(getCalculoNlinha(latitude, longitude));
        }

         MyMathUtils.printNum("nZero", nZero); 
         MyMathUtils.printNum("getCalculoNorteUTM", norteRetorno);
        return norteRetorno;

    }

    private BigDecimal getCalculoLesteUTM(BigDecimal latitude, BigDecimal longitude) {
        BigDecimal lesteRetorno = falsoLeste.add(getCalculoElinha(latitude, longitude));
        
        MyMathUtils.printNum("getCalculoLesteUTM", lesteRetorno);
        return lesteRetorno;
    }

    public CoordCartograficaUTM realizarCalculoCartUTM(BigDecimal latitude, BigDecimal longitude) {

        MyMathUtils.printNum("LONGITUDE", longitude);
        MyMathUtils.printNum("LATITUDE", latitude);

        BigDecimal norteUTM = getCalculoNorteUTM(latitude, longitude);
        BigDecimal lesteUTM = getCalculoLesteUTM(latitude, longitude);
        BigDecimal MCUTM =   getCalculoLongitudeMeridianoCentral(longitude);

        String norteUTMString = MyMathUtils.numeroToString(norteUTM, MyMathUtils.globalMathContext_4);
        String lesteUTMString = MyMathUtils.numeroToString(lesteUTM, MyMathUtils.globalMathContext_4);
        String MCUTMString = MyMathUtils.numeroToString(MCUTM, MyMathUtils.globalMathContext_0);

        CoordCartograficaUTM coordenadaCartograficaUTM = new CoordCartograficaUTM();
        coordenadaCartograficaUTM.norteUTM = norteUTMString;
        coordenadaCartograficaUTM.lesteUTM = lesteUTMString;
        coordenadaCartograficaUTM.mc = MCUTMString;
        
        return coordenadaCartograficaUTM;
    }

}
