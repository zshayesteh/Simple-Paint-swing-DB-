package HW9_simplePaint;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

public class pageDBManager {
	Statement stmt;

	public pageDBManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://127.0.0.1/paint?user=root&password=";
			Connection conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();
		} catch (Exception e) {
			System.out.println("exception " + e);
		}
	}

	public void insertSnapshot(Page p) throws SQLException {// inserting a new
															// file address
		stmt.executeUpdate(
				MessageFormat.format("update page set capture_image_address=''{0}'' where person_username=''{1}'';",
						p.getFileAddress(), p.getUsername()));
	}

	public String getSnapshot(Page p) throws SQLException {// retriving the
															// current person's
															// page
		String file = "";
		ResultSet rs = stmt.executeQuery(
				"select capture_image_address from page where person_username='" + Person.getUsername() + "';");
		while (rs.next()) {
			file = rs.getString(1);
			break;
		}
		return file;
	}
}
