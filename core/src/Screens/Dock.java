package Screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

public class Dock extends WidgetGroup implements ActionListener {
	
	private int dockWidth;
	private int dockHeight;
	
	private Texture texture;
	private Texture iconTexture;
	private TextureRegion region;
	private TextureRegion healthRegion;
	private TextureRegion attackRegion;
	private TextureRegion defenseRegion;
	private TextureRegion goldRegion;
	
	private Table infoTable;
	private LabelStyle style;
	private BitmapFont font;
	
	private Image currentPlayer;
	private Integer health;
	private Integer attack;
	private Integer defense;
	private Integer gold;
	
	private Table arrowTable;
	private TextureAtlas buttonAtlas;
	private Skin skin;
	
	
	private ImageButtonStyle upArrowStyle;
	private ImageButtonStyle leftArrowStyle;
	private ImageButtonStyle downArrowStyle;
	private ImageButtonStyle rightArrowStyle;
	private ImageButton upArrowButton;
	private ImageButton leftArrowButton;
	private ImageButton downArrowButton;
	private ImageButton rightArrowButton;
	
	private ImageButtonStyle pauseStyle;
	private ImageButton pauseButton;
	
	private ImageButtonStyle cardsStyle;
	private ImageButton cardsButton;
	
	public Dock(int width, int height) {

		dockWidth = width;
		dockHeight = height/5;
		
		this.setPosition(0, 0);
		this.setSize(dockWidth, dockHeight);
		
		this.create();
		
	}
	
	private void create() {
		
		texture = new Texture("docktex.png");
		region = new TextureRegion(texture, 0, 0, dockWidth, dockHeight);
		
		
		iconTexture = new Texture("playericons.png");
		healthRegion = new TextureRegion(iconTexture, 0, 0, 180, 180);
		attackRegion = new TextureRegion(iconTexture, 180, 0, 180, 180); 
		defenseRegion = new TextureRegion(iconTexture, 360, 0, 180, 180);
		goldRegion = new TextureRegion(iconTexture, 540, 0, 180, 180);
		
		font = new BitmapFont(Gdx.files.internal("exo-small.fnt"), false);
		style = new LabelStyle(font, Color.BLACK);
		
		skin = new Skin();
		buttonAtlas = new TextureAtlas("buttons/button.pack");
		skin.addRegions(buttonAtlas);
				
		upArrowStyle = new ImageButtonStyle();
		upArrowStyle.up = skin.getDrawable("uparrow");
		upArrowStyle.disabled = skin.getDrawable("uparrowdisabled");
		upArrowButton = new ImageButton(upArrowStyle);
		upArrowButton.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("up arrow pressed");
				upArrowButton.setDisabled(!upArrowButton.isDisabled());
				addPlayerGold(10);
				return true;
			}
		});
		
		
		leftArrowStyle = new ImageButtonStyle();
		leftArrowStyle.up = skin.getDrawable("leftarrow");
		leftArrowButton = new ImageButton(leftArrowStyle);
		leftArrowButton.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("left arrow pressed");
				addPlayerGold(-1);
				return true;
			}
		});
		
		downArrowStyle = new ImageButtonStyle();
		downArrowStyle.up = skin.getDrawable("downarrow");
		downArrowButton = new ImageButton(downArrowStyle);
		downArrowButton.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("down arrow pressed");
				addPlayerGold(-10);
				return true;
			}
		});
		
		rightArrowStyle = new ImageButtonStyle();
		rightArrowStyle.up = skin.getDrawable("rightarrow");
		rightArrowButton = new ImageButton(rightArrowStyle);
		rightArrowButton.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("right arrow pressed");
				addPlayerGold(1);
				return true;
			}
		});
		
		pauseStyle = new ImageButtonStyle();
		pauseStyle.up = skin.getDrawable("pauseicon");
		pauseButton = new ImageButton(pauseStyle);
		pauseButton.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("pause button pressed");
				return true;
			}
		});
		
		cardsStyle = new ImageButtonStyle();
		cardsStyle.up = skin.getDrawable("cardicon");
		cardsButton = new ImageButton(cardsStyle);
		cardsButton.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("cards button pressed");
				return true;
			}
		});
	}
	
	public void show() {
		
		//
		//
		updatePlayerInfo(10,7,5,100);
		//
		//all below added to updatePlayerInfo
		//
		//infoTable = createInfoTable(dockWidth/2, dockHeight);
		//infoTable = createInfoTable(400, 100);
		//infoTable.setPosition(800, 50);
		//infoTable.debug();
		//this.addActor(infoTable);
		
		arrowTable = createArrowTable(350);
		arrowTable.setPosition(200, 74);
		arrowTable.debug();
		this.addActor(arrowTable);
		

	}
	
	
	private Table createArrowTable(float width) {
		
		/// Only width passed in because height is based on width, height will be 2/5 of the width
		
		float cellWidth = width/5;
		
		Table table = new Table();
		
		table.add(pauseButton).size(cellWidth, cellWidth);
		table.add(cardsButton).size(cellWidth, cellWidth);;
		
		Table left = new Table();
		left.add(leftArrowButton).size(cellWidth, cellWidth);
		table.add(left);
		
		Table center = new Table();
		center.add(upArrowButton).size(cellWidth, cellWidth);
		center.row();
		center.add(downArrowButton).size(cellWidth, cellWidth);
		table.add(center);
		
		Table right = new Table();
		right.add(rightArrowButton).size(cellWidth, cellWidth);
		table.add(right);
		
		//table.setFillParent(true);
		
		left.debug();
		center.debug();
		right.debug();
		return table;
	}
	
	private Table createInfoTable(float width, float height) {
		
		float cellWidth = width/4;
		float imageSize = height/2;
		
		Table table = new Table();
		
		Table healthTable = new Table();
		Image healthImage = new Image(healthRegion);
		healthTable.add(healthImage).size(imageSize, imageSize).expand();
		healthTable.row();
		healthTable.add(new Label(health.toString(), style)).expand();
		table.add(healthTable).size(cellWidth, height);

		Table attackTable = new Table();
		Image attackImage = new Image(attackRegion);
		attackTable.add(attackImage).size(imageSize, imageSize).expand();
		attackTable.row();
		attackTable.add(new Label(attack.toString(), style)).expand();
		table.add(attackTable).size(cellWidth, height);
		
		Table defenseTable = new Table();
		Image defenseImage = new Image(defenseRegion);
		defenseTable.add(defenseImage).size(imageSize, imageSize).expand();
		defenseTable.row();
		defenseTable.add(new Label(defense.toString(), style)).expand();
		table.add(defenseTable).size(cellWidth, height);
		
		Table goldTable = new Table();
		Image goldImage = new Image(goldRegion);
		goldTable.add(goldImage).size(imageSize, imageSize).expand();
		goldTable.row();
		goldTable.add(new Label(gold.toString(), style)).expand();
		table.add(goldTable).size(cellWidth, height);
		
		//table.setFillParent(true);
		
		healthTable.debug();
		attackTable.debug();
		defenseTable.debug();
		goldTable.debug();
		return table;
	}
	
	public void updatePlayerInfo(int health, int attack, int defense, int gold){
		this.health = health;
		this.attack = attack;
		this.defense = defense;
		this.gold = gold;
		
		if(this.getChildren().contains(infoTable, true))
			this.removeActor(infoTable);
		infoTable = createInfoTable(400, 100);
		infoTable.setPosition(800, 50);
		infoTable.debug();
		this.addActor(infoTable);
	}
	
	public boolean addPlayerGold(int gold) {
		int newGold;
		
		newGold = this.gold + gold;
		
		if(newGold < 0)
			return false;
		else
			this.gold = newGold;
		
		updatePlayerInfo(this.health, this.attack, this.defense, this.gold);
		
		return true;
	}
	
	public void draw(Batch batch, float alpha) {
		
		batch.draw(region, this.getX(), this.getY());
		
		this.drawChildren(batch, alpha);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
