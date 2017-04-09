package com.example.czarnaotcha.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;

import java.lang.reflect.Array;


public class MainActivity extends AppCompatActivity {
    private TextView textViewHello;
    private TextView textViewQuestion;
    private  EditText name;
    private String nameString;
    private int i=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.textViewQuestion = (TextView) findViewById(R.id.textView3);
        textViewQuestion.setText(getString(R.string.question));
        this.textViewHello = (TextView) findViewById(R.id.textView4);
        textViewHello.setText(getString(R.string.hi));
    }

    public void clickButton(View v) {
        this.name = ((EditText) findViewById(R.id.editText2));
        this.name.setEnabled(false);
       nameString = name.getText().toString();
        if (nameString.isEmpty()){
            textViewHello.setText(getString(R.string.hi)+ " " + getString(R.string.st));
        }
        else {
            textViewHello.setText(getString(R.string.hi) + " " + name.getText().toString());
        }
        i+=1;

        if(i == 1){
            textViewHello.setTextColor(getResources().getColor(R.color.red));
        }
        else if(i==2){
            textViewHello.setTextColor(getResources().getColor(R.color.green));

        }
        else if(i==3){
            textViewHello.setTextColor(getResources().getColor(R.color.blue));
            i=0;
        }
    }

}