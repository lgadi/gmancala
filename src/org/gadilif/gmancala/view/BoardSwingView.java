package org.gadilif.gmancala.view;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import org.gadilif.gmancala.model.BoardModel;
import org.gadilif.gmancala.model.PlayerType;

public class BoardSwingView extends JFrame implements IBoardView {

	private static final long serialVersionUID = 1L;

	protected BoardModel model;

	ViewDelegate viewDelegate;

	public BoardSwingView(BoardModel model) {
		this.model = model;
	}

	public void init() {
		viewDelegate = new ViewDelegate(model);
		viewDelegate.createComponents(this.getContentPane());
		setSize(700, 300);
		setVisible(true);
		
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

	public void debug(String message) {
		viewDelegate.debug(message);
	}

	public int getPlay(PlayerType playerType) {
		return viewDelegate.waitForPlay(playerType);
	}

	public void refresh() {
		viewDelegate.refresh();
	}
}
