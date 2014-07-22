package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

public class Dock extends WidgetGroup {
	
	private int dockWidth;
	private int dockHeight;
	
	private Texture texture;
	private Texture iconTexture;
	private TextureRegion region;
	private TextureRegion healthRegion;
	private TextureRegion attackRegion;
	private TextureRegion defenseRegion;
	private TextureRegion goldRegion;
	
	private Table table;
	private LabelStyle style;
	private BitmapFont font;
	
	private Integer health;
	private Integer attack;
	private Integer defense;
	private Integer gold;
	
	public Dock(int width, int height) {
		
		dockWidth = width;
		dockHeight = height/5;
		
		texture = new Texture("docktex.png");
		//region = new TextureRegion(texture, 0, 0, dockWidth, dockHeight);
		// Texture I used is bad should be line above
		region = new TextureRegion(texture, 0, 70, dockWidth, dockHeight);
		
		this.setPosition(0, 0);
		this.setSize(dockWidth, dockHeight);
		
		iconTexture = new Texture("playericons.png");
		healthRegion = new TextureRegion(iconTexture, 0, 0, 180, 180);
		attackRegion = new TextureRegion(iconTexture, 180, 0, 180, 180); 
		defenseRegion = new TextureRegion(iconTexture, 360, 0, 180, 180);
		goldRegion = new TextureRegion(iconTexture, 540, 0, 180, 180);
		
		font = new BitmapFont(Gdx.files.internal("exo-small.fnt"), false);
		style = new LabelStyle(font, Color.BLACK);
		

		
	}
	
	public void show(Stage stage) {
	
		table = new Table();
		
		table.add().width(dockWidth/2);
		
		getPlayerInfo();
		
		Table healthTable = new Table();
		healthTable.add(new Image(healthRegion));
		healthTable.row();
		healthTable.add(new Label(health.toString(), style));
		table.add(healthTable);

		Table attackTable = new Table();
		attackTable.add(new Image(attackRegion));
		attackTable.row();
		attackTable.add(new Label(attack.toString(), style));
		table.add(attackTable);
		
		Table defenseTable = new Table();
		defenseTable.add(new Image(defenseRegion));
		defenseTable.row();
		defenseTable.add(new Label(defense.toString(), style));
		table.add(defenseTable);
		
		Table goldTable = new Table();
		goldTable.add(new Image(goldRegion));
		goldTable.row();
		goldTable.add(new Label(gold.toString(), style));
		table.add(goldTable);
		
		
		this.addActor(table);
		
		table.setFillParent(true);
		
		table.debugCell();
	}
	
	public void getPlayerInfo(){
		this.health = 10;
		this.attack = 5;
		this.defense = 7;
		this.gold = 100;
	}
	
	@Override
	public void draw(Batch batch, float alpha) {
		
		batch.draw(region, this.getX(), this.getY());
		
		this.drawChildren(batch, alpha);
		
		
	}
	
	
	
	
}
