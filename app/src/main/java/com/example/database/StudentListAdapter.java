package com.example.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentListHolder> {

    private Context context;
    private ArrayList<student> studentList;

    private StudentClickListener listener;

    public StudentListAdapter(Context context,ArrayList<student> student){
        this.context = context;
        this.studentList = student;
    }

    public void setListener(StudentClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public StudentListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StudentListHolder holder = new StudentListHolder(LayoutInflater.from(context).inflate(R.layout.student_details,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentListHolder holder, int position) {
        student currentStudent = studentList.get(position);

        holder.mTvname.setText(currentStudent.getName());
        holder.mTvroll.setText(String.valueOf(currentStudent.getRollNo()));
        holder.mTvbg.setText(currentStudent.getBG());
        holder.mTvDOB.setText(currentStudent.getDOB());

        holder.mrledit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onEditStudentClicked(currentStudent);
                }
            }
        });

        holder.mrldelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteStudentClicked(currentStudent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return studentList.size();
    }

    class StudentListHolder extends RecyclerView.ViewHolder{

        private TextView mTvname;
        private TextView mTvroll;
        private TextView mTvbg;
        private TextView mTvDOB;

        private RelativeLayout mrledit;
        private RelativeLayout mrldelete;


        public StudentListHolder(@NonNull View itemView) {
            super(itemView);
            mTvname = itemView.findViewById(R.id.name_details);
            mTvroll = itemView.findViewById(R.id.rollno_details);
            mTvbg = itemView.findViewById(R.id.blood_group_details);
            mTvDOB = itemView.findViewById(R.id.DOB_details);
            mrledit = itemView.findViewById(R.id.edit_icon);
            mrldelete = itemView.findViewById(R.id.delete_icon);
        }
    }

    public interface StudentClickListener{
        void onEditStudentClicked(student student);
        void onDeleteStudentClicked(student student);
    }
}
