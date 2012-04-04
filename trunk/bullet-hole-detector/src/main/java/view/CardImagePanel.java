package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class CardImagePanel extends JPanel {
	private static final long	serialVersionUID	= 1L;
	
	public BufferedImage		offScreenImage;
	public Graphics2D			offScreen;
	
	public CardPanel			cardPanel;
	
	public Point				mouseLocation		= new Point(0, 0);
	public Point				zoomLocation		= new Point(0, 0);
	public int					zoomSize			= 64;
	
	public int					imageWidth;
	public int					imageHeight;
	public int					imageX;
	public int					imageY;
	
	public double				zoomScale;
	
	public CardImagePanel(CardPanel cardPanel) {
		this.cardPanel = cardPanel;
		
		offScreenImage = new BufferedImage(320, 240, BufferedImage.TYPE_INT_ARGB);
		offScreen = offScreenImage.createGraphics();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		offScreen.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		offScreen.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		offScreen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		offScreen.setColor(getBackground());
		offScreen.fillRect(0, 0, getWidth(), getHeight());
		
		if (cardPanel.getCurrentCard() != null) {
			drawImage(cardPanel.getCurrentCard().getImage(), offScreen);
		}
		
		g.drawImage(offScreenImage, 0, 0, this);
	}
	
	private void drawImage(BufferedImage image, Graphics2D g) {
		double imageRatio = (double) image.getWidth() / (double) image.getHeight();
		double panelRatio = (double) getWidth() / (double) getHeight();
		
		imageWidth = (int) (imageRatio > panelRatio ? getWidth() : getHeight() * imageRatio);
		imageHeight = (int) (imageRatio > panelRatio ? getWidth() / imageRatio : getHeight());
		
		imageX = (getWidth() - imageWidth) / 2;
		imageY = (getHeight() - imageHeight) / 2;
		
		g.drawImage(image, imageX, imageY, imageWidth, imageHeight, null);
		
		zoomScale = (double) imageWidth / (double) image.getWidth();
		int scaledZoomSize = (int) (zoomSize * zoomScale);
		
		zoomLocation = mouseLocation.getLocation();
		zoomLocation.x -= scaledZoomSize / 2;
		zoomLocation.y -= scaledZoomSize / 2;
		
		if (zoomLocation.x < imageX) {
			zoomLocation.x = imageX;
		}
		if (zoomLocation.y < imageY) {
			zoomLocation.y = imageY;
		}
		if (zoomLocation.x > imageX + imageWidth - scaledZoomSize) {
			zoomLocation.x = imageX + imageWidth - scaledZoomSize;
		}
		if (zoomLocation.y > imageY + imageHeight - scaledZoomSize) {
			zoomLocation.y = imageY + imageHeight - scaledZoomSize;
		}
		
		g.setColor(Color.BLACK);
		
		g.drawRect(zoomLocation.x, zoomLocation.y, scaledZoomSize - 1, scaledZoomSize - 1);
		
		g.drawRect(imageX, imageY, imageWidth - 1, imageHeight - 1);
	}
}
