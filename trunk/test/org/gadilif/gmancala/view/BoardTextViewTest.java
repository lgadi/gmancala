package org.gadilif.gmancala.view;

import java.io.OutputStream;
import java.io.PrintStream;

import org.gadilif.gmancala.model.BoardModel;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTextViewTest {
	@Test
	public void drawPasses() {
		BoardModel boardModel = new BoardModel();
		boardModel.init();
		BoardTextView boardTextView = new BoardTextView(boardModel);
		boardTextView.draw(System.out);
	}

	
	private class MyPrintStream extends PrintStream {

		int lineCount;
		public MyPrintStream(OutputStream out) {
			super(out);
		
		}

		@Override
		public void println(String x) {
			super.println(x);
			lineCount++;
		}
		
		public int getLineCount() {
			return lineCount;
		}
	}
	
	@Test
	public void drawToMyStream() {
		BoardModel boardModel = new BoardModel();
		boardModel.init();
		BoardTextView boardTextView = new BoardTextView(boardModel);
		MyPrintStream myPrintStream = new MyPrintStream(System.out);
		boardTextView.draw(myPrintStream);
		assertEquals(6, myPrintStream.getLineCount());
	}
}
