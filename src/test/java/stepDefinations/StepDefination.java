package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	ResponseSpecification respo;
	RequestSpecification req;
	Response response;
	TestDataBuild ts = new TestDataBuild();
	static String place_id; //if you define static on one varaiable all testcases in that particular run will refer to same variable
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name,String language,String address) throws IOException {
		
		// kept the request specifications in separate class and extends step definations to utils class and pass required method name since we will be using it frequently
		//kept the body parameter in seprate calss and calling that method after creating object of that particular class
		 req = given().spec(requestSpecifications()).body(ts.addPlacePayLoad(name,language,address)); 		 
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpmethod) {
		
		// constructor will be called with value of resource which you pass
		APIResources resourceAPI = APIResources.valueOf(resource); 
		//resourceAPI.getResource(); this will get the value of set resource and it will be passed inside httpmethods like post/get/delete
		
		//generalizing the response with common reusable items
		respo = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if (httpmethod.equalsIgnoreCase("POST"))
		  response = req.when().post(resourceAPI.getResource());
		else if (httpmethod.equalsIgnoreCase("GET"))
			response = req.when().get(resourceAPI.getResource());
		
	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String ExpectedValue) {
  	    assertEquals(getJsonPath(response, keyValue),ExpectedValue);	
  	
	}
	@Then("verify place_id created to map {string} using {string}")
	public void verify_place_id_created_to_map_using(String expectedName, String resource) throws IOException {
		place_id = getJsonPath(response,"place_id");//get the place id from addPlaceAPI reponse and pass as query params in next getPlaceAPI
		req = given().spec(requestSpecifications()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource,"GET");// sending values in above method to use getPlaceAPI
		String actualName = getJsonPath(response,"name"); // get the name from getPlaceAPI response and compare with expected name
		assertEquals(actualName,expectedName);
	}
	
	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException {
		req= given().spec(requestSpecifications()).body(ts.deletePlacePayLoad(place_id));
	}


}
