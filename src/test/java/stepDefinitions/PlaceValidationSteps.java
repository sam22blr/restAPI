package stepDefinitions;

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
import resources.EnumResources;
import resources.TestData;
import resources.Utils;

public class PlaceValidationSteps extends Utils {
	RequestSpecification reqSpec;
	ResponseSpecification respSpec;
	Response response;
	TestData testData = new TestData();
	public static String place_id;

	@Given("User builds an add place payload with {string}, {string}, {string}")
	public void user_builds_an_add_place_payload(String name, String language, String address) throws IOException {

		reqSpec = given().spec(requestSpectBuilder()).body(testData.addPlace(name, language, address));

	}

	@When("User {string} http request to {string}")
	public void user_posts_http_request_to(String method, String resource) {
		respSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (method.equalsIgnoreCase("get"))
			response = reqSpec.when().get(EnumResources.valueOf(resource).getResource());
		else if (method.equalsIgnoreCase("post"))
			response = reqSpec.when().post(EnumResources.valueOf(resource).getResource());

		System.out.println(response);
	}

	@Then("User gets a http success code as {int}")
	public void user_gets_a_http_success_code_as(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("User gets a {string} in response body as {string}")
	public void user_gets_a_in_response_body_as(String statusKey, String expectedValue) {

		assertEquals(getJsonPathValue(response, statusKey), expectedValue);
	}
	 

	@Then("User verifies {string} using {string}")
	public void user_verifies_using(String name, String method) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		place_id = getJsonPathValue(response, "place_id");
		reqSpec = given().spec(requestSpectBuilder()).queryParam("place_id", place_id);
		user_posts_http_request_to("GET", method);
		System.out.println(getJsonPathValue(response, "name"));
		//assertEquals(getJsonPathValue(response, "name"), name);
	}
	
	@Given("User builds delete payload")
	public void delete_place() throws IOException {
		reqSpec = given().spec(requestSpectBuilder()).body(testData.deletePlace(place_id));
	}

  	
  	@Given("User builds location payload")
	public void user_builds_location_payload() throws IOException {
		reqSpec = given().spec(requestSpectBuilderGraphQL()).body("{\"query\":\"{\\n  location(locationId: 11420) {\\n    id\\n  }\\n}\\n\",\"variables\":null}");
	}
	
	@Given("User posts the location request")
	public void user_posts_location_request() throws IOException {
		respSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		response = reqSpec.when().post("gq/graphql");
		
		System.out.println(getJsonPathValue(response, "data.location.id"));
	}
}
