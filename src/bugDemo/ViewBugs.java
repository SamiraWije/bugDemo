package bugDemo;

public class ViewBugs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MYSQLAccess dao = new MYSQLAccess();
		try {
			dao.readDataBase();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e);
		}
		

	}

}
