package com.example.arshu.assignment2;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class DownloadOriginTask extends AsyncTask<String, Void, String> {
    private Exception exception = null;
    private Observer observer;
    public void setObserver(Observer observer){this.observer = observer;}
    @Override
    protected String doInBackground(String... urls) {
        String definition = "";
        try {
            // parse out the data
            URL url = new URL(urls[0]);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            InputStream inputRaw = conn.getInputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(inputRaw));
            String line = input.readLine();
            while (line != null) {
                definition += line;
                line=input.readLine();
            }
            inputRaw.close();
            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
            exception = e;
        }

        return definition;
    }

    @Override
    protected void onPostExecute(String result) {
        observer.dataUpdated(result);
    }
}
