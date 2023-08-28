package dor.dinoloso;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;
import dor.dinoloso.game.GameObject;
import com.badlogic.gdx.math.*;

public final class Dino extends GameObject{
	private Vector2 vel = new Vector2();
	private float jumpForce = 10;
	private boolean isJumping;
	
	@Override
	public void update(float delta){
		if(Gdx.input.justTouched() & position.y==0){
			vel.y = jumpForce;
			isJumping = true;
		}
		
		if(position.y >= 0 & isJumping){
			currentTime = 0;
				
			position.add(0, Config.GRAVITY);
			vel.y += Config.GRAVITY;
			position.add(0, vel.y);
			
		}else{
			position.y = 0;
			vel.y = 0;
			isJumping = false;
		}
	}
	
	public Dino(){
		super(new Texture("dino.png"), 0, 7, new Vector2(), 32, 32);
	}
	
	
}
