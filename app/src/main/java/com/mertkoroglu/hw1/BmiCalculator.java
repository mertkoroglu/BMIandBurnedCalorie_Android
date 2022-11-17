package com.mertkoroglu.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class BmiCalculator extends AppCompatActivity {
    double cm, kg, avg;
    String name;
    String age;
    String gender;

    SeekBar height, weight;
    Button btnCalc, btnBack;
    TextView tvHeight, tvWeight, tvName, tvAge, tvGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_bmi_calculator);

        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        age = intent.getStringExtra("age");
        gender = intent.getStringExtra("gender");

        height = findViewById(R.id.sbHeight);
        weight = findViewById(R.id.sbWeight);
        btnCalc = findViewById(R.id.btnCalc);
        tvHeight = findViewById(R.id.tvHeight);
        tvWeight = findViewById(R.id.tvWeight);
        tvName = findViewById(R.id.tvName1);
        tvAge = findViewById(R.id.tvAge1);
        tvGender = findViewById(R.id.tvGender1);
        btnBack = findViewById(R.id.backBtn);

        tvName.setText("Name: " + name);
        tvAge.setText("Age: " + age);
        tvGender.setText("Gender: " + gender);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cm = (height.getProgress() / 100.0) * (height.getProgress() / 100.0);
                kg = weight.getProgress();
                avg = kg/cm;

                if( avg < 18.5)
                    makeAndShowDialog(avg + " = Underweight");
                else if( avg >= 18.5 && avg < 25)
                    makeAndShowDialog(avg + " = Normal");
                else if (avg >= 25 && avg < 30)
                    makeAndShowDialog(avg + " = Overweight");
                else
                    makeAndShowDialog(avg + " = Obese");


            }
        });

        height.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvHeight.setText("Height (cm): " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        weight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvWeight.setText("Weight (kg): " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    private void makeAndShowDialog(String message) {
        AlertDialog.Builder box = new AlertDialog.Builder(this);
        box.setTitle("Your BMI is: ");
        box.setMessage(message);
        box.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        box.create();
        box.show();
    }

}