package org.gadilif.gmancala.view;

import java.io.PrintStream;

public interface IBoardView {

	void draw();

	void debug(String message);

	void setOut(PrintStream out);

}