package com.skilldistillery.blackjack.app;

import java.util.Scanner;

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
		Scanner sc = new Scanner(System.in);
		Hand hand = new BlackjackHand();
		Dealer dealer = new Dealer("Dealer", hand);
		Player player = new Player("Player", new BlackjackHand());
		Deck deck = new Deck();
		boolean running = true;
		deck.shuffle();
		dealer.getHand().addCard(deck);
		dealer.getHand().addCard(deck);

		player.getHand().addCard(deck);
		player.getHand().addCard(deck);

		dealer.viewPlayerHand();
		player.viewPlayerHand();

		// check cards for blackjack(player and dealer)... create 'if statement' for
		// both scenarios
		// if neither have blackjack, ask if they would like to hit or stand
		// dealer must draw if less than 17

		while (running) {

			if (player.isBlackjack()) {

				if (dealer.isBlackjack()) {
					System.out.println("Push! Both players have Blackjack.");
				} else {
					System.out.println("You beat the dealer with " + player.getHandValue() + " compared to "
							+ dealer.getHandValue());
				}
			}
			if (player.getHandValue() < 21) {
				if (dealer.isBlackjack()) {
					System.out.println("Dealer has Blackjack. Dealer wins!" + dealer.getHandValue());
				} else {
					System.out.println("Would you like to hit or hold? \n Press 1: hit \n Press 2: hold");
					int userSelection = sc.nextInt();

					if (userSelection == 1) {
						System.out.println("You've chosen to hit!");
						player.getHand().addCard(deck);
						player.viewPlayerHand();
					} else {
						System.out.println("You've chosen to hold.");
						running = false;
					}
				}
			}
		}

	}

//	
//	public Player determineWinner() {
//
//		return theWinner;
//	}

}
