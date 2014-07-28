package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SevenDungeons;

public class ChooseClassScreen implements Screen{


	//private static Batch batch;
	private static Texture img;
	private int width; 
	private int height;
	
	public ChooseClassScreen() {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// TODO Auto-generated method stub
		if(Gdx.input.justTouched()){
			SevenDungeons.game.setScreen(SevenDungeons.boardScreen);
			dispose();
		}
		
		SevenDungeons.batch.begin();
		SevenDungeons.batch.draw(img,0,0, width, height);
		SevenDungeons.batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		this.width = width;
		this.height = height;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		img = new Texture("player1_class_select.png");
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		
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
		
	}

}
