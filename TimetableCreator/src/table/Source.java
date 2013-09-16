package table;

import tableLib.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.zip.GZIPOutputStream;


	/* Это главный класс серверного приложения.
	 * Здесь мы будем наполнять объекты и сохранять их в файлы.
	 */

public class Source {
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello, world!");
		//примитивное консольное заполнение трех объектов
		//Scanner поможет нам считывать из консоли
		Scanner sc = new Scanner(System.in);
		university pgups = new university("PGUPS-write");
		//названия методов говорят сами за себя=)
		pgups.addFaculty("Bridges");
		pgups.getHead().addCourse(1);
		pgups.getHead().getHead().addGroup("tst101");
		group tst123 = pgups.getHead().getHead().getHead();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		for (short i = 0; i<3; i++){
			System.out.println("Enter subject:");
			String s, tn, r;
			short w, d, t;
			s = in.readLine();
			System.out.println("Enter teacher");
			tn = in.readLine();
			System.out.println("Enter room");
			r = in.readLine();
			System.out.println("Enter week");
			w = sc.nextShort();
			System.out.println("Enter day");
			d = sc.nextShort();				
			System.out.println("Enter time");
			t = sc.nextShort();		
			tst123.addLesson(s, tn, r, w, d, t);
		}
		sc.close();
		
		//пишем объект в файл
        try {
            //указываем выходной файл
            FileOutputStream fos = new FileOutputStream("tableZIP.out");
            //сериализуем объект, но на выход идет еще и ужатый через
            //GZIPOutputStream поток
            ObjectOutputStream oos = new ObjectOutputStream(new GZIPOutputStream(fos));
            //пишем в файл один объект
            oos.writeObject(pgups);
            oos.flush();
            oos.close();        

        } catch (IOException ex){
            ex.printStackTrace();
        }/*
        //читаем объект из файла
        /*FileInputStream fis = new FileInputStream("table.out");
        ObjectInputStream oin = new ObjectInputStream(fis);
        university ts = null;
        try {
        	//university ts = new university("");
        	
			ts = (university) oin.readObject();
			System.out.println(ts.getHead().getHead().getHead().getName());
			oin.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        lesson temp = ts.getHead().getHead().getHead().getHead();
        do{
        	System.out.println(temp.getSubj());
        	System.out.println(temp.getRoom());
        	temp = temp.getNext();
        }while(temp!=ts.getHead().getHead().getHead().getTail());*/
        
	}
	
	//ниже - помойка
	
	public static void forlulz() throws IOException{
        URL myURL = new URL("http://bloodoed.zg5.ru/projects/pgups_timetable/table.out");
        InputStream dataStream = myURL.openConnection().getInputStream();
        InputStreamReader isr = new InputStreamReader(dataStream);
        StringBuffer data = new StringBuffer();
        int c;
        while ((c = isr.read()) != -1){
               data.append((char) c);
        }
        isr.close();
        dataStream.close();
        System.out.println("data = " + data.length() + " \ndata:"+ data + "c = " + c );
        //data.charAt(0);
        String FILENAME = "table.cer";
        FileOutputStream fos = new FileOutputStream(FILENAME);
        fos.write(data.toString().getBytes());
        fos.close();      
//==============================================================================================        
        FileInputStream fis = new FileInputStream("table.cer");
		/*InputStream dataStream1 = fis;
        InputStreamReader isr1 = new InputStreamReader(dataStream1);
        StringBuffer data1 = new StringBuffer();
        int c1;
        while ((c1 = fis.read()) != -1){
               data1.append((char) c1);
        }		
        System.out.println(data.equals(data1));
        System.out.println("data = " + data1.length() + " \ndata:" + data1 + "c = " + c1);
        ByteArrayInputStream byteA = new ByteArrayInputStream(data.toString().getBytes());
        fis.close();
        dataStream1.close();
        isr1.close();*/
        
		ObjectInputStream oin = new ObjectInputStream(fis);
		try {
			university ts = (university) oin.readObject();
			lesson temp = ts.getHead().getHead().getHead().getHead();
	        do{
	        	System.out.println(temp.getSubj());
	        	System.out.println(temp.getRoom());
	        	temp = temp.getNext();
	        }while(temp!=null);			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


