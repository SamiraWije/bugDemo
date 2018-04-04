package bugDemo;

import java.io.IOException;
import java.sql.SQLException;

import org.codehaus.jackson.map.ObjectMapper;

public class ViewBugs {

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		SQLQuery sql = new SQLQuery();

		ObjectMapper mapper = new ObjectMapper();

		try {

			String jsonInString = mapper.writeValueAsString(sql.sqlQuery());
			System.out.println("JSON: \n" + jsonInString);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

}