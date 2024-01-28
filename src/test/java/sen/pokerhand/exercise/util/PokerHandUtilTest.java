package sen.pokerhand.exercise.util;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import sen.pokerhand.exercise.hand.Card;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class PokerHandUtilTest {

    @ParameterizedTest
    @CsvSource({"TC JC QC KC AC, true", "2C JC QC KC AC, false", ", false"})
    public void testIsRoyalFlush(String data, boolean expected) {
        List<Card> list;

        if (data == null) {
            list = null;
        } else {
            list = PokerHandParser.generateCardList(data.split(" "));
        }
        assertEquals(expected, PokerHandUtil.isRoyalFlush(list));
    }

    @ParameterizedTest
    @CsvSource({
            "TC JC QC KC AC, false",
            "9C TC JC QC KC, true",
            ", false",
            "8H 7H 6H 4H 5H, true",
            "3C 7C 6H 4D 5H, false"})
    public void testIsStraightFlush(String data, boolean expected) {
        List<Card> list;

        if (data == null) {
            list = null;
        } else {
            list = PokerHandParser.generateCardList(data.split(" "));
        }
        assertEquals(expected, PokerHandUtil.isStraightFlush(list));
    }

    @ParameterizedTest
    @CsvSource({
            "TC TH TS TD AC, true",
            "9C TC JC QC KC, false",
            ", false"})
    public void testIsFourOfKind(String data, boolean expected) {
        List<Card> list;

        if (data == null) {
            list = null;
        } else {
            list = PokerHandParser.generateCardList(data.split(" "));
        }
        assertEquals(expected, PokerHandUtil.isFourOfKind(list));
    }

    @ParameterizedTest
    @CsvSource({
            "TC TH TS AD AC, true",
            "9C TC JC QC KC, false",
            ", false"})
    public void testIsFullHouse(String data, boolean expected) {
        List<Card> list;

        if (data == null) {
            list = null;
        } else {
            list = PokerHandParser.generateCardList(data.split(" "));
        }
        assertEquals(expected, PokerHandUtil.isFullHouse(list));
    }

    @ParameterizedTest
    @CsvSource({
            "TC 7C 2C 9C TC, true",
            "9C TC JC 2D 2H, false",
            "2C 3C 4C 5C 6C, true",
            ", false"})
    public void testIsFlush(String data, boolean expected) {
        List<Card> list;

        if (data == null) {
            list = null;
        } else {
            list = PokerHandParser.generateCardList(data.split(" "));
        }
        assertEquals(expected, PokerHandUtil.isFlush(list));
    }

    @ParameterizedTest
    @CsvSource({
            "2C 3C 4C 5C 6C, true",
            "9C TC JC 2D 2H, false",
            "2C 3C 4D 5C 6H, true",
            ", false"})
    public void testIsStraight(String data, boolean expected) {
        List<Card> list;

        if (data == null) {
            list = null;
        } else {
            list = PokerHandParser.generateCardList(data.split(" "));
        }
        assertEquals(expected, PokerHandUtil.isStraight(list));
    }

    @ParameterizedTest
    @CsvSource({
            "2C 2S 2D 5C 5D, false", // full house
            "9C TC JC 2D 2H, false",
            "2C 2H 2D 5C 6H, true",
            ", false"})
    public void testIsThreeOfKind(String data, boolean expected) {
        List<Card> list;

        if (data == null) {
            list = null;
        } else {
            list = PokerHandParser.generateCardList(data.split(" "));
        }
        assertEquals(expected, PokerHandUtil.isThreeOfKind(list));
    }


    @ParameterizedTest
    @CsvSource({
            "2C 2S 3D 3C 5D, true",
            "9C TC JC 2D 2H, false",
            "2C 2H 2D 5C 5H, false",
            ", false"})
    public void testIsTwoPairs(String data, boolean expected) {
        List<Card> list;

        if (data == null) {
            list = null;
        } else {
            list = PokerHandParser.generateCardList(data.split(" "));
        }
        assertEquals(expected, PokerHandUtil.isTwoPairs(list));
    }

    @ParameterizedTest
    @CsvSource({
            "2C 2S 4D 3C 5D, true",
            "9C 9D JC 2D 2H, false",
            "2C 2H 2D 5C 5H, false",
            ", false"})
    public void testIsPairs(String data, boolean expected) {
        List<Card> list;

        if (data == null) {
            list = null;
        } else {
            list = PokerHandParser.generateCardList(data.split(" "));
        }
        assertEquals(expected, PokerHandUtil.isPair(list));
    }

    @ParameterizedTest
    @CsvSource({
            "2C 2S 4D 3C 5D, 1",
            "9C 9D JC 2D 2H, 2",
            "2C 2H 2D 5C 5H, 1",
            "2C 3H 6D 5C 9H, 0",
            ", 0"})
    public void testFindNumberOfPairs(String data, int expected) {
        List<Card> list;

        if (data == null) {
            list = null;
        } else {
            list = PokerHandParser.generateCardList(data.split(" "));
        }
        assertEquals(expected, PokerHandUtil.findNumberOfPairs(list));
    }


    @ParameterizedTest
    @CsvSource({
            "2C 2S 4D 3C 5D, 2, 2",
            "9C 9D 9S 2D 2H, 3, 9",
            "2C 2H 2D 2S 5H, 4, 2",
            "2C 2H 2D 2S 5H, 5, 0"})
    public void testGetKind(String data, int type, int expected) {
        List<Card> list;

        if (data == null) {
            list = null;
        } else {
            list = PokerHandParser.generateCardList(data.split(" "));
        }
        assertEquals(expected, PokerHandUtil.getTheSameKind(list, type));
    }

    @Test
    public void testGetTwoPairs() {
        String str1 = "2C 2S 3D 3C 5D";
        List<Card> cardList1 = PokerHandParser.generateCardList(str1.split(" "));
        List<Integer> expected1 = List.of(2, 3);
        assertArrayEquals(expected1.toArray(), PokerHandUtil.getTwoPairs(cardList1).toArray());


        String str2 = "6C 6S 3D 3C 5D";
        List<Card> cardList2 = PokerHandParser.generateCardList(str2.split(" "));
        List<Integer> expected2 = List.of(3, 6);
        assertArrayEquals(expected2.toArray(), PokerHandUtil.getTwoPairs(cardList2).toArray());

        String str3 = "2C 2S 4D 3C 5D";
        List<Card> cardList3 = PokerHandParser.generateCardList(str3.split(" "));
        assertNull(PokerHandUtil.getTwoPairs(cardList3));
    }

    @Test
    public void testIsNull() {
        assertTrue(PokerHandUtil.isNull(null));

        assertTrue(PokerHandUtil.isNull(List.of()));

        String str = "2C 2S 4D 3C 5D";
        List<Card> cardList3 = PokerHandParser.generateCardList(str.split(" "));
        assertFalse(PokerHandUtil.isNull(cardList3));
    }

    @Test
    public void testGetCardMatrix() {
        String str = "2C 2S 4D 3C 5D";
        List<Card> cardList = PokerHandParser.generateCardList(str.split(" "));
        int[] actual = PokerHandUtil.getCardMatrix(cardList);

        int[] expected = new int[14];
        expected[1] = 2;
        expected[2] = 1;
        expected[3] = 1;
        expected[4] = 1;

        assertArrayEquals(expected, actual);


        String str2 = "AC AS AD AH 5D";
        List<Card> cardList2 = PokerHandParser.generateCardList(str2.split(" "));
        int[] actual2 = PokerHandUtil.getCardMatrix(cardList2);

        int[] expected2 = new int[14];
        expected2[4] = 1;
        expected2[13] = 4;
        assertArrayEquals(expected2, actual2);

        // null cardlist
        assertNull(PokerHandUtil.getCardMatrix(null));
    }


    @Test
    public void testCompareTwoListEmptyList() {

        Exception exception = assertThrows(RuntimeException.class, () -> {
            PokerHandUtil.compareList(null, null);
        });

        String expectedMessage = "Both cardlist cannot be empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testCompareTwoListNotSameSize() {

        List<Card> cardList1 = List.of(new Card(1, "S"), new Card(2, "H"));
        List<Card> cardList2 = List.of(new Card(1, "S"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            PokerHandUtil.compareList(cardList1, cardList2);
        });

        String expectedMessage = "Both cardlist must be the same size";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }


    @ParameterizedTest
    @CsvSource({
            "2C 2S 4D 3C 5D, 2C 2S 4D 3C 5D, 0",
            "2C 2S 4D 3C AD, 2C 2S 4D 3C 5D, 1",
            "2C 2S 4D 3C TD, 2C 2S 4D 3C AD, -1"})
    public void testCompareTwoList(String data1, String data2, int expected) {
        List<Card> cardList1 = PokerHandParser.generateCardList(data1.split(" "));
        List<Card> cardList2 = PokerHandParser.generateCardList(data2.split(" "));

        assertEquals(expected, PokerHandUtil.compareList(cardList1, cardList2));

    }

}
