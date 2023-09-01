package dor.dinoloso.util;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;
import java.util.*;
import java.lang.reflect.*;

public abstract class MatrixUtil
{
	public static <E extends Object> E[]  getRange(int firstIndex, int lastIndex, E[][] regions) {
		int count = 0;
	
		E[] frames = (E[])Array.newInstance(regions[0][0].getClass(), lastIndex-firstIndex+1);
		
		for(E[] rows : regions)
			for(E region : rows){
				if(count>=firstIndex){		
					if(count>lastIndex)break;
					frames[count] = region;

				}
				count++;
			}	
		return frames;
	}
}
