package com.mycompany.a2;

import com.codename1.charts.models.*;

public abstract class Fixed extends GameObject{
	
	public Fixed(){
		super();
	}
	
	public Fixed(int newcolor) {
		super(newcolor);
	}
	
	// Constructor with given size and color
	public Fixed(int newsize, int newcolor){
		super(newsize, newcolor);
	}
	
	//Constructor with given size , color and location
	public Fixed(int newsize, Point newlocation, int newcolor) {
		super(newsize, newlocation, newcolor);
	}

	public void setLocation()
	{
		//fixed objects cannot move
	}
	
	public void move(){
		//fixed objects cannot move
	}
}
