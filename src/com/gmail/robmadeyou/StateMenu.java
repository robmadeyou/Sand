package com.gmail.robmadeyou;

import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.openal.SoundStore;

public class StateMenu {
	
	static GuiButton startButton ;
	static GuiButton creditsButton;
	static GuiButton helpButton;
	static GuiButton exitButton;
	
	static GuiButton toggleSoundButton;
	
	public static void onSetup(){
		GuiBackground.changeColor(0.3F, 0.3F, 0.3F);
		
		Gui.addNewButton(new Gui.button(0, 200, 256, 64, "menu", "startButton", Textures.buttonStart, Textures.buttonStartU, Color.white));
		startButton  = Gui.buttonArray[Gui.getButtonByName("startButton")];
		
		Gui.addNewButton(new Gui.button(0, 350, 256, 64, "menu", "creditsButton", Textures.buttonCredits, Textures.buttonCreditsU, Color.white));
		creditsButton = Gui.buttonArray[Gui.getButtonByName("creditsButton")];
		
		Gui.addNewButton(new Gui.button(Display.getWidth() - 256, 200, 256, 64, "menu", "helpButton", Textures.buttonHelp, Textures.buttonHelpU, Color.white));
		helpButton = Gui.buttonArray[Gui.getButtonByName("helpButton")];
		
		Gui.addNewButton(new Gui.button(Display.getWidth() - 256, 350, 256, 64, "menu", "exitButton", Textures.buttonExit, Textures.buttonExitU, Color.white));
		exitButton = Gui.buttonArray[Gui.getButtonByName("exitButton")];
		
		
		Gui.addNewButton(new Gui.button(Display.getWidth() - 64, 0, 64, 32, "menu", "toggleSound", Textures.buttonSoundOn, Textures.buttonSoundU, Color.white));
		toggleSoundButton = Gui.buttonArray[Gui.getButtonByName("toggleSound")];
	}
	
	
	public static void onUpdate(){
		if(startButton.isPressed()){
			StateManager.changeState("select", true);
		}
		
		if(exitButton.isPressed()){
			Display.destroy();
			AL.destroy();
			System.exit(1);
		}
		if(startButton.isMouseOver()){
			GuiBackground.changeColor(0.4F, 0.0F, 0.0F);
		}
		if(creditsButton.isMouseOver()){
			GuiBackground.changeColor(0, 0.4F, 0);
		}
		if(helpButton.isMouseOver()){
			GuiBackground.changeColor(0, 0, 0.4F);
		}
		if(exitButton.isMouseOver()){
			GuiBackground.changeColor(0.3F, 0.3F, 0.3F);
		}
		
		
		if(toggleSoundButton.isPressed()){
			Sound.toggleSounds();
			if(Sound.isSoundMuted){
				toggleSoundButton.setNormalTexture(Textures.buttonSoundOff);
			}else{
				toggleSoundButton.setNormalTexture(Textures.buttonSoundOn);
			}
		}
	}
}
