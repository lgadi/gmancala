package org.gadilif.gmancala.data;

import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {
	@Test
	public void boardInit() {
		Board board = new Board();
		assertFalse("board should not be initialized at that time", board.isInitialized());
		board.init();
		assertTrue("board should be initialized now", board.isInitialized());
		
	}

}
