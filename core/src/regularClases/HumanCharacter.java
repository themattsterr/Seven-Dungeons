package regularClases;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GameBoard;

public abstract class HumanCharacter extends Character {
	
	//the characters square position
	public int position = 0;
	//the characters level
	public int level = 0;
	
	//will be replaced with dice class
	Random rand = new Random();
	
	//takes in board so it can move
	GameBoard board;
	
	public HumanCharacter(String texture, int health, int attack, int defense, GameBoard board) {
		super(texture, health, attack, defense);
		this.board = board;
	}
	
	public boolean move(){
		//tells the current tile that the character has left
		board.getTile(level, position).removePlayer(this);
		
		
			position += this.rollDice();
		
		
		switch (level){
			case 0: position %= 36;
					break;
			case 1: position %= 28;
					break;
			case 2: position %= 18;
					break;
		}
		
		board.getTile(level, position).addPlayer(this);
		super.move(board.getTile(level, position).getVector());
		return true;
	}
	
	private int rollDice(){
		return rand.nextInt(6) + 1;
		
	}
	
	
		
	
}
