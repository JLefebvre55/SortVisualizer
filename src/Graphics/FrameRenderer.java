package Graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Main.Driver;

/**
 * Renderer for screen frames. ALL update calls use this.
 * @author jaydenlefebvre
 *
 */
public class FrameRenderer {
	private BufferedImage frame;
	private Graphics2D frameg;

	/**
	 * Makes a renderer without bg
	 * @param width in px
	 * @param height in px
	 */
	public FrameRenderer(int width, int height) {
		this.frame = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		frameg = frame.createGraphics();
	}

	/**
	 * Makes a renderer with bg
	 * @param width in px
	 * @param height in px
	 */
	public FrameRenderer(int width, int height, Color bgcolor) {
		this(width, height);
		frameg.setColor(bgcolor);
		frameg.fillRect(0, 0, width, height);
	}

	/**
	 * Renders an image to a location on screen
	 * @param image
	 * @param x
	 * @param y
	 */
	public void renderImage(BufferedImage image, double x, double y) {
		frameg.drawImage(image, (int)x, (int)y, null);
	}

	public void renderBar(Bar b) {
		frameg.setColor(b.getColor());
		if(b.getWidth()>3) {
			frameg.fillRect(b.getX(), b.getY()-b.getHeight(), b.getWidth()-1, b.getHeight());
		} else {
			frameg.fillRect(b.getX(), b.getY()-b.getHeight(), b.getWidth(), b.getHeight());
		}
	}

	public void renderScreenFrame(SortScreen screen) {
		frameg.setColor(Color.BLACK);
		frameg.drawRect(screen.getMinx(), screen.getMiny(), screen.getWidth(), screen.getHeight());
	}

	/**
	 * Get rendered frame
	 * @return rendered frame
	 */
	public BufferedImage getFrame() {
		return frame;
	}

	public void renderMarker(Marker marker) {
		// TODO Auto-generated method stub
		frameg.setColor(marker.getColor());
		frameg.fillOval(marker.getX()-Marker.RADIUS, marker.getY()-Marker.RADIUS, Marker.RADIUS*2, Marker.RADIUS*2);
	}

	public void renderString(String s, int x, int y) {
		frameg.setColor(Color.BLACK);
		frameg.drawString(s, x, y);
	}
}