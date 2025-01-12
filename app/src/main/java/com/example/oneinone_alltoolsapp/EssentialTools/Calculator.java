package com.example.oneinone_alltoolsapp.EssentialTools;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.oneinone_alltoolsapp.R;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;

import java.util.ArrayList;

public class Calculator extends AppCompatActivity {

    private StringBuilder currentExpression = new StringBuilder();
    private boolean newCalculation = false;
    private static final int MAX_LENGTH = 50;
    private TextView screen;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // Initialize TextViews
        screen = findViewById(R.id.screen);
        result = findViewById(R.id.result);

        // Numeric Buttons
        Button num0 = findViewById(R.id.num0);
        Button num1 = findViewById(R.id.num1);
        Button num2 = findViewById(R.id.num2);
        Button num3 = findViewById(R.id.num3);
        Button num4 = findViewById(R.id.num4);
        Button num5 = findViewById(R.id.num5);
        Button num6 = findViewById(R.id.num6);
        Button num7 = findViewById(R.id.num7);
        Button num8 = findViewById(R.id.num8);
        Button num9 = findViewById(R.id.num9);

        // Operator Buttons
        Button dot = findViewById(R.id.num_dot);
        Button del = findViewById(R.id.del);
        Button div = findViewById(R.id.div);
        Button mul = findViewById(R.id.mul);
        Button sub = findViewById(R.id.sub);
        Button add = findViewById(R.id.add);
        Button equal = findViewById(R.id.equal);

        // Scientific Buttons
        Button sin = findViewById(R.id.sin);
        Button cos = findViewById(R.id.cos);
        Button tan = findViewById(R.id.tan);
        Button log = findViewById(R.id.log);
        Button ln = findViewById(R.id.ln);
        Button ac = findViewById(R.id.ac);
        Button sqrt = findViewById(R.id.sqrt);
        Button percent = findViewById(R.id.percentage);
        Button pow = findViewById(R.id.pow);
        Button rparen = findViewById(R.id.rparen);
        Button copy = findViewById(R.id.copy);

        copy.setOnClickListener(view -> {
            String resultText = result.getText().toString();

            if (!resultText.isEmpty()) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Copied Result", resultText);
                clipboard.setPrimaryClip(clip);

                showToast("Result copied to clipboard");
            } else {
                showToast("Nothing to copy");
            }
        });

        // Clear all inputs
        ac.setOnClickListener(view -> {
            currentExpression.setLength(0);
            screen.setText("0");
            result.setText("");
        });

        // Handle numeric button clicks
        ArrayList<Button> nums = new ArrayList<>();
        nums.add(num0);
        nums.add(num1);
        nums.add(num2);
        nums.add(num3);
        nums.add(num4);
        nums.add(num5);
        nums.add(num6);
        nums.add(num7);
        nums.add(num8);
        nums.add(num9);

        for (Button b : nums) {
            b.setOnClickListener(view -> {
                if (newCalculation) {
                    screen.setText("");
                    currentExpression.setLength(0);
                    newCalculation = false;
                }
                if (currentExpression.length() < MAX_LENGTH) {
                    if (!screen.getText().toString().equals("0")) {
                        currentExpression.append(b.getText().toString());
                    } else {
                        currentExpression.setLength(0); // Clear initial zero
                        currentExpression.append(b.getText().toString());
                    }
                    screen.setText(currentExpression.toString());
                } else {
                    showToast("Max length reached");
                }
            });
        }

        // Handle operator button clicks
        ArrayList<Button> ops = new ArrayList<>();
        ops.add(div);
        ops.add(mul);
        ops.add(sub);
        ops.add(add);

        for (Button b : ops) {
            b.setOnClickListener(view -> {
                if (currentExpression.length() == 0) {
                    showToast("Please enter a number");
                } else {
                    String currentOperator = b.getText().toString();
                    if (newCalculation) {
                        newCalculation = false;
                        currentExpression.setLength(0);
                        screen.setText("");
                    }
                    currentExpression.append(" ").append(currentOperator).append(" ");
                    screen.setText(currentExpression.toString());
                }
            });
        }

        // Handle scientific functions
        sin.setOnClickListener(view -> addFunctionToExpression("sin"));
        cos.setOnClickListener(view -> addFunctionToExpression("cos"));
        tan.setOnClickListener(view -> addFunctionToExpression("tan"));
        ln.setOnClickListener(view -> addFunctionToExpression("ln"));
        log.setOnClickListener(view -> addFunctionToExpression("log"));
        sqrt.setOnClickListener(view -> addFunctionToExpression("sqrt"));
        pow.setOnClickListener(view -> addToExpression("^"));

        rparen.setOnClickListener(view -> {
            String currentText = currentExpression.toString();

            // Count the number of opening and closing brackets
            int openCount = currentText.length() - currentText.replace("(", "").length();
            int closeCount = currentText.length() - currentText.replace(")", "").length();

            // If there is an unmatched opening bracket, add a closing bracket
            if (openCount > closeCount && !currentText.endsWith(" ")) {
                currentExpression.append(")");
            } else {
                // If the last character is an operator or no unmatched opening bracket, add an opening bracket
                if (currentText.isEmpty() || currentText.endsWith(" ") || currentText.endsWith("(")) {
                    currentExpression.append("(");
                } else {
                    currentExpression.append(" ");
                    currentExpression.append("(");
                }
            }

            screen.setText(currentExpression.toString());
        });

        // Handle percentage button click
        percent.setOnClickListener(view -> {
            if (currentExpression.length() > 0 && !currentExpression.toString().endsWith(" ")) {
                try {
                    // Evaluate the current expression
                    Expression expression = new ExpressionBuilder(currentExpression.toString()).build();
                    double value = expression.evaluate();

                    // Calculate percentage
                    double percentageValue = value / 100;

                    // Update the current expression
                    currentExpression.setLength(0);
                    currentExpression.append(percentageValue);

                    // Display the result
                    screen.setText(currentExpression.toString());
                    result.setText(" " + percentageValue);
                } catch (Exception e) {
                    showToast("Invalid expression");
                }
            } else {
                showToast("Please enter a valid number before %");
            }
        });

        // Handle delete button click
        del.setOnClickListener(view -> {
            String currentText = currentExpression.toString();
            if (currentText.length() > 1) {
                currentExpression.setLength(currentExpression.length() - 1);
                screen.setText(currentExpression.toString());
            } else {
                currentExpression.setLength(0);
                screen.setText("0");
            }
        });

        // Handle decimal point button click
        dot.setOnClickListener(view -> {
            if (!currentExpression.toString().contains(".")) {
                currentExpression.append(".");
                screen.setText(currentExpression.toString());
            }
        });

        // Handle equals button click
        equal.setOnClickListener(view -> {
            try {
                // Replace "X" with "*" for multiplication
                String expression = currentExpression.toString().replace("X", "*");

                // Automatically add closing parenthesis if needed
                if (expression.contains("(") && !expression.contains(")")) {
                    expression += ")";
                }

                // Create and evaluate the expression
                Expression exp = new ExpressionBuilder(expression)
                        .functions(new Function("ln", 1) {
                            @Override
                            public double apply(double... args) {
                                return Math.log(args[0]);
                            }
                        })
                        .functions(new Function("log", 1) {
                            @Override
                            public double apply(double... args) {
                                return Math.log10(args[0]);
                            }
                        })
                        .build();

                double resultValue = exp.evaluate();

                // Display the result
                result.setText(" " + resultValue);

                // Prepare for a new calculation
                currentExpression.setLength(0);
                currentExpression.append(resultValue);
                newCalculation = true;
            } catch (Exception e) {
                // Handle invalid expression
                showToast("Invalid expression");
            }
        });
    }

    private void addFunctionToExpression(String function) {
        if (currentExpression.length() > 0 && !currentExpression.toString().endsWith(" ")) {
            currentExpression.append(" ");
        }
        currentExpression.append(function).append("(");
        screen.setText(currentExpression.toString());
    }

    private void addToExpression(String text) {
        if (newCalculation) {
            screen.setText("");
            currentExpression.setLength(0);
            newCalculation = false;
        }
        currentExpression.append(text);
        screen.setText(currentExpression.toString());
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
