package table;

import java.io.Serializable;

import table.lesson;

public class group implements Serializable {
	private lesson head;
	private lesson tail;
	private short totalLessons;
	private String name;
	public group(String n){
		totalLessons = 0;
		setName(n);
		head = tail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void addLesson(String s, String tn, String r, short w, short d, short t){
		if (head == null){
			head = new lesson(s, tn, r, w, d, t);
			tail = head;			
		}
		else
		{
			lesson tmp = new lesson(s, tn, r, w, d, t);
			tail.setNext(tmp);
			tail = tail.getNext();
		}
		totalLessons++;
	}
	public void removeLessons(){
		lesson temp;
		while(head != tail){
			temp = head;
			head = head.getNext();
			temp.setNext(null);			
		}
		head = null;
		totalLessons = 0;
	}
}
