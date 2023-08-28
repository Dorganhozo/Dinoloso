package dor.dinoloso;
import dor.dinoloso.game.*;
import com.badlogic.gdx.graphics.*;
import java.util.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.*;

public final class Ground extends Row{
	
	@Override
	public void update(Vector2 position, float delta){
		if(position.x >= -object.getWidth())
			position.add(Config.SCROLL_VELOCITY * (1 + DinolosoGame.SCORE/object.getWidth()), 0);
		else
			position.x = getWidth() - object.getWidth();
	}	

	private static class CustomObject extends GameObject{
		@Override
		public void update(float delta){}

		public CustomObject(){
			super(new Texture("ground.png"), new Vector2(0, -32), 32, 32); 
		}
	}
	
	public Ground(){
		super(new CustomObject(), Gdx.graphics.getWidth()/32);
	}
}
