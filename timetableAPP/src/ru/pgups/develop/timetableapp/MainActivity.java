package ru.pgups.develop.timetableapp;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import tableLib.*;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import ru.pgups.develop.timetableapp.AsyncLoad;

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
	/*Context ctx;
	public ProgressDialog loadMsg;	
	boolean loadSuccess = false;
	public void onDownloadPreExecute() {
		loadMsg = new ProgressDialog(this);
	    loadMsg.setMessage("Загрузка расписания...");
	    loadMsg.setIndeterminate(true);
	    loadMsg.setCancelable(false);
	    loadMsg.show();
	}	
	
	public void onPostDownloadExecute(){
		loadMsg.dismiss();
	}*/

//метод ниже пока лень править, так что больше интересен readTable 
	public void getTable(View view) {
		System.out.println("hello!");
		/*System.out.println(Context.MODE_PRIVATE);
		AsyncLoad al = new AsyncLoad();
		al.forceGet();*/
		

		
		Thread download = new Thread(new Runnable() {
			public void run()
			{

				try {					
					URL myURL = new URL("http://bloodoed.zg5.ru/projects/pgups_timetable/tableZIP.out");
					InputStream dataStream = myURL.openConnection().getInputStream();
					String FILENAME = "table.cer"; 
					OutputStream fos = openFileOutput(FILENAME,	Context.MODE_PRIVATE);
					byte[] b = new byte[1];
					try{
						while((dataStream.read(b)) != -1){
							fos.write(b);
						}
					}catch(IOException e){
						System.out.println("Something saaaad happened(((");
					}
					dataStream.close();
					fos.close();
					//loadSuccess = true;
				} catch (IOException ie) {
					//onPostDownloadExecute();

					ie.printStackTrace();
				}
			}
		});
		download.start();
		/*onDownloadPreExecute();
		 // Запуск потока
		do
		{
		    try{
		        download.join(250);				//Подождать окончания загрузки четверть секунды.
		    }catch(InterruptedException e){}
		}
		while(download.isAlive());		
		onPostDownloadExecute();
		if (!loadSuccess){
			Toast toast = Toast.makeText(getApplicationContext(), "Не удалось установить соединение(((", Toast.LENGTH_LONG); 
			toast.show();	
		}*/
	}

	public void readTable(View view) throws OptionalDataException,
			ClassNotFoundException, IOException {
		System.out.println("hello!");
		Thread myThready = new Thread(new Runnable() {
			public void run() // Этот метод будет выполняться в побочном потоке
			{
				try {
					InputStream dataStream = openFileInput("table.cer");
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
