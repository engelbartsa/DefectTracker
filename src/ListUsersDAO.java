//Heather Bowling

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListUsersDAO {

	//LL changed ListUsers to UserInfo 
	ArrayList<UserInfo> arrayList = new ArrayList<UserInfo>();
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;

	public ListUsersDAO() {

			makeConnection();
			
			try {
				String q = "SELECT * FROM user ";
				st = con.createStatement();
				rs = st.executeQuery(q);			
				while (rs.next()){
					String tempUserId = rs.getString(1);
					String tempFirstName = rs.getString(2);
					String tempLastName = rs.getString(3);
					String tempRole = rs.getString(4);
					String tempEmail = rs.getString(5);
					
					//LL changed ListUsers to UserInfo 
					UserInfo e = new UserInfo(tempUserId, tempFirstName, tempLastName, tempRole, tempEmail);
					arrayList.add(e);			
				}
				if (rs!= null) {
					rs.close();
				}
				if (st!= null) {
					st.close();
				}
				if (con!= null) {
					con.close();
				}
					
			} catch (SQLException ex) {
				System.out.println("Error with table or data");
			}

		}

	public String getCurrentList() {

		String str = "";

		return str;
	}

	public String getCurrentListFromUser(String s) {
		String str = "";

		for (int i = 0; i < arrayList.size(); i++) {
			if (arrayList.get(i).getUserID().equals(s)) {
				str += arrayList.get(i).getFirstName();
				str += arrayList.get(i).getLastName();
				str += arrayList.get(i).getRole();
				str += arrayList.get(i).getEmail();
				str += "\n";
			}
		}
		return str;
	}

/*	public void updateUser(ListUsers i) {
		makeConnection();

		try {
			String q = "update user (user_id, first_name, last_name, role, email) values " + " ('" + i.getUserId()
					+ "', '" + i.getFirstName() + "', '" + i.getLastName() + "', '" + i.getRole() + "', '" + i.getEmail()
					+ "');";

			st = con.createStatement();
			st.executeUpdate(q);

			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (SQLException ex) {
			System.out.println("Error with table or data");
		}
		
	}*/
	
	//LL changed ListUsers to UserInfo 
	public void insertNewUser(UserInfo i) {
		makeConnection();

		try {
			String q = "insert into user (user_id, first_name, last_name, role, email) values " + " ('" + i.getUserID()
					+ "', '" + i.getFirstName() + "', '" + i.getLastName() + "', '" + i.getRole() + "', '" + i.getEmail()
					+ "');";

			st = con.createStatement();
			st.executeUpdate(q);

			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (SQLException ex) {
			System.out.println("Error with table or data");
		}

	}

	//LL changed ListUsers to UserInfo 
	public void deleteUser(UserInfo i) {
		makeConnection();

		try {
			String q = "delete from user where user_id = '" + i.getUserID() + "' and first_name = '" + i.getFirstName() 
					+ "' and last_name = '" + i.getLastName() + "' and role = '" + i.getRole() + "' and email = '" + i.getEmail()  
					+ "' limit 1";

			st = con.createStatement();
			st.executeUpdate(q);

			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (SQLException ex) {
			System.out.println("Error with table or data");
		}

	}

	public void makeConnection() {
		String url = "jdbc:mysql://localhost:3306/defect_tracking";
		String user = "root";
		String password = "disney99";
	
		try {

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Connection made");

		} catch (Exception ex) {
			Logger lgr = Logger.getLogger(ListUsersDAO.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
			System.out.println("Sql Exception");

		}

	}
}