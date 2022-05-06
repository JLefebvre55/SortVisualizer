package main.sorting;

import java.awt.Color;

import main.graphics.SortScreen;

public class Sorter implements Runnable{

	private int [] a;
	private Thread thread;
	private SortScreen screen;
	private int msdelay, accesses, comparisons;
	private Sorts type;

	public Sorter(int [] a, int msdelay, SortScreen screen, Sorts type) {
		this.a = a;
		this.screen = screen;
		this.msdelay = msdelay;
		this.type = type;
		thread = new Thread(this);
	}

	public void start() {
		thread.start();
	}

	@Override
	public void run() {
		switch(type) {
		case SELECTION:
			// TODO Auto-generated method stub
			for(int i = a.length-1; i> 0; i--) {

				//Top lid marker
				screen.colorBar(i, Color.BLUE);

				int big = 0;

				for(int j = 0; j < i; j++) {

					//Biggest marker
					screen.colorBar(big, Color.GREEN);

					//Current marker
					screen.colorBar(j, Color.RED);

					//Stats
					comparisons++;
					accesses+=2;

					if(a[j] > a[big]) {
						//Set current big to black before setting new big
						screen.colorBar(big, Color.BLACK);
						big = j;

					}
					try {
						Thread.sleep(msdelay);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//Reset current marker
					screen.colorBar(j, Color.BLACK);
				}

				//Stats
				comparisons++;
				accesses+=2;

				//Only swap biggest and end if biggest is bigger
				if(a[big] > a[i]) {
					screen.swap(i, big);
					screen.colorBar(big, Color.BLACK);
					accesses+=2;
				}

				//Reset bar colours
				screen.colorBar(i, Color.BLACK);
				screen.colorBar(big, Color.BLACK);
			}

			break;
		case BUBBLE:
			for(int i = a.length-1; i >0; i--) {
				screen.colorBar(i, Color.BLUE);
				for(int j = 0; j < i; j++) {
					screen.colorBar(j, Color.RED);
					accesses+=2;
					comparisons++;
					if(a[j] > a[j+1]) {
						screen.swap(j, j+1);
						accesses+=2;
					}
					try {
						Thread.sleep(msdelay);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					screen.colorBar(j, Color.BLACK);
				}
				screen.colorBar(i, Color.BLACK);
			}
			break;
		case INSERTION:

			for(int i = 1; i < a.length-1; i++) {
				for(int j = i-1; j >=0; j--) {
					if(a[i] > a[j]) {
						screen.insert(i, j+1);
					}
				}
			}

			break;
		case QUICK:
			quickSort(a, 0, a.length-1);
			break;
		}
		screen.stop();
	}

	/**
	 * @return the accesses
	 */
	public int getAccesses() {
		return accesses;
	}

	/**
	 * @return the comparisons
	 */
	public int getComparisons() {
		return comparisons;
	}

	public void quickSort(int arr[], int begin, int end) {
		if (begin < end) {
			int partitionIndex = partition(arr, begin, end);

			quickSort(arr, begin, partitionIndex-1);
			quickSort(arr, partitionIndex+1, end);
		}
	}

	private int partition(int arr[], int begin, int end) {
		accesses++;
		int pivot = arr[end];
		int i = (begin-1);

		for (int j = begin; j < end; j++) {
			screen.colorBar(j, Color.RED);
			accesses++;
			comparisons++;
			if (arr[j] <= pivot) {
				i++;
				screen.colorBar(j, Color.BLACK);
				screen.swap(i, j);
				screen.colorBar(j, Color.RED);
				accesses+=2;
			}
			try {
				Thread.sleep(msdelay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			screen.colorBar(j, Color.BLACK);
		}

		screen.swap(end, i+1);
		accesses+=2;

		return i+1;
	}

}
