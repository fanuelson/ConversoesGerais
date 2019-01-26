package utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class MyMathUtils {

    public static final int ESCALA_PRECISAO = 1000;
    
    public static final MathContext CTX_0_DOWN = new MathContext(0, RoundingMode.DOWN);
    
    public static final RoundingMode roundingModeDown = RoundingMode.DOWN;

    static final MathContext globalMathContext = new MathContext(ESCALA_PRECISAO, roundingModeDown);
    
    
    public static final MathContext globalMathContext_10 = new MathContext(10, RoundingMode.HALF_UP);
    public static final MathContext globalMathContext_8 = new MathContext(8, RoundingMode.HALF_UP);
    public static final MathContext globalMathContext_3 = new MathContext(8, RoundingMode.HALF_UP);
    public static final MathContext globalMathContext_4 = new MathContext(4, RoundingMode.HALF_UP);
    public static final MathContext globalMathContext_0 = new MathContext(0, RoundingMode.HALF_UP);
    
    public static BigDecimal senoRAD(BigDecimal numero) {
        return createBigDecimal(Math.sin(numero.doubleValue() * Math.PI / 180));
    }
    
    public static BigDecimal seno(BigDecimal numero) {
        return createBigDecimal(Math.sin(numero.doubleValue()));  //* Math.PI / 180));
    }

    public static BigDecimal cossenoRAD(BigDecimal numero) {
        return createBigDecimal(Math.cos(numero.doubleValue() * Math.PI / 180));
    }
    
    public static BigDecimal tgRAD(BigDecimal numero) {
        return createBigDecimal(Math.tan(numero.doubleValue() *  Math.PI / 180 ));
    }
    
    public static BigDecimal tg(BigDecimal numero) {
        return createBigDecimal(Math.tan(numero.doubleValue())); // *  Math.PI / 180 ));
    }
    
    public static BigDecimal secRAD(BigDecimal numero) {
        return createBigDecimal(1/Math.cos(numero.doubleValue() * Math.PI / 180));
    }
    
    public static BigDecimal sec(BigDecimal numero) {
        return createBigDecimal(1/Math.cos(numero.doubleValue()));
    }
    
    public static BigDecimal arctg2RAD(BigDecimal numeroX, BigDecimal numeroY) {
        return createBigDecimal((Math.atan2(numeroY.doubleValue(), numeroX.doubleValue())) * (180 / Math.PI));
    }

    public static BigDecimal arctgRAD(BigDecimal numero) {
        return createBigDecimal(Math.atan(numero.doubleValue()) * 180 / Math.PI);
    }

    public static BigDecimal pow(BigDecimal numero, int expoente) {
        return createBigDecimal(Math.pow(numero.doubleValue(), expoente));
    }
    
    public static BigDecimal pow(BigDecimal numero, double expoente) {
        return createBigDecimal(Math.pow(numero.doubleValue(), expoente));
    }
    
    public static BigDecimal quadrado(BigDecimal numero) {
        return pow(numero, 2);
    }
    
    public static BigDecimal cubo(BigDecimal numero) {
        return pow(numero, 3);
    }

    public static BigDecimal sqrt(BigDecimal numero) {
        return createBigDecimal(Math.sqrt(numero.doubleValue()));
    }

    public static BigDecimal dividir(BigDecimal num1, BigDecimal num2) {
        return num1.divide(num2, globalMathContext.getPrecision(), globalMathContext.getRoundingMode());
    }
    
    public static BigDecimal dividir(BigDecimal num1, BigDecimal num2, MathContext ctx) {
        return num1.divide(num2, ctx.getPrecision(), ctx.getRoundingMode());
    }

    public static BigDecimal umMenos(BigDecimal numero) {
        return BigDecimal.ONE.subtract(numero);
    }

    public static BigDecimal createBigDecimal(String num) {
        return new BigDecimal(num).setScale(globalMathContext.getPrecision(), globalMathContext.getRoundingMode());
    }

    public static BigDecimal createBigDecimal(int num) {
        return new BigDecimal(num).setScale(globalMathContext.getPrecision(), globalMathContext.getRoundingMode());
    }

    public static BigDecimal createBigDecimal(double num) {
        return new BigDecimal(num).setScale(globalMathContext.getPrecision(), globalMathContext.getRoundingMode());
    }

    public static BigDecimal createBigDecimal(String num, MathContext context) {
        return new BigDecimal(num).setScale(context.getPrecision(), context.getRoundingMode());
    }

    public static BigDecimal createBigDecimal(int num, MathContext context) {
        return new BigDecimal(num).setScale(context.getPrecision(), context.getRoundingMode());
    }

    public static BigDecimal createBigDecimal(double num, MathContext context) {
        return new BigDecimal(num).setScale(context.getPrecision(), context.getRoundingMode());
    }

    public static String numeroToString(BigDecimal num, MathContext context) {
        if (context != null) {
            return num.setScale(context.getPrecision(), context.getRoundingMode()).toString();
        }

        return num.setScale(globalMathContext.getPrecision(), globalMathContext.getRoundingMode()).toString();
    }

    public static void printNum(String nomeNumero, BigDecimal num) {
        System.out.print(nomeNumero + ": ");
        System.out.println(num);
    }
    
    public static void printNum(String nomeNumero, String num) {
        System.out.print(nomeNumero + ": ");
        System.out.println(num);
    }

}
