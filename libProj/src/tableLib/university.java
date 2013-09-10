package tableLib;

import java.io.Serializable;

import tableLib.university;

public class university implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2222470044591596364L;
	private String name;
	private faculty head;
	private faculty tail;
	
	public university(String n){
		setName(n);
		setHead(null);
		setTail(null);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public faculty getHead() {
		return head;
	}
	public void setHead(faculty head) {
		this.head = head;
	}
	public faculty getTail() {
		return tail;
	}
	public void setTail(faculty tail) {
		this.tail = tail;
	}

	public void addFaculty(String n){
		if (getHead() == null){
			setHead(new faculty(n));
			setTail(getHead());			
		}
		else
		{
			faculty tmp = new faculty(n);
			getTail().setNext(tmp);
			setTail(tail.getNext());
		}
	}
	public void removeFaculties(){
		faculty temp;
		while(getHead() != getTail()){
			temp = getHead();
			setHead(getHead().getNext());
			temp.setNext(null);			
		}
		setHead(null);
	}	
}
