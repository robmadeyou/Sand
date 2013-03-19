package com.gmail.robmadeyou;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2i;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class GuiText {
	private double x, y, size;
	private String text, state, name;
	private boolean hasBackground = false;
	private Texture backTex;
	private Color textColor;
	public GuiText(String text, double x, double y, double size, String state, String name, Color textColor){
		this.text = text;
		this.x = x;
		this.y = y;
		this.size = size;
		this.state = state;
		this.name = name;
		this.textColor = textColor;
		this.backTex = Textures.textBackground;
	}
	public String getName(){
		return name;
	}
	public String getState(){
		return state;
	}
	public void setBackgroundVisibility(boolean b){
		this.hasBackground = b;
	}
	public boolean hasBackground(){
		return hasBackground;
	}
	public void setBackTexture(Texture tex){
		this.backTex = tex;
	}
	
	private void drawBackground(){
		int width = Fonts.getWidth(text, size);
		int height = Fonts.getHeight(text, size);
		
		backTex.bind();
		glBegin(GL_QUADS);//ball.getY() thingy
			glTexCoord2d(0, 1);
			glVertex2i((int) x - (2 * (int)size),(int) y - (2 * (int)size)); //1
			glTexCoord2d(1, 1);
			glVertex2i((int) x - (2 * (int)size) + width + 10,(int) y - (2 * (int)size)); //2
			glTexCoord2d(1, 0);
			glVertex2i((int) x - (2 * (int)size)+ width + 10, (int) y - (2 * (int)size) + height + 10); //3
			glTexCoord2d(0, 0);
			glVertex2i((int) x - (2 * (int)size), (int) y - (2 * (int)size) + height + 10); //4
		glEnd();
	}
	public void drawBoxWithText(){
		if(hasBackground){
			drawBackground();
		}
		Fonts.drawString(text, (int) x + 10 * (int)size, (int) y, size, textColor);
	}
}
