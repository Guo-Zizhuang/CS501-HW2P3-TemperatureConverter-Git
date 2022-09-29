package com.example.hw3p3_temperatureconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private EditText celsius_EditText;
    private EditText fahrenheit_EditText;
    private SeekBar celsius_SeekBar;
    private SeekBar fahrenheit_SeekBar;
    private TextView interestMessage_TextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        celsius_EditText = (EditText) findViewById(R.id.celsius_EditText);
        fahrenheit_EditText = (EditText) findViewById(R.id.fahrenheit_EditText);
        celsius_SeekBar = (SeekBar) findViewById(R.id.celsius_SeekBar);
        fahrenheit_SeekBar = (SeekBar) findViewById(R.id.fahrenheit_SeekBar);
        interestMessage_TextView = (TextView) findViewById(R.id.interestMessage_TextView);

        celsius_SeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                String tmp_string = String.format("%.2f", (progress * 1.8 + 32));
                Double tmp_EditText = Double.parseDouble(tmp_string);
                Integer tmp_Seekbar =  (int) Math.ceil(tmp_EditText);
                if ((double)Math.abs((int) Math.ceil(tmp_EditText)-tmp_EditText) <= 0.5){
                    tmp_Seekbar =  (int) Math.ceil(tmp_EditText);
                }else{
                    tmp_Seekbar =  (int) Math.floor(tmp_EditText);
                }

                fahrenheit_SeekBar.setProgress(Integer.parseInt (tmp_Seekbar.toString()));
                celsius_EditText.setText(String.valueOf(progress));
                if (progress <= 20){
                    interestMessage_TextView.setText(getResources().getString(R.string.cold));
                }else{
                    interestMessage_TextView.setText(getResources().getString(R.string.warm));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        fahrenheit_SeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                if (progress < 32){
                    progress = 32;
                    fahrenheit_SeekBar.setProgress(progress);
                }
                String tmp_string = String.format("%.2f", ((progress - 32) / 1.8));
                Double tmp_EditText = Double.parseDouble(tmp_string);
                Integer tmp_Seekbar =  (int) Math.ceil(tmp_EditText);
                if ((double)Math.abs((int) Math.ceil(tmp_EditText)-tmp_EditText) <= 0.5){
                    tmp_Seekbar =  (int) Math.ceil(tmp_EditText);
                }else{
                    tmp_Seekbar =  (int) Math.floor(tmp_EditText);
                }
                celsius_SeekBar.setProgress(Integer.parseInt (tmp_Seekbar.toString()));
                fahrenheit_EditText.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        celsius_EditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                            Double tmp_EditText = Double.parseDouble(celsius_EditText.getText().toString());
                            if(tmp_EditText < 0){
                                tmp_EditText = 0.00;
                            }
                            Integer tmp_Seekbar =  (int) Math.ceil(tmp_EditText);
                            if ((double)Math.abs((int) Math.ceil(tmp_EditText)-tmp_EditText) <= 0.5){
                                tmp_Seekbar =  (int) Math.ceil(tmp_EditText);
                            }else{
                                tmp_Seekbar =  (int) Math.floor(tmp_EditText);
                            }
                            try{
                                celsius_SeekBar.setProgress(Integer.parseInt(tmp_Seekbar.toString()));
                            }catch(Exception ex) {}
                            return true;
                        }
                return false;
            }
        });

        fahrenheit_EditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                            Double tmp_EditText = Double.parseDouble(fahrenheit_EditText.getText().toString());
                            if(tmp_EditText < 32){
                                tmp_EditText = 32.00;
                            }
                            Integer tmp_Seekbar =  (int) Math.ceil(tmp_EditText);
                            if ((double)Math.abs((int) Math.ceil(tmp_EditText)-tmp_EditText) <= 0.5){
                                tmp_Seekbar =  (int) Math.ceil(tmp_EditText);
                            }else{
                                tmp_Seekbar =  (int) Math.floor(tmp_EditText);
                            }
                            try{
                                fahrenheit_SeekBar.setProgress(Integer.parseInt(tmp_Seekbar.toString()));
                            }catch(Exception ex) {}
                            return true;
                        }
                return false;
            }
        });
    }

}