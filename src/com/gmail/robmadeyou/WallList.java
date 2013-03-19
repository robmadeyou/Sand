package com.gmail.robmadeyou;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;

public class WallList {
	public static class Walls extends Wall{
		public Walls(int x, int y, int w, int h, String color) {
			super(x, y, w, h, color);
		}
	}
	public static class WallsOp extends WallOp{
		public WallsOp(int x, int y, int w, int h, float op) {
			super(x, y, w, h, op);
		}
	}
	
	static Wall[][] wall = new Wall[10][100];
	static WallOp[] wallOp = new WallOp[1275];
	//lol variable
	static boolean haveWallsOpBeenSetUp = false;
	public static void addNewOpBoxes(WallsOp w){
		for(int i = 0; i < wallOp.length; i++){
			if(wallOp[i] == null){
				wallOp[i] = w;
				break;
			}
		}
	}
	public static void removeAllOpWalls(){
		for(int i = 0; i < wallOp.length; i++){
			wallOp[i] = null;
			haveWallsOpBeenSetUp = false;
		}
	}
	//Loads all the levels in their proper arrays; just for now. Does not do anything with them
	//until the actual level is run, where then only they are being drawn, and the collision is
	//being calculated
	public static void loadAllLevels(){
		/*
		 * 
		 * Please future me follow this order:
		 * [0] = START POSITION;
		 * [1] = END POSITION;
		 * [2+] = WALL BLOCKS;
		 * 
		 * 
		 * THANKYOU!c
		 */
		
		/*
		 * START OF LEVEL 1!
		 */
		//Start!
		wall[0][0] = new Walls((Display.getWidth() / 2) - 412 , 200, 60, 140, "blue");
		//End!
		wall[0][1] = new Walls((Display.getWidth() / 2) + 348, 200, 60, 140, "red");
		//Start with the left side wall
		wall[0][2] = new Walls(0, 0, 100, Display.getHeight() - 100, "cyan");
		//Top piece
		wall[0][3] = new Walls(100, 0, Display.getWidth() - 100, 100, "cyan");
		//Right hand side piece
		wall[0][4] = new Walls(Display.getWidth() - 100, 100, 100, Display.getHeight() - 100, "cyan");
		//Bottom piece :D
		wall[0][5] = new Walls(0, Display.getHeight() - 100, Display.getWidth() - 100, 100, "cyan");
		
		//Centre piece
		wall[0][6] = new Walls((Display.getWidth() / 2) - 352, 200, 700, 150, "cyan");
		/*
		 * END OF LEVEL 1!
		 */
		
		/*
		 * START OF LEVEL 2!
		 */
		wall[1][0] = new Walls(0,0,50,50,"blue");
		wall[1][1] = new Walls((Display.getWidth() / 2) - 50, (Display.getHeight() / 2) - 50, 50, 50, "red");
		
		wall[1][2] = new Walls(50,0, Display.getWidth(), 100, "red");
		/*
		 * END OF LEVEL 2!
		 */
		
		/*
		 * START OF LEVEL 3!
		 */
		wall[2][0] = new Walls(400,100,50,50,"blue");
		wall[2][1] = new Walls((Display.getWidth() / 2) - 50, (Display.getHeight() / 2) - 50, 50, 50, "red");
		/*
		 * END OF LEVEL 3!
		 */
		
		/*
		 * START OF LEVEL 4!
		 */
		wall[3][0] = new Walls(0,0,50,50,"blue");
		wall[3][1] = new Walls((Display.getWidth() / 2) - 50, (Display.getHeight() / 2) - 50, 50, 50, "red");
		/*
		 * END OF LEVEL 4!
		 */
		
		/*
		 * START OF LEVEL 5!
		 */
		wall[4][0] = new Walls(150, 150,50,50,"blue");
		wall[4][1] = new Walls((Display.getWidth() / 2) - 50, (Display.getHeight() / 2) - 50, 50, 50, "red");
		/*
		 * END OF LEVEL 5!
		 */
		
		/*
		 * START OF LEVEL 6!
		 */
		
		wall[5][0] = new Walls(150, 150,50,50,"blue");
		wall[5][1] = new Walls((Display.getWidth() / 2) - 50, (Display.getHeight() / 2) - 50, 50, 50, "red");
	
		/*
		 * END OF LEVEL 6!
		 */
		
		/*
		 * START OF LEVEL 7!
		 */
		
		wall[6][0] = new Walls(150, 150,50,50,"blue");
		wall[6][1] = new Walls((Display.getWidth() / 2) - 50, (Display.getHeight() / 2) - 50, 50, 50, "red");
		
		/*
		 * END OF LEVEL 7!
		 */
	}
	public static void setUpAllOpBoxes(){
		if(!haveWallsOpBeenSetUp){
			for(int i = 0; i < wall.length; i++){
				if(wall[Level.currentLevel][i] != null){
					wall[Level.currentLevel][i].setUpSmallBoxes();
				}
			}
		}
		haveWallsOpBeenSetUp = true;
	}
	public static void onUpdate(){
		wall[Level.currentLevel][0].draw();
		wall[Level.currentLevel][1].draw();
		for(int i = 0; i < wallOp.length; i++){
			if(wallOp[i] != null){
				wallOp[i].onUpdate();
			}
		}
	}
}
