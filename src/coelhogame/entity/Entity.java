package coelhogame.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Comparator;
import coelhogame.game.Game;


public class Entity {
	protected double x,y;
	protected double speed;
	protected int depth;
	public Entity(int x, int y, int depth) {
		this.x = x;
		this.y = y;
		this.depth = depth;
	}
	public void update() {
		
	}
	public Entity isColiddionTree(int x, int y) {
		Rectangle player = new Rectangle(x,y,16,16);
		for(int i = 0;i<Game.getEntityList().size();i++) {
			Entity e = Game.getEntityList().get(i);
			if(!(e instanceof Tree))
				continue;
			Rectangle entityCurrent = new Rectangle(e.getX(),e.getY(),32,46);
			if(entityCurrent.intersects(player)) {
				return e;
			}
		}
		return null;
	}
	
	public boolean isColiddionhouse(int x, int y) {
		Rectangle house = new Rectangle(x,y-16,32,32);
		for(int i = 0;i<Game.getEntityList().size();i++) {
			Entity e = Game.getEntityList().get(i);
			if(!(e instanceof House))
				continue;
			Rectangle entityCurrent = new Rectangle(e.getX(),e.getY(),32,32);
			if(entityCurrent.intersects(house)) {
				return true;
			}
		}
		return false;
	}
	
	public Entity isColiddionWood(int x, int y) {
		Rectangle player = new Rectangle(x,y,16,16);
		for(int i = 0;i<Game.getEntityList().size();i++) {
			Entity e = Game.getEntityList().get(i);
			if(!(e instanceof Wood))
				continue;
			Rectangle entityCurrent = new Rectangle(e.getX(),e.getY(),16,16);
			if(entityCurrent.intersects(player)) {
				return e;
			}
		}
		return null;
	}
	
	public static Comparator<Entity> nodeSorter = new Comparator<Entity>() {
		
		public int compare(Entity n0 , Entity n1) {
			if(n1.depth < n0.depth)
				return +1;
			if(n1.depth > n0.depth)
				return -1;
			return 0;
		}
	};
	
	public void render(Graphics g) {
		
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public int getX() {
		return (int)x;
	}

	public int getY() {
		return (int)y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
}
