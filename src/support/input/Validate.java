package support.input;

public class Validate {
    public boolean validateBorders(String s){
        String[] temp = s.strip().split(" ");
        if (temp.length != 3){
            return false;
        }
        for (int i = 0; i < temp.length; i++){
            temp[i] = temp[i].replace(",", ".");
        }
        System.out.println(temp[0]);
        System.out.println(temp[1]);
        System.out.println(temp[2]);
        if (!((temp[2]).matches("^[+]?[0-9]*[.,]?[0-9]+$"))){
            return false;
        }
        else{
            double accuracy = Double.parseDouble(temp[2]);
            if ((accuracy < 0.000001) || (accuracy > 1.0)){
                return false;
            }
        }
        return (((temp[0]).matches("^[+-]?[0-9]*[.,]?[0-9]+$")) && ((temp[1]).matches("^[+-]?[0-9]*[.,]?[0-9]+$")));
    }

}
