package sen.pokerhand.exercise;

import sen.pokerhand.exercise.hand.PokerHand;
import sen.pokerhand.exercise.util.PokerHandParser;
import sen.pokerhand.exercise.util.PokerHandUtil;

import java.util.List;
import java.util.Map;

import static sen.pokerhand.exercise.constant.PokerHandTypeConstant.*;

public class PokerHandSorter {
    public static void main(String[] args) {
        PokerHandSorter pokerHandSorter = new PokerHandSorter();
        try {
            pokerHandSorter.sort(PokerHandParser.generatePokerHands(
                    PokerHandParser.readFile()
            ));

        } catch (Exception e) {
            System.err.println("Errors: " + e.getMessage());
        }
    }

    /**
     * compare the player list and print out the result
     * @param playerHandMap a map that contains both player's poker hand
     * @throws Exception
     */
    public void sort(Map<String, List<PokerHand>> playerHandMap) throws Exception {
        List<PokerHand> playerOne = playerHandMap.get(PLAYER_ONE);
        List<PokerHand> playerTwo = playerHandMap.get(PLAYER_TWO);

        int playerOneCount = 0;
        int playerTwoCount = 0;

        for (int i = 0; i < playerOne.size(); i ++) {
            if (playerOne.get(i).getRank() > playerTwo.get(i).getRank()) {
                playerOneCount ++;
            } else if (playerOne.get(i).getRank() < playerTwo.get(i).getRank()) {
                playerTwoCount ++;
            } else {
                // two hands are the same rank compare the card, we can also use
                // player two's rank as they are the same rank
                int result = compareCard(playerOne.get(i), playerTwo.get(i), playerOne.get(i).getRank());

                if (result > 0) {
                    playerOneCount ++;
                } else if (result < 0) {
                    playerTwoCount ++;
                }
            }
        }

        System.out.println("Player 1: " + playerOneCount);
        System.out.println("Player 2: " + playerTwoCount);
    }

    /**
     * compare two poker hand.
     * @param pokerHand1
     * @param pokerHand2
     * @param rank
     * @return positive number pokerhand1 win, negative number pokerhand2 win, 0 no win
     */
    private int compareCard(PokerHand pokerHand1, PokerHand pokerHand2, int rank) {
        if (rank == ROYAL_FLUSH || rank == STRAIGHT_FLUSH || rank == FLUSH || rank == STRAIGHT || rank == HIGH_CARD) {
            return PokerHandUtil.compareList(pokerHand1.getCardList(), pokerHand2.getCardList());
        } else if (rank == FOUR_OF_A_KIND) {
           return PokerHandUtil.compareFourOfKind(pokerHand1, pokerHand2);
        } else if (rank == FULL_HOUSE) {
            return PokerHandUtil.compareFullHouse(pokerHand1, pokerHand2);
        } else if (rank == THREE_OF_A_KIND) {
            return PokerHandUtil.compareThreeOfKind(pokerHand1, pokerHand2);
        } else if (rank == TWO_PAIRS) {
            return PokerHandUtil.compareTwoPairs(pokerHand1, pokerHand2);
        } else {
            return PokerHandUtil.comparePair(pokerHand1, pokerHand2);
        }
    }
}
