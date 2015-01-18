package javagui.views;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MovieInfoPanel extends JPanel {

	
	private static final long serialVersionUID = 1L;

	//Global and static so that it can be changed by actionListners.
	static JLabel lblMovieTitel;
	static JLabel lblMovieInfo;
	static JTextArea txtrMovieDescription;
	
	/**
	 * Creates panel to show a movies information
	 */
	public MovieInfoPanel() {
		
		//Sets the panel to GridBaglayout
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		//Create Jlabel to show the movie title
		lblMovieTitel = new JLabel();
		GridBagConstraints gbc_lblMovietitel = new GridBagConstraints();
		gbc_lblMovietitel.anchor = GridBagConstraints.WEST;
		gbc_lblMovietitel.insets = new Insets(0, 0, 5, 0);
		gbc_lblMovietitel.gridx = 1;
		gbc_lblMovietitel.gridy = 0;
		add(lblMovieTitel, gbc_lblMovietitel);
		
		//create another Jlabel to show additional such as length, and original price.
		lblMovieInfo = new JLabel();
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		add(lblMovieInfo, gbc_lblNewLabel);
		
		//creates a text area to show the Description of a movie.
		txtrMovieDescription = new JTextArea();
		txtrMovieDescription.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrMovieDescription.setRows(6);
		txtrMovieDescription.setLineWrap(true);
		txtrMovieDescription.setWrapStyleWord(true);
		txtrMovieDescription.setEditable(false);
		txtrMovieDescription.setBackground(null);
		GridBagConstraints gbc_txtrWhenTonyStark = new GridBagConstraints();
		gbc_txtrWhenTonyStark.insets = new Insets(0, 0, 5, 0);
		gbc_txtrWhenTonyStark.fill = GridBagConstraints.BOTH;
		gbc_txtrWhenTonyStark.gridx = 1;
		gbc_txtrWhenTonyStark.gridy = 2;
		add(txtrMovieDescription, gbc_txtrWhenTonyStark);
		
	}
	
	
	
}
