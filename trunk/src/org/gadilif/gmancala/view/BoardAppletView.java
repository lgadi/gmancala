package org.gadilif.gmancala.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import org.gadilif.gmancala.controller.BoardController;
import org.gadilif.gmancala.model.BoardModel;
import org.gadilif.gmancala.model.PlayerType;
import org.gadilif.gmancala.strategies.HumanPlayer;
import org.gadilif.gmancala.strategies.SingleTurnLookAheadPlayer;

public class BoardAppletView extends JApplet {

	
	@Override
	public void destroy() {
		super.destroy();
		System.out.println("destroy");
	}

	BoardModel model = null;
	@Override
	public void init() {
		super.init();
		System.out.println("init");
		model = new BoardModel();
		model.init();
	
		
		getContentPane().setLayout(new BorderLayout());
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
		makebutton(mainPanel, "Button", i,  gridbag, c, 0 , 0, 1, 2);
		
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridheight = GridBagConstraints.RELATIVE;
		for (i = 1;i<ROW_LEN + 1;i++) {
			makebutton(mainPanel, "Button", i, gridbag, c, i, 1, 1, 1);
		}
		i = ROW_LEN+1;
		c.gridheight = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
		makebutton(mainPanel, "Button", i, gridbag, c, i, 0, 1, 2);

		c.gridwidth = GridBagConstraints.RELATIVE;
		c.fill = GridBagConstraints.BOTH;
		c.gridheight = 1;
		c.gridwidth = 1;
		
		for (i = ROW_LEN+2;i<ROW_LEN+8;i++) {
			makebutton(mainPanel, "Button", i, gridbag, c, ROW_LEN-(i-ROW_LEN-2), 0, 1, 1);
		}
		
		
		setSize(700, 300);
		debugTextArea.setAutoscrolls(true);
		JScrollPane debugPanel = new JScrollPane(debugTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		debugPanel.setPreferredSize(new Dimension(0,0));
		c.gridheight = 1;
		c.gridwidth = 8;
		c.weighty = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		gridbag.setConstraints(debugPanel, c);
		mainPanel.add(debugPanel);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		IBoardView view = new BoardSwingView(model);
		view.setVisible(true);
		BoardController controller = new BoardController(model, view);
		//controller.setPlayer1(new SingleTurnLookAheadPlayer(PlayerType.ONE, controller));
		controller.setPlayer1(new HumanPlayer(PlayerType.ONE, controller));
		controller.setPlayer2(new SingleTurnLookAheadPlayer(PlayerType.TWO, controller));
		controller.run();
		
	}

	@Override
	public void start() {
		super.start();
		System.out.println("start");
	}

	@Override
	public void stop() {
		super.stop();
		System.out.println("stop");
	}
	
	List<JButton> buttonList = new ArrayList<JButton>();

	private static final long serialVersionUID = 1L;
	final static int ROW_LEN = 6;
	protected void makebutton(JPanel hostingPanel, String name, int i, GridBagLayout gridbag,	GridBagConstraints c, int x, int y, int width, int height) {
		
		JButton button = new JButton("button "+i);
		
		buttonList.add(button);
		c.gridx = x;
		c.gridy = y;
		c.gridheight = height;
		c.gridwidth = width;
		button.setEnabled(false);
		button.addActionListener(new PlayActionListener(i));
		gridbag.setConstraints(button, c);
	
		hostingPanel.add(button);
		System.out.println(button.getGraphics());
	}

	JTextArea debugTextArea = new JTextArea();

	Object playLock = new Object();

	int play = -1;
	
	
	

	


	public void debug(String message) {
		debugTextArea.append(message+"\n");
		debugTextArea.setCaretPosition(debugTextArea.getText().length());
		
	}


	public int getPlay(PlayerType playerType) {	
		return waitForPlay(playerType);
	}


	private void setButtonsState(final boolean state, final int start) {
		SwingUtilities.invokeLater(new Runnable(){
		
			public void run() {
				for (int i=start;i<start+6;i++) {
					buttonList.get(i).setEnabled(state);
				}
			}
		
		});
		
		
	}
	private int waitForPlay(PlayerType playerType) {
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

	public void refresh() {
		SwingUtilities.invokeLater(new Runnable() {
		
			public void run() {
				for (int i=0;i<buttonList.size();i++) {
					buttonList.get(i).setText(""+model.getCellValue(i));
				}
			}
			
		});
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private class PlayActionListener implements ActionListener {
		private int index;
		public PlayActionListener(int index) {
			this.index = index;
		}
		public void actionPerformed(ActionEvent e) {
			synchronized(playLock) {
				play = index;
				playLock.notify();
			}
		}
		
	}

}
