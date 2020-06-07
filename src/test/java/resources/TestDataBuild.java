package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.location;

public class TestDataBuild {

	public AddPlace addPlacePayLoad(String name,String language,String address) {
		
		// this method is to have all request payload related things and passing this object in stepdefinations

		
		//setting request body values below
		 AddPlace place = new AddPlace();
		 place.setAccuracy(50);
		 place.setName(name);
		 place.setPhone_number("(+91) 983 893 3937");
		 place.setAddress(address);
		 place.setWebsite("http://google.com");
		 place.setLanguage(language);
		 location loc = new location();
		 loc.setLat(-38.383494);
		 loc.setLng(33.427362);
		 place.setLocation(loc); // location s seprate class so we need to set values to that class variables	 
		 List<String> typeList = new ArrayList<String>();
		 typeList.add("shoe park");
		 typeList.add("shop");
		 place.setTypes(typeList); // Type is list so we need to create a new list and pass values to list 
		 return place;
	}
	
	public String deletePlacePayLoad(String placeId) {
	
		return "{\r\n" + 
				"  \"place_id\": \""+placeId+"\"\r\n" + 
				"}";
	}
}
