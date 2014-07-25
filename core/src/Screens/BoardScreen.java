package Screens;

import regularClases.Archer;
import regularClases.Assassin;
import regularClases.HumanCharacter;
import regularClases.Knight;
import regularClases.Mage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.SevenDungeons;
import com.mygdx.game.Dice;
import com.mygdx.game.GameBoard;

public class BoardScreen implements Screen, GestureListener {
	
	//holds the actors
	private Stage boardStage;
	
	//initial screen size (samsung galaxy s5
	static final int WIDTH = Gdx.graphics.getWidth();
	static final int HEIGHT = Gdx.graphics.getHeight();
	//initialized already ?
	static boolean initial = false;
	private boolean move = false; 
	

	//camera
	private Viewport viewport;
	private OrthographicCamera camera; 
	
	//initial position of screen
	float x =  WIDTH / 2;
	float y = HEIGHT /2;
	
	private Back back;
	private BoardActor board;
	
	private Dock dock;
	private Stage dockStage;
	private InputMultiplexer multiplexer;
	//private Dice dice;
	
	
	public BoardScreen(){
		// stuff taken from show
		camera = new OrthographicCamera();
		viewport = new ScreenViewport();
		boardStage = new Stage(new FitViewport(WIDTH,HEIGHT,camera), SevenDungeons.batch);
		
		//creates the board 
		board = new BoardActor();
		back = new Back();
		
		// int dock and its own stage
		dockStage = new Stage(new FitViewport(WIDTH,HEIGHT), SevenDungeons.batch);
		dock = new Dock(WIDTH,HEIGHT);
	}
	
	//the game board
	public class BoardActor extends Actor{
		public Texture texture = new Texture("new_board.png");
		public void draw(Batch batch, float parentAlpha){
			//batch.begin();
			batch.draw(texture, 0, 0);
			//batch.end();
		}
	}
	
	public class Back extends Actor{
		public Texture texture = new Texture("background_table.png");
		public void draw(Batch batch, float parentAlpha){
			//batch.begin();
			batch.draw(texture, -500, -500);
			//batch.end();
		}
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
		//resets graphics
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    boardStage.act(Gdx.graphics.getDeltaTime());
		dockStage.act(Gdx.graphics.getDeltaTime());
	    
		//batch.draw(texture, 0, 0);
		
	   
	   //draws the stages
	    
		boardStage.draw();
	    dockStage.draw();
		
	    
	    
	    Table.drawDebug(dockStage);
	    
		//checks the cameras max bounsd 
	    checkMax();
	    
	    //sets the camera
	    camera.position.set(x, y,0);
	    camera.update();
	 
	    	//rolls the dice if the player shakes the game while holding down
	    if(move == false)
	    if((Gdx.input.getAccelerometerX() + Gdx.input.getAccelerometerY() + Gdx.input.getAccelerometerZ()) < 2){
	    	SevenDungeons.getPlayer().move();
	    	x = SevenDungeons.getPlayer().getX();
	    	y = SevenDungeons.getPlayer().getY();
	    	move = true;
	    }
	}
	

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		viewport.update(width, height);
	}

	@Override
	public void show() {


		
		boardStage.addActor(back);
		boardStage.addActor(board);
					
		//adds the actors to the screean
		for(int i = 0; i < SevenDungeons.getNumPlayers(); i++){
			boardStage.addActor(SevenDungeons.getPlayer(i));
		}
		
		x = SevenDungeons.getPlayer().getX() - 75;
    	y = SevenDungeons.getPlayer().getY() - 75;


		dockStage.addActor(dock);
		dock.show();
		
		multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(dockStage);
		multiplexer.addProcessor(new GestureDetector(this));
		
		Gdx.input.setInputProcessor(multiplexer);
	

		 
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
		boardStage.dispose();
	//	dockStage.dispose();
	}



	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	//changes turn if the player makes a quick tap
	public boolean tap(float x, float y, int count, int button) {
		SevenDungeons.changeTurn();
		 move = false;
		this.x = SevenDungeons.getPlayer().getX() - 75;
	    this.y = SevenDungeons.getPlayer().getY() - 75;
	  
		//dice.roll();

		return false;
	}



	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		camera.zoom = 3;
	return false;
	
	}



	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		 this.x-= deltaX;
		 this.y+= deltaY;
		 
		  
		 	
		 	
	
		  return false;
	
	}



	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	//pinch to zoom
	public boolean zoom(float initialDistance, float distance) {
		if(initialDistance > distance) camera.zoom+= .02;
		else camera.zoom-= .02;
		
		
		return true;
	}



	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//checks camera bounds 
	 public void checkMax(){
		 
		 if (camera.zoom >3) camera.zoom = 3;
		 if (camera.zoom < .5) camera.zoom = 0.5f;
		 if(y < HEIGHT / 2) y = HEIGHT / 2;
		 if (x < WIDTH / 2) x = WIDTH /2;
		 
		 if(y > 1509.0) y =  1509;
		 if (x > 2665.0) x = 2665;
	 }


 
 
}
