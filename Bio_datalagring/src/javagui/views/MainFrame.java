package javagui.views;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import administration.CreateCinemaDialog;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	/**
	 * Creates Main ProgramFrame
	 */
	public MainFrame() {
		
		// Setting up the JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		setMinimumSize(new Dimension(900, 600));
		
		// Setting up menu
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		// main tab on menu
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		//Get the Cinema Movie Finder View menuItem
		JMenuItem mnFindMovie = new JMenuItem("Find movie");
		mnFile.add(mnFindMovie);
		mnFindMovie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//set MovieFinder To main
				CinemaBrowserPanel Bodypanel = new CinemaBrowserPanel();
				setContentPane(Bodypanel);
				pack();
			}
		});
		
		//Close menu Item another way to close the program for the user
		JMenuItem mnClose = new JMenuItem("Close");
		//Add ActionListener another way too close the program.
		mnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mnClose);
		
		//Administration Tab for Administration Functions
		JMenu mnAdminTab = new JMenu("Administration");
		menuBar.add(mnAdminTab);

		//Create theather menu item clicked when the user wants to create a new theater
		JMenuItem mnCreateTheater = new JMenuItem("Create theather");
		mnAdminTab.add(mnCreateTheater);
		mnCreateTheater.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new CreateCinemaDialog();
			}
		});
		
		
		//Menu Item allows user to see all theaters inside a city 
		JMenuItem mntmView = new JMenuItem("View theathers");
		mntmView.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				CitysPanel Bodypanel = new CitysPanel();
				setContentPane(Bodypanel);
				pack();
				
			}
		});
		mnAdminTab.add(mntmView);
		
		//Starts the program in to cinema browser / finder.
		CinemaBrowserPanel Bodypanel = new CinemaBrowserPanel();
		setContentPane(Bodypanel);
		pack();
	}

}
