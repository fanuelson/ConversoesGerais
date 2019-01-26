package novo;

import java.math.BigDecimal;
import leitorArquivo.CoordCartesiana;
import leitorArquivo.CoordGeodesica;
import utils.MyMathUtils;

public class CalculadoraGRS80 {

    static BigDecimal DOIS = MyMathUtils.createBigDecimal("2");
    private BigDecimal coeficiente_a = MyMathUtils.createBigDecimal("6378137");        
    private BigDecimal coeficiente_b = MyMathUtils.createBigDecimal("6356752.3141332"); 
    private BigDecimal coeficiente_f = MyMathUtils.createBigDecimal(1 / 298.257222101);

    private BigDecimal getCalculoCoeficienteE() {
        return (coeficiente_f.multiply(MyMathUtils.createBigDecimal("2")))
                .subtract(MyMathUtils.pow(coeficiente_f, 2));
    }

    private BigDecimal getCalculoCoeficienteV(BigDecimal numeroX, BigDecimal numeroY, BigDecimal numeroZ) {

        BigDecimal senofi = MyMathUtils.senoRAD(getCalculoLatitude(numeroX, numeroY, numeroZ));
        BigDecimal denominador = MyMathUtils.sqrt(BigDecimal.ONE.subtract(getCalculoCoeficienteE().multiply(MyMathUtils.pow(senofi, 2))));
        
        MyMathUtils.printNum("SENOFI", senofi);
        return MyMathUtils.dividir(coeficiente_a, denominador);
    }

    private BigDecimal getCalculoCoeficienteP(BigDecimal numero_x, BigDecimal numero_y) {

        return MyMathUtils.sqrt(MyMathUtils.pow(numero_x, 2).add(MyMathUtils.pow(numero_y, 2)));
    }

    private BigDecimal getCalculoTeta(BigDecimal numero_x, BigDecimal numero_y, BigDecimal numero_z) {

        BigDecimal divisao = MyMathUtils.dividir(numero_z.multiply(coeficiente_a), getCalculoCoeficienteP(numero_x, numero_y).multiply(coeficiente_b));
        return MyMathUtils.arctgRAD(divisao);

    }

    private BigDecimal getCalculoElinha2(BigDecimal a, BigDecimal b) {

        BigDecimal divisao = MyMathUtils.dividir(MyMathUtils.pow(a, 2).subtract(MyMathUtils.pow(b, 2)), MyMathUtils.pow(b, 2));
//        MyMathUtils.printNum("E'2", divisao);
        return (divisao);
    }

    private BigDecimal getCalculoLatitude(BigDecimal numeroX, BigDecimal numeroY, BigDecimal numeroZ) {

        BigDecimal seno = MyMathUtils.senoRAD(getCalculoTeta(numeroX, numeroY, numeroZ));
        BigDecimal cosseno = MyMathUtils.cossenoRAD(getCalculoTeta(numeroX, numeroY, numeroZ));
        BigDecimal numerador = (numeroZ).add((getCalculoElinha2(coeficiente_a, coeficiente_b)).multiply(MyMathUtils.pow(MyMathUtils.senoRAD(getCalculoTeta(numeroX, numeroY, numeroZ)), 3)).multiply(coeficiente_b));
        BigDecimal denominador = ((getCalculoCoeficienteP(numeroX, numeroY)).subtract(getCalculoCoeficienteE().multiply(coeficiente_a).multiply(MyMathUtils.pow(MyMathUtils.cossenoRAD(getCalculoTeta(numeroX, numeroY, numeroZ)), 3))));
        BigDecimal numero = MyMathUtils.dividir(numerador, denominador);

//        MyMathUtils.printNum("teta", getCalculoTeta(numeroX, numeroY, numeroZ));
//        MyMathUtils.printNum("senoTeta", seno);
//        MyMathUtils.printNum("cossenoTeta", cosseno);
//        MyMathUtils.printNum("numerador", numerador);
//        MyMathUtils.printNum("denominador", denominador);
//        MyMathUtils.printNum("numero", numero);
            BigDecimal senoDois = MyMathUtils.senoRAD(DOIS);
        BigDecimal senoDoisRAD = MyMathUtils.seno(DOIS);
        BigDecimal cosDois = MyMathUtils.cossenoRAD(DOIS);
        BigDecimal tgDois = MyMathUtils.tg(DOIS);
        MyMathUtils.printNum("SENODOIS", senoDois);
        MyMathUtils.printNum("SENODOISRAD", senoDoisRAD);

        return MyMathUtils.arctgRAD(numero);

    }

    private BigDecimal getCalculoLongitude(BigDecimal numeroX, BigDecimal numeroY) {

        return MyMathUtils.arctg2RAD(numeroX, numeroY);
    }

    private BigDecimal getCalculoAltitude(BigDecimal numeroX, BigDecimal numeroY, BigDecimal numeroZ) {

        return ((MyMathUtils.dividir(getCalculoCoeficienteP(numeroX, numeroY), MyMathUtils.cossenoRAD(getCalculoLatitude(numeroX, numeroY, numeroZ)))).subtract(getCalculoCoeficienteV(numeroX, numeroY, numeroZ)));
    }
    private BigDecimal getCalculoCoeficienteX(BigDecimal numeroFi, BigDecimal numeroLambda, BigDecimal numero_h) {
        BigDecimal cosfi = MyMathUtils.cossenoRAD(numeroFi);
        BigDecimal coslambda = MyMathUtils.cossenoRAD(numeroLambda);
        BigDecimal coeficiente_v = getCalculoCoeficienteV(numeroFi);
        
        MyMathUtils.printNum("cosfi", cosfi);
        MyMathUtils.printNum("coslambda", coslambda);
        MyMathUtils.printNum("coeficiente_v", coeficiente_v);
        
           BigDecimal senoDois = MyMathUtils.senoRAD(DOIS);
        BigDecimal senoDoisRAD = MyMathUtils.seno(DOIS);
        BigDecimal cosDois = MyMathUtils.cossenoRAD(DOIS);
        BigDecimal tgDois = MyMathUtils.tg(DOIS);
        MyMathUtils.printNum("SENODOIS", senoDois);
        MyMathUtils.printNum("SENODOISRAD", senoDoisRAD);
        
        return (coeficiente_v.add(numero_h)).multiply(cosfi).multiply(coslambda);

    }
    
    private BigDecimal getCalculoCoeficienteY(BigDecimal numeroFi, BigDecimal numeroLambda, BigDecimal numero_h) {
        BigDecimal cosfi = MyMathUtils.cossenoRAD(numeroFi);
        BigDecimal senoLambda = MyMathUtils.senoRAD(numeroLambda);
        BigDecimal coeficiente_v = getCalculoCoeficienteV(numeroFi);
        
        return (coeficiente_v.add(numero_h)).multiply(cosfi).multiply(senoLambda);
    }
    
    private BigDecimal getCalculoCoeficienteZ(BigDecimal numeroFi, BigDecimal numeroLambda, BigDecimal numero_h) {
        BigDecimal senofi = MyMathUtils.senoRAD(numeroFi);
        BigDecimal coeficiente_v = getCalculoCoeficienteV(numeroFi);
        BigDecimal coeficiente_e = getCalculoCoeficienteE();
        
        MyMathUtils.printNum("senofi", senofi);
        return ((coeficiente_v.multiply(MyMathUtils.umMenos(coeficiente_e))).add(numero_h).multiply(senofi));
    }
     private BigDecimal getCalculoCoeficienteV(BigDecimal numeroFi) {

        BigDecimal senofi = MyMathUtils.senoRAD(numeroFi);
        BigDecimal denominador = MyMathUtils.sqrt(BigDecimal.ONE.subtract(getCalculoCoeficienteE().multiply(MyMathUtils.pow(senofi,2))));
        
        return MyMathUtils.dividir(coeficiente_a, denominador);
    }

    public CoordGeodesica realizarCalculoGEODGRS80(BigDecimal numeroDigi_x, BigDecimal numeroDigi_y, BigDecimal numeroDigi_z) {

        BigDecimal numeroLatitude = getCalculoLatitude(numeroDigi_x, numeroDigi_y, numeroDigi_z);
        BigDecimal numeroLongitude = getCalculoLongitude(numeroDigi_x, numeroDigi_y);
        BigDecimal numeroAltitude = getCalculoAltitude(numeroDigi_x, numeroDigi_y, numeroDigi_z);

        String latitudeString = MyMathUtils.numeroToString(numeroLatitude, MyMathUtils.globalMathContext_8);
        String longitudeString = MyMathUtils.numeroToString(numeroLongitude, MyMathUtils.globalMathContext_8);
        String altitudeString = MyMathUtils.numeroToString(getCalculoAltitude(numeroDigi_x, numeroDigi_y, numeroDigi_z), MyMathUtils.globalMathContext_4);

        CoordGeodesica coordenadaGeodesica = new CoordGeodesica();
        coordenadaGeodesica.latitude = latitudeString;
        coordenadaGeodesica.longitude = longitudeString;
        coordenadaGeodesica.altitude = altitudeString;

        return coordenadaGeodesica;
    }
public CoordCartesiana realizarCalculoCARTGRS80(BigDecimal numero1, BigDecimal numero2, BigDecimal numero3) {

        BigDecimal coeficienteX = getCalculoCoeficienteX(numero1, numero2, numero3);
        BigDecimal coeficienteY = getCalculoCoeficienteY(numero1, numero2, numero3);
        BigDecimal coeficienteZ = getCalculoCoeficienteZ(numero1, numero2, numero3);
        
        String coeficienteXString = MyMathUtils.numeroToString(coeficienteX, MyMathUtils.globalMathContext_4);
        String coeficienteYString = MyMathUtils.numeroToString(coeficienteY, MyMathUtils.globalMathContext_4);
        String coeficienteZString = MyMathUtils.numeroToString(coeficienteZ, MyMathUtils.globalMathContext_4);
        
        CoordCartesiana coordenadaCartesiana = new CoordCartesiana();
        coordenadaCartesiana.x = coeficienteXString;
        coordenadaCartesiana.y = coeficienteYString;
        coordenadaCartesiana.z = coeficienteZString;

        return coordenadaCartesiana;
    }

}
