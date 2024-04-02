package algorithm;

import support.input.Console;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class BinarySearch {
    BigDecimal a, b, x, accuracy, f1, f2, f3, module, x2;
    int functionNumber, iterationCount;

    public BinarySearch(BigDecimal a, BigDecimal b, BigDecimal accuracy, int functionNumber) {
        this.a = a;
        this.b = b;
        this.accuracy = accuracy;
        this.functionNumber = functionNumber;
        iterationCount = 0;
        module = a.subtract(b).abs();
        x2 = new BigDecimal(999999);
        x = new BigDecimal(0);
    }

    public ArrayList<ArrayList<BigDecimal>> solve(){
        ArrayList<ArrayList<BigDecimal>> answer = new ArrayList<ArrayList<BigDecimal>>();
        while (((module.compareTo(accuracy) > 0) && (x2.subtract(x).abs().compareTo(accuracy) > 0)) || (iterationCount < 3)) {
            answer.add(new ArrayList<BigDecimal>());
            module = a.subtract(b).abs();
            x2 = x;
            x = a.add(b).multiply(new BigDecimal(0.5));
            f1 = Functions.function(x, functionNumber);
            f2 = Functions.function(a, functionNumber);
            f3 = Functions.function(b, functionNumber);
            answer.get(iterationCount).add(new BigDecimal(iterationCount));
            answer.get(iterationCount).add(a.setScale(7, RoundingMode.HALF_EVEN));
            answer.get(iterationCount).add(b.setScale(7, RoundingMode.HALF_EVEN));
            answer.get(iterationCount).add(x.setScale(7, RoundingMode.HALF_EVEN));
            answer.get(iterationCount).add(f1.setScale(7, RoundingMode.HALF_EVEN));
            answer.get(iterationCount).add(f2.setScale(7, RoundingMode.HALF_EVEN));
            answer.get(iterationCount).add(f3.setScale(7, RoundingMode.HALF_EVEN));
            answer.get(iterationCount).add(module.setScale(7, RoundingMode.HALF_EVEN));
            /*Console.print(iterationCount + " " + a + " " + b + " " + x + " " + f1 + " " + f2 + " " + f3 + " " + module, "а");
            Console.print((module.compareTo(accuracy) > 0)? "да" : "нет", "г");
            Console.print((x2.subtract(x).abs().compareTo(accuracy) > 0)? "да" : "нет", "г");
            Console.print(x.toString(), "г");
            Console.print(x2.toString(), "г");*/
            if (f1.multiply(f2).compareTo(BigDecimal.ZERO) < 0) {
                b = x;
            } else if (f1.multiply(f3).compareTo(BigDecimal.ZERO) < 0) {
                a = x;
            }
            iterationCount += 1;
        }
        return answer;
    }
}
