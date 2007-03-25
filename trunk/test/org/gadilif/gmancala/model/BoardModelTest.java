package org.gadilif.gmancala.model;

import org.gadilif.gmancala.model.BoardModel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardModelTest {
	
	private BoardModel board;
	
	@Before
	public void init() {
		board = new BoardModel();
		board.init();
	}
	
	@Test
	public void boardInit() {
		assertTrue("board should be initialized now", board.isInitialized());
	}
	
	@Test
	public void getCellValue() {
		assertEquals(4, board.getCellValue(1));
		System.out.println(board);
	}
	
	@Test
	public void getCellAfterPlay() {
		board.play(4);
		assertEquals(0, board.getCellValue(4));
		assertEquals(1, board.getRightHoleValue());
		System.out.println(board);
	}
	
	@Test
	public void getCellAfterPlay2() {
		board.play(11);
		assertEquals(0, board.getCellValue(11));
		assertEquals(1, board.getLeftHoleValue());
		System.out.println(board);
	}
	
	@Test
	public void consecutivePlays() {
		board.play(4);
		assertEquals(1, board.getRightHoleValue());
		assertFalse(board.canPlay(4));
		board.play(4);
		assertEquals(1, board.getRightHoleValue());
		board.play(5);
		assertEquals(2, board.getRightHoleValue());
		System.out.println(board);
	}

}
