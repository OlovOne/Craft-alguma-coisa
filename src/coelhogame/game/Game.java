package coelhogame.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;

import coelhogame.entity.Entity;
import coelhogame.entity.Player;
import coelhogame.graphics.Spritesheet;
import coelhogame.graphics.UI;
import coelhogame.world.World;

public class Game extends Canvas implements Runnable, KeyListener{
	private static final long serialVersionUID = 1L;
	//Logica para rodar o jogo
	private boolean isRunning;
	private Thread thread;
	
	//Frame de visão do jogo
	private JFrame frame;
	public static final int WIDTH = 240;
	public static final int HEIGHT = 160;
	public static final int SCALE = 2;
	
	//Variaveis para rendereização
	private BufferStrategy bs;
	private Graphics g;
	private BufferedImage canva;
	private static Spritesheet spriteSheet;
	private static List<Entity> entityList;
	private static World world;
	//player
	private static Player player;
	
	//UI
	private UI ui;
	
	public Game(){
		frame = new JFrame();
		canva = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		spriteSheet = new Spritesheet("/spritesheet.png");
		player = new Player(0,0,2);
		entityList = new ArrayList<Entity>();
		entityList.add(player);
		world = new World("/world.png");
		ui = new UI();
		this.addKeyListener(this);
		initFrame();
	}
	public void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void initFrame() {
		frame.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		frame.add(this);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setVisible(true);
	}
	
	public void renderEntity() {
		Collections.sort(entityList, Entity.nodeSorter);
		for(int i = 0;i<entityList.size();i++) {
			Entity e = entityList.get(i);
			e.render(g);
		}
	}
	
	public void updateEntity() {
		for(int i = 0;i<entityList.size();i++) {
			Entity e = entityList.get(i);
			e.update();
		}
	}
	
	private void update() {
		updateEntity();
		ui.update();
	}
	
	private void render() {
		bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		g = canva.getGraphics();
		
		world.render(g);
		ui.render(g);
		renderEntity();
		
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(canva,0,0,WIDTH*SCALE,HEIGHT*SCALE, null);
		bs.show();
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfUpdate = 60.0;
		double ns = (100000000/amountOfUpdate);
		double delta = 0;
		while(isRunning){
			this.requestFocus();
			long nowTime = System.nanoTime();
			delta = (nowTime - lastTime) /ns;
			if(delta >=1) {
				lastTime = nowTime;
				update();
				render();
				delta--;
			}
		}
	}
	
	public static Spritesheet getSpriteSheet() {
		return spriteSheet;
	}

	public void keyTyped(KeyEvent e) {
		
	}
	
	public static Player getPlayer() {
		return player;
	}
	
	public static List<Entity> getEntityList() {
		return entityList;
	}
	
	public static World getWorld() {
		return world;
	}
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.setLeft(true);
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			player.setRight(true);
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.setDown(true);
		}else if(e.getKeyCode() == KeyEvent.VK_UP){
			player.setUp(true);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.setLeft(false);
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			player.setRight(false);
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.setDown(false);
		}else if(e.getKeyCode() == KeyEvent.VK_UP){
			player.setUp(false);
		}
	}
	
}
