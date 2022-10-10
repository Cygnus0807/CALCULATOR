package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView Result;
    TextView Solution;
    MaterialButton buttonC,buttonOpenBracket,buttonCloseBracket;;
    MaterialButton buttonDivide,buttonMultiply,buttonSub,buttonAdd;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonDecimal,buttonAC,buttonEqual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Result=findViewById(R.id.result);
        Solution=findViewById(R.id.solution);

        assignId(buttonC,R.id.button_C);
        assignId(buttonOpenBracket,R.id.button_openBracket);
        assignId(buttonCloseBracket,R.id.button_closeBracket);
        assignId(buttonDivide,R.id.button_divide);
        assignId(buttonMultiply,R.id.button_multiply);
        assignId(buttonSub,R.id.button_sub);
        assignId(buttonAdd,R.id.button_add);
        assignId(button0,R.id.button_0);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(buttonDecimal,R.id.button_divide);
        assignId(buttonAC,R.id.button_AC);
        assignId(buttonEqual,R.id.button_equal);





    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        Solution.setText(buttonText);
        //data calculate krne ke concatenate krenge
        String dataToCal = Solution.getText().toString();

        if (buttonText.equals("AC")) {
            Solution.setText("");
            Result.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            Solution.setText(Result.getText());
            return;
        }
        if (buttonText.equals("C")) {
            dataToCal = dataToCal.substring(0, dataToCal.length() - 1);
        } else {
            dataToCal = dataToCal + buttonText;
        }

        Solution.setText(dataToCal);

        String finalResult = getResult(dataToCal);
        if (!finalResult.equals("Err")) {
            Result.setText(finalResult);
        }
    }
    String getResult(String data) {

        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();


            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e) {
            return "Err";
            //  return "Calculated";
        }

    }
}
