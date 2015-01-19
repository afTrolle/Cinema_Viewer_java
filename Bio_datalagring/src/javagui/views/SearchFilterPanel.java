package javagui.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import ODBC.ODBCHandler;
import ODBC.dataStructure.CityTable;
import ODBC.dataStructure.MovieInfo;
import ODBC.dataStructure.ShowTime;

@SuppressWarnings("serial")
public class SearchFilterPanel extends JPanel {

	ODBCHandler ODBC = new ODBCHandler();

	JComboBox cityBox = new JComboBox();
	JComboBox cinemaBox = new JComboBox();
	JComboBox movieBox = new JComboBox();
	JComboBox dateBox = new JComboBox();
	Boolean theatherIsLatest = null;
	public static ArrayList<ShowTime> Availebelshows;

	/**
	 * Create the panel.
	 */
	public SearchFilterPanel() {

		// Sets the panel to a gridbag layout
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };
		setLayout(gridBagLayout);

		/*
		 * Titles for the Panel
		 */
		// JTextArea To help the user figure out what to do.
		JTextArea txtrHelpText = new JTextArea();
		txtrHelpText.setWrapStyleWord(true);
		txtrHelpText.setBackground(null);
		txtrHelpText.setEditable(false);
		txtrHelpText.setTabSize(3);
		txtrHelpText.setLineWrap(true);
		txtrHelpText
				.setText("Here you can choose Performance. By choosing the "
						+ "city and the cinema or movie and then select the day");
		GridBagConstraints gbc_txtrHereYouCan = new GridBagConstraints();
		gbc_txtrHereYouCan.gridwidth = 5;
		gbc_txtrHereYouCan.insets = new Insets(5, 0, 5, 5);
		gbc_txtrHereYouCan.fill = GridBagConstraints.BOTH;
		gbc_txtrHereYouCan.gridx = 0;
		gbc_txtrHereYouCan.gridy = 0;
		add(txtrHelpText, gbc_txtrHereYouCan);

		/*
		 * Labels for the different combo boxes
		 */

		JLabel lblCity = new JLabel("City");
		GridBagConstraints gbc_lblCity = new GridBagConstraints();
		gbc_lblCity.insets = new Insets(0, 0, 5, 5);
		gbc_lblCity.anchor = GridBagConstraints.WEST;
		gbc_lblCity.gridx = 0;
		gbc_lblCity.gridy = 1;
		add(lblCity, gbc_lblCity);

		JLabel lblTheather = new JLabel("Theater");
		GridBagConstraints gbc_lblTheather = new GridBagConstraints();
		gbc_lblTheather.anchor = GridBagConstraints.WEST;
		gbc_lblTheather.insets = new Insets(0, 0, 5, 5);
		gbc_lblTheather.gridx = 0;
		gbc_lblTheather.gridy = 2;
		add(lblTheather, gbc_lblTheather);

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

		JLabel lblDate = new JLabel("Date");
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.anchor = GridBagConstraints.WEST;
		gbc_lblDate.insets = new Insets(0, 0, 0, 5);
		gbc_lblDate.gridx = 0;
		gbc_lblDate.gridy = 3;
		add(lblDate, gbc_lblDate);

		/*
		 * Comboboxes here is the action!
		 */

		// cityBox Contains all the available city where there is a
		// cinema/theather
		initCityBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.anchor = GridBagConstraints.WEST;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 1;
		add(cityBox, gbc_comboBox);

		// this box will contain all the cinemas in a city which is selected by
		// citybox
		cinemaBox.setModel(new DefaultComboBoxModel(
				new String[] { "-Select City-" }));

		// adds when user selects a cinema then we get all the available dates
		// that shows movies in that cinema.
		cinemaBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String CinemaName = (String) cb.getSelectedItem();

				//this checks that user has selected a city.
				if (CinemaName.equals("-Select City-")) {
					return;
				}
				
				clearBrowserPanel();
				try {
					String City = (String) cityBox.getSelectedItem();
					System.out.println(City);
					ArrayList<String> avaibleDays = ODBC.getAvailableDatesAtCinema(CinemaName,City);
					dateBox.setModel(new DefaultComboBoxModel(avaibleDays
							.toArray()));
					
					if (avaibleDays.isEmpty()) {
						dateBox.setModel(new DefaultComboBoxModel( new String[] {"-Not available-"}));
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				theatherIsLatest = true;
			}
		});
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 2;
		add(cinemaBox, gbc_comboBox_1);

		// this will be set by city box, will hold all movies that will be shown
		// in a specific city.
		movieBox.setModel(new DefaultComboBoxModel(
				new String[] { "-Select City-" }));

		// If a person selects a movie then we set the date to all days that
		// that movie will be shown.
		movieBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String MovieTitle = (String) cb.getSelectedItem();

				//check that user has selected a city with a valid movie selection!
				if (MovieTitle.equals("-Not available-") || MovieTitle.equals("-Select City-") ) {
					return;
				}
				clearBrowserPanel();
				MovieInfoPanel.lblMovieTitel.setText(MovieTitle);
				try {
					MovieInfo movie = ODBC.getMovieInfo(MovieTitle);
					MovieInfoPanel.txtrMovieDescription
							.setText(movie.Description);
					MovieInfoPanel.lblMovieInfo.setText("Lenght: "
							+ movie.Lenght + " min  , Price: " + movie.Price
							+ " :-");

					String City = (String) cityBox.getSelectedItem();
					ArrayList<String> avaibleDays = ODBC
							.getAvailableDatesCityAndMovie(MovieTitle, City);

					dateBox.setModel(new DefaultComboBoxModel(avaibleDays
							.toArray()));

					CinemaBrowserPanel.movieInfo.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				theatherIsLatest = false;
			}
		});
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 4;
		gbc_comboBox_2.gridy = 2;
		add(movieBox, gbc_comboBox_2);

		// This will hold the dates that is set by the two other boxes
		dateBox.setModel(new DefaultComboBoxModel(
				new String[] { "-Select Above-" }));
		// when you select a date then from selection from previous boxes it selects which shows that fits.
		dateBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JComboBox cb = (JComboBox) e.getSource();
				String Date = (String) cb.getSelectedItem();

				try {
					if (theatherIsLatest) {
						System.out.println((String) cityBox.getSelectedItem());
						Availebelshows = ODBC.getShows2(
								(String) cinemaBox.getSelectedItem(), Date, (String) cityBox.getSelectedItem());
					} else {
						Availebelshows = ODBC.getShows(
								(String) cityBox.getSelectedItem(), Date,
								(String) movieBox.getSelectedItem());
					}

					updateBrowserPanel();
					
				} catch (Exception e2) {
				}

			}
		});

		GridBagConstraints gbc_comboBox_3 = new GridBagConstraints();
		gbc_comboBox_3.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_3.gridx = 1;
		gbc_comboBox_3.gridy = 3;
		add(dateBox, gbc_comboBox_3);

	}
	
	// Clears the browser Panel so it looks nice and clean!
	public void clearBrowserPanel(){
		CinemaBrowserPanel.model.clear();
	}

	// it updates the list of available shows that are done. 
	public void updateBrowserPanel() {
		CinemaBrowserPanel.model.clear();

		for (int i = 0; i < Availebelshows.size(); i++) {

			CinemaBrowserPanel.model
					.addElement(Availebelshows.get(i).movieTitle + ", "
							+ Availebelshows.get(i).movieStartTime + ", "
							+ Availebelshows.get(i).movieLenght
							+ "min, Cinema: "
							+ Availebelshows.get(i).cinemaName + ", Salon: "
							+ Availebelshows.get(i).SalonName);
			CinemaBrowserPanel.showsList.setVisible(true);
		}
	}

	public void initCityBox() {

		// Get all cities that contain a cinema/theather and set them as options
		// in the combo box.
		try {
			ArrayList<CityTable> cityTable = ODBC.GetCitys();
			String[] cityNames = new String[cityTable.size()];
			for (int i = 0; i < cityTable.size(); i++) {
				cityNames[i] = cityTable.get(i).cityName;
			}
			cityBox.setModel(new DefaultComboBoxModel(cityNames));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// when user selects a city update the available theathers/cinemas and
		// movies in that city in their relative combo box
		cityBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// getTheathers in city
				JComboBox cb = (JComboBox) e.getSource();
				String CityName = (String) cb.getSelectedItem();
				try {
					
					ArrayList<String> res = ODBC.getCinemasInCity(CityName);
					cinemaBox.setModel(new DefaultComboBoxModel(res.toArray()));

					ArrayList<String> moviesInTown = ODBC
							.getMoviesInCity(CityName);
					
					//if there aren't any availble movies in that town.
					if (moviesInTown.isEmpty()){
						movieBox.setModel( new DefaultComboBoxModel(new String[] {"-Not available-"}));
					} else {
						movieBox.setModel(new DefaultComboBoxModel(moviesInTown
								.toArray()));
					}
					
					clearBrowserPanel();
					// Reset The Date Box
					dateBox.setModel(new DefaultComboBoxModel(
							new String[] { "-Select Above-" }));
				} catch (Exception e1) {
				}

			}
		});

	}
}
