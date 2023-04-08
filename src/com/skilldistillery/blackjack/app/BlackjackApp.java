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

		dealer.viewPlayerHand();
		dealer.printHandValue();
		System.out.println(" ");
		player.viewPlayerHand();
		player.printHandValue();


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
				break;
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
			bja.determineWinner(dealer,player);

		}

	}

	public Player determineWinner(Dealer dealer, Player player) {
		Player theWinner = null;
		if (dealer.isBust()) {
			System.out.println("Dealer busts! Player Wins!");
			theWinner = player;
		}
		if (player.isBust()) {
			System.out.println("Player busts! Dealer Wins!");
			theWinner = dealer;
		}
		if (player.getHandValue() > dealer.getHandValue()) {
			System.out.println("Player wins!");
			theWinner = player;
		}
		if (dealer.getHandValue() > player.getHandValue()) {
			System.out.println("Dealer wins!");
			theWinner = dealer;
		}
		if (player.getHandValue() == dealer.getHandValue()) {
			System.out.println("Push! Game ends in a tie!");
			theWinner = player;
		}
		
		
		return theWinner;
	}

}
