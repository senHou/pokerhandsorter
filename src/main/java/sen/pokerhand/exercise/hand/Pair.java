package sen.pokerhand.exercise.hand;

import sen.pokerhand.exercise.constant.PokerHandTypeConstant;
import sen.pokerhand.exercise.util.PokerHandUtil;

import java.util.List;
import java.util.stream.Collectors;

import static sen.pokerhand.exercise.constant.PokerHandTypeConstant.TWO;

/**
 * Two cards of same value
 */
public class Pair extends AbstractPokerHand {
    private int pair;

    public Pair(List<Card> cardList) {
        this.cardList = cardList;
        this.rank = PokerHandTypeConstant.PAIR;
        this.setPair(PokerHandUtil.getTheSameKind(cardList, TWO));
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
        return this.cardList.stream().filter((c) -> c.getValue() != this.pair)
                .collect(Collectors.toList());
    }
}
