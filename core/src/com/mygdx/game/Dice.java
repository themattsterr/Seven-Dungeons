package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Dice extends Actor {
	
	private Random rand = new Random();
	private int value;
	
	private Texture texture;
	private TextureRegion[] regions;
	private TextureRegion currentRegion;
	

	//dice
	public Dice() {
		
		
		texture = new Texture("die.png");
		regions = new TextureRegion[6];
		regions[0] = new TextureRegion(texture,138,138,138,138);
		regions[1] = new TextureRegion(texture,138,414,138,138);
		regions[2] = new TextureRegion(texture,276,414,138,138);
		regions[3] = new TextureRegion(texture,138,0,138,138);
		regions[4] = new TextureRegion(texture,0,414,138,138);
		regions[5] = new TextureRegion(texture,138,414,138,138);
		
		value = roll();
		
	}
	
	public int roll() {
		value = generateValue();
		currentRegion = regions[value-1];
		return value;
	}
	
	public void draw(Batch batch, float alpha) {
		batch.draw(currentRegion, this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
	
	private int generateValue() {
		return rand.nextInt(6) + 1;
	}

}
