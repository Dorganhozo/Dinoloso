package dor.dinoloso.object;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import java.util.*;
import com.badlogic.gdx.graphics.g2d.*;
import dor.dinoloso.entity.*;
import dor.dinoloso.*;

public class Cactus{
	
	private Dino dino;
	private Random random = new Random();
	private Texture texture;
	private Rectangle[] rects;
	
	private Rectangle lastRect;
	
	public void draw(SpriteBatch batch){
		for(Rectangle rect : rects){
			batch.begin();
			batch.draw(texture, rect.x, rect.y);
			batch.end();
		}
	}
	
	public void update(float delta){
		for(Rectangle rect : rects){
			
			Rectangle dinoMask = dino.getMask();
			Rectangle cactusMask = getMask(rect);
			
			if(Intersector.intersectRectangles(dinoMask, cactusMask, dinoMask))
				dino.setIsAlive(false);
		
			rect.x += Config.INITIAL_VELOCITY;
				
			if(
				random.nextInt(100)==1 &
				rect.x + rect.width < 0 &
				lastRect.x < (Config.SCREEN_WIDTH - cactusMask.width)
			)
			{
				rect.x = Config.SCREEN_WIDTH;
				lastRect = rect;
			}
			
		}
	}
	
	private Rectangle getMask(Rectangle rect){
		return new Rectangle(rect.x, rect.y+5, rect.width, rect.height-5);
	}

	public void dispose(){
		texture.dispose();
	}

	@Override
	public String toString(){
		// TODO: Implement this method
		return String.format("%s, %s, %s", rects);
	}

	
	
	public Cactus(float x, float y, Dino dino){
		this.dino = dino;
		texture = new Texture("cactus.png");
		rects = new Rectangle[3];

		for(int i=0; i < rects.length; i++){
			rects[i] = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
		}
		
		lastRect = rects[rects.length-1];
	}
	
}
