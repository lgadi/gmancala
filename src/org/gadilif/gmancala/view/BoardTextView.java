package org.gadilif.gmancala.view;

import java.io.PrintStream;

import org.gadilif.gmancala.model.BoardModel;

public class BoardTextView {

	private PrintStream out;
	
	private BoardModel boardModel;
	public BoardTextView(BoardModel boardModel, PrintStream out) {
		this.boardModel = boardModel;
		this.out = out;
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
	
	public void draw() {
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


	public void debug(String message) {
		out.println(message);
	}


	public void setOut(PrintStream out) {
		this.out = out;
	}

}
