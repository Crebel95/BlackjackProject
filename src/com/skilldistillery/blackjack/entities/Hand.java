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

	public int getHandValue() {
		int value = 0;
		for (Card card : hand) {
			value += card.getValue();
		}return value;
	}

	public String toString() {
		String output = null;
		for (Card card : hand) {
			output += card + " ";
		}
		return output;
	}

}
