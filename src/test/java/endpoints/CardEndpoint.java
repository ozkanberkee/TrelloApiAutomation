package endpoints;

import base.BaseApi;
import config.Config;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CardEndpoint extends BaseApi {
	public Response createCard(String listId, String name, String desc) {
		return getRequestSpec().queryParam("idList", listId).queryParam("name", name).queryParam("desc", desc)
				.post("/cards");
	}

	public Response getCardById(String cardId) {
		return RestAssured.given().queryParam("key", Config.KEY).queryParam("token", Config.TOKEN).when()
				.get("https://api.trello.com/1/cards/" + cardId).then().extract().response();
	}

	public Response updateCard(String cardId, String newName, String newDesc) {
		return getRequestSpec().queryParam("name", newName).queryParam("desc", newDesc).put("/cards/" + cardId);
	}

	public Response deleteCard(String cardId) {
		return getRequestSpec().delete("/cards/" + cardId);
	}

}
