package com.mygdx.game;



import regularClases.Player;

public class Monster extends Player {
	
	private static String[][] strings = {{new String("grenflytrap.jpg"), new String("greensprite.jpg"), new String("greenbiclops.jpg"), new String("greenslime.jpg"), new String("greengargoyle.jpg"), new String("greenspider.jpg")},
									{new String("blueflytrap.jpg"), new String("bluesprite.jpg"), new String("bluebiclops.jpg"), new String("blueslime.jpg"), new String("greengargoyle.jpg"), new String("bluespider.jpg")},
									{new String("redflytrap.jpg"), new String("redsprite.jpg"), new String("redbiclops.jpg"), new String("redslime.jpg"), new String("redgargoyle.jpg"), new String("redspider.jpg")}};
									

	private static int[] health = {2,3,4,5,3,5};
	private static int[] attack = {2,2,3,3,3,4};
	private static int[] defense = {0,0,1,1,2,2};
	private static int[] gold = {10,12,13,15,16,25};
	
	int type;
	int level;
	
	public Monster(int level, int type) {
		
		
		super(strings[level][type-1], health[type -1 ] *(level + 1), attack[type -1  ] * (level + 1), defense[type -1] * (level + 1),false);
		this.level = level;
		this.type = type - 1;
	}
	
	public Monster(String texture, int health, int attack, int defense){
		super(texture, health, attack, defense, false);
	}

	private int attack(){
	//	return new Dice().roll;
		return 2;
	}
	
	private int defeated(){
		return gold[type] * (level + 1);
	}
	
	public void giveGold(int gold){
		// keep empty used in battleScreen
	}
	
	public void recover(){
		// keep empty used in battleScreen
		this.setDead(false);
	}

	@Override
	public void death(Player attacker) {
		//SevenDungeons.game.setScreen(SevenDungeons.boardScreen);
		this.setDead(true);
		attacker.giveGold(this.gold[type] * (level + 1));
	}

	@Override
	public int getGold() {
		return this.gold[type] * (level + 1);
	}
}
