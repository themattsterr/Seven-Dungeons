package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import regularClases.HumanCharacter;

public class ItemCard extends Card {


	
	//health, attack, or defense bonus?
	public int type;
	private int price;
	public int magnitude = 2;
	
	private Table infoTable;
	
	private Group group;
	public boolean isSpell = false;
	public boolean purchased = false;
	public int scaledCost;
	
	public ItemCard(int type, int price) {
		
		this.type = type;
		this.price = price;
		//this.setImageButton("item_card");
		
		
		group = new Group();
	}
	
	public Group getGroup(){
		
		//Group group = new Group();
		//group.addActor(this.button);
		
		infoTable = createInfoTable(SevenDungeons.getPlayer());
		if (isSpell)
			infoTable.setBackground(Card.skin.getDrawable("spell_card"));
		else
			infoTable.setBackground(Card.skin.getDrawable("item_card"));
		group.addActor(infoTable);
		group.setSize(width,height);
		
		group.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				//System.out.println("item card pressed");
				Group target = (Group) event.getListenerActor();
				
				if (SevenDungeons.game.getScreen() == SevenDungeons.shopScreen)
					SevenDungeons.shopScreen.removeFromShop(target);
				
				if (SevenDungeons.game.getScreen() == SevenDungeons.handScreen)
					SevenDungeons.handScreen.useSpell(target);
				
				return true;
			}
		});
		
		
		
		return group;
	}
	
	private Table createInfoTable(HumanCharacter active) {
		Table table = new Table();
		Image statImage;
		Image goldImage = new Image(SevenDungeons.goldRegion);
		
		switch (type){
		case 1:
			statImage = new Image(SevenDungeons.healthRegion);
			break;
		case 2:
			statImage = new Image(SevenDungeons.attackRegion);
			break;
		case 3:
			statImage = new Image(SevenDungeons.defenseRegion);
			break;
		default:
			statImage = new Image();
			break;
		}
		
		table.add(new Label(this.getText(), SevenDungeons.labelStyle)).align(Align.center).align(Align.bottom).height(height/2);
		table.row();
		
		Table innerTable = new Table();
		innerTable.add(statImage).align(Align.center).size(height/2, height/2).expand();
		innerTable.add(new Label(String.valueOf(magnitude), SevenDungeons.labelStyle));
		innerTable.add(goldImage).align(Align.center).size(height/2, height/2).expand();
		innerTable.add(new Label(String.valueOf(getCost(active)), SevenDungeons.labelStyle));
		innerTable.setSize(width, height/2);
		innerTable.debug();
		
		table.add(innerTable);
		table.setSize(width, height);
		
		table.debug();
		
		return table;
	}
	
	public int purchase(HumanCharacter active){
		//method subtracts the cards cost from the players gold.
		scaledCost = (int) (this.price * (active.Purchased(type) * 1.5 + 1));
		System.out.println("cost: " + scaledCost + " ");
		
		if(active.getGold() >= (this.price * (active.Purchased(type) * 1.5 + 1))){
			this.activate(active);
			return (int) (this.price * (active.Purchased(type) * 1.5 + 1));
		}
		else
			System.out.println("did not purchase");
		return 0;
	}
	
	
	public boolean canPurchase(HumanCharacter active) {
		
		// change this
		boolean enoughGold = (active.getGold() >= getCost(active) && active.hand.size() < 3);
		
		if(enoughGold) {
			//if player has enough gold and room inventory purchase and return true
			active.giveGold(-1 * getCost(active));
			this.activate(active);
			return true;
		}
		//else dont purchase and return false
		return false;
		
	}
	
	public int getCost(HumanCharacter active){
		if (!isSpell)
			return (int) (price * (active.Purchased(type) * 1.5 + 1));
		else
			return price;
	}
	
	public int getMagnitude(HumanCharacter active){
		return magnitude;
	}

	@Override
	public boolean activate(HumanCharacter active) {
		if (!this.isSpell)
			active.getItem(this);
		else
			active.giveCard(this);
		return true;
	}

}
