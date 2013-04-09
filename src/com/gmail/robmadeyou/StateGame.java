package com.gmail.robmadeyou;

import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

public class StateGame {
	
	static GuiText dotsSecured;
	static GuiText dotsLeft;
	static GuiText hForHelp;
	
	public static void onSetup(){
		Gui.addNewTextBox(new Gui.text("Dots secured  " + Level.dotsSecured, Display.getWidth() / 2 - 106,  30, 2, "game", "dotsSecured", Color.white));
		dotsSecured = Gui.textArray[Gui.getTextByName("dotsSecured")];
		dotsSecured.setBackgroundVisibility(true);
		
		Gui.addNewTextBox(new Gui.text("Dots left  " + (Level.dotsOnScreen - Level.dotsSecured), Display.getWidth() / 2 - 106, 30 + 20, 2, "game", "dotsLeft", Color.orange));
		dotsLeft = Gui.textArray[Gui.getTextByName("dotsLeft")];
		dotsLeft.setBackgroundVisibility(true);
		
		Gui.addNewTextBox(new Gui.text("Press H for help any time", Display.getWidth() / 2 - 106, 10, 2, "game", "hForHelp", Color.orange));
		hForHelp = Gui.textArray[Gui.getTextByName("hForHelp")];
		hForHelp.setBackgroundVisibility(true);
	}
	//Level 3 counters
	static int swapLocationCurrent3 = 0;
	static int swapLocationMax3 = 100;
	static boolean isSwapLocation3 = false;
	//Level 4 counters
	static int swapLocationCurrent4 = 0;
	static int swapLocationMax4 = 140;
	static boolean isSwapLocation4 = false;
	//Level 5 counters
	static int swapLocationCurrent5 = 0;
	static int swapLocationMax5 = 120;
	static boolean isSwapLocation5 = false;
	
	static int y21 = 0;
	static int y22 = Display.getHeight() - 80;
	
	static int x41 = 0;
	static int x42 = Display.getWidth() - 80;
	
	public static void onUpdate(int delta){
		dotsSecured.setText("Dots secured  " + Level.dotsSecured);
		dotsLeft.setText("Dots left  " + (Level.dotsOnScreen - Level.dotsSecured));
		GuiBackground.changeColor(0, 0, 0);
		if(WallList.wallOp[0] != null && WallList.wallOp[0].getR() != 0.7F){
			WallList.changeActiveColor(0.7F, 0.7F, 0.7F);
		}
		Player.onUpdate();
		WallList.onUpdate();
		SandList.onUpdate(delta);
		Level.onLevelUpdate();
		while(Keyboard.next()){
			if(Keyboard.getEventKeyState()){
				if(Keyboard.isKeyDown(Keyboard.KEY_H)){
					StateManager.changeState("inhelp", true);
				}
			}
		}
		if(Level.currentLevel == 2){
			swapLocationCurrent3++;
			if(isSwapLocation3){
				if(WallList.wall[2][1].getY() == y21){
					WallList.wall[2][1].setY(y22);
				}else{
					WallList.wall[2][1].setY(y21);
				}
			}
			
			if(isSwapLocation3){
				isSwapLocation3 = false;
			}
			if(swapLocationCurrent3 >= swapLocationMax3){
				isSwapLocation3 = true;
				swapLocationCurrent3 = 0;
			}
		}else if(Level.currentLevel == 3){
			swapLocationCurrent4++;
			if(isSwapLocation4){
				Random random = new Random();
				//The 80 + here makes sure the finish box isn't inside the start box so
				//the player can't just be lucky and win
				//The - 160 there is to make sure the random generator is inside the actual screen
				//otherwise the finish box would be generated somewhere outside of the screen :(
				int ranX = 80 +random.nextInt(Display.getWidth() - 160);
				int ranY = 80 + random.nextInt(Display.getHeight() - 160);
				WallList.wall[3][1].setPosition(ranX, ranY);
			}
			if(isSwapLocation4){
				isSwapLocation4 = false;
			}
			if(swapLocationCurrent4 >= swapLocationMax4){
				isSwapLocation4 = true;
				swapLocationCurrent4 = 0;
			}
		}else if(Level.currentLevel == 4){
			swapLocationCurrent5++;
			if(isSwapLocation5){
				if(WallList.wall[4][1].getX() == x41){
					WallList.wall[4][1].setX(x42);
				}else{
					WallList.wall[4][1].setX(x41);
				}
			}
			if(isSwapLocation5){
				isSwapLocation5 = false;
			}
			if(swapLocationCurrent5 >= swapLocationMax5){
				isSwapLocation5 = true;
				swapLocationCurrent5 = 0;
			}
		}
	}
}
