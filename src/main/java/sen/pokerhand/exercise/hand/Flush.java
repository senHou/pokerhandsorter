package sen.pokerhand.exercise.hand;

import sen.pokerhand.exercise.constant.PokerHandTypeConstant;

import java.util.List;

/**
 * All five cards having the same suit
 */
public class Flush extends AbstractPokerHand {

    public Flush(List<Card> cardList) {
        this.cardList = cardList;
        this.rank = PokerHandTypeConstant.FLUSH;
    }
}
