package com.example.androidprojectcollection;

import java.text.DecimalFormat;
import java.util.Stack;

public class Calculator {
    private MainActivity3Calc activity;

    public Calculator(MainActivity3Calc activity) {

        this.activity = activity;
    }

    public double evaluateExpression(String expression) {
        expression = expression.replaceAll("\\s+", "");
        return ActualResult(expression);
    }

    public String formatResult(double result) {
        // Check if the result has decimal places
        if (result == (long) result) {
            // If the result is an integer, remove the decimal part
            return String.format("%d", (long) result);
        } else {
            DecimalFormat decimalFormat = new DecimalFormat("#.##########");
            return decimalFormat.format(result);
        }
    }

    public void updateResult() {
        String expression = activity.getRecall().getText().toString();
        if (expression.contains("+") || expression.contains("-") || expression.contains("x") || expression.contains("/")) {
            try {
                double result = evaluateExpression(expression);
                String formattedResult = formatResult(result);
                activity.getResult().setText(formattedResult); // Updated to use the result TextView from CalculatorExercise
            } catch (Exception e) {
                activity.getResult().setText("");
            }
        } else {
            activity.getResult().setText("");
        }
    }

    private Double ActualResult(String expression) {
        expression = expression.replaceAll("\\s+", "");

        String[] tokens = expression.split("(?<=[-+x/()])|(?=[-+x/()])");

        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (String token : tokens) {
            if (token.matches("\\d+(\\.\\d+)?")) {
                numbers.push(Double.parseDouble(token));
            } else if (token.equals("(")) {
                operators.push(token.charAt(0));
            } else if (token.equals(")")) {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    evaluateOperator(numbers, operators);
                }
                operators.pop();
            } else {
                while (!operators.isEmpty() && Precedence(token.charAt(0), operators.peek())) {
                    evaluateOperator(numbers, operators);
                }
                operators.push(token.charAt(0));
            }
        }

        while (!operators.isEmpty()) {
            evaluateOperator(numbers, operators);
        }
        return numbers.pop();
    }

    private void evaluateOperator(Stack<Double> numbers, Stack<Character> operators) {
        char operator = operators.pop();
        double num2 = numbers.pop();
        double num1 = numbers.pop();
        double result;
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case 'x':
                result = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
        numbers.push(result);
    }

    private boolean Precedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        return (op1 != 'x' && op1 != '/') || (op2 != '+' && op2 != '-');
    }
}
