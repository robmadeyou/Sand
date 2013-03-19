package com.gmail.robmadeyou;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class WallOp {
	
	
	private int x, y, w, h;
	private float op;
	private boolean isDimming = false;
	private final float speed;
	private float r,g,b;
	private float oR, oG, oB;
	public WallOp(int x, int y,int w,int h, float op){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.op = op;
		this.r = 0;
		this.g = 1;
		this.b = 1;
		this.oR = r;
		this.oG = g;
		this.oB = b;
		Random ran = new Random();
		int ranOp = ran.nextInt(30);
		if(ranOp / 25000F < 0.001){
			this.speed = (ranOp / 25000F) + 0.001F; 
		}else if(ranOp / 25000F > 0.07F){
			this.speed = (ranOp / 25000F) - 0.03F;
		}else{
			this.speed = ranOp / 25000F;
		}
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
		animate();
		if(r != oR || g != oG || b != oB){
			resetColor();
		}
		int mX = Mouse.getX();
		int mY = Display.getHeight() - Mouse.getY();
		if(mX >= x && mX <= x + w && mY >= y && mY <= y + h){
			op = 1F;
			r = 0F;
			g = 1F;
			b = 0F;
		}
	}
	public void resetColor(){
		//Reseting to original color after being touched
			//Red
			if(r > oR && r <= 1F && r >= 0F){
				r -= speed;
			}else if(r > oR && r <= 1F && r >= 0F){
				r += speed;
			}
			//Green
			if(g > oG && g <= 1F && g >= 0F){
				g -= speed;
			}else if(g < oG && g <= 1F && g >= 0F){
				g += speed;
			}
			//Blue
			if(b > oB && b <= 1F && b >= 0F){
				b -= speed;
			}else if(b < oB && b <= 1F && b >= 0F){
				b += speed;
				
			}
	}
	public void dim(){
		op -= speed;
	}
	public void brigten(){
		op += speed;
		
	}
	public void animate(){
		if(op >= 1F){
			isDimming = true;
		}
		if(op <= 0F){
			isDimming = false;
		}
		if(isDimming){
			dim();
		}
		if(!isDimming){
			brigten();
		}
	}
	public void setOp(float op){
		this.op = op;
	}
	
	public static Texture tex;
	public static boolean texLoad =false;
	public void draw(){
		Textures.textBackground.bind();
		glBegin(GL_QUADS);
			glColor4f(r,g,b,op);
			glTexCoord2f(0, 0);
			glVertex2f(x , y);
			glTexCoord2f(1, 0);
			glVertex2f(x + w, y);
			glTexCoord2f(1, 1);
			glVertex2f(x + w, y + h);
			glTexCoord2f(0, 1);
			glVertex2f(x , y + h);
			glColor4f(0,0,0, op);
		glEnd();
	}
}
