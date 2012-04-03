package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import controller.ImageUtil;

public class CardImagePanel extends JPanel {
	private static final long	serialVersionUID	= 1L;
	
	private BufferedImage		offScreenImage;
	private Graphics2D			offScreen;
	
	private CardPanel			cardPanel;
	
	public CardImagePanel(CardPanel cardPanel) {
		this.cardPanel = cardPanel;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		offScreenImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		offScreen = offScreenImage.createGraphics();
		offScreen.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		offScreen.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		offScreen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		offScreen.setColor(getBackground());
		offScreen.fillRect(0, 0, getWidth(), getHeight());
		
		if (cardPanel.getCurrentCard() != null) {
			drawImage(cardPanel.getCurrentCard().getImage(), offScreen);
		} else {
			drawImage(ImageUtil.getNoImage(), offScreen);
		}
		
		g.drawImage(offScreenImage, 0, 0, this);
	}
	
	private void drawImage(BufferedImage image, Graphics2D g) {
		double imageRatio = (double) image.getWidth() / (double) image.getHeight();
		double panelRatio = (double) getWidth() / (double) getHeight();
		
		int imageWidth = (int) (imageRatio > panelRatio ? getWidth() : getHeight() * imageRatio);
		int imageHeight = (int) (imageRatio > panelRatio ? getWidth() / imageRatio : getHeight());
		
		int imageX = (getWidth() - imageWidth) / 2;
		int imageY = (getHeight() - imageHeight) / 2;
		
		g.drawImage(image, imageX, imageY, imageWidth, imageHeight, null);
		
		g.setColor(Color.BLACK);
		g.drawRect(imageX, imageY, imageWidth - 1, imageHeight - 1);
	}
	
}
