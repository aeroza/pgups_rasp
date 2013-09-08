package ru.pgups.develop.timetableapp;

import java.io.Serializable;

import ru.pgups.develop.timetableapp.group;

public class course implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1623458151371995910L;
	private int level;
	private group head;
	private group tail;	
	private course next;
	private int totalGroups;
	public course(int l){
		setLevel(l);
		setHead(null);
		setTail(null);		
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public group getHead() {
		return head;
	}
	public void setHead(group head) {
		this.head = head;
	}
	public group getTail() {
		return tail;
	}
	public void setTail(group tail) {
		this.tail = tail;
	}
	public course getNext() {
		return next;
	}
	public void setNext(course next) {
		this.next = next;
	}
	public int getTotalGroups() {
		return totalGroups;
	}
	public void setTotalGroups(int totalGroups) {
		this.totalGroups = totalGroups;
	}
	public void addGroup(String n){
		if (getHead() == null){
			setHead(new group(n));
			setTail(getHead());			
		}
		else
		{
			group tmp = new group(n);
			getTail().setNext(tmp);
			setTail(tail.getNext());
		}
		setTotalGroups(getTotalGroups() + 1);
	}
	public void removeGroups(){
		group temp;
		while(getHead() != getTail()){
			temp = getHead();
			setHead(getHead().getNext());
			temp.setNext(null);			
		}
		setHead(null);
		setTotalGroups(0);
	}	
}
