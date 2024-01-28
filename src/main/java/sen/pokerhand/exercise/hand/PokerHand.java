package sen.pokerhand.exercise.hand;

import java.util.List;

/**
 * PokerHand Interface.
 * Declare required methods for poker hand
 */
public interface PokerHand {

    int getRank();

    int getPair();

    void setPair(int pair);

    int getHighPair();

    void setHighPair(int highPair);

    int getLowPair();

    void setLowPair(int lowPair);

    int getThreeOfKind();

    void setThreeOfKind(int threeOfKind);

    int getFourOfKind();

    void setFourOfKind(int fourOfKind);

    List<Card> getSingleCardList();

    List<Card> getCardList();
}
