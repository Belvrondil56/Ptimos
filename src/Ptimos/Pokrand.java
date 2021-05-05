package Ptimos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pokrand extends Ptimos {

	public ArrayList<String> main = new ArrayList<String>(); // main de cartes sortis par le Pokrand quand il fait une grosse attaque
	private int damage; // Variable indicateur de degats, plus il est grand, plus les cons�quences sont d�sastreuses

	/*
	 * Constructeur de Pokrand
	 *
	 */
	public Pokrand() {
		this.dominance = 80; // Fixe la dominance � 80
		this.stress = 50; // Fixe le stress � 50
		this.name = "Pokrand"; // Donne le nom Pokrand au Pokrand
	}
	
	/*
	 * M�thode int pokrandSpecialMagicAttack
	 * 
	 * G�re l'attaque magique renforc�e du Pokrand
	 * 
	 * Plus la main est forte, plus l'effet est puissant
	 * 
	 */
	public int pokrandSpecialMagicAttack(Ptimos ptimo, Juliette juju) {

		Card deck = new Card(); // Cr�� l'objet Card qui est un deck
		deck.shuffledList(); // M�lange le deck
		main = deck.drawCards(main); // Pioche les 5 premi�res cartes du deck
		Combo hand = new Combo(main); // Cr�� l'objet main qui prend les 5 cartes en param�tre
		damage = 0;
		hand.afficherMain(); // Permet au Pokrand de montrer sa main de cartes
		hand.orderCards(); // Range sa main dans l'ordre croissant
		hand.orderCardsValue(hand.getValueCard(main)); // Range la main dans l'ordre croissant de valeur
		
		int bestHandValue = hand.bestCombo(main); // Cherche la meilleure combinaison de cartes
		main.clear(); // Vide la main
		if (bestHandValue >= 7) {
			System.out.println("Pokrand vous assome et lib�re tous les Ptimos !");
			System.out.println("Vous avez perdu ! haha !");
			return damage = 5;
		} else if (bestHandValue >= 4 && bestHandValue < 7) {
			System.out.println("Pokrand fuit !");
			return damage = 4;
		} else if (bestHandValue == 2 || bestHandValue == 3) {
			System.out.println("Attaque magique");
			return damage = 3;
		}
		ptimo.setDominance(ptimo.getDominance() - 10); //R�duit la dominance du Pokrand de 10
		return damage = 0;

	}
	
	/*
	 * M�thode void magicAttack (juju et ptimo en argument comme les autres m�thodes)
	 * Basse la vie de Juliette de 35
	 * R�duit le Stress du Ptimo qui attaque de 30
	 * Remet la dominance du Ptimo qui a attaqu� � 50 si elle �tait de 100
	 * 
	 */
	public void magicAttack(Juliette juju, Ptimos ptimo) {
		
		
		juju.setLife(juju.getLife()-45);
		ptimo.setStress(ptimo.getStress()-30);
		ptimo.setDominance(50);
		System.out.println(ptimo.name + " vous inflige un puissant burst gr�ce � sa puissante magie !");
	}


	
}
