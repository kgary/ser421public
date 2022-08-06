package edu.asupoly.ser422.jdbcex;

import java.sql.*;

/*
This sample program connects to the database at the given URL and makes the specified query.
It takes as parameters the url, username, password, driver, and the dept name.
*/
public class DeptQueryByName
{
    public static void main(String[] args)
    {

	ResultSet rs = null;
	PreparedStatement stmt = null;
	Connection conn = null;

	if (args.length != 5)
	    {
		System.out.println("USAGE: java edu.asupoly.ser422.jdbcex.DeptQueryByName <user> <passwd> <url> <driver> <dept name>");
		System.exit(0);
	    }
	String _url = args[2];
	try {
	    // Step 1: Load the JDBC driver
	    Class.forName(args[3]);

	    // Step 2: make a connection
	    conn = DriverManager.getConnection(_url, args[0], args[1]);

	    // Step 3: Create a statement
	    stmt = conn.prepareStatement("Select * from Department WHERE dname=?");
	    stmt.setString(1, args[4]);

	    // Step 4: Make a query
	    rs = stmt.executeQuery();

	    // Step 5: Display the results
	    while (rs.next()) {
		System.out.print(rs.getString(1) + "\t");
		System.out.print(rs.getInt(2) + "\t ");
		System.out.print(rs.getInt(3) + "\t ");
		System.out.println(rs.getDate(4));
	    }

	    // Step 6: Show off some 2.0 features and retrieve data
	    // from ResultSet using named columns
	    /*
	    rs.beforeFirst(); 
	    System.out.println("\nHERE WE GO AGAIN\n");
	    while (rs.next()) {
		System.out.print(rs.getString("dname") + "\t");
		System.out.print(rs.getInt("dnumber") + "\t ");
		System.out.print(rs.getString("mgrssn") + "\t ");
		System.out.println(rs.getDate("date"));
	    }
	    */
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

