package org.gadilif.gmancala.view;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import org.gadilif.gmancala.model.BoardModel;

public class BoardSwingView extends AbstractBoardView implements IBoardView {

	private static final long serialVersionUID = 1L;

	public BoardSwingView(BoardModel model) {
		super();
		this.model = model;
	}
	
	@Override
	public void viewInit() {
		this.addWindowListener(new WindowListener() {
			
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
			}
	
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
			}
	
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
			}
	
			public void windowDeactivated(WindowEvent e) {
				// TODO
			}
	
			public void windowClosing(WindowEvent e) {
				System.out.println("Window closing");
				System.exit(0);
			}
	
			public void windowClosed(WindowEvent e) {
				System.out.println("Window closed");
			}
	
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}

	
}
