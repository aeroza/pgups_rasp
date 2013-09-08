package ru.pgups.develop.timetableapp;

import java.io.Serializable;

import ru.pgups.develop.timetableapp.lesson;

public class group implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4895479639028265696L;
	private lesson head;
	private lesson tail;
	private int totalLessons;
	private String name;
	private group next;
	public group(String n){
		setTotalLessons(0);
		setName(n);
		setHead(null);
		setTail(null);
	}
	public int getTotalLessons() {
		return totalLessons;
	}
	public void setTotalLessons(int i) {
		this.totalLessons = i;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public lesson getHead() {
		return head;
	}
	public void setHead(lesson head) {
		this.head = head;
	}
	public lesson getTail() {
		return tail;
	}
	public void setTail(lesson tail) {
		this.tail = tail;
	}	
	public group getNext() {
		return next;
	}
	public void setNext(group next) {
		this.next = next;
	}
	public void addLesson(String s, String tn, String r, short w, short d, short t){
		if (getHead() == null){
			setHead(new lesson(s, tn, r, w, d, t));
			setTail(getHead());			
		}
		else
		{
			lesson tmp = new lesson(s, tn, r, w, d, t);
			getTail().setNext(tmp);
			setTail(tail.getNext());
		}
		setTotalLessons(getTotalLessons() + 1);
	}
	public void removeLessons(){
		lesson temp;
		while(getHead() != getTail()){
			temp = getHead();
			setHead(getHead().getNext());
			temp.setNext(null);			
		}
		setHead(null);
		setTotalLessons(0);
	}
}
