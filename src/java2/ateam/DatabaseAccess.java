package java2.ateam;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class DatabaseAccess {

	Connection conn;
	String dbPath;
	static final String dbName = "DefectTracker.accdb";

	public DatabaseAccess(String dbPath) {
		this.dbPath = dbPath;
	}

	public void getConnection() {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			
			String pathToDatabase = dbPath + dbName;
			
			this.conn = DriverManager.getConnection("jdbc:ucanaccess://" + pathToDatabase);
			
		} catch (SQLException ex) {
			System.err.println("Error: " + ex);
		} catch (ClassNotFoundException ex) {
			System.err.println("Error: " + ex);
		}
	}
	
	public void closeConnection(){
		try{
			conn.close();
		} catch (SQLException ex) {
			System.err.println("Error: " + ex);
		}		
	}

	public Integer insertDefect(String title, String statusId, int priorityId, int assigneeId, String description) {

		int rowsInserted = 0;
		String query = "INSERT into Defects (title, status, priority, assignee, description, create_date)"
						+ "VALUES (?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			Timestamp currentTime = new Timestamp(new Date().getTime());
			
			stmt.setString(1, title);
			stmt.setString(2, statusId);
			stmt.setInt(3, priorityId);
			stmt.setInt(4, assigneeId);
			stmt.setString(5, description);
			stmt.setTimestamp(6, currentTime);

			rowsInserted = stmt.executeUpdate();

		} catch (SQLException ex) {
			System.err.println("Error: " + ex);
		}
		
		return rowsInserted;
	}

	public Integer updateDefect(String summary, String statusId, int priority, int assigneeId, String description, int defectId) {
		int rowsUpdated = 0;

		String query = "UPDATE Defects set title = ?, status = ?, priority = ?, assignee = ?, description = ?"
				+ " WHERE defect_id = ?";
		try {

			PreparedStatement stmt = conn.prepareStatement(query);

			stmt.setString(1, summary);
			stmt.setString(2, statusId);
			stmt.setInt(3, priority);
			stmt.setInt(4, assigneeId);
			stmt.setString(5, description);
			stmt.setInt(6,  defectId);

			rowsUpdated = stmt.executeUpdate();

		} catch (SQLException ex) {
			System.err.println("Error: " + ex);
		}

		return rowsUpdated;
	}

	public Defect getDefect(int defectId) {

		String sql = "SELECT a.defect_id, a.title, a.status, c.name,  a.priority, d.name, a.assignee, b.first_name, b.last_name, b.email, a.description"
				+ " FROM Defects a INNER JOIN Users b on a.assignee = b.user_id"
				+ " INNER JOIN Status_Codes c on a.status = c.status_id"
				+ " INNER JOIN Priorities d on a.priority = d.priority_id" 
				+ " WHERE a.defect_id = ?"
				+ " ORDER BY defect_id DESC";

		Defect d = null;

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, defectId);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				
				int id = rs.getInt(1);
				String title = rs.getString(2);
				Status status = new Status(rs.getString(3), rs.getString(4));
				Priority priority = new Priority(rs.getInt(5), rs.getString(6));
				User assignee = new User(rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10));
				String description = rs.getString(11);

				d = new Defect(id, status, priority, assignee, title, description);
			}
			
			rs.close();

		} catch (SQLException ex) {
			System.err.println("Error: " + ex);
		}

		return d;
	}

	public ArrayList<Defect> getDefects() {

		String sql = "SELECT a.defect_id, a.title, a.status, c.name,  a.priority, d.name, a.assignee, b.first_name, b.last_name, b.email, a.description"
				+ " FROM Defects a INNER JOIN Users b on a.assignee = b.user_id"
				+ " INNER JOIN Status_Codes c on a.status = c.status_id"
				+ " INNER JOIN Priorities d on a.priority = d.priority_id" 
				+ " ORDER BY defect_id DESC";

		ArrayList<Defect> defects = new ArrayList<Defect>();

		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				
				int id = rs.getInt(1);
				String title = rs.getString(2);
				Status status = new Status(rs.getString(3), rs.getString(4));
				Priority priority = new Priority(rs.getInt(5), rs.getString(6));
				User assignee = new User(rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10));
				String description = rs.getString(11);

				Defect d = new Defect(id, status, priority, assignee, title, description);

				defects.add(d);
			}

			rs.close();
			
		} catch (SQLException ex) {
			System.err.println("Error: " + ex);
		}

		return defects;
	}

	public ArrayList<Priority> getPriorities() {
		String sql = "SELECT priority_id, name"
				+ " FROM Priorities";

		ArrayList<Priority> priorities = new ArrayList<Priority>();
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				
				int id = rs.getInt(1);
				String name = rs.getString(2);

				Priority p = new Priority(id, name);

				priorities.add(p);
			}

			rs.close();
			
		} catch (SQLException ex) {
			System.err.println("Error: " + ex);
		}

		return priorities;
	}

	public ArrayList<Status> getStatuses() {
		String sql = "SELECT status_id, name"
				+ " FROM Status_Codes";

		ArrayList<Status> statuses = new ArrayList<Status>();
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				
				String id = rs.getString(1);
				String name = rs.getString(2);

				Status s = new Status(id, name);

				statuses.add(s);
			}

			rs.close();
			
		} catch (SQLException ex) {
			System.err.println("Error: " + ex);
		}
		
		return statuses;
	}

	public ArrayList<User> getUsers() {
		String sql = "SELECT user_id, first_name, last_name, email"
				+ " FROM Users";

		ArrayList<User> names = new ArrayList<User>();

		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				
				int id = rs.getInt(1);
				String fname = rs.getString(2);
				String lname = rs.getString(3);
				String email = rs.getString(4);

				User u = new User(id, fname, lname, email);

				names.add(u);
			}

			rs.close();
			
		} catch (SQLException ex) {
			System.err.println("Error: " + ex);
		}
		
		return names;
	}
}
