package coelhogame.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import coelhogame.game.Game;
import coelhogame.graphics.Cam;

public class Player extends Entity{
	private BufferedImage sprite;
	private boolean right,left,up,down;
	private boolean isHit;
	private int wood;
	
	public Player(int x, int y, int depth) {
		super(x, y, depth);
		sprite = Game.getSpriteSheet().getSprite(0, 0);
		speed = 0.1;
		wood = 0;
	}
	
	public void update() {
		Wood w = (Wood) isColiddionWood(getX(), getY());
		if(w != null) {
			Game.getEntityList().remove(w);
			wood += 10;
		}
		
		if(left) {
			x-=speed;
		}else if(right) {
			x+=speed;
		}
		
		if(down && wood >= 10) {
			if(!isColiddionhouse(getX(), getY()-16)) {
				Game.getEntityList().add(new House(getX(),getY()-16,0));
				wood -=10;
			}
		}else if(up){
			Tree t = (Tree) isColiddionTree(getX(), getY());
			if(t != null && isHit) {
				t.setLife(t.getLife()-1);
				isHit = false;
			}
		}else{
			isHit = true;
		}
		
		Cam.setX(Cam.clamp(0, getX()-Game.WIDTH/2, (Game.getWorld().getWidth()*16)-Game.WIDTH));
		Cam.setY(Cam.clamp(0, getY()-Game.HEIGHT/2, (Game.getWorld().getHeight()*16)-Game.HEIGHT));
		
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, getX()-Cam.getX(),getY()-Cam.getY(), null);
	}
	
	public BufferedImage getSprite() {
		return sprite;
	}

	public double getSpeed() {
		return speed;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public int getWood() {
		return wood;
	}

	public void setWood(int wood) {
		this.wood = wood;
	}
	
}
