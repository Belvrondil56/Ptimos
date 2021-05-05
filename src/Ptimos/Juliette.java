package Ptimos;

import java.text.DecimalFormat;
import java.util.Random;

public class Juliette {

	public String name; // Nom de Juliette
	private int life; // Vie de Juliette
	private int nbreCages; // Nombres de cages que poss�de Juliette
	private int friandises; // Nombre de friandises que poss�de Juliette
	private int flechette; // Nombre de flechette que poss�de Juliette
	public int distanceMax = 15; // Distance maximum ou l'on peut voir appara�tre un Ptimo
	public int pourcentageCaptureMax = 70; // Pourcentage Max d'attraper un Ptimo avec une friandise
	public int pourcentageCaptureMin = 10; // Pourcentage Min d'attraper un Ptimo avec une friandise
	public Combat partie; // D�claration de la variable partie de type Combat
	public static Juliette juju; // D�claration de la variable juju de type Juliette

	/**
	 * Constructeur de Juliette
	 * 
	 * @param partie     = Instanciation de partie de type Combat unique car
	 *                   Singleton
	 * @param name       = pr�nom de Juliette
	 * @param life       = montant de la vie de Juliette
	 * @param nbreCages  = nombre de cages que poss�de Juliette
	 * @param friandises = nombre de friandises que poss�de Juliette
	 * @param flechette  = nombre de flechettes que poss�de Juliette
	 * 
	 */

	public Juliette() {
		partie = Combat.getCombatInstance();
		name = "Juliette";
		life = 100;
		nbreCages = 10;
		friandises = 30;
		flechette = 15;
	}

	/*
	 * Instanciation de l'objet Juliette en Singleton
	 * 
	 * Si l'on instancie l'objet Juliette et qu'il existe d�j�, il renvoie juju
	 * sinon il le cr��
	 * 
	 */

	public static Juliette getJulietteInstance() {
		if (juju == null) {
			juju = new Juliette();
			return juju;
		}
		return juju;
	}

	/*
	 * Getters et Setters de Juliette
	 */

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getNbreCages() {
		return nbreCages;
	}

	public void setNbreCages(int nbreCages) {
		this.nbreCages = nbreCages;
	}

	public int getFriandises() {
		return friandises;
	}

	public void setFriandises(int friandises) {
		this.friandises = friandises;
	}

	public int getFlechette() {
		return flechette;
	}

	public void setFlechette(int flechette) {
		this.flechette = flechette;
	}

	// ------------------------------------------------------------------------------------------------------------------

	/**
	 * M�thode void Observe
	 * 
	 * @param target = Ptimo qui est en combat avec juliette
	 * 
	 * Renvoie sous forme de Sysout les stats de Stress et de Dominance du Ptimo
	 * re�u en param�tre
	 *
	 */

	public void observe(Ptimos target) {

		String etatStress = "rien"; // Etat de stress du Ptimo

		if (target.stress < 26) {
			etatStress = "d�tendu";
		} else if (target.stress > 25 && target.stress <= 50) {
			etatStress = "m�fiant";
		} else if (target.stress > 25 && target.stress < 75) {
			etatStress = "nerveux";
		} else if (target.stress >= 75) {
			etatStress = "paniqu�";
		}

		String etatDomi = "rien"; // Etat de Dominance du Ptimo

		if (target.dominance < 26) {
			etatDomi = "inoffensif";
		} else if (target.dominance > 25 && target.dominance <= 50) {
			etatDomi = "neutre";
		} else if (target.dominance > 50 && target.dominance < 75) {
			etatDomi = "f�roce";
		} else if (target.dominance >= 75) {
			etatDomi = "dangereux";
		}

		System.out.println(target.stress + " de stress per�u ! " + target.name + " est " + etatStress);
		System.out.println(target.dominance + " de dominance per�u ! " + target.name + " est " + etatDomi);

	}

	/**
	 * M�thode void launchFriandise
	 * 
	 * @param1 target = Ptimo cibl� par Juliette
	 * 
	 * @param2 distance = distance qui s�pare Juliette du Ptimo
	 * 
	 * @param3 friandises = Nombres de friandises restantes de Juliette
	 * 
	 * V�rfie en premier lieu si il reste des friandises � Juliette,
	 * 
	 * Si oui, calcul le ratio entre la distance et le pourcentage de chance de
	 * toucher un Ptimo
	 * 
	 * Si le Ptimo est touch�, le stress du Ptimo baisse de 30
	 * 
	 */

	public void launchFriandise(Ptimos target, int distance, int friandises) {

		this.friandises = friandises;
		if (friandises == 0) {
			System.out.println("Vous n'avez plus de friandises");
		} else {
			this.friandises--;
			int range = pourcentageCaptureMax - pourcentageCaptureMin;
			double ratioDistance = distance * 100 / distanceMax;
			double ratioPourcentage = ratioDistance * range / 100;
			ratioPourcentage += pourcentageCaptureMin;
			double chanceNecessaire = ratioPourcentage + pourcentageCaptureMax - 2 * ratioPourcentage
					+ pourcentageCaptureMin;

			int alea = (int) ((Math.random()) * 100);
			if (alea < chanceNecessaire) {
				System.out
						.println("Votre lancer est un succ�s ! " + target.name + " � l'air beaucoup moins stress�...");
				target.stress -= 30;
			}
		}

	}

	/**
	 * M�thode Boolean launchSpike
	 * 
	 * @param1 target = Ptimo cibl� par Juliette
	 * 
	 * @param2 flechettes = nombre de flechette restantes � Juliette
	 * 
	 * V�rifie si il reste des flechettes � Juliette
	 * 
	 * Si oui, une chance sur 2 de toucher
	 * 
	 * Renvoie true ou false
	 * 
	 */

	public boolean launchSpike(Ptimos target, int flechettes) {

		this.flechette = flechettes;
		if (flechettes == 0) {
			System.out.println("Vous n'avez plus de flechettes");
		} else {
			this.flechette--;
			int num = (int) (Math.random() * 100);
			System.out.println(num);
			if (num > 51) {
				System.out.println(target.name + " est endormi !");
				return true;
			} else {
				System.out.println("Rat� ! Vous �tes tr�s mauvais lanceur !");
				return false;
			}
		}
		return false;
	}
	
	/**
	 * Methode void dance
	 * 
	 * @param1 target = Ptimo cibl� par Juliette
	 * 
	 * Tire un nombre entre 7 et 21
	 * 
	 * R�duit la Dominance du Ptimo cibl� par le nombre tir� pr�c�demment
	 * 
	 */

	public void dance(Ptimos target) {
		int num = 7;
		num = num + (int) (Math.random() * 21);
		System.out.println("Vous faites une danse tr�s impresionnante !");
		target.dominance -= num;
		System.out.println(target.name + " semble beaucoup moins f�roce que pr�c�demment...");
	}
	
	/**
	 * M�thode void drinkPotion
	 * 
	 * Faire boire une potion � Juliette et lui permet de remonter sa vie � 90pv
	 * 
	 */

	public void drinkPotion() {
		setLife(90);
	}

}
