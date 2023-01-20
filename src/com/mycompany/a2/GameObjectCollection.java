package com.mycompany.a2;

import java.util.ArrayList;

public class GameObjectCollection implements ICollection{
	private ArrayList<GameObject> gameObjects;
	
	//Constructor
	public GameObjectCollection() {
		gameObjects = new ArrayList<GameObject>();
	}
	
	//add new object to list
	@Override
	public void add(GameObject newGameObject)
	{
		gameObjects.add(newGameObject);
	}
	
	@Override
	public IIterator getIterator()
	{
		return new GameWorldIterator();
	}
	
	private class GameWorldIterator implements IIterator{
		private int currentIndex;
		
		public GameWorldIterator()
		{
			currentIndex = -1;
		}
		
		@Override
		public boolean hasNext()
		{
			if(gameObjects.size()<=0)
			{
				return false;
			}
			if(currentIndex==gameObjects.size()-1)
			{
				return false;
			}
			return true;
		}
		
		@Override
		public GameObject getNext()
		{
			currentIndex++;
			return gameObjects.get(currentIndex);
		}
		
		@Override
		public void remove(GameObject gameObject)
		{
			gameObjects.remove(gameObject);
		}
		
		@Override
		public GameObject getCurrent()
		{
			return gameObjects.get(currentIndex);
		}
	}
}
