package dor.dinoloso;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.viewport.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;

public class PlayScreen extends ScreenAdapter{
	private SpriteBatch batch;
	private Viewport viewPort;
	private OrthographicCamera camera;
	
	private BitmapFont font;

	private Dino player;
	private Ground ground;
	private Cactus cactus;


	@Override
	public void show(){
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		viewPort = new ExtendViewport(Config.WORLD_WIDTH, Config.WORLD_HEIGHT);
		
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		camera.setToOrtho(false, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		
		ground = new Ground();	
		player = new Dino();
		cactus = new Cactus();
	}

	@Override
	public void render(float delta){
	    batch.begin();
	    font.draw(batch, DinolosoGame.SCORE+"", player.getPosition().x, player.getPosition().y + 64);
	    batch.end();
		
	    viewPort.apply();
	    camera.update();

	    batch.setProjectionMatrix(camera.combined);
		
	    camera.position.y = (player.getPosition().y - camera.position.y) * 0.2f + 64;
		
	    
	    ground.render(batch);
	    cactus.render(batch);
	    player.render(batch);

		check();
	}

	private void check(){
		
		if(cactus.isCollided(player))
			DinolosoGame.isGameOver = true;
	}
	
	@Override
	public void dispose(){
		super.dispose();
		batch.dispose();
		player.dispose();
		ground.dispose();
		font.dispose();
	}

	@Override
	public void resize(int width, int height){
		super.dispose();
		viewPort.update(width, height);
		ground = new Ground();
		cactus = new Cactus();
	}

	@Override
	public void pause(){
		super.pause();
	}

	@Override
	public void resume(){
		super.resume();
	}
}
