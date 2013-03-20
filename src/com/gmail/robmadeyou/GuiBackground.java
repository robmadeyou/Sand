package com.gmail.robmadeyou;

import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;
import static org.lwjgl.opengl.GL11.glColor3f;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.GL_QUADS;


public class GuiBackground {
	
	
	public static class interactiveSquares extends WallOp{
		public interactiveSquares(int x, int y, int w, int h, float op) {
			super(x, y, w, h, op);
		}
		
	}
	
	static Texture texture = Textures.none;
	static float speed = 0.05F;
	static float r = 0.3F,g = 0.3F,b = 0.3F, toR = 0.3F, toG = 0.3F, toB = 0.3F;
	
	static boolean isBackgroundInteractiveSquares = false;//World largest boolean
	
	static WallOp[][] squares = new WallOp[51][25];
	
	public static void init(){
		for(int i = 0; i < 51; i++){
			for(int r = 0; r < 25; r++){
				squares[i][r] = new interactiveSquares(i * 20, r * 20, 20, 20, 1F);
			}
		}
	}
	public static void draw(){
		if(!isBackgroundInteractiveSquares){
			texture.bind();
			glBegin(GL_QUADS);
				glTexCoord2f(0F, 1F);
				glVertex2i(0,0);
				glTexCoord2f(1F, 1F);
				glVertex2i(Display.getWidth(),0);
				glTexCoord2f(1F,0F);
				glVertex2i(Display.getWidth(), Display.getHeight());
				glTexCoord2f(0F,0F);
				glVertex2i(0, Display.getHeight());
			glEnd();
		}else{
			for(int i = 0; i < 51; i++){
				for(int r = 0; r < 25; r++){
					squares[i][r].onUpdate();
				}
			}
		}
	}
	public static void changeActiveColor(float r, float g, float b){
		for(int i = 0; i < 51; i++){
			for(int d = 0; d < 25; d++){
				squares[i][d].setChangeColors(r, g, b);
			}
		}
	}
	public static void changeColor(float r, float g, float b){
		if(!isBackgroundInteractiveSquares){
			toR = r;
			toG = g;
			toB = b;
		}else{
			for(int i = 0; i < 51; i++){
				for(int d = 0; d < 25; d++){
					squares[i][d].setOriginalColors(r, g, b);
				}
			}
		}
	}
	
	public static void onUpdate(int delta){
		
		for(int i = 0; i < 51; i++){
			for(int r = 0; r <= 24; r++){
				if(i + 1 < 51 && r + 1 < 25){
					if(squares[i + 1][r].isActive() && !squares[i][r].wasActive()){
						squares[i][r].setActive(true);
					}
					if(squares[i][r + 1].isActive() && !squares[i][r].wasActive()){
						squares[i][r].setActive(true);
					}
				}
			}
		}
		for(int i = 51; i > 0; i--){
			for(int r = 24; r >= 0; r--){
				if(i >= 0 && i + 1 <= 51 && r > 0){
					if(squares[i - 1][r].isActive() && !squares[i][r].wasActive()){
						squares[i][r].setActive(true);
					}
					if(squares[i][r - 1].isActive() && !squares[i][r].wasActive()){
						squares[i][r].setActive(true);
					}
				}
			}
		}
		
		
		if(r != toR || g != toG || b != toB){
			if(r > toR){
				r -= speed;
			}
			if(r < toR){
				r += speed;
			}
			if(g > toG){
				g -= speed;
			}
			if(g < toG){
				g += speed;
			}
			if(b > toB){
				b -= speed;
			}
			if(b < toB){
				b += speed;
			}
		}
		glColor3f(r,g,b);
		draw();
		glColor3f(1,1,1);
	}
}
