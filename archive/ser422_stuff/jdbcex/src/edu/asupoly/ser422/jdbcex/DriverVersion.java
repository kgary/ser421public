/*
  Sample program to play around with multiple database drivers
  Author: Kevin Gary
 
  USAGE: java edu.asupoly.ser422.jdbcex.DriverVersion <query> <url> <user> <passwd> <classname> [<classname>|...]

  Example:
  % java edu.asupoly.ser422.jdbcex.DriverVersion "select * from test1" "jdbc:postgresql://localhost:5434/demo" demo ser422 org.postgresql.Driver com.mysql.jdbc.Driver oracle.jdbc.driver.OracleDriver net.sourceforge.jtds.jdbc.Driver

  Output:
Loading driver class: org.postgresql.Driver
Loading driver class: com.mysql.jdbc.Driver
Loading driver class: oracle.jdbc.driver.OracleDriver
Loading driver class: net.sourceforge.jtds.jdbc.Driver
Listing all loaded drivers...
Found driver: 7, 3
Found driver: 3, 0
Found driver: 1, 0
It is a JDBC compliant driver
Found driver: 0, 5
Executing query select * from test1 on URL jdbc:postgresql://localhost:5434/demo
Got Driver org.postgresql.Driver@9664a1, making Connection
id	name	
2	YYYYYYYYYYY	
1	YYYYYYYYYY	
Deregistering drivers
De-registering driver org.postgresql.Driver@9664a1
De-registering driver com.mysql.jdbc.Driver@1cd2e5f
De-registering driver oracle.jdbc.driver.OracleDriver@13c5982
De-registering driver net.sourceforge.jtds.jdbc.Driver@c2ea3f
Listing all loaded drivers...
 */

package edu.asupoly.ser422.jdbcex;

import java.sql.*;
import java.util.*;

public class DriverVersion {
	public static void main(String[] args)
	{
		Connection conn = null;
		Statement  stmt = null;
		ResultSet  rs   = null;

		if (args.length < 5)
		{
			System.out.println("USAGE: java edu.asupoly.ser422.jdbcex.DriverVersion <query> <url> <user> <passwd> <classname> [<classname>|...]");
			System.exit(0);
		}

		for (int i = 4; i < args.length; i++) {
			System.out.println("Loading driver class: " + args[i]);

			try {
				// Load the JDBC driver
				Class.forName(args[i]);
			}
			catch (Throwable t) {
				System.out.println("\tFAILED! Skipping " + args[i]);
			}
		}

		try {
			printLoadedDrivers();

			System.out.println("Executing query " + args[0] + " on URL " + args[1]);
			Driver driver = DriverManager.getDriver(args[1]);
			if (driver != null) {
				System.out.println("Got Driver " + driver.toString() + ", making Connection");
				Properties props = new Properties();
				props.put("user", args[2]);
				props.put("password", args[3]);
				conn = driver.connect(args[1], props);
				stmt = conn.createStatement();
				rs = stmt.executeQuery(args[0]);
				printResultSet(rs);
				rs.close(); rs = null;
				stmt.close(); stmt = null;
				conn.close(); conn = null;
			}
			else {
				System.out.println("Driver cannot understand protocol!");
			}

			System.out.println("Deregistering drivers");
			Enumeration<Driver> e = DriverManager.getDrivers();
			for (; e.hasMoreElements(); ) {
				driver = (Driver)e.nextElement();
				System.out.println("De-registering driver " + driver);
				DriverManager.deregisterDriver(driver);
			}
			printLoadedDrivers();
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			}
			catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		System.exit(0);
	}

	private static void printLoadedDrivers() throws SQLException {
		System.out.println("Listing all loaded drivers...");
		Enumeration<Driver> e = DriverManager.getDrivers();
		Driver d = null; 

		for (; e.hasMoreElements(); ) {
			d = (Driver)e.nextElement();
			System.out.println("Found driver: " + d.getMajorVersion() + ", " + d.getMinorVersion());
			if (d.jdbcCompliant()) {
				System.out.println("It is a JDBC compliant driver");
			}
		}
	}

	private static void printResultSet(ResultSet rs) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();

		// Get the column header info for report writing
		int numColumns = metaData.getColumnCount();
		for (int i=1; i <= numColumns; i++)
			System.out.print(metaData.getColumnLabel(i) + "\t");
		System.out.println("");

		// Print out the results
		Object obj = null;
		while (rs.next()) {
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

}
