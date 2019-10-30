package gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GridGUI extends JPanel {
	
	private static final int RECT_WIDTH = 30;
	private static final int RECT_HEIGHT = 30;
	
	private Graphics g;

	public GridGUI() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(250,250));
	}
	
	public void printGrid(Graphics g) {
		for(int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				g.drawRect((i*50), (j*50), RECT_WIDTH, RECT_HEIGHT);
			}
		}
	}

}
