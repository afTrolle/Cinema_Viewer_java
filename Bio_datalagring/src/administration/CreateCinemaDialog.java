package administration;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ODBC.ODBCHandler;
import ODBC.dataStructure.CityTable;

public class CreateCinemaDialog {

	public CreateCinemaDialog() {

		ODBCHandler ODBC = new ODBCHandler();
		String[] cityNames;
		JComboBox cityBox = new JComboBox();

		try {
			ArrayList<CityTable> citys = ODBC.GetCitys();
			cityNames = new String[(citys.size() + 1)];
			for (int i = 0; i < citys.size(); i++) {
				cityNames[i] = citys.get(i).cityName;
			}
			cityNames[citys.size()] = "Other";
			cityBox.setModel(new DefaultComboBoxModel(cityNames));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		JTextField cinemaName = new JTextField();
		JTextField cinemaAddress = new JTextField();
		JTextField postcode = new JTextField();
		
		postcode.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) {
		      char c = e.getKeyChar();
		      if (!((c >= '0') && (c <= '9') ||
		         (c == KeyEvent.VK_BACK_SPACE) ||
		         (c == KeyEvent.VK_DELETE))) {
		        e.consume();
		      }
		    }
		  });
		
		Object[] message = { "City:", cityBox, "Cinema name:", cinemaName, "Address:" , cinemaAddress , "Postcode:", postcode};

		int option = JOptionPane.showConfirmDialog(null, message,
				"Create Theater/Cinema", JOptionPane.OK_CANCEL_OPTION);
		
		
		if (option == JOptionPane.OK_OPTION) {
			//check that all fields are field!
			if (cinemaName.getText().isEmpty() || cinemaAddress.getText().isEmpty() || postcode.getText().isEmpty()){
				JOptionPane.showMessageDialog(null,
					    "You need too fill all the fields",
					    "Warning",
					    JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			if (((String) cityBox.getSelectedItem()).equals("Other")) {
				String s = (String)JOptionPane.showInputDialog(	null, "Type City Name","specify city", JOptionPane.INFORMATION_MESSAGE);
				if (s.isEmpty()){
					JOptionPane.showMessageDialog(null,
						    "You need too fill all the fields",
						    "Warning",
						    JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				try {
					ODBC.CreateBiograf(s, cinemaName.getText(), cinemaAddress.getText(), Integer.parseInt(postcode.getText()));
					JOptionPane.showMessageDialog(null,
						    "Created Cinema!",
						    "Success",
						    JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
						    "Failed to Created Cinema! \n note that only english works",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			} else {
				try {
					ODBC.CreateBiograf((String)cityBox.getSelectedItem(), cinemaName.getText(), cinemaAddress.getText(), Integer.parseInt(postcode.getText()));
					JOptionPane.showMessageDialog(null,
						    "Created Cinema!",
						    "Success",
						    JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
						    "Failed to Created Cinema! \n note that only english works",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		} else {
			return;
		}
	}

}
