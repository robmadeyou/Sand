package com.gmail.robmadeyou;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

public class StateLevelSelect {
	
	static ArrayList<GuiButton> buttons = new ArrayList<GuiButton>();
	static GuiButton backButton;
	
	public static void onSetup(){
		Gui.addNewButton(new Gui.button(Display.getWidth() / 2 - 50, Display.getHeight() - 60, 100, 60, "select", "backButton", Textures.player1, Textures.player2, Color.white));
		backButton = Gui.buttonArray[Gui.getButtonByName("backButton")];
		
		for(int i = 0; i < 7; i++){
			Gui.addNewButton(new Gui.button(((Display.getWidth() - (7 * 100)) / 2) + (100 * i), (Display.getHeight() / 2) - 50, 90, 100, "select", "level" + i, Textures.player1, Textures.player2, Color.white));
			
		}
		for(int i = 0; i < 7; i++){
			buttons.add(Gui.buttonArray[Gui.getButtonByName("level" + i)]);
		}
	}
	
	public static void onUpdate(){
		for(int i = 0; i < buttons.size(); i++){
			if(buttons.get(i).isPressed()){
				if(i <= Level.levelUnlocked){
					Level.currentLevel = i;
					Level.setUpLevel(i);
					StateManager.changeState("game", true);
				}else{
					System.out.println("aaaaaaaa");
					Main.message = "Level not unlocked yet. Level: " + (i + 1);
				}
			}
			if(i <= Level.levelUnlocked){
				buttons.get(i).setColor(Color.white);
			}else{
				buttons.get(i).setColor(Color.gray);
			}
		}
		if(backButton.isPressed()){
			StateManager.changeState("menu", true);
		}
	}
}
