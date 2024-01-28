package sen.pokerhand.exercise.hand;

import sen.pokerhand.exercise.constant.PokerHandTypeConstant;

import java.util.List;

/**
 * All five cards in consecutive value order, with the same suit
 */
public class StraightFlush extends AbstractPokerHand {

    public StraightFlush(List<Card> cardList) {
        this.cardList = cardList;
        this.rank = PokerHandTypeConstant.STRAIGHT_FLUSH;
    }
}
