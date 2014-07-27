package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;

import regularClases.HumanCharacter;

public class QuestCard extends Card {

	//holds the gold that the player will receive upon completion of the quest.
	private int value;
	private int id;
	//method will generate a random integer.
	private Random rand = new Random();
	
	public QuestCard(HumanCharacter active) {
		
		this.id = active.getId();
		this.setQuest();
		
		
	
	}
	
	//generates a random board position and puts the quest on that location
	public void setQuest(){
		int level = rand.nextInt(3);
		int position = rand.nextInt(36);
		switch(level){
			case 2: position %= 18;
					break;
			case 1: position %= 28;
					break;
			case 0: position %= 36;
		}
		
		this.value = 10 * new Dice().roll() *  (level + 1);
		
		SevenDungeons.board.getTile(level, position).addQuest(this);
		
		System.out.println("new quest at level " + level + " position " + position);
	}
	
	


	@Override
	public boolean activate(HumanCharacter active) {
		
		System.out.println("landed on quest");
		if (this.id == active.getId()){
			active.giveGold(value);
			return true;
		}
		
		else return false;
		
	}

	@Override
	public Group getGroup() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
