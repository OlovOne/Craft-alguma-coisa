package coelhogame.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import coelhogame.game.Game;
import coelhogame.graphics.Cam;

public class Tree extends Entity{
	private BufferedImage sprite;
	private double life = 10;
	public Tree(int x, int y, int depth) {
		super(x, y, depth);
		sprite = Game.getSpriteSheet().getSpriteWithSize(64, 0, 32, 48);
	}
	
	public void update() {
		if(life <= 0) {
			Game.getEntityList().remove(this);
			Game.getEntityList().add(new Wood(getX()+8,getY(),1));		
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, getX()-Cam.getX(), getY()-Cam.getY(),null);
	}

	public double getLife() {
		return life;
	}

	public void setLife(double life) {
		this.life = life;
	}
	
}
