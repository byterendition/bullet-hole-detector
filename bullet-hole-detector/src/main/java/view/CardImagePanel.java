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

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import model.Model;
import controller.ImageUtil;

@SuppressWarnings("serial")
public class CardImagePanel extends JPanel {
	private BufferedImage	rawImage;
	private BufferedImage	scaledImage;
	
	public BufferedImage	offScreenImage;
	public Graphics2D		offScreen;
	
	private Model			model;
	
	public CardImagePanel(Model model) {
		this.model = model;
		
		buildImage();
		
		addComponentListener(new CardImagePanelListener());
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if (getWidth() > 0 && getHeight() > 0 && offScreen != null) {
			offScreen.setColor(getBackground());
			offScreen.fillRect(0, 0, getWidth(), getHeight());
			if (scaledImage != null) {
				offScreen.drawImage(scaledImage, 0, 0, this);
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
		// offScreen.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		// offScreen.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		// offScreen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
	private void buildScaledImage() {
		ImageUtil.scaleImagePreserveAspect(rawImage, getWidth(), getHeight(), this);
	}
	
	private class CardImagePanelListener extends MouseInputAdapter implements ComponentListener {
		private Point	mouseLocation	= new Point(0, 0);
		private Point	startDragLocation;
		private Point	dragOffset		= new Point();
		
		private boolean	leftButton;
		private boolean	middleButton;
		private boolean	rightButton;
		private boolean	mouseDragged;
		
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
}
