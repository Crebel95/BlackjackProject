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
		BlackjackApp bja = new BlackjackApp();
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

		System.out.println("Dealer: ");
		System.out.println(dealer.getHand().getCard(0));

		System.out.println(" ");
		System.out.println("Player: ");
		player.viewPlayerHand();
		player.printHandValue();

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

		while (running) {

			if (player.getHandValue() > 21) {
				running = false;
			}

			if (player.getHandValue() < 21) {
				System.out.println("");
				System.out.println("Would you like to hit or hold? \n Press 1: hit \n Press 2: hold");
				int userSelection = sc.nextInt();

				if (userSelection == 1) {
					System.out.println("You've chosen to hit!");
					player.getHand().addCard(deck);
					System.out.println(" ");
					player.viewPlayerHand();
					player.printHandValue();
					System.out.println("--------------------------------");
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

		}

		do {
			dealer.getHand().addCard(deck);

		} while (dealer.getHandValue() < 17);

		bja.determineWinner(dealer, player);
	}

	public Player determineWinner(Dealer dealer, Player player) {
		Player theWinner = null;
		if (dealer.isBust()) {
			System.out.println("Dealer busts! Player Wins!");
			dealer.viewPlayerHand();
			dealer.printHandValue();
			System.out.println(" ");
			player.viewPlayerHand();
			player.printHandValue();
			theWinner = player;
		} else if (player.isBust()) {
			System.out.println("Player busts! Dealer Wins!");
			dealer.viewPlayerHand();
			dealer.printHandValue();
			System.out.println(" ");
			player.viewPlayerHand();
			player.printHandValue();
			theWinner = dealer;
		} else if (player.getHandValue() > dealer.getHandValue()) {
			System.out.println("Player wins!");
			dealer.viewPlayerHand();
			dealer.printHandValue();
			System.out.println(" ");
			player.viewPlayerHand();
			player.printHandValue();
			theWinner = player;
		} else if (dealer.getHandValue() > player.getHandValue()) {
			System.out.println("Dealer wins!");
			dealer.viewPlayerHand();
			dealer.printHandValue();
			System.out.println(" ");
			player.viewPlayerHand();
			player.printHandValue();
			theWinner = dealer;
		} else if (player.getHandValue() == dealer.getHandValue()) {
			System.out.println("Push! Game ends in a tie!");
			dealer.viewPlayerHand();
			dealer.printHandValue();
			System.out.println(" ");
			player.viewPlayerHand();
			player.printHandValue();
			theWinner = player;
		} else {
			System.out.println("Both players bust!");
			theWinner = null;
		}

		return theWinner;
	}

}
