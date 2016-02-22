//Heather Bowling

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListDefectsDAO {

	//LL changed <DefectInfo> from <ListDefects>
	ArrayList<DefectInfo> arrayList = new ArrayList<DefectInfo>();
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;

	public ListDefectsDAO() {

				makeConnection();
				
				try {
					String tempCloseDate = " ";
					String q = "SELECT * FROM defect";
					st = con.createStatement();
					rs = st.executeQuery(q);			
					while (rs.next()){
						int tempDefectId = rs.getInt(1);
						String tempOpenDate = rs.getTimestamp(2).toString();
						if (rs.getTimestamp(3) != null){
							tempCloseDate = rs.getTimestamp(3).toString(); }
						else{
							tempCloseDate = " "; }
						String tempReporterId = rs.getString(4);
						String tempDefectSummary = rs.getString(5);
						String tempDetailDescription = rs.getString(6);
						String tempAssignee = rs.getString(7);
						String tempStatus = rs.getString(8);
						String tempPriority = rs.getString(9);
						String tempComments = rs.getString(10);
//need to add comments from history table.  Connect by defect_id				
						
						//LL changed <DefectInfo> from <ListDefects>
						DefectInfo e = new DefectInfo(tempDefectId, tempOpenDate, tempCloseDate, tempReporterId, tempDefectSummary
								        , tempDetailDescription, tempAssignee, tempStatus, tempPriority, tempComments);
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

	public String getCurrentListFromDefect(int s) {
		String str = "";

		for (int i = 0; i < arrayList.size(); i++) {
			if (arrayList.get(i).getDefectID() == s) {
				str += arrayList.get(i).getOpenDate();
				str += arrayList.get(i).getCloseDate();
				str += arrayList.get(i).getReporterID();
				str += arrayList.get(i).getSummary();
				str += arrayList.get(i).getDescription();
				str += arrayList.get(i).getAssigneeID();
				str += arrayList.get(i).getStatus();
				str += arrayList.get(i).getPriority();
				str += arrayList.get(i).getComments();
				str += "\n";
			}
		}
		return str;
	}

/*
	 * public void updateDefect(ListDefects i) { 
	 * makeConnection();
	 * 
	 * try { 
	 * 		String q = "update  Users (user_id, first_name, last_name, role, email) values " + " ('" + i.getUserId() + "', '" 
	 * 					+ i.getFirstName() + "', '" + i.getLastName() + "', '" + i.getRole() + "', '" + i.getEmail() + "');";
	 * 
	 * st = con.createStatement();
	 * st.executeUpdate(q);
	 * 
	 * if (st != null) { 
	 * 	st.close(); 
	 * } 
	 * 
	 * if (con != null) { 
	 * 	con.close(); 
	 * }
	 * 
	 * } catch (SQLException ex) { 
	 * 		System.out.println("Error with table or data");
	 * }
	 * 
	 * }
	 */
	
//LL changed <DefectInfo> from <ListDefects>
//Heather changed the insertNewDefect to not pass the defect_id, open_date, or the close_date. This one is now working as it should.
	public void insertNewDefect(DefectInfo i) {
		makeConnection();

		try {
			String q = "insert into defect (reporter_id, defect_summary, detail_description, assignee, status, priority, comments) values " 
					+ " ('" + i.getReporterID() + "', '" + i.getSummary() + "', '" + i.getDescription() + "', '" 
					+ i.getAssigneeID() + "', '" + i.getStatus() + "', '" + i.getPriority() + "', '" + i.getComments() + "');";

			st = con.createStatement();
			st.executeUpdate(q);

			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (SQLException ex) {
			System.out.println("Error with table or data; inserting into defect table");
		}

	}

// Heather added this to add a row to the history table after we insert into the defect table but it's not working.  I sent an 
// email to Kelli with the code snippet but i'm also going to take my laptop to work tomorrow to see if I can get some help from someone 
// at work.  See if they see anything.  If any of you take a look and can figure it out let me know.  Since we auto increment the defect_id
// in the insertDefect we have to retrieve the defect_id since that is a foreign key to the history table.  I've tested the query in mysql
// and it works but seems to fail here.  I'm fried so I'm going to look at it tomorrow night.  if it holds you up comment out the insertHistory
// in addDefect. Sorry ladies was hoping to make this work so I could move on. 
	public int getDefectId() {
		
		makeConnection();
		int tempDefectId = 0;

		try {

			String q1 = "SELECT MAX(DEFECT_ID) FROM defect";
			st = con.createStatement();
			rs = st.executeQuery(q1);
			rs.next();
			tempDefectId = rs.getInt(1);
			System.out.println("tempDefectId: " + tempDefectId);
			
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
			System.out.println("Error with table or data; error selecting defect_id");
		}
		
		return tempDefectId;

	}

	public String selectDefectbyID(int id) {
				
		makeConnection();
		String chgLog = " ";

		try {
			String q2 = "SELECT * FROM defect where defect_id = id";
			st = con.createStatement();
			rs = st.executeQuery(q2);
			rs.next();
			
			System.out.println("before the resultset move statements");
			int defectId = rs.getInt(1); 
			String openDate = rs.getTimestamp(2).toString();
			String closeDate = rs.getDate(3).toString();
			String reporterId = rs.getString(4);
			String defectSummary = rs.getString(5);
			String detailDescription = rs.getString(6);
			String assignee = rs.getString(7);
			String status = rs.getString(8);
			String priority = rs.getString(9);
			String comments = rs.getString(10);
			
			DefectInfo e = new DefectInfo(defectId, openDate, closeDate, reporterId, defectSummary, detailDescription, assignee, status, priority, comments);
			chgLog = e.toString();
			
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
			System.out.println("Error with table or data; error selecting defect using defect_id");
		}
		
		return chgLog;

	}

	public void insertHistory(DefectInfo h) {
		
		makeConnection();

		try {
			
			String q = "insert into history (defect_id, user_id, change_log) values " 
					+ " ('" + h.getDefectID() + "', '" + h.getReporterID() + "', '" + h.getChangeLog() + "');";

			st = con.createStatement();
			st.executeUpdate(q);

			if (st != null) {
				st.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (SQLException ex) {
			System.out.println("Error with table or data; inserting into history table");
		}

	}

	//LL changed ListDefects to DefectInfo 
	public void deleteDefect(DefectInfo i) {
		makeConnection();

		try {
			String q = "delete from defect where defect_id = '" + i.getDefectID() + "' and open_date = '" + i.getOpenDate()
					+ "' and close_date = '" + i.getCloseDate() + "' and reporter_id = '" + i.getReporterID() + "' and defect_summary = '"
					+ i.getSummary() + "' and detail_description = '" + i.getDescription() + "' and assignee = '" + i.getAssigneeID()
					+ "' and status = '" + i.getStatus() + "' and priority = '" + i.getPriority() + "' and comments = '" + i.getComments()
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
				Logger lgr = Logger.getLogger(ListDefectsDAO.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
				System.out.println("Sql Exception");

			}

		}
}
