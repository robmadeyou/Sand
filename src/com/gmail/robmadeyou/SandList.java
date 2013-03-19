package com.gmail.robmadeyou;

import java.util.Random;

public class SandList {
	public static class particles extends Sand{
		public particles(int x, int y, String color) {
			super(x, y, color);
		}
	}
	static int maxSand = 20;
	static Sand[] s = new Sand[maxSand];
	static boolean isPlayerMouseDown = false;
	
	public static void addMoreSand(particles p){
		for(int i = 0; i < maxSand; i++){
			if(s[i] == null){
				s[i] = p;
				s[i].setNumber(i);
				Level.dotsOnScreen++;
				break;
			}
		}
	}
	public static void checkCollision(int n){
		for(int i = 0; i < maxSand; i++){
			if(s[i] != null && s[n]!= null){
				int x1 = s[i].getX();
				int y1 = s[i].getY();
				
				int x2 = s[n].getX();
				int y2 = s[n].getY();
				
				if(s[i].getNumber() != s[n].getNumber()){
					if(x1 == x2 && y1 == y2){
						Random ran = new Random();
						int ranX = 2 - ran.nextInt(6);
						int ranY = 2 - ran.nextInt(6);
						s[n].setX(s[n].getX() + ranX);
						s[n].setY(s[n].getY() + ranY);
					}
				}
				
			}
		}
	}
	/*
	 * Used in harder difficulties where when new level starts; instead of removing all the dots
	 * and then placing them back on the screen they are all instead moved to the new location
	 * 
	 * This won't locate them to the specific spot, instead they will be moved
	 * randomly in the spawn squares radius, yet still very close and not touching the walls!
	 * 
	 */
	public static void placeNewLocation(int levelNum){
		for(int i = 0; i < maxSand; i++){
			if(WallList.wall[levelNum] != null){
				int x = WallList.wall[levelNum][0].getX();
				int y = WallList.wall[levelNum][0].getY();
				int w = WallList.wall[levelNum][0].getWidth();
				int h = WallList.wall[levelNum][0].getHeight();
				
				Random ran = new Random();
				int ranX = x +ran.nextInt(w - 10);
				int ranY = y +ran.nextInt(h - 10);
				//Final thing that actually sets the location in the end for all of the pounts :D
				if(s[i] != null){
					s[i].setLocation(ranX, ranY);
				}
			}
		}
	}
	//This is silly o_O
	public static void removeAllDots(){
		for(int i = 0; i < maxSand; i++){
			if(s[i] != null){
				s[i] = null;
				Level.dotsOnScreen = 0;
			}
		}
	}
	
	/*
	 * Every time this method is called the sand particles are going to update
	 * accordingly, move, slow down and check collision all depending
	 * on their location;
	 */
	public static void onUpdate(int delta){
		for(int i = 0; i < maxSand; i++){
			if(s[i] != null){
				s[i].setMovingToMouse(isPlayerMouseDown);
				s[i].onUpdate();
				int x = s[i].getX();
				int y = s[i].getY();
				//Checking collision
				//This includes the finish block too
				for(int r = 1; r < 100; r++){
					if(WallList.wall[Level.currentLevel][r] != null){
						int x2 = WallList.wall[Level.currentLevel][r].getX();
						int y2 = WallList.wall[Level.currentLevel][r].getY();
						int w = WallList.wall[Level.currentLevel][r].getWidth();
						int h = WallList.wall[Level.currentLevel][r].getHeight();
						if(x >= x2 && x <= x2 + w && y >= y2 && y <= y2 + h){
							if(r != 1){
								s[i] = null;
								Sound.playSound(Sound.wavEffect);
								Level.dotsOnScreen--;
							}else{
								if(!s[i].isInFinish()){
								s[i].setToFinish(true);
								Level.dotsSecured++;
								}
							}
						}
					}
				}
				checkCollision(i);
			}
		}
	}
}
