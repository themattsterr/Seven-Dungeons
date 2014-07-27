package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.scenes.scene2d.Group;

import regularClases.HumanCharacter;

public class FortuneCard extends Card {

	//the gold that the player will receive upon activating the card
	private int value;
	private Random rand = new Random();
	
	
	public FortuneCard(HumanCharacter active){
		this.value = (rand.nextInt(7) - 2) * 10 * (active.level + 1);
		this.activate(active);
	}

	

	@Override
	public boolean activate(HumanCharacter active) {
		active.giveGold(value);
		System.out.println("congradulations " + value);
		return true;
	}



	@Override
	public Group getGroup() {
		// TODO Auto-generated method stub
		return null;
	}




	
	
}
