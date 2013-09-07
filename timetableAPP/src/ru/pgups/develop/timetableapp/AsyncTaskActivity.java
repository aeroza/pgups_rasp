package ru.pgups.develop.timetableapp;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
    //import android.provider.Settings.System;
    import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

    public class AsyncTaskActivity extends Activity implements OnClickListener {
    Button btn;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //btn = (Button) findViewById(R.id.button1);
                    //because we implement OnClickListener we only have to pass "this" (much easier)
        //btn.setOnClickListener(this);
    }

    public void onClick(View view){
        //detect the view that was "clicked"
        switch(view.getId())
        {
          case R.id.button1:
              new getData().execute("");
          break;

        }

    }

    private class getData extends AsyncTask<String, Void, String> {

          @Override
          protected String doInBackground(String... params) {
                for(int i=0;i<5;i++) {
                    try {
                        URL myURL = new URL("http://bloodoed.zg5.ru/projects/pgups_timetable/table.out");
                        InputStream dataStream = myURL.openConnection().getInputStream();
                        InputStreamReader isr = new InputStreamReader(dataStream, "UTF-8");
                        StringBuffer data = new StringBuffer();
                        int c;
                        while ((c = isr.read()) != -1){
                               data.append((char) c);
                        }
                    } catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }

                return "Executed";
          }      

          @Override
          protected void onPostExecute(String result) {
                System.out.println("Done!");
          }

          @Override
          protected void onPreExecute() {
        	  System.out.println("Executing!");
          }

          @Override
          protected void onProgressUpdate(Void... values) {
          }
    } 
}