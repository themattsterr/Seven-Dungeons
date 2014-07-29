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
		super(texture, health, attack, defense, true);
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
		SevenDungeons.boardScreen.dock.show();
	
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
		hasRolled = true;
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
	
	public void useItem(ItemCard item){
		switch(item.type){
		case 1: 
			if (currentHealth != maxHealth){
				currentHealth += (item.magnitude);
				items[item.type - 1]--;
				this.setStatus(" health +" + item.magnitude);
			} else {
				maxHealth += (item.magnitude);
				this.setStatus(" max health +" + item.magnitude);
			}
			break;
		case 2: attack += (item.magnitude);
				this.setStatus(" attack +" + item.magnitude);
				break;
		case 3: defense += (item.magnitude);
				this.setStatus(" defense +" + item.magnitude);
				break;
		}
		
		items[item.type - 1]++;
	}
	
	// uses spell on all other players
	public void useSpell(ItemCard spell){
		for (int i = 0; i < SevenDungeons.getNumPlayers(); i++)
		{
			if (SevenDungeons.getPlayer() != SevenDungeons.getPlayer(i)){
				switch(spell.type){
				case 1: 
					SevenDungeons.getPlayer().setStatus(" used a health spell");
					//SevenDungeons.getPlayer(i).setStatus(" -" + spell.magnitude + " health");
					if (SevenDungeons.getPlayer(i).currentHealth - spell.magnitude > 0){
						//SevenDungeons.getPlayer(i).currentHealth -= (spell.magnitude);
					} else {
						SevenDungeons.getPlayer(i).death(SevenDungeons.getPlayer());
						SevenDungeons.getPlayer(i).recover();
					}
				case 2: 
					SevenDungeons.getPlayer().setStatus(" used an attack spell");
					if (SevenDungeons.getPlayer(i).attack - spell.magnitude > 0){
						//SevenDungeons.getPlayer(i).setStatus(" attack -" + spell.magnitude);
						SevenDungeons.getPlayer(i).attack -= (spell.magnitude);
					} else {
						//SevenDungeons.getPlayer(i).setStatus(" attack -" + SevenDungeons.getPlayer(i).attack);
						SevenDungeons.getPlayer(i).attack = 0;
					}
				case 3: 
					SevenDungeons.getPlayer().setStatus(" used a defense spell");
					if (SevenDungeons.getPlayer(i).defense - spell.magnitude > 0){
						//SevenDungeons.getPlayer(i).setStatus("defense -" + spell.magnitude);
						SevenDungeons.getPlayer(i).defense -= (spell.magnitude);
					} else {
						//SevenDungeons.getPlayer(i).setStatus(" defense -" + SevenDungeons.getPlayer(i).defense);
						SevenDungeons.getPlayer(i).defense = 0;
					}
				}
			}
				
		}
	}
	

	public void giveGold(int value){
		int goldBefore = this.gold;
		this.gold += value;
		if(this.gold < 0) this.gold = 0;
		
		if (value > 0)
			this.setStatus(" gold +" + value);
		else if(this.gold == 0){
			this.setStatus(" gold -" + goldBefore);
		} else {
			this.setStatus(" gold -" + (-value));
		}
	}

	public void recover(){
		this.setStatus(" died");
		currentHealth = maxHealth;
		this.warp(0, 0);
		this.setDead(false);
	}
	
	public void death(Player attacker){
		//SevenDungeons.game.setScreen(SevenDungeons.boardScreen);
		this.gold *=  .5;
		attacker.giveGold((int)(gold*.5));
		this.setDead(true);
	}
}
