package com.mygdx.game;

import regularClases.HumanCharacter;

public class FortuneSpace extends Tile {

	public FortuneSpace(int level, float x, float y) {
		super(level, x, y);
		// TODO Auto-generated constructor stub
	}
	
	public void land(HumanCharacter active){
		if (super.execute(active) ) new FortuneCard(active);
	}
}
