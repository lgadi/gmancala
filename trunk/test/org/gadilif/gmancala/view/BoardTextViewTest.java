package org.gadilif.gmancala.view;

import java.io.OutputStream;
import java.io.PrintStream;

import org.gadilif.gmancala.model.BoardModel;
import org.gadilif.gmancala.view.listeners.ICellChangedListener;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTextViewTest {
	
	
	BoardModel boardModel;
	BoardTextView boardTextView;
	
	@Before
	public void before() {
		boardModel = new BoardModel();
		boardModel.init();
		boardTextView = new BoardTextView(boardModel);
		boardTextView.init();
	}

	private static class MyPrintStream extends PrintStream {
		int lineCount;
		public MyPrintStream(final OutputStream out) {
			super(out);
		}

		@Override
		public void println(final String x) {
			super.println(x);
			lineCount++;
		}
		
		public int getLineCount() {
			return lineCount;
		}
	}
	
	@Test
	public void drawToMyStream() {
		MyPrintStream myPrintStream = new MyPrintStream(System.out);
		boardTextView.setOut(myPrintStream);
		boardTextView.refresh();
		assertEquals(6, myPrintStream.getLineCount());
	}
	
	@Test
	public void testDebugging() {
		MyPrintStream myPrintStream = new MyPrintStream(System.out);
		boardTextView.setOut(myPrintStream);
		boardModel.addCellChangedListener(new ICellChangedListener() {
			public void cellChanged(final int cellId, final int newValue) {
				boardTextView.debug("cell #"+cellId+" new value is "+newValue);
			}
		});
		boardModel.play(4);
		assertEquals(8, myPrintStream.getLineCount());
	}
}
