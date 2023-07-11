package com.skilldistillery.blackjack.app;

import java.util.Scanner;

import com.skilldistillery.blackjack.entities.BlackjackHand;
import com.skilldistillery.blackjack.entities.Dealer;
import com.skilldistillery.blackjack.entities.Deck;
import com.skilldistillery.blackjack.entities.Hand;
import com.skilldistillery.blackjack.entities.Player;

public class BlackjackApp {
	static Player bot;
	static Dealer dealer;
	static String playerName;
	static int playerCash = 500;
	static int playerBet;
	static Scanner sc;

	public static void main(String[] args) {
		BlackjackApp bja = new BlackjackApp();
		sc = new Scanner(System.in);
		System.out.println("Enter your name: ");
		playerName = sc.next();

		bja.launch();

	}

	public void launch() {
		playersBet();

		BlackjackApp bja = new BlackjackApp();
		Hand hand = new BlackjackHand();
		dealer = new Dealer("Dealer", hand);
		bot = new Player("Billy", new BlackjackHand());
		Player player = new Player(playerName, new BlackjackHand());
		Deck deck = new Deck();

		boolean running = true;

		deck.shuffle();
		dealer.getHand().addCard(deck);
		dealer.getHand().addCard(deck);
		player.getHand().addCard(deck);
		player.getHand().addCard(deck);
		bot.getHand().addCard(deck);
		bot.getHand().addCard(deck);

		System.out.println(dealer.getName() + ":");
		System.out.println("[Card is upsidedown]  " + "[" + dealer.getHand().getCard(0) + "]");
		System.out.println(dealer.getName() + "'s card value: " + dealer.getHand().getCard(0).getValue().getValue());
		System.out.println(" ");
		System.out.println(bot.getName() + ":");
		System.out.println("[Card is upsidedown]  " + "[" + bot.getHand().getCard(0) + "]");
		System.out.println(bot.getName() + "'s visible card value: " + bot.getHand().getCard(0).getValue().getValue());
		System.out.println(" ");
		System.out.println(playerName + ":");
		player.viewPlayerHand();
		player.printHandValue();
		System.out.println("Cash: $" + playerCash);

		if (player.isBlackjack() || dealer.isBlackjack()) {

			if (player.isBlackjack() && !(dealer.isBlackjack())) {
				printLine();
				System.out.println(playerName + " beats Dealer with Blackjack!");
				subMenu();
			}

			if (dealer.isBlackjack() && !(player.isBlackjack())) {
				printLine();
				System.out.println("Dealer beats " + playerName + " with Blackjack!");
				dealer.viewPlayerHand();
				subMenu();
			}
			if (dealer.isBlackjack() && (player.isBlackjack())) {
				printLine();
				System.out.println("Push! Both players have Blackjack!");
				dealer.viewPlayerHand();
				subMenu();
			}

		}

		while (running) {

			if (player.getHandValue() >= 22) {
				determineWinner(dealer, player);
				subMenu();
			}

			if (player.getHandValue() <= 20) {
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
			bot.getHand().addCard(deck);

		} while (bot.getHandValue() < 16);

		do {
			dealer.getHand().addCard(deck);

		} while (dealer.getHandValue() < 17);
		System.out.println("----------End Result----------");
		dealer.viewPlayerHand();
		dealer.printHandValue();
		space();
		bot.viewPlayerHand();
		bot.printHandValue();
		space();
		player.viewPlayerHand();
		player.printHandValue();

		bja.determineWinner(dealer, player);
		subMenu();
	}

	public Player determineWinner(Dealer dealer, Player player) {
		Player theWinner = null;
		space();

		if (player.isBust() && dealer.isBust()) {
			System.out.println("Both players bust!");

			theWinner = player;
		} else if (dealer.isBust()) {
			System.out.println("Dealer busts! " + playerName + " wins!");

			theWinner = player;
		} else if (player.isBust()) {
			System.out.println(playerName + " busts! Dealer wins!");

			theWinner = dealer;
		} else if (player.getHandValue() > dealer.getHandValue()) {
			System.out.println(playerName + " wins!");

			theWinner = player;
		} else if (dealer.getHandValue() > player.getHandValue()) {
			System.out.println("Dealer wins!");

			theWinner = dealer;
		} else if (player.getHandValue() == dealer.getHandValue()) {
			space();
			System.out.println("Push! Game ends in a tie!");

			theWinner = player;
		}

		if (player.getHandValue() == dealer.getHandValue()) {
			playerCash = playerCash + playerBet;
		} else if (theWinner.equals(player)) {
			playerCash = playerCash + (playerBet * 2);
		}

		System.out.println("Your Cash: $" + playerCash);
		System.out.println(botStatus());

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
			System.out.println("Goodbye!");
			System.exit(userSelection);
		}
		if (userSelection != 1 && userSelection != 2) {
			System.err.println(
					"Invalid option. Your selection must be either \"1\" or \"2\": \n Press 1: to hit \n Press 2: to hold ");
		}
	}

	public String botStatus() {
		String status = null;
		if (bot.getHandValue() > dealer.getHandValue()) {
			if (!bot.isBust()) {
				status = "Billy beats dealer";
			}

			else {
				status = "Billy busts";
			}
		} else if (dealer.isBust() && !bot.isBust()) {
			status = "Billy beats dealer";
		} else if (bot.getHandValue() == dealer.getHandValue()) {
			if (!bot.isBust()) {
				status = "Billy ties dealer";
			} else {
				status = "Billy busts!";
			}

		} else {
			status = "Billy loses";
		}
		return status;
	}

	private void playersBet() {
		boolean badBet = true;
		while (true) {

			System.out.println(
					" How much would you like to bet? (Whole numbers only) \n Current holdings: $" + playerCash);
			playerBet = sc.nextInt();
			if (playerBet > playerCash) {
				System.err.print("Invalid bet. Select a number within " + playerCash);
				playerBet = 0;
				playersBet();
			}
			playerCash = playerCash - playerBet;
			break;
		}
	}
	
	public void printLine() {
		System.out.println("--------------------------------------------");
	}

	public void space() {
		System.out.println(" ");
	}

}
