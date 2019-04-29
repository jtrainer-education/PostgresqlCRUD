package jtrainer.education.api.serverless.postgresql.function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class CreateProduct {

	public Integer createProduct(Context context, String name, Integer product_type_id, String ean) {
		LambdaLogger logger = context.getLogger();
		Connection conn=PostGresqlConnection.getConnection();
		try {
			Statement stmt = conn.createStatement();
			String query="INSERT INTO public.products(name, product_type_id, ean) VALUES ('"+name+"', '"+product_type_id+"', '"+ean+"');";
			logger.log("["+query+"]");
			ResultSet resultSet = stmt.executeQuery(query);
			int newId = resultSet.getInt(1);
			conn.close();
			return newId;
		} catch (Exception e) {
			e.printStackTrace();
			logger.log("Caught exception: " + e.getMessage());
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return 0;
	}
}