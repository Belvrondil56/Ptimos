package Ptimos;

import java.util.Random;

public abstract class Ptimos {

	protected String name; // Nom du Ptimo
	protected int dominance; // Dominance du Ptimo
	protected int stress; // Stress du Ptimo
	int alea; // variable aléatoire

	/**
	 * Constructeur Ptimos
	 * VIDE
	 */
	
	public Ptimos() {
	}
	
	/**
	 * Méthode void roar
	 * 
	 * Fait rugir le Ptimo
	 * Augmente sa Dominance de 30
	 * Baisse son Stress de 25 
	 * 
	 */

	public void roar() {
		
		this.setDominance(this.getDominance() + 30);
		this.setStress(this.getStress() - 25);

		System.out.println(this.name + " fait un cri féroce qui le rend plus sûr de lui...");
	}
	
	/**
	 * Méthode void attack
	 * 
	 * @param1 juju = Juliette
	 * 
	 * Baisse la vie de Juliette de 15pv
	 */

	public void attack(Juliette juju) {
		juju.setLife(juju.getLife() - 15);
		System.out.println(this.name + " avance et vous attaque d'une violence inouïe..." + juju.getLife());
	}
	
	/*
	 * Méthode abstraite void magicAttack
	 * 
	 * @param1 juju = Juliette
	 * @param2 Ptimo = Ptimo
	 * 
	 */

	public abstract void magicAttack(Juliette juju, Ptimos ptimo);
	
	
	/*
	 * getters et Setters de la classe Ptimo (Dominance, Stress)
	 * Empeche 'avoir plus de 100 de stress et plus de 100 de Dominance
	 */

	public void setDominance(int dominance) {
		dominance = Math.max(dominance, 0);
		dominance = Math.min(dominance, 100);
		this.dominance = dominance;
	}

	public int getStress() {
		return stress;
	}

	public void setStress(int stress) {
		stress = Math.max(stress, 0);
		stress = Math.min(stress, 100);
		this.stress = stress;
	}

	public int getDominance() {
		return dominance;
	}
	
	/*
	 * Méthode abstraite int pokrandSpecialMagicAttack
	 * Utile seulement pour la classe Pokrand
	 */

	public abstract int pokrandSpecialMagicAttack(Ptimos ptimo, Juliette juju);

	

}
