 package com.mycompany.a2;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class GameWorld extends Observable {

	private int Countlife = 3;
	final private int BlackColor = ColorUtil.BLACK;
	final private int RedColor = ColorUtil.rgb(255,0,0);
	final private int GreenColor = ColorUtil.GREEN;
	final private int BlueColor = ColorUtil.BLUE;
	private int timer = 0;
	private GameObjectCollection gameObjectCollection;
	private boolean soundOn = false;
	private int FlagCount;
	private int FoodStationCount;
	private int SpiderCount;
	private boolean gameOver;
	Random rand = new Random();
	
	//Constructor
	public GameWorld() {
		
	}
	
	//initial game objects/setup
	public void init() {
		gameObjectCollection = new GameObjectCollection();
		
		FlagCount = 0;
		FoodStationCount = 0;
		SpiderCount = 0;
		gameOver = false;
		addObjects();
		this.setChanged();
		this.notifyObservers(this);
	}
	
	//add game objects to world
	public void addObjects() {
		
		//add new flags
		int newflagcnt = 4+rand.nextInt(5);
		int newfoodstationcnt = 2;
		int newspidercnt = 2;
		int randomflagsize = rand.nextInt(40)+10;
		
		//add flag objects into collection
		for(int i=1;i<newflagcnt;i++)
		{
			gameObjectCollection.add(new Flag(randomflagsize, BlueColor, i));
			FlagCount++;
		}
		
		//add spider objects into collection
		for(int i=0;i<newspidercnt;i++)
		{
	 		gameObjectCollection.add(new Spider(BlackColor));
			SpiderCount++;
		}
		
		//add foodstation objects into collection
		for(int i=0;i<newfoodstationcnt;i++)
		{
			gameObjectCollection.add(new FoodStation(GreenColor));
			FoodStationCount++;
		}
		
		IIterator itr = gameObjectCollection.getIterator();
		while(itr.hasNext())
		{
			GameObject tempObject = itr.getNext();
			if(tempObject instanceof Flag)
			{
				Ant ant = Ant.getAnt();
				ant.setColor(RedColor);
				ant.setLocation(tempObject.getLocation());
				gameObjectCollection.add(ant);
				break;
			}
		}
	}
	
	//add new randomly-specified size and location food station
	public void addFoodStation()
	{
		gameObjectCollection.add(new FoodStation(GreenColor));
		FoodStationCount++;
	}
	
	//press 'a' fro acceleration
	public void increaseSpeed() {
		System.out.println("Ant accelerates!\n");
		IIterator itr = gameObjectCollection.getIterator();
		while(itr.hasNext())
		{
			GameObject tempObject = itr.getNext();
			if(tempObject instanceof Ant)
			{
				((Ant)tempObject).accelerate();
				break;
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}

	//press 'b' fro brake
	public void decreaseSpeed() {
		System.out.println("Ant brakes!\n");
		IIterator itr = gameObjectCollection.getIterator();
		while(itr.hasNext())
		{
			GameObject tempObject = itr.getNext();
			if(tempObject instanceof Ant)
			{
				((Ant)tempObject).brake();
				break;
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}

	//press 'l' to move ant to 5 degrees left
	public void headLeft() {
		System.out.println("Ant has truned left!\n");
		IIterator itr = gameObjectCollection.getIterator();
		while(itr.hasNext())
		{
			GameObject tempObject = itr.getNext();
			if(tempObject instanceof Ant)
			{
				((Ant)tempObject).steerleft();
				break;
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}

	//press 'r' to move ant to 5 degrees right 
	public void headRight() {
		System.out.println("Ant has truned right!\n");
		IIterator itr = gameObjectCollection.getIterator();
		while(itr.hasNext())
		{
			GameObject tempObject = itr.getNext();
			if(tempObject instanceof Ant)
			{
				((Ant)tempObject).steerright();
				break;
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}

	//press 'a number bwteen 1-9'
	//pretend the ant has collided with the flag number num(1-9)
	public void flagCollision(int num) {
		System.out.println("There has been a collision betweeen ant and flag!\n");
		IIterator itr = gameObjectCollection.getIterator();
		while(itr.hasNext())
		{
			GameObject tempObject = itr.getNext();
			if(tempObject instanceof Ant)
			{
				if(((Ant)tempObject).getLastFlagReached()==(num-1))
				{
					System.out.println("The Ant has reached the next flag!\n");
					((Ant)tempObject).newFlagReached();
					
					if(((Ant)tempObject).getLastFlagReached()==FlagCount)
					{
						System.out.println("Game over, you win! Total time: #" + timer);
						endGame();
					}
				}
				else
				{
					System.out.println("The flag has been passed or haven't been reached yet!\n");
				}
				break;
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}

	//press 'f'
	//pretend the ant has collided with a food station
	public void foodStationCollision() {
		System.out.println("There has been a collision between ant and food station!\n");
		IIterator itr = gameObjectCollection.getIterator();
		while(itr.hasNext())
		{
			GameObject tempObject = itr.getNext();
			if(tempObject instanceof Ant)
			{
				IIterator itr2 = gameObjectCollection.getIterator();
				while(itr2.hasNext())
				{
					GameObject tempObject2 = itr2.getNext();
					if(tempObject2 instanceof FoodStation)
					{
						int tempFood = ((FoodStation)tempObject2).getCapacity();
						if(tempFood>0)
						{
							//let the ant consume food
							((Ant)tempObject).addFoodLevel(tempFood);
							//empty out food station
							((FoodStation)tempObject2).emptycapacity();
							//fade color of food station
							((FoodStation)tempObject2).setColor(ColorUtil.rgb(125, 255, 125));
							//add new food station
							addFoodStation();
							break;
						}
					}
				}
				break;
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}

	//press 'g'
	//pretend spider has gotten to the same location and collided with ant
	public void spiderCollision() {
		System.out.println("There is a collision between ant and spider!\n");
		IIterator itr = gameObjectCollection.getIterator();
		while(itr.hasNext())
		{
			GameObject tempObject = itr.getNext();
			if(tempObject instanceof Ant)
			{
				((Ant)tempObject).lostHealth();
				((Ant)tempObject).checkHealthLevel();
				if(((Ant)tempObject).checkDie())
				{
					decLifeCount();
					break;
				}
				int temp = 10-((Ant)tempObject).getHealthLevel();
				int newcolor = ColorUtil.rgb(255, temp, temp);
				((Ant)tempObject).setColor(newcolor);
				break;
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}

	//press 't' to tick the clock
	//in charge of updating ant and spider's status
	public void clocktick() {
		System.out.println("The clock has ticked!\n");
		incTimer();
		IIterator itr = gameObjectCollection.getIterator();
		while(itr.hasNext())
		{
			GameObject tempObject = itr.getNext();
			if(tempObject instanceof Spider)
			{
				do {
					((Spider)tempObject).changeHeading();
					((Spider)tempObject).move();
				}while(((Spider)tempObject).checkBound());
			}
			if(tempObject instanceof Ant)
			{
				((Ant)tempObject).dropFoodLevel();
				((Ant)tempObject).checkFoodLevel();
				((Ant)tempObject).checkHealthLevel();
				if(!((Ant)tempObject).checkDie())
				{
					((Ant)tempObject).move();
				}
				else
				{
					decLifeCount();
				}
			}
			this.setChanged();
			this.notifyObservers(this);
		}
	}

	//press 'd' to display current game/Ant status value
	public void displayStatus() {
		System.out.println("Lives: " + getCountlife());
		System.out.println("Time#: " + gettimer());
		IIterator itr = gameObjectCollection.getIterator();
		while(itr.hasNext())
		{
			GameObject tempObject = itr.getNext();
			if(tempObject instanceof Ant)
			{
				System.out.println("Last Flag Number Reached: " + ((Ant)tempObject).getLastFlagReached());
				System.out.println("Food Level: " + ((Ant)tempObject).getFoodLevel());
				System.out.println("Health Level: " + ((Ant)tempObject).getHealthLevel() + "\n");
				break;
			}
		}
	}

	//press 'm' to print a map
	public void printMap() {
		IIterator itr = gameObjectCollection.getIterator();
		while(itr.hasNext())
		{
			GameObject tempObject = itr.getNext();
			if(tempObject instanceof Flag)
			{
				System.out.println(((Flag)tempObject).toString());
			}
			if(tempObject instanceof Ant)
			{
				System.out.println(((Ant)tempObject).toString());
			}
			if(tempObject instanceof Spider)
			{
				System.out.println(((Spider)tempObject).toString());
			}
			if(tempObject instanceof FoodStation)
			{
				System.out.println(((FoodStation)tempObject).toString());
			}
		}
		System.out.println("\n");
	}
	
	public void GameExit() {
		Label myLabel = new Label("Are you sure you want to exit(y/n)?: ");
		Game game = new Game();
		game.addComponent(myLabel);
		final TextField myTextField = new TextField();
		game.addComponent(myTextField);
		game.show();
		
		myTextField.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent evt) {
				
			String sCommand=myTextField.getText().toString();
			myTextField.clear();
			if(sCommand.length() != 0)
			{
				switch (sCommand.charAt(0)) {
				case 'y':
					System.exit(0);
					break;
				case 'n':
					System.out.println("Continue Game!");
					break;
				default :
					System.out.println("Error! Invalid input. Try again");
					break;
				}
			}
			}
		});
		
	}
	
	//get life count
	public int getCountlife() 
	{
		return Countlife;
	}
	
	//get timer
	public int gettimer()
	{
		return timer;
	}
	
	//set life count
	public void setCountlife(int newcountlife) 
	{
		Countlife = newcountlife;
		this.setChanged();
		this.notifyObservers(this);
	}
	
	//set timer
	public void settimer(int newtimer)
	{
		timer = newtimer;
		this.setChanged();
		this.notifyObservers(this);
	}

	//decrement life count
	public void decLifeCount()
	{
		Countlife--;
		System.out.println("You have lost 1 life!");
		if(getCountlife()==0)
		{
			System.out.println("Game over, you failed!");
			endGame();
		}
		init();
	}
	
	//increment timer
	public void incTimer()
	{
		timer++;
		this.setChanged();
		this.notifyObservers(this);
	}
	
	//end game
	public void endGame()
	{
		gameOver = true;
		Display.getInstance().exitApplication();
	}
	
	public GameObjectCollection getCollection()
	{
		return gameObjectCollection;
	}
	
	//get sound status
	public boolean getSoundOn()
	{
		return soundOn;
	}
	
	//set sound status
	public void setSoundOn(boolean s)
	{
		this.soundOn = s;
		this.setChanged();
		this.notifyObservers(this);
	}
	
	//get flag count
	public int getFlagCount()
	{
		return FlagCount;
	}
}
