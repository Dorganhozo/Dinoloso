package dor.dinoloso.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import dor.dinoloso.utils.*;
import dor.dinoloso.*;
import java.util.*;

public abstract class Row{
	protected GameObject object;
	private Vector2[] positions;
	protected float currentTime;
	
	
	public abstract void update(Vector2 position, float delta)

	public void render(SpriteBatch batch){
		TextureRegion currentFrame = object.animation.getKeyFrame(currentTime, true);

		float delta = Gdx.graphics.getDeltaTime();

		for(Vector2 position : positions){
			batch.begin();
			batch.draw(currentFrame, position.x, position.y);
			if(!DinolosoGame.isGameOver)
				update(position, delta);
			batch.end();
			
			
		}
	}

	public boolean isCollided(GameObject entity){
		for(Vector2 position : positions)
			if(GameUtils.isCollided(position.x, position.y, position.x+object.getWidth(), position.y+object.getHeight(), 
					entity.position.x, entity.position.y, entity.position.x+entity.width, entity.position.y+entity.height))
					return true;
					
		return false;
	}
	
	
	
	public int getWidth(){
		return positions.length*object.width;
	}
	
	public int getHeight(){
		return object.getHeight();
	}

	public void dispose(){
		object.dispose();
	}

	public Row(final GameObject object, int n){
		this.object = object;
		if(n > 0){
			positions = new Vector2[n];
		

			for(int i=0; i < positions.length; i++)
				positions[i] = new Vector2(i*object.width+object.position.x, object.position.y);
		}
		else
			positions = new Vector2[]{
				object.getPosition()
			};
	}
}
