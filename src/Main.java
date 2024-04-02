import algorithm.AlgorithmManager;
import algorithm.Graphics;
import support.input.Validate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {
    private static AlgorithmManager solver = new AlgorithmManager();
    private static Validate validator = new Validate();
    private static Graphics g = new Graphics();
    public static JFrame getWindow() {
        JFrame window = new JFrame();
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        window.setBounds(0, 0, dimension.width, dimension.height);
        window.setTitle("mathematics_lab2_335099");
        return window;

    }

    public static void main(String[] args) {
        int functionNumber = 1;
        int methodNumber = 1;
        JFrame window = getWindow();
        JPanel panel = new JPanel();
        Label label = new Label();
        GridLayout grid = new GridLayout(10, 6);
        panel.setLayout(grid);
        grid.setHgap(10);
        ArrayList<JButton> startMethod = new ArrayList<JButton>();
        CheckboxGroup oneFunction = new CheckboxGroup();
        Checkbox firstFunction = new Checkbox("3x^3 + 2x^2 + x - 3", oneFunction, false);
        Checkbox secondFunction = new Checkbox("0.5x^2 - x", oneFunction, false);
        Checkbox thirdFunction = new Checkbox("sin(x) + 0.5x^2 - 1", oneFunction, false);
        Checkbox fourthFunction = new Checkbox("cos(x) + sin(x)", oneFunction, false);
        Checkbox fifthFunction = new Checkbox("x^3 - x^2 - 2x", oneFunction, false);
        panel.add(firstFunction);
        panel.add(secondFunction);
        panel.add(thirdFunction);
        panel.add(fourthFunction);
        panel.add(fifthFunction);
        CheckboxGroup oneMethod = new CheckboxGroup();
        JTextField binarySearchInput = new JTextField();
        JButton binarySearchButton = new JButton("Посчитать методом половинного деления!");
        Label binarySearchLabel = new Label();
        Checkbox firstMethod = new Checkbox("Метод половинного деления", oneMethod, true);
        firstMethod.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                binarySearchButton.setText("Посчитать методом половинного деления!");
            }
        });
        Checkbox secondMethod = new Checkbox("Метод секущих", oneMethod, false);
        secondMethod.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                binarySearchButton.setText("Посчитать методом секущих!");
            }
        });
        Checkbox thirdMethod = new Checkbox("Метод простой итерации", oneMethod, false);
        thirdMethod.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                binarySearchButton.setText("Посчитать методом простой итерации!");
            }
        });
        panel.add(firstMethod);
        panel.add(secondMethod);
        panel.add(thirdMethod);
        /*JTextField secantsInput = new JTextField();
        JButton secantsButton = new JButton("Посчитать методом секущих!");
        Label secantsLabel = new Label();
        JTextField iterationInput = new JTextField();
        JButton iterationButton = new JButton("Посчитать методом простой итерации!");
        Label iterationLabel = new Label();*/
        panel.add(binarySearchInput);
        panel.add(binarySearchButton);
        panel.add(binarySearchLabel);
        /*panel.add(secantsInput);
        panel.add(secantsButton);
        panel.add(secantsLabel);
        panel.add(iterationInput);
        panel.add(iterationButton);
        panel.add(iterationLabel);*/

        binarySearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (oneFunction.getSelectedCheckbox() == null){
                    binarySearchLabel.setText("Выберите, пожалуйста, одну функцию из списка");
                }
                else if (validator.validateBorders(binarySearchInput.getText())){
                    binarySearchLabel.setText("");
                    BigDecimal first, second, accuracy;
                    String[] temp = binarySearchInput.getText().strip().split(" ");
                    for (int i = 0; i < temp.length; i++){
                        temp[i] = temp[i].replace(",", ".");
                    }
                    first = new BigDecimal(temp[0]);
                    second = new BigDecimal(temp[1]);
                    accuracy = new BigDecimal(temp[2]);
                    String function = oneFunction.getSelectedCheckbox().getLabel();
                    String method = oneMethod.getSelectedCheckbox().getLabel();
                    int functionNumber, methodNumber;
                    functionNumber = -1;
                    methodNumber = -1;
                    switch (function){
                        case "3x^3 + 2x^2 + x - 3":
                            functionNumber = 1;
                            break;
                        case "0.5x^2 - x":
                            functionNumber = 2;
                            break;
                        case "sin(x) + 0.5x^2 - 1":
                            functionNumber = 3;
                            break;
                        case "cos(x) + sin(x)":
                            functionNumber = 4;
                            break;
                        case "x^3 - x^2 - 2x":
                            functionNumber = 5;
                            break;
                    }
                    switch (method){
                        case "Метод половинного деления":
                            methodNumber = 1;
                            break;
                        case "Метод секущих":
                            methodNumber = 2;
                            break;
                        case "Метод простой итерации":
                            methodNumber = 3;
                            break;
                    }
                    /*System.out.println(functionNumber);
                    System.out.println(methodNumber);
                    System.out.println(first);
                    System.out.println(second);
                    System.out.println(accuracy);*/
                    ArrayList<ArrayList<BigDecimal>> answer = solver.solve(methodNumber, functionNumber, first, second, accuracy);
                    DefaultTableModel model = new DefaultTableModel();
                    JTable table = new JTable(model);
                    ArrayList<BigDecimal> temp1 = answer.get(0);
                    String[] temp2 = new String[temp1.size()];
                    if (methodNumber == 1){
                        temp2 = new String[]{"№ итерации", "a", "b", "x", "f(a)", "f(b)", "f(x)", "|a - b|"};
                    }
                    else if (methodNumber == 2){
                        temp2 = new String[]{"№ итерации", "x(k - 1)", "x(k)", "x(k + 1)", "f(x(k + 1))", "|x(k + 1) - x(k)|"};
                    }
                    else if (methodNumber == 3){
                        temp2 = new String[]{"№ итерации", "x(k)", "x(k + 1)", "p(x(k + 1))", "f(x(k + 1))", "|x(k + 1) - x(k)|"};
                    }
                    for (int i = 0; i < temp1.size(); i++){
                        model.addColumn(i);
                    }
                    model.addRow(temp2);
                    for (ArrayList<BigDecimal> l: answer){
                        //System.out.println(l.get(2));
                        model.addRow(l.toArray());
                    }
                    JFrame tableWindow = getWindow();
                    JPanel tablePanel = new JPanel();
                    tablePanel.add(table);
                    tableWindow.add(tablePanel);
                    tableWindow.setVisible(true);
                    g.chart(first, second, functionNumber);
                }
                else{
                    //solver.solve(1, oneFunction.getSelectedCheckbox());
                    binarySearchLabel.setText("Введите числа в так: число(слева) пробел число(справа) пробел число(0.000001 <= точность <= 1.0)");
                }
            }
        });
        CheckboxGroup oneEquations = new CheckboxGroup();
        Checkbox firstEquations = new Checkbox("sin(x - 1) + y = 1.5, x - sin(y + 1) = 1", oneEquations, true);
        Checkbox secondEquations = new Checkbox("cos(x) = x^2 + y, 0.5x^2 + 2y^2 = 1", oneEquations, false);
        JTextField equationsInput = new JTextField();
        JButton equationsButton = new JButton("Посчитать методом Ньютона!");
        Label equationsLabel = new Label();
        equationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (oneEquations.getSelectedCheckbox() == null){
                    equationsLabel.setText("Выберите, пожалуйста, один вариант из двух");
                }
                else if (validator.validateBorders(equationsInput.getText())){
                    equationsLabel.setText("");
                    BigDecimal first, second, accuracy;
                    String[] temp = equationsInput.getText().strip().split(" ");
                    for (int i = 0; i < temp.length; i++){
                        temp[i] = temp[i].replace(",", ".");
                    }
                    first = new BigDecimal(temp[0]);
                    second = new BigDecimal(temp[1]);
                    accuracy = new BigDecimal(temp[2]);
                    String function = oneEquations.getSelectedCheckbox().getLabel();
                    int functionNumber, methodNumber;
                    functionNumber = -1;
                    methodNumber = 1;
                    switch (function){
                        case "sin(x - 1) + y = 1.5, x - sin(y + 1) = 1":
                            functionNumber = 1;
                            break;
                        case "cos(x) = x^2 + y, 0.5x^2 + 2y^2 = 1":
                            functionNumber = 3;
                            break;
                    }
                    /*System.out.println(functionNumber);
                    System.out.println(methodNumber);
                    System.out.println(first);
                    System.out.println(second);
                    System.out.println(accuracy);*/
                    ArrayList<ArrayList<BigDecimal>> answer = solver.solveEquations(methodNumber, functionNumber, first, second, accuracy);
                    DefaultTableModel model = new DefaultTableModel();
                    JTable table = new JTable(model);
                    ArrayList<BigDecimal> temp1 = answer.get(0);
                    String[] temp2 = new String[temp1.size()];
                    if (methodNumber == 1){
                        temp2 = new String[]{"№ итерации", "x", "y"};
                    }
                    for (int i = 0; i < temp1.size(); i++){
                        model.addColumn(i);
                    }
                    model.addRow(temp2);
                    for (ArrayList<BigDecimal> l: answer){
                        //System.out.println(l.get(2));
                        model.addRow(l.toArray());
                    }
                    JFrame tableWindow = getWindow();
                    JPanel tablePanel = new JPanel();
                    tablePanel.add(table);
                    tableWindow.add(tablePanel);
                    tableWindow.setVisible(true);
                    g.chartEquations(first, second, functionNumber);

                }
                else{
                    //solver.solve(1, oneFunction.getSelectedCheckbox());
                    equationsLabel.setText("Введите числа в так: число(x0) пробел число(y0) пробел число(0.000001 <= точность <= 1.0)");
                }
            }
        });
        panel.add(label);
        panel.add(firstEquations);
        panel.add(secondEquations);
        panel.add(equationsInput);
        panel.add(equationsButton);
        panel.add(equationsLabel);
        window.add(panel);
        window.setVisible(true);
        /*JTable table = new JTable(100, 10);
        JFrame tableWindow = getWindow();
        JPanel tablePanel = new JPanel();
        tablePanel.add(table);
        tableWindow.add(tablePanel);
        tableWindow.setVisible(true);*/

        /*secantsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //solver.solve();
            }
        });

        iterationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //solver.solve();
            }
        });*/


    }

}