package com.mycompany.a2;

import com.codename1.charts.models.Point;

public abstract class Movable extends GameObject {

	private int heading;
	private int speed;
	
	// Constructor with random heading and speed
	public Movable(){
		super();
		heading = randomInt(0, 359);
		speed = randomInt(5, 10);
	}
		
	// Constructor with given color
	public Movable(int newcolor){
		super(newcolor);
		heading = randomInt(0, 359);
		speed = randomInt(5, 10);
	}
	
	//Constructor with given size, color, location, speed, and heading
	public Movable(int newsize, Point newlocation, int newcolor, int newheading, int newspeed) {
		super(newsize, newlocation, newcolor);
		heading = newheading;
		speed = newspeed;
	}

	public int getHeading() {
		return heading;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setHeading(int newheading) {
		heading = newheading;
	}
	
	public void setSpeed(int newspeed) {
		speed = newspeed;
	}
	
	public void move()
	{
		int angle = 90-heading;
		float deltaX = (((float)Math.cos(Math.toRadians(angle)))*speed);
		float deltaY = (((float)Math.sin(Math.toRadians(angle)))*speed);
		
		super.move(deltaX, deltaY);
	}
}

