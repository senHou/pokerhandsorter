package sen.pokerhand.exercise.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;

import sen.pokerhand.exercise.exception.InvalidHandException;
import  sen.pokerhand.exercise.hand.Card;
import  sen.pokerhand.exercise.hand.PokerHand;
import sen.pokerhand.exercise.hand.PokerHandFactory;

import static sen.pokerhand.exercise.constant.PokerHandTypeConstant.PLAYER_ONE;
import static sen.pokerhand.exercise.constant.PokerHandTypeConstant.PLAYER_TWO;


/**
 * Poker hand parser, parse the input file and generate pokerhand list
 */
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

    /**
     * read the input file and generate a list
     * @return a list contains all the data in the file
     * @throws Exception
     */
    public static List<String> readFile() throws Exception {

        List<String> data = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in, Charset.defaultCharset()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
        }

        return data;
    }

    /**
     * Generate a map that contains both players poker hand
     * @param data a string that contains both players poker hand  2H 9S 9C JD KH TD 4H JC 9H 8H
     * @return a map
     * @throws Exception
     */
    public static Map<String, List<PokerHand>> generatePokerHands(List<String> data) throws Exception {

        if (data == null || data.isEmpty()) {
            throw new InvalidHandException();
        }

        PokerHandFactory pokerHandFactory = new PokerHandFactory();
        Map<String, List<PokerHand>> playerHandsMap = new HashMap<>();


        List<PokerHand> playerOneHands = new ArrayList<>();
        List<PokerHand> playerTwoHands = new ArrayList<>();

        for (String row : data) {
            // validate the data
            // it must be the patten  2H 9S 9C JD KH TD 4H JC 9H 8H
            if (!row.matches("([1-9TJQKA][CDSH] ){9}[1-9TJQKA][CDSH]")) {
                throw new InvalidHandException();
            }

            String[] cards = row.split(" ");
            String[] handOne = Arrays.copyOfRange(cards, 0, 5);
            String[] handTwo = Arrays.copyOfRange(cards, 5, 10);

            PokerHand playerOneHand = pokerHandFactory.getPokerHand(generateCardList(handOne));
            PokerHand playerTwoHand = pokerHandFactory.getPokerHand(generateCardList(handTwo));

            playerOneHands.add(playerOneHand);
            playerTwoHands.add(playerTwoHand);

        }

        playerHandsMap.put(PLAYER_ONE, playerOneHands);
        playerHandsMap.put(PLAYER_TWO, playerTwoHands);

        return playerHandsMap;
    }

    /**
     * generate a card list
     * @param data passed in dta Example :  [2H,9S,9C,JD,KH]
     * @return a card list
     */
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
