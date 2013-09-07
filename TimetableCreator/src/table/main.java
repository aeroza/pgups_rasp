package table;

import table.group;

import java.io.BufferedReader;
/*import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;*/
import java.io.FileOutputStream;
//import java.io.FileWriter;
import java.io.IOException;
//import java.io.InputStream;
import java.io.InputStreamReader;
//import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
//import java.io.Reader;
public class main {

	
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello, world!");
		Scanner sc = new Scanner(System.in);
		group tst123 = new group("tst123");
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
		
        try {
            
            FileOutputStream fos = new FileOutputStream("table.out");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            group ts = new group("");
            oos.writeObject(ts);
            oos.flush();
            oos.close();

        } catch (IOException ex){
            ex.printStackTrace();
        }
	}

}
