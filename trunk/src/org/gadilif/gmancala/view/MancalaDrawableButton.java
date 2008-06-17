package org.gadilif.gmancala.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class MancalaDrawableButton extends JButton {

	private static final long serialVersionUID = 1L;
	private int numberOfBalls;
	private List<XYC> xyc = new ArrayList<XYC>();
	Color[] colors = new Color[]{Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.WHITE, Color.RED, Color.PINK, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.YELLOW};
	
		
	public MancalaDrawableButton(String string) {
	//	super(string);
	}

	@Override
	public void setText(String text) {
		revalidate();
		repaint();
		
	}
	public void setNumberOfBalls(int numberOfBalls) {
		int delta = numberOfBalls - this.numberOfBalls;
		this.numberOfBalls = numberOfBalls;
		initXY(delta);
	}
	
	private void initXY(int delta) {
		int midX = getWidth()/2;
		int midY = getHeight()/2;
		if (delta > 0) {
		for (int i=0;i<delta;i++) {
			xyc.add(new XYC((int)(midX-midX/2+Math.random()*midX), (int)(midY-midY/2+Math.random()*midY/2), colors[(int)(Math.random()*10)]));
		}
		}
		else {
			for (int i=0;i>delta;i--) {
				xyc.remove(0);
			}
		}
	}

	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Color old = g.getColor();
		
		System.out.println(getX()+", "+getY());
		for (XYC pos:xyc) {
			g.setColor(pos.c);
			g.fillOval(pos.x, pos.y, 15, 15);
		}
		
		g.setColor(Color.BLACK);
		g.drawString(xyc.size()+"", getWidth()/2, getHeight()-20);
		g.setColor(old);
		
	}
	
	private class XYC {
		public int x;
		public int y;
		public Color c;
		public XYC(int x, int y, Color c) {
			this.x = x;
			this.y = y;
			this.c = c;
		}
	}

}
