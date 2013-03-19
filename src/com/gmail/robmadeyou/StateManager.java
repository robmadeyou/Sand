package com.gmail.robmadeyou;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2i;
import static org.lwjgl.opengl.GL11.glColor4f;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;



public class StateManager {
	
	/*
	 * Different states include:
	 * 	intro - Introduction screen
	 * 	menu - Main menu screen
	 * 	select - Level selection
	 * 	game - Game screen
	 * 	help - Help screen
	 */
	static String State = "intro";
	static String changeToState = "";
	
	static boolean hasFinishedChanging = false;
	static boolean isHalfWayChanging = false;
	static boolean hasStartedChanging = false;
	
	static boolean isIntroSetUp = false;
	static boolean isMenuSetUp = false;
	static boolean isLevelSelectSetUp = false;
	static boolean isGameSetUp = false;
	
	
	static float opacity = 0F;
	
	public static void onUpdate(int delta){
		GuiBackground.onUpdate(delta);
		if(State.equals("intro")){
			if(!isIntroSetUp){
				StateIntro.onSetUp();
				isIntroSetUp =  true;
			}	
			StateIntro.onUpdate();
		}else if(State.equals("menu")){
			if(!isMenuSetUp){
				StateMenu.onSetup();
				Sound.playMusic(Sound.introMusic);
				isMenuSetUp = true;
			}
			GuiBackground.texture = Textures.mainMenu;
			StateMenu.onUpdate();
			
		}else if(State.equals("select")){
			if(!isLevelSelectSetUp){
				StateLevelSelect.onSetup();
				isLevelSelectSetUp = true;
			}
			StateLevelSelect.onUpdate();
		}else if(State.equals("game")){
			if(!isGameSetUp){
				StateGame.onSetup();
				isGameSetUp = true;
			}
			StateGame.onUpdate(delta);
		
		}else{
			System.out.println("Invalid state!");
		}
		Gui.onUpdate();
		updateChange();
	}
	
	public static void updateChange(){
		if(hasStartedChanging){
			if(opacity >= 1){
				isHalfWayChanging = true;
				State = changeToState;
			}
			if(opacity <= 0 && isHalfWayChanging){
				hasStartedChanging = false;
				isHalfWayChanging = false;
				hasFinishedChanging = true;
			}
			if(hasStartedChanging && !isHalfWayChanging){
				opacity += 0.01F;
				Textures.none.bind();
				glColor4f(0,0,0, opacity);
			}
			if(isHalfWayChanging){
				opacity -= 0.01F;
				Textures.none.bind();
				glColor4f(0,0,0, opacity);
			}
			Textures.none.bind();
			glBegin(GL_QUADS);
				glTexCoord2d(0, 0);
				glVertex2i(0, 0);	//1
				glTexCoord2d(1, 0);
				glVertex2i(Display.getWidth(), 0);	//2
				glTexCoord2d(1, 1);
				glVertex2i(Display.getWidth(), Display.getHeight());	//3
				glTexCoord2d(0, 1);
				glVertex2i(0, Display.getHeight());	//4
			glEnd();
			glColor4f(0,0,0,1F);
		}
	}
	public static void changeState(String toState, boolean fadeThrough){
		if(!State.equals(toState)){
			if(fadeThrough){
				changeToState = toState;
				hasStartedChanging = true;
				hasFinishedChanging = false;
			}else{
				State = toState;
				hasStartedChanging = false;
				hasFinishedChanging = false;
			}
		}
	}	
}
