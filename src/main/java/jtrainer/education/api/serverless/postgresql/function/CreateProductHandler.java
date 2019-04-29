package jtrainer.education.api.serverless.postgresql.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

public class CreateProductHandler implements RequestStreamHandler  {

	@Override
	public void handleRequest(InputStream inputStream,  OutputStream outputStream, Context context) throws IOException {

		LambdaLogger logger = context.getLogger();

		JSONParser parser = new JSONParser();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		JSONObject responseJson = new JSONObject();

		try {
			JSONObject event= (JSONObject)parser.parse(reader);
			logger.log("event:"+event.toJSONString());

			if (event.get("body") != null) {
				logger.log("body:"+event.get("body"));

				Product product = new Product((String) event.get("body"));
				new CreateProduct().createProduct(context, product.getName(),product.getProduct_type(),product.getEan());
			}

			JSONObject responseBody = new JSONObject();
			responseBody.put("message", "New product created");

			JSONObject headerJson = new JSONObject();
			headerJson.put("x-custom-header", "my custom header value");

			responseJson.put("statusCode", 200);
			responseJson.put("headers", headerJson);
			responseJson.put("body", responseBody.toString());

		} catch (org.json.simple.parser.ParseException  pex) {
			responseJson.put("statusCode", 400);
			responseJson.put("exception", pex);
		}
		OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
		writer.write(responseJson.toString());
		writer.close();
	}
}