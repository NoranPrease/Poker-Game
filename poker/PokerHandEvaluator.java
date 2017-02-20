package poker;

import java.util.Arrays;

public class PokerHandEvaluator {
	/*
	 * Calls getNumPairs method and returns true if the method returns 2
	 */
	public static boolean hasPair(Card[] cards){
		boolean answer = false;
		if(getNumPairs(cards) >= 2){
			answer = true;
		}
		return answer;
	}
	/*
	 * Checks every card to see if every suit is present in the hand.
	 */
	public static boolean hasRainbow(Card[] cards){
		boolean answer = false;
		if(hasSuit(cards, 0)==true && hasSuit(cards, 1)==true
				&& hasSuit(cards, 2)==true && hasSuit(cards, 3)==true){
			answer = true;
		}
		return answer;
	}
	/*
	 * Sorts the values of the hand, then checks at specific locations in the
	 *array to see if there are at least two different pairs(i.e. 2 J's 2 Q's).
	 */
	public static boolean hasTwoPair(Card[] cards){
		boolean answer = false;
		//Both two pair and full house both return two pair to true.
		if(getNumPairs(cards)==4 || getNumPairs(cards)==8){
			answer = true;
		}
		return answer;
	}
	/*
	 * Sorts the values of the hand, then checks at specific locations in the
	 * array to see if there are at least 3 kinds of that card.
	 */
	public static boolean hasThreeOfAKind(Card[] cards){
		boolean answer = false;
		if(getNumPairs(cards)>=6){
			answer = true;
		}
		return answer;
	}
	/*
	 * Sorts the values of the hand, then checks to see if the card at count
	 * is exactly 2 less than the card at count+1.
	 */
	public static boolean hasOddStraight(Card[] cards){
		boolean answer = false;
		int sortedCards[] = new int[cards.length];
		int straightCards = 1;
		sortedCards = sorter(cards);
		//Needs to only go to length-1 to keep the for loop in bounds
		for(int count=0; count<sortedCards.length-1; count++){
			if(sortedCards[count]+2 == sortedCards[count+1]
					&& sortedCards[count]%2 != 0){
				straightCards++;
			}
		}
		if(straightCards==5){
			answer = true;
		}
		return answer;
	}
	/*
	 * Sorts the values of the hand, then checks to see if the card at count
	 * is exactly 2 less than the card at count+1.
	 */
	public static boolean hasEvenStraight(Card[] cards){
		boolean answer = false;
		int sortedCards[] = new int[cards.length];
		int straightCards = 1;
		sortedCards = sorter(cards);
		//If there's an Ace, it assigns the value 14 so it can be counted
		for(int count=0; count<sortedCards.length; count++){
			if(sortedCards[count] == 1){
				sortedCards[count] = 14;
			}
		}
		//Needs to only go to length-1 to keep the for loop in bounds
		for(int count=0; count<sortedCards.length-1; count++){
			if(sortedCards[count]+2 == sortedCards[count+1]
					&& sortedCards[count]%2 == 0){
				straightCards++;
			}
		}
		if(straightCards==5){
			answer = true;
		}
		//If there's an Ace, the value at sortedCards[0] should be 14 but it
		//shouldn't increment the straightCards value
		else if(sortedCards[0]==14 && straightCards ==4){
			answer = true;
		}
		return answer;
	}
	/*
	 * Gets the suit at cards[0], and sees if all the other cards are the same.
	 */
	public static boolean hasFlush(Card[] cards){
		boolean answer = false;
		int correctSuit = cards[0].getSuit();
		for(int count=0; count<cards.length; count++){
			if(cards[count].getSuit() == correctSuit){
				answer = true;
			}
			else{
				answer = false;
				break;
			}
		}
		return answer;
	}
	/*
	 *Sorts the values of the hand and gets two ints to represent the values
	 *of the full house.  Then check if these values have either 1 or 2 more
	 *cards like it in the hand. 
	 */
	public static boolean hasFullHouse(Card[] cards){
		boolean answer = false;
		if(getNumPairs(cards) == 8){
			answer = true;
		}
		return answer;
	}
	/*
	 * Sorts the values of the hand and checks two different cards to see
	 * if there are 4 with the same value in the deck.
	 */
	public static boolean hasFourOfAKind(Card[] cards){
		boolean answer = false;
		if(getNumPairs(cards) >= 12){
			answer = true;
		}
		return answer;
	}
	/*
	 * If either hasStraight method returns true, and the hasRainbow method
	 * returns true, answer = true.
	 */
	public static boolean hasStraightRainbow(Card[] cards){
		boolean answer = false;
		if(PokerHandEvaluator.hasEvenStraight(cards) == true
				|| PokerHandEvaluator.hasOddStraight(cards) == true){
			if(PokerHandEvaluator.hasRainbow(cards)==true){
				answer = true;
			}
		}
		return answer;
	}
	/*
	 * If either hasStraight method returns true, and the hasFlush method
	 * returns true, then answer = true.
	 */
	public static boolean hasStraightFlush(Card[] cards){
		boolean answer = false;
		if(PokerHandEvaluator.hasEvenStraight(cards) == true
				|| PokerHandEvaluator.hasOddStraight(cards) == true){
			if(PokerHandEvaluator.hasFlush(cards)==true){
				answer = true;
			}
		}
		return answer;
	}
	/*
	 * Helper method that sees if the hand has a specific suit.
	 */
	public static boolean hasSuit(Card[] cards, int suit){
		boolean answer = false;
		for(int count=0; count<cards.length; count++){
			if(cards[count].getSuit()==suit){
				answer = true;
				break;
			}
		}return answer;
	}
	/*
	 * This method compares every card to every other card and keeps track of
	 * how many pairs there are in the hand.  Due to the way this method works,
	 * you don't get the actual number of pairs, but rather a unique
	 * return value that speaks for each individual hand. They are:
	 * 		Return Value:		Resulting Hand:
	 * 			2+	--------------	Pair
	 * 			4	--------------	Two Pair			
	 * 			6+	--------------	Three of a Kind
	 * 			8	--------------	Full House
	 * 			12	--------------	Four of a Kind
	 */
	public static int getNumPairs(Card[] cards){
		int totalPairs = 0;
		for(int count1=0; count1<cards.length; count1++){
			for(int count2=0; count2<cards.length; count2++){
				if(cards[count1].getValue() == (cards[count2].getValue())
						&& cards[count1] != cards[count2]){
					totalPairs++;
				}
			}
		}
		return totalPairs;
	}
	/*
	 * This helper method sorts the values of the card array and returns a
	 * one-dimensional array of the sorted values
	 */
	public static int[] sorter(Card[] cards){
		int temp;
		int[] newArray = new int[cards.length];
		for(int count=0; count<newArray.length; count++){
			newArray[count] = cards[count].getValue();
		}
		for(int count1=0; count1<5; count1++)
		for(int count=0; count<4; count++){
			if(newArray[count] > newArray[count+1]){
				temp = newArray[count];
				newArray[count] = newArray[count+1];
				newArray[count+1] = temp;
			}
		}
		return newArray;
	}
}