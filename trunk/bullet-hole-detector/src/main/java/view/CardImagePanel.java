package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import model.Card;
import model.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.ImageUtil;

@SuppressWarnings("serial")
public class CardImagePanel extends JPanel {
	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(CardImagePanel.class);
	
	private BufferedImage		rawImage;
	private Card				currentCard;
	private BufferedImage		scaledImage;
	
	public BufferedImage		offScreenImage;
	public Graphics2D			offScreen;
	
	public CardImagePanel(Model model) {
		model.addObserver(new ModelListenerCardImagePanel());
		
		buildImage();
		
		addComponentListener(new CardImagePanelListener());
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if (getWidth() > 0 && getHeight() > 0 && offScreen != null) {
			offScreen.setColor(getBackground());
			offScreen.fillRect(0, 0, getWidth(), getHeight());
			if (scaledImage != null) {
				int xOffset = (getWidth() - scaledImage.getWidth()) / 2;
				int yOffset = (getHeight() - scaledImage.getHeight()) / 2;
				offScreen.drawImage(scaledImage, xOffset, yOffset, this);
			} else {
				offScreen.setColor(Color.BLACK);
				offScreen.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
				offScreen.drawLine(0, 0, getWidth(), getHeight());
				offScreen.drawLine(0, getHeight(), getWidth(), 0);
			}
			
			g.drawImage(offScreenImage, 0, 0, this);
		}
	}
	
	private void buildImage() {
		if (getWidth() > 0 && getHeight() > 0) {
			buildOffScreen();
			
			if (rawImage != null) {
				buildScaledImage();
			}
		}
	}
	
	private void buildOffScreen() {
		offScreenImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		offScreen = offScreenImage.createGraphics();
	}
	
	private void buildScaledImage() {
		scaledImage = ImageUtil.scaleImagePreserveAspect(rawImage, getWidth(), getHeight());
	}
	
	private class CardImagePanelListener extends MouseInputAdapter implements ComponentListener {
		private Point	mouseLocation	= new Point(0, 0);
		private Point	startDragLocation;
		private Point	dragOffset		= new Point();
		
		private boolean	leftButton;
		private boolean	middleButton;
		private boolean	rightButton;
		
		@SuppressWarnings("unused")
		private boolean	mouseDragged;
		
		@SuppressWarnings("unused")
		public int		scrollLevel		= 0;
		
		@Override
		public void mouseMoved(MouseEvent e) {
			if (contains(e.getPoint())) {
				mouseLocation = e.getPoint();
			}
			
			// log.debug("Mouse moved - x: {}, y: {}", mouseLocation.x, mouseLocation.y);
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			if (contains(e.getPoint())) {
				mouseLocation = e.getPoint();
				if (startDragLocation == null) {
					startDragLocation = new Point(mouseLocation);
				} else {
					dragOffset.setLocation(mouseLocation.x - startDragLocation.x, mouseLocation.y - startDragLocation.y);
				}
				mouseDragged = true;
				
				// log.debug("Drag - left: {}, middle: {}, right: {}", new Object[] {
				// leftButton, middleButton, rightButton });
			}
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {}
		
		@Override
		public void mouseEntered(MouseEvent e) {}
		
		@Override
		public void mouseExited(MouseEvent e) {}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if (contains(e.getPoint())) {
				leftButton = e.getButton() == MouseEvent.BUTTON1 ? true : leftButton;
				middleButton = e.getButton() == MouseEvent.BUTTON2 ? true : middleButton;
				rightButton = e.getButton() == MouseEvent.BUTTON3 ? true : rightButton;
				
				// log.debug("Press - left: {}, middle: {}, right: {}", new Object[]
				// { leftButton, middleButton, rightButton });
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			leftButton = e.getButton() == MouseEvent.BUTTON1 ? false : leftButton;
			middleButton = e.getButton() == MouseEvent.BUTTON2 ? false : middleButton;
			rightButton = e.getButton() == MouseEvent.BUTTON3 ? false : rightButton;
			
			if (!leftButton && !middleButton && !rightButton) {
				startDragLocation = null;
				dragOffset.setLocation(0, 0);
				mouseDragged = false;
			}
			
			// log.debug("Release - left: {}, middle: {}, right: {}", new Object[] {
			// leftButton, middleButton, rightButton });
		}
		
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			if (contains(e.getPoint())) {
				scrollLevel += e.getWheelRotation();
				
				// log.debug("Mouse Wheel Moved - amount: {}", e.getWheelRotation());
			}
		}
		
		@Override
		public void componentHidden(ComponentEvent e) {}
		
		@Override
		public void componentMoved(ComponentEvent e) {}
		
		@Override
		public void componentResized(ComponentEvent e) {
			buildImage();
			repaint();
		}
		
		@Override
		public void componentShown(ComponentEvent arg0) {}
	}
	
	public class ModelListenerCardImagePanel implements Observer {
		@Override
		public void update(Observable o, Object arg) {
			if (o instanceof Model) {
				Model model = (Model) o;
				if (model.getCurrentCard() != currentCard) {
					if (model.getCurrentCardIndex() >= 0) {
						currentCard = model.getCurrentCard();
						try {
							rawImage = ImageUtil.loadImage(model.getCurrentCard().getImageFile());
							buildImage();
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						rawImage = null;
						scaledImage = null;
					}
					repaint();
				}
			}
		}
	}
}
