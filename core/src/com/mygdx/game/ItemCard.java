package com.mygdx.game;

import regularClases.HumanCharacter;

public class ItemCard extends Card {


	
	//health, attack, or defense bonus?
	public int type;
	private int price;
	public int magnitude = 2;
	
	public ItemCard( int type, int price) {
		
		this.type = type;
		this.price = price;
	}
	
	
	public int purchase(HumanCharacter active){
		//method subtracts the cards cost from the players gold.
		if(active.getGold() >= (this.price * (active.Purchased(type) * 1.5 + 1))){
			this.activate(active);
			return (int) (this.price * (active.Purchased(type) * 1.5 + 1));
		}
		else return 0;
	}
	
	public int getCost(HumanCharacter active){
		
		return price * (active.Purchased(type) + 1);
	}
	
	public int getMagnitude(HumanCharacter acative){
		return magnitude;
	}

	@Override
	public boolean activate(HumanCharacter active) {
		active.getItem(this);
		return true;
	}

}
