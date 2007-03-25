package org.gadilif.gmancala.view;

import java.io.PrintStream;

import org.gadilif.gmancala.model.BoardModel;

public class BoardTextView {

	private BoardModel boardModel;
	public BoardTextView(BoardModel boardModel) {
		this.boardModel = boardModel;
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
	
	public void draw(PrintStream out) {
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

}
