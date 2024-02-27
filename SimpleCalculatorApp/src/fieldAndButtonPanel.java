import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class fieldAndButtonPanel extends JPanel {
    private JTextField field;
    public fieldAndButtonPanel() {
        content();
    }
    public void content() {
        this.setLayout(new BorderLayout());
        field = new JTextField();
        field.setText("");
        field.setPreferredSize(new Dimension(100, 50));
        field.setFont(new Font("Times New Roman", Font.BOLD, 30));
        this.add(field, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BorderLayout());

        JPanel numButtonsPanel =  new JPanel();
        numButtonsPanel.setLayout(new GridLayout(4,3));

        JButton[] numButtons = new JButton[12];
        for(int i = 0; i < numButtons.length; i++) {
            numButtons[i] = new JButton();
            numButtons[i].setPreferredSize(new Dimension(100, 100));
            numButtons[i].setFont(new Font("Times New Roman", Font.BOLD, 30));


            if(i <= 8) {
                numButtons[i].setText(Integer.toString(i+1));
                numButtonsPanel.add(numButtons[i]);
            }
            else if(i == 9) {
                numButtons[i].setText("0");
                numButtonsPanel.add(numButtons[i]);
            }
            else if(i == 10) {
                numButtons[i].setText(".");
                numButtonsPanel.add(numButtons[i]);
            }
            else {
                numButtons[i].setText("=");
                numButtonsPanel.add(numButtons[i]);
                numButtons[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String total = field.getText();

                        try {
                            double result = evaluateExpression(total);
                            field.setText(Double.toString(result));
                        }
                        catch(ArithmeticException exception) {
                            field.setText(exception.getMessage());
                            field.setEditable(false);
                            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
                            field.setText("");
                            field.setEditable(true);
                        }



                    }
                });
            }
            if(i != 11) {
                int finalI = i;
                numButtons[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        field.setText(field.getText().concat(numButtons[finalI].getText()));
                    }
                });
            }

        }
        buttonsPanel.add(numButtonsPanel, BorderLayout.CENTER);

        JPanel operationsPanel = new JPanel();
        operationsPanel.setLayout(new GridLayout(4 ,1));

        JButton addBtn, subBtn, mulBtn, divBtn;
        addBtn = new JButton("+");
        addBtn.setFont(new Font("Times New Roman", Font.BOLD, 30));
        addBtn.setPreferredSize(new Dimension(100, 100));
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                field.setText(field.getText().concat(addBtn.getText()));
            }
        });



        subBtn = new JButton("-");
        subBtn.setFont(new Font("Times New Roman", Font.BOLD, 30));
        subBtn.setPreferredSize(new Dimension(100, 100));
        subBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                field.setText(field.getText().concat(subBtn.getText()));
            }
        });


        mulBtn = new JButton("*");
        mulBtn.setFont(new Font("Times New Roman", Font.BOLD, 30));
        mulBtn.setPreferredSize(new Dimension(100, 100));
        mulBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                field.setText(field.getText().concat(mulBtn.getText()));
            }
        });


        divBtn = new JButton("/");
        divBtn.setFont(new Font("Times New Roman", Font.BOLD, 30));
        divBtn.setPreferredSize(new Dimension(100, 100));
        divBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                field.setText(field.getText().concat(divBtn.getText()));
            }
        });

        operationsPanel.add(addBtn);
        operationsPanel.add(subBtn);
        operationsPanel.add(mulBtn);
        operationsPanel.add(divBtn);

        buttonsPanel.add(operationsPanel, BorderLayout.EAST);
        this.add(buttonsPanel, BorderLayout.CENTER);

        JPanel bottomBtnPanel = new JPanel();
        bottomBtnPanel.setLayout(new FlowLayout());

        JButton delBtn = new JButton("Del");
        delBtn.setFont(new Font("Times New Roman", Font.BOLD, 30));
        delBtn.setPreferredSize(new Dimension(120, 40));

        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fieldText = field.getText();
                int length = fieldText.length();

            }
        });

        JButton clrBtn = new JButton("Clr");
        clrBtn.setFont(new Font("Times New Roman", Font.BOLD, 30));
        clrBtn.setPreferredSize(new Dimension(120, 40));

        clrBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fieldText = field.getText();
                int length = fieldText.length();
                if (length > 0) {
                    field.setText(fieldText.substring(0, length - 1));
                }
            }
        });

        bottomBtnPanel.add(delBtn);
        bottomBtnPanel.add(clrBtn);

        this.add(bottomBtnPanel, BorderLayout.SOUTH);
    }
    public double evaluateExpression(String expressions) {
        String[] tokens = expressions.split("(?<=[-+*/])|(?=[-+*/])");
        double result = Double.parseDouble(tokens[0]);
        for (int i = 1; i < tokens.length; i+=2) {
            String operator = tokens[i];
            double operand = Double.parseDouble(tokens[i+1]);
            if(operator.equals("+")) {
                result = result + operand;
            }
            else if(operator.equals("-")) {
                result = result - operand;
            }
            else if(operator.equals("*")) {
                result = result * operand;
            }
            else if(operator.equals("/")) {
                if (operand == 0) {
                    throw new ArithmeticException("Math Error");
                } else {
                    result = result / operand;
                }
            }
        }
        return result;
    }
}
