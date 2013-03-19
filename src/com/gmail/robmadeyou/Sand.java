package com.gmail.robmadeyou;

import java.util.Random;
import static org.lwjgl.opengl.GL11.GL_POINTS;

import java.text.DecimalFormat;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2d;
import static org.lwjgl.opengl.GL11.glColor3f;


public class Sand {
	
	private double x, y;
	private double dX, dY;
	private boolean isMovingToMouse;
	private String color;
	private int number;
	private float speed;
	private boolean isInFinish;
	private Texture tex;
	public Sand(int x, int y, String color){
		this.x = x;
		this.y = y;
		this.color = color;
		this.dX = 0;
		this.dY = 0;
		Random ran = new Random();
		this.speed = ran.nextFloat() / 2;
		this.isMovingToMouse = false;
		this.isInFinish = false;
	}
	public void setToFinish(boolean set){
		this.isInFinish = set;
	}
	public boolean isInFinish(){
		return isInFinish;
	}
	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return (int)x;
	}
	public int getY(){
		return (int)y;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public void addX(int x){
		this.x = this.x + x;
	}
	public void addY(int y){
		this.y = this.y + y;
	}
	public void setVel(double dX, double dY){
		this.dX = dX;
		this.dY = dY;
	}
	public void setMovingToMouse(boolean bool){
		this.isMovingToMouse = bool;
	}
	public int getNumber(){
		return number;
	}
	public void setNumber(int number){
		this.number = number;
	}
	public void draw(){
		
		float r = 1,g = 1,b = 1;
		if(color.equals("green")){
			r = 0;
			g = 0;
			b = 1;
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
		
		Textures.none.bind();
		glBegin(GL_POINTS);
			glColor3f(r,g,b);
			glVertex2d(x, y);
			glVertex2d(x + 1, y);
			glVertex2d(x + 1, y + 1);
			glVertex2d(x , y + 1);
		glEnd();
			
	}
	public void onUpdate(){
		draw();
		if(!isInFinish){
			if(isMovingToMouse){
				//Random double that will fluctuate very slightly to
				//make each grain of sand move at a different speed
				
				
				double s = 2.8;
			
				double toX = 0;
				double toY = 0;
			
				toX = x -(Mouse.getX());
				toY = y - (Display.getHeight() -Mouse.getY());
			
				double tan = (float) Math.atan2(toX,toY);
			
				dX = s*Math.sin(tan);
				dY = s*Math.cos(tan);
				dX += speed;
				dY += speed;
				x -= dX;
				y -= dY;
			}else{
				x -= dX;
				y -= dY;
				//What I do here is slow the pieces of sand to add a 
				//gradual slowing down effect, like water would slow down
				//I like it. Simply multiplying dX or dY by 0.9
				//So this would decrease the speed at the same fraction
				//every time
				dX *= 0.9;
				dY *= 0.9;
		
			}
		}else{
			x -= dX;
			y -= dY;
			
			dX *= 0.7;
			dY *= 0.7;
		}
	}
}
