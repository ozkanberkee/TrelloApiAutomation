package endpoints;

import base.BaseApi;
import io.restassured.response.Response;

public class BoardEndpoint extends BaseApi {
	public Response createBoard(String name) {
		return getRequestSpec().queryParam("name", name).post("/boards");
	}

	public Response deleteBoard(String boardId) {
		return getRequestSpec().delete("/boards/" + boardId);
	}

}
