package coelhogame.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import coelhogame.game.Game;
import coelhogame.graphics.Cam;

public class House extends Entity{
	private BufferedImage sprite;
	
	//Wood
	private BufferedImage wood_p;
	private boolean isDone;
	private long lastTime;
	private int xWood, yWood;
	private long delay;
	
	public House(int x, int y, int depth) {
		super(x, y, depth);
		sprite = Game.getSpriteSheet().getSpriteWithSize(32, 0, 32, 32);
		wood_p = Game.getSpriteSheet().getSprite(0, 32);
		lastTime = System.currentTimeMillis();
		this.xWood = x;
		this.yWood = y;
		speed = 0.01;
	}
	
	public void update() {
		long nowTime = System.currentTimeMillis();
		if(nowTime - lastTime>=1000) {
			lastTime = nowTime;
			isDone = true;
			Game.getPlayer().setWood(Game.getPlayer().getWood()+1);
		}
		if(isDone) {
			if(nowTime - delay >=30) {
				delay = nowTime;
				yWood-=speed;
			}
			if(y - 10 >= yWood) {
				isDone = false;
				yWood = getY();
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, getX()-Cam.getX(), getY()-Cam.getY(),null);
		if(isDone) {
			g.drawImage(wood_p, xWood-Cam.getX()+8, yWood-Cam.getY(), null);
		}
	}

}
