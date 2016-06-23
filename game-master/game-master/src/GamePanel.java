
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;

	private Thread thread;
	private boolean running;

	private BufferedImage image;
	private Graphics2D g;

	private int FPS = 30;
	private int targetTime = 1000 / FPS;

	private TileMap tileMap; 
	private Player player;

	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}

	public void addNotify() {
		super.addNotify();
		addKeyListener(this);
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}

	}

	public void run() {
		init();
		long startTime;
		long urdTime;
		long waitTime;

		while (running) {

			startTime = System.currentTimeMillis();

			update();
			render();
			draw();

			urdTime = (System.currentTimeMillis() - startTime) / 1000;

			waitTime = targetTime - urdTime;

			try {
				Thread.sleep(waitTime);
			} catch (Exception e) {

			}
		}

	}

	private void init() {
		running = true;
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();

		// depois tilemap
		tileMap = new TileMap("map.txt", 32);
		tileMap.loadTiles("tileset.gif");
		
	 

		player = new Player(tileMap);
		player.setX(50.0);
		player.setY(50.0);
	}

	private void update() {
 
		player.update();
		tileMap.update();
	}

	private void render() {

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		
		tileMap.renderBack(g);
		player.render(g);
		tileMap.render(g);
	}

	private void draw() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		int code = arg0.getKeyCode();
		
		if(code == KeyEvent.VK_LEFT){
			player.setLeft(true);
		}
		
		if(code == KeyEvent.VK_RIGHT){
			player.setRigth(true);
		}
		
		if(code == KeyEvent.VK_SPACE){
			player.setJumping(true);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		int code = arg0.getKeyCode();
		
		if(code == KeyEvent.VK_LEFT){
			player.setLeft(false);
		}
		
		if(code == KeyEvent.VK_RIGHT){
			player.setRigth(false);
		}
		
		 
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
