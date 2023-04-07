package com.skilldistillery.blackjack.test;

import java.util.Scanner;

import com.skilldistillery.blackjack.entities.Card;
import com.skilldistillery.blackjack.entities.Deck;

public class DealerTest {

	public static void main(String[] args) {
		DealerTest dealer = new DealerTest();
		dealer.run();
	}

	public void run() {
		Deck deck = new Deck();
		deck.shuffle();
		Scanner sc = new Scanner(System.in);
		int numCardsInDeck = deck.checkDeckSize();
		System.out.println("Current number of cards in deck: " + numCardsInDeck);

		System.out.println("How many cards would you like?");
		int numCardsIWant = sc.nextInt();
		sc.nextLine();

		if (numCardsIWant <= numCardsInDeck) {
			int sumOfCards = 0;
			for (int cardNumDealt = 0; cardNumDealt < numCardsIWant; cardNumDealt++) {
				Card dealtCard = deck.dealCard();
				sumOfCards += dealtCard.getRank().getValue();
				System.out.println("You now have: " + dealtCard + " sum of cards " + sumOfCards + " "
						+ deck.checkDeckSize() + " cards left");
			}
		} else {
			System.out.println("Go play somewhere else!");
		}

		sc.close();
	}

}
