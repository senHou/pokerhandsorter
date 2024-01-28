package sen.pokerhand.exercise.hand;

import java.util.List;

/**
 * An abstract poker hand class that implements PokerHand interface.
 * Implemented some common methods that declared in the interface
 */
public abstract class AbstractPokerHand implements PokerHand {

    protected List<Card> cardList;
    protected  int rank;

    @Override
    public int getRank() {
        return rank;
    }

    @Override
    public int getPair() {
        return 0;
    }

    @Override
    public void setPair(int pair) {

    }

    @Override
    public int getHighPair() {
        return 0;
    }

    @Override
    public void setHighPair(int highPair) {

    }

    @Override
    public int getLowPair() {
        return 0;
    }

    @Override
    public void setLowPair(int lowPair) {

    }

    @Override
    public int getThreeOfKind() {
        return 0;
    }

    @Override
    public void setThreeOfKind(int threeOfKind) {

    }

    @Override
    public int getFourOfKind() {
        return 0;
    }

    @Override
    public void setFourOfKind(int fourOfKind) {

    }

    @Override
    public List<Card> getSingleCardList() {
        return this.cardList;
    }

    @Override
    public List<Card> getCardList() {
        return this.cardList;
    }
}
