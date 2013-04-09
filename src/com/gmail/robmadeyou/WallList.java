package com.gmail.robmadeyou;

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
	public static void changeActiveColor(float r, float g, float b){
		for(int i = 0; i < 1275; i++){
			if(wallOp[i] != null){
				wallOp[i].setChangeColors(r, g, b);
			}
		}
	}
	//Loads all the levels in their proper arrays; just for now. Does not do anything with them
	//until the actual level is run, where then only they are being drawn, and the collision is
	//being calculated
	public static void loadAllLevels(){
		long startTime = System.nanoTime();
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
		wall[1][0] = new Walls( 0, 0, 80, 80, "blue");
		wall[1][1] = new Walls(Display.getWidth() - 80, Display.getHeight() - 80, 80, 80, "red");
		
		wall[1][2] = new Walls((Display.getWidth() / 2) - 352, 200, 700, 150, "cyan");
		wall[1][3] = new Walls(80, 0, 400, 120, "cyan");
		wall[1][4] = new Walls(600, 40, 80, 120, "cyan");
		wall[1][5] = new Walls(0, 100, 40, 60, "cyan");
		wall[1][6] = new Walls(0, 400, 40, 200, "cyan");
		wall[1][7] = new Walls(Display.getWidth() - 100, 0, 100, 240, "cyan");
		wall[1][8] = new Walls(400, Display.getHeight() - 60, 140, 60, "cyan");
		/*
		 * END OF LEVEL 2!
		 */
		
		/*
		 * START OF LEVEL 3!
		 */
		wall[2][0] = new Walls(0, Display.getHeight() / 2 - 40, 80, 80, "blue");
		wall[2][1] = new Walls(Display.getWidth() - 80, Display.getHeight() - 80, 80, 80, "red");
		
		wall[2][2] = new Walls(Display.getWidth() - 400, 200, 400, 140, "cyan");
		wall[2][3] = new Walls(Display.getWidth() - 540, 0, 100, 220, "cyan");
		wall[2][4] = new Walls(Display.getWidth() - 540, Display.getHeight() - 220, 100, 220, "cyan");
		/*
		 * END OF LEVEL 3!
		 */
		
		/*
		 * START OF LEVEL 4!
		 */
		wall[3][0] = new Walls(0,0,80,80,"blue");
		wall[3][1] = new Walls((Display.getWidth() / 2) - 50, (Display.getHeight() / 2) - 50, 80, 80, "red");
		/*
		 * END OF LEVEL 4!
		 */
		
		/*
		 * START OF LEVEL 5!
		 */
		wall[4][0] = new Walls((Display.getWidth() / 2) - 40, (Display.getHeight() / 2) + 20,80,80,"blue");
		wall[4][1] = new Walls(0, 20, 80, 80, "red");
		
		wall[4][2] = new Walls(120, 40, 780, 60, "cyan");
		wall[4][3] = new Walls(120, 40, 40, 380, "cyan");
		wall[4][4] = new Walls(860, 40, 40, 380, "cyan");
		wall[4][5] = new Walls((Display.getWidth() / 2) - 160, Display.getHeight() - 140 ,320, 60, "cyan");
		wall[4][6] = new Walls((Display.getWidth() / 2) - 200, Display.getHeight() - 300, 40, 300,"cyan");
		wall[4][7] = new Walls((Display.getWidth() / 2) + 160, Display.getHeight() - 300, 40, 300,"cyan");
		wall[4][8] = new Walls((Display.getWidth() / 2) - 160, Display.getHeight() - 300, 140, 40, "cyan");
		wall[4][9] = new Walls((Display.getWidth() / 2) + 20, Display.getHeight() - 300, 140, 40, "cyan");
		/*
		 * END OF LEVEL 5!
		 */
		
		/*
		 * START OF LEVEL 6!
		 */
		
		wall[5][0] = new Walls(Display.getWidth() - 80, 0, 80, 80,"blue");
		wall[5][1] = new Walls(0, Display.getHeight() - 80, 80, 80, "red");
		
		wall[5][2] = new Walls(Display.getWidth() - 120, 0, 40, 420, "cyan");
		wall[5][3] = new Walls(Display.getWidth() - 240, 80, 40, 420, "cyan");
		wall[5][4] = new Walls(Display.getWidth() - 360, 0, 40, 420, "cyan");
		wall[5][5] = new Walls(Display.getWidth() - 480, 80, 40, 420, "cyan");
		wall[5][6] = new Walls(Display.getWidth() - 600, 0, 40, 420, "cyan");
		wall[5][7] = new Walls(Display.getWidth() - 720, 80, 40, 420, "cyan");
		wall[5][8] = new Walls(Display.getWidth() - 840, 0, 40, 420, "cyan");
		wall[5][9] = new Walls(Display.getWidth() - 960, 80, 40, 420, "cyan");
		/*
		 * END OF LEVEL 6!
		 */
		
		/*
		 * START OF LEVEL 7!
		 */
		
		wall[6][0] = new Walls(Display.getWidth() / 2 - 80, 40, 160, 160,"blue");
		wall[6][1] = new Walls((Display.getWidth() / 2) - 20, (Display.getHeight() / 2) + 120, 20, 20, "red");
		
		wall[6][2] = new Walls( 20, Display.getHeight() / 2 - 20, 1004, 40, "cyan");
		/*
		 * END OF LEVEL 7!
		 */
		//This is here to stop crashing at the end of level 6
		wall[7][0] = new Walls(150, 150, 50, 50, "blue");
		wall[7][1] = new Walls(200,200,50,50, "red");
		double endTime = (System.nanoTime() - startTime) * 0.0000000001;
		System.out.println("Done in: " + endTime + " seconds.");
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
