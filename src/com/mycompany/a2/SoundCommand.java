package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.CheckBox;

public class SoundCommand extends Command{

	private GameWorld gw;

	public SoundCommand(GameWorld gw) {
		super("Sound ON/OFF");		//dislpay "Sound ON/OFF
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(((CheckBox)e.getComponent()).isSelected())
		{
			gw.setSoundOn(true);
		}
		else
		{
			gw.setSoundOn(false);
		}
	}

}
