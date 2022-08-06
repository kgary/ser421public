package edu.asupoly.ser422.jdbcex;

import java.sql.*;

/*
This sample program connects to the database at the given URL and makes the specified query.
It takes as parameters the username and password.
*/
public class DeptQueryMetaData
{
    public static void main(String[] args)
    {
	ResultSet rs = null;
	Statement stmt = null;
	Connection conn = null;

	if (args.length != 4)
	    {
		System.out.println("USAGE: java edu.asupoly.ser422.jdbcex.DeptQuery <user> <passwd> <url> <driver>");
		System.exit(0);
	    }
	String _url = args[2];
	try {
	    // Step 1: Load the JDBC driver
	    Class.forName(args[3]);

	    // Step 2: make a connection
	    conn = DriverManager.getConnection(_url, args[0], args[1]);

	    // Step 3: Create a statement
	    stmt = conn.createStatement();

	    // Step 4: Make a query
	    rs = stmt.executeQuery("Select * from department");

	    // Step 5: Use ResultSetMetaData to discover the size of the returned relation
	    ResultSetMetaData metaData = rs.getMetaData();

	    // Step 5.1: Get the column header info for report writing
	    int numColumns = metaData.getColumnCount();
	    for (int i=1; i <= numColumns; i++)
		System.out.print(metaData.getColumnLabel(i) + "\t");
	    System.out.println("");

	    // Step 6: Print out the results
	    while (rs.next())
		{
		    for (int i=1; i <= numColumns; i++) {
		    	Object obj = rs.getObject(i);
		    	if (obj != null)
		    		System.out.print(rs.getObject(i).toString() + "\t");
		    }
		   	System.out.println("");
		}

	    // Step 7: Close the resultset and statement
	    rs.close();
	    rs = null;
	    stmt.close();
	    stmt = null;
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
	    } catch (Throwable t1) {
		System.out.println("A problem closing db resources!");
	    }
	    try {
		if (conn != null)
		    conn.close();
	    }
	    catch (Throwable t2) {
		System.out.println("Oh-oh! Connection leaked!");
	    }
	}
    }
}

