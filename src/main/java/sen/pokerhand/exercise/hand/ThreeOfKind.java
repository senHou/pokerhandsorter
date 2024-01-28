package sen.pokerhand.exercise.hand;

import sen.pokerhand.exercise.constant.PokerHandTypeConstant;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Three cards of the same value
 */
public class ThreeOfKind extends AbstractPokerHand {

    private int threeOfKind;

    public ThreeOfKind(List<Card> cardList) {
        this.cardList = cardList;
        this.rank = PokerHandTypeConstant.THREE_OF_A_KIND;
    }

    @Override
    public int getThreeOfKind() {
        return threeOfKind;
    }

    public void setThreeOfKind(int threeOfKind) {
        this.threeOfKind = threeOfKind;
    }

    @Override
    public List<Card> getSingleCardList() {
        return this.cardList.stream().filter((c) -> c.getValue() != this.threeOfKind)
                .collect(Collectors.toList());
    }
}
