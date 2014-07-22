package com.mygdx.game;

import Screens.BattleScreen;
import Screens.BoardScreen;
import Screens.ChooseClassScreen;
import Screens.HandScreen;
import Screens.NewGameScreen;
import Screens.ShopScreen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameTest extends Game {
	public static NewGameScreen newGameScreen;
	public static ChooseClassScreen chooseClassScreen;
	public BoardScreen boardScreen;
	public BattleScreen battleScreen;
	public ShopScreen shopScreen;
	public HandScreen handScreen; 
	public GameBoard board = new GameBoard();
	
	
	@Override
	public void create () {
		newGameScreen = new NewGameScreen(this);
		chooseClassScreen = new ChooseClassScreen(this);
		boardScreen = new BoardScreen(this);
		battleScreen = new BattleScreen(this);
		shopScreen = new ShopScreen(this);
		handScreen = new HandScreen(this);
		
		setScreen(newGameScreen);
		
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 5, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		super.render();
	}
	
	
}


