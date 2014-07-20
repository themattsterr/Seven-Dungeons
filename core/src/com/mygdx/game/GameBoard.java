package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameBoard {
	private Tile[][] spaces = new Tile[3][36];
	
	static final float[] xOut = {113,436,715,1036,1257,1663,1966,2269,2560,2872};
	static final float[] yOut=  {62, 257, 431, 605, 770, 950, 1127, 1304, 1478, 1664};
	
	
	
	public GameBoard(){
		
		for(int i = 0; i < 10; i++){
			spaces[0][i] = new MonsterSpace(0, xOut[i], yOut[0]);
		}
		for(int i = 0; i < 8; i++){
			spaces[0][i + 10] = new MonsterSpace(0, xOut[9], yOut[i + 1]);
		}
		for(int i = 0; i < 10; i++){
			spaces[0][i+18] = new MonsterSpace(0, xOut[9 - i], yOut[9]);
		}
		for(int i =0; i < 8; i++){
			spaces[0][i + 28] = new MonsterSpace(0, xOut[0], yOut[9 - i - 1]);
		}
		
		//mid
		for(int i = 0; i < 8; i++){
			spaces[1][i] = new MonsterSpace(1, xOut[i + 1], yOut[1]);
		}
		for(int i = 0; i < 6; i++){
			spaces[1][i + 8] = new MonsterSpace(1, xOut[8], yOut[i + 2]);
		}
		for(int i = 0; i < 8; i++){
			spaces[1][i+14] = new MonsterSpace(1, xOut[8 - i], yOut[8]);
		}
		for(int i =0; i < 6; i++){
			spaces[1][i + 22] = new MonsterSpace(1, xOut[1], yOut[9 - i - 2]);
		}
		
		//inside
		for(int i = 0; i < 6; i++){
			spaces[2][i] = new MonsterSpace(2, xOut[i + 2], yOut[2] + 100);
		}
		for(int i = 0; i < 3; i++){
			spaces[2][i + 6] = new MonsterSpace(2, xOut[7], yOut[i + 3] + 100);
		}
		for(int i = 0; i < 6; i++){
			spaces[2][i+9] = new MonsterSpace(2, xOut[7 - i], yOut[7]);
		}
		for(int i =0; i < 3; i++){
			spaces[2][i + 15] = new MonsterSpace(2, xOut[2], yOut[9 - i - 3]);
		}
		
	}
	
	public Tile getTile(int x , int y){
		return spaces[x][y];
	}
	
}
