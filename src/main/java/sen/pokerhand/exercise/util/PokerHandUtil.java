package sen.pokerhand.exercise.util;

import sen.pokerhand.exercise.hand.Card;

import java.util.*;

import static sen.pokerhand.exercise.constant.PokerHandTypeConstant.*;

/**
 * A PokerHandUtil class that provide checking and comparing methods
 */
public class PokerHandUtil {

    /**
     * check royal flush Ten, Jack, Queen, King and Ace in the same suit
     * @param cardList a cardlist in a poker hand
     * @return true if is royal flush
     */
    public static boolean isRoyalFlush(List<Card> cardList) {
        if (isNull(cardList)) {
            return false;
        }

        // if it is straight and flush and the smallest value is T 10
        // then it is Royal Flush
        if (isStraight(cardList) && isFlush(cardList)) {
            List<Card> temp = new ArrayList<>(cardList);
            Collections.sort(temp);
            return temp.get(0).getValue() == T;
        } else {
            return false;
        }
    }

    /**
     * check straight flush All five cards in consecutive value order, with the same suit
     * @param cardList a cardlist in a poker hand
     * @return ture if is straight flush
     */
    public static boolean isStraightFlush(List<Card> cardList) {
        if (isNull(cardList)) {
            return false;
        }

        // if it is straight and flush and the smallest value is not T 10
        // then it is straight flush
        if (isStraight(cardList) && isFlush(cardList)) {
            List<Card> temp = new ArrayList<>(cardList);
            Collections.sort(temp);
            return temp.get(0).getValue() != T;
        } else {
            return false;
        }
    }

    /**
     * check four of kind Four cards of the same value
     * @param cardList a cardlist in a poker hand
     * @return true if is four of kind
     */
    public static boolean isFourOfKind(List<Card> cardList) {
        if (isNull(cardList)) {
            return false;
        }

        int[] matrix = getCardMatrix(cardList);
        if (matrix != null) {
            for (int count : matrix) {
                if (count == FOUR) {
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * check full house Three of a kind and a Pair
     * @param cardList a cardlist in a poker hand
     * @return true if is full house
     */
    public static boolean isFullHouse(List<Card> cardList) {
        if (isNull(cardList)) {
            return false;
        }

        int[] matrix = getCardMatrix(cardList);
        if (matrix != null) {
            boolean threeOfKindFound = false;
            boolean pairFound = false;
            for (int count : matrix) {
                if (count == THREE) {
                    threeOfKindFound = true;
                }

                if (count == TWO) {
                    pairFound = true;
                }
            }

            return threeOfKindFound && pairFound;
        }
        return false;
    }

    /**
     * check flush All five cards having the same suit
     * @param cardList a cardlist in a poker hand
     * @return true if is flush
     */
    public static boolean isFlush(List<Card> cardList) {
        if (isNull(cardList)) {
            return false;
        }
        Set<String> suitSet = new HashSet<>();

        // add the card suite into a set.
        for (Card card : cardList) {
            suitSet.add(card.getSuite());
        }
        // if the set size is 1, it is flush
        return suitSet.size() == 1;
    }

    /**
     * check straight
     * @param cardList a cardlist in a poker hand
     * @return true if is straight
     */
    public static boolean isStraight(List<Card> cardList) {
        if (isNull(cardList)) {
            return false;
        }

        // sort the card in ascending order
        List<Card> temp = new ArrayList<>(cardList);
        Collections.sort(temp);
        int firstElement = temp.get(0).getValue();

        // loop through the list from position 2.
        // subtract the value by index and compare the value with
        // the first element in the list. If the result are all equal to the
        // first element, then return true, otherwise, return false;
        for (int i = 1; i < temp.size(); i ++) {
            if (temp.get(i).getValue() - i != firstElement) {
                return false;
            }
        }
        return true;
    }

    /**
     * check three of kind Three cards of the same value
     * @param cardList a cardlist in a poker hand
     * @return true if is three of kind
     */
    public static boolean isThreeOfKind(List<Card> cardList) {
        if (isNull(cardList)) {
            return false;
        }

        int[] matrix = getCardMatrix(cardList);
        if (matrix != null) {
            boolean threeOfKindFound = false;
            boolean pairFound = false;
            for (int count : matrix) {
                if (count == THREE) {
                    threeOfKindFound = true;
                }

                if (count == TWO) {
                    pairFound = true;
                }
            }

            // make sure it is not a full house
            return threeOfKindFound && !pairFound;
        }

        return false;
    }

    /**
     * check two pairs Two different pairs
     * @param cardList a cardlist in a poker hand
     * @return true if is two pairs
     */
    public static boolean isTwoPairs(List<Card> cardList) {
        return findNumberOfPairs(cardList) == TWO;
    }

    /**
     * check pair Two cards of same value
     * @param cardList a cardlist in a poker hand
     * @return true if is a pair
     */
    public static boolean isPair(List<Card> cardList) {
        return findNumberOfPairs(cardList) == 1 && !isFullHouse(cardList);
    }

    /**
     * get the number of pairs in a hand
     * @param cardList a cardlist in a poker hand
     * @return the number of pairs
     */
    public static int findNumberOfPairs(List<Card> cardList) {
        if (isNull(cardList)) {
            return 0;
        }

        int pairCount = 0;
        int[] matrix = getCardMatrix(cardList);
        if (matrix != null) {
            for (int count : matrix) {
                // if count == 2, then it is a pair
                if (count == TWO) {
                    pairCount ++;
                }
            }
        }

        return pairCount;
    }


    /**
     * get the same card (the value is the same) in a poker hand
     * @param cardList a cardlist in a poker hand
     * @param times how many times a number appear in the cardlist it must be within 2 and 4
     * @return the value that exist in the cardlist the number of times
     */
    public static int getTheSameKind(List<Card> cardList, int times) {
        if (times < TWO || times > FOUR) {
            return 0;
        }

        if (isNull(cardList)) {
            return 0;
        }

        int[] matrix = getCardMatrix(cardList);
        if (matrix != null) {
            for (int i = 0; i < matrix.length; i ++) {
                if (matrix[i] == times) {
                    return i + 1;
                }
            }
        }
        return 0;
    }


    /**
     * check two pairs Two different pairs
     * @param cardList a cardlist in a poker hand
     * @return a list of two numbers. the first number is the low pair value, the last number is the high pair value
     */
    public static List<Integer> getTwoPairs(List<Card> cardList) {
        if (!isTwoPairs(cardList)) {
            return null;
        }

        List<Integer> pairList = new ArrayList<>();
        int[] matrix = getCardMatrix(cardList);

        if (matrix != null) {
            for (int i = 0; i < matrix.length; i ++) {
                if (matrix[i] == TWO) {
                    pairList.add(i + 1);
                }
            }
        }

        // sort the result so that the low pair is in the fist element and the high pair is in the last element
        Collections.sort(pairList);
        return pairList;
    }

    /**
     * check the passed in cardlist is null
     * @param cardList a cardlist in a poker hand
     * @return ture if is empty or null
     */
    public static boolean isNull(List<Card> cardList) {
        return cardList == null || cardList.isEmpty();
    }

    /**
     * get the number of appearance for each value in the cardlist
     * the card value  array index + 1
     * Example: card value 2, the index is 1 card value A, the index is 13
     * @param cardList a cardlist in a poker hand
     * @return an array that keep the number of appearance for each value
     */
    public static int[] getCardMatrix(List<Card> cardList) {
        if (isNull(cardList)) {
            return null;
        }

        // an array with size 14. it keeps the card value from 2 - A and the number of appearance for each value
        int[] matrix = new int[14];

        for (Card card : cardList) {
            matrix[card.getValue() - 1]  = matrix[card.getValue() - 1] + 1;
        }

        return matrix;
    }


    /**
     * compare the value in two lists they must not be null and they must be the same size
     * @param cardList1 a cardlist in a poker hand
     * @param cardList2 a cardlist in another poker hand
     * @return positive number if cardlist1 contains bigger value
     *         negative number if cardlist2 contains bigger value
     *         0 if both list contain the same value
     */
    public static int compareList(List<Card> cardList1, List<Card> cardList2) {

        if (cardList1 == null || cardList2 == null) {
            throw new RuntimeException("Both cardlist cannot be empty");
        }

        if (cardList1.size() != cardList2.size()) {
            throw new RuntimeException("Both cardlist must be the same size");
        }

        // sort two list in ascending order
        Collections.sort(cardList1);
        Collections.sort(cardList2);


        // loop through the both list in the reverse order and compare the value one by one
        for (int i = cardList1.size() - 1; i >= 0; i --) {
            if (cardList1.get(i).getValue() > cardList2.get(i).getValue()) {
                return 1;
            } else if (cardList1.get(i).getValue() < cardList2.get(i).getValue()) {
                return -1;
            }
        }

        return 0;
    }
}
