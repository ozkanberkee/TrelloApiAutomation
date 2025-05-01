package tests;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import endpoints.BoardEndpoint;
import endpoints.CardEndpoint;
import endpoints.ListEndpoint;
import io.restassured.response.Response;

public class TrelloTest {

    @Test
    public void createBoardAndUpdateRandomCard() {
        BoardEndpoint boardEndpoint = new BoardEndpoint();
        ListEndpoint listEndpoint = new ListEndpoint();
        CardEndpoint cardEndpoint = new CardEndpoint();

        Response boardRes = boardEndpoint.createBoard("Test Board");
        String boardId = boardRes.jsonPath().getString("id");

        Response listRes = listEndpoint.createList(boardId, "Yapılacaklar");
        String listId = listRes.jsonPath().getString("id");

        Response card1Res = cardEndpoint.createCard(listId, "Kart 1", "Açıklama 1");
        String cardId1 = card1Res.jsonPath().getString("id");

        Response card2Res = cardEndpoint.createCard(listId, "Kart 2", "Açıklama 2");
        String cardId2 = card2Res.jsonPath().getString("id");

        List<String> cardIds = Arrays.asList(cardId1, cardId2);
        String selectedCardId = cardIds.get(new Random().nextInt(cardIds.size()));

        Response updateRes = cardEndpoint.updateCard(selectedCardId, "Güncellenmiş Kart", "Yeni açıklama");
        Assert.assertTrue(
        	    updateRes.getBody().asString().contains("Güncellenmiş Kart") &&
        	    updateRes.getBody().asString().contains("Yeni açıklama")
        	);

        Response delCard1 = cardEndpoint.deleteCard(cardId1);
        Assert.assertEquals(200, delCard1.statusCode());

        Response delCard2 = cardEndpoint.deleteCard(cardId2);
        Assert.assertEquals(200, delCard2.statusCode());
     
        Response delBoard = boardEndpoint.deleteBoard(boardId);
        Assert.assertEquals(200, delBoard.statusCode());
    }
}
