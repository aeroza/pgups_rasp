package table;

import table.group;

import table.packFile;
import table.unPackZip;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class main {
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello, world!");
		/*Scanner sc = new Scanner(System.in);
		university pgups = new university("PGUPS-write");
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
            
            FileOutputStream fos = new FileOutputStream("table.out");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(pgups);
            oos.flush();
            oos.close();    */    
            //makePackage2();

        /*} catch (IOException ex){
            ex.printStackTrace();
        }*/
        //читаем объект из файла
        FileInputStream fis = new FileInputStream("table.out");
        ObjectInputStream oin = new ObjectInputStream(fis);
        university ts = null;
        try {
        	//university ts = new university("");
        	
			ts = (university) oin.readObject();
			makePackage2(ts);
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
        }while(temp!=ts.getHead().getHead().getHead().getTail());
        
	}
	/*public static void makePackage()
	{
		System.out.println("Создание jar-архива");
		// массив файлов для сжатия
		String[] filesToJar = new String[1];
		filesToJar[0] = "table.out";
//		filesToJar[1] = "chapt09//UseJar.class";
		byte[] buffer = new byte[1024];
		// имя полученного архива
		String jarFileName = "package.jar";
		packFile.pack(filesToJar, jarFileName, buffer);
	}*/
	
	public static void makePackage2(university t) throws IOException
	{
		FileOutputStream fos = new FileOutputStream("package2.zip");
        GZIPOutputStream gz = new GZIPOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(gz);
        oos.writeObject(t);
        //oos.writeObject(sam);
        oos.flush();
        oos.close();
        fos.close();
        try {
			extractFile();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void extractFile() throws IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream("package2.zip");
		//File f;
		//String f = "http://bloodoed.zg5.ru/projects/pgups_timetable/package2.zip";
		//FileInputStream fis = new FileInputStream((File) f);
        GZIPInputStream gs = new GZIPInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(gs);
        university pgups= (university) ois.readObject();
        //Employee sam = (Employee) ois.readObject();
        System.out.println(pgups.getHead().getHead().getHead().getName());
        ois.close();
        //fis.close();
	}
}

