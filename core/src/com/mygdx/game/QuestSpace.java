package com.mygdx.game;

import regularClases.HumanCharacter;

public class QuestSpace extends Tile {

	
	public QuestSpace(int level, float x, float y) {
		super(level, x, y);
	}
	
	
	public void land(HumanCharacter active){
		if( super.execute(active)) new QuestCard(active);
		else return;  
	}
}
