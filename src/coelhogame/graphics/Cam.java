package coelhogame.graphics;

public class Cam {
	private static int x , y;
	public static int clamp(int min, int now, int max) {
		if(min>now) {
			now = min;
		}
		if(max<now) {
			now = max;
		}
		return now;
	}
	public static int getX() {
		return x;
	}
	public static void setX(int x) {
		Cam.x = x;
	}
	public static int getY() {
		return y;
	}
	public static void setY(int y) {
		Cam.y = y;
	}
	
}
