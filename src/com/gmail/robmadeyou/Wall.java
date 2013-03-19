package com.gmail.robmadeyou;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;
import static org.lwjgl.opengl.GL11.GL_LINES;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import com.gmail.robmadeyou.WallList.WallsOp;

public class Wall {
	
	
	private int x, y, w, h;
	private String color;
	public Wall(int x, int y,int w,int h, String color){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.color = color;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getWidth(){
		return w;
	}
	public int getHeight(){
		return h;
	}
	public void onUpdate(){
		draw();
	}
	public void setUpSmallBoxes(){
		glColor4f(1,1,1, 1F);
		if(!WallList.haveWallsOpBeenSetUp){
			int totalWidth = w;
			int totalHeight = h;
			int widthLeft = totalWidth;
			int widthUsed = x + 0;
			int boxesToFitHoriz = (int) ((totalWidth / 20) + 0.5);
			int boxesToFitVert = (int) ((totalHeight / 20) + 0.5);
			System.out.println(boxesToFitHoriz + "   " + boxesToFitVert);
			int heightUsed = y + 0;
			for(int i = 0; i < boxesToFitVert; i++){//omfg so fucking tired o_O
				for(int q = 0; q < boxesToFitHoriz; q++){//It is so tired and I can't function properly :/
					int eX1 = WallList.wall[Level.currentLevel][0].getX() -1;
					int eY1 = WallList.wall[Level.currentLevel][0].getY() -1;
					int eW1 = WallList.wall[Level.currentLevel][0].getWidth();
					int eH1 = WallList.wall[Level.currentLevel][0].getHeight();
					
					int eX2 = WallList.wall[Level.currentLevel][1].getX() -1;
					int eY2 = WallList.wall[Level.currentLevel][1].getY() -1;
					int eW2 = WallList.wall[Level.currentLevel][1].getWidth();
					int eH2 = WallList.wall[Level.currentLevel][1].getHeight();
					//Checks if this box is within the bounds of the START box
					boolean one = (widthUsed > eX1 && widthUsed < eX1 + eW1 && heightUsed > 
									eY1 && heightUsed < eY1 + eH1);
					//Checks if this box is within the bounds of the END box
					boolean two = (widthUsed > eX2 && widthUsed < eX2 + eW2 && heightUsed > 
							eY2 && heightUsed < eY2 + eH2);
					if(!one && !two){
						WallList.addNewOpBoxes(new WallList.WallsOp(widthUsed, heightUsed, 20, 20, 0.5F));//Please call me a really silly word :)
					}
					widthUsed += 20;
					
				}
				widthUsed = x + 0;
				heightUsed += 20;
			}
			
		}
	}
	public static Texture tex;
	public void draw(){
		
		float r = 1,g = 1,b = 1;
		if(color.equals("green")){
			r = 0;
			g = 1;
			b = 0;
		}else if(color.equals("blue")){
			r = 0;
			g = 0;
			b = 1;
		}else if(color.equals("red")){
			r = 1;
			g = 0;
			b = 0;
		}else if(color.equals("cyan")){
			r = 0;
			g = 1;
			b = 1;
		}
		//TODO: Add random quads to the square, haven't decided what amount yet but they
		//should be randomly generated and act as texture
		//to distinguish them I'll probably add opacity to each and every quad then
		//so the color is kept the same, just the shade is turned down (as there will be a dark background)
		Textures.none.bind();
		glBegin(GL_QUADS);
			glColor4f(r,g,b,1F);
			glVertex2i(x, y);
			glVertex2i(x + w, y);
			glVertex2i(x + w, y + h);
			glVertex2i(x, y + h);
			glColor4f(1,1,1, 1F);
		glEnd();
		/*
		 * And here I'm going to complete the todo :D
		 * 
		 */
		//Moved it to a new method! Save some power! :D
		/* Going to draw a box by just using lines around the wall box, this should give it a FEZ like texture :3
		 * 1.....2
		 * .     .
		 * .     .
		 * 4.....3
		 */
		glColor4f(0,0,0,0.3F);
		//1 to 2
		glBegin(GL_LINES);
			glVertex2i(x, y);
			glVertex2i(x + w, y);
		glEnd();
		
		//2 to 3
		glBegin(GL_LINES);
			glVertex2i(x + w, y);
			glVertex2i(x + w, y + h);
		glEnd();
		
		//3 to 4
		glBegin(GL_LINES);
			glVertex2i(x + w, y + h);
			glVertex2i(x, y + h);
		glEnd();
		
		//4 to 1
		glBegin(GL_LINES);
			glVertex2i(x, y + h);
			glVertex2i(x, y);
		glEnd();
		glColor4f(1,1,1,1F);
		//Done :3
	}
}
