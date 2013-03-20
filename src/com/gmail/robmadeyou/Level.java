package com.gmail.robmadeyou;

public class Level {
	/*
	 * Current level decides which level to draw right now and
	 * it also decides which level will the collision be tested on. This plays
	 * a very large role in the game and is very vital
	 */
	public static int currentLevel = -1;
	
	public static int levelUnlocked = 0;
	
	
	/*
	 * This will be used for other difficulties
	 * On harder levels the number of sand grains will not 
	 * reset upon new level; instead it will be globally kept a record of
	 */
	public static int dotsOnScreen = 0;
	/*
	 * The amount of grains of sand that are securely in the "safe zone" of the current level
	 * hehe :D
	 */
	public static int dotsSecured = 0;
	/*
	 * Score is kept globally and will increase as the player progresses on with the game
	 */
	public static int score = 0;
	
	public static int totalScore = 0;
	/*
	 * This is to check the difficulty of the game, if set to true dots won't
	 * re-generate on a new level selecting :D very hard imo
	 */
	
	public static boolean onHard = false;
	public static void Timer(){
		
	}
	public static void setUpLevel(int level){
		currentLevel = level;
		dotsSecured = -1;
		WallList.removeAllOpWalls();
		WallList.setUpAllOpBoxes();
		if(!onHard){
			SandList.removeAllDots();
			for(int i = 0; i < SandList.maxSand; i++){
				SandList.addMoreSand(new SandList.particles(0, 0, "cyan"));
			}
			SandList.placeNewLocation(currentLevel);
		}else if(onHard){
			SandList.placeNewLocation(currentLevel);
		}
		dotsSecured = 0;
	}
	public static void onLevelUpdate(){
		if(levelUnlocked < currentLevel + 1){
			levelUnlocked = currentLevel + 1;
			
		}
		if(dotsOnScreen == dotsSecured && dotsOnScreen > 0){
			StateManager.changeState("select", false);
			totalScore += dotsSecured;
			if(currentLevel <= 6){
				setUpLevel(currentLevel + 1);
			}
			
		}
		if(dotsOnScreen == 0){
			StateManager.changeState("select", true);
			levelUnlocked--;
		}
	}
}
