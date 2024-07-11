package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestData {
	
	public AddPlace addPlace(String name, String language, String address) {
		AddPlace addPlace = new AddPlace();
		addPlace.setAccuracy(50);
		addPlace.setAddress(address);
		addPlace.setLanguage(language);
		addPlace.setPhone_number("123 123 1234");
		addPlace.setWebsite("https://rahulshettyacademy.com/client/dashboard/dash");
		addPlace.setName(name);

		List<String> list = new ArrayList<String>();
		list.add("Shoe Park");
		list.add("Shop");
		addPlace.setTypes(list);

		Location loc = new Location();
		loc.setLat(-38.38);
		loc.setLng(33.42);
		addPlace.setLocation(loc);
		
		return addPlace;
	}
	
	public String deletePlace(String place_id) {
		return "{\r\n \"place_id\":\"" + place_id + "\"\r\n}";
	}

}
