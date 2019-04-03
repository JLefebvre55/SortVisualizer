package Sorting;

public enum Sorts {
	INSERTION, BUBBLE, SELECTION, QUICK;
	
	@Override
	public String toString() {
		return name().substring(0, 1)+name().substring(1).toLowerCase();
	}
}
