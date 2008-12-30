package org.gadilif.gmancala.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;

/**
 * Customizable button, displays the contents with play items..
 * @author Gadi
 *
 */
public final class MancalaDrawableButton extends JButton {

	private static final long serialVersionUID = 1L;
	private int numberOfBalls;
	private List<XYC> xyc = new ArrayList<XYC>();
	private Color[] colors = new Color[]{Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.RED, Color.RED, Color.PINK, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.YELLOW};
	private static List<Color> balls = new ArrayList<Color>();
	private final Random rng = new Random();
	
		
	public MancalaDrawableButton(String string) {
		for (int i=0;i<48;i++) {
			balls.add(colors[rng.nextInt(colors.length)]);
		}
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
			Color c = balls.remove(0);
			xyc.add(new XYC((int)(midX-midX/2+Math.random()*midX), (int)(midY-midY/2+Math.random()*midY/2), c));
		}
		}
		else {
			for (int i=0;i>delta;i--) {
				Color c = xyc.remove(0).c;
				balls.add(0, c);
			}
		}
	}

	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Color oldColor = g.getColor();
		
		for (XYC pos:xyc) {
			g.setColor(pos.c);
			g.fillRoundRect(pos.x, pos.y, 20, 15, 15, 15);
		}
		
		g.setColor(Color.RED);
		g.drawString(xyc.size()+"", getWidth()/2, getHeight()-10);
		g.setColor(oldColor);
		
	}
	
	private static class XYC {
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
