package edu.asupoly.ser422.jdbcex;

import java.sql.*;

/*
This sample program connects to the database and queries metadata about the Connection.
*/
public class JDBCConnectionMetadata
{
    public static void main(String[] args)
    {
	if (args.length != 4)
	    {
		System.out.println("USAGE: java edu.asu.ser422.JDBCConnectionMetadata <driver><url><username><passwd>");
		System.exit(0);
	    }

	Connection conn = null;

	try {
	    // Step 1: Load the JDBC driver
	    // Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	    // Class.forName("oracle.jdbc.driver.OracleDriver");
	    // Class.forName("org.postgresql.Driver");
	    Class.forName(args[0]);

	    // Step 2: make a connection
	    conn = DriverManager.getConnection(args[1], args[2], args[3]);

	    switch (conn.getTransactionIsolation()) {
	    case Connection.TRANSACTION_NONE:
		System.out.println("Transaction Isolation level is NONE");
		break;
	    case Connection.TRANSACTION_READ_COMMITTED:
		System.out.println("Transaction Isolation level is READ_COMMITTED");
		break;
	    case Connection.TRANSACTION_READ_UNCOMMITTED:
		System.out.println("Transaction Isolation level is READ_UNCOMMITTED");
		break;
	    case Connection.TRANSACTION_REPEATABLE_READ:
		System.out.println("Transaction Isolation level is READ_COMMITTED");
		break;
	    case Connection.TRANSACTION_SERIALIZABLE:
		System.out.println("Transaction Isolation level is READ_COMMITTED");
		break;
	    default:
		System.out.println("Unknown Transaction Isolation Level");
	    }

	    System.out.println("AutoCommit on: " + conn.getAutoCommit());

	    if (conn.getHoldability() == ResultSet.HOLD_CURSORS_OVER_COMMIT) {
		System.out.println("Holdability is HOLD_CURSORS_OVER_COMMIT");
	    }
	    else if (conn.getHoldability() == ResultSet.CLOSE_CURSORS_AT_COMMIT) {
		System.out.println("Holdability is CLOSE_CURSORS_AT_COMMIT");
	    }
	    else {
		System.out.println("Unknown Holdability");
	    }
	}
	catch (Exception exc)
	    {
		exc.printStackTrace();
	    }
	finally {
	    // Step 7: Close the DB resources
	    try {
		if (conn != null) { conn.close(); }
	    }
	    catch (Exception exc) {
		System.out.println("Exception cleaning up DB resources!");
		exc.printStackTrace();
	    }
	    System.exit(0);
	}
    }
}

