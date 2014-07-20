package regularClases;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Character extends Actor {
	private int maxHealth;
	private int currentHealth;
	private int attack;
	private int defense;
	private Texture texture;
	public float xPos = 0;
	public float yPos = 0;
	Batch batch = new SpriteBatch();

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

}
