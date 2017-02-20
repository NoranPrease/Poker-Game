package poker;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class StudentTests {

	
	@Test
	public void testSingleTest() {
		Card[] testHand = new Card[5];
		testHand[0] = new Card(1,1);
		testHand[1] = new Card(2,1);
		testHand[2] = new Card(3,1);
		testHand[3] = new Card(4,1);
		testHand[4] = new Card(5,1);
		assertFalse(PokerHandEvaluator.hasPair(testHand));
		
	}
	@Test
	public void testConstructor(){
		Deck testDeck = new Deck();
		assertEquals(testDeck.getNumCards(), 52);
	}
	@Test
	public void CopyConstructor(){
		
		Deck testDeck = new Deck();
		assertEquals(testDeck.getNumCards(), 52);
		Deck testDeck2 = new Deck(testDeck);
		assertTrue(testDeck.getCardAt(1).equals(testDeck2.getCardAt(1))
				&& testDeck.getCardAt(11).equals(testDeck2.getCardAt(11))
				&& testDeck.getCardAt(15).equals(testDeck2.getCardAt(15))
				&& testDeck.getCardAt(28).equals(testDeck2.getCardAt(28))
				&& testDeck.getCardAt(50).equals(testDeck2.getCardAt(50)));
	}
	@Test
	public void testGetter(){
		Deck testDeck = new Deck();
		
		assertTrue(testDeck.getCardAt(1).getSuit() == 0
				&& testDeck.getCardAt(1).getValue() == 2
				&& testDeck.getCardAt(16).getSuit() == 1
				&& testDeck.getCardAt(16).getValue() == 4);
	}
	@Test
	public void testShuffle(){
		Deck testDeck = new Deck();
		testDeck.shuffle();
		
		assertTrue(testDeck.getCardAt(1).getSuit() == 2
				&& testDeck.getCardAt(1).getValue() == 1
				&& testDeck.getCardAt(26).getSuit() == 1
				&& testDeck.getCardAt(26).getValue() == 1);
	}
	@Test
	public void testCut(){
		Deck testDeck = new Deck();

		testDeck.cut(4);
		
		assertTrue(testDeck.getCardAt(0).getValue() == 5
				&& testDeck.getCardAt(0).getSuit() == 0
				&& testDeck.getCardAt(48).getValue() == 1
				&& testDeck.getCardAt(48).getSuit() == 0);
	}
	@Test
	public void testHasPair(){
		Card[] testCards = new Card[5];
		testCards[0] = new Card(1,2);
		testCards[1] = new Card(1,1);
		testCards[2] = new Card(1,0);
		testCards[3] = new Card(1,3);
		testCards[4] = new Card(6,2);
		assertTrue(PokerHandEvaluator.hasPair(testCards));
		
		testCards[0] = new Card(1,0);
		testCards[1] = new Card(2,1);
		testCards[2] = new Card(3,2);
		testCards[3] = new Card(4,3);
		testCards[4] = new Card(5,0);
		assertFalse(PokerHandEvaluator.hasPair(testCards));
		
		testCards[0] = new Card(1,0);
		testCards[1] = new Card(2,1);
		testCards[2] = new Card(1,2);
		testCards[3] = new Card(4,3);
		testCards[4] = new Card(5,0);
		assertTrue(PokerHandEvaluator.hasPair(testCards));
	}
	@Test
	public void testHasTwoPair(){
		Card[] testCards = new Card[5];
		testCards[0] = new Card(3,2);
		testCards[1] = new Card(4,1);
		testCards[2] = new Card(3,0);
		testCards[3] = new Card(11,3);
		testCards[4] = new Card(11,2);
		assertTrue(PokerHandEvaluator.hasTwoPair(testCards));
		
		testCards[0] = new Card(3,2);
		testCards[1] = new Card(3,1);
		testCards[2] = new Card(12,0);
		testCards[3] = new Card(11,3);
		testCards[4] = new Card(11,2);
		assertTrue(PokerHandEvaluator.hasTwoPair(testCards));
		
		//Four of a kind should return false for two pair
		testCards[0] = new Card(5,1);
		testCards[1] = new Card(13,1);
		testCards[2] = new Card(5,0);
		testCards[3] = new Card(5,3);
		testCards[4] = new Card(5,2);
		assertFalse(PokerHandEvaluator.hasTwoPair(testCards));
		
		//A full house should return true for two pair
		testCards[0] = new Card(3,2);
		testCards[1] = new Card(11,1);
		testCards[2] = new Card(3,0);
		testCards[3] = new Card(11,3);
		testCards[4] = new Card(11,2);
		assertTrue(PokerHandEvaluator.hasTwoPair(testCards));
		
		testCards[0] = new Card(2,2);
		testCards[1] = new Card(3,1);
		testCards[2] = new Card(3,0);
		testCards[3] = new Card(3,3);
		testCards[4] = new Card(11,2);
		assertFalse(PokerHandEvaluator.hasTwoPair(testCards));
	}
	@Test
	public void testHasOddStraight(){
		Card[] testCards = new Card[5];
		testCards[0] = new Card(3,2);
		testCards[1] = new Card(5,1);
		testCards[2] = new Card(7,0);
		testCards[3] = new Card(11,3);
		testCards[4] = new Card(9,2);
		assertTrue(PokerHandEvaluator.hasOddStraight(testCards));
		
		testCards[0] = new Card(3,1);
		testCards[1] = new Card(1,0);
		testCards[2] = new Card(7,2);
		testCards[3] = new Card(5,1);
		testCards[4] = new Card(9,3);
		assertTrue(PokerHandEvaluator.hasOddStraight(testCards));
		
		testCards[0] = new Card(3,2);
		testCards[1] = new Card(1,1);
		testCards[2] = new Card(7,0);
		testCards[3] = new Card(5,3);
		testCards[4] = new Card(11,2);
		assertFalse(PokerHandEvaluator.hasOddStraight(testCards));
	}
	@Test
	public void testHasEvenStraight(){
		Card[] testCards = new Card[5];
		testCards[0] = new Card(2,2);
		testCards[1] = new Card(6,1);
		testCards[2] = new Card(4,0);
		testCards[3] = new Card(10,3);
		testCards[4] = new Card(8,2);
		assertTrue(PokerHandEvaluator.hasEvenStraight(testCards));
		
		//Tests if it accounts for the Ace as the last card in the straight
		testCards[0] = new Card(6,2);
		testCards[1] = new Card(10,1);
		testCards[2] = new Card(12,0);
		testCards[3] = new Card(8,3);
		testCards[4] = new Card(1,2);
		assertTrue(PokerHandEvaluator.hasEvenStraight(testCards));
		
		testCards[0] = new Card(6,2);
		testCards[1] = new Card(10,1);
		testCards[2] = new Card(12,0);
		testCards[3] = new Card(8,3);
		testCards[4] = new Card(2,2);
		assertFalse(PokerHandEvaluator.hasEvenStraight(testCards));
	}
	@Test
	public void testHasRainbow(){
		Card[] testCards = new Card[5];
		testCards[0] = new Card(2,2);
		testCards[1] = new Card(4,1);
		testCards[2] = new Card(6,0);
		testCards[3] = new Card(8,3);
		testCards[4] = new Card(10,2);
		assertTrue(PokerHandEvaluator.hasRainbow(testCards));
		
		testCards[0] = new Card(5,2);
		testCards[1] = new Card(1,1);
		testCards[2] = new Card(11,0);
		testCards[3] = new Card(12,2);
		testCards[4] = new Card(10,0);
		assertFalse(PokerHandEvaluator.hasRainbow(testCards));
	}
	@Test
	public void testHasThreeOfAKind(){
		Card[] testCards = new Card[5];
		testCards[0] = new Card(3,0);
		testCards[1] = new Card(5,1);
		testCards[2] = new Card(3,1);
		testCards[3] = new Card(3,2);
		testCards[4] = new Card(10,2);
		assertTrue(PokerHandEvaluator.hasThreeOfAKind(testCards));
		
		//Even though it's a full house, it should still return true
		testCards[0] = new Card(8,0);
		testCards[1] = new Card(12,2);
		testCards[2] = new Card(8,2);
		testCards[3] = new Card(12,1);
		testCards[4] = new Card(12,3);
		assertTrue(PokerHandEvaluator.hasThreeOfAKind(testCards));
		
		//Four of a kind should return true for 3 of a kind
		testCards[0] = new Card(8,0);
		testCards[1] = new Card(8,2);
		testCards[2] = new Card(8,1);
		testCards[3] = new Card(8,3);
		testCards[4] = new Card(12,3);
		assertTrue(PokerHandEvaluator.hasThreeOfAKind(testCards));
		
		testCards[0] = new Card(3,0);
		testCards[1] = new Card(5,1);
		testCards[2] = new Card(8,1);
		testCards[3] = new Card(3,2);
		testCards[4] = new Card(13,2);
		assertFalse(PokerHandEvaluator.hasThreeOfAKind(testCards));
	}
	@Test
	public void testHasFlush(){
		Card[] testCards = new Card[5];
		testCards[0] = new Card(1,1);
		testCards[1] = new Card(2,1);
		testCards[2] = new Card(3,1);
		testCards[3] = new Card(4,1);
		testCards[4] = new Card(5,1);
		assertTrue(PokerHandEvaluator.hasFlush(testCards));
		
		testCards[0] = new Card(3,3);
		testCards[1] = new Card(2,3);
		testCards[2] = new Card(9,3);
		testCards[3] = new Card(11,3);
		testCards[4] = new Card(12,3);
		assertTrue(PokerHandEvaluator.hasFlush(testCards));
		
		testCards[0] = new Card(12,1);
		testCards[1] = new Card(5,1);
		testCards[2] = new Card(3,2);
		testCards[3] = new Card(4,1);
		testCards[4] = new Card(5,1);
		assertFalse(PokerHandEvaluator.hasFlush(testCards));
	}
	@Test
	public void testHasFullHouse(){
		Card[] testCards = new Card[5];
		testCards[0] = new Card(1,1);
		testCards[1] = new Card(2,1);
		testCards[2] = new Card(2,0);
		testCards[3] = new Card(1,0);
		testCards[4] = new Card(1,2);
		assertTrue(PokerHandEvaluator.hasFullHouse(testCards));
		
		testCards[0] = new Card(10,1);
		testCards[1] = new Card(6,1);
		testCards[2] = new Card(3,0);
		testCards[3] = new Card(6,0);
		testCards[4] = new Card(3,2);
		assertFalse(PokerHandEvaluator.hasFullHouse(testCards));
		
		testCards[0] = new Card(3,2);
		testCards[1] = new Card(11,1);
		testCards[2] = new Card(3,1);
		testCards[3] = new Card(11,0);
		testCards[4] = new Card(11,2);
		assertTrue(PokerHandEvaluator.hasFullHouse(testCards));
	}
	@Test
	public void testHasFourOfAKind(){
		Card[] testCards = new Card[5];
		testCards[0] = new Card(1,0);
		testCards[1] = new Card(1,1);
		testCards[2] = new Card(2,0);
		testCards[3] = new Card(1,2);
		testCards[4] = new Card(1,3);
		assertTrue(PokerHandEvaluator.hasFourOfAKind(testCards));
		
		testCards[0] = new Card(7,0);
		testCards[1] = new Card(13,1);
		testCards[2] = new Card(13,0);
		testCards[3] = new Card(13,2);
		testCards[4] = new Card(13,3);
		assertTrue(PokerHandEvaluator.hasFourOfAKind(testCards));
		
		testCards[0] = new Card(5,0);
		testCards[1] = new Card(8,1);
		testCards[2] = new Card(5,1);
		testCards[3] = new Card(8,2);
		testCards[4] = new Card(8,3);
		assertFalse(PokerHandEvaluator.hasFourOfAKind(testCards));
	}
	@Test
	public void testHasStraightRainbow(){
		Card[] testCards = new Card[5];
		testCards[0] = new Card(3,0);
		testCards[1] = new Card(1,2);
		testCards[2] = new Card(9,1);
		testCards[3] = new Card(7,2);
		testCards[4] = new Card(5,3);
		assertTrue(PokerHandEvaluator.hasStraightRainbow(testCards));
		
		//Tests to see if it recognizes the Ace in even straight rainbow
		testCards[0] = new Card(6,0);
		testCards[1] = new Card(10,2);
		testCards[2] = new Card(1,1);
		testCards[3] = new Card(8,2);
		testCards[4] = new Card(12,3);
		assertTrue(PokerHandEvaluator.hasStraightRainbow(testCards));
		
		testCards[0] = new Card(5,1);
		testCards[1] = new Card(13,2);
		testCards[2] = new Card(9,1);
		testCards[3] = new Card(7,2);
		testCards[4] = new Card(11,3);
		assertFalse(PokerHandEvaluator.hasStraightRainbow(testCards));
	}
	@Test
	public void testHasStraightFlush(){
		Card[] testCards = new Card[5];
		testCards[0] = new Card(3,0);
		testCards[1] = new Card(11,0);
		testCards[2] = new Card(7,0);
		testCards[3] = new Card(9,0);
		testCards[4] = new Card(5,0);
		assertTrue(PokerHandEvaluator.hasStraightFlush(testCards));
		
		//Tests if it recognizes the ace in the even straight flush
		testCards[0] = new Card(10,2);
		testCards[1] = new Card(12,2);
		testCards[2] = new Card(1,2);
		testCards[3] = new Card(8,2);
		testCards[4] = new Card(6,2);
		assertTrue(PokerHandEvaluator.hasStraightFlush(testCards));
		
		testCards[0] = new Card(3,0);
		testCards[1] = new Card(1,0);
		testCards[2] = new Card(9,2);
		testCards[3] = new Card(7,0);
		testCards[4] = new Card(5,0);
		assertFalse(PokerHandEvaluator.hasStraightFlush(testCards));
	}
	@Test
	public void testGetNumPairs(){
		Card[] testCards = new Card[5];
		//Tests Two of a Kind
		testCards[0] = new Card(1,0);
		testCards[1] = new Card(2,1);
		testCards[2] = new Card(7,2);
		testCards[3] = new Card(12,3);
		testCards[4] = new Card(1,2);
		assertTrue(PokerHandEvaluator.getNumPairs(testCards) == 2);
		
		//Tests Two Pair
		testCards[0] = new Card(5,0);
		testCards[1] = new Card(2,1);
		testCards[2] = new Card(5,2);
		testCards[3] = new Card(11,3);
		testCards[4] = new Card(11,0);
		assertTrue(PokerHandEvaluator.getNumPairs(testCards) == 4);
		
		testCards[0] = new Card(1,0);
		testCards[1] = new Card(2,1);
		testCards[2] = new Card(1,2);
		testCards[3] = new Card(2,3);
		testCards[4] = new Card(1,0);
		assertFalse(PokerHandEvaluator.getNumPairs(testCards) == 4);
		
		//Tests Three of a Kind
		testCards[0] = new Card(8,0);
		testCards[1] = new Card(13,1);
		testCards[2] = new Card(8,2);
		testCards[3] = new Card(5,3);
		testCards[4] = new Card(8,3);
		assertTrue(PokerHandEvaluator.getNumPairs(testCards) == 6);
		
		//Tests Full House
		testCards[0] = new Card(1,0);
		testCards[1] = new Card(2,1);
		testCards[2] = new Card(1,2);
		testCards[3] = new Card(2,3);
		testCards[4] = new Card(1,0);
		assertTrue(PokerHandEvaluator.getNumPairs(testCards) == 8);
		
		//Tests Four of a Kind
		testCards[0] = new Card(9,0);
		testCards[1] = new Card(9,1);
		testCards[2] = new Card(8,2);
		testCards[3] = new Card(9,3);
		testCards[4] = new Card(9,2);
		assertTrue(PokerHandEvaluator.getNumPairs(testCards) == 12);
	}
}