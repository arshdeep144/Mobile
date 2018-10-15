package ca.uoit.csci4100u.internetresourcesdemo;

import java.util.ArrayList;

public interface StudentDataObserver {
    void studentDataUpdated(ArrayList<Student> studentData);
}
