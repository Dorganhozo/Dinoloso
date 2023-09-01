package dor.dinoloso.object;
import com.badlogic.gdx.graphics.*;
import java.util.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.*;
import dor.dinoloso.*;

public final class Ground{
	
	private Texture texture;
	
	private Vector2[] positions;
	
	private Rectangle rect;
	
	public void draw(SpriteBatch batch){
		for(Vector2 position : positions){
			batch.begin();
			batch.draw(texture, position.x, position.y);
			batch.end();
			
		}
	}
	
	public Rectangle getRectangle(){
		return new Rectangle(rect);
	}
	
	public void update(float delta){
		for(Vector2 position : positions){
			position.x+=Config.INITIAL_VELOCITY;
		
			if(rect.x - texture.getWidth() > position.x)
				position.x = rect.getWidth() - texture.getWidth();
		}
	}

	public void dispose(){
		texture.dispose();
	}
	
	public Ground(float x, float y, float width, float height){
		texture = new Texture("ground.png");
		rect = new Rectangle(x, y, width, height);
		
		positions = new Vector2[(int)(rect.getWidth()/texture.getWidth())];
		
		for(int i=0; i < positions.length; i++){
			positions[i] = new Vector2(i * texture.getWidth() + x, y);
		}
	}
	
}
