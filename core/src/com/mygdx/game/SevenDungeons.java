package com.mygdx.game;

import java.util.ArrayList;

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
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import regularClases.Archer;
import regularClases.Assassin;
import regularClases.HumanCharacter;
import regularClases.Knight;
import regularClases.Mage;

public  class SevenDungeons extends Game{
	
	//references to every screen
	public static NewGameScreen newGameScreen;
	public static ChooseClassScreen chooseClassScreen;
	public static BoardScreen boardScreen;
	public static BattleScreen battleScreen;
	public static ShopScreen shopScreen;
	public static HandScreen handScreen; 
	public static GameBoard board = new GameBoard();
	
	 //Holds Players
	static ArrayList<HumanCharacter> players = new ArrayList<HumanCharacter>();
	//whos turn is it
	static int turn = 0; 
	
	
	//saves the first instance of itself, allows us to cheat and use non static members on a static class
	public static SevenDungeons game;
	
	public  void create () {
		
		//builds screen references
		newGameScreen = new NewGameScreen();
		chooseClassScreen = new ChooseClassScreen();
		boardScreen = new BoardScreen();
		battleScreen = new BattleScreen();
		shopScreen = new ShopScreen();
		handScreen = new HandScreen();
		
		
		//sets the initial screen
		this.setScreen(newGameScreen);
		
		
		/*TEMPERARY PLACING PLAYERS PLAYER SELECT SCREEN WILL HANDLE THIS */
		players.add(new Mage());
		players.add(new Archer());
		players.add(new Knight());
		players.add(new Assassin());
		game = this;
		
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
	public static void setEncounter(Monster defender){
		battleScreen = new BattleScreen();
		game.setScreen(battleScreen);
		battleScreen.setBattle(players.get(turn), defender);	
	}

	
}


