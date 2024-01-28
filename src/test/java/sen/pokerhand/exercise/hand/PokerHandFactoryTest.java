package sen.pokerhand.exercise.hand;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import sen.pokerhand.exercise.util.PokerHandParser;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class PokerHandFactoryTest {

    @ParameterizedTest
    @MethodSource("getPokerHands")
    public void testGetPokerHand(List<Card> cardList, PokerHand expected) {

        PokerHandFactory pokerHandFactory = new PokerHandFactory();

        PokerHand pokerHand = pokerHandFactory.getPokerHand(cardList);

        assertEquals(expected.getRank(), pokerHand.getRank());

        List<Integer> actualValueList = pokerHand.getCardList().stream().map(Card::getValue).toList();
        List<String> actualSuiteList = pokerHand.getCardList().stream().map(Card::getSuite).toList();
        List<Integer> expectedValueList = expected.getCardList().stream().map(Card::getValue).toList();
        List<String> expectedSuiteList = expected.getCardList().stream().map(Card::getSuite).toList();

        assertArrayEquals(expectedValueList.toArray(), actualValueList.toArray());
        assertArrayEquals(actualSuiteList.toArray(), expectedSuiteList.toArray());
    }

    private static Stream<Arguments> getPokerHands() {
        PokerHand royalFlush = new RoyalFlush(PokerHandParser.generateCardList("TC JC QC KC AC".split(" ")));
        PokerHand straightFlush = new StraightFlush(PokerHandParser.generateCardList("9C TC JC QC KC".split(" ")));
        PokerHand fourOfKind = new FourOfKind(PokerHandParser.generateCardList("TC TH TS TD AC".split(" ")));
        PokerHand fullHouse = new FullHouse(PokerHandParser.generateCardList("TC TH TS AD AC".split(" ")));
        PokerHand flush = new Flush(PokerHandParser.generateCardList("TC 7C 2C 9C TC".split(" ")));
        PokerHand straight = new Straight(PokerHandParser.generateCardList("2C 3C 4D 5C 6H".split(" ")));
        PokerHand threeOfKind = new ThreeOfKind(PokerHandParser.generateCardList("2C 2H 2D 5C 6H".split(" ")));
        PokerHand twoPairs = new TwoPairs(PokerHandParser.generateCardList("2C 2S 3D 3C 5D".split(" ")));
        PokerHand pair = new Pair(PokerHandParser.generateCardList("2C 2S 4D 3C 5D".split(" ")));
        PokerHand highCard = new HighCard(PokerHandParser.generateCardList("2C 6S 9D JC AD".split(" ")));

        return Stream.of(
                Arguments.of(PokerHandParser.generateCardList("TC JC QC KC AC".split(" ")), royalFlush),
                Arguments.of(PokerHandParser.generateCardList("9C TC JC QC KC".split(" ")), straightFlush),
                Arguments.of(PokerHandParser.generateCardList("TC TH TS TD AC".split(" ")), fourOfKind),
                Arguments.of(PokerHandParser.generateCardList("TC TH TS AD AC".split(" ")), fullHouse),
                Arguments.of(PokerHandParser.generateCardList("TC 7C 2C 9C TC".split(" ")), flush),
                Arguments.of(PokerHandParser.generateCardList("2C 3C 4D 5C 6H".split(" ")), straight),
                Arguments.of(PokerHandParser.generateCardList("2C 2H 2D 5C 6H".split(" ")), threeOfKind),
                Arguments.of(PokerHandParser.generateCardList("2C 2S 3D 3C 5D".split(" ")), twoPairs),
                Arguments.of(PokerHandParser.generateCardList("2C 2S 4D 3C 5D".split(" ")), pair),
                Arguments.of(PokerHandParser.generateCardList("2C 6S 9D JC AD".split(" ")), highCard)

        );
    }
}
