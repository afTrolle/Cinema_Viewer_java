package ODBC;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import ODBC.dataStructure.CinemaTable;
import ODBC.dataStructure.CityTable;
import ODBC.dataStructure.MovieInfo;

public class ODBCHandler {

	// ODBC connection variable
	static protected Connection con;
	// ODBC access BIOodbc driver.
	private String URL = "jdbc:odbc:BIOodbc";
	// Get Class Name for ODBC Driver
	private String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
	// Username for ODBC Driver
	private String userID = "";
	// Password for ODBC Driver
	private String password = "";

	// method for establishing a ODBC connection
	public void connect() {
		try {
			// register the driver with DriverManager
			Class.forName(driver);
			// create a connection to ODBC driver
			con = DriverManager.getConnection(URL, userID, password);
			// Set the auto commit of the connection to false.
			// An explicit commit will be required in order to save changes
			con.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void simpleselect(String query) throws Exception {
		// Local variables
		ResultSet rs;
		Statement stmt;

		// Create a statement associated to the connection con.
		// The new statement is placed in the variable stmt.
		stmt = con.createStatement();

		// Execute the SQL statement that is stored in the variable query
		// and store the result in the variable rs.
		rs = stmt.executeQuery(query);

		// Loop through the result set and print the results.
		// The method next() returns false when there are no more rows.
		while (rs.next()) {
			System.out.print("Stad: " + rs.getString("stad"));
			System.out.println(" Antal personer: " + rs.getInt("antal"));
		}

		// Close the variable stmt and release all resources bound to it
		// Any ResultSet associated to the Statement will be automatically
		// closed too.
		stmt.close();
	}

	public void CreateBiograf(String stad, String namn, String adress,
			int postnr) throws Exception {

		connect();

		// Gets DB cityID if its available else it will get a 0.
		int cityID = 0;
		cityID = GetCityID(stad);

		// if city doesn't exist then we create it!
		if (cityID == 0) {
			// City doesn't exist in DB so insert a new city and return the new
			// ID.
			System.out.println("create city");
			cityID = createCity(stad);
		}

		CreateCinema(cityID, namn, adress, postnr);

		// Commit the changes made to the database through this connection.
		con.commit();
		// Close the connection.
		con.close();
	}

	private void CreateCinema(int cityID, String namn, String adress, int postNr)
			throws Exception {

		String query;
		int rs;
		PreparedStatement stmt;

		// Set the SQL statement into the query variable
		query = "INSERT INTO Biograf (Namn, Stad, Adress, Postnr) VALUES (? ,? ,? ,?)";

		// Create a statement associated to the connection and the query.
		// The new statement is placed in the variable stmt.
		stmt = con.prepareStatement(query);

		// Provide the value for the first ? in the SQL statement.
		// The value of the variable markeparam will be sent to the database
		// manager
		// through the variables stmt and con.
		stmt.setString(1, namn);
		stmt.setInt(2, cityID);
		stmt.setString(3, adress);
		stmt.setInt(4, postNr);

		// Execute the SQL statement that is prepared in the variable stmt
		// and store the result in the variable rs.
		rs = stmt.executeUpdate();

		if (rs != 1) {
			throw new Exception("Failed to create Cinema");
		}
	}

	private int createCity(String stad) throws Exception {

		String query;
		int rs;
		PreparedStatement stmt;

		// Set the SQL statement into the query variable
		query = "INSERT INTO Stad (Namn) VALUES (?)";

		// Create a statement associated to the connection and the query.
		// The new statement is placed in the variable stmt.
		stmt = con.prepareStatement(query);

		// Provide the value for the first ? in the SQL statement.
		// The value of the variable markeparam will be sent to the database
		// manager
		// through the variables stmt and con.
		stmt.setString(1, stad);

		// Execute the SQL statement that is prepared in the variable stmt
		// and store the result in the variable rs.
		rs = stmt.executeUpdate();

		stmt.close();

		if (rs == 1) {
			return GetCityID(stad);
		} else {
			return 0;
		}

	}

	public ArrayList<String> getCinemasInCity(String city) throws Exception {

		connect();

		int cityID = GetCityID(city);

		String query;
		ResultSet rs;
		PreparedStatement stmt;

		// Set the SQL statement into the query variable
		query = "SELECT Biograf.Namn FROM Biograf WHERE Biograf.Stad=?";

		// Create a statement associated to the connection and the query.
		// The new statement is placed in the variable stmt.
		stmt = con.prepareStatement(query);

		// Provide the value for the first ? in the SQL statement.
		// The value of the variable markeparam will be sent to the database
		// manager
		// through the variables stmt and con.
		stmt.setInt(1, cityID);

		// Execute the SQL statement that is prepared in the variable stmt
		// and store the result in the variable rs.
		rs = stmt.executeQuery();
		// should only exist one!
		ArrayList<String> ret = new ArrayList<String>();

		while (rs.next()) {
			ret.add(rs.getString("Namn"));
		}

		// Close the variable stmt and release all resources bound to it
		// Any ResultSet associated to the Statement will be automatically
		// closed too.
		stmt.close();
		return ret;
	}

	public int GetCityID(String Stad) throws Exception {
		// Local variables
		String query;
		ResultSet rs;
		PreparedStatement stmt;
		String markeparam;

		// Set the SQL statement into the query variable
		query = "SELECT Stad.StadID FROM Stad WHERE Stad.Namn=?";

		// Create a statement associated to the connection and the query.
		// The new statement is placed in the variable stmt.
		stmt = con.prepareStatement(query);

		// Provide the value for the first ? in the SQL statement.
		// The value of the variable markeparam will be sent to the database
		// manager
		// through the variables stmt and con.
		stmt.setString(1, Stad);

		// Execute the SQL statement that is prepared in the variable stmt
		// and store the result in the variable rs.
		rs = stmt.executeQuery();
		int answer = 0;
		// should only exist one!
		if (rs.next()) {
			answer = rs.getInt("StadID");
		}

		System.out.println("stadsID: " + answer);

		// Close the variable stmt and release all resources bound to it
		// Any ResultSet associated to the Statement will be automatically
		// closed too.
		stmt.close();

		return answer;
	}

	public ArrayList<CityTable> GetCitys() throws Exception {

		connect();

		// Local variables
		String query;
		ResultSet rs;
		Statement stmt;
		ArrayList<CityTable> answer = new ArrayList<CityTable>();

		// Set the SQL statement into the query variable
		query = "SELECT * FROM stad ";

		// Create a statement associated to the connection con.
		// The new statement is placed in the variable stmt.
		stmt = con.createStatement();

		// Execute the SQL statement that is stored in the variable query
		// and store the result in the variable rs.
		rs = stmt.executeQuery(query);

		// Loop through the result set and print the results.
		// The method next() returns false when there are no more rows.
		while (rs.next()) {
			answer.add(new CityTable(rs.getInt("StadID"), rs.getString("Namn")));
		}

		// Close the variable stmt and release all resources bound to it
		// Any ResultSet associated to the Statement will be automatically
		// closed too.
		stmt.close();

		return answer;
	}

	public ArrayList<CinemaTable> getCinemasInCity(int cityID) throws Exception {

		String query;
		ResultSet rs;
		PreparedStatement stmt;
		ArrayList<CinemaTable> answer = new ArrayList<CinemaTable>();

		connect();

		// Set the SQL statement into the query variable
		query = "SELECT * FROM Biograf WHERE Stad = ? ";

		// Create a statement associated to the connection and the query.
		// The new statement is placed in the variable stmt.
		stmt = con.prepareStatement(query);

		// Provide the value for the first ? in the SQL statement.
		// The value of the variable markeparam will be sent to the database
		// manager
		// through the variables stmt and con.
		stmt.setInt(1, cityID);

		// Execute the SQL statement that is prepared in the variable stmt
		// and store the result in the variable rs.
		rs = stmt.executeQuery();

		while (rs.next()) {
			CinemaTable temp = new CinemaTable();
			temp.CinemaID = rs.getInt("BiografID");
			temp.CinemaName = rs.getString("Namn");
			temp.CityID = rs.getInt("Stad");
			temp.openingHour = rs.getTime("Öppettid");
			temp.closingHour = rs.getTime("Stängningstid");
			temp.adress = rs.getString("Adress");
			temp.postcode = rs.getInt("Postnr");
			answer.add(temp);
		}

		stmt.close();

		return answer;
	}

	public void turnoff() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<String> getMoviesInCity(String cityName) throws Exception {

		String query;
		ResultSet rs;
		PreparedStatement stmt;

		// Set the SQL statement into the query variable
		query = "SELECT Film.Titel FROM Film Where Film.FilmID IN (select Föreställning.Film FROM ((Föreställning INNER JOIN Salong ON Föreställning.Salong=Salong.SalongsID ) INNER JOIN Biograf ON  Salong.Biograf=Biograf.BiografID) INNER JOIN  Stad on Biograf.Stad=Stad.StadID WHERE Stad.Namn=? AND (0 >  DATEDIFF('d',Föreställning.Starttid,Now())) )";    

		// Create a statement associated to the connection and the query.
		// The new statement is placed in the variable stmt.
		stmt = con.prepareStatement(query);

		// Provide the value for the first ? in the SQL statement.
		// The value of the variable markeparam will be sent to the database
		// manager
		// through the variables stmt and con.
		stmt.setString(1, cityName);

		// Execute the SQL statement that is prepared in the variable stmt
		// and store the result in the variable rs.
		rs = stmt.executeQuery();
		// should only exist one!
		ArrayList<String> ret = new ArrayList<String>();

		while (rs.next()) {
			ret.add(rs.getString("Titel"));
		}

		// Close the variable stmt and release all resources bound to it
		// Any ResultSet associated to the Statement will be automatically
		// closed too.
		stmt.close();

		return ret;
	}

	public MovieInfo getMovieInfo(String movieTitle) throws Exception {
		
		String query;
		ResultSet rs;
		PreparedStatement stmt;

		// Set the SQL statement into the query variable
		query = "SELECT Titel, Grundpris, Beskrivning, Bild, Längd FROM Film WHERE Titel=? ";    

		// Create a statement associated to the connection and the query.
		// The new statement is placed in the variable stmt.
		stmt = con.prepareStatement(query);

		// Provide the value for the first ? in the SQL statement.
		// The value of the variable markeparam will be sent to the database
		// manager
		// through the variables stmt and con.
		stmt.setString(1, movieTitle);

		// Execute the SQL statement that is prepared in the variable stmt
		// and store the result in the variable rs.
		rs = stmt.executeQuery();
		// should only exist one!
		MovieInfo ret = new MovieInfo();
		
		if (rs.next()) {
			
			ret.Titel = rs.getString("Titel");
			ret.Price = rs.getBigDecimal("Grundpris") ;
			ret.Description = rs.getString("Beskrivning");
			ret.Lenght = rs.getInt("Längd");
			
			 
		}
		

		 
		// Close the variable stmt and release all resources bound to it
		// Any ResultSet associated to the Statement will be automatically
		// closed too.
		stmt.close();

		return ret;
		
		
	}

}
