import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Player {

	private double x;
	private double y;
	private double dx;
	private double dy;

	private int width;
	private int height;

	private boolean left;
	private boolean rigth;
	private boolean jumping;
	private boolean falling;

	private double moveSpeed;
	private double maxSpeed;
	private double maxFallingSpeed;
	private double stopSpeed;
	private double jumpStart;
	private double gravity;

	private TileMap tileMap;

	private boolean topLeft;
	private boolean topRigth;
	private boolean bottomLeft;
	private boolean bottomRight;

	private Animation animation;

	private BufferedImage[] walkingSprites;
	private BufferedImage[] jumpingSprites;
	private BufferedImage[] fallingSprites;
	private BufferedImage[] idleSprites;

	private boolean facingLeft;

	public Player(TileMap tm) {
		this.tileMap = tm;

		width = 23;
		height = 33;

		moveSpeed = 0.6;
		maxSpeed = 4.2;
		maxFallingSpeed = 12;
		stopSpeed = 30;
		jumpStart = -11.0;
		gravity = 0.64;

		try {
			
			jumpingSprites = new BufferedImage[1];
			fallingSprites = new BufferedImage[1];
			idleSprites = new BufferedImage[1];
			walkingSprites = new BufferedImage[3];
			
			jumpingSprites[0] = ImageIO.read(getClass().getResourceAsStream("jump.png"));
			fallingSprites[0] = ImageIO.read(getClass().getResourceAsStream("idle.png"));
			idleSprites[0] = ImageIO.read(getClass().getResourceAsStream("idle.png"));

			BufferedImage aux = ImageIO.read(getClass().getResourceAsStream("walk.png")); 
			for (int i = 0; i < aux.getWidth() / width; i++) {
				walkingSprites[i] = aux.getSubimage(i * width, 0, width, height);

			}

		} catch (Exception e) {
			System.out.println("Loading image Player: " + e.getMessage());
		}

		animation = new Animation();
		facingLeft = false;
		
		 
	}

	public void update() {

		if (left) {
			dx -= moveSpeed;
			if (dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		} else if (rigth) {
			dx += moveSpeed;
			if (dx > maxSpeed) {
				dx = maxSpeed;
			}
		} else {
			if (dx > 0) {
				dx -= stopSpeed;
				if (dx < 0) {
					dx = 0;
				}
			} else if (dx < 0) {
				dx += stopSpeed;
				if (dx > 0) {
					dx = 0;
				}
			}
		}

		if (jumping) {
			dy = jumpStart;
			falling = true;
			jumping = false;

		}

		if (falling) {
			dy += gravity;

			if (dy > maxFallingSpeed) {
				dy = maxFallingSpeed;
			}

		} else {
			dy = 0;
		}

		int currCol = tileMap.getColTile((int) x);
		int currRow = tileMap.getRowTile((int) y);

		double tox = x + dx;
		double toy = y + dy;

		double tempx = x;
		double tempy = y;

		calculateCorners(x, toy);
		if (dy < 0) {
			if (topLeft || topRigth) {
				dy = 0;
				tempy = currRow * tileMap.getTileSize() + height / 2;
			} else {
				tempy += dy;
			}

		}
		if (dy > 0) {
			if (bottomLeft || bottomRight) {
				dy = 0;
				falling = false;
				tempy = (currRow + 1) * tileMap.getTileSize() - height / 2;
			} else {
				tempy += dy;
			}

		}

		calculateCorners(tox, y);

		if (dx > 0) {
			if (bottomRight || topRigth) {
				dx = 0;
				tempx = (currCol + 1) * tileMap.getTileSize() - width / 2;
			} else {
				tempx += dx;
			}
		}

		if (dx < 0) {
			if (topLeft || bottomLeft) {
				dx = 0;
				tempx = (currCol) * tileMap.getTileSize() + width / 2;

			} else {
				tempx += dx;
			}

		}

		if (!falling) {
			calculateCorners(x, y + 1);
			if (!bottomLeft && !bottomRight) {
				falling = true;
			}
		}

		x = tempx;
		y = tempy;

		tileMap.setX((int) (GamePanel.WIDTH / 2 - x));
		tileMap.setY((int) (GamePanel.HEIGHT / 2 - y));

		if (left || rigth) {
			animation.setFrame(walkingSprites);
			animation.setDelay(100);
		} else {
			animation.setFrame(idleSprites);
			animation.setDelay(-1);
		}

		if (dy < 0) {
			animation.setFrame(jumpingSprites);
			animation.setDelay(-1);
		}

		if (dy > 0) {
			animation.setFrame(fallingSprites);
			animation.setDelay(-1);
		}

		if (dx < 0) {
			facingLeft = true;
		}

		if (dx > 0) {
			facingLeft = false;
		}

		animation.update();

	}

	private void calculateCorners(double x, double y) {
		int leftTile = tileMap.getColTile((int) (x - width / 2));
		int rigthtTile = tileMap.getColTile((int) (x + width / 2) - 1);
		int topTile = tileMap.getRowTile((int) (y - height / 2));
		int bottomTile = tileMap.getRowTile((int) (y + height / 2) - 1);

		topLeft = tileMap.isBlocked(topTile, leftTile);
		topRigth = tileMap.isBlocked(topTile, rigthtTile);
		bottomLeft = tileMap.isBlocked(bottomTile, leftTile);
		bottomRight = tileMap.isBlocked(bottomTile, rigthtTile);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void render(Graphics2D g) {

		int tx = tileMap.getX();
		int ty = tileMap.getY();

		
		if(facingLeft){
			g.drawImage(animation.getImage(),(int) (x - width / 2) + tx, 
					(int) (y - height / 2) + ty, width, height, null);
			
		}else{
			g.drawImage(animation.getImage(),(int) (x + width / 2) + tx, 
					(int) (y - height / 2) + ty, -width, height, null);
		}
		
 
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRigth() {
		return rigth;
	}

	public void setRigth(boolean rigth) {
		this.rigth = rigth;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		if (!falling) {
			this.jumping = jumping;
		}

	}

}
