
import java.io.*;
import java.net.URL;
import java.util.concurrent.SynchronousQueue;

import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.TitlePaneLayout;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TileMap {

	private int x;
	private int y;
	private int tileSize;
	private int[][] map;
	private int[][] mapBack;
	private int mapWidth;
	private int mapHeight;

	private BufferedImage tileset;
	private Tile[][] tiles;
	private Tile[][] tilesBack;
	private int minX;
	private int minY;
	private int maxX = 0;
	private int maxY = 0;
	
	public TileMap(String s, int tileSize) {
		this.tileSize = tileSize;

		try {
			BufferedReader br = new BufferedReader(new FileReader(getClass().getResource(s).getFile()));
			mapWidth = Integer.parseInt(br.readLine());

			mapHeight = Integer.parseInt(br.readLine());

			minX = GamePanel.WIDTH  - mapWidth * tileSize;
			minY = GamePanel.HEIGHT  - mapHeight * tileSize;
			
			map = new int[mapHeight][mapWidth];
			mapBack = new int[mapHeight][mapWidth];
			
			String delimiters = "\\s+"; // n espaços

			for (int row = 0; row < mapHeight; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delimiters);
				for (int col = 0; col < mapWidth; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
					mapBack[row][col] = 0;
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void loadTiles(String s) {
		try {
			tileset = ImageIO.read(getClass().getResourceAsStream(s));
			int numTilesAcross = (tileset.getWidth() + 1) / (tileSize + 1);
			int numCol = (tileset.getHeight() + 1) / (tileSize + 1);

			tiles = new Tile[numCol][numTilesAcross];
			BufferedImage subImage;

			for (int col = 0; col < numTilesAcross; col++) {
				subImage = tileset.getSubimage(col * tileSize + col, 0, tileSize, tileSize);
				tiles[0][col] = new Tile(subImage, false);

				subImage = tileset.getSubimage(col * tileSize + col, tileSize + 1, tileSize, tileSize);

				tiles[1][col] = new Tile(subImage, true);
				
			}

		} catch (Exception e) {
			System.out.println("TileMap load image: " + e.getMessage());
		}

	}

	public void update() {

	}

	public void renderBack(Graphics2D g){

		for (int row = 0; row < mapHeight; row++) {
			for (int col = 0; col < mapWidth; col++) {

 
				g.drawImage(tiles[1][2].getImage(), x + col * tileSize, y + row * tileSize, null);

			}
		}
		
	}
	public void render(Graphics2D g) {
		
		
		
		for (int row = 0; row < mapHeight; row++) {
			for (int col = 0; col < mapWidth; col++) {

				int rc = map[row][col];

				int r = rc / tiles[0].length;
				int c = rc % tiles[0].length;
				
				

				g.drawImage(tiles[r][c].getImage(), x + col * tileSize, y + row * tileSize, null);

			}
		}

	}

	public boolean isBlocked(int row, int col) {

		int rc = map[row][col];

		int r = rc / tiles[0].length;
		int c = rc % tiles[0].length;

//		if(rc == 0 ){
//			map[row][col] = 1;
//		}
		return tiles[r][c].isBlocked();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		
		if(x < minX){
			this.x = minX;
		}

		if(x > maxX){
			this.x = maxX;
		}
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		
		if(y < minY){
			this.y = minY;
		}

		if(y > maxY){
			this.y = maxY;
		}
	}

	public int getColTile(int x) {
		return x / tileSize;
	}

	public int getRowTile(int y) {
		return y / tileSize;
	}

	public int getTile(int row, int col) {
		return map[row][col];

	}

	public int getTileSize() {
		return tileSize;
	}

	public void setTileSize(int tileSize) {
		this.tileSize = tileSize;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
	}

}
