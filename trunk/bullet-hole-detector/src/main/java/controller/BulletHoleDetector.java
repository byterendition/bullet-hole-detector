package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import listeners.CardContainerListener;
import listeners.CardImagePanelListener;
import listeners.CardListener;
import listeners.MenuListener;
import model.Card;
import model.CardContainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import view.CardImagePanel;
import view.MainWindow;

public class BulletHoleDetector {
	private static final Logger		log	= LoggerFactory.getLogger(BulletHoleDetector.class);
	
	public CardContainer			cardContainer;
	public MainWindow				mainWindow;
	
	private CardListener			cardListener;
	private CardContainerListener	cardContainerListener;
	private CardImagePanelListener	cardImagePanelListener;
	
	public BulletHoleDetector() {
		cardContainer = new CardContainer();
		
		mainWindow = new MainWindow(cardContainer, new MenuListener(this));
		
		CardImagePanel cardImagePanel = mainWindow.cardPanel.cardImagePanel;
		cardImagePanelListener = new CardImagePanelListener(cardImagePanel, mainWindow.cardZoomPanel.cardZoomImagePanel);
		cardImagePanel.addMouseListener(cardImagePanelListener);
		cardImagePanel.addMouseMotionListener(cardImagePanelListener);
		cardImagePanel.addMouseWheelListener(cardImagePanelListener);
		cardImagePanel.addComponentListener(cardImagePanelListener);
		
		cardListener = new CardListener(mainWindow.cardPanel);
		cardContainerListener = new CardContainerListener(mainWindow.cardPanel);
		
		cardContainer.addObserver(cardContainerListener);
	}
	
	public void loadCards(File[] fileArr) {
		for (File file : fileArr) {
			try {
				BufferedImage image = ImageIO.read(file);
				Card card = new Card(image);
				card.addObserver(cardListener);
				cardContainer.addCard(new Card(image));
			} catch (IOException e) {
				log.error("Unable to load '{}'", file.getName());
			}
		}
	}
	
	public static void main(String[] args) {
		new BulletHoleDetector();
	}
}
