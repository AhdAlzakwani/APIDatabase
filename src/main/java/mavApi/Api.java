package mavApi;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Api {

	public static void selectById(Long number) {
		Scanner scanner = new Scanner(System.in);
		String selectUrl = "jdbc:sqlserver://localhost:1433;databaseName=ApiTable;encrypt=true;trustServerCertificate=true";
		String selectUser = "sa";
		String selectPass = "root";
		String SelectSqlDB = "SELECT * FROM Api where id=" + number;
		Connection connection = null;
		try {
			Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
			DriverManager.registerDriver(driver);
			connection = DriverManager.getConnection(selectUrl, selectUser, selectPass);

			Statement st = connection.createStatement();

			ResultSet m = st.executeQuery(SelectSqlDB);

			if (m.next()) {

				do {

					System.out.println(" ***************************** " + "|");
					System.out.println("web_pages : " + m.getString(1));
					System.out.println("state_province : " + m.getString(2));
					System.out.println("alpha_two_code : " + m.getString(3));
					System.out.println("name : " + m.getString(4));
					System.out.println("country : " + m.getString(5));
					System.out.println("domains : " + m.getString(6));
					System.out.println(" ***************************** " + "|");

				} while (m.next());

			} else {
				System.out.println("No such user id is already registered");
			}

			connection.close();
		}

		catch (Exception ex) {
			System.err.println(ex);
		}

	}

	public static void UpdateById(Long number) {
		try{
		Scanner scanner = new Scanner(System.in);
		String selectUrl = "jdbc:sqlserver://localhost:1433;databaseName=ApiTable;encrypt=true;trustServerCertificate=true";
		String selectUser = "sa";
		String selectPass = "root";
		System.out.println(" Enter country to update ?");
		String country = scanner.next();
		System.out.println(" Enter domains to update ?");
		String domains = scanner.next();
		String SelectSqlDB = "Update Api SET country='"+country+"',domains='"+domains+"'where id=" + number;
		Connection conn = null;

		Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		DriverManager.registerDriver(driver);
		conn = DriverManager.getConnection(selectUrl, selectUser, selectPass);

		Statement st = conn.createStatement();

		int m = st.executeUpdate(SelectSqlDB);

		if (m >= 1) {
			System.out.println("Values updated in given Table...");

		} else {
			System.out.println("Values already updated in given database...");
		}

		conn.close();
		}

		catch (Exception ex) {
			System.err.println(ex);
		}

	}
	
	public static void deleteById(long id) {
		Scanner scanner = new Scanner(System.in);
		String url = "jdbc:sqlserver://localhost:1433;databaseName=ApiTable;encrypt=true;trustServerCertificate=true";
		String user = "sa";
		String pass = "root";
		
		String sqlDB = "delete from Api where id ="+id;
		Connection conn = null;
	try {
		Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
		DriverManager.registerDriver(driver);
		conn = DriverManager.getConnection(url, user, pass);

		Statement st = conn.createStatement();

		int m = st.executeUpdate(sqlDB);

		if (m >= 1) {
			System.out.println("Row Deleted in given Table...");

		} else {
			System.out.println(" Row already Deleted in given database...");
		}

		conn.close();
		  

	} catch (Exception ex) {
		System.err.println(ex);
	}
	}	
	public String getState_province() {
		return state_province;
	}

	public void setState_province(String state_province) {
		this.state_province = state_province;
	}

	public String getAlpha_two_code() {
		return alpha_two_code;
	}

	public void setAlpha_two_code(String alpha_two_code) {
		this.alpha_two_code = alpha_two_code;
	}

	public String getName() {
		return name;
	}

	public String[] getWeb_pages() {
		return web_pages;
	}

	public void setWeb_pages(String[] web_pages) {
		this.web_pages = web_pages;
	}

	public String[] getDomains() {
		return domains;
	}

	public void setDomains(String[] domains) {
		this.domains = domains;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	private String[] web_pages;
	private String state_province;
	private String alpha_two_code;
	private String name;
	private String country;
	private String[] domains;

}
