/**
 * 
 */
package org.gadilif.gmancala.strategies;

public enum PlayerType {
	ONE(1, 7, 7), TWO(8, 14, 0);
	
	private final int start;
	private final int end;
	private final int home;
	PlayerType(final int start, final int end, final int home) {
		this.start = start;
		this.end = end;
		this.home = home;
	}
	public int getEnd() {
		return end;
	}
	public int getHome() {
		return home;
	}
	public int getStart() {
		return start;
	}
}