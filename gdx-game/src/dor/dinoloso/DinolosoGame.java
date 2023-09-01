package dor.dinoloso;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.viewport.*;
import java.util.*;
import dor.dinoloso.entity.*;
import dor.dinoloso.object.*;

public class DinolosoGame extends ApplicationAdapter{
	
	public static int SCORE, RECORD;
	
	private static float timePassed;

	private SpriteBatch batch;
	private Viewport viewPort;
	private OrthographicCamera camera;

	private BitmapFont font;
	private Texture gameOverSprite;
	private Texture retrySprite;

	private Dino player;
	private Ground ground;
	private Cactus cactus;


	@Override
	public void create(){
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		viewPort = new ExtendViewport(Config.WORLD_WIDTH, Config.WORLD_HEIGHT);

		font = new BitmapFont();
		font.setColor(Color.BLACK);
		font.setScale(.5f);
		
		gameOverSprite = new Texture("game_over.png");
		retrySprite = new Texture("try_again.png");
		
		camera.setToOrtho(false, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		
		reset();
	}

	@Override
	public void render(){
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		float delta = Gdx.graphics.getDeltaTime();
		
		timePassed += delta;
		
	    viewPort.apply();
	    camera.update();

	    batch.setProjectionMatrix(camera.combined);
		
	    ground.draw(batch);
	    cactus.draw(batch);
	    player.draw(batch);
		
		batch.begin();
		String scoreText = String.format("Recorde: %04d Pontos: %04d", RECORD, SCORE);
	    font.draw(batch, scoreText, Config.SCREEN_WIDTH*0.9f- font.getBounds(scoreText).width, Config.SCREEN_HEIGHT*0.9f);
	    batch.end();
		
		if(!player.isAlive()){
			batch.begin();
			batch.draw(gameOverSprite, Config.SCREEN_WIDTH/2 - gameOverSprite.getWidth()/2, Config.SCREEN_HEIGHT - gameOverSprite.getHeight());
			batch.end();
			
			if(timePassed-SCORE*.2 > 3){
				batch.begin();
				batch.draw(retrySprite, Config.SCREEN_WIDTH/2 - retrySprite.getWidth()/2, Config.SCREEN_HEIGHT - gameOverSprite.getHeight() * 2);
				batch.end();
			
				if(Gdx.input.justTouched())
					reset();
			}
		}else{
			
			update(delta);
		}
		
	}

	public void update(float delta){
		
		SCORE = (int)(timePassed/.2);
		
		
		ground.update(delta);
		cactus.update(delta);
		player.update(delta);
		
		if(Gdx.input.justTouched() & player.getRectangle().y == 32)
			player.realeseJump();
		else 
			if(player.getRectangle().y < 32)
			player.cancelJump();
	}

	public void reset(){
		timePassed = 0;
		RECORD = Math.max(SCORE, RECORD);
		SCORE = 0;
		ground = new Ground(0, 0, Gdx.graphics.getWidth(), 32);	
		player = new Dino(0, 32);
		cactus = new Cactus(Config.SCREEN_WIDTH, 32, player);
	}
	
	@Override
	public void dispose(){
		super.dispose();
		gameOverSprite.dispose();
		retrySprite.dispose();
		batch.dispose();
		player.dispose();
		ground.dispose();
		font.dispose();
	}

	@Override
	public void resize(int width, int height){
		super.resize(width, height);
		viewPort.update(width, height);
		ground = new Ground(0, 0, width, height);
	}

	@Override
	public void pause(){
		super.pause();
	}

	@Override
	public void resume(){
		super.resume();
	}

	public static float getTimePassed(){
		return timePassed;
	}
}
