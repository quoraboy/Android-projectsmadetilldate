package com.example.lenovo.smartcalculater;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtEnterPercent;
    private EditText edtEnterNumber;
    private Button btnCalculate;
    private TextView textResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edtEnterPercent =(EditText) findViewById(R.id.edtEnterPercent);
        edtEnterNumber = (EditText) findViewById(R.id.edtEnterNumber);
        Button btnCalculate = (Button) findViewById(R.id.btnCalculate);
        textResult = (TextView) findViewById(R.id.textResult);


        btnCalculate.setOnClickListener(MainActivity.this);
        }
            @Override
            public void onClick(View v) {
float percentNumericValue = Float.parseFloat(edtEnterPercent.getText().toString());
float decimalvalue = percentNumericValue/100;
float result = decimalvalue*Float.parseFloat(edtEnterNumber.getText().toString());
        textResult.setText(Float.toString(result).substring(0,4));
            }
        }



