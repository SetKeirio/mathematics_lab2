package algorithm;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Functions {
    public static BigDecimal function(BigDecimal x, int functionNumber){
        switch (functionNumber){
            case 1:
                return f1(x);
            case 2:
                return f2(x);
            case 3:
                return f3(x);
            case 4:
                return f4(x);
            case 5:
                return f5(x);
        }
        return new BigDecimal(0);
    }

    public static BigDecimal function_first(BigDecimal x, int functionNumber){
        switch (functionNumber){
            case 1:
                return f1_first(x);
            case 2:
                return f2_first(x);
            case 3:
                return f3_first(x);
            case 4:
                return f4_first(x);
            case 5:
                return f5_first(x);
        }
        return new BigDecimal(0);
    }

    public static BigDecimal function_second(BigDecimal x, int functionNumber){
        switch (functionNumber){
            case 1:
                return f1_second(x);
            case 2:
                return f2_second(x);
            case 3:
                return f3_second(x);
            case 4:
                return f4_second(x);
            case 5:
                return f5_second(x);
        }
        return new BigDecimal(0);
    }

    public static BigDecimal function_iteration(BigDecimal x, int functionNumber, BigDecimal l){
        BigDecimal temp = new BigDecimal(0);
        switch (functionNumber){
            case 1:
                temp = f1(x);
            case 2:
                temp = f2(x);
            case 3:
                temp = f3(x);
            case 4:
                temp = f4(x);
            case 5:
                temp = f5(x);
        }
        temp = temp.multiply(l).setScale(30, RoundingMode.HALF_EVEN);
        temp = temp.add(x).setScale(30, RoundingMode.HALF_EVEN);
        return temp;
    }

    public static BigDecimal function_iteration_first(BigDecimal x, int functionNumber, BigDecimal l){
        BigDecimal temp = new BigDecimal(0);
        switch (functionNumber){
            case 1:
                temp = f1_first(x);
            case 2:
                temp = f2_first(x);
            case 3:
                temp = f3_first(x);
            case 4:
                temp = f4_first(x);
            case 5:
                temp = f5_first(x);
        }
        temp = temp.multiply(l);
        temp = temp.add(new BigDecimal(1));
        return temp;
    }

    public static BigDecimal function_equation(BigDecimal x, BigDecimal y, int functionNumber){
        switch (functionNumber){
            case 1:
                return e1(x, y);
            case 2:
                return e2(x, y);
            case 3:
                return e3(x, y);
            case 4:
                return e4(x, y);
        }
        return new BigDecimal(0);
    }
    public static BigDecimal function_equation_first_x(BigDecimal x, BigDecimal y, int functionNumber){
        switch (functionNumber){
            case 1:
                return e1_first_x(x, y);
            case 2:
                return e2_first_x(x, y);
            case 3:
                return e3_first_x(x, y);
            case 4:
                return e4_first_x(x, y);
        }
        return new BigDecimal(0);
    }

    public static BigDecimal function_equation_first_y(BigDecimal x, BigDecimal y, int functionNumber){
        switch (functionNumber){
            case 1:
                return e1_first_y(x, y);
            case 2:
                return e2_first_y(x, y);
            case 3:
                return e3_first_y(x, y);
            case 4:
                return e4_first_y(x, y);
        }
        return new BigDecimal(0);
    }

    public static BigDecimal f1(BigDecimal x){
        return (x.pow(3)).multiply(new BigDecimal(3)).add(x.pow(2).multiply(new BigDecimal(2))).add(x).add(new BigDecimal(-3));
    }

    public static BigDecimal f1_first(BigDecimal x){
        return (x.pow(2)).multiply(new BigDecimal(9)).add(x.multiply(new BigDecimal(4))).add(new BigDecimal(1));
    }

    public static BigDecimal f1_second(BigDecimal x){
        return (x.multiply(new BigDecimal(18))).add(new BigDecimal(4));
    }

    public static BigDecimal f2(BigDecimal x){
        return x.pow(2).multiply(new BigDecimal(0.5)).add(x.multiply(new BigDecimal(-1)));
    }

    public static BigDecimal f2_first(BigDecimal x){
        return x.add(new BigDecimal(-1));
    }

    public static BigDecimal f2_second(BigDecimal x){
        return new BigDecimal(1);
    }

    public static BigDecimal f3(BigDecimal x){
        return new BigDecimal(Math.sin(x.doubleValue())).add(x.pow(2).multiply(new BigDecimal(0.5))).add(new BigDecimal(-1));
    }

    public static BigDecimal f3_first(BigDecimal x){
        return x.add(new BigDecimal(Math.cos(x.doubleValue())));
    }

    public static BigDecimal f3_second(BigDecimal x){
        return new BigDecimal(Math.sin(x.doubleValue())).multiply(new BigDecimal(-1)).add(new BigDecimal(1));
    }

    public static BigDecimal f4(BigDecimal x){
        return new BigDecimal(Math.cos(x.doubleValue())).add(new BigDecimal(Math.sin(x.doubleValue())));
    }

    public static BigDecimal f4_first(BigDecimal x){
        return new BigDecimal(Math.cos(x.doubleValue())).add(new BigDecimal(Math.sin(x.doubleValue())).multiply(new BigDecimal(-1)));
    }

    public static BigDecimal f4_second(BigDecimal x){
        return new BigDecimal(Math.cos(x.doubleValue())).multiply(new BigDecimal(-1)).add(new BigDecimal(Math.sin(x.doubleValue())).multiply(new BigDecimal(-1)));
    }

    public static BigDecimal f5(BigDecimal x){
        return (x.pow(3)).add(x.pow(2).multiply(new BigDecimal(-1))).add(x.multiply(new BigDecimal(-2)));
    }

    public static BigDecimal f5_first(BigDecimal x){
        return (x.pow(2)).multiply(new BigDecimal(3)).add(x.multiply(new BigDecimal(-2))).add(new BigDecimal(-2));
    }

    public static BigDecimal f5_second(BigDecimal x){
        return (x.multiply(new BigDecimal(6))).add(new BigDecimal(-2));
    }

    public static BigDecimal e1(BigDecimal x, BigDecimal y){
        return new BigDecimal(Math.sin(x.doubleValue() - 1)).add(y).add(new BigDecimal(-1.5));
    }

    public static BigDecimal e1_first_x(BigDecimal x, BigDecimal y){
        return new BigDecimal(Math.cos(1 - x.doubleValue()));
    }

    public static BigDecimal e1_first_y(BigDecimal x, BigDecimal y){
        return new BigDecimal(1);
    }

    public static BigDecimal e2(BigDecimal x, BigDecimal y){
        return x.add(new BigDecimal(Math.sin(y.doubleValue() + 1)).multiply(new BigDecimal(-1))).add(new BigDecimal(-1));
    }

    public static BigDecimal e2_first_x(BigDecimal x, BigDecimal y){
        return new BigDecimal(1);
    }

    public static BigDecimal e2_first_y(BigDecimal x, BigDecimal y){
        return new BigDecimal(Math.cos(y.doubleValue() + 1)).multiply(new BigDecimal(-1));
    }

    public static BigDecimal e3(BigDecimal x, BigDecimal y){
        return new BigDecimal(Math.cos(x.doubleValue())).add(x.multiply(x).multiply(new BigDecimal(-1))).add(y.multiply(new BigDecimal(-1)));
    }

    public static BigDecimal e3_first_x(BigDecimal x, BigDecimal y){
        return x.multiply(new BigDecimal(-2)).add(new BigDecimal(Math.sin(x.doubleValue())).multiply(new BigDecimal(-1)));
    }

    public static BigDecimal e3_first_y(BigDecimal x, BigDecimal y){
        return new BigDecimal(1);
    }

    public static BigDecimal e4(BigDecimal x, BigDecimal y){
        return x.pow(2).multiply(new BigDecimal(0.5)).add(y.pow(2).multiply(new BigDecimal(2))).add(new BigDecimal(-1));
    }

    public static BigDecimal e1_y(BigDecimal x){
        return new BigDecimal(Math.sin(x.doubleValue() - 1)).multiply(new BigDecimal(-1)).add(new BigDecimal(1.5));
    }

    public static BigDecimal e2_x(BigDecimal y){
        return new BigDecimal(Math.sin(y.doubleValue() + 1)).add(new BigDecimal(1));
    }

    public static BigDecimal e3_y(BigDecimal x){
        return x.pow(2).multiply(new BigDecimal(-1)).add(new BigDecimal(Math.cos(x.doubleValue())));
    }

    public static BigDecimal e4_y1(BigDecimal x){
        return new BigDecimal(0.5).add(x.pow(2).multiply(new BigDecimal(-0.25))).sqrt(new MathContext(3));
    }

    public static BigDecimal e4_y2(BigDecimal x){
        return new BigDecimal(0.5).add(x.pow(2).multiply(new BigDecimal(-0.25))).sqrt(new MathContext(3)).multiply(new BigDecimal(-1));
    }

    public static BigDecimal e4_first_x(BigDecimal x, BigDecimal y){
        return x;
    }

    public static BigDecimal e4_first_y(BigDecimal x, BigDecimal y){
        return y.multiply(new BigDecimal(4));
    }

}
