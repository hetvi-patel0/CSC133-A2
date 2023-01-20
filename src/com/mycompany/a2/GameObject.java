package com.mycompany.a2;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject {
	private int size;
	private Point location;
	private int color;
	
	// Constructor with random size, color and location
	public GameObject(){
		size = randomInt(10, 50);			// size range 10-50
		color = ColorUtil.rgb(randomInt(0, 255), randomInt(0, 255), randomInt(0, 255));	// random color
		location = new Point(randomInt(0, 1000), randomInt(0, 1000));	// random location
	}
		
	// Constructor with given color
	public GameObject(int newcolor){
		size = randomInt(10, 50);			// size range 10-50
		color = newcolor;
		location = new Point(randomInt(0, 1000), randomInt(0, 1000));	// random location
	}
		
	// Constructor with given size and color
	public GameObject(int newsize, int newcolor){
		size = newsize;
		color = newcolor;
		location = new Point(randomInt(0, 1000), randomInt(0, 1000));	// random location
	}
	
	//Constructor with given size, color and location
	public GameObject(int newsize, Point newlocation, int newcolor) {
		size = newsize;
		location = newlocation;
		color = newcolor;
	}

	public int getSize() {
		return size;
	}
	
	public Point getLocation() {
		return location;
	}
	
	public int getColor() {
		return color;
	}
	
	public void setLocation(Point newLocation) {
		location = newLocation;
	}
	
	public void setColor(int newcolor) {
		color = newcolor;
	}

	public String toString() {
		String myDesc = "loc=" + getLocation().getX() + "," + getLocation().getY() 
					+" color=[" + ColorUtil.red(getColor()) + "," + 
					ColorUtil.green(this.getColor()) + "," + ColorUtil.blue(getColor()) + 
					"]" + " size=" + getSize();
		
		return myDesc;			
	}
	
	public int randomInt(int min, int max)
	{
		Random rand = new Random();
		return min + rand.nextInt(max);
	}

	public void move(float deltaX, float deltaY) {
		float newX = location.getX() + deltaX;
		float newY = location.getY() + deltaY;
		Point newPoint = new Point(newX, newY);
		setLocation(newPoint);
	}
}
