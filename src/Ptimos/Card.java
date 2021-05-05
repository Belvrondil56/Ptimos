package Ptimos;

import java.util.ArrayList;
import java.util.Collections;

public class Card {
	
	ArrayList<String> cards = new ArrayList<String>(); // ¨Paquet de cartes
	
	/**
	 * Méthode shuffledList
	 * 
	 * Permet de mélanger le paquet de carte
	 */
	
	public void shuffledList () {
		Collections.shuffle(cards); // Mélange le paquet de cartes
	}
	
	/**
	 * Constructeur de Card
	 * Ajoute chaque couleur de cartes
	 */
	
	Card () {
		addCard("s");
		addCard("q");
		addCard("d");
		addCard("c");
	}
	
	/**
	 * Méthode void addCard
	 * @param cards = paquet de carte
	 * 
	 * Créé le deck de cartes
	 */
	
	public void addCard(String cards) {
		for (int i=2; i<10; i++) {
			this.cards.add(i+cards);
		}
		this.cards.add("T"+cards);
		this.cards.add("J"+cards);
		this.cards.add("Q"+cards);
		this.cards.add("K"+cards);
		this.cards.add("A"+cards);
	}
	
	
	public void afficherCartes () {
		System.out.println(cards); // Affiche les cartes
	}
	
	/**
	 * Méthode ArrayList<String> drawCards
	 * @param main = main de 5 cartes
	 * @return La main de 5 cartes
	 */
	
	public ArrayList<String> drawCards (ArrayList<String> main){
		for (int i=0; i<5; i++) {
			main.add(cards.get(i));
		}
		return main;
	}
	
	
	
}
