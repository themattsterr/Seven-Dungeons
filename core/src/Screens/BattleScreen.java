package Screens;

import regularClases.HumanCharacter;
import regularClases.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Dice;
import com.mygdx.game.SevenDungeons;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

public class BattleScreen implements Screen {

	static final float WIDTH = Gdx.graphics.getWidth();
	static final float HEIGHT = Gdx.graphics.getHeight();
	
	private Stage stage;
	private Back background;
	
	private Table battleTable;
	private Table middleTable;
	private Table fighterTable;
	private Table defenderTable;
	
	private Image fighterImage;
	private Image defenderImage;
	
	public int turn;
	public boolean fought = false;
	public boolean isFinished = false;
	
	public Dice dice;
	
	public HumanCharacter fighter;
	public Player defender;
	
	public static Skin skin = new Skin();
	public static TextureAtlas atlas = new TextureAtlas("buttons/button.pack");
	
	private ImageButtonStyle exitStyle;
	private ImageButton exitButton;
	
	public String battleStatus = "";
	public Label battleLabel;
	
	Sound music;
	public BattleScreen() {
		// TODO Auto-generated constructor stub
		
		stage = new Stage(new FitViewport(WIDTH,HEIGHT), SevenDungeons.batch);
		background = new Back();
		
		battleTable = new Table();
		middleTable = new Table();
		fighterTable = new Table();
		defenderTable = new Table();
		
		
		
		skin.addRegions(atlas);
		exitStyle = new ImageButtonStyle();
		exitStyle.up = skin.getDrawable("exit");
		
		exitButton = new ImageButton(exitStyle);
		exitButton.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				//if(SevenDungeons.randomDice.roll() > 4)SevenDungeons.game.setScreen(SevenDungeons.boardScreen);
				//else{ turn++; refresh(); }
				if((turn % 2) == 0)
					run(fighter, defender);
				else
					run(defender, fighter);
				return true;
			}
		});
	
	}
	
	public class Back extends Actor{
		public Texture texture = new Texture("background_table_noalpha.jpg");
		public void draw(Batch batch, float parentAlpha){
			//batch.begin();
			batch.draw(texture, 0, 0,WIDTH,HEIGHT);
			//batch.end();
		}
	}

	//THIS FUNCTION TAKES IN TWO FIGHTERS AND MAKES THE BATTLE 
		public void setBattle(HumanCharacter player1, Player player2){
			fighter = player1;
			defender = player2;
			turn = 0;

			
		}
		
		
		public void removeDeadPlayerImage(){
			if (turn%2 == 0){
				this.defenderImage.setVisible(false);
			}
			else {
				this.fighterImage.setVisible(false);
			}
			refresh();
		}
		
		
		public void run(Player runner, Player winner){
			// runner loses gold and gives to winner if battle finishes early
			if(!isFinished){
				int gold = (int)(runner.getGold()*.25);
				runner.giveGold(-gold);
				winner.giveGold(gold);
			}
			
			if (fighter.isDead())
				fighter.recover();
			
			if (defender.isDead())
				defender.recover();
			
			SevenDungeons.game.setScreen(SevenDungeons.boardScreen);
			SevenDungeons.boardScreen.setFocus(SevenDungeons.getPlayer().xPos, SevenDungeons.getPlayer().yPos);
		}
		
		public void fight(Player attacker, Player defender){
			
			int diceNumb = dice.roll();
			int  theAttack;
			
			
			switch(diceNumb){
			 		
			case 1: theAttack = (int) ((attacker.getAttack() - defender.getDefense() ) * 0);
					break;
			
			case 2:	theAttack = (int) ((attacker.getAttack() - defender.getDefense())*.50);
					break;
			
			case 3: theAttack = (int) ((attacker.getAttack() - defender.getDefense())*.50);
					break;
			
			case 4: theAttack = (int) ((attacker.getAttack() - defender.getDefense()));
					break;
			
			case 5: theAttack = (attacker.getAttack() - defender.getDefense());
					break;
			
			case 6: theAttack = (attacker.getAttack());
					break;
		    
			default: theAttack = 0;
					break;
			}
			
			if (theAttack <= 0)
				battleStatus = "No damage done";
			else
				battleStatus = theAttack + " damage done";
			
			defender.takeHit(theAttack, attacker);
			
			refresh();
		}
	

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//fighter.drawFighter();
		//defender.drawDefennder();
		
		
		// moved to dice class
		
		if(Gdx.input.isKeyPressed(Keys.D)){
			fought = false; 
		}
		stage.draw();
		
		//Table.drawDebug(stage);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	public void refresh(){
		
		battleTable.remove();
		dice.remove();
		battleLabel.remove();
		
		battleTable.remove();
		dice.remove();
		battleLabel.remove();
		
		battleLabel.setText(battleStatus);
		battleLabel.setPosition(stage.getWidth()/2 - 100, stage.getHeight()/2);
		
		dice.setPosition(stage.getWidth()/2, stage.getHeight()/2 - 100);
		dice.setSize(75, 75);
		
		if (turn > 4 || isFinished)
			exitButton.setVisible(true);
		
		if(turn %2 == 0){
			dice.setPosition(stage.getWidth() /2 , stage.getHeight() /2 - 100);
			exitButton.setPosition(WIDTH - exitButton.getWidth() - 75, 75);
		}
		else {
			if (!isFinished){
				dice.setPosition(stage.getWidth() / 2, stage.getHeight() / 2 + 65);
				exitButton.setPosition(75, HEIGHT - exitButton.getHeight() - 75);
				
				if (!defender.isHuman)
					exitButton.setVisible(false);
			}
		}
		
		
		fighterTable = createInfoTable(this.fighter, stage.getWidth()/3, stage.getWidth()/6);	
		defenderTable = createInfoTable(this.defender, stage.getWidth()/3, stage.getWidth()/6);
		
		battleTable = new Table();
		
		battleTable.add().size(stage.getWidth()/3, stage.getWidth()/3);
		battleTable.add(defenderTable).align(Align.top);
		battleTable.add(defenderImage).align(Align.top).expand();
		battleTable.row();
		battleTable.add(fighterImage).align(Align.bottom).expand();
		battleTable.add(fighterTable).align(Align.bottom);
		battleTable.add().size(stage.getWidth()/3, stage.getWidth()/3);
		
		battleTable.setFillParent(true);
		
		
		stage.addActor(battleTable);
		stage.addActor(battleLabel);
		stage.addActor(dice);
		
		if (isFinished)
			dice.setTouchable(Touchable.disabled);
		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub

		isFinished = false;
		fighterImage = new Image(fighter.getTexture());
		defenderImage = new Image(defender.getTexture());
		battleLabel = new Label(battleStatus, SevenDungeons.labelStyle);
		dice = new Dice(40,40);
		refresh();
		
		
		stage.addActor(background);
		stage.addActor(battleTable);
		stage.addActor(battleLabel);
		stage.addActor(dice);
		stage.addActor(exitButton);
		exitButton.setSize(75, 75);
		exitButton.setVisible(false);

		

		
		
		fighterTable.debug();
		defenderTable.debug();
		middleTable.debug();
		battleTable.debug();
		
		
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
		//music.dispose();
	}
	
	private Table createInfoTable(Player player, float width, float height) {
		
		float cellWidth = width/3;
		float imageSize = height/2;
		Integer health = new Integer(player.getCurrentHealth());
		Integer attack =  new Integer(player.getAttack());
		Integer defense = new Integer(player.getDefense());
		
		if(player.isDead()){
			health = 0;
			attack = 0;
			defense = 0;
		}
		
		Table table = new Table();
		table.setWidth(width);
		table.setHeight(height);
		
		Image healthImage = new Image(SevenDungeons.healthRegion);
		Image attackImage = new Image(SevenDungeons.attackRegion);
		Image defenseImage = new Image(SevenDungeons.defenseRegion);
		
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
		
		
		//table.setFillParent(true);
		
		healthTable.debug();
		attackTable.debug();
		defenseTable.debug();
		return table;
	}

}
