package dor.dinoloso.utils;

public abstract class GameUtils{
	public static boolean isCollided(float left1, float top1, float right1, float bottom1, 
				float left2, float top2, float right2, float bottom2){
		return 
			right1 	>= left2 &
			left1   <= right2 &
			top1 	<= bottom2 & 
			bottom1 >= top2;
	}


}
