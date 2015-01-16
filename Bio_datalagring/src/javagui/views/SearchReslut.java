package javagui.views;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;

public class SearchReslut extends JPanel {
	
	final int  windowWidth = 1068;
	final int  windowHeight = 614;

	/**
	 * Create the panel.
	 */
	public SearchReslut() {
		setSize(windowWidth, windowHeight);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
//		gridBagLayout.columnWidths = new int[]{200, 200, 0};
//		gridBagLayout.rowHeights = new int[]{123, 123, 123, 123, 70,0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		SearchPanel movieFinder = new SearchPanel();
		GridBagConstraints gbc_movieFinder = new GridBagConstraints();
		gbc_movieFinder.insets = new Insets(0, 0, 5, 5);
		gbc_movieFinder.anchor = GridBagConstraints.NORTH;
		gbc_movieFinder.fill = GridBagConstraints.HORIZONTAL;
		gbc_movieFinder.gridx = 0;
		gbc_movieFinder.gridy = 0;
		CompoundBorder searchborder = new CompoundBorder(new TitledBorder("Select a Movie"), new EmptyBorder(4, 4, 4, 4));
		movieFinder.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5),searchborder));
		add(movieFinder, gbc_movieFinder);
		
		
		MovieInfoPanel moviesPanel = new MovieInfoPanel();
		GridBagConstraints gbc_moviesPanel = new GridBagConstraints();
		gbc_moviesPanel.insets = new Insets(0, 0, 5, 0);
		gbc_moviesPanel.fill = GridBagConstraints.BOTH;
		gbc_moviesPanel.gridx = 1;
		gbc_moviesPanel.gridy = 0;
		gbc_moviesPanel.gridheight = 2;
		CompoundBorder movieinfoborder = new CompoundBorder(new TitledBorder("Movie Info"), new EmptyBorder(3, 6, 6, 6));
		moviesPanel.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5),movieinfoborder));
		
		add(moviesPanel, gbc_moviesPanel);
		
		JLabel label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		add(label, gbc_label);
		
		JPanel timeandmoviePanel = new JPanel();
		GridBagConstraints gbc_timeandmoviePanel = new GridBagConstraints();
		gbc_timeandmoviePanel.insets = new Insets(0, 0, 0, 5);
		gbc_timeandmoviePanel.fill = GridBagConstraints.BOTH;
		gbc_timeandmoviePanel.gridx = 0;
		gbc_timeandmoviePanel.gridy = 1;
		gbc_timeandmoviePanel.gridheight = 3;
		add(timeandmoviePanel, gbc_timeandmoviePanel);
		
		JList list = new JList();
		list.setBackground(null);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"hobbit", "no sprite"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		CompoundBorder moviesborder = new CompoundBorder(new TitledBorder("Available movies"), new EmptyBorder(4, 4, 4, 4));
		timeandmoviePanel.setLayout(new BorderLayout(0, 0));
		list.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5),moviesborder));
		
		timeandmoviePanel.add(list, BorderLayout.CENTER);
		
		JPanel searchResultPanel = new JPanel();
		GridBagConstraints gbc_searchResultPanel = new GridBagConstraints();
		gbc_searchResultPanel.anchor = GridBagConstraints.NORTH;
		gbc_searchResultPanel.insets = new Insets(0, 0, 5, 0);
		gbc_searchResultPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchResultPanel.gridx = 1;
		gbc_searchResultPanel.gridy = 2;
		add(searchResultPanel, gbc_searchResultPanel);
		searchResultPanel.setLayout(new BoxLayout(searchResultPanel, BoxLayout.Y_AXIS));
		
		CompoundBorder movieOccasionborder = new CompoundBorder(new TitledBorder("Available Dates"), new EmptyBorder(4, 4, 4, 4));
		searchResultPanel.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5),movieOccasionborder));
		
		ResultLinePanel p1 = new ResultLinePanel();
		searchResultPanel.add(p1);
		
	}
	
}
	
	