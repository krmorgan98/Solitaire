package Solitaire;

/*
 * File: PlayingCard.java
 * Author: Kirsten Morgan
 * Date: 2/8/17
 * Description: Creates playing cards
 */
public class PlayingCard implements Comparable<PlayingCard> {

	// WRITE YOUR FIELDS, METHODS AND JAVADOC COMMENTS HERE:
	private int cardRank;
	private char cardSuit;
	/*
	 * constructs playing card
	 * @param rank of card
	 * @param suit of card
	 */
	public PlayingCard(int cardRank, char cardSuit) {
		this.cardRank = cardRank;
		this.cardSuit = cardSuit;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * @param other card to compare to
	 */
	public int compareTo(PlayingCard otherCard) {
		int i = 0;
		if (otherCard.getRank() > this.cardRank) {
			i = 1;
		}
		if (otherCard.getRank() < this.cardRank) {
			i = -1;
		}
		return i;
	}
	/*
	 * finds card images
	 */

	public String getImageFileName() {
		return cardRank + "" + cardSuit + ".png";
	}
	/*
	 * @return rank of card
	 */
	public int getRank() {
		return cardRank;
	}
	/*
	 * @return suit of card
	 */
	public char getSuit() {
		return cardSuit;
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String rank = Integer.toString(cardRank);
		String suit = Character.toString(cardSuit);
		return rank + suit;
	}

	public Object getSize() {
		
		return null;
	}

	public void setLocation(int i, int j) {
		// TODO Auto-generated method stub
		
	}

}
