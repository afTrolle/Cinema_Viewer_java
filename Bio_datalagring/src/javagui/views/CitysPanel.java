package javagui.views;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.BoxLayout;

import ODBC.ODBCHandler;
import ODBC.dataStructure.CinemaTable;
import ODBC.dataStructure.CityTable;

import java.awt.BorderLayout;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class CitysPanel extends JPanel {

	public CitysPanel() {
		JTree tree;
		ODBCHandler ODBC = new ODBCHandler();
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Crop");
		
		try {
			ArrayList<CityTable> cityTable = ODBC.GetCitys();

			for (int i = 0; i < cityTable.size(); i++) {
				
				DefaultMutableTreeNode City = new DefaultMutableTreeNode(cityTable.get(i).cityName);
				
				 ArrayList<CinemaTable> cinemaTable = ODBC.getCinemasInCity(cityTable.get(i).cityID);
				 for (int j = 0; j < cinemaTable.size(); j++) {
					 DefaultMutableTreeNode Cinema = new DefaultMutableTreeNode("Cinema: "+ cinemaTable.get(j).CinemaName);
					 DefaultMutableTreeNode addres = new DefaultMutableTreeNode("Address: "+cinemaTable.get(j).adress);
					 
					 City.add(Cinema);
					 Cinema.add(addres);
					 
				}
				 
				 root.add(City);
				 
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setLayout(new BorderLayout(0, 0));
		//create the tree by passing in the root node
		tree = new JTree(root);
		add(tree);
		
		
	}
}
