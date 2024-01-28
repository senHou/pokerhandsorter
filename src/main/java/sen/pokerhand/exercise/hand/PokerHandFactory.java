package sen.pokerhand.exercise.hand;

import sen.pokerhand.exercise.util.PokerHandUtil;

import java.util.List;

/**
 * A Poker hand factory class to create the poker hand
 */
public class PokerHandFactory {
    public PokerHand getPokerHand(List<Card> cardList) {
        if (PokerHandUtil.isRoyalFlush(cardList)) {
            return new RoyalFlush(cardList);
        } else if (PokerHandUtil.isStraightFlush(cardList)) {
            return new StraightFlush(cardList);
        } else if (PokerHandUtil.isFourOfKind(cardList)) {
            return new FourOfKind(cardList);
        } else if (PokerHandUtil.isFullHouse(cardList)) {
            return new FullHouse(cardList);
        } else if (PokerHandUtil.isFlush(cardList)) {
            return new Flush(cardList);
        } else if (PokerHandUtil.isStraight(cardList)) {
            return new Straight(cardList);
        } else if (PokerHandUtil.isThreeOfKind(cardList)) {
            return new ThreeOfKind(cardList);
        } else if (PokerHandUtil.isTwoPairs(cardList)) {
            return new TwoPairs(cardList);
        } else if (PokerHandUtil.isPair(cardList)) {
            return new Pair(cardList);
        } else {
            return new HighCard(cardList);
        }
    }
}
