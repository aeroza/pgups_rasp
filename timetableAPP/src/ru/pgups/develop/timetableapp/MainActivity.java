package ru.pgups.develop.timetableapp;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import tableLib.*;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

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
	//метод ниже пока лень править, так что больше интересен readTable 
	public void getTable(View view) throws IOException {
		System.out.println("hello!");
		Thread myThready = new Thread(new Runnable() {
			public void run() // Этот метод будет выполняться в побочном потоке
			{
				try {
					URL myURL = new URL("http://bloodoed.zg5.ru/projects/pgups_timetable/string.out");
					InputStream dataStream = myURL.openConnection().getInputStream();
					ByteArrayInputStream[] data = null;	
					byte[] c = null;
					dataStream.read(c);
					/*while ((c = isr.read()) != -1) {
						data.append((char) c);
					}*/

					System.out.println("c = " + c);
					// data.charAt(0);
					String FILENAME = "string.cer";
					FileOutputStream fos = openFileOutput(FILENAME,	Context.MODE_PRIVATE);
					fos.write(c, 0, c.length);
					fos.close();

				} catch (IOException ie) {
					ie.printStackTrace();
				}
			}
		});
		myThready.start(); // Запуск потока

	}

	public void readTable(View view) throws OptionalDataException,
			ClassNotFoundException, IOException {
		System.out.println("hello!");
		Thread myThready = new Thread(new Runnable() {
			public void run() // Этот метод будет выполняться в побочном потоке
			{
				URL myURL;
				try {
					//пилим ссылку
					myURL = new URL("http://bloodoed.zg5.ru/projects/pgups_timetable/tableZIP.out");
					//качаем в поток
					InputStream dataStream = myURL.openConnection().getInputStream();
					//здесь уже чтото знакомое - GZIPInputStream разархивирует поток,
					//а ObjectInputStream преобразует поток в объекты
					ObjectInputStream oin = new ObjectInputStream(new GZIPInputStream(dataStream));
					//читаем то, что записали
					university ts = (university) oin.readObject();
					//проверяем
			        lesson temp = ts.getHead().getHead().getHead().getHead();
			        do{
			        	System.out.println(temp.getSubj());
			        	System.out.println(temp.getRoom());
			        	temp = temp.getNext();
			        }while(temp!=null);					
					System.out.println(ts);
					oin.close();
					dataStream.close();
					//дальше кусочек исправлю попозже
					/*
					FileOutputStream fos = openFileOutput("table.cer",
							Context.MODE_PRIVATE);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(ts);
					oos.flush();
					oos.close();

					FileInputStream fis = openFileInput("table.cer");
					ObjectInputStream oIN = new ObjectInputStream(fis);
					String s1 = (String) oIN.readObject();
					System.out.println(s1);*/
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		myThready.start();
	}
}
