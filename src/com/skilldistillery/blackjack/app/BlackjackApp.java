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
		dealer.printHandValue();
		System.out.println(" ");
		player.viewPlayerHand();
		player.printHandValue();

		// check cards for blackjack(player and dealer)... create 'if statement' for
		// both scenarios
		// if neither have blackjack, ask if they would like to hit or stand
		// dealer must draw if less than 17

		while (running) {

			if (player.isBust() || dealer.isBust()) {
				running = false;
			}

			if (player.isBlackjack() || dealer.isBlackjack()) {

				if (player.isBlackjack() && !(dealer.isBlackjack())) {
					System.out.println("Player beats Dealer with Blackjack!");
				}

				if (dealer.isBlackjack() && !(player.isBlackjack())) {
					System.out.println("Dealer beats Player with Blackjack!");
				}
				if (dealer.isBlackjack() && (player.isBlackjack())) {
					System.out.println("Push! Both Player and Dealer have Blackjack!");
				}
			}

			if (player.getHandValue() < 21) {

				System.out.println("Would you like to hit or hold? \n Press 1: hit \n Press 2: hold");
				int userSelection = sc.nextInt();

				if (userSelection == 1) {
					System.out.println("You've chosen to hit!");
					player.getHand().addCard(deck);
					dealer.viewPlayerHand();
					dealer.printHandValue();
					System.out.println(" ");
					player.viewPlayerHand();
					player.printHandValue();
				}
				if (userSelection == 2) {
					System.out.println("You've chosen to hold.");
					running = false;
				}
				if (userSelection != 1 && userSelection != 2) {
					System.err.println(
							"Invalid option. Your selection must be either \"1\" or \"2\": \n Press 1: to hit \n Press 2: to hold ");
				}

			}
			if (dealer.getHandValue() < 17) {
				dealer.getHand().addCard(deck);
				dealer.viewPlayerHand();
				dealer.printHandValue();

			}
			if (player.getHandValue() > 21) {
				System.out.println("Player busts! The Dealer Wins!");
			} else if (dealer.getHandValue() > 21) {
				System.out.println("Dealer busts! Player wins!");
			} else if (player.getHandValue() > dealer.getHandValue()) {
				System.out.println("Player wins with a score of: ");
				player.printHandValue();
				System.out.println("to");
				dealer.printHandValue();
			} else if (dealer.getHandValue() > player.getHandValue()) {
				System.out.println("Dealer wins with a score of: ");
				player.printHandValue();
				System.out.println("to");
				dealer.printHandValue();
			} else if (dealer.getHandValue() == player.getHandValue()) {
				System.out.println("Push! The Dealer and Player have tied.");
			}
		}

	}

	

}
