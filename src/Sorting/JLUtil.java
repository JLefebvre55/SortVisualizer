package Sorting;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class JLUtil {

	public static void bubble(int [] a) {
		for(int i = a.length-1; i >0; i--) {
			for(int j = 0; j < i; j++) {
				if(a[j] > a[j+1]) {
					int temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
				}
			}
		}
	}
	
	public static void selection(int [] a) {
		
		for(int i = a.length-1; i> 0; i--) {
			int big = 0;
			for(int j = 0; j < i; j++) {
				if(a[j] > a[big]) {
					big = j;
				}
			}
			int temp = a[i];
			a[i] = a[big];
			a[big] = temp;
		}
	}
	
	public static int [] randArray(int n, int m) {
		int a [] = new int [n];
		for(int i = 0; i < n; i++) {
			a[i] = (int)(Math.random()*m);
		}
		return a;
	}
	
	public static int [] randSequentialArray(int n) {
		int a [] = new int [n];
		for(int i = 0; i < n; i++) {
			a[i] = i;
		}
		for(int i = 0; i < n*2; i++) {
			swap(a, (int)(Math.random()*n), (int)(Math.random()*n));
		}
		return a;
	}
	
	private static void swap(int[] a, int i, int j) {
		// TODO Auto-generated method stub
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void printArray(int [] a) {
		for(int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}
	
	public static void main(String[] args) throws LineUnavailableException {
        final AudioFormat af =
            new AudioFormat(Note.SAMPLE_RATE, 8, 1, true, true);
        SourceDataLine line = AudioSystem.getSourceDataLine(af);
        line.open(af, Note.SAMPLE_RATE);
        line.start();
        for  (Note n : Note.values()) {
            play(line, n, 500);
            play(line, Note.REST, 10);
        }
        line.drain();
        line.close();
    }

    private static void play(SourceDataLine line, Note note, int ms) {
        ms = Math.min(ms, Note.SECONDS * 1000);
        int length = Note.SAMPLE_RATE * ms / 1000;
        int count = line.write(note.data(), 0, length);
    }
	
}

enum Note {

    REST, A4, A4$, B4, C4, C4$, D4, D4$, E4, F4, F4$, G4, G4$, A5;
    public static final int SAMPLE_RATE = 16 * 1024; // ~16KHz
    public static final int SECONDS = 2;
    private byte[] sin = new byte[SECONDS * SAMPLE_RATE];

    Note() {
        int n = this.ordinal();
        if (n > 0) {
            double exp = ((double) n - 1) / 12d;
            double f = 440d * Math.pow(2d, exp);
            for (int i = 0; i < sin.length; i++) {
                double period = (double)SAMPLE_RATE / f;
                double angle = 2.0 * Math.PI * i / period;
                sin[i] = (byte)(Math.sin(angle) * 127f);
            }
        }
    }

    public byte[] data() {
        return sin;
    }
}
