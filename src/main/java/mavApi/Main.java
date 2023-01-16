package mavApi;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

public class Main {

	public static void main(String[] args) {
		Api api = new Api();

		Scanner scannerr = new Scanner(System.in);
		boolean menuExit = true;
		while(menuExit) {
			System.out.println("1- Fetch API AND DISPLAY" );
			System.out.println("2- CREATE TABLE IN DATABASE " );
			System.out.println("3- INSERT INTO TABLE " );
			System.out.println("4- READE FROM TABLE " );
			System.out.println("5- UPDATE FROM TABLE " );
			System.out.println("6- DELETE FROM TABLE " );
			int option = scannerr.nextInt();
			switch(option) {
			
			case 1 :
				URL url;
				try {
					url = new URL("http://universities.hipolabs.com/search?country=United+States");

					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.connect();
					StringBuilder apiInformation = new StringBuilder();
					int responseCode = conn.getResponseCode();
					if (responseCode != 200) {
						throw new RuntimeException("HttpresponseCode");

					} else {
						Scanner scanner = new Scanner(url.openStream());
						while (scanner.hasNext()) {
							apiInformation.append(scanner.nextLine());
						}
						scanner.close();
					System.out.println(apiInformation);
						Gson gson = new Gson();


							Api[] apiResult = gson.fromJson(apiInformation.toString(), Api[].class);

						for (Api x : apiResult) {

							try {
								System.out.println(" ***************************** " + "|");
//						System.out.println("id : "+k);
								System.out.println("web_pages : " + x.getWeb_pages());
								System.out.println("state_province : " + x.getState_province());
								System.out.println("alpha_two_code : " + x.getAlpha_two_code());
								System.out.println("name : " + x.getName());
								System.out.println("country : " + x.getCountry());
								System.out.println("domains : " + x.getDomains());

								System.out.println("|" + " ***************************** " + "|");
							} catch (Exception e) {
							}

						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				}
				break;
				
			case 2:
				String urld = "jdbc:sqlserver://localhost:1433;databaseName=ApiTable;encrypt=true;trustServerCertificate=true";
				String user = "sa";
				String pass = "root";
				String sqlDB = "CREATE TABLE Api " + "(id INTEGER Identity(1,1), " + " web_pages TEXT not null, "
						+ " state_province VARCHAR(20), " + " alpha_two_code VARCHAR(20), " +"name VARCHAR(50), "+"country VARCHAR(20) ,"+"domains TEXT,"+" PRIMARY KEY ( id ))";

				Connection conn = null;
				try {

					Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
					DriverManager.registerDriver(driver);
					conn = DriverManager.getConnection(urld, user, pass);

					Statement st = conn.createStatement();

					int m = st.executeUpdate(sqlDB);
					if (m >= 1) {
						System.out.println("Created table in given database...");
						
					} else {
						System.out.println(" table already Created in given database...");
					}
					conn.close();
				}

				catch (Exception ex) {
					System.err.println(ex);
				}

				break;
				
			case 3:
				String urld1 = "jdbc:sqlserver://localhost:1433;databaseName=ApiTable;encrypt=true;trustServerCertificate=true";
				String user1 = "sa";
				String pass1 = "root";
				URL urll;
				try {
					urll = new URL("http://universities.hipolabs.com/search?country=United+States");

					HttpURLConnection connn = (HttpURLConnection) urll.openConnection();
					connn.setRequestMethod("GET");
					connn.connect();
					StringBuilder apiInformation = new StringBuilder();
					int responseCode = connn.getResponseCode();
					if (responseCode != 200) {
						throw new RuntimeException("HttpresponseCode");

					} else {
						Scanner scanner = new Scanner(urll.openStream());
						while (scanner.hasNext()) {
							apiInformation.append(scanner.nextLine());
						}
						scanner.close();
//					System.out.println(apiInformation);
						Gson gson = new Gson();


				Api[] apiResult = gson.fromJson(apiInformation.toString(), Api[].class);
				for (Api x : apiResult) {
	
				String sqlDBInsert = "INSERT INTO Api (" +" web_pages,"+ " state_province," + " alpha_two_code ," +"name,"+"country,"+"domains "+")VALUES("
				+"'"+x.getWeb_pages()[0]+"','"+x.getState_province()+"','"+x.getAlpha_two_code()+"','"+x.getName()+"','"+x.getCountry()+"','"+x.getDomains()[0]+"')";

				Connection Insertconn = null;
				

					Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
					DriverManager.registerDriver(driver);
					Insertconn = DriverManager.getConnection(urld1, user1, pass1);

					Statement st = Insertconn.createStatement();

					int m = st.executeUpdate(sqlDBInsert);
					if (m >= 1) {
						System.out.println("Created table in given database...");
						
					} else {
						System.out.println(" table already Created in given database...");
					}
					Insertconn.close();
				}


				
			
				}
				}catch (Exception ex) {
					System.err.println(ex);
				}
	
				break;
				
			case 4:
				System.out.println(" Enter id ?");
				long id = scannerr.nextInt();

					api.selectById(id);
					
				
				break;
				
			case 5:
				System.out.println(" Enter id ?");
				long updateid = scannerr.nextInt();
				api.UpdateById(updateid);
				
				break;
				
			case 6:
				System.out.println(" Enter id to be deleted ?");
				  int deleteId = scannerr.nextInt();
				  api.deleteById(deleteId);
				
				break;
			
			
			}
			}
			}
		
		
		
		
		
				
				
		
		
		
				
}
