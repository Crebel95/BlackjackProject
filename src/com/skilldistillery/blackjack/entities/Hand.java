package com.skilldistillery.blackjack.entities;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand {

	List<Card> hand;

	public Hand() {
		hand = new ArrayList<>();
	}

	public void addCard(Deck deck) {
		hand.add(deck.dealCard());
	}

	public void clear() {

	}
	public Card getCard(int index) {
		return hand.get(0);
	}


	public abstract int getHandValue();

	public String toString() {
		String output = "Cards: ";
		for (Card card : hand) {
			output += "["+ card +"]" + "  ";
		}
		return output;
	}


}
