import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
public class SerialiazeCode {

	public static void main(String[] args){
	
		RequestSpecification req =  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		AddPlacePOJO addPlace = new AddPlacePOJO();
		location loc = new location();
		loc.setLat("-38.383494");
		loc.setLng("33.427362");
		addPlace.setLocation(loc);
		addPlace.setAccuracy(50);
		addPlace.setName("Frontline house");
		addPlace.setAddress("29, side layout, cohen 09");
		addPlace.setPhone_number("(+91) 983 893 3937");
		ArrayList<String> typeList = new ArrayList<String>();
		typeList.add("shoe park");
		typeList.add("shop");
		addPlace.setTypes(typeList);
		addPlace.setWebsite("http://google.com");
		addPlace.setLanguage("French-IN");
		Response  res1 = given()
		.spec(req)
		.body(addPlace)
		.when()
		.post("/maps/api/place/add/json")
		.then()
		.spec(res)
		.extract().response();
		String placeId= res1.getBody().jsonPath().get("place_id");
		System.out.println(placeId);
	}
}
