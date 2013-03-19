package com.gmail.robmadeyou;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Textures {
	static Texture none;
	
	static Texture player1;
	static Texture player2;
	
	static Texture shadow;
	
	static Texture textBackground;
	
	static Texture mainMenu;
	
	static Texture buttonStart;
	static Texture buttonCredits;
	static Texture buttonHelp;
	static Texture buttonExit;
	
	static Texture buttonSoundOn;
	static Texture buttonSoundOff;
	
	static Texture buttonStartU;
	static Texture buttonCreditsU;
	static Texture buttonHelpU;
	static Texture buttonExitU;
	static Texture buttonSoundU;
	
	static Texture level;
	static Texture levelLocked;
	
	static Texture intro;
	
	
	
	public static void loadTextures(){
		
		try {
			none = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/none.png"), true);
		
			player1 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/player/player1.png"), true);
			player2 = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/player/player2.png"), true);
		
			shadow = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/shadow.png"), true);
			
			textBackground = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/textBackground.png"), true);
		
			mainMenu = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/gui/background/mainMenu.png"), true);
			
			buttonStart = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/gui/buttons/startButton.png"), true);
			buttonCredits = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/gui/buttons/creditsButton.png"), true);
			buttonHelp = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/gui/buttons/helpButton.png"), true);
			buttonExit = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/gui/buttons/exitButton.png"), true);
			buttonSoundOn = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/gui/buttons/soundUnMuted.png"), true);
			buttonSoundOff = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/gui/buttons/soundMuted.png"), true);
			
			buttonStartU = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/gui/buttons/startButtonU.png"), true);
			buttonCreditsU = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/gui/buttons/creditsButtonU.png"), true);
			buttonHelpU = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/gui/buttons/helpButtonU.png"), true);
			buttonExitU = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/gui/buttons/exitButtonU.png"), true);
			buttonSoundU = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/gui/buttons/soundHover.png"), true);
			
			
			
			intro = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/logo.png"), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
