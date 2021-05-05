package Ptimos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Combo {

	ArrayList<String> hand = new ArrayList<String>(); // Main de 5 cartes
	private String cardColor; // Couleur d'une carte
	private String cardNumber; // Valeur d'une carte
	private String aCard; // Une carte
	private int comboPokrand; // Valeur d'une combo
	
	/*
	 * Constructeur de l'objet Combo
	 */

	Combo(ArrayList<String> hand) {
		this.hand = hand;
	}
	
	/**
	 * Méthode int bestCombo
	 * 
	 * @param hand
	 * 
	 * Effectue un test qui va de la combo la plus fort e à la combo la moins forte
	 * Si il trouve une combo qui renvoie true, il attribue la valeur voulue à comboPokrand
	 * 
	 * @return la variable comboPokrand qui correspond à la valeur de la combo qui renvoie true
	 */

	public int bestCombo(ArrayList<String> hand) {
		
		System.out.println(hand);
		if (suit(getValueCard(hand)) == true && couleur() == true && getNumberCard(0).equals("T")) {
			System.out.println("Le pokrand vous sort une Quinte Flush Royale");
			comboPokrand = 10;
		} else if (suit(getValueCard(hand)) == true && couleur() == true) {
			System.out.println("Le pokrand vous sort une Quinte Flush");
			comboPokrand = 9;
		} else if (isSquare() == true) {
			System.out.println("Le pokrand vous sort un Carré");
			comboPokrand = 8;
		} else if (isFull() == true) {
			System.out.println("Le pokrand vous sort un full");
			comboPokrand = 7;
			return comboPokrand;
			} else if (couleur() == true) {
			System.out.println("Le pokrand vous sort une Couleur");
			comboPokrand = 6;
			return comboPokrand;
		} else if (suit(getValueCard(hand)) == true) {
			System.out.println("Le pokrand vous sort une Suite");
			comboPokrand = 5;
		} else if (isBrelan() == true) {
			System.out.println("Le pokrand vous sort un Brelan");
			comboPokrand = 4;
			return comboPokrand;
		} else if (isDoublePair() == true) {
			System.out.println("Le pokrand vous sort une Double Pair");
			comboPokrand = 3;
			return comboPokrand;
		} else if (isPair() == true) {
			System.out.println("Le pokrand vous sort une Paire");
			comboPokrand = 2;
			return comboPokrand;
		} else {
			comboPokrand = 1;
		}
		return comboPokrand;
	}
	
	/**
	 * Méthode String getCard
	 * 
	 * @param numCard = numéro de la carte que l'on veut dans la main
	 * @return
	 */

	public String getCard(int numCard) {
		return hand.get(numCard - 1);
	}
	
	/**
	 * 
	 * @param numCard
	 * @return
	 */

	public String getColorCard(int numCard) {
		return hand.get(numCard).substring(1, 2);
	}

	public String getNumberCard(int numCard) {
		return hand.get(numCard).substring(0, 1);
	}
	
	/**
	 * Méthode Liste getValueCard
	 * 
	 * Récupère la hauteur de chaque carte de la main et y assigne sa valeur en points
	 * 
	 * @param hand = récupère la liste en String de la main
	 * @return la liste en int des valeurs de carte de la main
	 */

	public ArrayList<Integer> getValueCard(ArrayList<String> hand) {
		ArrayList<Integer> valueCards = new ArrayList<Integer>();
		String numCard = " ";
		int value = 0;
		for (int i = 0; i < hand.size(); i++) {
			numCard = getNumberCard(i);
			switch (numCard) {
			case "A" -> value = 14;
			case "K" -> value = 13;
			case "Q" -> value = 12;
			case "J" -> value = 11;
			case "T" -> value = 10;
			case "9" -> value = 9;
			case "8" -> value = 8;
			case "7" -> value = 7;
			case "6" -> value = 6;
			case "5" -> value = 5;
			case "4" -> value = 4;
			case "3" -> value = 3;
			case "2" -> value = 2;
			}
			valueCards.add(value);
		}
		return valueCards;
	}

	public void orderCardsValue(ArrayList<Integer> valueCards) {
		Collections.sort(valueCards);
	}

	public void orderCards() {
		Collections.sort(hand);
	}
	
	/**
	 * Méthode boolean suit
	 * 
	 * Vérifie si il y a une suite
	 * 
	 * @return true ou false
	 */

	public boolean suit(ArrayList<Integer> suit) {
		int compteur = 1;
		for (int i = 0; i < suit.size() - 1; i++) {
			if (suit.get(i).equals(suit.get(i + 1) - 1)) {
				compteur++;
			}
		}
		if (compteur == 5) {
			return true;
		}
		return false;
	}
	
	/**
	 * Méthode afficherMain
	 * 
	 * @return Affiche une phrase où le Pokrand montre sa main
	 */

	public void afficherMain() {
		System.out.println(
				"Pokrand dégaine de l'interieur de sa veste un deck de carte !! Il pioche 5 cartes et vous montre sa main ! : "
						+ getCard(1) + " " + getCard(2) + " " + getCard(3) + " " + getCard(4) + " " + getCard(5));
	}
	
	/**
	 * Méthode boolean isPair
	 * 
	 * Vérifie si il y a une paire
	 * 
	 * @return true ou false
	 */

	public boolean isPair() {
		for (int i = 0; i < hand.size(); i++) {
			for (int j = i + 1; j < hand.size(); j++) {
				if (getNumberCard(i).equals(getNumberCard(j))) {
					return true;
				}
			}
			}
		
		return false;
	}
	
	/**
	 * Méthode boolean isDoublePair
	 * 
	 * Vérifie si il y a une double paire
	 * @return true ou false
	 */
	
	public boolean isDoublePair() {
		int compteur = 1;
		for (int i = 0; i < hand.size(); i++) {
			for (int j = i + 1; j < hand.size(); j++) {
				if (getNumberCard(i).equals(getNumberCard(j))) {
					compteur++;
				}
			}
			}
		for (int i = 1; i < hand.size(); i++) {
			for (int j = i + 1; j < hand.size(); j++) {
				if (getNumberCard(i).equals(getNumberCard(j))) {
					compteur++;
				}
			}
			}
		if (compteur==4) {
			return true;
		}
		return false;
	}
	
	/**
	 * Méthode boolean isBrelan
	 * 
	 * Vérifie si il y a un brelan
	 * @return true ou false
	 */

	public boolean isBrelan() {
		int compteur = 0;
		for (int i = 0; i < hand.size(); i++) {
			for (int j = i + 1; j < hand.size(); j++) {
				if (getNumberCard(i).equals(getNumberCard(j))) {
					compteur++;
				}
			}
		}
		if (compteur == 3) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Méthode boolean isSquare
	 * 
	 * Vérifie si il y a un carré
	 * @return true ou false
	 */

	public boolean isSquare() {
		int compteur = 0;
		for (int i = 0; i < hand.size(); i++) {
			for (int j = i + 1; j < hand.size(); j++) {
				if (getNumberCard(i).equals(getNumberCard(j))) {
					compteur++;
					System.out.println(compteur);
				}
			}
		}
		if (compteur == 8) {
			return true;
		}
		return false;
	}
	
	/**
	 * Méthode boolean isFull
	 * 
	 * Vérifie si il y a un full
	 * @return true ou false
	 */

	public boolean isFull() {
		int compteur = 0;
		for (int i = 0; i < hand.size(); i++) {
			for (int j = i + 1; j < hand.size(); j++) {
				if (getNumberCard(i).equals(getNumberCard(j))) {
					compteur++;
				}
			}
		}
		if (compteur == 4) {
			return true;
		}
		return false;

	}
	
	/**
	 * Méthode boolean couleur
	 * 
	 * Vérifie si il y a une couleur
	 * @return true ou false
	 */

	public boolean couleur() {
		int compteur = 1;
		int k = 0;
		for (int j = k + 1; j < hand.size(); j++) {
			if (getColorCard(j).equals(getColorCard(k))) {
				compteur++;
			}
		}
		if (compteur == 5) {
			return true;
		}
		return false;
	}

}
