package sen.pokerhand.exercise.util;

import org.junit.jupiter.api.Test;
import sen.pokerhand.exercise.exception.InvalidHandException;
import sen.pokerhand.exercise.hand.Card;
import sen.pokerhand.exercise.hand.PokerHand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static sen.pokerhand.exercise.constant.PokerHandTypeConstant.PLAYER_ONE;
import static sen.pokerhand.exercise.constant.PokerHandTypeConstant.PLAYER_TWO;

public class PokerHandParserTest {

    @Test
    public void testGenerateCardListNullDataThrowException() {
        Exception exception = assertThrows(InvalidHandException.class, () -> {
            PokerHandParser.generateCardList(null);
        });

        String expectedMessage = "Invalid hand exception";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGenerateCardListLessDataThrowException() {

        // not enough element, must be 5
        String[] data = new String[2];
        data[0] = "2H";
        data[1] = "3H";

        Exception exception = assertThrows(InvalidHandException.class, () -> {
            PokerHandParser.generateCardList(data);
        });

        String expectedMessage = "Invalid hand exception";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGenerateCardListMoreDataThrowException() {

        // too many element, must be 5
        String[] data = new String[6];
        data[0] = "2H";
        data[1] = "3H";
        data[2] = "8H";
        data[3] = "6H";
        data[4] = "7C";
        data[5] = "8D";

        Exception exception = assertThrows(InvalidHandException.class, () -> {
            PokerHandParser.generateCardList(data);
        });

        String expectedMessage = "Invalid hand exception";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGenerateCardListShortDataThrowException() {
        String[] data = new String[5];
        data[0] = "2H";
        data[1] = "3H";
        data[2] = "8H";
        data[3] = "6H";
        data[4] = "7"; // invalid need to throw exception

        Exception exception = assertThrows(InvalidHandException.class, () -> {
            PokerHandParser.generateCardList(data);
        });

        String expectedMessage = "Invalid hand exception";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGenerateCardListLongDataThrowException() {
        String[] data = new String[5];
        data[0] = "2H";
        data[1] = "3H";
        data[2] = "8H";
        data[3] = "6H";
        data[4] = "7CC"; // invalid need to throw exception

        Exception exception = assertThrows(InvalidHandException.class, () -> {
            PokerHandParser.generateCardList(data);
        });

        String expectedMessage = "Invalid hand exception";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGenerateCardList(){
        String[] data = new String[5];
        data[0] = "2H";
        data[1] = "3H";
        data[2] = "8H";
        data[3] = "6H";
        data[4] = "7C";

        List<Card> cardList =  PokerHandParser.generateCardList(data);
        assertEquals(5, cardList.size());

        assertEquals(new Card(2, "H"), cardList.get(0));
        assertEquals(new Card(3, "H"), cardList.get(1));
        assertEquals(new Card(8, "H"), cardList.get(2));
        assertEquals(new Card(6, "H"), cardList.get(3));
        assertEquals(new Card(7, "C"), cardList.get(4));
    }

    @Test
    public void testGenerateCardListCharValue(){
        String[] data = new String[5];
        data[0] = "TH";
        data[1] = "JH";
        data[2] = "QH";
        data[3] = "KH";
        data[4] = "AC";

        List<Card> cardList =  PokerHandParser.generateCardList(data);
        assertEquals(5, cardList.size());

        assertEquals(new Card(10, "H"), cardList.get(0));
        assertEquals(new Card(11, "H"), cardList.get(1));
        assertEquals(new Card(12, "H"), cardList.get(2));
        assertEquals(new Card(13, "H"), cardList.get(3));
        assertEquals(new Card(14, "C"), cardList.get(4));
    }

    @Test
    public void testGeneratePokerHandsMissingOneCard() {
        Exception exception = assertThrows(InvalidHandException.class, () -> {
            List<String> data = List.of("2H 9S 9C JD KH TD 4H JC 9H ");
            PokerHandParser.generatePokerHands(data);
        });

        String expectedMessage = "Invalid hand exception";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGeneratePokerHandsTooManyCards() {
        Exception exception = assertThrows(InvalidHandException.class, () -> {
            List<String> data = List.of("2H 9S 9C JD KH TD 4H JC 9H TD 2D");
            PokerHandParser.generatePokerHands(data);
        });

        String expectedMessage = "Invalid hand exception";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGeneratePokerHandsInvalidPattern() {
        Exception exception = assertThrows(InvalidHandException.class, () -> {
            List<String> data = List.of("2H 9S 9C JD KH TD 4H JC 9HTD2D");
            PokerHandParser.generatePokerHands(data);
        });

        String expectedMessage = "Invalid hand exception";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGeneratePokerHandsNull() {
        Exception exception = assertThrows(InvalidHandException.class, () -> {
            PokerHandParser.generatePokerHands(null);
        });

        String expectedMessage = "Invalid hand exception";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGeneratePokerHandsEmptyData() {
        Exception exception = assertThrows(InvalidHandException.class, () -> {
            PokerHandParser.generatePokerHands(new ArrayList<>());
        });

        String expectedMessage = "Invalid hand exception";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGeneratePokerHands() throws Exception {
        List<String> data = List.of("2H 9S 9C JD KH TD 4H JC 9H 8H", "3H 9C 4D QD 7S 5D 7H 4H 5S QH");
        Map<String, List<PokerHand>> pokerHandMap = PokerHandParser.generatePokerHands(data);

        List<PokerHand> playerOne = pokerHandMap.get(PLAYER_ONE);
        List<PokerHand> playerTwo = pokerHandMap.get(PLAYER_TWO);

        List<Card> playerOneCardList = List.of(
                new Card(2, "H"),
                new Card(9, "S"),
                new Card(9, "C"),
                new Card(11, "D"),
                new Card(13, "H"),
                new Card(3, "H"),
                new Card(9, "C"),
                new Card(4, "D"),
                new Card(12, "D"),
                new Card(7, "S")
        );

        List<Card> playerTwoCardList = List.of(
                new Card(10, "D"),
                new Card(4, "H"),
                new Card(11, "C"),
                new Card(9, "H"),
                new Card(8, "H"),
                new Card(5, "D"),
                new Card(7, "H"),
                new Card(4, "H"),
                new Card(5, "S"),
                new Card(12, "H")
        );

        for (int i = 0; i < playerOne.size(); i ++) {
            PokerHand pokerHand = playerOne.get(i);
            for (int j = 0; j < pokerHand.getCardList().size(); j ++) {
                Card card = pokerHand.getCardList().get(j);
                Card expected = playerOneCardList.get(i * pokerHand.getCardList().size() + j);
                assertEquals(expected.getValue(), card.getValue());
                assertEquals(expected.getSuite(), card.getSuite());
            }
        }

        for (int i = 0; i < playerTwo.size(); i ++) {
            PokerHand pokerHand = playerTwo.get(i);
            for (int j = 0; j < pokerHand.getCardList().size(); j ++) {
                Card card = pokerHand.getCardList().get(j);
                Card expected = playerTwoCardList.get(i * pokerHand.getCardList().size() + j);
                assertEquals(expected.getValue(), card.getValue());
                assertEquals(expected.getSuite(), card.getSuite());
            }
        }

    }
}
