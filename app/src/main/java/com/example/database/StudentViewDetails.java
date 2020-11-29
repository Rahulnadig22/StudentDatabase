package com.example.database;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class StudentViewDetails extends AppCompatActivity implements StudentListAdapter.StudentClickListener{

    private DatabaseHelper dbHelper;
    private RecyclerView mRcStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view_details);
        mRcStudent = findViewById(R.id.rv_student_details);
        mRcStudent.setLayoutManager(new LinearLayoutManager(StudentViewDetails.this,RecyclerView.VERTICAL,false));
        dbHelper = new DatabaseHelper(StudentViewDetails.this);
        setDataToAdapter();


    }
    private void setDataToAdapter(){
        ArrayList<student> students = dbHelper.getStudentFromDatabase(dbHelper.getReadableDatabase());
        StudentListAdapter adapter = new StudentListAdapter(StudentViewDetails.this,students);
        adapter.setListener(StudentViewDetails.this);
        mRcStudent.setAdapter(adapter);
    }

    public void onAddClicked(View view){
        startActivityForResult(new Intent(StudentViewDetails.this,MainActivity.class),101);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101 && resultCode == Activity.RESULT_OK){
            setDataToAdapter();
        }else if(requestCode == 120 && resultCode == Activity.RESULT_OK){
            setDataToAdapter();
        }
    }

    @Override
    public void onEditStudentClicked(student student) {
//        Toast.makeText(StudentViewDetails.this,"Edit",Toast.LENGTH_LONG).show();
        Intent editIntent = new Intent(StudentViewDetails.this,MainActivity.class);
        editIntent.putExtra(MainActivity.BUNDLE_IS_EDIT,true);
        editIntent.putExtra(MainActivity.BUNDLE_STUDENT,student);
        startActivityForResult(editIntent,120);
    }

    @Override
    public void onDeleteStudentClicked(student student) {
//        Toast.makeText(StudentViewDetails.this,"Delete",Toast.LENGTH_LONG).show();
        dbHelper.deleteStudent(student,dbHelper.getWritableDatabase());
        setDataToAdapter();
    }
}