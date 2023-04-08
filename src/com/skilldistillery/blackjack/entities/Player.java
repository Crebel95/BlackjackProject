package com.skilldistillery.blackjack.entities;

public class Player {
	protected String name;
	private Hand hand;
	
	public Player(String name, Hand hand) {
		this.name = name;
		this.hand = hand;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public void viewPlayerHand() {
		System.out.println(this.hand);
	}
	
	public void receiveCard(Deck card) {
		hand.addCard(card);
	}


	public Hand getHand() {
		return hand;
	}


	public void setHand(Hand hand) {
		this.hand = hand;
	}
	

}
