package org.option.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * init Session Factory for DB connection
 * 
 * @author rahulksh
 * 
 */
public class HibernateUtil {

	
	private static SessionFactory sessionFactory;
	/*
	private static String dbUrl="jdbc:mysql://localhost:3306/stocksrin";
	private static String dbUser="root"; 
	private static String  dbPassword="admin";*/
	
	private static String dbUrl = "jdbc:mysql://591ab3d37628e1317300013a-smarttrade.rhcloud.com:45626/stocksrin";
	private static String dbUser = "adminN9me6g3";
	private static String dbPassword = "WMuZ1p-JcMZD";

	public static void init() throws Exception {

		if (sessionFactory == null) {
			sessionFactory = buildSessionFactory();
		}
	}

	private static SessionFactory buildSessionFactory() throws Exception {

		try {
			// String driver = "com.mysql.jdbc.Driver";
			// Class.forName(driver);
			System.out.println(System.getenv("OPENSHIFT_MYSQL_DB_HOST"));
			System.out.println(System.getenv("OPENSHIFT_MYSQL_DB_PORT"));
			System.out.println(System.getenv("OPENSHIFT_MYSQL_DB_USERNAME"));
			System.out.println(System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD"));

			Properties dbConnectionProperties = new Properties();
			try {
				System.out.println("dbUrl :" + dbUrl);
				System.out.println("dbUser :" + dbUser);
				System.out.println("dbPassword :" + dbPassword);
				dbConnectionProperties.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
				dbConnectionProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
				dbConnectionProperties.setProperty("hibernate.connection.url", dbUrl);
				dbConnectionProperties.setProperty("hibernate.connection.username", dbUser);
				dbConnectionProperties.setProperty("hibernate.connection.password", dbPassword);
				//dbConnectionProperties.setProperty("hibernate.listeners.envers.autoRegister", "false");

				Configuration cfg = new Configuration();
				cfg.configure("hibernate.cfg.xml");
				cfg.mergeProperties(dbConnectionProperties);
				
				
				SessionFactory factory = cfg.buildSessionFactory();
				//factory.
				return factory;
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("Can't initialised DB Module " + e.getMessage());
			}

		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new Exception("Can't initialised DB Module " + ex.getMessage());
		}
	}

	public static SessionFactory getSessionFactory() throws Exception {
		if (sessionFactory == null) {
			throw new Exception("DB Module is not initialised! DB details are not configured");
		}
		return sessionFactory;
	}

	private static synchronized void jdbcConnect() {
		Connection conn = null;

		String driver = "com.mysql.jdbc.Driver";

		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			System.out.println("\t\t*** Connected to the database! ***");
			conn.close();
			System.out.println("\t\tDisconnected from database");
		} catch (Exception e) {
			System.out.println("\t\t *** Trouble connecting to database: " + e);
		}
	}

	/*
	 * private static void Jnditest(){ Connection result = null; try { Context
	 * initialContext = new InitialContext(); DataSource datasource =
	 * (DataSource)initialContext.lookup("java:jboss/datasources/MySQLDS");
	 * result = datasource.getConnection(); Statement stmt =
	 * result.createStatement() ;
	 * 
	 * 
	 * DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); Date myDate =
	 * new Date(); java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
	 * 
	 * String sql = "INSERT INTO stocksrin.USDINR " +
	 * "VALUES ("+sqlDate+","+sqlDate+"," + 61+ ","+100+ "," + 200+")";
	 * System.out.println("sql" + sql); stmt.executeUpdate(sql);
	 * 
	 * /// create a sql date object so we can use it in our INSERT statement
	 * Calendar calendar = Calendar.getInstance(); java.sql.Date startDate = new
	 * java.sql.Date(calendar.getTime().getTime());
	 * 
	 * // the mysql insert statement String insertQuery =
	 * " insert into stocksrin.USDINR (Date, Expiry, Strick, CE_OI_Value, PE_OI_Value)"
	 * + " values (?, ?, ?, ?, ?)";
	 * 
	 * // create the mysql insert preparedstatement PreparedStatement
	 * preparedStmt = result.prepareStatement(insertQuery); preparedStmt.setDate
	 * (1, startDate); preparedStmt.setDate (2, startDate);
	 * preparedStmt.setFloat(3, 61); preparedStmt.setInt(4, 100);
	 * preparedStmt.setInt (5, 5000); // execute the preparedstatement
	 * preparedStmt.execute();
	 * 
	 * String query = "select * from stocksrin.USDINR;" ;
	 * 
	 * ResultSet rs = stmt.executeQuery(query) ;
	 * 
	 * while (rs.next()) { System.out.println(rs.getString(1) + " " +
	 * rs.getString(2) + " " + rs.getString(3) + "<br />"); }
	 * 
	 * } catch (Exception ex) { System.out.println("Exception: " + ex +
	 * ex.getMessage()); } }
	 */

}