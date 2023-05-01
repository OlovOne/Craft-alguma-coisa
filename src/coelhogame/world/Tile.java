package coelhogame.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import coelhogame.game.Game;
import coelhogame.graphics.Cam;

public class Tile {
	protected BufferedImage sprite;
	protected int width,height,xSprite,ySprite,x,y;
	public Tile(int width, int height, int xSprite, int ySprite,int x, int y) {
		this.width = width;
		this.height = height;
		this.xSprite = xSprite;
		this.ySprite = ySprite;
		this.x = x;
		this.y = y;
		this.sprite = Game.getSpriteSheet().getSprite(xSprite, ySprite);
	}
	public void render(Graphics g) {
		g.drawImage(sprite, x-Cam.getX(), y-Cam.getY(),width,height, null);
	}
}
