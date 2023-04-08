package com.skilldistillery.blackjack.entities;

public class BlackjackHand extends Hand {

	public BlackjackHand() {

	}
    @Override
	public int getHandValue(){
		int value = 0;
		for (Card card : hand) {
			value += card.getValue().getValue();
		}return value;
	}

	public boolean isBlackjack() {
		if (getHandValue() == 21) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isBust() {
		
		if (getHandValue() > 21) {
			return true;
		} else {
			return false;
		}

	}

}
