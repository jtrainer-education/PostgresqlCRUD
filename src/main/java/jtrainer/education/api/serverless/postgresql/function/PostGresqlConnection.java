package jtrainer.education.api.serverless.postgresql.function;

import java.sql.Connection;
import java.sql.DriverManager;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class PostGresqlConnection {
	
		static Connection getConnection(){

		Connection conn=null;
	    try {
	      String url = "jdbc:postgresql://play2devdb.coglzk7gjdwx.us-east-1.rds.amazonaws.com:5432/Play2Dev1";
	      String username = "root";
	      String password = "London01";

	      conn = DriverManager.getConnection(url, username, password);
	      return conn;
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return conn;
	}
}
