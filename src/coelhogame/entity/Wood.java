package coelhogame.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import coelhogame.game.Game;
import coelhogame.graphics.Cam;
import coelhogame.world.World;

public class Wood extends Entity{
	private BufferedImage sprite;
	
	public Wood(int x, int y, int depth) {
		super(x, y, depth);
		speed = 0.15;
		sprite = Game.getSpriteSheet().getSprite(0, 32);
	}
	public void update() {
		if(World.colissionTile(getX()+32, (int)(y-speed)+32)) {
			y+=speed;
		}
	}
	public void render(Graphics g) {
		g.drawImage(sprite, getX()-Cam.getX(),getY()-Cam.getY(), null);
	}
}
