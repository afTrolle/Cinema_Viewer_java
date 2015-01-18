package javagui.views;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import ODBC.dataStructure.ShowTime;

public class CinemaBrowserPanel extends JPanel {

	// Unecesssary but cool with no warnings
	private static final long serialVersionUID = 1L;

	// Global and Static so that we can update These Variables from everywhere!
	static JList showsList = new JList();
	static MovieInfoPanel movieInfo = new MovieInfoPanel();
	public static DefaultListModel model = new DefaultListModel();

	/**
	 * Creates a Panel for finding amazing cinema movies
	 */
	public CinemaBrowserPanel() {

		// Set the panel to use GridBagLayout
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		// Setup a Search View/Panel here the user can specify which cinema the
		// user is looking for
		SearchFilterPanel searchFilter = new SearchFilterPanel();
		searchFilter.setBorder(createFancyBorder("Select a Movie", 4));
		GridBagConstraints gbc_movieFinder = new GridBagConstraints();
		gbc_movieFinder.insets = new Insets(0, 0, 5, 5);
		gbc_movieFinder.anchor = GridBagConstraints.NORTH;
		gbc_movieFinder.fill = GridBagConstraints.HORIZONTAL;
		gbc_movieFinder.gridx = 0;
		gbc_movieFinder.gridy = 0;
		add(searchFilter, gbc_movieFinder);

		// This tab show selected movie information
		movieInfo.setVisible(false);
		movieInfo.setBorder(createFancyBorder("Movie Info", 6));
		GridBagConstraints gbc_moviesPanel = new GridBagConstraints();
		gbc_moviesPanel.insets = new Insets(0, 0, 5, 0);
		gbc_moviesPanel.fill = GridBagConstraints.BOTH;
		gbc_moviesPanel.gridx = 1;
		gbc_moviesPanel.gridy = 0;
		gbc_moviesPanel.gridheight = 2;
		add(movieInfo, gbc_moviesPanel);

		// This panel shows all the available cinema shows/performance by date
		// and name and so on.
		showsList.setBackground(null);
		showsList.setVisible(false);
		showsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		showsList.setLayoutOrientation(JList.VERTICAL_WRAP);
		showsList.setModel(model);
		showsList.setBorder(createFancyBorder("Available movies", 5));
		showsList.addListSelectionListener(new ListSelectionListener() {

			/**
			 * if someone selects an item then we need to react
			 */
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					JList jlist = (JList) e.getSource();
					if (jlist.getSelectedIndex() >= 0) {

						try {
							ShowTime movie = SearchFilterPanel.Availebelshows
									.get(jlist.getSelectedIndex());
							MovieInfoPanel.lblMovieTitel.setText(movie.movieTitle);
							MovieInfoPanel.txtrMovieDescription
									.setText(movie.movieDescription);
							MovieInfoPanel.lblMovieInfo.setText("Lenght: "
									+ movie.movieLenght + " min  , Price: "
									+ movie.moviePrice + " :-");

							movieInfo.setVisible(true);
						} catch (Exception e2) {}

					}
				}
			}
		});

		// This panel holds the panel that shows all the available Shows
		JPanel availableShowsHolder = new JPanel();
		GridBagConstraints gbc_timeandmoviePanel = new GridBagConstraints();
		gbc_timeandmoviePanel.gridwidth = 2;
		gbc_timeandmoviePanel.insets = new Insets(0, 0, 0, 5);
		gbc_timeandmoviePanel.fill = GridBagConstraints.BOTH;
		gbc_timeandmoviePanel.gridx = 0;
		gbc_timeandmoviePanel.gridy = 2;
		gbc_timeandmoviePanel.gridheight = 3;
		availableShowsHolder.setLayout(new BorderLayout(0, 0));
		add(availableShowsHolder, gbc_timeandmoviePanel);
		availableShowsHolder.add(showsList, BorderLayout.CENTER);

	}

	/**
	 * Creates a nice border for panels
	 */
	private CompoundBorder createFancyBorder(String Name, int Spaceing) {

		CompoundBorder Border1 = new CompoundBorder(new TitledBorder(Name),
				new EmptyBorder(Spaceing, Spaceing, Spaceing, Spaceing));
		return (new CompoundBorder(new EmptyBorder(Spaceing, Spaceing,
				Spaceing, Spaceing), Border1));
	}

}
