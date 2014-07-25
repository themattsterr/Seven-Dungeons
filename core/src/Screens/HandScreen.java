package Screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import Screens.ShopScreen.Back;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.SevenDungeons;

public class HandScreen implements Screen, GestureListener, ActionListener{
	
	static final float WIDTH = Gdx.graphics.getWidth();
	static final float HEIGHT = Gdx.graphics.getHeight();
	
	private Stage stage;
	private Back background;
	
	private Table buttonTable;
	private TextureAtlas buttonAtlas;
	private Skin skin;
	
	private List<ImageButtonStyle> cardStyles;
	private List<ImageButton> cardButtons;
	
	private ImageButtonStyle exitStyle;
	private ImageButton exitButton;
	

	public HandScreen() {
		// TODO Auto-generated constructor stub
		
		stage = new Stage(new FitViewport(WIDTH,HEIGHT), SevenDungeons.batch);
		background = new Back();
		
		
		skin = new Skin();
		buttonAtlas = new TextureAtlas("buttons/button.pack");
		skin.addRegions(buttonAtlas);
		
		buttonTable = new Table();
		
		cardStyles = new ArrayList<ImageButtonStyle>(3);
		cardButtons = new ArrayList<ImageButton>(3);
		
		for(int i = 0; i < 3; i++) {
			ImageButtonStyle style = new ImageButtonStyle();
			style.up = skin.getDrawable(getButtonImage(i));
			cardStyles.add(style);
			
			ImageButton button = new ImageButton(style);
			button.addListener(createButtonListener(i));
			
			cardButtons.add(button);
		}
		
		exitStyle = new ImageButtonStyle();
		exitStyle.up = skin.getDrawable("pauseicon");
		
		exitButton = new ImageButton(exitStyle);
		exitButton.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("exit button pressed");
				SevenDungeons.game.setScreen(SevenDungeons.boardScreen);
				return true;
			}
		});

	}
	
	private InputListener createButtonListener(int index) {
		
		InputListener listener;
		switch(index){
		case 0:
			listener =  new InputListener(){
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					System.out.println("card 0 button pressed");
					return true;
				}};
			break;
		case 1:
			listener =  new InputListener(){
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					System.out.println("card 1 button pressed");
					return true;
				}};
			break;
		case 2:
			listener =  new InputListener(){
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					System.out.println("card 2 button pressed");
					return true;
				}};
			break;
		default:
			listener = new InputListener();
			System.out.println("Error with card buttons");
			break;
		}
		return listener;
	}
	
	private String getButtonImage(int index) {
		String path;
		switch(index){
		case 0:
			path =  "cardicon";
			break;
		case 1:
			path =  "cardicon";
			break;
		case 2:
			path =  "cardicon";
			break;
		default:
			path =  "cardicon";
			break;
		}
		return path;
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
		
		
		for(int i = 1; i <= 3; i++) {
			buttonTable.add(cardButtons.get(i-1)).size(50, 50);
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
