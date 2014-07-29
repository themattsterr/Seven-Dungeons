package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;

import regularClases.HumanCharacter;


public abstract class Card extends Actor{
	
	private String text;
	 
	
	public static Skin skin = new Skin();
	public static TextureAtlas atlas = new TextureAtlas("buttons/card.pack");
		
	public ImageButtonStyle style;
	public ImageButton button;
	public int width = 200;
	public int height = 100;
	
	private boolean clicked = false;
	
	public void create(){
		skin.addRegions(atlas);
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public String getText(){
		return text;
		
	}
	
	public void setClicked(){
		clicked = !clicked;
	}
	
	public boolean getClicked(){
		return clicked;
	}
	
	public void setImageButton(String path){
		skin.addRegions(atlas);
		style = new ImageButtonStyle();
		style.up = skin.getDrawable(path);
		button = new ImageButton(style);
		button.setSize(width, height);
	}
	
	public abstract boolean activate(HumanCharacter active);
	
	public abstract Group getGroup();
	
	

}
