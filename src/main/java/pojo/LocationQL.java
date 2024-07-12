package pojo;

public class LocationQL {
	private int locationId;
	private int id;
	private String name;
	private String dimension;
	private String type;

	public LocationQL() {

	}

	public LocationQL(int locationId) {
		this.locationId = locationId;

	}

	public LocationQL(String name, String type, String dimension) {
		this.name = name;
		this.dimension = dimension;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDimension() {
		return name;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String buildLocationQuery() {
		return "{ location(locationId: " + locationId + ") { id name } }";
	}

	public String executeLocationQuery() {
		String query = buildLocationQuery();
		return "{\"query\": \"" + query + "\"}";

	}

	public String buildQueryMutation() {
		return "{\"query\":\"mutation {createLocation(location: {name: \\\"" + name + "\\\", type: \\\"" + type
				+ "\\\", dimension: \\\"" + dimension + "\\\"}) {\\n    id\\n  }\\n}\\n\",\"variables\":null}";

		// {"query":"mutation {\n createLocation(location: {name: \"Hello\", type:
		// \"World\", dimension: \"!\"}) {\n id\n }\n}\n","variables":null}

	}
}
