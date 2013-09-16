import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPOutputStream;


public class temp {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/*String s = "You'll be crying like a bitch, when i serialize you! Ahahahahahaaaa!";
        FileOutputStream fos = new FileOutputStream("string.out");
        ObjectOutputStream oos = new ObjectOutputStream(new GZIPOutputStream(fos));
        oos.writeObject(s);
        oos.flush();
        oos.close();*/
		int a = 0;
		plus(a);
		System.out.println(a);
	}
	public static void plus(int b){
		b++;
	}	

}
