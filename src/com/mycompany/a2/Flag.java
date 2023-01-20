package com.mycompany.a2;

import com.codename1.charts.models.*;
import com.codename1.charts.util.ColorUtil;

public class Flag extends Fixed {

	private int sequenceNumber;
	private int size=10;
	
	//Constructor
	public Flag(){
		super();
		setSequenceNumber(0);
	}
	
	// Constructor with given color
	public Flag(int newcolor, int newseq){
		super(newcolor);
		setSequenceNumber(newseq);
	}
	
	//Constructor with size, color and sequencenumber
	public Flag(int newsize, int newcolor, int newseq) {
		super(newsize, newcolor);
		setSequenceNumber(newseq);
	}
	
	//Constructor with given size, color and location
	public Flag(int newsize, Point newlocation, int newcolor) {
		super(newsize, newlocation, newcolor);
		setSequenceNumber(0);
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}
	
	public void setSequenceNumber(int newsequenceNumber) {
		sequenceNumber = newsequenceNumber;
	}
	
	public String toString()
	{
		//print flag
		String flagstring = "Flag: ";
		//print loc
		String parentDesc = "loc=" + getLocation().getX() + "," + getLocation().getY() 
				+" color=[" + ColorUtil.red(getColor()) + "," + 
				ColorUtil.green(this.getColor()) + "," + ColorUtil.blue(getColor()) + 
				"]";
		String myDesc = "size= " + size + " seqNum=" + getSequenceNumber();
		
		return flagstring + parentDesc + myDesc;
	}
	
	public void setColor()
	{
		//flags can not change their color
	}
}
