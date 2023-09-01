package dor.dinoloso.entity;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.g2d.*;
import dor.dinoloso.util.*;
import dor.dinoloso.*;

public final class Dino{
	private Rectangle rect;
	
	private boolean isJumping;
	private float velY;
	private float frameDuration = .1f;
	private boolean isAlive = true;
	

	private Animation walkingAnimation;
	private TextureRegion deadSprite;
	private Texture spriteSheet;

	public void draw(Batch batch){
		TextureRegion currentFrame = walkingAnimation.getKeyFrame(
			isJumping? 0 : DinolosoGame.getTimePassed(), true
		);
		
		batch.begin();
		batch.draw(isAlive? currentFrame : deadSprite, rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
		batch.end();
	}

	public void update(float delta){
		if(isJumping){
			velY += Config.GRAVITY;
			rect.y += Config.GRAVITY;
			rect.y += velY;
		}
	}
	
	public void realeseJump(){
		isJumping = true;
		velY = Config.JUMP_FORCE;
	}
	
	public void cancelJump(){
		rect.y = 32;
		velY = 0;
		isJumping = false;
	}
	
	public void setIsAlive(boolean isAlive){
		this.isAlive = isAlive;
	}
	
	public boolean isAlive(){
		return isAlive;
	}
	
	public boolean isJumping(){
		return isJumping;
	}
	
	public Rectangle getRectangle(){
		return new Rectangle(rect);
	}
	
	public Rectangle getMask(){
		return new Rectangle(rect.x+8, rect.y, rect.width-14, rect.height);
		
	}
	
	public float getWidth(){
		return rect.getWidth();
	}
	
	public float getHeight(){
		return rect.getHeight();
	}
	
	
	public void dispose(){
		spriteSheet.dispose();
	}
	
	public Dino(float x, float y){
		rect = new Rectangle(x, y, 32, 32);
		spriteSheet = new Texture("dino.png");
		
		TextureRegion[][] regions = TextureRegion.split(spriteSheet, 32, 32);
		
		walkingAnimation = new Animation(frameDuration, MatrixUtil.getRange(0, 6,  regions));
													
		deadSprite = regions[1][2];
	}
	
}
