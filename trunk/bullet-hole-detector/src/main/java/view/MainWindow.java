package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import listeners.MenuListener;
import model.CardContainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainWindow extends JFrame {
	private static final long	serialVersionUID	= 1L;
	
	private static final Logger	log					= LoggerFactory.getLogger(MainWindow.class);
	
	public CardPanel			cardPanel;
	
	public MainWindow(CardContainer cardContainer, MenuListener menuListener) {
		// Create and set up the window.
		setTitle("BulletHoleDetector");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Set up the content pane, where the "main GUI" lives.
		Container contentPane = getContentPane();
		JPanel frameContents = new JPanel();
		frameContents.setLayout(new BorderLayout());
		
		// Start creating and adding components.
		// Card viewer
		cardPanel = new CardPanel(cardContainer);
		cardPanel.setPreferredSize(new Dimension(320, 240));
		
		// Menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		
		JMenuItem menuItemLoadCards = new JMenuItem("Load cards");
		menuItemLoadCards.setName("menuItemLoadCards");
		menuItemLoadCards.addActionListener(menuListener);
		menu.add(menuItemLoadCards);
		
		JMenuItem menuItemExit = new JMenuItem("Exit");
		menuItemExit.setName("menuItemExit");
		menuItemExit.addActionListener(menuListener);
		menu.add(menuItemExit);
		
		menuBar.add(menu);
		setJMenuBar(menuBar);
		
		// Adding components to JPanel, which is then added to the contentPane
		frameContents.add(BorderLayout.CENTER, cardPanel);
		contentPane.add(frameContents);
		contentPane.validate();
		
		// Show the window.
		pack();
		
		Point centerOfScreen = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		centerOfScreen.translate(-getSize().width / 2, -getSize().height / 2);
		setLocation(centerOfScreen);
		
		setVisible(true);
	}
	
	public void loadCards(File[] fileArr) {
		for (File file : fileArr) {
			log.info("Opening: {}", file.getName());
		}
		
	}
}