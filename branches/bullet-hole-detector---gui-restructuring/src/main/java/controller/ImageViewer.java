/*
 * ImageViewer.java
 */
package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ImageViewer extends JFrame {
	JFileChooser	imageChooser	= new JFileChooser();
	JLabel			imageLabel		= new JLabel();
	
	public static void main(String args[]) {
		// construct frame
		new ImageViewer().show();
	}
	
	public ImageViewer() {
		// create frame
		setTitle("Image Viewer");
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exitForm(e);
			}
		});
		getContentPane().setLayout(new GridBagLayout());
		
		// position controls (establish event methods)
		GridBagConstraints gridConstraints = new GridBagConstraints();
		String[] ext = new String[] { "gif", "jpg" };
		imageChooser.addChoosableFileFilter(new ExampleFileFilter(ext, "Graphics Files"));
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 0;
		getContentPane().add(imageChooser, gridConstraints);
		imageChooser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imageChooserActionPerformed(e);
			}
		});
		
		imageLabel.setPreferredSize(new Dimension(270, 300));
		imageLabel.setBorder(BorderFactory.createLineBorder(Color.RED));
		imageLabel.setOpaque(true);
		imageLabel.setBackground(Color.white);
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imageLabel.setVerticalAlignment(SwingConstants.CENTER);
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 0;
		gridConstraints.insets = new Insets(10, 10, 10, 10);
		getContentPane().add(imageLabel, gridConstraints);
		
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height - getHeight())), getWidth(), getHeight());
		
	}
	
	private void imageChooserActionPerformed(ActionEvent e) {
		// create and display graphic if open selected
		if (e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
			ImageIcon myImage = new ImageIcon(imageChooser.getSelectedFile().toString());
			imageLabel.setIcon(myImage);
		}
		
	}
	
	private void exitForm(WindowEvent e) {
		System.exit(0);
	}
	
}
