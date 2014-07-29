package com.mygdx.game;

import regularClases.Player;

public class Dragon extends Monster {

	public Dragon() {
		super("dragon.jpg", 100, 10, 10);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void death(Player attacker) {
		//SevenDungeons.game.setScreen(SevenDungeons.boardScreen);
		this.setDead(true);
		
	}
}
