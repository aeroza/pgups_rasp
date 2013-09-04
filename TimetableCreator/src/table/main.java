package table;

import table.group;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
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
		group tst123 = new group();
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
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            System.out.println("Serializing...");
            oos.writeObject(tst123);
            oos.flush();
            baos.flush();            
            oos.close();
            baos.close();
            System.out.println(baos.toString());
            FileWriter oFile = new FileWriter("tableOut.ser", false);
            oFile.write(baos.toString());
            oFile.close();
            
            String s = "";
            Scanner inFile = new Scanner(new File("tableOut.ser"));
            while(inFile.hasNext())
            s += inFile.nextLine() + "\r\n";
            inFile.close();
            
            //ByteArrayInputStream bais = new ByteArrayInputStream(s.)
            
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            System.out.println("Deserializing...");
            group g1 = (group)ois.readObject();
            System.out.println("subj="+g1.lessons[0].getSubj());
            System.out.println("time3="+g1.lessons[2].getTime());
        } catch (IOException ex){
            ex.printStackTrace();
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }		
		//BufferedReader input 
	}

}
