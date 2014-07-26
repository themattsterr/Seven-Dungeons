package com.mygdx.game;

import regularClases.HumanCharacter;

public class HealthSpace extends Tile {

	public HealthSpace(int level, float x, float y) {
		super(level, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void land(HumanCharacter active) {
		super.execute(active);
		active.recover();
		
	}

}
