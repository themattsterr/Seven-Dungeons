package com.mygdx.game;

import java.util.ArrayList;

import regularClases.HumanCharacter;

import Screens.BattleScreen;
import Screens.Dock;

import com.badlogic.gdx.math.Vector2;

public abstract class Tile {
	private Vector2 vector;
	private ArrayList<HumanCharacter> playersOnSpace = new ArrayList<HumanCharacter>();
	private int level;
	private QuestCard quest = null;
	private boolean anywhere = false;
	private boolean up;
	
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
	
	public boolean addQuest(QuestCard quest){
		if(this.quest  == null){ this.quest = quest; return true;}
		else return false;
	}
	
	public boolean ifClash(){
		if(playersOnSpace.size() > 1) return true;
		else return false;
	}
	
	public void setAnywhere(boolean movement){
		anywhere = true;
		up = movement; 
	}
	
	public void getArrow(Dock dock){
		System.out.print("test");
		dock.rightArrowButton.setVisible(true);
		
		if (anywhere){
			dock.leftArrowButton.setVisible(true);
			if(up == false) dock.downArrowButton.setVisible(true);
			else dock.upArrowButton.setVisible(true);
		}
	
	}
	public Vector2 getVector(){
		return vector;
	}
	
	public boolean execute(HumanCharacter active){
		if(this.quest != null){
			quest.activate(active);
			this.quest = null;
		}
		
		if(this.ifClash()){
				SevenDungeons.setEncounter(active, this.playersOnSpace.get(0));
				return false;
		}
	
		return true;
	}

	public abstract void land(HumanCharacter active);

}
