package sen.pokerhand.exercise.hand;

import sen.pokerhand.exercise.constant.PokerHandTypeConstant;

import java.util.List;

/**
 * Ten, Jack, Queen, King and Ace in the same suit
 */
public class RoyalFlush extends AbstractPokerHand {

    public RoyalFlush(List<Card> cardList) {
        this.cardList = cardList;
        this.rank = PokerHandTypeConstant.ROYAL_FLUSH;
    }
}
