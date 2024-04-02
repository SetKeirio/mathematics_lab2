package algorithm;

import java.math.BigDecimal;
import java.util.ArrayList;

public class AlgorithmManager {
    public ArrayList<ArrayList<BigDecimal>> solve(int method, int function, BigDecimal left, BigDecimal right, BigDecimal accuracy){
        ArrayList<ArrayList<BigDecimal>> answer = new ArrayList<ArrayList<BigDecimal>>();
        switch (method) {
            case 1:
                answer = binarySearch(function, left, right, accuracy);
                break;
            case 2:
                answer = secants(function, left, right, accuracy);
                break;
            case 3:
                answer = iteration(function, left, right, accuracy);
                break;
        }
        return answer;
    }

    public ArrayList<ArrayList<BigDecimal>> solveEquations(int method, int function, BigDecimal left, BigDecimal right, BigDecimal accuracy){
        ArrayList<ArrayList<BigDecimal>> answer = new ArrayList<ArrayList<BigDecimal>>();
        switch (method) {
            case 1:
                answer = newton(function, left, right, accuracy);
                break;
        }
        return answer;
    }
    public ArrayList<ArrayList<BigDecimal>> binarySearch(int function, BigDecimal left, BigDecimal right, BigDecimal accuracy){
        BinarySearch method = new BinarySearch(left, right, accuracy, function);
        ArrayList<ArrayList<BigDecimal>> answer = method.solve();
        return answer;
    }

    public ArrayList<ArrayList<BigDecimal>> secants(int function, BigDecimal left, BigDecimal right, BigDecimal accuracy){
        Secants method = new Secants(left, right, accuracy, function);
        method.setup();
        ArrayList<ArrayList<BigDecimal>> answer = method.solve();
        return answer;
    }

    public ArrayList<ArrayList<BigDecimal>> iteration(int function, BigDecimal left, BigDecimal right, BigDecimal accuracy){
        Iteration method = new Iteration(left, right, accuracy, function);
        method.setup();
        ArrayList<ArrayList<BigDecimal>> answer = method.solve();
        return answer;

    }

    public ArrayList<ArrayList<BigDecimal>> newton(int function, BigDecimal left, BigDecimal right, BigDecimal accuracy){
        Newton method = new Newton(left, right, accuracy, function);
        ArrayList<ArrayList<BigDecimal>> answer = method.solve();
        return answer;
    }
}
