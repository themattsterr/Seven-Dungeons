package regularClases;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.Card;
import com.mygdx.game.SevenDungeons;

public abstract class Player extends Actor {
	protected int maxHealth;
	protected int currentHealth;
	protected int attack;
	protected int defense;
	private Texture texture;
	public float xPos = 0;
	public float yPos = 0;
	Batch batch;
	public boolean isHuman;
	public ArrayList<Card> hand = new ArrayList<Card>();
	
	private boolean dead = false;
	private String status = "";


	public Player(String texture, int health, int attack, int defense, boolean human) {
		this.texture = new Texture(texture);
		this.batch = SevenDungeons.batch;
		this.maxHealth = this.currentHealth = health;
		this.attack = attack;
		this.defense = defense;
		this.isHuman = human;
	}

	public void draw(Batch batch, float alpha) {
		batch.draw(texture, xPos, yPos, 150, 150);
	}

	public void move(Vector2 vec) {
		xPos = vec.x;
		yPos = vec.y;
	}
	
	public void drawFighter(){
		
		batch.begin();
		batch.draw(texture, 0, 0, Gdx.graphics.getWidth() / 3 ,Gdx.graphics.getHeight() / 3);
		batch.end();
	}
	
	public void drawDefennder(){
		
		batch.begin();
		batch.draw(texture, Gdx.graphics.getWidth() * 2 / 3, Gdx.graphics.getHeight() * 2 / 3,Gdx.graphics.getWidth() / 3,Gdx.graphics.getHeight() / 3);
		batch.end();
	}

	public int getCurrentHealth(){
		return currentHealth;
	}
	
	
	public int getAttack(){
		return this.attack;
	}
	
	public int getDefense(){
		return this.defense;
	}
	
	public Texture getTexture(){
		return this.texture;
	}
	
	public void takeHit(int hit, Player attacker){
		if (hit > 0)
			currentHealth -= hit;
		if (currentHealth <= 0) {
			SevenDungeons.battleScreen.isFinished = true;
			SevenDungeons.battleScreen.removeDeadPlayerImage();
			this.death(attacker);
		}
	}
	
	public abstract void death(Player attacker);
	
	public abstract void giveGold(int value);
	
	public abstract int getGold();
	
	public abstract void recover();
	
	public void updateStat(Card card){
		
			// maxHealth += card.activateHealth();
		//	 attack += card.activateAttack();
		//	 defense += card.activateDefense();
		
	}
	
	public String getStatus(){
		return status;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public boolean isDead(){
		return dead;
	}
	
	public void setDead(boolean dead){
		this.dead = dead;
	}
	
	public void giveCard(Card card){
		hand.add(card);
	}
	
//test
}
