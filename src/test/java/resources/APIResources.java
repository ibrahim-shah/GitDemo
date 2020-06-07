package resources;

//enum class is special class in java which has collection of constants or methods
public enum APIResources {
	
    
	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	private String resource;
	
	APIResources(String resource){
		this.resource = resource; // this will set the passed resource to resource declared in this class
	}
	
	public String getResource() {
		return resource; // this will return the resource from class which is set in ablve APIResource constructor
	}
}
