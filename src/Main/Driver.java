package main;

import java.awt.Color;
import java.util.ArrayList;

import main.graphics.FrameRenderer;
import main.graphics.SortScreen;
import main.graphics.Window;
import main.sorting.Sorts;

public class Driver implements Runnable, Engine{

	private static double scale = 1, aspectratio = 16.0/9.0;
	public static final int width = 1500, height = (int)(width/aspectratio);
	//private ArrayList<PhysicsObject> objects = new ArrayList<PhysicsObject>();
	private static String title = "Sort Visualizer";
	private boolean running;
	private Window window;
	private static final int FIXEDUPDATEMS = 100;
	private Thread thread;
	private ArrayList<SortScreen> screens = new ArrayList<SortScreen>();

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		while(true) {
			init();
			long lastFixedUpdate = 0, fps = 0, lastSlowUpdate = 0;

			while(running) {
				if(System.currentTimeMillis()-lastFixedUpdate >= FIXEDUPDATEMS) {
					fixedUpdate();
					lastFixedUpdate = System.currentTimeMillis();
				}
				update(new FrameRenderer((int)(width/scale), (int)(height/scale), Color.WHITE));
				fps++;
				if(System.currentTimeMillis() - lastSlowUpdate >= 1000) {
					window.getFrame().setTitle(title+"\t"+fps+"FPS");
					lastSlowUpdate = System.currentTimeMillis();
					fps = 0;
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(FrameRenderer screen) {

		//Update bars and markers
		for(SortScreen s : screens) {
			s.update(screen);
		}

		window.getCanvas().getBufferStrategy().getDrawGraphics().drawImage(screen.getFrame(), 0, 0, width, height, null);
		window.getCanvas().getBufferStrategy().show();
	}

	private void init() {
		running = true;
		window = new Window(title, width, height);
		window.getCanvas().createBufferStrategy(3);

		screens.add(new SortScreen(0, 0, width/2, height/2, Sorts.BUBBLE));

		screens.add(new SortScreen(width/2, height/2, width, height, Sorts.SELECTION));
		
		screens.add(new SortScreen(0, height/2, width/2, height, Sorts.QUICK));
		
		// screens.add(new SortScreen(width/2, 0, width, height/2, Sorts.INSERTION));

		for(SortScreen s : screens) {
			s.startSort();
		}
	}

	@Override
	public void fixedUpdate() {
		// TODO Auto-generated method stub
		for(SortScreen s : screens) {
			s.fixedUpdate();
		}
	}

	public void stop() {
		running = false;
	}
}
