package sen.pokerhand.exercise.hand;

import sen.pokerhand.exercise.constant.PokerHandTypeConstant;

import java.util.List;

/**
 * All five cards in consecutive value order
 */
public class Straight extends AbstractPokerHand {

    public Straight(List<Card> cardList) {
        this.cardList = cardList;
        this.rank = PokerHandTypeConstant.STRAIGHT;
    }
}
