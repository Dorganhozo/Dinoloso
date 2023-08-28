package dor.dinoloso;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;

public class GameOverScreen extends ScreenAdapter{
	private Texture gameOverImg;
	private SpriteBatch batch;
	
	@Override
	public void show(){
		// TODO: Implement this method
		super.show();
		batch = new SpriteBatch();
		gameOverImg = new Texture("game_over.png");
	}
	
	
	
	@Override
	public void render(float delta){
		super.render(delta);
		batch.begin();
		batch.draw(gameOverImg, 0, 0);
		batch.end();
	}

	@Override
	public void resize(int width,int height){
		super.resize(width, height);
	}

	
	
}
