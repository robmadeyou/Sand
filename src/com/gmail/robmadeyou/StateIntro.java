package com.gmail.robmadeyou;

import org.lwjgl.input.Mouse;

public class StateIntro {
	
	public static void onSetUp(){
		GuiBackground.texture = Textures.intro;
		GuiBackground.changeColor(1, 1, 1);
	}
	static int timerMax = 100;
	static int timerCurrent = 0;
	public static void onUpdate(){
		timerCurrent++;
		
		if(timerMax <= timerCurrent){
			StateManager.changeState("menu", true);
		}
		if(Mouse.isButtonDown(0)){
			StateManager.changeState("menu", true);
		}
	}
}
