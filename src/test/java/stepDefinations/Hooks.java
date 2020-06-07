package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace") // this will run before deletePlace scenario with dependent on AddPlace scenario when deletePlace scenario  is called in tags 
	public void beforeScenario() throws IOException {
		 
		StepDefination sd = new StepDefination();
		//since place_id is static we are calling static variable with class name stepDefination.place id	
		if(StepDefination.place_id == null) // if place id is null only below code will be executed
		{ 
		sd.add_Place_Payload_with("ibrahim", "Arabic", "Dubai");
		sd.user_calls_with_http_request("AddPlaceAPI", "POST");
		sd.verify_place_id_created_to_map_using("ibrahim", "getPlaceAPI");
		}
		
	}

}
