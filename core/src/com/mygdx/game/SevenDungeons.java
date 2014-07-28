package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import Screens.BattleScreen;
import Screens.BoardScreen;
import Screens.ChooseClassScreen;
import Screens.HandScreen;
import Screens.NewGameScreen;
import Screens.ShopScreen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import regularClases.Archer;
import regularClases.Assassin;
import regularClases.HumanCharacter;
import regularClases.Knight;
import regularClases.Mage;
import regularClases.Player;

public  class SevenDungeons extends Game{
	
	//references to every screen
	public static NewGameScreen newGameScreen;
	public static ChooseClassScreen chooseClassScreen;
	public static BoardScreen boardScreen;
	public static ShopScreen shopScreen;
	public static HandScreen handScreen; 
	public static BattleScreen battleScreen;
	public static GameBoard board = new GameBoard();
	
	public static LabelStyle labelStyle;
	public static BitmapFont font;
	
	private static Texture iconTexture;
	public static TextureRegion healthRegion;
	public static TextureRegion attackRegion; 
	public static TextureRegion defenseRegion;
	public static TextureRegion goldRegion;
	
	 //Holds Players
	static ArrayList<HumanCharacter> players = new ArrayList<HumanCharacter>();
	//whos turn is it
	public static int turn = 0; 
	
	
	//saves the first instance of itself, allows us to cheat and use non static members on a static class
	public static SevenDungeons game;
	
	public static Batch batch;
	
	public SevenDungeons(){
		System.out.println("created");
		

	}
	
	public  void create () {
		
		//builds screen references
		newGameScreen = new NewGameScreen();
		chooseClassScreen = new ChooseClassScreen();
		boardScreen = new BoardScreen();
		shopScreen = new ShopScreen();
		handScreen = new HandScreen();
		battleScreen = new BattleScreen();
		batch = new SpriteBatch();
		//sets the initial screen
		//this.setScreen(newGameScreen);
		
		// used for labels throughout UI
		font = new BitmapFont(Gdx.files.internal("exo-small.fnt"), false);
		labelStyle = new LabelStyle(font, Color.BLACK);
		
		iconTexture = new Texture("playericons.png");
		healthRegion = new TextureRegion(iconTexture, 0, 0, 180, 180);
		attackRegion = new TextureRegion(iconTexture, 180, 0, 180, 180); 
		defenseRegion = new TextureRegion(iconTexture, 360, 0, 180, 180);
		goldRegion = new TextureRegion(iconTexture, 540, 0, 180, 180);
		
		/*TEMPERARY PLACING PLAYERS PLAYER SELECT SCREEN WILL HANDLE THIS */
		players.add(new Mage(0));
		players.get(0).warp(2,0);
		players.add(new Archer(1));
		players.get(1).warp(2,4);
		players.add(new Knight(2));
		players.get(2).warp(1,18);
		players.add(new Assassin(3));
		players.get(3).warp(1, 27);
		game = this;
		
		this.setScreen(boardScreen);
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 5, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		super.render();
	}
	
	//adds a new player to the game
	public static void addPlayer(HumanCharacter player){
		players.add(player);
	}
	
	//changes turn
	public static void changeTurn(){
		turn++;
		turn %= players.size();
		boardScreen.dock.show();
		boardScreen.setFocus(getPlayer().xPos, getPlayer().yPos);
		boardScreen.move = false;
		boardScreen.rolled = false;
		
	}
	
	//gets the current player
	public static HumanCharacter getPlayer(){
		return players.get(turn);
	}
	
	//gets the ammount of players playing
	public static int getNumPlayers(){
		return players.size();
	}
	
	public static HumanCharacter getPlayer(int i){
		return players.get(i);
	}
	//starts a battle with a monster
	public static void setEncounter(HumanCharacter attacker, Player defender){
		
		BattleScreen battleScreen = new BattleScreen();
		battleScreen.setBattle(attacker, defender);	
	 	game.setScreen(battleScreen);
		
	}
	
	// opens current players inventory
	public static void openInventory() {
		handScreen.setPlayer(getPlayer());
		game.setScreen(handScreen);
	}
	
	// opens shop screen
	public static void openShop(){
		ArrayList<ItemCard> cards = new ArrayList<ItemCard>(6);
		for (int i = 0; i < 6; i++){
			
			ItemCard curCard = new ItemCard((i%3)+1,i*5);
			curCard.create();
			if (i>2)
				curCard.isSpell = true;
			cards.add(curCard);
			
		}
		
		shopScreen.setInventory(cards);
		game.setScreen(shopScreen);
	}
	


	
}


