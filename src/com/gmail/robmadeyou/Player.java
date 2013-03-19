package com.gmail.robmadeyou;

import static org.lwjgl.opengl.GL11.GL_QUADS;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glVertex2i;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glPopMatrix;

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
