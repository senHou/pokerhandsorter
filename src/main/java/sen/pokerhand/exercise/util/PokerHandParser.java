package sen.pokerhand.exercise.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sen.pokerhand.exercise.exception.InvalidHandException;
import  sen.pokerhand.exercise.hand.Card;

public class PokerHandParser {

    private final static Map<String, Integer> cardValueMap = new HashMap<>();

    static {
        cardValueMap.put("2", 2);
        cardValueMap.put("3", 3);
        cardValueMap.put("4", 4);
        cardValueMap.put("5", 5);
        cardValueMap.put("6", 6);
        cardValueMap.put("7", 7);
        cardValueMap.put("8", 8);
        cardValueMap.put("9", 9);
        cardValueMap.put("T", 10);
        cardValueMap.put("J", 11);
        cardValueMap.put("Q", 12);
        cardValueMap.put("K", 13);
        cardValueMap.put("A", 14);
    }

    public static List<Card> generateCardList(String[] data) {
        if (data == null || data.length != 5) {
            throw new InvalidHandException();
        }

        List<Card> cardList = new ArrayList<>();

        for (String cardString: data) {
            if (cardString == null || cardString.length() != 2) {
                throw new InvalidHandException();
            }
            String value = String.valueOf(cardString.charAt(0));
            String suite = String.valueOf(cardString.charAt(1));
            Card card = new Card(cardValueMap.get(value), suite);
            cardList.add(card);
        }

        return cardList;
    }

}
