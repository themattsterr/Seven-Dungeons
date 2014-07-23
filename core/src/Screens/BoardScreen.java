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
import com.mygdx.game.Dice;
import com.mygdx.game.GameBoard;
import com.mygdx.game.GameTest;

public class BoardScreen implements Screen, GestureListener {
	
	private Stage stage;
	static final int WIDTH = 1080;
	static final int HEIGHT = 720;
	static boolean initial = false;
	private GameBoard board; 
	private HumanCharacter[] players;
	
	private int turn = 0;
	
	

	
	private Viewport viewport;
	private OrthographicCamera camera; 
	
	
	float x =  WIDTH / 2;
	float y = HEIGHT /2;

	private GameTest game;
	
	int testPos = 0;
	
	private Dock dock;
	private Stage dockStage;
	private InputMultiplexer multiplexer;
	private Dice dice;
	
	public class MyActor extends Actor{
		public Texture texture = new Texture("board.png");
		public void draw(Batch batch, float alpha){
			batch.draw(texture, 0, 0);
		}
	}
	
	
	
	public BoardScreen(GameTest game) {
		this.game = game;
		this.board = game.board;
		
	}


	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    
		
		stage.act(Gdx.graphics.getDeltaTime());
		dockStage.act(Gdx.graphics.getDeltaTime());
	   
	    stage.draw();
	    dockStage.draw();
	    
	    Table.drawDebug(dockStage);
	    
	    checkMax();
	    
	    
	    camera.position.set(x, y,0);
	 
	
	
	    camera.update();
	 
	    if((Gdx.input.getAccelerometerX()  > 3) && (Gdx.input.getAccelerometerY() > 3)){
	    	players[turn++].move();
	    	turn %= 4;
	 }
	
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		//viewport.update(width, height);
	}

	@Override
	public void show() {
		if(initial == false){
			// TODO Auto-generated method stub
			camera = new OrthographicCamera();
			viewport = new ScreenViewport();
			stage = new Stage(new FitViewport(WIDTH,HEIGHT,camera));
			
			
			MyActor boardPic = new MyActor();
			
			players = new HumanCharacter[4];
			players[0] = new Assassin(board);
			players[1] = new Mage(board);
			players[2] = new Knight(board);
			players[3] = new Archer(board);
			
			stage.addActor(boardPic);
	
			for(int i = 0; i < 4; i++){
				stage.addActor(players[i]);
			}
			
			dockStage = new Stage(new FitViewport(WIDTH,HEIGHT));
			dock = new Dock(WIDTH,HEIGHT);
			dockStage.addActor(dock);
			dock.show();
			
			
			dice = new Dice();
			dockStage.addActor(dice);
			dice.setPosition(400, 50);
			dice.setSize(80, 80);
			dice.roll();
			
			multiplexer = new InputMultiplexer();
			multiplexer.addProcessor(dockStage);
			multiplexer.addProcessor(new GestureDetector(this));
			
			Gdx.input.setInputProcessor(multiplexer);
			initial = true;
		}
		
	
		 
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
		stage.dispose();
		dockStage.dispose();
	}



	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean tap(float x, float y, int count, int button) {
		dice.roll();
		
		while(!players[turn++].move());
		turn %= 4;
		return false;
	}



	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
	
	game.setScreen(game.battleScreen);
	game.battleScreen.setBattle(players[turn], players[(turn + 1) % 4]);
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
	

 public void checkMax(){
	 
	 if (camera.zoom >3) camera.zoom = 3;
	 if (camera.zoom < .5) camera.zoom = 0.5f;
	 if(y < HEIGHT / 2) y = HEIGHT / 2;
	 if (x < WIDTH / 2) x = WIDTH /2;
	 
	 if(y > 1509.0) y =  1509;
	 if (x > 2665.0) x = 2665;
 }


 
 
}
