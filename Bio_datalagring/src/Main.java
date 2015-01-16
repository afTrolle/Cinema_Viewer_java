import java.util.ArrayList;

import ODBC.ODBCHandler;
import ODBC.dataStructure.CinemaTable;
import ODBC.dataStructure.CityTable;
import javagui.views.MainFrame;


public class Main {

	public static void main(String[] args) {

		MainFrame frame = new MainFrame();
		frame.setVisible(true);
		
		ODBCHandler ODBC = new ODBCHandler();
	// creates a cinema	
	// 	try { ODBC.CreateBiograf("Stockholm", "test2", "kunga vägen" , 11453); } catch (Exception e) { }
	
	
//	ArrayList<CityTable> citys = null;	
//	try { citys = ODBC.GetCitys(); } catch (Exception e) {	}
//	
//	for (int i = 0; i < citys.size(); i++) {
//		System.out.println(citys.get(i).cityName);
//	}
//	
	try {
		ArrayList<CinemaTable> cinemasInCity =  ODBC.getCinemasInCity(2);
		
		for (int i = 0; i < cinemasInCity.size(); i++) {
			System.out.println(cinemasInCity.get(i).CinemaName);
		}
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
}
