package poker;

public class Deck {

	private Card[] cards;
	//Initializes a new deck with the standard 52 cards, in a specific order.
	public Deck() {
		cards = new Card[52];
		int counter=0;
		for(int suit=0; suit<=3; suit++){
			for(int value=1; value<=13; value++){
				cards[counter] = new Card(value, suit);
				counter++;
			}
		}
	}
	//Copy constructor
	public Deck(Deck other) {
		cards = new Card[other.cards.length];
		for(int count=0; count<cards.length; count++){
			cards[count] = other.cards[count];
		}
	}
	public Card getCardAt(int position) {
		return cards[position];
	}
	public int getNumCards() {
		return cards.length;
	}
	/*
	 * Re-arranges the cards that are in the deck.
	 */
	public void shuffle() {
		int indexCount = 0;
		Card[] bottom, top;
		//If the deck has an odd amount of cards, top[] carries the extra card.
		if(cards.length%2==0){
			bottom = new Card[cards.length/2];
			top = new Card[cards.length/2];
		}else{
			bottom = new Card[cards.length/2];
			top = new Card[(cards.length/2)+1];
		}
		for(int topCount=0; topCount<top.length; topCount++){
			top[topCount] = cards[topCount];
		}
		for(int bottomCount=0; bottomCount<bottom.length; bottomCount++){
			bottom[bottomCount] = cards[(top.length)+bottomCount];
		}
		for(int count=0; count<cards.length; count++){
			if(count%2==0){
				cards[count] = top[indexCount];
			}else{
				cards[count] = bottom[indexCount];
				indexCount++;
			}
		}
	}
	/*
	 * Divides the deck into two sub-packets, then switches their position.
	 */
	public void cut(int position) {
		Card[] top = new Card[position];
		Card[] bottom = new Card[cards.length-position];
		//Card[] tempDeck = new Card[cards.length];
		//Populates top[] with the cards from the 'top' of the deck
		for(int count=0; count<top.length; count++){
			top[count] = cards[count];
		}
		//Populates bottom[] with the cards from the 'bottom' of the deck
		for(int count=0; count<bottom.length; count++){
			bottom[count] = cards[position+count];
		}
		//copies the 2 new arrays back into the cards array
		for(int count=0; count<cards.length; count++){
			if(count<(cards.length-position)){
				cards[count] = bottom[count];
			}else{
				cards[count] = top[count-bottom.length];
			}
		}
	}
	/*
	 * Creates a smaller array and copies the values from the original deck,
	 * leaving out the cards that have been dealt.
	 */
	public Card[] deal(int numCards) {
		Card[] smaller = new Card[cards.length-numCards];
		Card[] dealtCards = new Card[numCards];
		for(int count=0; count<smaller.length; count++){
			smaller[count] = cards[count+(numCards)];
		}
		for(int count=0; count<dealtCards.length; count++){
			dealtCards[count] = cards[count];
		}
		cards = smaller;
		return dealtCards;
	}
}
