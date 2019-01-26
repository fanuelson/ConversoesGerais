
package novo;

import java.math.BigDecimal;
import java.math.MathContext;
import leitorArquivo.CoordCartograficaUTM;
import leitorArquivo.CoordGeodesica;
import static novo.CalculadoraGEOparaUTM.DOIS;
import static novo.CalculadoraGEOparaUTM.SEIS;
import static novo.CalculadoraGEOparaUTM.TRES;
import utils.MyMathUtils;


public class CalculadoraUTMparaGEO {
    
    static BigDecimal UM = BigDecimal.ONE;
    static BigDecimal DOIS = MyMathUtils.createBigDecimal("2");
    static BigDecimal TRES = MyMathUtils.createBigDecimal("3");
    static BigDecimal QUATRO = MyMathUtils.createBigDecimal("4");
    static BigDecimal CINCO = MyMathUtils.createBigDecimal("5");
    static BigDecimal SEIS = MyMathUtils.createBigDecimal("6");
    static BigDecimal OITO = MyMathUtils.createBigDecimal("8");
    static BigDecimal NOVE = MyMathUtils.createBigDecimal("9");
    static BigDecimal DOZE = MyMathUtils.createBigDecimal("12");
    static BigDecimal ONZE = MyMathUtils.createBigDecimal("11");
    
    private BigDecimal kZero = MyMathUtils.createBigDecimal("0.9996");
    private BigDecimal falsoNorteHN = MyMathUtils.createBigDecimal("0"); 
    private BigDecimal falsoNorteHS = MyMathUtils.createBigDecimal("10000000");
    private BigDecimal falsoLeste = MyMathUtils.createBigDecimal("500000");
    private BigDecimal nZero;
    private BigDecimal nLinha;
    private BigDecimal eLinha;
   // private String hemisferio;
    private BigDecimal coeficiente_a = MyMathUtils.createBigDecimal("6378137");
    private BigDecimal coeficiente_f = MyMathUtils.createBigDecimal(1/298.257222101);

    private BigDecimal getCalculoNlinha(BigDecimal norteUTM, String hemisferio ) {
        BigDecimal nLinha = BigDecimal.ZERO;
        //String hn = null;
        if(hemisferio.equals("HN")) {
            nLinha = norteUTM;
        }
        else {
            nLinha = norteUTM.subtract(falsoNorteHS);
        }
            //MyMathUtils.printNum("nLinha", nLinha);
            return nLinha;
    }
    
    private BigDecimal mMinusculo(BigDecimal norteUTM, String hemisferio) {
        BigDecimal mMinusculo = MyMathUtils.dividir(getCalculoNlinha(norteUTM, hemisferio), kZero);
        
        //MyMathUtils.printNum("mMinusculo", mMinusculo);
        return mMinusculo;
                
    }
    
    private BigDecimal nMinusculo() {
        BigDecimal subtracao = DOIS.subtract(coeficiente_f);
        BigDecimal nMinusculo = MyMathUtils.dividir(coeficiente_f, subtracao); 
        
        //MyMathUtils.printNum("nMinusculo", nMinusculo);
        return  nMinusculo;         
    }
    
    private BigDecimal gMaiusculo() {
        BigDecimal parentesesUm = UM.subtract(nMinusculo());
        BigDecimal parentesesDois = UM.subtract(MyMathUtils.pow(nMinusculo(), 2));
        BigDecimal parentesesTres = MyMathUtils.dividir((NOVE.multiply(MyMathUtils.pow(nMinusculo(), 2))), QUATRO);
        BigDecimal parentesesQuatro = MyMathUtils.dividir((MyMathUtils.createBigDecimal(225).multiply(MyMathUtils.pow(nMinusculo(), 4))), MyMathUtils.createBigDecimal(64));
        BigDecimal parentesesCinco = MyMathUtils.dividir( MyMathUtils.createBigDecimal(Math.PI) , MyMathUtils.createBigDecimal(180));
        BigDecimal parentesesGrande = UM.add(parentesesTres).add(parentesesQuatro);
        BigDecimal gMaiusculo = coeficiente_a.multiply(parentesesUm).multiply(parentesesDois).multiply(parentesesGrande).multiply(parentesesCinco);
        
        //MyMathUtils.printNum("gMaiusculo", gMaiusculo);
        return gMaiusculo;
    } 
    
     private BigDecimal sigma(BigDecimal norteUTM, String hemisferio) {   
         BigDecimal Numerador = mMinusculo(norteUTM, hemisferio).multiply(MyMathUtils.createBigDecimal(Math.PI));
         BigDecimal Denominador = MyMathUtils.createBigDecimal(180).multiply(gMaiusculo());
         BigDecimal sigma = MyMathUtils.dividir(Numerador, Denominador);
         
        //MyMathUtils.printNum("sigma", sigma);
        return sigma;       
    }
    
    private BigDecimal latitudeLinhaRadiano(BigDecimal norteUTM, String hemisferio) {   
         BigDecimal parentesesUm = MyMathUtils.dividir((TRES.multiply(nMinusculo())), DOIS);
         BigDecimal parentesesDois = MyMathUtils.dividir((MyMathUtils.createBigDecimal(27).multiply(MyMathUtils.cubo(nMinusculo()))), MyMathUtils.createBigDecimal(32));
         BigDecimal parentesesTres = MyMathUtils.dividir((MyMathUtils.createBigDecimal(21).multiply(MyMathUtils.quadrado(nMinusculo()))), MyMathUtils.createBigDecimal(16));
         BigDecimal parentesesQuatro = MyMathUtils.dividir((MyMathUtils.createBigDecimal(55).multiply(MyMathUtils.pow(nMinusculo(), 4))), MyMathUtils.createBigDecimal(32));
         BigDecimal parentesesCinco = MyMathUtils.dividir((MyMathUtils.createBigDecimal(151).multiply(MyMathUtils.cubo(nMinusculo()))), MyMathUtils.createBigDecimal(96));
         BigDecimal parentesesSeis = MyMathUtils.dividir((MyMathUtils.createBigDecimal(1097).multiply(MyMathUtils.pow(nMinusculo(), 4))), MyMathUtils.createBigDecimal(512));
         BigDecimal parentesesGrandeUm = parentesesUm.subtract(parentesesDois);
         BigDecimal parentesesGrandeDois = parentesesTres.subtract(parentesesQuatro);
         BigDecimal senoDoisSigma = MyMathUtils.seno(DOIS.multiply(sigma(norteUTM,hemisferio)));
         BigDecimal senoQuatroSigma = MyMathUtils.seno(QUATRO.multiply(sigma(norteUTM,hemisferio)));
         BigDecimal senoSeisSigma = MyMathUtils.seno(SEIS.multiply(sigma(norteUTM,hemisferio)));
         BigDecimal senoOitoSigma = MyMathUtils.seno(OITO.multiply(sigma(norteUTM,hemisferio)));
         
         BigDecimal latitudeLinhaRadiano = sigma(norteUTM,hemisferio)
                 .add(parentesesGrandeUm.multiply(senoDoisSigma))
                 .add(parentesesGrandeDois.multiply(senoQuatroSigma))
                 .add(parentesesCinco.multiply(senoSeisSigma))
                 .add(parentesesSeis.multiply(senoOitoSigma));
         
         return latitudeLinhaRadiano;
         
    }
    private BigDecimal getCalculoCoeficienteE() {
        BigDecimal coeficiente_e = (coeficiente_f.multiply(DOIS))
                .subtract(MyMathUtils.quadrado(coeficiente_f));
        
        return coeficiente_e;
    }
    
    private BigDecimal vLinha(BigDecimal norteUTM, String hemisferio) {   
        BigDecimal senoLatitudeLinhaRadiano = MyMathUtils.seno(latitudeLinhaRadiano(norteUTM,hemisferio));
        BigDecimal eQuadrado = getCalculoCoeficienteE();
        BigDecimal quadradoSenoLatitudeLinhaRadiano = MyMathUtils.quadrado(senoLatitudeLinhaRadiano);
        BigDecimal eQuadradoVezesQuadradoSenoLatitudeLinhaRadiano = eQuadrado.multiply(quadradoSenoLatitudeLinhaRadiano);
        BigDecimal conta = MyMathUtils.umMenos(eQuadradoVezesQuadradoSenoLatitudeLinhaRadiano);

        BigDecimal numerador = coeficiente_a;
        BigDecimal denominador = MyMathUtils.sqrt(conta);
        BigDecimal vLinha = MyMathUtils.dividir(numerador, denominador);
        
        MyMathUtils.printNum("vLinha", vLinha);
        MyMathUtils.printNum("vLinhaNumerador", numerador);
        MyMathUtils.printNum("vLinhaDenominador", denominador);
        return vLinha;
                  
    }
    
    private BigDecimal pLinha(BigDecimal norteUTM, String hemisferio) {
        BigDecimal eQuadrado = getCalculoCoeficienteE();
        BigDecimal senoLatitudeLinhaRadiano = MyMathUtils.seno(latitudeLinhaRadiano(norteUTM,hemisferio));
        BigDecimal quadradoSenoLatitudeLinhaRadiano = MyMathUtils.quadrado(senoLatitudeLinhaRadiano);
        BigDecimal eQuadradoVezesQuadradoSenoLatitudeLinhaRadiano = eQuadrado.multiply(quadradoSenoLatitudeLinhaRadiano);
        BigDecimal conta = BigDecimal.ONE.subtract(eQuadradoVezesQuadradoSenoLatitudeLinhaRadiano);
       // BigDecimal tresDivididoDois = MyMathUtils.dividir(TRES, DOIS);
        BigDecimal tresDois = MyMathUtils.createBigDecimal("1.5");

        BigDecimal numerador = coeficiente_a.multiply(UM.subtract(eQuadrado));
        BigDecimal denominador = MyMathUtils.pow(conta, tresDois.doubleValue()); //tresDivididoDois.intValue());
        BigDecimal pLinha = MyMathUtils.dividir(numerador, denominador);
        
        MyMathUtils.printNum("pLinha", pLinha);
        return pLinha;
    }
    
    private BigDecimal psiLinha(BigDecimal norteUTM, String hemisferio) {
        BigDecimal psi = MyMathUtils.dividir(vLinha(norteUTM,hemisferio), pLinha(norteUTM,hemisferio));
        
        MyMathUtils.printNum("psi", psi);
        return psi;
    }
    
    private BigDecimal Elinha (BigDecimal lesteUTM) {
        BigDecimal eLinha = lesteUTM.subtract(falsoLeste);
        
       // MyMathUtils.printNum("eLinha", eLinha);
        return eLinha;
    }
    
    private BigDecimal tLinha (BigDecimal norteUTM, String hemisferio){
       BigDecimal tLinha = MyMathUtils.tg(latitudeLinhaRadiano(norteUTM,hemisferio)); 
       
       MyMathUtils.printNum("tLinha", tLinha);
       return tLinha;
    }
    
    private BigDecimal getCalculoX (BigDecimal norteUTM, BigDecimal lesteUTM, String hemisferio){
       BigDecimal x = MyMathUtils.dividir(Elinha(lesteUTM), (kZero.multiply(vLinha(norteUTM,hemisferio))));
       
       MyMathUtils.printNum("X", x);
       return x;
    }
    
    private BigDecimal termoUm (BigDecimal norteUTM, BigDecimal lesteUTM, String hemisferio){
       BigDecimal parentesesUm = MyMathUtils.dividir(tLinha(norteUTM,hemisferio), (kZero.multiply(pLinha(norteUTM,hemisferio))));
       BigDecimal parentesesDois = MyMathUtils.dividir((getCalculoX(norteUTM,lesteUTM, hemisferio).multiply(Elinha(lesteUTM))) , DOIS );
       BigDecimal termoUm = parentesesUm.multiply(parentesesDois);
       
       MyMathUtils.printNum("termoUm", termoUm);
       return termoUm;
    }
    
    private BigDecimal termoDois (BigDecimal norteUTM, BigDecimal lesteUTM, String hemisferio){
       
       BigDecimal parentesesUm = MyMathUtils.dividir(tLinha(norteUTM,hemisferio), (kZero.multiply(pLinha(norteUTM,hemisferio))));
       BigDecimal parentesesDois = MyMathUtils.dividir((MyMathUtils.cubo(getCalculoX(norteUTM, lesteUTM, hemisferio)).multiply(Elinha(lesteUTM))) , MyMathUtils.createBigDecimal(24));
       BigDecimal parentesesDentroColchete = UM.subtract(MyMathUtils.quadrado(tLinha(norteUTM,hemisferio)));
       BigDecimal colchetes = (NOVE.multiply(psiLinha(norteUTM,hemisferio)).multiply(parentesesDentroColchete)).subtract(QUATRO.multiply(MyMathUtils.quadrado(psiLinha(norteUTM,hemisferio)))).add(DOZE.multiply(MyMathUtils.quadrado(tLinha(norteUTM,hemisferio))));
       BigDecimal termoDois = parentesesUm.multiply(parentesesDois).multiply(colchetes);
       
       MyMathUtils.printNum("termoDois", termoDois);
       return termoDois;
    }
    
    private BigDecimal termoTres (BigDecimal norteUTM, BigDecimal lesteUTM, String hemisferio){
       
       BigDecimal parentesesUm = MyMathUtils.dividir(tLinha(norteUTM,hemisferio), (kZero.multiply(pLinha(norteUTM,hemisferio))));
       BigDecimal parentesesDois = MyMathUtils.dividir((MyMathUtils.pow(getCalculoX(norteUTM, lesteUTM, hemisferio), 5).multiply(Elinha(lesteUTM))) , MyMathUtils.createBigDecimal(720));
       BigDecimal parentesesDentroUm = ONZE.subtract(MyMathUtils.createBigDecimal(24).multiply(MyMathUtils.quadrado(tLinha(norteUTM,hemisferio))));
       BigDecimal parentesesDentroDois = MyMathUtils.createBigDecimal(21).subtract(MyMathUtils.createBigDecimal(71).multiply(MyMathUtils.quadrado(tLinha(norteUTM,hemisferio))));
       BigDecimal parentesesDentroTres = MyMathUtils.createBigDecimal(15).subtract(MyMathUtils.createBigDecimal(98).multiply(MyMathUtils.quadrado(tLinha(norteUTM,hemisferio)))).add(MyMathUtils.createBigDecimal(15).multiply(MyMathUtils.pow(tLinha(norteUTM,hemisferio), 4)));
       BigDecimal parentesesDentroQuatro = (CINCO.multiply(MyMathUtils.quadrado(tLinha(norteUTM,hemisferio)))).subtract(TRES.multiply(MyMathUtils.pow(tLinha(norteUTM,hemisferio), 4)));
       
       BigDecimal chaves = (OITO.multiply(MyMathUtils.pow(psiLinha(norteUTM,hemisferio), 4)).multiply(parentesesDentroUm))
               .subtract(DOZE.multiply(MyMathUtils.cubo(psiLinha(norteUTM,hemisferio))).multiply(parentesesDentroDois))
               .add(MyMathUtils.createBigDecimal(15).multiply(MyMathUtils.quadrado(psiLinha(norteUTM,hemisferio))).multiply(parentesesDentroTres))
               .add(MyMathUtils.createBigDecimal(180).multiply(psiLinha(norteUTM,hemisferio)).multiply(parentesesDentroQuatro))
               .add(MyMathUtils.createBigDecimal(360).multiply(MyMathUtils.pow(tLinha(norteUTM,hemisferio), 4)));
       BigDecimal termoTres = parentesesUm.multiply(parentesesDois).multiply(chaves);
       
       MyMathUtils.printNum("termoTres", termoTres);
       return termoTres;
    }
    
    private BigDecimal termoQuatro (BigDecimal norteUTM, BigDecimal lesteUTM,  String hemisferio){
       
       BigDecimal parentesesUm = MyMathUtils.dividir(tLinha(norteUTM,hemisferio), (kZero.multiply(pLinha(norteUTM,hemisferio))));
       BigDecimal parentesesDois = MyMathUtils.dividir((MyMathUtils.pow(getCalculoX(norteUTM, lesteUTM,hemisferio), 7).multiply(Elinha(lesteUTM))) , MyMathUtils.createBigDecimal(40320));
       BigDecimal parentesesTres = MyMathUtils.createBigDecimal(1385).add(MyMathUtils.createBigDecimal(3633).multiply(MyMathUtils.quadrado(tLinha(norteUTM,hemisferio)))).add(MyMathUtils.createBigDecimal(4095).multiply(MyMathUtils.pow(tLinha(norteUTM,hemisferio), 4))).add(MyMathUtils.createBigDecimal(1575).multiply(MyMathUtils.pow(tLinha(norteUTM,hemisferio), 6)));
       BigDecimal multiplicaçao = parentesesUm.multiply(parentesesDois).multiply(parentesesTres);
       
       MyMathUtils.printNum("termoQuatro", multiplicaçao);
       return multiplicaçao;
    }
    
    private BigDecimal latitudeDoPontoP (BigDecimal norteUTM, BigDecimal lesteUTM, String hemisferio) {
       BigDecimal latitudePontoP = (latitudeLinhaRadiano(norteUTM,hemisferio)
                .subtract(termoUm(norteUTM, lesteUTM, hemisferio)).add(termoDois(norteUTM, lesteUTM,hemisferio)).subtract(termoTres(norteUTM, lesteUTM,hemisferio)).add(termoQuatro(norteUTM, lesteUTM,hemisferio)))
                .multiply(MyMathUtils.dividir(MyMathUtils.createBigDecimal(180), MyMathUtils.createBigDecimal(Math.PI)));
       
       MyMathUtils.printNum("latitudePontoP", latitudePontoP);
       return latitudePontoP;     
    }
    
    private BigDecimal termoCinco (BigDecimal norteUTM, BigDecimal lesteUTM, String hemisferio){
       BigDecimal secFi = MyMathUtils.sec(latitudeLinhaRadiano(norteUTM,hemisferio));
       MyMathUtils.printNum("latitudeLinhaRadiano", latitudeLinhaRadiano(norteUTM,hemisferio));
       MyMathUtils.printNum("secFi", secFi);
       MyMathUtils.printNum("secFiRAD", MyMathUtils.secRAD(latitudeLinhaRadiano(norteUTM,hemisferio)));
       BigDecimal termoCinco = getCalculoX(norteUTM, lesteUTM,hemisferio).multiply(secFi);
       
       return termoCinco;
    }
    
    private BigDecimal termoSeis (BigDecimal norteUTM, BigDecimal lesteUTM, String hemisferio){
        BigDecimal parentesesUm = MyMathUtils.dividir((MyMathUtils.cubo(getCalculoX(norteUTM, lesteUTM,hemisferio))), SEIS);
        BigDecimal parentesesDois = psiLinha(norteUTM,hemisferio).add(DOIS.multiply(MyMathUtils.quadrado(tLinha(norteUTM,hemisferio))));
        
        BigDecimal termoSeis = parentesesUm.multiply(MyMathUtils.sec(latitudeLinhaRadiano(norteUTM,hemisferio))).multiply(parentesesDois);
       
       MyMathUtils.printNum("termoSeis", termoSeis);
       return termoSeis;
    }
    
    private BigDecimal termoSete (BigDecimal norteUTM, BigDecimal lesteUTM, String hemisferio){
        BigDecimal parentesesUm = MyMathUtils.dividir(MyMathUtils.pow(getCalculoX(norteUTM, lesteUTM,hemisferio), 5), MyMathUtils.createBigDecimal("120"));
        BigDecimal parentesesDentroUm = UM.subtract(SEIS.multiply(MyMathUtils.quadrado(tLinha(norteUTM,hemisferio))));
        BigDecimal parentesesDentroDois = NOVE.subtract(MyMathUtils.createBigDecimal(68).multiply(MyMathUtils.quadrado(tLinha(norteUTM,hemisferio))));
        BigDecimal colchetes = (MyMathUtils.quadrado(psiLinha(norteUTM,hemisferio)).multiply(parentesesDentroDois))
                .subtract(QUATRO.multiply(MyMathUtils.cubo(psiLinha(norteUTM,hemisferio))).multiply(parentesesDentroUm))
                .add(MyMathUtils.createBigDecimal(72).multiply(psiLinha(norteUTM,hemisferio).multiply(MyMathUtils.quadrado(tLinha(norteUTM,hemisferio)))))
                .add(MyMathUtils.createBigDecimal(24).multiply(MyMathUtils.pow(tLinha(norteUTM,hemisferio), 4)));
        
        BigDecimal termoSete = parentesesUm.multiply(MyMathUtils.sec(latitudeLinhaRadiano(norteUTM,hemisferio))).multiply(colchetes);
       
       MyMathUtils.printNum("termoSete", termoSete);
       return termoSete;
    }
    
    private BigDecimal termoOito (BigDecimal norteUTM, BigDecimal lesteUTM, String hemisferio){
        BigDecimal parentesesUm = MyMathUtils.dividir((MyMathUtils.pow(getCalculoX(norteUTM, lesteUTM,hemisferio), 7)), MyMathUtils.createBigDecimal(5040));
        BigDecimal parentesesDois = MyMathUtils.createBigDecimal(61)
                .add(MyMathUtils.createBigDecimal(662).multiply(MyMathUtils.quadrado(tLinha(norteUTM,hemisferio))))
                .add(MyMathUtils.createBigDecimal(1320).multiply(MyMathUtils.pow(tLinha(norteUTM,hemisferio), 4)))
                .add(MyMathUtils.createBigDecimal(720).multiply(MyMathUtils.pow(tLinha(norteUTM,hemisferio), 6)));
        
        BigDecimal termoOito = parentesesUm.multiply(MyMathUtils.sec(latitudeLinhaRadiano(norteUTM,hemisferio))).multiply(parentesesDois);
       
       MyMathUtils.printNum("termoOito", termoOito);
        return termoOito;
    }
    
    private BigDecimal getCalculoW (BigDecimal norteUTM, BigDecimal lesteUTM, String hemisferio){
       BigDecimal calculoW = termoCinco(norteUTM, lesteUTM,hemisferio)
               .subtract(termoSeis(norteUTM, lesteUTM,hemisferio))
               .add(termoSete(norteUTM, lesteUTM,hemisferio))
               .subtract(termoOito(norteUTM, lesteUTM,hemisferio));
       MyMathUtils.printNum("CALCULO DE  W", calculoW);
       
       MyMathUtils.printNum("termoCinco", termoCinco(norteUTM, lesteUTM,hemisferio));
       //TODO: CONTINUAR IMPRIMINDO OS TERMOS
       
       
       return calculoW;
    }
    
    public BigDecimal longitudeDoVerticeP (BigDecimal norteUTM, BigDecimal lesteUTM, BigDecimal mc, String hemisferio) {
       BigDecimal longitudeVerticeP = mc
               .add(getCalculoW(norteUTM, lesteUTM,hemisferio)
               .multiply(MyMathUtils.dividir((MyMathUtils.createBigDecimal(180)), (MyMathUtils.createBigDecimal(Math.PI)))));
       
       MyMathUtils.printNum("MC ", mc);
       MyMathUtils.printNum("(W vezes 180/PI)" , getCalculoW(norteUTM, lesteUTM,hemisferio)
               .multiply(MyMathUtils.dividir((MyMathUtils.createBigDecimal(180)), (MyMathUtils.createBigDecimal(Math.PI)))));        
       
       MyMathUtils.printNum("longitudeVerticeP", longitudeVerticeP);
       return longitudeVerticeP;     
    }
    
    public void atribuirHemisferioEscolhido(String hemisferioEscolhido) {
//        this.hemisferioEscolhido = hemisferioEscolhido;
    }
    
    private void atribuirValorNZero(BigDecimal nZero) {
        this.nZero = nZero;
    }
    
   /* private BigDecimal getCalculoLatitude(BigDecimal norteUTM, BigDecimal lesteUTM) {
        BigDecimal latitudeRetorno = BigDecimal.ZERO;
        if (hemisferioEscolhido == "Hemisfério Norte") {
            
            atribuirValorNZero(MyMathUtils.createBigDecimal("0"));
            latitudeRetorno = latitudeDoPontoP(norteUTM, lesteUTM);
            
        }

        if (hemisferioEscolhido.equals("Hemisfério Sul")) {
            atribuirValorNZero(MyMathUtils.createBigDecimal("10000000"));
            latitudeRetorno = latitudeDoPontoP(norteUTM, lesteUTM);
        }

        MyMathUtils.printNum("nZero", nZero);
        return latitudeRetorno;
    }
    
    private BigDecimal getCalculoLongitude(BigDecimal norteUTM, BigDecimal lesteUTM, BigDecimal mc) {
        BigDecimal longitudeRetorno = BigDecimal.ZERO;
        if (hemisferioEscolhido == "Hemisfério Norte") {
            
            atribuirValorNZero(MyMathUtils.createBigDecimal("0"));
            longitudeRetorno = longitudeDoVerticeP(norteUTM, lesteUTM, mc);
            
        }

        if (hemisferioEscolhido.equals("Hemisfério Sul")) {
            atribuirValorNZero(MyMathUtils.createBigDecimal("10000000"));
            longitudeRetorno = longitudeDoVerticeP(norteUTM, lesteUTM, mc);
        }

        MyMathUtils.printNum("nZero", nZero);
        return longitudeRetorno;
    }
    
    */

    public CoordGeodesica realizarCalculoGEO(BigDecimal norteUTM, BigDecimal lesteUTM, BigDecimal mc, String hemisferio) {

        MyMathUtils.printNum("NORTEUTM", norteUTM);
        MyMathUtils.printNum("LESTEUTM", lesteUTM);
        
        BigDecimal latitude = latitudeDoPontoP(norteUTM, lesteUTM,hemisferio);
        BigDecimal longitude = longitudeDoVerticeP(norteUTM, lesteUTM, mc,hemisferio);

        String latitudeUTMString = MyMathUtils.numeroToString(latitude, MyMathUtils.globalMathContext_8);
        String longitudeUTMString = MyMathUtils.numeroToString(longitude, MyMathUtils.globalMathContext_8);
        //String altitudeString = MyMathUtils.numeroToString(altitude, MyMathUtils.globalMathContext_4);
        
        CoordGeodesica coordenadaGeodesica = new CoordGeodesica();
        coordenadaGeodesica.latitude = latitudeUTMString;
        coordenadaGeodesica.longitude = longitudeUTMString;
       // coordenadaGeodesica.altitude = altitudeString;
      
        
        return coordenadaGeodesica;
    }
    
    
    
    
    
    
    
    
    
    
    
    
     
    
    
}
