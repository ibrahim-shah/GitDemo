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

	public static RequestSpecification res;// made that as static so that variable is shared across all testcases during that execution
	
	public RequestSpecification requestSpecifications() throws IOException {
			
		if (res==null)
		{	
			
		PrintStream ps = new PrintStream(new FileOutputStream("logging.txt") ); // this is to save logs in file

		// this method is to use all reusable components related to all request in step definations 
		 res = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
				 .addFilter(RequestLoggingFilter.logRequestTo(ps)) // this is used to log request to print stream object
				 .addFilter(ResponseLoggingFilter.logResponseTo(ps)) // this is used to log response to print stream object
				 .setContentType(ContentType.JSON).build();
         return res;
		}
	    return res; // this if and return statement is to avoid logs getting replaced with latest values this will maintain all logged values when multiple test are ran
	}

	public static String getGlobalValue(String key) throws IOException {
		// this method is to set base url or any keys in property file and fetch any keys in property file by call this method with key parameters in property file
		Properties pro = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\Ibrahim\\eclipse-workspace\\APIFramework\\src\\test\\java\\resources\\global.properties");
	    pro.load(fis);
	    return pro.getProperty(key);
	}
	
	
	// this is common method to extract AddPlaceAPI response and use it to another APIs
	public String getJsonPath(Response response, String key) {
		
		String resoo = response.asString();
		JsonPath js = new JsonPath(resoo);
		return js.get(key).toString();
	}
}
