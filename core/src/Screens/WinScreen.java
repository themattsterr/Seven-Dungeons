package Screens;

import regularClases.HumanCharacter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WinScreen implements Screen{

	HumanCharacter winner;
	private Batch win;
	
	public WinScreen(HumanCharacter winner){
		this.winner = winner;
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		win.begin();
		win.draw(winner.getTexture(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		win.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		win = new SpriteBatch();
		
	
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
