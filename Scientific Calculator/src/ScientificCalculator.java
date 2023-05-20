import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScientificCalculator extends JFrame {
    private JTextField displayField;
    private double num1, num2, result;
    private String operator;

    public ScientificCalculator() {
        setTitle("Scientific Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        displayField = new JTextField();
        displayField.setPreferredSize(new Dimension(200, 30));
        displayField.setEditable(false);
        panel.add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "sqrt", "sin", "cos", "tan",
                "←" // Backspace button
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        panel.add(buttonPanel, BorderLayout.CENTER);
        add(panel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch (command) {
                case "0": case "1": case "2": case "3":
                case "4": case "5": case "6": case "7":
                case "8": case "9":
                    displayField.setText(displayField.getText() + command);
                    break;
                case ".":
                    if (!displayField.getText().contains(".")) {
                        displayField.setText(displayField.getText() + ".");
                    }
                    break;
                case "+": case "-": case "*": case "/":
                    num1 = Double.parseDouble(displayField.getText());
                    operator = command;
                    displayField.setText("");
                    break;
                case "=":
                    num2 = Double.parseDouble(displayField.getText());
                    result = calculate();
                    displayField.setText(String.valueOf(result));
                    break;
                case "sqrt":
                    num1 = Double.parseDouble(displayField.getText());
                    result = Math.sqrt(num1);
                    displayField.setText(String.valueOf(result));
                    break;
                case "sin":
                    num1 = Double.parseDouble(displayField.getText());
                    result = Math.sin(Math.toRadians(num1));
                    displayField.setText(String.valueOf(result));
                    break;
                case "cos":
                    num1 = Double.parseDouble(displayField.getText());
                    result = Math.cos(Math.toRadians(num1));
                    displayField.setText(String.valueOf(result));
                    break;
                case "tan":
                    num1 = Double.parseDouble(displayField.getText());
                    result = Math.tan(Math.toRadians(num1));
                    displayField.setText(String.valueOf(result));
                    break;
                case "←": // Backspace button
                    String currentText = displayField.getText();
                    if (!currentText.isEmpty()) {
                        displayField.setText(currentText.substring(0, currentText.length() - 1));
                    }
                    break;
            }
        }

        private double calculate() {
            switch (operator) {
                case "+":
                    return num1 + num2;
                case "-":
                    return num1 - num2;
                case "*":
                    return num1 * num2;
                case "/":
                    if (num2 != 0) {
                        return num1 / num2;
                    } else {
                        throw new ArithmeticException("Cannot divide by zero!");
                    }
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ScientificCalculator();
            }
        });
    }
}
