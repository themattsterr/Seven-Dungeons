package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameBoard {
	private Tile[][] spaces = new Tile[3][36];
	
	static final float[] xOut = {113,436,715,1036,1257,1663,1966,2269,2560,2872};
	static final float[] yOut=  {62, 257, 431, 605, 770, 950, 1127, 1304, 1478, 1664};
	
	public GameBoard(){
		
		for(int i = 0; i < 10; i++){
			spaces[0][i] = generateSpace(i,0, xOut[i], yOut[0]);
		}
		for(int i = 0; i < 8; i++){
			spaces[0][i + 10] = generateSpace(i + 10,0, xOut[9], yOut[i + 1]);
		}
		for(int i = 0; i < 10; i++){
			spaces[0][i+18] = generateSpace(i + 18,0, xOut[9 - i], yOut[9]);
		}
		for(int i =0; i < 8; i++){
			spaces[0][i + 28] = generateSpace(i + 28,0, xOut[0], yOut[9 - i - 1]);
		}
		
		//mid
		for(int i = 0; i < 8; i++){
			spaces[1][i] = generateSpace(i,1, xOut[i + 1], yOut[1]);
		}
		for(int i = 0; i < 6; i++){
			spaces[1][i + 8] = generateSpace(i + 8,1, xOut[8], yOut[i + 2]);
		}
		for(int i = 0; i < 8; i++){
			spaces[1][i+14] = generateSpace(i + 14,1, xOut[8 - i], yOut[8]);
		}
		for(int i =0; i < 6; i++){
			spaces[1][i + 22] = generateSpace(i + 22,1, xOut[1], yOut[9 - i - 2]);
		}
		
		//inside
		for(int i = 0; i < 6; i++){
			spaces[2][i] = generateSpace(i,2, xOut[i + 2], yOut[2] + 100);
		}
		for(int i = 0; i < 3; i++){
			spaces[2][i + 6] = generateSpace(i + 6,2, xOut[7], yOut[i + 3] + 100);
		}
		for(int i = 0; i < 6; i++){
			spaces[2][i+9] = generateSpace(i + 9,2, xOut[7 - i], yOut[7]);
		}
		for(int i =0; i < 3; i++){
			spaces[2][i + 15] = generateSpace(i + 15,2, xOut[2], yOut[9 - i - 3]);
		}
		
		
		//makes the space hwere you can go anywhere
		//true means you cant go up false means you cant go down
		spaces[0][4].setAnywhere(true);
		spaces[1][4].setAnywhere(false);
		spaces[0][23].setAnywhere(true);
		spaces[1][12].setAnywhere(true);
		spaces[1][11].setAnywhere(false);
		spaces[1][25].setAnywhere(true);
		spaces[2][7].setAnywhere(false);
		spaces[2][16].setAnywhere(false);
	}
	
	public Tile getTile(int x , int y){
		return spaces[x][y];
	}
	
	public Tile generateSpace(int i, int level, float x, float y){
		
		if((i-1) % 8 == 0) return new FortuneSpace(level, x, y);
		if(((i-2) % 8 == 0) || ((i -4) % 8 == 0)) return new ShopSpace(level,x,y); 
		if(((i - 3) % 8 == 0) || ((i - 6) % 8 == 0) || (i % 8 == 0)) return new MonsterSpace(level, x, y);
		if((i - 5) % 8 == 0) return new QuestSpace(level,x,y);
		if((i - 7) % 8 == 0) return new HealthSpace(level,x,y);
		
		return null;
		}
	
	
}
