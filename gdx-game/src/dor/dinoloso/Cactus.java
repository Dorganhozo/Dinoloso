package dor.dinoloso;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import dor.dinoloso.game.*;
import java.util.*;

public class Cactus extends Row{
	private Random random = new Random();
	@Override
	public void update(Vector2 position, float delta){
		if(random.nextInt(100) == 1 & position.x < 0)	
			position.x = Gdx.graphics.getWidth() - object.getWidth();

		if(!DinolosoGame.isGameOver & position.x > 0 & position.x + object.getWidth() < 32)
			DinolosoGame.SCORE++;	
		

		position.add(Config.SCROLL_VELOCITY * (1 + DinolosoGame.SCORE/object.getWidth()), 0);
		

	}

	private final static class CustomObject extends GameObject{
		@Override
		public void update(float delta){}

		public CustomObject(){
			super(new Texture("cactus.png"), new Vector2(Gdx.graphics.getWidth(), 0), 20, 29);
		}
	}

	public Cactus(){
		super(new CustomObject(), 3);
	}
}
