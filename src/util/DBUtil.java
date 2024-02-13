package util;

import java.sql.Connection;
import java.sql.SQLException;
//import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * The {@link DBUtil} class is responsible for
 * establishing a connection to the database.
 */
public class DBUtil {
	
	private static BasicDataSource ds = new BasicDataSource();
    private static Connection conn;
    private final static DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
	/**
	 * No instances of this class should be available
	 */
	private DBUtil(){ }

    static {
        ds.setUrl("jdbc:mysql://localhost:somePort/courseDB?serverTimezone=UTC");
        ds.setUsername("randomName");
        ds.setPassword("randomPass");
        ds.setInitialSize(8);
        ds.setMaxTotal(32);
        ds.setMinIdle(8);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }
    
    public static Connection getConnection() throws SQLException {
        conn = ds.getConnection();
//        LocalDateTime currentTime = java.time.LocalDateTime.now();
//        System.out.println(currentTime.format(CUSTOM_FORMATTER) + ": Connection opened");
        return conn;
    }
    
    public static void closeConnection() {
    	try {
			if (conn != null) {
				conn.close();
//		        LocalDateTime currentTime = java.time.LocalDateTime.now();
//		        System.out.println(currentTime.format(CUSTOM_FORMATTER) + ": Connection closed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public static DateTimeFormatter getCustomFormatter() {
    	return CUSTOM_FORMATTER;
    }
}
