package com.gmail.robmadeyou;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

public class WallOp {
	
	
	private int x, y, w, h;
	private float op;
	private boolean isDimming = false;
	private final float speed;
	private float r,g,b;
	private float oR, oG, oB;
	private float sR, sG, sB;
	private boolean isActive = false;
	private boolean wasActive = false;
	private int activeTimer = 10;
	private int activeTimerCurrent = 0;
	private int wasActiveTimerCurrent = 0;
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
		if(ranOp / 25000F < 0.01){
			this.speed = (ranOp / 25000F) + 0.001F; 
		}else if(ranOp / 25000F > 0.1F){
			this.speed = (ranOp / 25000F) - 0.03F;
		}else{
			this.speed = ranOp / 25000F;
		}
		this.op = ran.nextFloat();
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
	public boolean isActive(){
		return isActive;
	}
	public boolean wasActive(){
		return wasActive;
	}
	public void setActive(boolean active){
		this.isActive = active;
	}
	public float getR(){
		return r;
	}
	public float getB(){
		return b;
	}
	public float getG(){
		return g;
	}
	public void setOriginalColors(float r, float g, float b){
		this.oR = r;
		this.oG = g;
		this.oB = b;
	}
	public void setChangeColors(float r, float g, float b){
		this.sR = r;
		this.sG = g;
		this.sB = b;
	}
	public void onUpdate(){
		
		if(r != oR || g != oG || b != oB){
			resetColor();
		}
		int mX = Mouse.getX();
		int mY = Display.getHeight() - Mouse.getY();
		if(wasActive){
			wasActiveTimerCurrent++;
			if(wasActiveTimerCurrent >= activeTimer){
				wasActiveTimerCurrent = 0;
				wasActive = false;
			}
		}
		if(isActive == true){
			Random ran = new Random();
			op = ran.nextFloat();
			if(op <= 0.4F){
				op = 0.4F;
			}
			activeTimerCurrent++;
			if(activeTimerCurrent >= activeTimer){
				isActive = false;
				activeTimerCurrent = 0;
				wasActive = true;
			}
		}
		if(mX >= x && mX <= x + w && mY >= y && mY <= y + h){
			op = 1F;
			r = sR;
			g = sG;
			b = sB;
			if(Mouse.isButtonDown(0)){
				isActive = true;
			}else{
				isActive = false;
			}
		}
		draw();
		animate();
	}
	public void resetColor(){
		//Reseting to original color after being touched
			//Red
		if(r > oR){
			r -= speed * 2;
		}
		if(r < oR){
			r += speed * 2;
		}
		if(g > oG){
			g -= speed * 2;
		}
		if(g < oG){
			g += speed * 2;
		}
		if(b > oB){
			b -= speed * 2;
		}
		if(b < oB){
			b += speed * 2;
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
		if(op <= 0.4F){
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
