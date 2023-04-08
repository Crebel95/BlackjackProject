package com.skilldistillery.blackjack.app;

import com.skilldistillery.blackjack.entities.BlackjackHand;
import com.skilldistillery.blackjack.entities.Dealer;
import com.skilldistillery.blackjack.entities.Deck;
import com.skilldistillery.blackjack.entities.Hand;
import com.skilldistillery.blackjack.entities.Player;

public class BlackjackApp {

	public static void main(String[] args) {
		BlackjackApp bja = new BlackjackApp();
		
		bja.launch();
	}

	public void launch() {
		Hand startNewGame = new BlackjackHand();
		Dealer dealer = new Dealer("Dealer", startNewGame);
		Player player = new Player("Player", startNewGame);
		Deck deck = new Deck();
		deck.shuffle();
		dealer.getHand().addCard(deck);
		dealer.getHand().addCard(deck);
		
		player.getHand().addCard(deck);
		player.getHand().addCard(deck);
		
		dealer.viewPlayerHand();
		player.viewPlayerHand();
		
		// check for blackjack, if player does not have blackjack,
		// ask if they would like to hit or stand
		
		
	
		
	}

//	
//	public Player determineWinner() {
//
//		return theWinner;
//	}

}
