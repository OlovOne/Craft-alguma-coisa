package coelhogame.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	private static BufferedImage spriteSheet;
	public Spritesheet(String path) {
		try {
			spriteSheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public BufferedImage getSprite(int x, int y) {
		return spriteSheet.getSubimage(x, y, 16, 16);
	}
	public BufferedImage getSpriteWithSize(int x, int y,int width,int height) {
		return spriteSheet.getSubimage(x, y, width, height);
	}
}
