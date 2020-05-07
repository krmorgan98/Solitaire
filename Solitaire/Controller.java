package Solitaire;
import java.io.PrintStream;
import java.util.*;

public class Controller {
	private ArrayList<PlayingCard> drawPile;
	private ArrayList<PlayingCard>[] aces;
	private ArrayList<PlayingCard>[] sevenPiles;
	private ArrayList<PlayingCard> threeCards;
	
	private final char suits[] = { 'C', 'D', 'H', 'S' };
	private PlayingCard card;
	
	public Controller() {
		drawPile = new ArrayList<PlayingCard>();
		for (int suit = 0; suit < 4; suit++) {
			for (int rank = 1; rank < 14; rank++) {
				//creates all 52 cards
				card = new PlayingCard(rank, suits[suit]);
				drawPile.add(card); //adds cards to the deck
			}

		}

		aces = new ArrayList[4];
		for (int j = 0; j < aces.length; j++)
			aces[j] = new ArrayList<PlayingCard>();

		sevenPiles = new ArrayList[7];
		for (int k = 0; k < sevenPiles.length; k++)
			sevenPiles[k] = new ArrayList<PlayingCard>();

		threeCards = new ArrayList<PlayingCard>();
	}

	public PlayingCard getCardFromPiles(int listNum, int index) {
		if (sevenPiles[listNum] == null || index <= -1 || index >= sevenPiles[listNum].size()) {
			return null; // @return null if index is invalid or if list is empty
		}
		if (listNum == 0 || listNum == 1 || listNum == 2 || listNum == 3 || listNum == 4 || listNum == 5 || listNum == 6) {
			return sevenPiles[listNum].get(index);
			// @return card at index from specified list
		} else {
			return null;
		}
		
	}

	public PlayingCard getCardFromThreeCards() {
		if (threeCards.size() > 0)
			return (PlayingCard) threeCards.get(threeCards.size() - 1);
		else
			return null;
	}

	public PlayingCard getCardFromAces(int i) {
		if ((i == 0 || i == 1 || i == 2 || i == 3) && aces[i].size() > 0)
			return (PlayingCard) aces[i].get(aces[i].size() - 1);
		else
			return null;
	}

	public PlayingCard getCardFromDrawPile() {
		if (drawPile.size() > 0)
			return (PlayingCard) drawPile.get(drawPile.size() - 1);
		else
			return null;
	}

	public void startNewGame() {
		for (int i = 0; i < 4; i++)
			if (aces[i].size() > 0) {
				drawPile.addAll(aces[i]);
				aces[i].clear();
			}

		for (int j = 0; j < 7; j++)
			if (sevenPiles[j].size() > 0) {
				drawPile.addAll(sevenPiles[j]);
				sevenPiles[j].clear();
			}

		for (int k = 0; k < threeCards.size(); k++)
			if (threeCards.size() > 0) {
				drawPile.addAll(threeCards);
				threeCards.clear();
			}

		Collections.shuffle(drawPile);
		firstDeal();
	}

	public boolean moveWithinLists(int i) {
		int j = 0;
		if (j < 7) {
			if (i >= 0 && i < 7 && sevenPiles[i].size() > 0 && sevenPiles[j].size() > 0) {
				if ((((PlayingCard) sevenPiles[i].get(sevenPiles[i].size() - 1)).getSuit() == 'S'
						|| ((PlayingCard) sevenPiles[i].get(sevenPiles[i].size() - 1)).getSuit() == 'C')
						&& (((PlayingCard) sevenPiles[j].get(sevenPiles[j].size() - 1)).getSuit() == 'D'
								|| ((PlayingCard) sevenPiles[j].get(sevenPiles[j].size() - 1)).getSuit() == 'H')
						&& ((PlayingCard) sevenPiles[i].get(sevenPiles[i].size() - 1))
								.getRank() == ((PlayingCard) sevenPiles[j].get(sevenPiles[j].size() - 1)).getRank()
										- 1) {
					sevenPiles[j].add(sevenPiles[i].remove(sevenPiles[i].size() - 1));
					return true;
				}
				if ((((PlayingCard) sevenPiles[i].get(sevenPiles[i].size() - 1)).getSuit() == 'D'
						|| ((PlayingCard) sevenPiles[i].get(sevenPiles[i].size() - 1)).getSuit() == 'H')
						&& (((PlayingCard) sevenPiles[j].get(sevenPiles[j].size() - 1)).getSuit() == 'S'
								|| ((PlayingCard) sevenPiles[j].get(sevenPiles[j].size() - 1)).getSuit() == 'C')
						&& ((PlayingCard) sevenPiles[i].get(sevenPiles[i].size() - 1))
								.getRank() == ((PlayingCard) sevenPiles[j].get(sevenPiles[j].size() - 1)).getRank()
										- 1) {
					sevenPiles[j].add(sevenPiles[i].remove(sevenPiles[i].size() - 1));
					return true;
				}
			}
			if (i >= 0 && i < 7 && sevenPiles[i].size() > 0
					&& ((PlayingCard) sevenPiles[i].get(sevenPiles[i].size() - 1)).getRank() == 13
					&& sevenPiles[j].size() == 0) {
				sevenPiles[j].add(sevenPiles[i].remove(sevenPiles[i].size() - 1));
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void moveToAces(int i) {
		if (i >= 0 && i < 7 && sevenPiles[i].size() > 0) {
			byte byte0 = -1;
			if (((PlayingCard) sevenPiles[i].get(sevenPiles[i].size() - 1)).getSuit() == 'S')
				byte0 = 0;
			else if (((PlayingCard) sevenPiles[i].get(sevenPiles[i].size() - 1)).getSuit() == 'C')
				byte0 = 1;
			else if (((PlayingCard) sevenPiles[i].get(sevenPiles[i].size() - 1)).getSuit() == 'D')
				byte0 = 2;
			else if (((PlayingCard) sevenPiles[i].get(sevenPiles[i].size() - 1)).getSuit() == 'H')
				byte0 = 3;
			if (aces[byte0].size() == 0 && ((PlayingCard) sevenPiles[i].get(sevenPiles[i].size() - 1)).getRank() == 1)
				aces[byte0].add(sevenPiles[i].remove(sevenPiles[i].size() - 1));
			else if (aces[byte0].size() > 0 && ((PlayingCard) sevenPiles[i].get(sevenPiles[i].size() - 1))
					.getRank() == ((PlayingCard) aces[byte0].get(aces[byte0].size() - 1)).getRank() + 1)
				aces[byte0].add(sevenPiles[i].remove(sevenPiles[i].size() - 1));
		}
	}

	public boolean canMoveToAces(int i) {
		if (i >= 0 && i < 7 && sevenPiles[i].size() > 0) {
			byte byte0 = -1;
			if (((PlayingCard) sevenPiles[i].get(sevenPiles[i].size() - 1)).getSuit() == 'S')
				byte0 = 0;
			else if (((PlayingCard) sevenPiles[i].get(sevenPiles[i].size() - 1)).getSuit() == 'C')
				byte0 = 1;
			else if (((PlayingCard) sevenPiles[i].get(sevenPiles[i].size() - 1)).getSuit() == 'D')
				byte0 = 2;
			else if (((PlayingCard) sevenPiles[i].get(sevenPiles[i].size() - 1)).getSuit() == 'H')
				byte0 = 3;
			if (aces[byte0].size() == 0 && ((PlayingCard) sevenPiles[i].get(sevenPiles[i].size() - 1)).getRank() == 1)
				return true;
			return aces[byte0].size() > 0 && ((PlayingCard) sevenPiles[i].get(sevenPiles[i].size() - 1))
					.getRank() == ((PlayingCard) aces[byte0].get(aces[byte0].size() - 1)).getRank() + 1;
		} else {
			return false;
		}
	}

	public void firstDeal() {
		boolean flag = false;
		for (int j = 1; j < 8; j++) {
			for (int i = 0; i != j; i++)
				sevenPiles[j - 1].add(drawPile.remove(0));

		}

		System.out.println(Arrays.deepToString(sevenPiles));
	}

	public void deal() {
		if (drawPile.size() >= 3) {
			for (int i = 0; i < 3; i++)
				threeCards.add(drawPile.remove(0));

		} else if (drawPile.size() == 2) {
			for (int j = 0; j < 2; j++)
				threeCards.add(drawPile.remove(0));

		} else if (drawPile.size() == 1)
			threeCards.add(drawPile.remove(0));
	}

	public void reDeck() {
		int i = threeCards.size();
		if (drawPile.size() == 0 && threeCards.size() > 0) {
			for (int j = 0; j < i; j++)
				drawPile.add(threeCards.remove(threeCards.size() - 1));

		}
	}

	public int getLast(int i) {
		if (sevenPiles[i].size() <= 0)
			return 0;
		if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6)
			return sevenPiles[i].size() - 1;
		else
			return 0;
	}

	public boolean moveFromThreesToSevens(int i) {
		if (i >= 0 && i < 7 && sevenPiles[i].size() > 0 && threeCards.size() > 0) {
			if ((((PlayingCard) threeCards.get(threeCards.size() - 1)).getSuit() == 'S'
					|| ((PlayingCard) threeCards.get(threeCards.size() - 1)).getSuit() == 'C')
					&& (((PlayingCard) sevenPiles[i].get(sevenPiles[i].size() - 1)).getSuit() == 'D'
							|| ((PlayingCard) sevenPiles[i].get(sevenPiles[i].size() - 1)).getSuit() == 'H')
					&& ((PlayingCard) threeCards.get(threeCards.size() - 1))
							.getRank() == ((PlayingCard) sevenPiles[i].get(sevenPiles[i].size() - 1)).getRank() - 1) {
				sevenPiles[i].add(threeCards.remove(threeCards.size() - 1));
				System.out.println(Arrays.deepToString(sevenPiles));
				return true;
			}
			if ((((PlayingCard) threeCards.get(threeCards.size() - 1)).getSuit() == 'D'
					|| ((PlayingCard) threeCards.get(threeCards.size() - 1)).getSuit() == 'H')
					&& (((PlayingCard) sevenPiles[i].get(sevenPiles[i].size() - 1)).getSuit() == 'S'
							|| ((PlayingCard) sevenPiles[i].get(sevenPiles[i].size() - 1)).getSuit() == 'C')
					&& ((PlayingCard) threeCards.get(threeCards.size() - 1))
							.getRank() == ((PlayingCard) sevenPiles[i].get(sevenPiles[i].size() - 1)).getRank() - 1) {
				sevenPiles[i].add(threeCards.remove(threeCards.size() - 1));
				System.out.println(Arrays.deepToString(sevenPiles));
				return true;
			}
		}
		if (i >= 0 && i < 7 && threeCards.size() > 0
				&& ((PlayingCard) threeCards.get(threeCards.size() - 1)).getRank() == 13 && sevenPiles[i].size() == 0) {
			sevenPiles[i].add(threeCards.remove(threeCards.size() - 1));
			return true;
		} else {
			return false;
		}
	}

	public void moveFromThreesToAces() {
		if (threeCards.size() > 0) {
			byte byte0 = -1;
			if (((PlayingCard) threeCards.get(threeCards.size() - 1)).getSuit() == 'S')
				byte0 = 0;
			else if (((PlayingCard) threeCards.get(threeCards.size() - 1)).getSuit() == 'C')
				byte0 = 1;
			else if (((PlayingCard) threeCards.get(threeCards.size() - 1)).getSuit() == 'D')
				byte0 = 2;
			else if (((PlayingCard) threeCards.get(threeCards.size() - 1)).getSuit() == 'H')
				byte0 = 3;
			if (aces[byte0].size() == 0 && ((PlayingCard) threeCards.get(threeCards.size() - 1)).getRank() == 1)
				aces[byte0].add(threeCards.remove(threeCards.size() - 1));
			else if (aces[byte0].size() > 0 && ((PlayingCard) threeCards.get(threeCards.size() - 1))
					.getRank() == ((PlayingCard) aces[byte0].get(aces[byte0].size() - 1)).getRank() + 1)
				aces[byte0].add(threeCards.remove(threeCards.size() - 1));
		}
	}

	public boolean canMoveFromThreesToAces() {
		if (threeCards.size() > 0) {
			byte byte0 = -1;
			if (((PlayingCard) threeCards.get(threeCards.size() - 1)).getSuit() == 'S')
				byte0 = 0;
			else if (((PlayingCard) threeCards.get(threeCards.size() - 1)).getSuit() == 'C')
				byte0 = 1;
			else if (((PlayingCard) threeCards.get(threeCards.size() - 1)).getSuit() == 'D')
				byte0 = 2;
			else if (((PlayingCard) threeCards.get(threeCards.size() - 1)).getSuit() == 'H')
				byte0 = 3;
			if (aces[byte0].size() == 0 && ((PlayingCard) threeCards.get(threeCards.size() - 1)).getRank() == 1)
				return true;
			return aces[byte0].size() > 0 && ((PlayingCard) threeCards.get(threeCards.size() - 1))
					.getRank() == ((PlayingCard) aces[byte0].get(aces[byte0].size() - 1)).getRank() + 1;
		} else {
			return false;
		}
	}

	public void moveFromAces(int i, int j) {
		if (i >= 0 && i < 4 && j >= 0 && j < 7 && aces[i].size() > 0 && sevenPiles[j].size() > 0) {
			if ((((PlayingCard) aces[i].get(aces[i].size() - 1)).getSuit() == 'S'
					|| ((PlayingCard) aces[i].get(aces[i].size() - 1)).getSuit() == 'C')
					&& (((PlayingCard) sevenPiles[j].get(sevenPiles[j].size() - 1)).getSuit() == 'D'
							|| ((PlayingCard) sevenPiles[j].get(sevenPiles[j].size() - 1)).getSuit() == 'H')
					&& ((PlayingCard) aces[i].get(aces[i].size() - 1))
							.getRank() == ((PlayingCard) sevenPiles[j].get(sevenPiles[j].size() - 1)).getRank() - 1)
				sevenPiles[j].add(aces[i].remove(aces[i].size() - 1));
			if ((((PlayingCard) aces[i].get(aces[i].size() - 1)).getSuit() == 'D'
					|| ((PlayingCard) aces[i].get(aces[i].size() - 1)).getSuit() == 'H')
					&& (((PlayingCard) sevenPiles[j].get(sevenPiles[j].size() - 1)).getSuit() == 'S'
							|| ((PlayingCard) sevenPiles[j].get(sevenPiles[j].size() - 1)).getSuit() == 'C')
					&& ((PlayingCard) aces[i].get(aces[i].size() - 1))
							.getRank() == ((PlayingCard) sevenPiles[j].get(sevenPiles[j].size() - 1)).getRank() - 1)
				sevenPiles[j].add(aces[i].remove(aces[i].size() - 1));
			if (((PlayingCard) aces[i].get(aces[i].size() - 1)).getRank() == 13 && sevenPiles[j].size() == 0)
				sevenPiles[j].add(aces[i].remove(aces[i].size() - 1));
		}
	}

}
