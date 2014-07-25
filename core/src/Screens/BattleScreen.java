package Screens;

import regularClases.HumanCharacter;
import regularClases.Player;
import Screens.ShopScreen.Back;

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
import com.badlogic.gdx.scenes.scene2d.Stage;
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
	
	private Texture iconTexture;
	private TextureRegion healthRegion;
	private TextureRegion attackRegion;
	private TextureRegion defenseRegion;
	private TextureRegion goldRegion;
	
	private Image fighterImage;
	private Image defenderImage;
	
	private LabelStyle labelStyle;
	private BitmapFont font;
	
	Dice dice;
	
	HumanCharacter fighter;
	Player defender;
	
	Sound music;
	public BattleScreen() {
		// TODO Auto-generated constructor stub
		iconTexture = new Texture("playericons.png");
		healthRegion = new TextureRegion(iconTexture, 0, 0, 180, 180);
		attackRegion = new TextureRegion(iconTexture, 180, 0, 180, 180); 
		defenseRegion = new TextureRegion(iconTexture, 360, 0, 180, 180);
		goldRegion = new TextureRegion(iconTexture, 540, 0, 180, 180);
		

		
		font = new BitmapFont(Gdx.files.internal("exo-small.fnt"), false);
		labelStyle = new LabelStyle(font, Color.BLACK);
		
		stage = new Stage(new FitViewport(WIDTH,HEIGHT), SevenDungeons.batch);
		background = new Back();
		
		battleTable = new Table();
		middleTable = new Table();
		fighterTable = new Table();
		defenderTable = new Table();
		
		dice = new Dice();
		
	
	}
	
	public class Back extends Actor{
		public Texture texture = new Texture("background_table.png");
		public void draw(Batch batch, float parentAlpha){
			//batch.begin();
			batch.draw(texture, 0, 0,WIDTH,HEIGHT);
			//batch.end();
		}
	}


	public void setBattle(HumanCharacter player1, Player player2){
		fighter = player1;
		defender = player2;
		//music = Gdx.audio.newSound(Gdx.files.internal("sab.mp3"));
	//	music.play();
	}

	//THIS FUNCTION TAKES IN TWO FIGHTERS AND MAKES THE BATTLE 
		public void setBattle(HumanCharacter player1, HumanCharacter player2){
			fighter = player1;
			defender = player2;
			//music = Gdx.audio.newSound(Gdx.files.internal("sab.mp3"));
		//	music.play();
			//music.play();
			fight(player1, player2);
		}
		
		private void fight(HumanCharacter attacker, HumanCharacter defender){
			
			int diceNumb = (int)(Math.random() * 6);
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
			
			defender.takeHit(theAttack);
			
		}


	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//fighter.drawFighter();
		//defender.drawDefennder();
		
		
		
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
		
		float imageHeight = 100;
		
		fighterImage = new Image(fighter.getTexture());
		defenderImage = new Image(defender.getTexture());
		
		fighterTable = createInfoTable(this.fighter, stage.getWidth()/3, stage.getWidth()/6);
		defenderTable = createInfoTable(this.defender, stage.getWidth()/3, stage.getWidth()/6);
		
		
		
		battleTable.add().size(stage.getWidth()/3, stage.getWidth()/3);
		battleTable.add(defenderTable).align(Align.top);
		battleTable.add(defenderImage).align(Align.top);
		battleTable.row();
		battleTable.add(fighterImage).align(Align.bottom);
		battleTable.add(fighterTable).align(Align.bottom);
		battleTable.add().size(stage.getWidth()/3, stage.getWidth()/3);
		
		battleTable.setFillParent(true);
		
		stage.addActor(background);
		stage.addActor(battleTable);
		stage.addActor(dice);
		dice.setPosition(stage.getWidth()/2, stage.getHeight()/2);
		dice.setSize(40, 40);
		
		
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
		Integer health = player.getCurrentHealth();
		Integer attack = player.getAttack();
		Integer defense = player.getDefense();
		
		Table table = new Table();
		table.setWidth(width);
		table.setHeight(height);
		
		Table healthTable = new Table();
		Image healthImage = new Image(healthRegion);
		healthTable.add(healthImage).size(imageSize, imageSize).expand();
		healthTable.row();
		healthTable.add(new Label(health.toString(), labelStyle)).expand();
		table.add(healthTable).size(cellWidth, height);

		
		Table attackTable = new Table();
		Image attackImage = new Image(attackRegion);
		attackTable.add(attackImage).size(imageSize, imageSize).expand();
		attackTable.row();
		attackTable.add(new Label(attack.toString(), labelStyle)).expand();
		table.add(attackTable).size(cellWidth, height);
		
		Table defenseTable = new Table();
		Image defenseImage = new Image(defenseRegion);
		defenseTable.add(defenseImage).size(imageSize, imageSize).expand();
		defenseTable.row();
		defenseTable.add(new Label(defense.toString(), labelStyle)).expand();
		table.add(defenseTable).size(cellWidth, height);
		
		
		//table.setFillParent(true);
		
		healthTable.debug();
		attackTable.debug();
		defenseTable.debug();
		return table;
	}

}
