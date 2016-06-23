import java.awt.image.BufferedImage;

public class Tile {
	
	private BufferedImage image;	
	private boolean blocked;
	
	public Tile(BufferedImage image, boolean blocked) {
		
		this.image = image;
		this.blocked = blocked;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}


	
	
}
