package com.mertkoroglu.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup rgGender;
    EditText name;
    EditText age;
    Button btnBMI, btnIntake;
    String gender;
    TextView tvTitle;

    private ValueAnimator colorAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_main);

        rgGender = findViewById(R.id.rgGender);
        age = findViewById(R.id.etAge);
        name = findViewById(R.id.etName);
        btnBMI = findViewById(R.id.btnBMI);
        btnIntake = findViewById(R.id.btnIntake);
        tvTitle = findViewById(R.id.tvTitle);

        colorAnim = ObjectAnimator.ofInt(tvTitle, "textColor", Color.BLUE,Color.GREEN );
        colorAnim.setDuration(500);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rbMale){
                    gender = "Male";
                }else{
                    gender = "Female";
                }
            }
        });

        btnBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                }else if(age.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Age cannot be empty", Toast.LENGTH_SHORT).show();
                }else if(gender == null){
                    Toast.makeText(MainActivity.this, "You must select your gender", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(MainActivity.this, BmiCalculator.class);

                    intent.putExtra("age", age.getText().toString());
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("gender", gender);

                    startActivity(intent);
                }
            }
        });

        btnIntake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                }else if(age.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Age cannot be empty", Toast.LENGTH_SHORT).show();
                }else if(gender == null){
                    Toast.makeText(MainActivity.this, "You must select your gender", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(MainActivity.this, CalorieCalculator.class);

                    intent.putExtra("age", age.getText().toString());
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("gender", gender);

                    startActivity(intent);
                }
            }
        });

    }
}