package ca.uoit.csci4100u.internetresourcesdemo;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class GetDataTask extends AsyncTask<String, Void, ArrayList<Student>> {
    private StudentDataObserver observer;

    public void setObserver(StudentDataObserver observer) {
        this.observer = observer;
    }

    @Override
    protected ArrayList<Student> doInBackground(String... urls) {
        ArrayList<Student> students = new ArrayList<>();

        // Download the student data
        try {
            // connect to the website
            URL url = new URL(urls[0]);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            // download the data
            InputStream inputRaw = conn.getInputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(inputRaw));
            String line = input.readLine();
            while ((line = input.readLine()) != null) {
                String[] columns = line.split(",");
                Student student = new Student(columns[0],
                                              columns[1],
                                              columns[2],
                                              Float.parseFloat(columns[3]));
                students.add(student);
            }
            inputRaw.close();
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return students;
    }

    protected void onPostExecute(ArrayList<Student> data) {
        // this is the UI thread

        // update the UI with the new data
        observer.studentDataUpdated(data);
    }
}
