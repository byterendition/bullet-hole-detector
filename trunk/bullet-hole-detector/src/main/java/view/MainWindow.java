package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainWindow extends JFrame {
	private ImagePanel		imagePanel;
	private MenuListener	menuListener;
	
	public MainWindow() {
		// Create and set up the window.
		setTitle("BulletHoleDetector");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Set up the content pane, where the "main GUI" lives.
		Container contentPane = getContentPane();
		JPanel frameContents = new JPanel();
		frameContents.setLayout(new BorderLayout());
		
		// Start creating and adding components.
		// Card viewer
		imagePanel = new ImagePanel();
		imagePanel.setPreferredSize(new Dimension(320, 240));
		
		// Menu
		menuListener = new MenuListener(this);
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		
		JMenuItem menuItemLoadCards = new JMenuItem("Load cards");
		menuItemLoadCards.addActionListener(menuListener);
		menu.add(menuItemLoadCards);
		
		JMenuItem menuItemExit = new JMenuItem("Exit");
		menuItemExit.addActionListener(menuListener);
		menu.add(menuItemExit);
		
		menuBar.add(menu);
		setJMenuBar(menuBar);
		
		// Adding components to JPanel, which is then added to the contentPane
		frameContents.add(BorderLayout.CENTER, imagePanel);
		contentPane.add(frameContents);
		contentPane.validate();
		
		// Show the window.
		pack();
		setVisible(true);
	}
}