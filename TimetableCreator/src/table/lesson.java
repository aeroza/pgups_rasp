package table;

import java.io.Serializable;

public class lesson implements Serializable{
	protected String subj;
	protected String teacher;
	protected String room;
	protected short week;
	protected short day;
	protected short time;
	private lesson next;

	public lesson(String s, String tn, String r, short w, short d, short t){
		subj = s;
		teacher = tn;
		room = r;
		week = w; 	//0 - both, 1 - top, 2 - bottom
		day = d; 	//1-6
		time = t;	//1-5
	}
	public lesson getNext() {
		return next;
	}
	public void setNext(lesson next) {
		this.next = next;
	}
	public String getSubj(){
		return subj;		
	}
	public String getTeacher(){
		return teacher;		
	}
	public String getRoom(){
		return room;		
	}	
	public short getWeek(){
		return week;		
	}
	public short getDay(){
		return day;		
	}	
	public short getTime(){
		return time;		
	}
}
