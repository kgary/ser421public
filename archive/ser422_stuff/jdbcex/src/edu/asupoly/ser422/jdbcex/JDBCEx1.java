package edu.asupoly.ser422.jdbcex;

import java.sql.*;

/*
This sample program connects to the database at the given URL and makes the specified query.
It takes as parameters the fully qualified classname of the JDBC database driver, the URL
to the database, username, password, and the query string (in quotes). It is assumed the
query is a SELECT query, not an INSERT, UPDATE, or DELETE.
*/
public class JDBCEx1
{
    public static void main(String[] args)
    {
	if (args.length != 5)
	    {
		System.out.println("USAGE: java edu.asupoly.ser422.jdbcex.JDBCEx1 <url> <username> <passwd> <driver> <query>");
		System.exit(0);
	    }

	ResultSet rs = null;
	Statement stmt = null;
	Connection conn = null;

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

	    // Step 5.1: Get the column header info for report writing
	    int numColumns = metaData.getColumnCount();
	    for (int i=1; i <= numColumns; i++)
		System.out.print(metaData.getColumnLabel(i) + "\t");
	    System.out.println("");

	    // Step 6: Print out the results
	    Object obj = null;
	    while (rs.next())
		{
		    for (int i=1; i <= numColumns; i++) {
			obj = rs.getObject(i);
			if (obj != null) {
			    System.out.print(rs.getObject(i).toString() + "\t");
			}
			else {
			    System.out.print("\t\t");
			}
		    }
		    System.out.println("");
		}

	}
	catch (Exception exc)
	    {
		exc.printStackTrace();
		System.exit(0);
	    }
	finally { // always close those resources!!!
	    // Step 7: Close the resultset and statement
	    try {
		rs.close();
		stmt.close();
	    }
	    catch (Throwable t) {
		// no matter what was thrown, avoid abnormal termination
		// so we can close the connection!
	    }
	    try {
		conn.close();
	    }
	    catch (Throwable t2) {
		System.err.println("Uh-oh! Connection leaked!");
	    }
	}
    }
}

