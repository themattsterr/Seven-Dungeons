package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.SevenDungeons;

public class NewGameScreen implements Screen{

	
	static int width;
    static int height;
	//private static Batch batch;
	private static Texture img;
	 

	

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		// TODO Auto-generated method stub
		if(Gdx.input.justTouched()){

			if(Gdx.input.getY()  < Gdx.graphics.getHeight() / 2){
				SevenDungeons.game.setScreen(SevenDungeons.chooseClassScreen);
			}
			else {

				SevenDungeons.loadGame();
				SevenDungeons.game.setScreen(SevenDungeons.boardScreen);

			}
		
		} 
		
	
		SevenDungeons.batch.begin();
		SevenDungeons.batch.draw(img,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		SevenDungeons.batch.end(); 

		
		
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void show() {

		// TODO Auto-generated method stub
		//batch = new SpriteBatch();
		//batch = SevenDungeons.batch;
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		img = new Texture("7_dungeons_title_screen_small.jpg");
		

		
		
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
