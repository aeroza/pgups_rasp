package table;

import java.io.Serializable;

import table.course;

public class faculty implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2222470044591596364L;
	private String name;
	private course head;
	private course tail;
	private faculty next;
	private int totalFaculties;
	
	public faculty(String n){
		setName(n);
		setHead(null);
		setTail(null);
		setTotalFaculties(0);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public course getHead() {
		return head;
	}
	public void setHead(course head) {
		this.head = head;
	}
	public course getTail() {
		return tail;
	}
	public void setTail(course tail) {
		this.tail = tail;
	}
	public faculty getNext() {
		return next;
	}
	public void setNext(faculty next) {
		this.next = next;
	}

	public int getTotalFaculties() {
		return totalFaculties;
	}

	public void setTotalFaculties(int totalFaculties) {
		this.totalFaculties = totalFaculties;
	}

	public void addCourse(int l){
		if (getHead() == null){
			setHead(new course(l));
			setTail(getHead());			
		}
		else
		{
			course tmp = new course(l);
			getTail().setNext(tmp);
			setTail(tail.getNext());
		}
		setTotalFaculties(getTotalFaculties() + 1);
	}
	public void removeCourses(){
		course temp;
		while(getHead() != getTail()){
			temp = getHead();
			setHead(getHead().getNext());
			temp.setNext(null);			
		}
		setHead(null);
		setTotalFaculties(0);
	}	
}
