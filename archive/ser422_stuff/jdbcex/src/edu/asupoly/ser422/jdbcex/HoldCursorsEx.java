package edu.asupoly.ser422.jdbcex;

import java.sql.*;

import edu.asupoly.ser422.Ser422DbUtils;

public class HoldCursorsEx {
	@SuppressWarnings("resource")
	public static void main(String[] args) {

		Connection conn = null;
		Statement  stmt = null;
		ResultSet  rs   = null;

		if (args.length != 6) {
			System.out.println("USAGE: java edu.asupoly.ser422.jdbcex.HoldCursorsEx <driver><url><user><passwd><select query><update query>");
			System.exit(0);
		}

		try {
			Class.forName(args[0]);
			conn = DriverManager.getConnection(args[1], args[2], args[3]);
			conn.setAutoCommit(false); // do not autocommit DML
			System.out.println(conn.getClass());

			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE,
					ResultSet.HOLD_CURSORS_OVER_COMMIT);

			rs = stmt.executeQuery(args[4]);

			System.out.println("Executed query, let's print...");
			if (rs.next()) {
				Ser422DbUtils.printResultSet(rs);
			}
			else {
				System.out.println("Empty resultset!");
			}

			// Now run the update query. Notice we haven't closed anything yet
			stmt = conn.createStatement();
			if (stmt.executeUpdate(args[5]) == 0) {
				System.out.println("No rows inserted/updated/deleted");
			}
			else {
				System.out.println("DML successful: " + args[5]);
			}
			// make durable
			conn.commit();

			// reposition our cursor. ResultSet should be there!
			rs.first();
			Ser422DbUtils.printResultSet(rs);
		}
		catch (Exception se) {
			se.printStackTrace();
		}
		finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			}
			catch (Exception exc) {
				exc.printStackTrace();
			}
		}
	}


	public static void whereAmI(ResultSet rs) throws SQLException {
		if (rs.isBeforeFirst()) {
			System.out.println("You are before first");
		}
		else if (rs.isFirst()) {
			System.out.println("You are at the first row");
			// let's output this row
			Ser422DbUtils.printResultSetRow(rs);
		}
		else if (rs.isLast()) {
			System.out.println("You are at the last row");
			// let's output this row
			Ser422DbUtils.printResultSetRow(rs);
		}
		else if (rs.isAfterLast()) {
			System.out.println("You are after the last row");
		}
		else {
			System.out.println("You are so lost dude...");
			// let's output this row
			Ser422DbUtils.printResultSetRow(rs);
		}
	}
}    
