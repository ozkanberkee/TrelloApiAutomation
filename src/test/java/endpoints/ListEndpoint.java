package endpoints;

import base.BaseApi;
import io.restassured.response.Response;

public class ListEndpoint extends BaseApi {
    public Response createList(String boardId, String listName) {
        return getRequestSpec()
                .queryParam("name", listName)
                .queryParam("idBoard", boardId)
                .post("/lists");
    }
}
