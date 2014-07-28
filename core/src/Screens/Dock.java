package Screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import regularClases.HumanCharacter;

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
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.game.Dice;
import com.mygdx.game.SevenDungeons;

public class Dock extends WidgetGroup implements ActionListener {
	
	private int dockWidth;
	private int dockHeight;
	
	private Texture dockTexture;
	private TextureRegion dockRegion;
	
	private Table playerTable;

	
	/* 
	 * HOW TO BUTTONS:
	 * I use a Table to arrange them easily
	 * Just one TextureAtlas and Skin for the buttons is needed
	 */
	private Table arrowTable;
	private TextureAtlas buttonAtlas;
	private Skin skin;
	
	/*
	 *  You'll need these for each button
	 *  ButtonImageButtonStyle instance
	 *  ImageButton instance
	 */
	private ImageButtonStyle upArrowStyle;
	private ImageButtonStyle leftArrowStyle;
	private ImageButtonStyle downArrowStyle;
	private ImageButtonStyle rightArrowStyle;
	public ImageButton upArrowButton;
	public ImageButton leftArrowButton;
	public ImageButton downArrowButton;
	public ImageButton rightArrowButton;
	
	private ImageButtonStyle exitStyle;
	public ImageButton exitButton;
	
	private ImageButtonStyle cardsStyle;
	private ImageButton cardsButton;
	
	public Dice dice;
	
	public Dock(int width, int height) {

		dockWidth = width;
		dockHeight = height/5;
		
		this.setPosition(0, 0);
		this.setSize(dockWidth, dockHeight);
		
		this.create();
		
	}
	
	private void create() {
		
		dockTexture = new Texture("background_table.png");
		dockRegion = new TextureRegion(dockTexture, 0, 0, dockWidth, dockHeight);
		
		dice = new Dice(dockHeight/2, dockHeight/2);
		
		/*
		 * Skin constructor takes nothing
		 * TextureAtlas constructor takes in path to .pack file that 
		 * is made with gdx-texturepacker and changing run configurations on Eclipse
		 * and running TexturePacker.class its complicated for Mac
		 * or I can make it quickly with the individual textures
		 * 
		 * Add TextureAtlas to the Skin
		 */
		skin = new Skin(); //
		buttonAtlas = new TextureAtlas("buttons/button.pack");
		skin.addRegions(buttonAtlas);
				
		/*
		 * ImageButtonStyle has empty constructor
		 * .up is the default image for the button and uses texture from .pack
		 * .disabled is used when button is disabled  
		 * ImageButton constructor takes in the ImageButtonStyle instance
		 * addListener to the ImageButton instance I got this from a tutorial and it works
		 */
		upArrowStyle = new ImageButtonStyle();
		upArrowStyle.up = skin.getDrawable("uparrow");
		upArrowButton = new ImageButton(upArrowStyle);
		upArrowButton.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		
		
		leftArrowStyle = new ImageButtonStyle();
		leftArrowStyle.up = skin.getDrawable("leftarrow");
		leftArrowButton = new ImageButton(leftArrowStyle);
		leftArrowButton.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		
		downArrowStyle = new ImageButtonStyle();
		downArrowStyle.up = skin.getDrawable("downarrow");
		downArrowButton = new ImageButton(downArrowStyle);
		downArrowButton.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		
		rightArrowStyle = new ImageButtonStyle();
		rightArrowStyle.up = skin.getDrawable("rightarrow");
		rightArrowButton = new ImageButton(rightArrowStyle);
		rightArrowButton.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		
		exitStyle = new ImageButtonStyle();
		exitStyle.up = skin.getDrawable("exit");
		exitButton = new ImageButton(exitStyle);
		exitButton.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("end turn pressed");
				SevenDungeons.changeTurn();
				return true;
			}
		});
		
		cardsStyle = new ImageButtonStyle();
		cardsStyle.up = skin.getDrawable("cardicon");
		cardsButton = new ImageButton(cardsStyle);
		cardsButton.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("cards button pressed");
				SevenDungeons.openInventory();
				return true;
			}
		});
		
	}
	
	public void show() {
				
		if(this.getChildren().removeValue(arrowTable, false)){
			System.out.println("arrow table updated");
		}
		arrowTable = createArrowTable(dockHeight);
		arrowTable.setPosition(this.getCenterX(), this.getCenterY());
		arrowTable.debug();
		this.addActor(arrowTable);
		
		if(this.getChildren().removeValue(playerTable, false)){
			System.out.println("player table updated");
		}
		playerTable = createPlayerTable(SevenDungeons.getPlayer(),dockHeight);
		playerTable.setPosition(this.getOriginX(), this.getOriginY());
		playerTable.debug();
		this.addActor(playerTable);
		
		float buttonSize = dockHeight/2;
		Table buttonTable = new Table();
		buttonTable.add(dice).size(buttonSize, buttonSize);
		buttonTable.add().width(buttonSize/2);
		buttonTable.add(cardsButton).size(buttonSize, buttonSize);
		buttonTable.add().width(buttonSize/2);
		buttonTable.add(exitButton).size(buttonSize, buttonSize);
		
		buttonTable.setSize(4*buttonSize, buttonSize);
		buttonTable.setPosition(this.getOriginX() + dockWidth - buttonTable.getWidth(), this.getCenterY() - buttonTable.getHeight()/2);
		buttonTable.debug();
		this.addActor(buttonTable);
	}
	
	public void resize(int width, int height){
		dockWidth = width;
		dockHeight = height/5;
	}
	
	private Table createPlayerTable(HumanCharacter player, float height) {
		
		float imageSize = 50;
		
		if(this.getChildren().contains(playerTable, true))
			this.removeActor(playerTable);
		
		Table table = new Table();
		table.setHeight(height);
		Table infoTable = createInfoTable(player,200,74);
		
		Table innerTable = new Table();
		Image playerImage = new Image(player.getTexture());
		innerTable.add(playerImage).size(imageSize, imageSize);
		innerTable.add(infoTable);
		innerTable.debug();
		
		String labelText = "Player " + String.valueOf(SevenDungeons.turn + 1);
		
		table.add(new Label(labelText, SevenDungeons.labelStyle)).align(Align.left).height(height - infoTable.getHeight());
		table.row();
		table.add(innerTable);
		table.setWidth(imageSize + infoTable.getWidth());
		
		
		return table;
	}
	
	private Table createArrowTable(float height) {
		
		/// Only width passed in because height is based on width, height will be 2/5 of the width
		
		float cellWidth = height/2;
		
		Table table = new Table();
		
		
		
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
	
	private Table createInfoTable(HumanCharacter player, float width, float height) {
		
		float cellWidth = width/4;
		float imageSize = height/2;
		Integer health = player.getCurrentHealth();
		Integer attack = player.getAttack();
		Integer defense = player.getDefense();
		Integer gold = player.getGold();
		
		
		Image healthImage = new Image(SevenDungeons.healthRegion);
		Image attackImage = new Image(SevenDungeons.attackRegion);
		Image defenseImage = new Image(SevenDungeons.defenseRegion);
		Image goldImage = new Image(SevenDungeons.goldRegion);
		
		Table table = new Table();
		table.setWidth(width);
		table.setHeight(height);
		
		Table healthTable = new Table();
		healthTable.add(healthImage).size(imageSize, imageSize).expand();
		healthTable.row();
		healthTable.add(new Label(health.toString(), SevenDungeons.labelStyle)).expand();
		table.add(healthTable).size(cellWidth, height);

		Table attackTable = new Table();
		attackTable.add(attackImage).size(imageSize, imageSize).expand();
		attackTable.row();
		attackTable.add(new Label(attack.toString(), SevenDungeons.labelStyle)).expand();
		table.add(attackTable).size(cellWidth, height);
		
		Table defenseTable = new Table();
		defenseTable.add(defenseImage).size(imageSize, imageSize).expand();
		defenseTable.row();
		defenseTable.add(new Label(defense.toString(), SevenDungeons.labelStyle)).expand();
		table.add(defenseTable).size(cellWidth, height);
		
		Table goldTable = new Table();
		goldTable.add(goldImage).size(imageSize, imageSize).expand();
		goldTable.row();
		goldTable.add(new Label(gold.toString(), SevenDungeons.labelStyle)).expand();
		table.add(goldTable).size(cellWidth, height);
		
		//table.setFillParent(true);
		
		healthTable.debug();
		attackTable.debug();
		defenseTable.debug();
		goldTable.debug();
		return table;
	}
	
	public void draw(Batch batch, float alpha) {
		
		batch.draw(dockRegion, this.getX(), this.getY());
		
		this.drawChildren(batch, alpha);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
