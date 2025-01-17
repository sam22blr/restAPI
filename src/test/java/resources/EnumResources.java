package resources;

public enum EnumResources {

	addPlaceAPI("maps/api/place/add/json"), getPlaceAPI("maps/api/place/get/json"),
	deletePlaceAPI("maps/api/place/delete/json");

	private String resource;

	EnumResources(String resource) {
		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}
}
