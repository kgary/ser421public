package edu.asupoly.ser422;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.Properties;

public final class Ser422DbUtils {

	public static Connection getConnection() throws Ser422DbWrapperException
	{
		// read info from default properties if needed
		//	return getConnection(jdbcUser, jdbcPasswd, jdbcURL, jdbcDriver);
		String url = "jdbc:postgresql://localhost:5432/patient";
		Properties props = new Properties();
		props.setProperty("user", "ser422");
		props.setProperty("password", "database");
		//props.setProperty("compatible", "7.1");

		return getConnection(url, props, "org.postgresql.Driver");
	}
	public static Connection getConnection(String jdbcUser, String jdbcPasswd,
			String jdbcURL,  String jdbcDriver)
					throws Ser422DbWrapperException
	{
		Properties props = new Properties();
		props.setProperty("user", jdbcUser);
		props.setProperty("password", jdbcPasswd);
		//props.setProperty("compatible", "7.1");

		return getConnection(jdbcURL, props, jdbcDriver);
	}
	public static Connection getConnection(String url, Properties props, String jdbcDriver)
			throws Ser422DbWrapperException
	{
		try {
			Class.forName(jdbcDriver);
			return DriverManager.getConnection(url, props);
		}
		catch (Throwable t) {
			t.printStackTrace();
			throw new Ser422DbWrapperException("Ser422DbUtils::getConnection", t);
		}
	}

	public static void releaseConnection(Connection conn) {
		// presumed fail-safe
		try {
			if (conn != null) {
				conn.close();
			}
		}
		catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public static int printResultSetHeader(ResultSet rs) throws SQLException {
		return printResultSetHeader(rs.getMetaData());
	}

	public static int printResultSetHeader(ResultSetMetaData metaData) throws SQLException
	{
		int numColumns = metaData.getColumnCount();
		for (int i=1; i <= numColumns; i++)
			System.out.print(metaData.getColumnLabel(i) + "\t");
		System.out.println("");
		return numColumns;
	}

	public static void printResultSet(ResultSet rs) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();
		int numColumns = printResultSetHeader(metaData);

		while (rs.next()) {
			printResultSetRow(rs, numColumns);
		}
	}

	public static void printResultSetRow(ResultSet rs) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();
		int numColumns = printResultSetHeader(metaData);

		printResultSetRow(rs, numColumns);
	}

	public static void printResultSetRow(ResultSet rs, int numColumns) throws SQLException {
		Object obj = null;
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

	private Ser422DbUtils() {}
}
