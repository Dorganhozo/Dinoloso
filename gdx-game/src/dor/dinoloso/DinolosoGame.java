package dor.dinoloso;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.viewport.*;
import dor.dinoloso.game.GameObject;
import java.util.*;

public class DinolosoGame extends Game{
	
	public static int SCORE;
	public static boolean isGameOver;
	
	@Override
	public void create(){
		setScreen(new GameOverScreen());
		setScreen(new PlayScreen());
	}

	@Override
	public void render(){
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
		
		if(isGameOver & Gdx.input.justTouched())
			isGameOver = false;
	}
	
	@Override
	public void dispose(){
		super.dispose();
	}

	@Override
	public void resize(int width, int height){
		super.resize(width, height);
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
