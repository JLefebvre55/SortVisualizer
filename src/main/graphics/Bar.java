package main.graphics;

import java.awt.Color;

import main.Engine;

public class Bar implements Engine{

	private int position, height, width, value;
	private SortScreen screen;
	private Color c;
	
	/**
	 * Make a bar
	 * @param position
	 * @param totalbars
	 * @param value
	 * @param maxvalue
	 * @param screenwidth
	 * @param screenheight
	 * @param c
	 */
	public Bar(int position, int totalbars, int value, int maxvalue, SortScreen screen, Color c) {
		this.position = position;
		this.value = value;
		this.height = (int)((screen.getHeight()-40)*((double)value/maxvalue));
		this.width = (int)(screen.getWidth()/totalbars);
		this.c = c;
		this.screen = screen;
	}
	
	public Bar(int position, int totalbars, int value, int maxvalue, SortScreen screen) {
		this(position, totalbars, value, maxvalue, screen, Color.BLACK);
	}

	@Override
	public void update(FrameRenderer screen) {
		// TODO Auto-generated method stub
		screen.renderBar(this);
	}

	@Override
	public void fixedUpdate() {
		// TODO Auto-generated method stub
	}
	
	public void setPosition(int n) {
		this.position = n;
	}
	
	public Color getColor() {
		return c;
	}
	
	public void setColor(Color c) {
		this.c = c;
	}

	/**
	 * @return the position
	 */
	public int getX() {
		return position*width+screen.getMinx();
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	public int getPosition() {
		// TODO Auto-generated method stub
		return position;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getY() {
		return screen.getMaxy();
	}
	
	@Override
	public String toString() {
		return "Bar at pos "+position+" with value "+value+", X:"+getX()+", Y:"+getY()+".";
	}
	
}
