package Screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import Screens.HandScreen.Back;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Card;
import com.mygdx.game.ItemCard;
import com.mygdx.game.SevenDungeons;

public class ShopScreen implements Screen, GestureListener, ActionListener{
	
	static float screenWidth = Gdx.graphics.getWidth();
	static float screenHeight = Gdx.graphics.getHeight();
	
	private Stage stage;
	private Back background;
	
	private Table buttonTable;
	
	public static Skin skin = new Skin();
	public static TextureAtlas atlas = new TextureAtlas("buttons/button.pack");
	
	private ImageButtonStyle exitStyle;
	private ImageButton exitButton;

	public List<ItemCard> inventory;
	
	public List<ItemCard> activeCards = new ArrayList<ItemCard>();

	public ShopScreen() {
		// TODO Auto-generated constructor stub
		inventory = new ArrayList<ItemCard>();
		
		stage = new Stage(new FitViewport(screenWidth,screenHeight), SevenDungeons.batch);
		background = new Back();
		
		buttonTable = new Table();
		
		skin.addRegions(atlas);
		
		exitStyle = new ImageButtonStyle();
		exitStyle.up = skin.getDrawable("exit");
		
		exitButton = new ImageButton(exitStyle);
		exitButton.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("exit button pressed");
				exitShop();
				SevenDungeons.game.setScreen(SevenDungeons.boardScreen);
				return true;
			}
		});

	}
	
	public void setInventory(List<ItemCard> cards) {
		this.inventory = cards;
		
	}
	
	
	public class Back extends Actor{
		public Texture texture = new Texture("shop_screen3.png");
		public void draw(Batch batch, float parentAlpha){
			batch.draw(texture, 0, 0,screenWidth,screenHeight);
		}
	}
	
	public void exitShop(){
		while (activeCards.size() > 0){
			ItemCard current = activeCards.get(0);
			current.purchase(SevenDungeons.getPlayer());
			if (current.isSpell)
				SevenDungeons.getPlayer().giveCard(current);
			activeCards.remove(0);
			
		}
	}
	
	public  void removeFromShop(Group cardGroup){
		
		for (int i = 0; i < 6; i++){
			
			if (cardGroup.equals(inventory.get(i).getGroup())){
				if(!inventory.get(i).purchased) {
					System.out.println(i);
					if (inventory.get(i).canPurchase(SevenDungeons.getPlayer())){
						inventory.get(i).purchased = true;
						cardGroup.setVisible(false);
						activeCards.add(inventory.get(i));
					}
					break;
				}

			}
				
		}
		
		

		
		
		
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
		//inventory.get(0).hit(Gdx.input.setCursorPosition(x, y);)
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		stage.getViewport().update(width,height);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		buttonTable = new Table();
		
		for(int i = 0; i < 6; i++) {
			Card curCard = inventory.get(i);
			//buttonTable.add(curCard.getGroup()).size(curCard.width, curCard.height);
			buttonTable.add(curCard.getGroup()).size(curCard.width, curCard.height);
			buttonTable.add().size(10);
			if (i == 2){
				buttonTable.row().height(10);
				buttonTable.add().size(60);
				buttonTable.row().height(10);
			}
		}
				
		stage.addActor(background);
		stage.addActor(buttonTable);
		
		
		buttonTable.setPosition(screenWidth/2, screenHeight/2 - 20);
		
		
		stage.addActor(exitButton);
		exitButton.setSize(50, 50);
		exitButton.setPosition(100, screenHeight - exitButton.getHeight() - 100);
		
		stage.draw();
		
		Gdx.input.setInputProcessor(stage);
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

}
