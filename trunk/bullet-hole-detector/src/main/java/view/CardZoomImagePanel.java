package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class CardZoomImagePanel extends JPanel {
	private static final long	serialVersionUID	= 1L;
	
	private BufferedImage		offScreenImage;
	private Graphics2D			offScreen;
	
	private CardZoomPanel		cardZoomPanel;
	
	private int					x, y, size;
	
	public CardZoomImagePanel(CardZoomPanel cardZoomPanel, int size) {
		this.cardZoomPanel = cardZoomPanel;
		
		this.size = size;
		x = 0;
		y = 0;
		
		offScreenImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
		offScreen = offScreenImage.createGraphics();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if (x >= 0 && y >= 0 && cardZoomPanel.getCurrentCard() != null) {
			BufferedImage image = cardZoomPanel.getCurrentCard().getImage();
			BufferedImage subImage = image.getSubimage(x, y, size, size);
			offScreen.drawImage(subImage, 0, 0, null);
			
			offScreen.setColor(Color.BLACK);
			offScreen.drawRect(0, 0, size - 1, size - 1);
		}
		
		g.drawImage(offScreenImage, 0, 0, this);
	}
}
