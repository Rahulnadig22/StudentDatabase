package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class StudentViewDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_details);
        TextView name = findViewById(R.id.tv_name);
        TextView roll = findViewById(R.id.tv_rollno);
        TextView bg = findViewById(R.id.tv_bg);
        TextView dob = findViewById(R.id.tv_dob);

        Bundle data = getIntent().getExtras();
        student studentdetails = (student) data.getSerializable(MainActivity.keyword);

        name.setText(studentdetails.getName());
        roll.setText(String.valueOf(studentdetails.getRollNo()));
        bg.setText(studentdetails.getBG());
        dob.setText(studentdetails.getDOB());

    }


}