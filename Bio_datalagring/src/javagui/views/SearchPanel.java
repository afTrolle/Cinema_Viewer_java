package javagui.views;

import java.awt.Color;

import javax.naming.CompositeName;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.BoxLayout;

import java.awt.Component;

import javax.swing.Box;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.SwingConstants;

import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SpringLayout;

import ODBC.ODBCHandler;
import ODBC.dataStructure.CityTable;
import ODBC.dataStructure.MovieInfo;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class SearchPanel extends JPanel {

	ODBCHandler ODBC = new ODBCHandler();
	JComboBox comboBox_1 = new JComboBox();
	JComboBox comboBox_2 = new JComboBox();

	/**
	 * Create the panel.
	 */
	public SearchPanel() {
		setSize(420, 246);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };
		setLayout(gridBagLayout);

		JTextArea txtrHereYouCan = new JTextArea();
		txtrHereYouCan.setWrapStyleWord(true);
		txtrHereYouCan.setBackground(null);
		txtrHereYouCan.setEditable(false);
		txtrHereYouCan.setTabSize(3);
		txtrHereYouCan.setLineWrap(true);
		txtrHereYouCan
				.setText("Here you can choose Performance. By choosing the city and the cinema or movie and then select the day");
		GridBagConstraints gbc_txtrHereYouCan = new GridBagConstraints();
		gbc_txtrHereYouCan.gridwidth = 5;
		gbc_txtrHereYouCan.insets = new Insets(5, 0, 5, 5);
		gbc_txtrHereYouCan.fill = GridBagConstraints.BOTH;
		gbc_txtrHereYouCan.gridx = 0;
		gbc_txtrHereYouCan.gridy = 0;
		add(txtrHereYouCan, gbc_txtrHereYouCan);

		JLabel lblCity = new JLabel("City :");
		GridBagConstraints gbc_lblCity = new GridBagConstraints();
		gbc_lblCity.insets = new Insets(0, 0, 5, 5);
		gbc_lblCity.anchor = GridBagConstraints.WEST;
		gbc_lblCity.gridx = 0;
		gbc_lblCity.gridy = 1;
		add(lblCity, gbc_lblCity);

		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// getTheathers in city
				JComboBox cb = (JComboBox) e.getSource();
				String CityName = (String) cb.getSelectedItem();
				try {

					ArrayList<String> res = ODBC.getCinemasInCity(CityName);
					comboBox_1.setModel(new DefaultComboBoxModel(res.toArray()));

					ArrayList<String> moviesInTown = ODBC
							.getMoviesInCity(CityName);
					comboBox_2.setModel(new DefaultComboBoxModel(moviesInTown
							.toArray()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Stockholm",
				"Malm\u00F6" }));
		try {

			ArrayList<CityTable> cityTable = ODBC.GetCitys();
			String[] cityNames = new String[cityTable.size()];
			for (int i = 0; i < cityTable.size(); i++) {
				cityNames[i] = cityTable.get(i).cityName;
			}
			comboBox.setModel(new DefaultComboBoxModel(cityNames));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		comboBox.setSelectedIndex(1);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.anchor = GridBagConstraints.WEST;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 1;
		add(comboBox, gbc_comboBox);

		JLabel lblTheather = new JLabel("Theater:");
		GridBagConstraints gbc_lblTheather = new GridBagConstraints();
		gbc_lblTheather.anchor = GridBagConstraints.WEST;
		gbc_lblTheather.insets = new Insets(0, 0, 5, 5);
		gbc_lblTheather.gridx = 0;
		gbc_lblTheather.gridy = 2;
		add(lblTheather, gbc_lblTheather);

		comboBox_1.setModel(new DefaultComboBoxModel(
				new String[] { "Rigoletto" }));
		comboBox_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Update Date! 
				
			}
		});
		
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 2;
		add(comboBox_1, gbc_comboBox_1);

		JLabel lblOr = new JLabel(" or");
		GridBagConstraints gbc_lblOr = new GridBagConstraints();
		gbc_lblOr.insets = new Insets(0, 0, 5, 5);
		gbc_lblOr.gridx = 2;
		gbc_lblOr.gridy = 2;
		add(lblOr, gbc_lblOr);

		JLabel lblMovie = new JLabel("Movie");
		GridBagConstraints gbc_lblMovie = new GridBagConstraints();
		gbc_lblMovie.insets = new Insets(0, 0, 5, 5);
		gbc_lblMovie.anchor = GridBagConstraints.EAST;
		gbc_lblMovie.gridx = 3;
		gbc_lblMovie.gridy = 2;
		add(lblMovie, gbc_lblMovie);

		comboBox_2.setModel(new DefaultComboBoxModel(
				new String[] { "The Hobbit" }));
		comboBox_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String MovieTitle = (String) cb.getSelectedItem();
				
				MovieInfoPanel.lblMovietitel.setText(MovieTitle);
				try {
					MovieInfo movie = ODBC.getMovieInfo(MovieTitle);
					MovieInfoPanel.txtrWhenTonyStark.setText(movie.Description);
					MovieInfoPanel.lblNewLabel.setText("Lenght: " +movie.Lenght +" min  , Price: " +movie.Price + " :-");
					
					//TODO Update Available Dates!
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				
				
			}
		});
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 4;
		gbc_comboBox_2.gridy = 2;
		add(comboBox_2, gbc_comboBox_2);

		JLabel lblDate = new JLabel("Date:");
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.anchor = GridBagConstraints.WEST;
		gbc_lblDate.insets = new Insets(0, 0, 0, 5);
		gbc_lblDate.gridx = 0;
		gbc_lblDate.gridy = 3;
		add(lblDate, gbc_lblDate);

		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(
				new String[] { "Today 15/1" }));
		GridBagConstraints gbc_comboBox_3 = new GridBagConstraints();
		gbc_comboBox_3.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_3.gridx = 1;
		gbc_comboBox_3.gridy = 3;
		add(comboBox_3, gbc_comboBox_3);

	}
}
