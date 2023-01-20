package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

public class FlagCollisionCommand extends Command{

	private GameWorld gw;

	public FlagCollisionCommand(GameWorld gw) {
		super("Collide with Flag");		//Display "Collide with Flag"
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		int flagCount = gw.getFlagCount();
		
		Command okCommand = new Command("OK");
		TextField myTF = new TextField();
		Dialog.show("Enter flag number", myTF, okCommand);
		String text = myTF.getText().toString();
		try {
			int temp = Integer.parseInt(text);
			if(temp>0 && temp<=flagCount)
			{
				gw.flagCollision(temp);
			}
			else
			{
				String info = "Please enter a number between 1-" + flagCount;
				Dialog.show("Error", info, "OK", null);
			}
		}catch(NumberFormatException e1) {
			String info = "Please enter a number!";
			Dialog.show("Error",  info, "OK", null);
		}
	}

}
