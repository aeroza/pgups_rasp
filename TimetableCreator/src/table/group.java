package table;

import java.io.Serializable;

import table.lesson;

public class group implements Serializable {
	protected lesson lessons[] = new lesson[30];
	private short n;
	public void group(){
		n = 0;
	}
	public void addLesson(String s, String tn, String r, short w, short d, short t){
		lessons[n] = new lesson(s, tn, r, w, d, t);
		n++;
	}
}
