package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command{
	public AboutCommand()
	{
		super("About");		//display "About"
	}
	
	public void actionPerfomed(ActionEvent e)
	{
		String info = "Name: Hetvi Patel\n" + "Course: CSC133-02\n" + "Version: 1.0";
		Dialog.show("About", info, "OK", null);
	}
}
