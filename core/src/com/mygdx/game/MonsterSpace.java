package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class MonsterSpace extends Tile {

	public MonsterSpace(int level, float x, float y) {
		super(level, x, y);
		
	}

	@Override
	public void execute(GameTest game) {
		game.setScreen(game.battleScreen);
		
	}

}
