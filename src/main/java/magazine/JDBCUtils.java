package magazine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {

	private static final String dbURL = "jdbc:mysql://localhost:3306/magazine";
	private static final String user = "oleg";
	private static final String password = "oleg";

	private static Connection myConn;

	private JDBCUtils() {
	}

	public static Connection getConnection() {
		if (myConn == null) {
			try {
				myConn = DriverManager.getConnection(dbURL, user, password);
				Statement mSt=myConn.createStatement();
//				 String sql = "CREATE TABLE REGISTRATION" +
//			                   "(client_id INTEGER  NOT NULL AUTO_INCREMENT, " +
//			                   " first VARCHAR(255), " + 
//			                   " last VARCHAR(255), " + 
//			                   " invoice DECIMAL(5,2), " + 
//			                   " PRIMARY KEY ( client_id ))";
//				 String sql1="CREATE TABLE MAGAZINE" +
//		                   "(client_id INTEGER not NULL, " +
//		                   " name VARCHAR(255)not NULL, " + 
//		                   " title VARCHAR(255)not NULL,"+
//		                   " price DECIMAL(5,2)not NULL, " + 
//		                   " PRIMARY KEY ( name ),"+
//		                   "FOREIGN KEY(client_id) REFERENCES REGISTRATION(client_id));";
//				 String sqlLog="CREATE TABLE LOGS"+
//						 		"(client_id INTEGER   NOT NULL,"+
//								 "date   DATE           NOT NULL,"+
//								 "logger  VARCHAR(50)    NOT NULL,"+
//								 "level   VARCHAR(10)    NOT NULL,"+
//								 "messsage VARCHAR(1000)  NOT NULL,"+
//				                  "FOREIGN KEY(client_id) REFERENCES REGISTRATION(client_id));";

//				 mSt.execute(sql);
//				 mSt.execute(sql1);
//		 mSt.execute(sqlLog);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return myConn;
	}

}
