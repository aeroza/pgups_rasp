package ru.pgups.develop.timetableapp;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;





@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onClick(View view) throws IOException{
		System.out.println("hello!");
        Thread myThready = new Thread(new Runnable()
        {
            public void run() //Этот метод будет выполняться в побочном потоке
            {
                try
                {
                    URL myURL = new URL("http://bloodoed.zg5.ru/projects/pgups_timetable/table.out");
                    InputStream dataStream = myURL.openConnection().getInputStream();
                    InputStreamReader isr = new InputStreamReader(dataStream, "UTF-8");
                    StringBuffer data = new StringBuffer();
                    int c;
                    while ((c = isr.read()) != -1){
                           data.append((char) c);
                    }
                    System.out.println("data = " + data.length() + " \ndata:" + data + "c = " + c);
                    
                    String FILENAME = "table.cer";
                    FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                    fos.write(data.length());
                    fos.close();
                    
            } 
            catch (IOException ie) {
                  ie.printStackTrace();
            }
            }
        });
        myThready.start();	//Запуск потока		
		
		 }
	}

