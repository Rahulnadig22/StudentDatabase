package com.example.database;

import java.io.Serializable;

public class student implements Serializable {
    private String name;
    private long rollNo;
    private String BG;
    private String DOB;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRollNo() {
        return rollNo;
    }

    public void setRollNo(long rollNo) {
        this.rollNo = rollNo;
    }

    public String getBG() {
        return BG;
    }

    public void setBG(String BG) {
        this.BG = BG;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }
}
