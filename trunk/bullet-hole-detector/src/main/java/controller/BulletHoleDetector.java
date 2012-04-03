package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import listeners.CardContainerListener;
import listeners.CardListener;
import listeners.MenuListener;
import model.Card;
import model.CardContainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import view.MainWindow;

public class BulletHoleDetector {
	private static final Logger	log	= LoggerFactory.getLogger(BulletHoleDetector.class);
	
	CardContainer				cardContainer;
	MainWindow					mainWindow;
	CardListener				cardListener;
	CardContainerListener		cardContainerListener;
	
	public BulletHoleDetector() {
		cardContainer = new CardContainer();
		
		mainWindow = new MainWindow(cardContainer, new MenuListener(this));
		
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
