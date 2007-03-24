package org.gadilif.gmancala.model;

public class Board {

	int[] cells = new int[14];
	private boolean initialized;
	public void init() {
		for (int i=0;i<14;i++) {
			cells[i] = 4;
		}
		cells[0] = 0;
		cells[7] = 0;
		this.initialized = true;
	}

	public boolean isInitialized() {
		return this.initialized;
	}

	public int getCellValue(int i) {
		return cells[i];
	}

	public void play(int start) {
		int cell = start;
		while (cells[start] > 0) {
			cells[(++cell)%14]++;
			cells[start]--;
		}
	}
	
	private String format3(int value) {
		String spaces = "";
		if (value < 10) {
			spaces = "  ";
		}
		else if (value < 100) {
			spaces = " ";
		}
		return spaces+value;
	}
	public String toString() {
		StringBuilder result = new StringBuilder("Board:\n");
		result.append("   +---+---+---+---+---+---+   \n");
		
		result.append("   |");
		for (int i=13;i>7;i--) {
			result.append(format3(cells[i]));
			result.append("|");
		}
		result.append("\n");
		result.append(format3(cells[0]));
		result.append("+---+---+---+---+---+---+");
		result.append(format3(cells[7]));
		result.append("\n");
		result.append("   |");
		for (int i=1;i<7;i++) {
			result.append(format3(cells[i]));
			result.append("|");
		}
		result.append("\n");
		result.append("   +---+---+---+---+---+---+   \n");
		return result.toString();
	}

	public int getRightHoleValue() {
		return cells[7];
	}

	public int getLeftHoleValue() {
		return cells[0];
	}

	public boolean canPlay(int i) {
		return cells[i] > 0;
	}

}
