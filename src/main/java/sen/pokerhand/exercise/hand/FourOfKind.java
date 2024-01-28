package sen.pokerhand.exercise.hand;

import sen.pokerhand.exercise.constant.PokerHandTypeConstant;
import sen.pokerhand.exercise.util.PokerHandUtil;

import java.util.List;
import java.util.stream.Collectors;

import static sen.pokerhand.exercise.constant.PokerHandTypeConstant.FOUR;

/**
 * Four cards of the same value
 */
public class FourOfKind extends AbstractPokerHand {

    private int fourOfKind;

    public FourOfKind(List<Card> cardList) {
        this.cardList = cardList;
        this.rank = PokerHandTypeConstant.FOUR_OF_A_KIND;
        this.setFourOfKind(PokerHandUtil.getTheSameKind(cardList, FOUR));
    }

    @Override
    public int getFourOfKind() {
        return fourOfKind;
    }

    public void setFourOfKind(int fourOfKind) {
        this.fourOfKind = fourOfKind;
    }

    @Override
    public List<Card> getSingleCardList() {
        return this.cardList.stream().filter((c) -> c.getValue() != this.fourOfKind)
                .collect(Collectors.toList());
    }
}
