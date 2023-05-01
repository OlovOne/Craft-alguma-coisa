package coelhogame.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import coelhogame.game.Game;

public class UI {
	private BufferedImage woodSprite;
	private int quanti_wood;
	
	public UI() {
		woodSprite = Game.getSpriteSheet().getSprite(0, 32);
	}
	
	public void update() {
		quanti_wood = Game.getPlayer().getWood();
	}
	
	public void render(Graphics g) {
		g.drawImage(woodSprite, 15, 0, null);
		g.drawString(Integer.toString(quanti_wood), 0, 12);
	}
}
