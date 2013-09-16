package ru.pgups.develop.timetableapp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
//import android.provider.Settings.System;

public class AsyncLoad extends Activity /*implements OnClickListener*/ {
	Button btn;
	public ProgressDialog loadMsg;
	Context ctx;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//btn = (Button) findViewById(R.id.button1);
		// because we implement OnClickListener we only have to pass "this"
		// (much easier)
		 //btn.setOnClickListener(this);
	}

	/*public void onClick(View view) {
		// detect the view that was "clicked"
		switch (view.getId()) {
		case R.id.button1:
			new getData().execute("");
			break;

		}

	}*/
	public void forceGet(){
		new getData().execute("");
	}

	private class getData extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) 
		{

			try {					
				System.out.println(Context.MODE_PRIVATE);
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


				//ie.printStackTrace();
			}
			return "Executed!";
		}
	}

		protected void onPostExecute(String result) {
			System.out.println("Done!");
			loadMsg.dismiss();
		}

		protected String onPreExecute() {
			System.out.println("Executing!");
			loadMsg = new ProgressDialog(this);
		    loadMsg.setMessage("Загрузка расписания...");
		    loadMsg.setIndeterminate(true);
		    loadMsg.setCancelable(false);
		    loadMsg.show();
			return "lol";
		}

		protected void onProgressUpdate(Void... values) {
		}
	}
