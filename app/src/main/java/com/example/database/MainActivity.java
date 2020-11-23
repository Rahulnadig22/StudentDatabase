package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.icu.util.EthiopicCalendar;
import android.opengl.ETC1;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText metName;
    private EditText metRoll;
    private  EditText metDOB;
    public static String keyword = "STUDENT";
    private  String selected_blood = "";
    private Spinner spnblood;
    private DatabaseHelper dbhelper;
    private Boolean first = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView mtvDetails = findViewById(R.id.tv_details);
        metName  = findViewById(R.id.et_name);
        metRoll = findViewById(R.id.et_rollno);
        spnblood = findViewById(R.id.blood_group);
        metDOB = findViewById(R.id.et_DOB);

        final String[] bloodgroups = getResources().getStringArray(R.array.bloodgroup);
        ArrayAdapter<String> bgAdapter = new ArrayAdapter<String>(MainActivity.this,R.layout.spinner_custom,R.id.spinner_text,bloodgroups);
        spnblood.setAdapter(bgAdapter);

        spnblood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    first = true;
                }else {
                    Toast.makeText(MainActivity.this,bloodgroups[position],Toast.LENGTH_LONG).show();
                    selected_blood = bloodgroups[position];
                    first = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        dbhelper = new DatabaseHelper(MainActivity.this);


    }

    public void onSubmitClicked(View view){
        String mname = metName.getText().toString();
        long mrollno = !metRoll.getText().toString().isEmpty()?Long.parseLong(metRoll.getText().toString()):0;
        String mdob = metDOB.getText().toString();

        if(mname.isEmpty()){
            Toast.makeText(MainActivity.this,"Please Enter Name",Toast.LENGTH_LONG).show();
        }else if (mdob.isEmpty()){
            Toast.makeText(MainActivity.this,"Please Enter DOB",Toast.LENGTH_LONG).show();
        }else if(first){
            Toast.makeText(MainActivity.this,"Please Select Blood Group",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(MainActivity.this,"Submitted Successfully",Toast.LENGTH_LONG).show();
            student mstudent = new student();
            mstudent.setName(mname);
            mstudent.setRollNo(mrollno);
            mstudent.setBG(selected_blood);
            mstudent.setDOB(mdob);

            dbhelper.insertDataToDatabase(dbhelper.getWritableDatabase(),mstudent);
            metName.setText("");
            metRoll.setText("");
            metDOB.setText("");
            spnblood.setSelection(0);
//            setResult(Activity.RESULT_OK);
//            finish();

            Intent details = new Intent(MainActivity.this, StudentViewDetails.class);
//            details.putExtra(keyword, mstudent);
            startActivity(details);

        }
    }

}