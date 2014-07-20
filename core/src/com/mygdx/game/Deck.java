package com.mygdx.game;

public class Deck {

	private final int CARD_COUNT = 52;
	private Card[] cards;
	private int deckPos;
	
	public Deck() {
		this.cards = new Card[CARD_COUNT];
		this.deckPos = 0;
	}
	
	//creates a bunch of cards and puts them in the cards array it then does some swaps so the cards are randomized
	public void generateCards(){
		
	}
	
	//give the current top of the deck to the tile or player that called it.
	public Card dealCard(){
		Card topCard = cards[deckPos];
		deckPos = (deckPos + 1) % CARD_COUNT;
		return topCard;
	}

}
