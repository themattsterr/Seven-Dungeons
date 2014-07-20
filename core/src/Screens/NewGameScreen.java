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
import com.mygdx.game.GameTest;

public class NewGameScreen implements Screen{

	
	static int width;
    static int height;
	GameTest game;
	private Batch batch;
	private Texture img;
	Vector3 position;
	
	 private OrthographicCamera  cam;
	 
	 
	public NewGameScreen(GameTest game) {
		// TODO Auto-generated constructor stub
		this.game = game;
	}

	

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// TODO Auto-generated method stub
		if(Gdx.input.justTouched()){
			game.setScreen(game.chooseClassScreen);
			//dispose();
			
		
		} 
		
		
		
		
		position.x += Gdx.input.getPitch() * .2;
		position.y -= Gdx.input.getRoll() * .2 ;
		cam.position.set(position);
		batch.setProjectionMatrix(cam.combined);
		cam.update();
		
		batch.begin();
		batch.draw(img,0,0);
		batch.end(); 
		
		
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		batch = new SpriteBatch();
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		
		img = new Texture("bluespider.png");
		cam = new OrthographicCamera(width, height ); 
		position = new Vector3(width/2, height /2, 0);
		cam.position.set(position);
		
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
