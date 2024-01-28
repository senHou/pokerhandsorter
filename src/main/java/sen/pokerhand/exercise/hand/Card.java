package sen.pokerhand.exercise.hand;

import java.util.Objects;

/**
 * Card class
 * A card contains a value and a suite
 */
public class Card implements Comparable<Card>{
    private int value;
    private String suite;

    public Card(int value, String suite) {
        this.value = value;
        this.suite = suite;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    @Override
    public int compareTo(Card card) {
        return this.value - card.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return value == card.value && Objects.equals(suite, card.suite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, suite);
    }
}
