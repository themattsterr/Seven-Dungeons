package regularClases;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Dice;
import com.mygdx.game.GameBoard;
import com.mygdx.game.SevenDungeons;

public abstract class HumanCharacter extends Player {
	
	//the characters square position
	public int position = 0;
	//the characters level
	public int level = 0;
	
	private int gold = 0;
	
	//will be replaced with dice class
	Random rand = new Random();
	
	
	public HumanCharacter(String texture, int health, int attack, int defense) {
		super(texture, health, attack, defense);
		
	}
	
	public void move(){
		//tells the current tile that the character has left
		SevenDungeons.board.getTile(level, position).removePlayer(this);
		
		
		position += this.rollDice();
		
		
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
	
	}
	
	public void warp(int level, int position){
		this.position = position;
		this.level = level;
		SevenDungeons.board.getTile(this.level, this.position).removePlayer(this);
		SevenDungeons.board.getTile(level, position).addPlayer(this);
		super.move(SevenDungeons.board.getTile(level, position).getVector());
	}
	
	private int rollDice(){
		return new Dice().roll();
		
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
	
	// returns true if gold was given, false if player's amount is < 0
	public boolean giveGold(int gold){
		
		if(this.gold + gold > 0)
			return false;
		else
			this.gold += gold;
		
		return true;
	}
		
	
}
