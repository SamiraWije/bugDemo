package bugDemo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.DriverManager;
import java.util.Properties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DBConnect {

	private Connection connect = null;
	private Statement statement = null;
	final private String host = "jdbc:mysql://127.0.0.1:3306/bugs?";
	/*
	 * final private String user = "root"; final private String password =
	 * "Aux@123";
	 */

	Properties prop = new Properties();
	InputStream input = null;

	public Statement getStatement() {
		return statement;
	}

	public Statement setStatement(Statement statement) {
		this.statement = statement;
		return statement;
	}

	public Statement dbConnect() throws Exception {
		try {

			input = new FileInputStream(
					"/home/aux-046/eclipse-workspace-new/bugDemo/src/bugDemo/application.properties");

			// load a properties file
			prop.load(input);

			Class.forName("com.mysql.jdbc.Driver");

			// setup the connection
			connect = (Connection) DriverManager.getConnection(host, prop.getProperty("user"),
					prop.getProperty("password"));
			// System.out.println("Connection to database established!");
			statement = (Statement) connect.createStatement();

			return setStatement((Statement) connect.createStatement());

		} catch (Exception e) {
			throw e;
		}
	}

}
