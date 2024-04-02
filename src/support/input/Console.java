package support.input;

import java.math.BigDecimal;

public class Console {
    public static void print(String s, String c){
        String color;
        if (c.equals("к")){
            color = Constants.ANSI_RED;
        }
        else if (c.equals("п")){
            color = Constants.ANSI_PURPLE;
        }
        else if (c.equals("а")){
            color = Constants.ANSI_CYAN;
        }
        else if (c.equals("г")){
            color = Constants.ANSI_BLUE;
        }
        else if (c.equals("б")){
            color = Constants.ANSI_WHITE;
        }
        else{
            color = "";
        }
        if (!(color.equals(""))) {
            System.out.println(color + s + Constants.ANSI_RESET);
        }
        else{
            System.out.println(s);
        }
    }
    public static void printEquation(BigDecimal[] l){
        String temp = "";
        for (int i1 = 0; i1 < l.length; i1++){
            temp = temp + l[i1].toString().replace("E", " * 10^") + " ";
        }
        print(temp, "б");
    }

    public static void printArray(BigDecimal[] l, String c){
        StringBuilder answer = new StringBuilder();
        for (BigDecimal e: l) {
            answer.append(e.stripTrailingZeros().toString().replace("E", "*10^"));
            answer.append(' ');
        }
        print(answer.substring(0, answer.length()), c);

    }
}