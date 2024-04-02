package algorithm;

import support.input.Console;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Secants {
    BigDecimal a, b, x1, x2, x3, accuracy, f1, f2, f3, module;
    int functionNumber, iterationCount;

    public Secants(BigDecimal a, BigDecimal b, BigDecimal accuracy, int functionNumber) {
        this.a = a;
        this.b = b;
        this.accuracy = accuracy;
        this.functionNumber = functionNumber;
        iterationCount = 0;
    }

    public void setup(){
        if (Functions.function(a, functionNumber).multiply(Functions.function_second(a, functionNumber)).compareTo(BigDecimal.ZERO) > 0){
            x1 = a;
        }
        else if (Functions.function(b, functionNumber).multiply(Functions.function_second(b, functionNumber)).compareTo(BigDecimal.ZERO) > 0){
            x1 = b;
        }
        else{
            Console.print("Метод секущих не может обеспечить сходимость!", "к");
            x1 = a;
        }
        /*System.out.println(Functions.function(a, functionNumber));
        System.out.println(Functions.function_second(a, functionNumber));
        System.out.println(Functions.function(b, functionNumber));
        System.out.println(Functions.function_second(b, functionNumber));
        System.out.println(Functions.function(b, functionNumber).multiply(Functions.function_second(b, functionNumber)));
        System.out.println(Functions.function(a, functionNumber).multiply(Functions.function_second(a, functionNumber)));*/
        x2 = a.add(b).multiply(new BigDecimal(0.5));
        module = x1.subtract(x2).abs();
    }

    public ArrayList<ArrayList<BigDecimal>> solve(){
        ArrayList<ArrayList<BigDecimal>> answer = new ArrayList<ArrayList<BigDecimal>>();
        while (module.compareTo(accuracy) > 0){
            answer.add(new ArrayList<BigDecimal>());
            /*System.out.println("\n\n\nasd");
            System.out.println(x1);
            System.out.println(x2);*/
            f1 = Functions.function(x1, functionNumber);
            f2 = Functions.function(x2, functionNumber);
            /*System.out.println("\n\n\ndsa");
            System.out.println(f1);
            System.out.println(f2);*/
            x3 = x2.subtract(x2.subtract(x1).divide(f2.subtract(f1), RoundingMode.HALF_EVEN).multiply(f2));
            f3 = Functions.function(x3, functionNumber);
            module = x2.subtract(x3).abs();
            answer.get(iterationCount).add(new BigDecimal(iterationCount));
            answer.get(iterationCount).add(x1.setScale(7, RoundingMode.HALF_EVEN));
            answer.get(iterationCount).add(x2.setScale(7, RoundingMode.HALF_EVEN));
            answer.get(iterationCount).add(x3.setScale(7, RoundingMode.HALF_EVEN));
            answer.get(iterationCount).add(f3.setScale(7, RoundingMode.HALF_EVEN));
            answer.get(iterationCount).add(module.setScale(7, RoundingMode.HALF_EVEN));
            //Console.print(iterationCount + " " + x1 + " " + x2 + " " + x3 + " " + f3 + " " + module, "б");
            iterationCount += 1;
            BigDecimal temp = x2;
            x2 = x3;
            x1 = temp;
        }
        return answer;
    }
}
