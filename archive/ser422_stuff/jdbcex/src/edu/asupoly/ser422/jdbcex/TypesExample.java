package edu.asupoly.ser422.jdbcex;

import java.sql.*;

/*
This sample program connects to the database at the given URL and makes the specified query.
It takes as parameters the username and password.
 */
public class TypesExample
{
	public static void main(String[] args)
	{
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;

		if (args.length != 5)
		{
			System.out.println("USAGE: java edu.asupoly.ser422.jdbcex.TypesExample <url><user><passwd><driver><query>");
			System.exit(0);
		}

		try {
			// Step 1: Load the JDBC driver
			Class.forName(args[3]);

			// Step 2: make a connection
			conn = DriverManager.getConnection(args[0], args[1], args[2]);

			// Step 3: Create a statement
			stmt = conn.createStatement();

			// Step 4: Make a query
			rs = stmt.executeQuery(args[4]);

			// Step 5: Use ResultSetMetaData to discover the size of the returned relation
			ResultSetMetaData metaData = rs.getMetaData();
			int numColumns = metaData.getColumnCount();

			// Step 5.1: Print column type info
			for (int i = 1; i <= numColumns; i++) {
				System.out.println("Column type from DB: " + metaData.getColumnTypeName(i));
				String sqlType = "unknown";
				switch (metaData.getColumnType(i)) {
				case java.sql.Types.BIGINT: sqlType = "BIGINT";
				break;
				case java.sql.Types.BIT: sqlType = "BIT";
				break;
				case java.sql.Types.INTEGER: sqlType = "INTEGER";
				break;
				case java.sql.Types.FLOAT: sqlType = "FLOAT";
				break;
				case java.sql.Types.VARCHAR: sqlType = "VARCHAR";
				break;
				case java.sql.Types.OTHER: sqlType = "OTHER";
				break;
				}
				System.out.println("java.sql.Types type: " + sqlType);
			}

			System.out.println("\n\n");

			// Step 5.2: Get the column header info for report writing

			for (int i=1; i <= numColumns; i++) {
				System.out.print(metaData.getColumnLabel(i) + "\t");
			}
			System.out.println("");

			// Step 6: Print out the results
			while (rs.next())
			{
				for (int i=1; i <= numColumns; i++) {
					System.out.print(rs.getObject(i).toString() + "\t");
				}
				System.out.println("");
			}

			// Step 7: Close the statement
			stmt.close();
			stmt = null;
			rs.close();
			rs = null;
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
		finally {  // ALWAYS clean up your DB resources
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}

