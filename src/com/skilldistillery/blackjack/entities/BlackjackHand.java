package com.skilldistillery.blackjack.entities;

public class BlackjackHand extends Hand {

	public BlackjackHand() {

	}

	public int getHandValue(int rank) {
		return rank;
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
