package com.skilldistillery.blackjack.entities;

public class Dealer extends Player {
	
	public Dealer(String name, Hand hand) {
		super(name, hand);
		}
	@Override
	public void viewPlayerHand() {
		System.out.println(this.hand.getCard(0));
	}
	

}
