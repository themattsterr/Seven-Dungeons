package regularClases;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Card;

public abstract class Character extends Actor {
	private int maxHealth;
	private int currentHealth;
	private int attack;
	private int defense;
	private Texture texture;
	public float xPos = 0;
	public float yPos = 0;
	Batch batch = new SpriteBatch();
	ArrayList<Card> hand = new ArrayList<Card>();

	public Character(String texture, int health, int attack, int defense) {
		this.texture = new Texture(texture);
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
	public void takeHit(int hit){
		currentHealth -= hit;
	}

	public void updateStat(Card card){
		
		//	 maxHealth += card.activateHealth();
		//	 attack += card.activateAttack();
			// defense += card.activateDefense();
		
	}
	
	public void giveCard(Card card){
		hand.add(card);
	}
//test

}
