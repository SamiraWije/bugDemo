package bugDemo;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class MYSQLAccess {

	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private Session session = null;

	private String sshHost = "192.168.10.23";
	private String serverUser = "bugzilla";
	private String serverPassword = "Auxenta123";
	private int port = 22;

	private int localPort = 3306;

	/*
	 * final private String host = "jdbc:mysql://192.168.10.23:3306/bugs"; final
	 * private String host = "jdbc:mysql://127.0.0.1:3306/bugs?"; final private
	 * String user = "root"; final private String password = "Aux@123";
	 */

	// "jdbc:mysql://192.168.10.23:3306/bugs?verifyServerCertificate=false&useSSL=true";
	final private String host = "jdbc:mysql://127.0.0.1:" + localPort + "/bugs";
	final private String user = "bugs";
	final private String password = "Rambo123";

	public void readDataBase() throws Exception {
		try {

			JSch jsch = new JSch();
			// get SSH session

			session = jsch.getSession(serverUser, sshHost, port);
			session.setPassword(serverPassword);
			java.util.Properties config = new java.util.Properties();
			// Never automatically add new host keys to the host file
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			// Connect to remote server
			session.connect();
			// Apply the port forwarding
			System.out.println("Connected");
			// String boundaddress ="0.0.0.0";
			// session.setPortForwardingL(lport, host, rport);

			// Load the mysql driver
			Class.forName("com.mysql.jdbc.Driver");

			// setup the connection
			connect = (Connection) DriverManager.getConnection(host, user, password);
			System.out.println("Connection to database established!");
			statement = (Statement) connect.createStatement();

			// get the result of the sql query
			resultSet = statement.executeQuery(
					"SELECT products.name as project_name, bugs.priority as priority ,count(bugs.priority) as bugs\n"
							+ "FROM bugs\n" + "INNER JOIN products ON bugs.product_id=products.id\n"
							+ "GROUP BY bugs.priority, products.name");

			writeResultSet(resultSet);
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}

	private void writeResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		while (resultSet.next()) {

			String project_name = resultSet.getString("project_name");
			String bug_priority = resultSet.getString("priority");
			String project_bugs = resultSet.getString("bugs");

			System.out.println("Project Name: " + project_name + "\n" + "Priority: " + bug_priority + "\n" + "Bugs: "
					+ project_bugs);

		}
	}

	// Close the result set
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {
			System.out.println(e);

		}
	}

}
