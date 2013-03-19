package com.gmail.robmadeyou;

public class StateGame {
	
	public static void onSetup(){
		
	}
	
	public static void onUpdate(int delta){
		GuiBackground.changeColor(0, 0, 0);
		Player.onUpdate();
		WallList.onUpdate();
		SandList.onUpdate(delta);
		Level.onLevelUpdate();
	}
}
