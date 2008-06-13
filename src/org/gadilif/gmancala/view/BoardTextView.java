package org.gadilif.gmancala.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.gadilif.gmancala.model.BoardModel;
import org.gadilif.gmancala.model.PlayerType;

public class BoardTextView implements IBoardView {

	private PrintStream out = System.out;
	
	private BoardModel boardModel;
	public BoardTextView(final BoardModel boardModel) {
		this.boardModel = boardModel;
		
	}

	
	private String format3(final int value) {
		String spaces = "";
		if (value < 10) {
			spaces = "  ";
		}
		else if (value < 100) {
			spaces = " ";
		}
		return spaces+value;
	}
	
	/* (non-Javadoc)
	 * @see org.gadilif.gmancala.view.IBoardView#draw()
	 */
	public void refresh() {
		out.println("Board:");
		String separator = "+---+---+---+---+---+---+";
		out.println("   "+separator+"   ");
		
		out.print("   |");
		for (int i=13;i>boardModel.getRightHole();i--) {
			out.print(format3(boardModel.getCellValue(i)));
			out.print("|");
		}
		out.println("");
		
		out.println(format3(boardModel.getLeftHoleValue()) +
			  separator +
			  format3(boardModel.getRightHoleValue()));
		
		out.print("   |");
		for (int i=1;i<boardModel.getRightHole();i++) {
			out.print(format3(boardModel.getCellValue(i)));
			out.print("|");
		}
		out.println("");
		out.println("   "+separator+"   ");
		
	}


	/* (non-Javadoc)
	 * @see org.gadilif.gmancala.view.IBoardView#debug(java.lang.String)
	 */
	public void debug(final String message) {
		out.println(message);
	}


	/* (non-Javadoc)
	 * @see org.gadilif.gmancala.view.IBoardView#setOut(java.io.PrintStream)
	 */
	public void setOut(final PrintStream out) {
		this.out = out;
	}


	public int getPlay(final PlayerType playerType) {
		System.out.print("Enter move for player "+playerType+": ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			return Integer.parseInt(reader.readLine());
		}
		catch (IOException ioe) {
			ioe.printStackTrace(System.err);
		}
		return -1;
	}

}
