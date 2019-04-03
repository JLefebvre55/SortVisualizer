package Graphics;

import java.awt.Color;

import Main.Driver;
import Main.Engine;

public class Marker implements Engine{

	private int x, y;
	private Color c;
	public static final int RADIUS = 4;
	private String id;
	
	public Marker(Bar bar, Color c, String id) {
		setBar(bar);
		this.c = c;
		this.id = id;
	}

	@Override
	public void update(FrameRenderer screen) {
		// TODO Auto-generated method stub
		screen.renderMarker(this);
	}

	@Override
	public void fixedUpdate() {
		// TODO Auto-generated method stub
		
	}
	
	public void setBar(Bar bar) {
		this.x = bar.getX() + bar.getWidth()/2;
		this.y = bar.getY() - bar.getHeight() - 4*RADIUS;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return c;
	}
	
	public String getID() {
		return id;
	}
	
	
}
