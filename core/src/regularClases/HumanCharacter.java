package regularClases;

import java.util.Random;

import Screens.Dock;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.Dice;
import com.mygdx.game.GameBoard;
import com.mygdx.game.ItemCard;
import com.mygdx.game.SevenDungeons;

public abstract class HumanCharacter extends Player {
	
	//the characters square position
	public int position = 0;
	//the characters level
	public int level = 0;
	private Timer wait = new Timer();
	private int gold = 5;
	private int id;
	
	private int items[] = {0,0,0};
	
	public boolean hasRolled = false;
	
	
	//will be replaced with dice class
	Random rand = new Random();
	
	
	public HumanCharacter(String texture, int id, int health, int attack, int defense) {
		super(texture, health, attack, defense);
		this.id = id;
	}
	
	public void move(int roll, int levelChange){
		//tells the current tile that the character has left
		SevenDungeons.board.getTile(level, position).removePlayer(this);
		
		level+= levelChange;
		position += roll;
		
		
		switch (level){
			case 0: position %= 36;
					break;
			case 1: position %= 28;
					break;
			case 2: position %= 18;
					break;
		}
		
		SevenDungeons.board.getTile(level, position).addPlayer(this);
		super.move(SevenDungeons.board.getTile(level, position).getVector());
		
	

		SevenDungeons.board.getTile(level, position).land(this);
	
	
	}
	
	
	public void warp(int level, int position){
		this.position = position;
		this.level = level;
		SevenDungeons.board.getTile(this.level, this.position).removePlayer(this);
		SevenDungeons.board.getTile(level, position).addPlayer(this);
		super.move(SevenDungeons.board.getTile(level, position).getVector());
	}
	
	public int rollDice(Dock dock){
		
		SevenDungeons.board.getTile(level, position).getArrow(dock);
		return dock.dice.roll();
		
	}
	
	public int getGold(){
		return this.gold;
	}
	
	public float getX(){
		return super.xPos;
	}
	
	public float getY(){
		return super.yPos;
	}
	
	public int getId(){
		return this.id;
	}
	
	//returns how many times the player has bought a certain upgrade
	public int Purchased(int type){
		return items[type - 1];
	}
	
	public void getItem(ItemCard item){
		switch(item.type){
		case 1: maxHealth += (item.magnitude);
		case 2: attack += (item.magnitude);
		case 3: defense += (item.magnitude);
		}
		
		items[item.type - 1]++;
	}
	

	public void giveGold(int value){
		this.gold+= value;
		System.out.print(" give gold + " + value + " have gold " + gold);
		if(this.gold < 0) this.gold = 0;
	}

	public void recover(){
		currentHealth = maxHealth;
	}
	
	public void death(Player attacker){
		SevenDungeons.game.setScreen(SevenDungeons.boardScreen);
		this.gold *=  .5;
		currentHealth = maxHealth;
		attacker.giveGold(gold);
		this.warp(0, 0);
		
	}
}
