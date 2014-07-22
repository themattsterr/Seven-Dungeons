package com.mygdx.game;



import regularClases.Player;

public class Monster extends Player {
	
	private static String[][] Strings = {{new String("greenflytrap.png"), new String("greensprite.png"), new String("greenbiclops.png"), new String("greenslime.png"), new String("greenspider.png")},
									{new String("blueflytrap.png"), new String("bluesprite.png"), new String("bluebiclops.png"), new String("blueslime.png"), new String("bluespider.png")},
									{new String("redflytrap.png"), new String("redsprite.png"), new String("redbiclops.png"), new String("redslime.png"), new String("redspider.png")}};
									

	private static int[] health = {2,3,4,5,3,5};
	private static int[] attack = {2,2,3,3,3,4};
	private static int[] defense = {0,0,1,1,2,2};
	private static int[] gold = {10,12,13,15,16,25};
	
	int type;
	int level;
	
	
	public Monster(int level, int type) {
		
		super(Strings[level][type], health[type], attack[type], defense[type]);
		this.level = level;
		this.type = type;
	}

	private int attack(){
	//	return new Dice().roll;
		return 2;
	}
	
	private int defeated(){
		return gold[type] * level;
	}
}
