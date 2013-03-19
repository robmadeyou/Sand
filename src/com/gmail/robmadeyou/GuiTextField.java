package com.gmail.robmadeyou;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2i;


import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_LINES;

public class GuiTextField {
	private String text, state, name;
	private int x,y,w,h;
	private Color color;
	private boolean isSelected;
	private int maxCharacters;
	public GuiTextField(int x, int y,int w, int h,String name, Color color, String state, int maxCharacters){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.state = state;
		this.name = name;
		this.color = color;
		this.text = "";
		this.maxCharacters = maxCharacters;
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
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public String getState(){
		return state;
	}
	public String getText(){
		return text;
	}
	public String getName(){
		return name;
	}
	public void onUpdate(){
		draw();
		int mX = Mouse.getX();
		int mY = Display.getHeight() - Mouse.getY();
		
		while(Mouse.next()){
			if(Mouse.getEventButtonState()){
				if(Mouse.isButtonDown(0)){
					if(mX >= x && mX <= x + w && mY >= y && mY <= y + h){
						isSelected = true;
					}else{
						isSelected = false;
					}
				}
			}
		}
		if(isSelected && text.length() < maxCharacters){
			while(Keyboard.next()){
				if(Keyboard.getEventKeyState()){
					if(Keyboard.isKeyDown(Keyboard.getEventKey())){
						for(int i = 0; i < Fonts.alphabet.length; i++){
							if(Keyboard.getEventCharacter() == Fonts.alphabet[i]){
								text += Keyboard.getEventCharacter();
							}
						}
						for(int i = 0; i < Fonts.symbols.length; i++){
							if(Keyboard.getEventCharacter() == Fonts.symbols[i]){
								text += Keyboard.getEventCharacter();
							}
						}
						if(Keyboard.getEventCharacter() == '.'){
							text += '.';
						}
					}
					if(Keyboard.isKeyDown(Keyboard.KEY_BACK)){
						if(text.length() >= 1){
							text = text.substring(0, text.length() - 1);
						}
					}
					if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
						text += " ";
					}
				}
			}
		}
	}
	public void draw(){
		Textures.none.bind();
		if(isSelected){
			Color.gray.bind();
			glBegin(GL_QUADS);
				glTexCoord2f(0,0);
				glVertex2i(x, y);
				glTexCoord2f(1,0);
				glVertex2i(x + w, y);
				glTexCoord2f(1,1);
				glVertex2i(x + w, y + h);
				glTexCoord2f(0,1);
				glVertex2i(x, y + h);
			glEnd();
		}else{
			color.bind();
			glBegin(GL_LINES);
				glTexCoord2f(0,0);
				glVertex2i(x, y);
				glTexCoord2f(1,1);
				glVertex2i(x + w, y);
			glEnd();
			
			glBegin(GL_LINES);
				glTexCoord2f(0,0);
				glVertex2i(x + w, y);
				glTexCoord2f(1,1);
				glVertex2i(x + w, y + h);
			glEnd();
		
			glBegin(GL_LINES);
				glTexCoord2f(0,0);
				glVertex2i(x + w, y + h);
				glTexCoord2f(1,1);
				glVertex2i(x, y + h);
			glEnd();
			
			glBegin(GL_LINES);
				glTexCoord2f(0,0);
				glVertex2i(x, y + h);
				glTexCoord2f(1,1);
				glVertex2i(x, y);
			glEnd();
		}
		Fonts.drawString(text, x, y, 1, color);
	}
}
