package sen.pokerhand.exercise.hand;

import sen.pokerhand.exercise.constant.PokerHandTypeConstant;
import sen.pokerhand.exercise.util.PokerHandUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Two different pairs
 */
public class TwoPairs extends AbstractPokerHand {
    private int lowPair;
    private int highPair;

    public TwoPairs(List<Card> cardList) {
        this.cardList = cardList;
        this.rank = PokerHandTypeConstant.TWO_PAIRS;
        List<Integer> pairs = PokerHandUtil.getTwoPairs(cardList);
        if (pairs != null && pairs.size() == 2) {
            this.setLowPair(pairs.get(0));
            this.setHighPair(pairs.get(1));
        }
    }

    @Override
    public int getLowPair() {
        return lowPair;
    }

    public void setLowPair(int lowPair) {
        this.lowPair = lowPair;
    }

    @Override
    public int getHighPair() {
        return highPair;
    }

    public void setHighPair(int highPair) {
        this.highPair = highPair;
    }

    @Override
    public List<Card> getSingleCardList() {
        return this.cardList.stream().filter((c) -> c.getValue() != this.lowPair && c.getValue() != this.highPair)
                .collect(Collectors.toList());
    }
}
