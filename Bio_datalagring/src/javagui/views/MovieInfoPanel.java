package javagui.views;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Insets;

import javax.swing.JTextArea;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class MovieInfoPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	static JLabel posterpanel;
	static JLabel lblMovietitel;
	static JLabel lblNewLabel;
	static JTextArea txtrWhenTonyStark;
	
	public MovieInfoPanel() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		
		ImageIcon icon= null;
		try {
			BufferedImage img = ImageIO.read(new File("res/test.jpg"));
	        icon = new ImageIcon( img.getScaledInstance(img.getWidth()/2, img.getHeight()/2, Image.SCALE_SMOOTH));
		} catch (IOException e) {		}
		JLabel posterpanel = new JLabel(icon);
		posterpanel.setBackground(Color.black);
		GridBagConstraints gbc_descriptionholder = new GridBagConstraints();
		gbc_descriptionholder.gridheight = 4;
		gbc_descriptionholder.insets = new Insets(0, 0, 0, 5);
		gbc_descriptionholder.fill = GridBagConstraints.BOTH;
		gbc_descriptionholder.gridx = 0;
		gbc_descriptionholder.gridy = 0;
		add(posterpanel, gbc_descriptionholder);
		
		 lblMovietitel = new JLabel("Movie_Titel");
		GridBagConstraints gbc_lblMovietitel = new GridBagConstraints();
		gbc_lblMovietitel.anchor = GridBagConstraints.WEST;
		gbc_lblMovietitel.insets = new Insets(0, 0, 5, 0);
		gbc_lblMovietitel.gridx = 1;
		gbc_lblMovietitel.gridy = 0;
		add(lblMovietitel, gbc_lblMovietitel);
		
		 lblNewLabel = new JLabel("Lenght: ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);
		
		txtrWhenTonyStark = new JTextArea();
		txtrWhenTonyStark.setPreferredSize(new Dimension(300, 100));
		txtrWhenTonyStark.setLineWrap(true);
		txtrWhenTonyStark.setWrapStyleWord(true);
		txtrWhenTonyStark.setEditable(false);
		txtrWhenTonyStark.setBackground(null);
		txtrWhenTonyStark.setText("When Tony Stark tries to jumpstart a dormant peacekeeping program, things go awry and it is up to the Avengers to stop the villainous Ultron from enacting his terrible plans.When Tony Stark tries to jumpstart a dormant peacekeeping program, things go awry and it is up to the Avengers to stop the villainous Ultron from enacting his terrible plans.When Tony Stark tries to jumpstart a dormant peacekeeping program, things go awry and it is up to the Avengers to stop the villainous Ultron from enacting his terrible plans.When Tony Stark tries to jumpstart a dormant peacekeeping program, things go awry and it is up to the Avengers to stop the villainous Ultron from enacting his terrible plans.");
		GridBagConstraints gbc_txtrWhenTonyStark = new GridBagConstraints();
		gbc_txtrWhenTonyStark.insets = new Insets(0, 0, 5, 0);
		gbc_txtrWhenTonyStark.fill = GridBagConstraints.BOTH;
		gbc_txtrWhenTonyStark.gridx = 1;
		gbc_txtrWhenTonyStark.gridy = 2;
		add(txtrWhenTonyStark, gbc_txtrWhenTonyStark);
		
		
	}
	
	
	
}
