package org.gadilif.gmancala.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.gadilif.gmancala.model.BoardModel;
import org.gadilif.gmancala.model.PlayerType;

import com.sun.corba.se.impl.ior.iiop.JavaSerializationComponent;

public class BoardSwingView extends JFrame implements IBoardView {

	List<JButton> buttonList = new ArrayList<JButton>();

	private static final long serialVersionUID = 1L;
	final static int ROW_LEN = 6;
	BoardModel model;
	protected void makebutton(JPanel hostingPanel, String name, int i, GridBagLayout gridbag,	GridBagConstraints c, int x, int y, int width, int height) {
		
		JButton button = new JButton("button "+i);
		buttonList.add(button);
		c.gridx = x;
		c.gridy = y;
		c.gridheight = height;
		c.gridwidth = width;
		gridbag.setConstraints(button, c);
		hostingPanel.add(button);
	}

	JTextArea debugTextArea = new JTextArea();
	
	public BoardSwingView(BoardModel model) {
		super();
		this.model = model;
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
				System.exit(0);
			}
		
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
		
			}
		
		});
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
		JScrollPane debugPanel = new JScrollPane(debugTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		c.gridheight = GridBagConstraints.REMAINDER;
		c.gridheight = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.gridwidth = 8;
		c.gridx = 0;
		c.gridy = 2;
		gridbag.setConstraints(debugPanel, c);
		mainPanel.add(debugPanel);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		//debugPanel.setPreferredSize(new Dimension(700,100));
		//debugTextArea.setPreferredSize(new Dimension(700,100));
		
		//getContentPane().add(debugPanel, BorderLayout.SOUTH);
	}


	


	public void debug(String message) {
		debugTextArea.append(message+"\n");
		debugTextArea.setCaretPosition(debugTextArea.getText().length());
	}


	public int getPlay(PlayerType playerType) {
		return 0;
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
	
}
