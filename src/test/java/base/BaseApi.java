package base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import config.Config;

public class BaseApi {
	protected static final String BASE_URL = "https://api.trello.com/1";

	protected RequestSpecification getRequestSpec() {
		return RestAssured.given().baseUri(BASE_URL).queryParam("key", Config.KEY).queryParam("token", Config.TOKEN)
				.header("Content-Type", "application/json");
	}
}
