package com.gmail.robmadeyou;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class Player {
	
	public static int x = Mouse.getX();
	public static int y = Display.getHeight() - Mouse.getY();
	
	public static void onUpdate(){
		if(Mouse.isButtonDown(0)){
			SandList.isPlayerMouseDown = true;
		}else{
			SandList.isPlayerMouseDown = false;
		}
	}
}
