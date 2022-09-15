package edu.asupoly.ser421.booktown.services.impl;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import edu.asupoly.ser421.booktown.model.Author;
import edu.asupoly.ser421.booktown.services.BooktownService;

import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

//A simple impl of interface BooktownService
public class RDBMBooktownServiceImpl implements BooktownService {
	private static Properties __dbProperties;
	private static String __jdbcUrl;
	private static String __jdbcUser;
	private static String __jdbcPasswd;
	private static String __jdbcDriver;

	// Only instantiated by factory within package scope
	public RDBMBooktownServiceImpl() {
	}

	public List<Author> getAuthors() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Author> rval = new ArrayList<Author>();
		try {
			try {
				Class.forName(__jdbcDriver);
			}
			catch (Throwable t) {
				t.printStackTrace();
				return null;
			}

			conn = DriverManager.getConnection(__jdbcUrl, __jdbcUser, __jdbcPasswd);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(__dbProperties.getProperty("sql.getAuthors"));
			while (rs.next()) {
				rval.add(new Author(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
		}
		catch (SQLException se) {
			se.printStackTrace();
			return null;
		}
		finally {  // why nest all of these try/finally blocks?
			try {
				if (rs != null) { rs.close(); }
			} catch (Exception e1) { e1.printStackTrace(); }
			finally {
				try {
					if (stmt != null) { stmt.close(); }
				} catch (Exception e2) { e2.printStackTrace(); }
				finally {
					try {
						if (conn != null) { conn.close(); }
					} catch (Exception e3) { e3.printStackTrace(); }
				}
			}
		}

		return rval;
	}

	public int createAuthor(String lname, String fname) {
		if (lname == null || fname == null || lname.length() == 0 || fname.length() == 0) {
			return -1;
		}

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = ThreadLocalRandom.current().nextInt(0, 64000);
		
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			try {
				Class.forName(__jdbcDriver);
			}
			catch (Throwable t) {
				t.printStackTrace();
				return -1;
			}
			conn = DriverManager.getConnection(__jdbcUrl, __jdbcUser, __jdbcPasswd);
			stmt = conn.prepareStatement(__dbProperties.getProperty("sql.createAuthor"));
			stmt.setInt(1, randomNum);  // chances of a key clash are low for a simple example
			stmt.setString(2, lname);
			stmt.setString(3, fname);
			return stmt.executeUpdate();
		} catch (SQLException sqe) {
			sqe.printStackTrace();
			return -1;
		} finally {  // why nest all of these try/finally blocks?
			try {
					if (stmt != null) { stmt.close(); }
			} catch (Exception e2) { e2.printStackTrace(); }
			finally {
				try {
					if (conn != null) { conn.close(); }
				} catch (Exception e3) { e3.printStackTrace(); }
			}
		}
	}

	public boolean deleteAuthor(int authorId) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			try {
				Class.forName(__jdbcDriver);
			}
			catch (Throwable t) {
				t.printStackTrace();
				return false;
			}
			conn = DriverManager.getConnection(__jdbcUrl, __jdbcUser, __jdbcPasswd);
			stmt = conn.prepareStatement(__dbProperties.getProperty("sql.deleteAuthor"));
			stmt.setInt(1, authorId);
			stmt.executeUpdate();
			return true;
		} catch (SQLException sqe) {
			sqe.printStackTrace();
			return false;
		} finally {  // why nest all of these try/finally blocks?
			try {
					if (stmt != null) { stmt.close(); }
			} catch (Exception e2) { e2.printStackTrace(); }
			finally {
				try {
					if (conn != null) { conn.close(); }
				} catch (Exception e3) { e3.printStackTrace(); }
			}
		}
	}

	
	// This class is going to look for a file named rdbm.properties in the classpath
	// to get its initial settings
	static {
		try {
			__dbProperties = new Properties();
			__dbProperties.load(RDBMBooktownServiceImpl.class.getClassLoader().getResourceAsStream("rdbm.properties"));
			__jdbcUrl    = __dbProperties.getProperty("jdbcUrl");
			__jdbcUser   = __dbProperties.getProperty("jdbcUser");
			__jdbcPasswd = __dbProperties.getProperty("jdbcPasswd");
			__jdbcDriver = __dbProperties.getProperty("jdbcDriver");
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
		}
	}

}
