import java.awt.image.BufferedImage;

public class Animation {

	private BufferedImage[] frames;
	private int currentFrame;

	private long startTime;
	private long delay;

	public Animation() {
	}

	public void update() {
		if (delay == -1)
			return;

		long elapse = (System.currentTimeMillis() - startTime) / 1000;

		if (elapse > delay) {
			currentFrame++;
			startTime = System.currentTimeMillis();
		}

		if (currentFrame == frames.length) {
			currentFrame = 0;
		}

	}
	
	public void setFrame(BufferedImage[] images){
		frames = images;
		if(currentFrame >= frames.length){ 
			currentFrame = 0;
		}
	}
	
	public void setDelay(long d){
		delay = d;
	}
	
	public BufferedImage getImage(){
		return frames[currentFrame];
	}

}
