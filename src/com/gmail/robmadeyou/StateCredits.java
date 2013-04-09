package com.gmail.robmadeyou;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

public class StateCredits {
	
	static GuiButton backButton;
	static GuiText congratsText;
	static boolean hasWonTheGame = false;
	
	public static void onSetup(){
		Gui.addNewButton(new Gui.button(Display.getWidth() / 2 - 50, Display.getHeight() - 60, 100, 60, "credits", "backButtonC", Textures.player1, Textures.player2, Color.white));
		backButton = Gui.buttonArray[Gui.getButtonByName("backButtonC")];
		
		Gui.addNewTextBox(new Gui.text("This game whole was made by Robert Ellis \n all of the code and resources are available in the folder labeled source \nThankyou for playing the game", 40, 40, 2, "credits", "creditsText", Color.white));
	}
	
	public static void onUpdate(){
		if(hasWonTheGame){
			 Gui.addNewTextBox(new Gui.text("Thank you for playing,  Well done for winning" , Display.getWidth() / 2 - 200, Display.getHeight() / 2, 4, "credits", "congrats", Color.cyan));
			congratsText = Gui.textArray[Gui.getTextByName("congrats")];
			congratsText.setBackgroundVisibility(true);
			hasWonTheGame = false;
		}
		if(backButton.isPressed()){
			StateManager.changeState("menu", true);
		}
	}
}
