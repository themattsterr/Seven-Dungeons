package com.mygdx.game;

import java.util.ArrayList;

import regularClases.HumanCharacter;

import com.badlogic.gdx.math.Vector2;

public abstract class Tile {
	private Vector2 vector;
	private ArrayList<HumanCharacter> playersOnSpace = new ArrayList<HumanCharacter>();
	private int level;
	
	public Tile(int level, float x, float y){
		this.vector = new Vector2(x,y);
		this.level = level;
	}
	
	public void addPlayer(HumanCharacter player){
		playersOnSpace.add(player);
	}
	
	public void removePlayer(HumanCharacter player){
			playersOnSpace.remove(player);
	}
	
	public HumanCharacter getPlayer(){
		return playersOnSpace.get(playersOnSpace.size() -1);
	}
	
	public int getLevel(){
		return level;
	}
	
	public boolean ifClash(){
		if(playersOnSpace.size() > 1) return true;
		else return false;
	}
	
	public Vector2 getVector(){
		return vector;
	}
	
	public abstract void execute(GameTest game);
}
