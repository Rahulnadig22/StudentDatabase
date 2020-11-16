package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.EthiopicCalendar;
import android.opengl.ETC1;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText metName;
    private EditText metRoll;
    private EditText metBG;
    private  EditText metDOB;
    public static String keyword = "STUDENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView mtvDetails = findViewById(R.id.tv_details);
        metName  = findViewById(R.id.et_name);
        metRoll = findViewById(R.id.et_rollno);
        metBG = findViewById(R.id.et_bloodgroup);
        metDOB = findViewById(R.id.et_DOB);

    }

    public void onSubmitClicked(View view){
        String mname = metName.getText().toString();
        long mrollno = !metRoll.getText().toString().isEmpty()?Long.parseLong(metRoll.getText().toString()):0;
        String mbg = metBG.getText().toString();
        String mdob = metDOB.getText().toString();

        if(mname.isEmpty()){
            Toast.makeText(MainActivity.this,"Please Enter Name",Toast.LENGTH_LONG).show();
        }else if(mbg.isEmpty()){
            Toast.makeText(MainActivity.this,"Please Enter Blood Group",Toast.LENGTH_LONG).show();
        }else if (mdob.isEmpty()){
            Toast.makeText(MainActivity.this,"Please Enter DOB",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(MainActivity.this,"Submitted Successfully",Toast.LENGTH_LONG).show();
            student mstudent = new student();
            mstudent.setName(mname);
            mstudent.setRollNo(mrollno);
            mstudent.setBG(mbg);
            mstudent.setDOB(mdob);

            Intent details = new Intent(MainActivity.this, StudentViewDetails.class);
            details.putExtra(keyword, mstudent);
            startActivity(details);
        }
    }

}