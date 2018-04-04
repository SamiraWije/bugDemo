package bugDemo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLQuery {

	private ResultSet resultSet = null;

	DBConnect db = new DBConnect();

	ArrayList<BugzillaResults> list = new ArrayList<BugzillaResults>();

	public ArrayList<BugzillaResults> sqlQuery() throws SQLException, Exception {

		resultSet = db.dbConnect().executeQuery(
				"SELECT products.name as project_name, bugs.priority as priority ,count(bugs.priority) as bugs\n"
						+ "FROM bugs\n" + "INNER JOIN products ON bugs.product_id=products.id\n"
						+ "GROUP BY bugs.priority, products.name");

		return (ArrayList<BugzillaResults>) writeResultSet(resultSet);
	}

	private List<BugzillaResults> writeResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			BugzillaResults bResults = new BugzillaResults();
			String project_name = resultSet.getString("project_name");
			String bug_priority = resultSet.getString("priority");
			String project_bugs = resultSet.getString("bugs");

			/*
			 * System.out.println("Project Name: " + project_name + "\n" + "Priority: " +
			 * bug_priority + "\n" + "Bugs: " + project_bugs);
			 */

			bResults.setProject_name(project_name);
			bResults.setBug_priority(bug_priority);
			bResults.setProject_bugs(project_bugs);

			list.add(bResults);

		}

		/*for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getProject_name());
			System.out.println(list.get(i).getBug_priority());
			System.out.println(list.get(i).getProject_bugs());

		}*/

		return list;
	}

}
