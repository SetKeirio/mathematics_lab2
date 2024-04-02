package algorithm;

import support.input.Console;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Iteration {
    BigDecimal a, b, x1, x2, accuracy, l, function2, f2, module;
    int functionNumber, iterationCount;

    public Iteration(BigDecimal a, BigDecimal b, BigDecimal accuracy, int functionNumber) {
        this.a = a;
        this.b = b;
        this.accuracy = accuracy;
        this.functionNumber = functionNumber;
        iterationCount = 0;
    }

    public void setup(){
        BigDecimal y1, y2;
        y1 = Functions.function_first(a, functionNumber);
        y2 = Functions.function_first(b, functionNumber);
        if (y1.abs().compareTo(y2.abs()) > 0){
            if (y1.compareTo(BigDecimal.ZERO) > 0){
                l = new BigDecimal(-1).divide(y1, 30, RoundingMode.HALF_EVEN);
            }
            else{
                l = new BigDecimal(1).divide(y1, 30, RoundingMode.HALF_EVEN);
            }
        }
        else{
            if (y2.compareTo(BigDecimal.ZERO) > 0){
                l = new BigDecimal(-1).divide(y2, 30, RoundingMode.HALF_EVEN);
            }
            else{
                l = new BigDecimal(1).divide(y2, 30, RoundingMode.HALF_EVEN);
            }
        }
        //System.out.println(l + " " + y1 + " " + y2);
        y1 = Functions.function_iteration_first(a, functionNumber, l);
        y2 = Functions.function_iteration_first(b, functionNumber, l);
        //System.out.println(l + " " + y1 + " " + y2);
        if ((y1.abs().compareTo(new BigDecimal(1)) <= 0) || (y2.abs().compareTo(new BigDecimal(1)) <= 0)){
            Console.print("Условие сходимости выполнено!", "г");
        }
        else{
            Console.print("Условие сходимости не выполнено!", "к");
        }
        if (y1.abs().compareTo(y2.abs()) < 0){
            x1 = a;
        }
        else{
            x1 = b;
        }
        module = a.subtract(b).abs();
    }

    public ArrayList<ArrayList<BigDecimal>> solve(){
        ArrayList<ArrayList<BigDecimal>> answer = new ArrayList<ArrayList<BigDecimal>>();
        while (module.compareTo(accuracy) > 0 || iterationCount < 3){
            answer.add(new ArrayList<BigDecimal>());
        //while (iterationCount < 5){
            x2 = Functions.function_iteration(x1, functionNumber, l);
            function2 = Functions.function_iteration(x2, functionNumber, l);
            f2 = Functions.function(x2, functionNumber);
            module = x1.subtract(x2).abs();
            answer.get(iterationCount).add(new BigDecimal(iterationCount));
            answer.get(iterationCount).add(x1.setScale(7, RoundingMode.HALF_EVEN));
            answer.get(iterationCount).add(x2.setScale(7, RoundingMode.HALF_EVEN));
            answer.get(iterationCount).add(function2.setScale(7, RoundingMode.HALF_EVEN));
            answer.get(iterationCount).add(f2.setScale(7, RoundingMode.HALF_EVEN));
            answer.get(iterationCount).add(module.setScale(7, RoundingMode.HALF_EVEN));
            Console.print(iterationCount + " " + x1.setScale(7, RoundingMode.HALF_EVEN) + " " + x2.setScale(5, RoundingMode.HALF_EVEN) + " " + function2.setScale(5, RoundingMode.HALF_EVEN) + " " + f2.setScale(5, RoundingMode.HALF_EVEN) + " " + module.setScale(5, RoundingMode.HALF_EVEN), "г");
            iterationCount += 1;
            x1 = x2;
        }
        return answer;
    }

}
