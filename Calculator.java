import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

    private JTextField textField;
    private double num1 = 0, num2 = 0, result = 0;
    private String operator = "";
    private boolean isOperatorClicked = false;

    public Calculator() {
        setTitle("Calculator");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        textField = new JTextField();
        textField.setBounds(30, 40, 330, 40);
        textField.setEditable(false);
        textField.setFont(new Font("Arial", Font.BOLD, 18));
        add(textField);

        String[] buttonLabels = {
            "7", "8", "9", "/", 
            "4", "5", "6", "*", 
            "1", "2", "3", "-", 
            "0", ".", "=", "+"
        };

        JButton[] buttons = new JButton[16];
        int x = 30, y = 100;
        for (int i = 0; i < 16; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setBounds(x, y, 70, 40);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 16));
            buttons[i].addActionListener(this);
            add(buttons[i]);
            x += 80;
            if ((i + 1) % 4 == 0) {
                x = 30;
                y += 60;
            }
        }

        JButton clearButton = new JButton("C");
        clearButton.setBounds(30, y, 330, 40);
        clearButton.setFont(new Font("Arial", Font.BOLD, 16));
        clearButton.addActionListener(e -> {
            textField.setText("");
            num1 = num2 = result = 0;
            operator = "";
        });
        add(clearButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
            if (isOperatorClicked) {
                textField.setText("");
                isOperatorClicked = false;
            }
            textField.setText(textField.getText() + command);
        } else if (command.equals("=")) {
            try {
                num2 = Double.parseDouble(textField.getText());
                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/":
                        if (num2 == 0) {
                            textField.setText("Cannot divide by zero");
                            return;
                        } else {
                            result = num1 / num2;
                        }
                        break;
                    default: return;
                }
                textField.setText(String.valueOf(result));
                operator = "";
            } catch (Exception ex) {
                textField.setText("Error");
            }
        } else {
            try {
                num1 = Double.parseDouble(textField.getText());
                operator = command;
                isOperatorClicked = true;
            } catch (Exception ex) {
                textField.setText("Error");
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
