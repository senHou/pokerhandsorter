package sen.pokerhand.exercise.util;

import org.junit.jupiter.api.Test;
import sen.pokerhand.exercise.exception.InvalidHandException;
import sen.pokerhand.exercise.hand.Card;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
}
