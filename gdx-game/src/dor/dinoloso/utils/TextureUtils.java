package dor.dinoloso.utils;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;

public abstract class TextureUtils{
	public static TextureRegion[] split(Texture texture, int width, int height, int start, int end){
		TextureRegion[] sprites = new TextureRegion[end-start];
		
		int coluns = texture.getWidth()/width;
		int lines = texture.getHeight()/height;

		for(int l=0; l < lines; l++)
			for(int c=0; c < coluns; c++){
				int i = c+coluns*l;
				if(i < start)continue;
				if(i >= end)break;
				sprites[i] = new TextureRegion(texture, c*width, l*height, width, height);
			}
			
		return sprites;
	}
	
	public static TextureRegion[] split(Texture texture, int width, int height){
		return split(texture, width, height, 0, (texture.getWidth()*texture.getHeight())/(width*height));
	}
}
