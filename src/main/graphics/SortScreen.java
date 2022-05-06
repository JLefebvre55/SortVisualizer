package main.graphics;

import java.awt.Color;
import java.util.ArrayList;

import main.sorting.JLUtil;
import main.sorting.Sorter;
import main.sorting.Sorts;

public class SortScreen implements main.Engine{
	
	private int minx, miny, maxx, maxy;
	private String titlestub = "";
	private Sorter sorter;
	private long starttime;
	private int [] a;
	private Bar [] bars;
	private ArrayList<Marker> markers = new ArrayList<Marker>();
	private static final int ARRLEN = 200, ARRMAX = ARRLEN, SORTDELAY = 5;
	private boolean running = false;
	private double runtime = 0.0;
	
	/**
	 * @param minx
	 * @param miny
	 * @param maxx
	 * @param maxy
	 */
	public SortScreen(int minx, int miny, int maxx, int maxy, Sorts type) {
		super();
		this.minx = minx;
		this.miny = miny;
		this.maxx = maxx;
		this.maxy = maxy;
		a = JLUtil.randSequentialArray(ARRLEN);
		titlestub = String.format("%s sort. Array Length %d, Int Range 0-%d, @%dms delay. ", type.toString(), a.length, ARRMAX, SORTDELAY);

		bars = new Bar [ARRLEN];
		for(int i = 0; i < a.length; i++) {
			bars[i] = new Bar(i, ARRLEN, a[i], ARRMAX, this);
			
		}
		System.out.println(bars[bars.length-1]);
		System.out.println((bars.length*bars[0].getWidth())+"/"+maxx);
		this.sorter = new Sorter(a, SORTDELAY, this, type);
	}

	@Override
	public void update(FrameRenderer screen) {
		// TODO Auto-generated method stub
		for(Bar b : bars) {
			b.update(screen);
		}
		for(Marker m : markers) {
			m.update(screen);
		}
		if(running) {
			runtime = (System.currentTimeMillis()-starttime)/1000.0;
		}
		screen.renderString(titlestub+String.format("%.2fs\t%d Array Accesses, %d Comparisons", runtime, sorter.getAccesses(), sorter.getComparisons()), minx+10, miny+20);
		screen.renderScreenFrame(this);
	}

	@Override
	public void fixedUpdate() {
		// TODO Auto-generated method stub
		for(Bar b : bars) {
			b.fixedUpdate();
		}
		for(Marker m : markers) {
			m.fixedUpdate();
		}
	}
	
	public void stop() {
		running = false;
	}
	
	public void startSort() {
		running = true;
		starttime = System.currentTimeMillis();
		sorter.start();
	}

	/**
	 * @return the minx
	 */
	public int getMinx() {
		return minx;
	}

	/**
	 * @return the miny
	 */
	public int getMiny() {
		return miny;
	}

	/**
	 * @return the maxx
	 */
	public int getMaxx() {
		return maxx;
	}

	/**
	 * @return the maxy
	 */
	public int getMaxy() {
		return maxy;
	}
	
	public int getWidth() {
		return maxx-minx;
	}
	
	public int getHeight() {
		return maxy-miny;
	}
	
	public void swap(int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		Bar tempb = bars[i];
		bars[i] = bars[j];
		bars[j] = tempb;
		bars[i].setPosition(i);
		bars[j].setPosition(j);
		//System.out.println("Swapped "+i+" and "+j);
	}
	
	public void insert(int i, int j) {
		for(int x = Math.max(i, j); i > Math.min(i, j); x--) {
			
		}
	}

	public void setMarker(int i, Color c, String id) {
		for(Marker m : markers) {
			if(m.getID().equals(id)) {
				m.setBar(bars[i]);
				return;
			}
		}
		markers.add(new Marker(bars[i], c, id));
	}
	
	public void colorBar(int i, Color c) {
		bars[i].setColor(c);
	}


}
