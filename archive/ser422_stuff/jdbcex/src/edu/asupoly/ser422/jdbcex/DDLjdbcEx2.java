package edu.asupoly.ser422.jdbcex;

import java.sql.*;

public class DDLjdbcEx2 {
    public static void main(String[] args)
    {
	ResultSet rs = null;
	Statement stmt = null;
	Connection conn = null;

	if (args.length != 4)
	    {
		System.out.println("USAGE: java edu.asupoly.ser422.jdbcex.DDLjdbcEx1 <url> <user> <passwd> <driver>");
		System.exit(0);
	    }

	try {
	    String _url = args[0];

	    // Step 1: Load the JDBC driver
	    Class.forName(args[3]);

	    // Step 2: make a connection
	    conn = DriverManager.getConnection(_url, args[1], args[2]);
	    conn.setAutoCommit(false);

	    // Step 2.1 - get the DB MetaData
	    DatabaseMetaData dbmd = conn.getMetaData();

	    // get the table named tab1 if it exists
	    rs = dbmd.getTables(null, null, "tab1", null);
	    if (rs.next()) {
		System.out.println("Table tab1 found, removing...");
		// the table exists, better remove it
		stmt = conn.createStatement();
		stmt.executeUpdate("DROP TABLE tab1");
		stmt.close();
	    }
	    if (rs != null) rs.close();

	    // Step 3 now we can recreate as needed
	    stmt = conn.createStatement();	    
	    stmt.executeUpdate("CREATE TABLE tab1 (id integer NOT NULL, name varchar(300)," +
			       " PRIMARY KEY(id))");
	    stmt.close();

	    // Let's write a tuple or two
	    stmt = conn.createStatement();
	    if (stmt.executeUpdate("INSERT INTO tab1 VALUES (1, 'Joe')") > 0) {
		System.out.println("Inserted first tuple OK");
	    }
	    stmt.close();

	    stmt = conn.createStatement();
	    if (stmt.executeUpdate("INSERT INTO tab1 VALUES (2, 'Sue')") > 0) {
		System.out.println("Inserted second tuple OK");
	    }
	    stmt.close();

	    // Have to do this to write changes to a DB
	    conn.commit();
	}
	catch (Exception se) {
	    se.printStackTrace();
	}
	finally {
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
