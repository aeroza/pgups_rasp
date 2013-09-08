package ru.pgups.develop.timetableapp;

import ru.pgups.develop.timetableapp.lesson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.zip.GZIPInputStream;

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
            public void run() //Ётот метод будет выполн€тьс€ в побочном потоке
            {
                try
                {
                    URL myURL = new URL("http://bloodoed.zg5.ru/projects/pgups_timetable/package2.zip");
                    InputStream dataStream = myURL.openConnection().getInputStream();
                    InputStreamReader isr = new InputStreamReader(dataStream, "UTF-8");
                    StringBuffer data = new StringBuffer();
                    int c;
                    while ((c = isr.read()) != -1){
                           data.append((char) c);
                    }
                    System.out.println("data = " + data.length() + " \ndata:" + data + "c = " + c);
                    
                    /*String FILENAME = "package2.zip";
                    FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                    fos.write(data.length());
                    fos.close();*/
                    BufferedWriter out = new BufferedWriter(
                    		new FileWriter(Environment.DIRECTORY_DOWNLOADS + "/package2.zip"));
                    out.write(data.toString());
                    out.close();
                    try {
                    	//String tmp = getFilesDir() + "/package2.zip";
                    	//URL url = new URL(getFilesDir() + "/package2.zip")
                    	//File f = new File(getFilesDir() + "/package2.zip");
						extractPackage();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (URISyntaxException e){
						e.printStackTrace();
					}
                    
                } 
                catch (IOException ie)
                {
                	ie.printStackTrace();
                }
            }
        });
        myThready.start();	//«апуск потока		

	}
	
/*	public void extractPackage() throws IOException, ClassNotFoundException{
		System.out.println("»звлечение данных из jar-архива");
		// расположение и им€ архива
		String nameJar = getFilesDir() + "/package.zip";
		// куда файлы будут распакованы
		String destination = getFilesDir () + "/";
		new unPackZip().unpack(destination, nameJar);
        //System.out.println(pgups.getHead().getHead().getHead().getName());

	}*/
	
	public void extractPackage() throws IOException, ClassNotFoundException, URISyntaxException{
		String tmp = Environment.DIRECTORY_DOWNLOADS + "/package2.zip";
		System.out.println(tmp);
        FileInputStream fis = new FileInputStream(tmp);
		//URL url = new URL( "http://bloodoed.zg5.ru/projects/pgups_timetable/package2.zip" );
		//File f;
		//String f = "http://bloodoed.zg5.ru/projects/pgups_timetable/package2.zip";
		//FileInputStream fis = new FileInputStream((File) f);

        GZIPInputStream gs = new GZIPInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(gs);
        university pgups= (university) ois.readObject();
        //Employee sam = (Employee) ois.readObject();
        System.out.println(pgups.getHead().getHead().getHead().getName());
        ois.close();
        fis.close();		
	}
	
}

