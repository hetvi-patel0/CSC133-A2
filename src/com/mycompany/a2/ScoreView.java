package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class ScoreView extends Container implements Observer {
	private GameWorld gw;
	private Label timeLabel;
	private Label lifeLeft;
	private Label lastFlagReached;
	private Label foodLevel;
	private Label healthLevel;
	private Label soundOn;
	
	public ScoreView() {
		this.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		setTimeLabel();
		setLifeLabel();
		setLastFlagReachedLabel();
		setFoodLevelLabel();
		setHealthLevelLabel();
		setSoundLabel();
		this.getAllStyles().setMarginLeft(280);
	}
	
	public void setTimeLabel()
	{
		Label clockLabel = new Label("Time:");
		clockLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		timeLabel = new Label("0");
		timeLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		timeLabel.getAllStyles().setPadding(RIGHT, 2);
		this.add(clockLabel);
		this.add(timeLabel);
	}
	
	public void setLifeLabel()
	{
		Label lifeLabel = new Label("Lives Left:");
		lifeLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		lifeLabel.getAllStyles().setPadding(1, 1, 1, 1);
		lifeLeft = new Label("0");
		lifeLeft.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		lifeLeft.getAllStyles().setPadding(RIGHT, 2);
		this.add(lifeLabel);
		this.add(lifeLeft);
	}
	
	public void setLastFlagReachedLabel()
	{
		Label lastFlagLabel = new Label("Last Flag Reached:");
		lastFlagLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		lastFlagLabel.getAllStyles().setPadding(1, 1, 1, 1);
		lastFlagReached = new Label("0");
		lastFlagReached.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		lastFlagReached.getAllStyles().setPadding(RIGHT, 2);
		this.add(lastFlagLabel);
		this.add(lastFlagReached);
	}
	
	public void setFoodLevelLabel()
	{
		Label foodLevelLabel = new Label("Food Level:");
		foodLevelLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		foodLevelLabel.getAllStyles().setPadding(1, 1, 1, 1);
		foodLevel = new Label("0");
		foodLevel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		foodLevel.getAllStyles().setPadding(RIGHT, 3);
		this.add(foodLevelLabel);
		this.add(foodLevel);
	}
	
	public void setHealthLevelLabel()
	{
		Label healthLevelLabel = new Label("Health Level:");
		healthLevelLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		healthLevelLabel.getAllStyles().setPadding(1, 1, 1, 1);
		healthLevel = new Label("0");
		healthLevel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		healthLevel.getAllStyles().setPadding(RIGHT, 6);
		this.add(healthLevelLabel);
		this.add(healthLevel);
	}
	
	public void setSoundLabel()
	{
		Label soundLabel = new Label("Sound:");
		soundLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		soundLabel.getAllStyles().setPadding(1, 1, 1, 1);
		soundOn = new Label("OFF");
		soundOn.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		soundOn.getAllStyles().setPadding(RIGHT, 5);
		this.add(soundLabel);
		this.add(soundOn);
	}
	
	@Override
	public void update(Observable observer, Object data)
	{
		gw = (GameWorld) data;
		this.lifeLeft.setText("" + gw.getCountlife());
		this.timeLabel.setText("" + gw.gettimer());
		GameObjectCollection goc = gw.getCollection();
		IIterator itr = goc.getIterator();
		while(itr.hasNext())
		{
			GameObject tempObject = itr.getNext();
			if(tempObject instanceof Ant)
			{
				this.lastFlagReached.setText("" + ((Ant)tempObject).getLastFlagReached());
				this.foodLevel.setText("" + ((Ant)tempObject).getFoodLevel());
				this.healthLevel.setText("" + ((Ant)tempObject).getHealthLevel());
				break;
			}
		}
		if(gw.getSoundOn())
		{
			this.soundOn.setText("ON");
		}
		else
		{
			this.soundOn.setText("OFF");
		}
		this.repaint();
	}
	
}
