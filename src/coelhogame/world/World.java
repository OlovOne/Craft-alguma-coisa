package coelhogame.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import coelhogame.entity.Player;
import coelhogame.entity.Tree;
import coelhogame.game.Game;
import coelhogame.graphics.Cam;


public class World {
	private BufferedImage world;
	private static int width;
	private static int height;
	private int[] pixelWorld;
	private static Tile[] tiles;
	
	public World(String path) {
		try {
			world = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = world.getWidth();
		height = world.getHeight();
		tiles = new Tile[width*height];
		pixelWorld = world.getRGB(0, 0, width, height, pixelWorld, 0, width);
		for(int xx = 0; xx<width;xx++) {
			for(int yy = 0; yy<height;yy++) {
				int nowPixel = xx+(yy*width);
				if(pixelWorld[nowPixel] == 0XFF49F5FF) {
					//sky
					tiles[nowPixel] = new Floor_Tile(16,16,16,16,xx*16,yy*16);
				}else if(pixelWorld[nowPixel] == 0XFF000000) {
					//player
					Game.getPlayer().setY(yy*16);
					Game.getPlayer().setX(xx*16);
					tiles[nowPixel] = new Sky_Tile(16,16,16,16,xx*16,yy*16);
				}else if(pixelWorld[nowPixel] == 0XFF28FF53) {
					//Floor
					tiles[nowPixel] = new Floor_Tile(16,16,16,0,xx*16,yy*16);
				}else if(pixelWorld[nowPixel] == 0XFF606060){
					tiles[nowPixel] = new Sky_Tile(16,16,0,16,xx*16,yy*16);
				}else if(pixelWorld[nowPixel] == 0XFFA55B57){
					Game.getEntityList().add(new Tree(xx*16,yy*16,0));
					tiles[nowPixel] = new Sky_Tile(16,16,16,16,xx*16,yy*16);
				}else {
					tiles[nowPixel] = new Sky_Tile(16,16,16,16,xx*16,yy*16);
				}
			}
		}
	}
	
	public static boolean colissionTile(int xNext, int yNext) {
		int x1 = xNext/16;
		int y1 = yNext/16;
		
		int x2 =(xNext + 16-1)/16;
		int y2 = yNext/16;
		
		int x3 = xNext/16;
		int y3 = (yNext + 16-1)/16;
		
		int x4 = (xNext + 16-1)/16;
		int y4 = (yNext + 16-1)/16;
		
		return  tiles[x1+(y1*width)] instanceof Floor_Tile||
				tiles[x2+(y2*width)] instanceof Floor_Tile||
				tiles[x3+(y3*width)] instanceof Floor_Tile||
				tiles[x4+(y4*width)] instanceof Floor_Tile ;
	}
	
	public void render(Graphics g) {
		int xStart = Cam.getX()/16;
		int yStart = Cam.getY()/16;
		int xEnd = xStart + Game.WIDTH/16;
		int yEnd = yStart + Game.HEIGHT/16;
		for(int xx = xStart;xx<=xEnd;xx++) {
			for(int yy = yStart;yy<=yEnd;yy++) {
				if(xx <0 || yy <0 || xx>=width || yy>=height) 
					continue;
				Tile tile = tiles[xx + (yy*width)];
				tile.render(g);
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
}
