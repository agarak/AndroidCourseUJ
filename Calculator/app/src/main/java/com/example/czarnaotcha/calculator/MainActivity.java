package com.example.czarnaotcha.calculator;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private TextView firstValue;
    private TextView secondValue;
    private TextView sign;
    private TextView result;
    private Button comma;
    private Button value;
    private double score;
    private double value1;
    private double value2;
    private String oldValue;
    private String newValue;
    private int flag=0;
    private DecimalFormat format = new DecimalFormat("0.###");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.firstValue = (TextView) findViewById(R.id.textView1);
        this.secondValue = (TextView) findViewById(R.id.textView3);
        this.sign = (TextView) findViewById(R.id.textView2);
        this.result = (TextView) findViewById(R.id.textView4);
        this.comma = (Button) findViewById(R.id.button18);

    }

    public int checkPlaceForValue(View v) {
        if(!(isEmpty(result))){
            return 0;
        }
        value = (Button) findViewById(v.getId());
        if (isEmpty(sign)) {
            setValue(firstValue, value.getText().toString());
        } else {
            setValue(secondValue, value.getText().toString());
        }
        return 0;
    }

    public void setValue(TextView place, String newValue) {
        if (isEmpty(place)){
            place.setText(newValue);
        }
        else if( parseToDouble(place) < 99999999999.99) {
            oldValue = place.getText().toString();
            place.setText(oldValue + newValue);
        }
    }

    public int setSign(View v) {
        if(!(isEmpty(result))) {
            return 0;
        }
        value = (Button) findViewById(v.getId());
        oldValue = sign.getText().toString();
        newValue = value.getText().toString();
        if(oldValue.equals(newValue)){
            vibration();
            showToast(getResources().getString(R.string.wrongSign));
        }
        sign.setText(newValue);
        comma.setEnabled(true);
        return 0;
    }

    public void setComma(View v){
        if(isEmpty(sign)) {
            setValue(firstValue, ".");
            comma.setEnabled(false);
        }
        else {
            setValue(secondValue, ".");
            comma.setEnabled(false);
        }
    }

    public int setResult(View v) {
        value = (Button) findViewById(v.getId());

        if(isEmpty(firstValue) || isEmpty(secondValue)){
            vibration();
            showToast(getResources().getString(R.string.wrongInput));
            return 0;
        }

        value1 = parseToDouble(firstValue);
        value2 = parseToDouble(secondValue);

        switch (sign.getText().toString()) {
            case "+":
                score = value1 + value2;
                break;
            case "-":
                score = value1 - value2;
                break;
            case "*":
                score = value1 * value2;
                break;
            case "/":
                if(value2 == 0){
                    flag=1;
                }
                else {
                    score = value1 / value2;
                }
                break;
            default:
                System.out.println("Wrong sign");
                return 0;
        }
        if (flag == 1){
            result.setText(R.string.wrongValue);
        }
        else {
            result.setText(String.valueOf(format.format(score)));
        }
        flag = 0;
        return 0;
    }

    public void clearAll(View v) {
        firstValue.setText("");
        secondValue.setText("");
        sign.setText("");
        result.setText("");
        comma.setEnabled(true);
    }

    public void clearLastValue(View v){
        if(isEmpty(result)) {
            if (isEmpty(sign) && !(isEmpty(firstValue))){
                firstValue.setText(deleteLastSign(firstValue));

            } else if (!(isEmpty(result) && isEmpty(secondValue))) {
                secondValue.setText(deleteLastSign(secondValue));
            }
            else{
                vibration();
                showToast(getResources().getString(R.string.cannotDelete));
            }
        }
    }

    public void changeSign(View v) {
        if(isEmpty(result)) {
            if (isEmpty(sign)) {
                value1 = parseToDouble(firstValue);
                value1 = -value1;
                firstValue.setText(String.valueOf(format.format(value1)));
            } else {
                value2 = parseToDouble(secondValue);
                value2 = -value2;
                secondValue.setText(String.valueOf(format.format(value2)));
            }
        }
    }

    public void vibration(){
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(400);
    }

    public void showToast(String message){
        Toast toast = Toast.makeText(this,message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public boolean isEmpty(TextView place){
        if(place.getText().toString().isEmpty()){
            return true;
        }
        else return false;
    }

    public Double parseToDouble(TextView value){
        return Double.parseDouble(value.getText().toString());
    }

    public String deleteLastSign(TextView value){
        newValue = value.getText().toString();
        oldValue = newValue.substring(newValue.length() - 1);
        if(oldValue.equals(".")){
            comma.setEnabled(true);
        }
        newValue = newValue.substring(0, newValue.length() - 1);
        return newValue;
    }
}
