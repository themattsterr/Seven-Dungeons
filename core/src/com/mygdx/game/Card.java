package com.mygdx.game;


public abstract class Card {
	
	private String text;
	
	// int for card identification
	private int id;
	
	public Card(int id) {
		this.id = id;
	}

	public void Activate(){
		
	}
	
	public String getText(){
		return text;
	}

}
