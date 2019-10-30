package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SquareGUI extends JPanel {
	private BufferedImage image;
	
	
	
	public void ImagePanel() {
		try {
			image = ImageIO.read(new File("../../img/whiterabbit.png"));
		} catch (IOException ex) {
			System.err.println("Unable to load file!");
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
}
