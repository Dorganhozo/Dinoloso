package dor.dinoloso.game;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.*;
import dor.dinoloso.utils.*;
import dor.dinoloso.*;

public abstract class GameObject{
	protected Animation animation;
	protected Texture spriteSheet;
	protected Vector2 position;
	protected int width, height;
	protected float currentTime;

	
	public abstract void update(float delta)
	
	public final void render(SpriteBatch batch){
		TextureRegion currentFrame = animation.getKeyFrame(currentTime, true);
		
		float delta = Gdx.graphics.getDeltaTime();
		batch.begin();
		
		batch.draw(currentFrame, position.x, position.y, width, height);
		if(!DinolosoGame.isGameOver)
			update(currentTime += delta);
		batch.end();
		
	}
	
	public void dispose(){
		spriteSheet.dispose();
	}
	
	public boolean isCollided(GameObject entity){
		return GameUtils.isCollided(position.x, position.y, position.x+width, position.y+height, 
				entity.position.x, entity.position.y, entity.position.x+entity.width, entity.position.x+entity.height);
	}

	public boolean isCollided(Row group){
		return GameUtils.isCollided(position.x, position.y, position.x+width, position.y+height, 
				group.object.position.x, group.object.position.y, group.object.position.x+group.getWidth(), group.object.position.y+group.getHeight());
	}



	public final Texture getTexture(){
		return spriteSheet;
	}

	public final Animation getAnimation(){
		return animation;
	}

	public final Vector2 getPosition(){
		return position;
	}

	public final int getWidth(){
		return width;
	}

	public final int getHeight(){
		return height;
	}

	public GameObject(Texture spriteSheet, int start, int end, Vector2 position, int width, int height){
		this.spriteSheet = spriteSheet;
		this.position = position;
		this.width = width;
		this.height = height;

		animation = new Animation((-Config.SCROLL_VELOCITY)/ (width+DinolosoGame.SCORE), TextureUtils.split(spriteSheet, width, height, start, end));
	}

	public GameObject(Texture sprite, Vector2 position, int width, int height){
		this(sprite, 0, 1, position, width, height);
	}
}
