package ca.uoit.csci4100u.internetresourcesdemo;

public class Student {
    private String firstName;
    private String lastName;
    private String sid;
    private float gpa;

    public Student(String firstName,
                   String lastName,
                   String sid,
                   float gpa) {
        setFirstName(firstName);
        setLastName(lastName);
        setSid(sid);
        setGpa(gpa);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }
}
