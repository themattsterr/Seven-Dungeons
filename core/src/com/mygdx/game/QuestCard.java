package com.mygdx.game;

import java.util.Random;

public class QuestCard extends Card {

	//holds the gold that the player will receive upon completion of the quest.
	private int value;
	//method will generate a random integer.
	private Random rand = new Random();
	
	public QuestCard(int id) {
		super(id);
	}
	
	//generates a random board position and puts the quest on that location
	public void setQuest(){
		
	}
	
	//called if a player lands on that location it gives the value to the player and their gold is added by it
	public void giveBonus(){
		
	}
	
	

}
