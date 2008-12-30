package org.gadilif.gmancala.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import org.gadilif.gmancala.model.BoardModel;
import org.gadilif.gmancala.model.PlayerType;
import org.gadilif.gmancala.view.listeners.ICellChangedListener;

/**
 * Does all the view operations, for testing purposes this is the separation.
 * @author Gadi
 *
 */
public class ViewDelegate implements ICellChangedListener {

	protected Object playLock = new Object();
	protected int play = -1;
	List<MancalaDrawableButton> buttonList = new ArrayList<MancalaDrawableButton>();
	static final int ROW_LEN = 6;
	JTextArea debugTextArea = new JTextArea();
	BoardModel model;

	public ViewDelegate(BoardModel model) {
		this.model = model;
		model.addCellChangedListener(this);
	}

	private void makebutton(JPanel hostingPanel, String name, int i,
			GridBagLayout gridbag, GridBagConstraints c, int x, int y,
			int width, int height) {

		MancalaDrawableButton button = new MancalaDrawableButton(""+i);
		//button.setNumberOfBalls(4);
		buttonList.add(button);
		c.gridx = x;
		c.gridy = y;
		c.gridheight = height;
		c.gridwidth = width;
		button.setEnabled(false);
		button.addActionListener(new PlayActionListener(i));
		gridbag.setConstraints(button, c);

		hostingPanel.add(button);
	}

	public void createComponents(Container container) {
		container.setLayout(new BorderLayout());
		JPanel mainPanel = new JPanel();
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		mainPanel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		mainPanel.setLayout(gridbag);

		c.fill = GridBagConstraints.BOTH;
		c.weighty = 1.0;
		c.weightx = 1.0;

		c.gridwidth = 1;
		c.gridheight = 2;
		int i = 0;
		makebutton(mainPanel, "Button", i, gridbag, c, 0, 0, 1, 2);

		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridheight = GridBagConstraints.RELATIVE;
		for (i = 1; i < ROW_LEN + 1; i++) {
			makebutton(mainPanel, "Button", i, gridbag, c, i, 1, 1, 1);
		}
		i = ROW_LEN + 1;
		c.gridheight = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
		makebutton(mainPanel, "Button", i, gridbag, c, i, 0, 1, 2);

		c.gridwidth = GridBagConstraints.RELATIVE;
		c.fill = GridBagConstraints.BOTH;
		c.gridheight = 1;
		c.gridwidth = 1;

		for (i = ROW_LEN + 2; i < ROW_LEN + 8; i++) {
			makebutton(mainPanel, "Button", i, gridbag, c, ROW_LEN
					- (i - ROW_LEN - 2), 0, 1, 1);
		}

		debugTextArea.setAutoscrolls(true);
		JScrollPane debugPanel = new JScrollPane(debugTextArea,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		debugPanel.setPreferredSize(new Dimension(0, 0));
		c.gridheight = 1;
		c.gridwidth = 8;
		c.weighty = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		gridbag.setConstraints(debugPanel, c);
		mainPanel.add(debugPanel);
		createMenu(container);
		container.add(mainPanel, BorderLayout.CENTER);
	}

	private void createMenu(Container container) {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		JMenuItem restartItem = new JMenuItem("Restart");
		restartItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				model.reset();
				synchronized(playLock) {
					playLock.notify();
				}
			}
		});
		fileMenu.add(restartItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);
		container.add(menuBar, BorderLayout.NORTH);
	}

	public void debug(String message) {
		debugTextArea.append(message + "\n");
		debugTextArea.setCaretPosition(debugTextArea.getText().length());
	}

	private void setButtonsState(final boolean state, final int start) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				for (int i = start; i < start + 6; i++) {
					buttonList.get(i).setEnabled(state);
				}
			}
		});
	}

	public int waitForPlay(PlayerType playerType) {
		int start = 1;
		if (playerType == PlayerType.TWO) {
			start = 8;
		}
		setButtonsState(true, start);
		synchronized (playLock) {
			try {
				playLock.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		setButtonsState(false, start);
		return play;
	}

	class PlayActionListener implements ActionListener {
		private int index;

		public PlayActionListener(int index) {
			this.index = index;
		}

		public void actionPerformed(ActionEvent e) {
			synchronized (playLock) {
				play = index;
				playLock.notify();
			}
		}
	}

	public void refresh() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				for (int i = 0; i < buttonList.size(); i++) {
					buttonList.get(i).setNumberOfBalls(model.getCellValue(i));
					buttonList.get(i).setText("" + model.getCellValue(i));
				}
			}
		});
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void cellChanged(int cellId, int newValue) {
		refresh();
	}

}
