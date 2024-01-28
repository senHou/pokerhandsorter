package sen.pokerhand.exercise.hand;

import sen.pokerhand.exercise.constant.PokerHandTypeConstant;

import java.util.List;

/**
 * Highest value card
 */
public class HighCard extends AbstractPokerHand {

    public HighCard(List<Card> cardList) {
        this.cardList = cardList;
        this.rank = PokerHandTypeConstant.HIGH_CARD;
    }
}
