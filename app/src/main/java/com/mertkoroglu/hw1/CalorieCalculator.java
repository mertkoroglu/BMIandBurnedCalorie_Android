package com.mertkoroglu.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CalorieCalculator extends AppCompatActivity {
    Spinner spExercise;
    ImageView imgExercise;
    EditText etWeight, etMinutes;
    Button btnCalc, btnBack;
    TextView tvName, tvAge, tvGender;


    String exercise;
    double calorie;
    double weight, minutes;

    String name, age, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_calorie_calculator);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        age = intent.getStringExtra("age");
        gender = intent.getStringExtra("gender");

        spExercise = findViewById(R.id.spExercise);
        imgExercise = findViewById(R.id.imgExercise);
        etWeight = findViewById(R.id.etWeight2);
        etMinutes = findViewById(R.id.etMinutes);
        btnCalc = findViewById(R.id.btnCalc2);
        tvName = findViewById(R.id.tvName);
        tvAge = findViewById(R.id.tvAge);
        tvGender = findViewById(R.id.tvGender);
        btnBack = findViewById(R.id.btnBack2);

        tvGender.setText("Gender: " + gender);
        tvName.setText("Name: " + name);
        tvAge.setText("Age: " + age);

        spExercise.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                exercise = spExercise.getItemAtPosition(position).toString();

                switch (exercise) {
                    case "Swimming":
                        imgExercise.setImageResource(R.drawable.swimming);
                        break;
                    case "Cycling":
                        imgExercise.setImageResource(R.drawable.cycling);
                        break;
                    case "Walking":
                        imgExercise.setImageResource(R.drawable.walking);
                        break;
                    default:
                        imgExercise.setImageResource(R.drawable.running);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etWeight.getText().toString().equals("")){
                    Toast.makeText(CalorieCalculator.this, "Weight cannot be empty", Toast.LENGTH_SHORT).show();

                }else if(etMinutes.getText().toString().equals("")){
                    Toast.makeText(CalorieCalculator.this, "Minutes cannot be empty", Toast.LENGTH_SHORT).show();
                }else {
                    exercise = spExercise.getSelectedItem().toString();
                    weight = Double.parseDouble(etWeight.getText().toString());
                    minutes = Double.parseDouble(etMinutes.getText().toString());


                    switch (exercise) {
                        case "Swimming":
                            calorie = (5.8 * weight * 3.5) / 200 * minutes;
                            break;
                        case "Cycling":
                            calorie = (4 * weight * 3.5) / 200 * minutes;
                            break;
                        case "Walking":
                            calorie = (3.5 * weight * 3.5) / 200 * minutes;
                            break;
                        default:
                            calorie = (8 * weight * 3.5) / 200 * minutes;
                            break;
                    }
                    makeAndShowDialog(calorie);

                }
            }
        });
    }

    private void makeAndShowDialog(double message) {
        android.app.AlertDialog.Builder box = new AlertDialog.Builder(this);
        box.setTitle("You lost: ");
        box.setMessage(message + " calories!");
        box.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        box.create();
        box.show();
    }
}