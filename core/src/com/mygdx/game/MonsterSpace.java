package com.mygdx.game;

import regularClases.HumanCharacter;
import Screens.BattleScreen;

import com.badlogic.gdx.math.Vector2;

public class MonsterSpace extends Tile {

	private int level;
	
	public MonsterSpace(int level, float x, float y) {
		super(level, x, y);
		this.level = level;
	}

	@Override
	public void land(HumanCharacter active) {
		if(super.execute(active)) SevenDungeons.game.setEncounter( active, new Monster(level, new Dice().roll()));
		
	}


}
