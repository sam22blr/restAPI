package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@deletePlace")
	public void beforeScenario() throws IOException {
		
		PlaceValidationSteps steps = new PlaceValidationSteps();
		
		if (PlaceValidationSteps.place_id == null) {
			steps.user_builds_an_add_place_payload("ANY", "ANY", "ANY");
			steps.user_posts_http_request_to("post", "addPlaceAPI");
			steps.user_verifies_using("ANY", "getPlaceAPI");
		}
	}

}
