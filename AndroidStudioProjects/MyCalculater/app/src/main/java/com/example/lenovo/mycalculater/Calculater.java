package com.example.lenovo.mycalculater;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Calculater extends Activity implements View.OnClickListener {
    private enum OPERATOR {
        PLUS, SUBTRACT, MULTIPLY, DIVIDE, EQUAL
    }

    TextView txtCalculations;
    TextView txtResult;

    //Instance Variables

    private String currentNumber;
    private String stringNumberAtLeft;
    private String stringNumberAtRight;
    private OPERATOR currentoperator;
    private int calcutatiosResult;
    private String calculationsString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculater);

        currentNumber = "";
        calcutatiosResult = 0;
        txtCalculations = findViewById(R.id.txtCalculations);
        txtResult = findViewById(R.id.txtResult);

        findViewById(R.id.btnEqual).setOnClickListener(Calculater.this);
        findViewById(R.id.btn9).setOnClickListener(Calculater.this);
        findViewById(R.id.btn7).setOnClickListener(Calculater.this);
        findViewById(R.id.btn8).setOnClickListener(Calculater.this);
        findViewById(R.id.btnPlus).setOnClickListener(Calculater.this);
        findViewById(R.id.btn4).setOnClickListener(Calculater.this);
        findViewById(R.id.btn5).setOnClickListener(Calculater.this);
        findViewById(R.id.btn6).setOnClickListener(Calculater.this);
        findViewById(R.id.btnStubtract).setOnClickListener(Calculater.this);
        findViewById(R.id.btn1).setOnClickListener(Calculater.this);
        findViewById(R.id.btn2).setOnClickListener(Calculater.this);
        findViewById(R.id.btn3).setOnClickListener(Calculater.this);
        findViewById(R.id.btnMultiply).setOnClickListener(Calculater.this);
        findViewById(R.id.btnClear).setOnClickListener(Calculater.this);
        findViewById(R.id.btn0).setOnClickListener(Calculater.this);
        findViewById(R.id.btnDivide).setOnClickListener(Calculater.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEqual:
                operatorIsTapped(OPERATOR.EQUAL);
                break;

            case R.id.btn9:
                numberIsTapped(9);
                break;
            case R.id.btn7:
                numberIsTapped(7);
                break;
            case R.id.btn8:
                numberIsTapped(8);
                break;
            case R.id.btnPlus:
                operatorIsTapped(OPERATOR.PLUS);
                calculationsString = calculationsString + "+";
                break;
            case R.id.btn4:
                numberIsTapped(4);
                break;
            case R.id.btn5:
                numberIsTapped(5);
                break;
            case R.id.btn6:
                numberIsTapped(6);
                break;
            case R.id.btnStubtract:
                operatorIsTapped(OPERATOR.SUBTRACT);
                calculationsString = calculationsString + "-";
                break;
            case R.id.btn1:
                numberIsTapped(1);
                break;
            case R.id.btn2:
                numberIsTapped(2);
                break;
            case R.id.btn3:
                numberIsTapped(3);
                break;
            case R.id.btnMultiply:
                operatorIsTapped(OPERATOR.MULTIPLY);
                calculationsString = calculationsString + "*";
                break;
            case R.id.btnClear:
                break;
            case R.id.btn0:
                numberIsTapped(0);
                break;
            case R.id.btnDivide:
                operatorIsTapped(OPERATOR.DIVIDE);
                calculationsString = calculationsString + "/";
                break;

        }
        txtCalculations.setText(calculationsString);
    }

    private void numberIsTapped(int tappedNumber) {

        currentNumber = currentNumber + String.valueOf(tappedNumber);
        txtResult.setText(currentNumber);
    }

    private void operatorIsTapped(OPERATOR tappedoperator) {
        if (currentoperator != null) {
            if (currentNumber != "") {
                stringNumberAtRight= currentNumber;
                currentNumber="";
            switch (currentoperator)
            {
                case PLUS:
                calcutatiosResult=Integer.parseInt(stringNumberAtLeft)+
                                         Integer.parseInt(stringNumberAtRight);
                    break;
                case SUBTRACT:
                    calcutatiosResult=Integer.parseInt(stringNumberAtLeft)-
                            Integer.parseInt(stringNumberAtRight);
                    break;
                case MULTIPLY:
                    calcutatiosResult=Integer.parseInt(stringNumberAtLeft)*
                            Integer.parseInt(stringNumberAtRight);
                    break;
                case DIVIDE:
                    calcutatiosResult=Integer.parseInt(stringNumberAtLeft)/
                            Integer.parseInt(stringNumberAtRight);
                    break;
            }
            stringNumberAtLeft= String.valueOf(calcutatiosResult);
            txtResult.setText(stringNumberAtLeft);
                calculationsString =stringNumberAtLeft;
            }
        }
        else {
            stringNumberAtLeft= currentNumber;
            currentNumber="";
        }
        currentoperator=tappedoperator;
              }
    }
