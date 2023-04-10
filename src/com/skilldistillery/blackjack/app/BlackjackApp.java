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
		System.out.println("[Card is upsidedown]  " + "[" + dealer.getHand().getCard(0) + "]");
		System.out.println("Dealer's visible card value: " + dealer.getHand().getCard(0).getValue().getValue());
		System.out.println(" ");
		System.out.println("Player: ");
		player.viewPlayerHand();
		player.printHandValue();

		if (player.isBlackjack() || dealer.isBlackjack()) {

			if (player.isBlackjack() && !(dealer.isBlackjack())) {
				printLine();
				System.out.println("Player beats Dealer with Blackjack!");
				subMenu();
			}

			if (dealer.isBlackjack() && !(player.isBlackjack())) {
				printLine();
				System.out.println("Dealer beats Player with Blackjack!");
				dealer.viewPlayerHand();
				subMenu();
			}
			if (dealer.isBlackjack() && (player.isBlackjack())) {
				printLine();
				System.out.println("Push! Both Player and Dealer have Blackjack!");
				dealer.viewPlayerHand();
				subMenu();
			}

		}

		while (running) {

			if (player.getHandValue() > 21) {
				determineWinner(dealer, player);
				subMenu();
			}

			if (player.getHandValue() < 21) {
				space();
				System.out.println("Would you like to hit or hold? \n Press 1: hit \n Press 2: hold");
				int userSelection = sc.nextInt();

				if (userSelection == 1) {
					printLine();
					System.out.println("You've chosen to hit!");
					player.getHand().addCard(deck);
					space();
					player.viewPlayerHand();
					player.printHandValue();
				}
				if (userSelection == 2) {
					printLine();
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
		System.out.println("----------End Result----------");
		dealer.viewPlayerHand();
		dealer.printHandValue();
		space();
		player.viewPlayerHand();
		player.printHandValue();

		bja.determineWinner(dealer, player);
		subMenu();
	}

	public Player determineWinner(Dealer dealer, Player player) {
		Player theWinner = null;

		if (player.isBust() && dealer.isBust()) {
			space();
			System.out.println("Both Player and Dealer bust!");

			theWinner = player;
		} else if (dealer.isBust()) {
			space();
			System.out.println("Dealer busts! Player Wins!");

			theWinner = player;
		} else if (player.isBust()) {
			space();
			System.out.println("Player busts! Dealer Wins!");

			theWinner = dealer;
		} else if (player.getHandValue() > dealer.getHandValue()) {
			space();
			System.out.println("Player wins!");

			theWinner = player;
		} else if (dealer.getHandValue() > player.getHandValue()) {
			space();
			System.out.println("Dealer wins!");

			theWinner = dealer;
		} else if (player.getHandValue() == dealer.getHandValue()) {
			space();
			System.out.println("Push! Game ends in a tie!");

			theWinner = player;
		}

		return theWinner;
	}

	public void subMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Would you like to deal another hand? Yes) Press: 1 or (No) Press: 2");
		int userSelection = sc.nextInt();

		if (userSelection == 1) {
			launch();
		}

		if (userSelection == 2) {
			sc.close();
			System.out.println("Goodbye!");
		}
		if (userSelection != 1 && userSelection != 2) {
			System.err.println(
					"Invalid option. Your selection must be either \"1\" or \"2\": \n Press 1: to hit \n Press 2: to hold ");
		}
	}
	public void printLine() {
		System.out.println("--------------------------------------------");
	}
	
	public void space() {
		System.out.println(" ");
	}

}
