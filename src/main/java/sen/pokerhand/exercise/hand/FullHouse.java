package sen.pokerhand.exercise.hand;

import sen.pokerhand.exercise.constant.PokerHandTypeConstant;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Three of a kind and a Pair
 */
public class FullHouse extends AbstractPokerHand {

    private int threeOfKind;
    private int pair;

    public FullHouse(List<Card> cardList) {
        this.cardList = cardList;
        this.rank = PokerHandTypeConstant.FULL_HOUSE;
    }

    @Override
    public int getThreeOfKind() {
        return threeOfKind;
    }

    public void setThreeOfKind(int threeOfKind) {
        this.threeOfKind = threeOfKind;
    }

    @Override
    public int getPair() {
        return pair;
    }

    public void setPair(int pair) {
        this.pair = pair;
    }

    @Override
    public List<Card> getSingleCardList() {
        return this.cardList.stream().filter((c) -> c.getValue() != this.threeOfKind && c.getValue() != this.pair)
                .collect(Collectors.toList());
    }
}
