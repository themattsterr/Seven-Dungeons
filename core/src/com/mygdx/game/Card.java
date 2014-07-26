package com.mygdx.game;

import regularClases.HumanCharacter;


public abstract class Card {
	
	private String text;
	
	


	
	public String getText(){
		return text;
	}
	
	public abstract boolean activate(HumanCharacter active);
}
