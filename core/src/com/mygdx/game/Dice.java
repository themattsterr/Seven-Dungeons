package com.mygdx.game;

import java.util.Random;

public class Dice {
	
	private Random rand = new Random();
	private int value;

	//dice
	public Dice() {
		value = 0;
	}
	
	public int roll() {
		value = generateValue();
		return value;
	}
	
	private int generateValue() {
		return rand.nextInt(6) + 1;
	}

}
