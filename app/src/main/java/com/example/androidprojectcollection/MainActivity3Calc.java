package com.example.androidprojectcollection;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3Calc extends AppCompatActivity {
    private TextView recall;
    private TextView result;
    private boolean decimalAdded = false; // Flag to track if decimal has been added

    private Calculator calculatorExerciseObject;
    private String checkLast; // Declare checkLast variable
    private boolean checkDec; // Declare checkDec variable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        recall = findViewById(R.id.Rev);
        result = findViewById(R.id.Result);

        // Instantiate the CalculatorExerciseObject
        calculatorExerciseObject = new Calculator(this);

        // Add button click listeners
        Button btn0 = findViewById(R.id.btnnum0);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recall.append("0");
                calculatorExerciseObject.updateResult();
            }
        });

        Button btn1 = findViewById(R.id.btnnum1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recall.append("1");
                calculatorExerciseObject.updateResult();
            }
        });

        Button btn2 = findViewById(R.id.btnnum2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recall.append("2");
                calculatorExerciseObject.updateResult();
            }
        });

        Button btn3 = findViewById(R.id.btnnum3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recall.append("3");
                calculatorExerciseObject.updateResult();
            }
        });

        Button btn4 = findViewById(R.id.btnnum4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recall.append("4");
                calculatorExerciseObject.updateResult();
            }
        });

        Button btn5 = findViewById(R.id.btnnum5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recall.append("5");
                calculatorExerciseObject.updateResult();
            }
        });

        Button btn6 = findViewById(R.id.btnnum6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recall.append("6");
                calculatorExerciseObject.updateResult();
            }
        });

        Button btn7 = findViewById(R.id.btnnum7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recall.append("7");
                calculatorExerciseObject.updateResult();
            }
        });

        Button btn8 = findViewById(R.id.btnnum8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recall.append("8");
                calculatorExerciseObject.updateResult();
            }
        });

        Button btn9 = findViewById(R.id.btnnum9);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recall.append("9");
                calculatorExerciseObject.updateResult();
            }
        });

        Button btnPlus = findViewById(R.id.btnAdd);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recall.append("+");
                calculatorExerciseObject.updateResult();
            }
        });

        Button btnMinus = findViewById(R.id.btnSub);
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recall.append("-");
                calculatorExerciseObject.updateResult();
            }
        });

        Button btnMult = findViewById(R.id.btnMult);
        btnMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recall.append("x");
                calculatorExerciseObject.updateResult();
            }
        });

        Button btnDiv = findViewById(R.id.btnDiv);
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recall.append("/");
                calculatorExerciseObject.updateResult();
            }
        });

        Button btnDec = findViewById(R.id.btnDec);
        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!decimalAdded) {
                    recall.append(".");
                    decimalAdded = true;
                } else {
                    String currentExpression = recall.getText().toString();
                    if (currentExpression.endsWith(".")) {
                        // Remove the last character (the decimal point)
                        recall.setText(currentExpression.substring(0, currentExpression.length() - 1));
                        decimalAdded = false;
                    }
                }
                calculatorExerciseObject.updateResult();
            }
        });




        Button btnEq = findViewById(R.id.btnEquals);
        btnEq.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button) view;
            String buttonText = button.getText().toString();
            if (buttonText.equals("=")) {
                String expression = recall.getText().toString();
                try {
                    double res = calculatorExerciseObject.evaluateExpression(expression);
                    String formattedResult = calculatorExerciseObject.formatResult(res);
                    recall.setText("");
                    result.setText(formattedResult);
                    decimalAdded = false; // Reset flag after result
                } catch (Exception e) {
                    result.setText("Invalid");
                    decimalAdded = false; // Reset flag if expression is invalid
                }
            } else {
                recall.append(buttonText);
                calculatorExerciseObject.updateResult();
            }
        }
    };

    // Add getter methods for recall and result TextViews
    public TextView getRecall() {
        return recall;
    }

    public TextView getResult() {
        return result;
    }
}
