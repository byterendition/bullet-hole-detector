package listeners;

import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;

import javax.swing.event.MouseInputAdapter;

import model.Card;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import view.CardImagePanel;
import view.CardZoomImagePanel;

public class CardImagePanelListener extends MouseInputAdapter implements ComponentListener {
	private static final Logger	log				= LoggerFactory.getLogger(CardImagePanelListener.class);
	
	private CardImagePanel		cardImagePanel;
	private CardZoomImagePanel	cardZoomImagePanel;
	
	public Point				mouseLocation	= new Point();
	public Point				startDragLocation;
	public Point				dragOffset		= new Point();
	
	public boolean				leftButton;
	public boolean				middleButton;
	public boolean				rightButton;
	public boolean				mouseDragged;
	
	public int					scrollLevel		= 0;
	
	public CardImagePanelListener(CardImagePanel cardImagePanel, CardZoomImagePanel cardZoomImagePanel) {
		this.cardImagePanel = cardImagePanel;
		this.cardZoomImagePanel = cardZoomImagePanel;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if (cardImagePanel.contains(e.getPoint())) {
			mouseLocation = e.getPoint();
		}
		
		cardImagePanel.mouseLocation = mouseLocation;
		cardImagePanel.repaint();
		
		cardZoomImagePanel.x = (int) ((cardImagePanel.zoomLocation.x - cardImagePanel.imageX) / cardImagePanel.zoomScale);
		cardZoomImagePanel.y = (int) ((cardImagePanel.zoomLocation.y - cardImagePanel.imageY) / cardImagePanel.zoomScale);
		
		Card currentCard = cardImagePanel.cardPanel.getCurrentCard();
		if (currentCard != null) {
			if (cardZoomImagePanel.x > currentCard.getImage().getWidth() - 64) {
				cardZoomImagePanel.x = currentCard.getImage().getWidth() - 64;
			}
			if (cardZoomImagePanel.y > currentCard.getImage().getHeight() - 64) {
				cardZoomImagePanel.y = currentCard.getImage().getHeight() - 64;
			}
		}
		
		cardZoomImagePanel.repaint();
		
		// log.debug("Mouse moved - x: {}, y: {}", mouseLocation.x, mouseLocation.y);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (cardImagePanel.contains(e.getPoint())) {
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
		if (cardImagePanel.contains(e.getPoint())) {
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
		if (cardImagePanel.contains(e.getPoint())) {
			scrollLevel += e.getWheelRotation();
			
			// log.debug("Mouse Wheel Moved - amount: {}", e.getWheelRotation());
		}
	}
	
	public void componentHidden(ComponentEvent arg0) {}
	
	public void componentMoved(ComponentEvent arg0) {}
	
	public void componentResized(ComponentEvent arg0) {
		cardImagePanel.offScreenImage = new BufferedImage(cardImagePanel.getWidth() > 0 ? cardImagePanel.getWidth() : 1, cardImagePanel.getHeight() > 0 ? cardImagePanel.getHeight() : 1, BufferedImage.TYPE_INT_ARGB);
		cardImagePanel.offScreen = cardImagePanel.offScreenImage.createGraphics();
	}
	
	public void componentShown(ComponentEvent arg0) {}
}
