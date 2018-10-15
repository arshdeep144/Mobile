package com.example.arshu.lab07;

/**
 * Created by Arshu on 2017-11-13.
 */

public class Grades {
    private int studentID;
    private String courseComponent;
    private float mark;

    public Grades(String courseComponent, float mark){
        this.courseComponent = courseComponent;
        this.mark = mark;
    }

    public int getStudentID(){
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getCourseComponent() {
        return courseComponent;
    }

    public void setCourseComponent(String courseComponent) {
        this.courseComponent = courseComponent;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return ""+ this.studentID + " "+ courseComponent + " " + mark;
    }
}
