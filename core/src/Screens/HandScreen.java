package Screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import regularClases.HumanCharacter;
import Screens.ShopScreen.Back;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Card;
import com.mygdx.game.ItemCard;
import com.mygdx.game.SevenDungeons;

public class HandScreen implements Screen, GestureListener, ActionListener{
	
	static final float WIDTH = Gdx.graphics.getWidth();
	static final float HEIGHT = Gdx.graphics.getHeight();
	
	private Stage stage;
	private Back background;
	
	private Table buttonTable;

	public static Skin skin = new Skin();
	public static TextureAtlas atlas = new TextureAtlas("buttons/button.pack");
	
	private ImageButtonStyle exitStyle;
	private ImageButton exitButton;
	
	private HumanCharacter player;
	public boolean hasPlayer = false;
	
	public HandScreen() {
		// TODO Auto-generated constructor stub
		
		stage = new Stage(new FitViewport(WIDTH,HEIGHT), SevenDungeons.batch);
		background = new Back();
		
		buttonTable = new Table();
		
		skin.addRegions(atlas);
		exitStyle = new ImageButtonStyle();
		exitStyle.up = skin.getDrawable("exit");
		
		exitButton = new ImageButton(exitStyle);
		exitButton.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("exit button pressed");
				SevenDungeons.game.setScreen(SevenDungeons.boardScreen);
				return true;
			}
		});

	}
	
	public void setPlayer(HumanCharacter player) {
		this.player = player;
		//debugHand();
	}
	
	private void debugHand(){
		for (int i = 1; i <= 3; i++) {
			ItemCard card = new ItemCard(i,i*5);
			card.create();
			player.giveCard(card);
		}
	}
	

	public class Back extends Actor{
		public Texture texture = new Texture("shop_screen2.png");
		public void draw(Batch batch, float parentAlpha){
			batch.draw(texture, 0, 0,WIDTH,HEIGHT);
		}
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
		
		stage.draw();
		Table.drawDebug(stage);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		buttonTable = new Table();
		
		int handCount = player.hand.size();
		for(int i = 0; i < handCount; i++) {
			Card curCard = player.hand.get(i);
			curCard.getGroup().setVisible(true);
			buttonTable.add(curCard.getGroup()).size(curCard.width, curCard.height);
		}
		
		stage.addActor(background);
		stage.addActor(buttonTable);
		buttonTable.setPosition(WIDTH/2, HEIGHT/2);
		
		stage.addActor(exitButton);
		exitButton.setSize(75, 75);
		exitButton.setPosition(100, HEIGHT - exitButton.getHeight() - 100);
		
		stage.draw();
		
		Gdx.input.setInputProcessor(stage);
		
	}
	
	public void useSpell(Group spellCard){
		
		if (!player.hand.isEmpty()){
			int handCount = player.hand.size();
			
			for (int i = 0; i < handCount; i++){
				Card current = player.hand.get(i);
				if (spellCard.equals(current.getGroup())){
					player.hand.remove(i);
					handCount--;
					current.getGroup().setVisible(false);
					// add whatever the spell does here
				}
			}
		}
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
