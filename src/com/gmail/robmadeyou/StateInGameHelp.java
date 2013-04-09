package com.gmail.robmadeyou;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

public class StateInGameHelp {
	
	static GuiButton backButton;
	
	public static void onSetup(){
		Gui.addNewTextBox(new Gui.text("Press H to access help in game at any time", 60, 60, 1.5, "inhelp", "text1", Color.white));
		Gui.addNewTextBox(new Gui.text("Use the mouse to navigate the pieces of sand around the level", 60, 80, 1.5, "inhelp", "text1", Color.white));
		Gui.addNewTextBox(new Gui.text("Navigate all of the pieces of sand to the red box to secure them", 60, 100, 1.5, "inhelp", "text1", Color.white));
		Gui.addNewTextBox(new Gui.text("Secure as many pieces of sand as you can", 60, 120, 1.5, "inhelp", "text1", Color.white));
		Gui.addNewTextBox(new Gui.text("Each piece of sand grants you 1 score", 60, 140, 1.5, "inhelp", "text1", Color.white));
		Gui.addNewTextBox(new Gui.text("Avoid cyan blocks. They kill sand and remove sand", 60, 160, 1.5, "inhelp", "text1", Color.white));
	
		Gui.addNewButton(new Gui.button(Display.getWidth() / 2 - 50, Display.getHeight() - 60, 100, 60, "inhelp", "backButtoniH", Textures.player1, Textures.player2, Color.white));
		backButton = Gui.buttonArray[Gui.getButtonByName("backButtoniH")];
	}
	
	public static void onUpdate(){
		if(backButton.isPressed()){
			StateManager.changeState("game", true);
		}
	}
}
