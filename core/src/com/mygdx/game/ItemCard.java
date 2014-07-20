package com.mygdx.game;

public class ItemCard extends Card {

	private final int TYPE_COUNT = 3;
	private int[] types;
	//health, attack, or defense bonus?
	private int type;
	private int price;
	
	
	public ItemCard(int id, int type, int price) {
		super(id);
		types = new int[TYPE_COUNT];
		this.type = type;
		this.price = price;
	}
	
	public void changeStat(){
		//changes the players stat of totalHealth, totalAttack, or totalDefense depending on the item type.
	}
	
	public void chargeCost(){
		//method subtracts the cards cost from the players gold.
	}
	
	public int getCost(){
		return price;
	}

}
