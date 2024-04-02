package algorithm;

import support.input.Console;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Newton {
    BigDecimal a, b, x1, x2, y1, y2, accuracy, l, function2, f2, module;
    int functionNumber, iterationCount;

    public Newton(BigDecimal a, BigDecimal b, BigDecimal accuracy, int functionNumber) {
        this.a = a;
        this.b = b;
        this.accuracy = accuracy;
        this.functionNumber = functionNumber;
        iterationCount = 0;
        module = new BigDecimal(2);
        x2 = new BigDecimal(9999);
        y2 = new BigDecimal(9999);
    }

    public ArrayList<ArrayList<BigDecimal>> solve(){
        ArrayList<ArrayList<BigDecimal>> answer = new ArrayList<ArrayList<BigDecimal>>();
        while ((module.compareTo(accuracy) > 0) && (iterationCount < 100)) {
        //while (iterationCount < 5){
            answer.add(new ArrayList<BigDecimal>());
            BigDecimal a1 = Functions.function_equation(a, b, functionNumber).negate().setScale(10, RoundingMode.HALF_EVEN);
            BigDecimal a2 = Functions.function_equation(a, b, functionNumber + 1).negate().setScale(10, RoundingMode.HALF_EVEN);
            BigDecimal b1 = Functions.function_equation_first_x(a, b, functionNumber).setScale(10, RoundingMode.HALF_EVEN);
            BigDecimal b2 = Functions.function_equation_first_y(a, b, functionNumber).setScale(10, RoundingMode.HALF_EVEN);
            BigDecimal b3 = Functions.function_equation_first_x(a, b, functionNumber + 1).setScale(10, RoundingMode.HALF_EVEN);
            BigDecimal b4 = Functions.function_equation_first_y(a, b, functionNumber + 1).setScale(10, RoundingMode.HALF_EVEN);
            BigDecimal b5 = new BigDecimal(1).divide(b2, 10, RoundingMode.HALF_EVEN);
            BigDecimal b6 = b3.add(b5.multiply(b1.negate()).multiply(b4)).setScale(10, RoundingMode.HALF_EVEN);
            BigDecimal a3 = a2.add(a1.multiply(b5).multiply(b4).negate()).setScale(10, RoundingMode.HALF_EVEN);
            BigDecimal x1 = a3.divide(b6, 10, RoundingMode.HALF_EVEN).setScale(10, RoundingMode.HALF_EVEN);
            BigDecimal y1 = (b1.multiply(x1).negate().add(a1)).divide(b2, 10, RoundingMode.HALF_EVEN).setScale(10, RoundingMode.HALF_EVEN);
            x1 = a.add(x1);
            y1 = b.add(y1);
            a = x1;
            b = y1;
            answer.get(iterationCount).add(new BigDecimal(iterationCount));
            answer.get(iterationCount).add(x1);
            answer.get(iterationCount).add(y1);
            BigDecimal temp1 = new BigDecimal(5);
            BigDecimal temp2 = new BigDecimal(6);
            Console.print(Functions.function_equation(temp1, temp2, 2).toString(), "г");
            //System.out.println(Math.sin(7));
            Console.print(b1 + "x + " + b2 + "y = " + a1, "г");
            Console.print(b3 + "x + " + b4 + "y = " + a2, "г");
            Console.print(b5.toString(), "г");
            Console.print(b6 + " x = " + a3, "г");
            //Console.print(iterationCount + " " + x1.setScale(7, RoundingMode.HALF_EVEN) + " " + y1.setScale(7, RoundingMode.HALF_EVEN), "г");
            iterationCount += 1;
            if (x1.subtract(x2).abs().compareTo(y1.subtract(y2).abs()) > 0){
                module = x1.subtract(x2).abs();
            }
            else{
                module = y1.subtract(y2).abs();
            }
            x2 = x1;
            y2 = y1;
        }
        return answer;
    }
}
