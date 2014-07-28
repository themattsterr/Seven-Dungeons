package com.mygdx.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Dice extends Actor implements ActionListener {
	
	private Random rand = new Random();
	private int value;
	
	private Texture texture;
	private TextureRegion[] regions;
	private TextureRegion currentRegion;
	

	//dice
	public Dice(float width, float height) {
		
		texture = new Texture("die.png");
		regions = new TextureRegion[6];
		regions[0] = new TextureRegion(texture,138,138,138,138);
		regions[1] = new TextureRegion(texture,138,414,138,138);
		regions[2] = new TextureRegion(texture,276,414,138,138);
		regions[3] = new TextureRegion(texture,138,0,138,138);
		regions[4] = new TextureRegion(texture,0,414,138,138);
		regions[5] = new TextureRegion(texture,138,414,138,138);
		
		value = roll();
		
		this.setSize(width, height);
		
		this.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("dice clicked");
				if (SevenDungeons.boardScreen.move == false 
						&& SevenDungeons.boardScreen.rolled == false) {
		    		SevenDungeons.boardScreen.roll = SevenDungeons.getPlayer().rollDice(SevenDungeons.boardScreen.dock);
		    		SevenDungeons.boardScreen.rolled = true;
		    		SevenDungeons.boardScreen.dock.show();
				}
				return true;
			}
		});
				
	}
	
	public Dice(){
		texture = new Texture("die.png");
		regions = new TextureRegion[6];
		regions[0] = new TextureRegion(texture,138,138,138,138);
		regions[1] = new TextureRegion(texture,138,414,138,138);
		regions[2] = new TextureRegion(texture,276,414,138,138);
		regions[3] = new TextureRegion(texture,138,0,138,138);
		regions[4] = new TextureRegion(texture,0,414,138,138);
		regions[5] = new TextureRegion(texture,138,414,138,138);
		
		value = roll();
		
		this.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("dice clicked");
				if(SevenDungeons.game.getScreen() == SevenDungeons.boardScreen) {
					if (SevenDungeons.boardScreen.move == false 
							&& SevenDungeons.boardScreen.rolled == false) {
			    		SevenDungeons.boardScreen.roll = SevenDungeons.getPlayer().rollDice(SevenDungeons.boardScreen.dock);
			    		SevenDungeons.boardScreen.rolled = true;
			    		SevenDungeons.boardScreen.dock.show();
					}
				}
				if (SevenDungeons.game.getScreen() == SevenDungeons.battleScreen){
					if ((SevenDungeons.battleScreen.fought == false)){
						SevenDungeons.battleScreen.fought = true;
						if((SevenDungeons.battleScreen.turn % 2) == 0)
							SevenDungeons.battleScreen.fight(SevenDungeons.battleScreen.fighter, SevenDungeons.battleScreen.defender);
						else 
							SevenDungeons.battleScreen.fight(SevenDungeons.battleScreen.defender, SevenDungeons.battleScreen.fighter);
						
						System.out.println(" your health " + SevenDungeons.battleScreen.fighter.getCurrentHealth() + " their health + " + SevenDungeons.battleScreen.defender.getCurrentHealth());
						
						SevenDungeons.battleScreen.turn++;
					}
					SevenDungeons.battleScreen.refresh();
				}
				return true;
			}
		});
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
