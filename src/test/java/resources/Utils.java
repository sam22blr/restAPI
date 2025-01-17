package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification req;

	public RequestSpecification requestSpectBuilder() throws IOException {
		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("log.txt"));
			req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
					.setContentType(ContentType.JSON).addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
			return req;
		}
		return req;
	}

	public RequestSpecification requestSpectBuilderGraphQL() throws IOException {
		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("log.txt"));
			req = new RequestSpecBuilder().setBaseUri(getGlobalValue("graphQLUrl"))
					.addHeader("Content-Type", "application/json").setContentType(ContentType.JSON)
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
			return req;
		}
		return req;
	}

	public String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/java/resources/global.properties");
		prop.load(fis);

		return prop.getProperty(key);
	}

	public String getJsonPathValue(Response response, String key) {
		String resp = response.asString();
		JsonPath json = new JsonPath(resp);

		return json.get(key).toString();
	}
}
