package Ptimos;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Combat {

	private static Combat partie; // Déclaration variable partie de type Combat
	private int distance; // Distance qui sépare Juliette du Ptimo qui apparait
	private int alea; // Nombre aleatoire qui sert dans plusieurs fonction
	private int apparition; // Variable pour le taux d'apparition des Ptimos
	private int legendaryApparition; // Variable pour le taux d'apparition du Pokrand
	private int numberCages = 10; // Nombres de cages que possède Juliette
	ArrayList<String> ptimodex = new ArrayList(); // Pokedex version Ptimos
	public boolean legendaryCondition = false; // Variable qui vérifie si les conditions sont réunies pour que le
												// pokrand apparaisse
	ArrayList<String> main = new ArrayList<String>(); // Main de 5 cartes tirées par le Pokrand
	private int magicDamageResult; // Résultat obtenu par la fonction de l'attaque puissante du Pokrand
	private ArrayList<String> ptimodexComplete = new ArrayList<String>(); // Ptimodex qu'il faut obtenir à la fin
	private boolean captured; // Variable qui dit si le Ptimo a été capturé ou non

	/*
	 * Constructeur de l'objet Combat
	 * 
	 * Ajoute au Ptimodex les trois Ptimos à obtenir
	 * 
	 */

	private Combat() {
		ptimodexComplete.add("Sacbleu");
		ptimodexComplete.add("Pyralia");
		ptimodexComplete.add("Pokrand");
	}

	/*
	 * Instanciation de l'objet Combat en Singleton
	 * 
	 * Si partie existe, la méthode renvoie partie sinon elle le créé
	 * 
	 */

	static Combat getCombatInstance() {
		if (partie == null) {
			return new Combat();
		}
		return partie;
	}

	/**
	 * Méthode void play
	 * 
	 * @param juju = Juliette
	 * 
	 * Annonce Juliette avec ses points de vie et lance la fonction de rencontre
	 * entre Juliette et un Ptimo
	 * 
	 */

	public void play(Juliette juju) {

		System.out.println(juju.name + " (" + juju.getLife() + "pv)");

		apparition(juju);

	}

	/*
	 * Getters et Setters de la distance entre Juliette et le Ptimo
	 */

	public int getDistance() {
		return this.distance;
	}

	public void setDistance(int dis, Juliette juju) {

		if (dis > 15) {
			System.out.println("Le Ptimo s'est échappé ! Dommage !");
			apparition(juju);
		}
		this.distance = dis;
	}

	/**
	 * Méthode void apparition
	 * 
	 * @param juju = Juliette
	 * 
	 * Gère l'apparition d'un Ptimo : Les Sacbleu ont 2x plus de chances
	 * d'apparaître par rapport à un Pyralia
	 * 
	 * Si le sac de Juliette contient 5 Ptimos et qu'il y a au moins un Pyralia et
	 * un Sacbleu, le Pokrand peut apparaître
	 * 
	 */

	private void apparition(Juliette juju) {

		if (ptimodex.containsAll(ptimodexComplete) == true) {
			finDeJeu(juju);
		} else {

		apparition = (int) (Math.random() * 100);
		legendaryApparition = (int) (Math.random() * 100);
		this.distance = 8 + (int) (Math.random() * 8);
		bag(ptimodex);

		if (legendaryCondition == true && ptimodex.size() >= 5) {
			if (legendaryApparition > 20) {
				Ptimos pokrand = new Pokrand();
				System.out.format("Un %s se cache dans ce bois, voule-vous le capturer ? \nOui [1] \nNon [2] \n",
						pokrand.name);
				Scanner userTapping = new Scanner(System.in);
				int str = userTapping.nextInt();
				if (str == 1) {
					approach(pokrand, juju);
				} else {
					distance = 8;
					apparition(juju);
				}
			}
		}

		if (apparition > 35) {

			Ptimos sacbleu = new Sacbleu();
			System.out.format("Un %s se cache dans ce bois, voulez-vous le capturer ? \nOui [1] \nNon [2] \n",
					sacbleu.name);
			Scanner userTapping = new Scanner(System.in);
			int str = userTapping.nextInt();
			if (str == 1) {
				approach(sacbleu, juju);
			} else {
				distance = 8;
				apparition(juju);
			}
		} else {
			Ptimos pyralia = new Pyralia();
			System.out.format("Un %s se cache dans ce bois, voulez-vous le capturer ? \nOui [1] \nNon [2] \n",
					pyralia.name);
			Scanner userTapping = new Scanner(System.in);
			int str = userTapping.nextInt();
			if (str == 1) {
				approach(pyralia, juju);
			} else {
				distance = 8;
				apparition(juju);
			}

		}
		}
	}

	/**
	 * Méthode void approach
	 * 
	 * @param1 target = Ptimo apparu et approché
	 * 
	 * @param2 juju = la fameuse Juliette
	 * 
	 * Boucle While qui permet de repéter un tour de jeu tant que Juliette à plus de
	 * 0 de vie
	 * 
	 * Vérifie si il reste des cages. Si 0 cages, propose d'en recharger 10 mais
	 * coûte 10pv
	 * 
	 * Vérifie si le sac est plein. Si oui, propose de le vider mais coûte 10pv
	 * 
	 * Propose 6 choix à Juliette
	 * 
	 * Choix 0 : Laisse le Ptimo partir et appel la fonction apparition Choix 1 :
	 * Appel la fonction Observe de la classe Juliette Choix 2 : Appel la fonction
	 * getCloser de la classe Combat Choix 3 : Appel la fonction launchFriandise de
	 * la classe Juliette Choix 4 : Appel la fonction dance de la classe Juliette
	 * Choix 5 : Appel la fonction launchSpike de la classe Juliette Choix 6 : Appel
	 * la fonction drinkPotion de la classe Juliette
	 * 
	 */

	public void approach(Ptimos target, Juliette juju) {

		System.out.println("Il reste " + juju.getLife() + " pdv à Juju");

		while (juju.getLife() > 0) {

			if (numberCages == 0) {
				System.out.println("Vous n'avez plus de cages ! Voulez-vous en racheter ? \nOui [1] (coût : 10pv) \n");
				Scanner userTappingCage = new Scanner(System.in);
				int strCage = userTappingCage.nextInt();
				if (strCage == 1) {
					numberCages = 10;
					juju.setLife(juju.getLife() - 10);
				}
			}
			if (ptimodex.size() == 10) {
				System.out.println(
						"Vous n'avez plus de place dans votre sac ! Voulez-vous relacher vos Ptimos ? \nOui [1] (coût : 10pv) \nNon [2] \n");
			}

			if (distance < 1) {
				capture(target, juju);
			}

			System.out.println("Vous êtes à " + this.distance + " mètres d'un " + target.name
					+ " sauvage, que souhaitez-vous faire ?\n");
			System.out
					.println("[1] - Observer \n[2] - Se rapprocher \n[3] - Lancer une friandise " + juju.getFriandises()
							+ "\n" + "[4] - Faire une danse impressionnante \n[5] - Tirer une flechette endormante "
							+ juju.getFlechette() + " \n[6] - Boire une potion \n" + "\n[0] - Laisser le " + target.name
							+ " en liberté");

			Scanner userTapping = new Scanner(System.in); // Permet au joueur d'entrer un chiffre
			int str = userTapping.nextInt(); // Récupère l'entrée du joueur

			switch (str) {
			case 0:
				distance = 8;
				apparition(juju);
				break;
			case 1:
				juju.observe(target);
				break;
			case 2:
				getCloser(juju);
				break;
			case 3:
				juju.launchFriandise(target, this.distance, juju.getFriandises());
				;
				break;
			case 4:
				juju.dance(target);
				break;
			case 5:
				captured = juju.launchSpike(target, juju.getFlechette());
				System.out.println(captured);
				break;
			case 6:
				juju.drinkPotion();
				break;
			}

			if (distance < 1 || captured == true) {
				capture(target, juju);
			}
			
			reaction(target, distance, juju);

		}

		/*
		 * Si Juliette à 0 de vie, affiche le message de défaite, recharge toutes les
		 * variables, vide le sac et fait rejouer le joueur
		 */

		System.out.println("Perdu ! T'es mauvais Jack ! T'es mauvais !");
		juju.setLife(100);
		juju.setFlechette(15);
		juju.setFriandises(30);
		numberCages = 10;
		ptimodex.clear();
		apparition(juju);
	}

	/**
	 * Méthode void reaction
	 * 
	 * @param1 target = Ptimo ciblé par Juliette
	 * 
	 * @param2 distance = distance entre Juliette et le Ptimo
	 * 
	 * @param3 juju = Juliette l'unique, la vraie
	 * 
	 * Si la Dominance du Ptimo est égale à 100, déclanche son attaque magique
	 * 
	 * Si le Ptimo est un Pokrand, déclanche les réactions possibles du Pokrand
	 * Sinon déclanche les réactions possibles du Ptimo
	 * 
	 */

	public void reaction(Ptimos target, int distance, Juliette juju) {

		if (target.getDominance() == 100) {
			target.magicAttack(juju, target);
		}

		if (target.name == "Pokrand") {

			ptimosReactionPokrand(target, distance, juju);

		} else {
			ptimosReaction(target, distance, juju);
		}

	}

	/**
	 * Methode vois resultMAPokrand
	 * 
	 * @param1 et @param2 déjà dit plus haut
	 * 
	 * Récupère le résultat de la fonction pokrandSpecialMagicAttack() de l'objet
	 * Pokrand Si 5 : Le Pokrand libère les Ptimos capturés et s'enfuit Si 4 : Le
	 * pokrand s'enfuit Si 3 : Le pokrand lance une attaque magique basique
	 * 
	 */

	public void resultMAPokrand(Ptimos target, Juliette juju) {
		int valueMagicAttack = target.pokrandSpecialMagicAttack(target, juju);
		switch (valueMagicAttack) {
		case (5):
			ptimodex.clear();
			apparition(juju);
			break;
		case (4):
			apparition(juju);
			break;
		case (3):
			target.magicAttack(juju, target);
			break;
		}
	}

	/**
	 * 
	 * Méthodes qui determines les actions des Ptimos en fonction de la distance, de
	 * la valeur du Stress et de la Dominance des Ptimos. Fait à peur près à partir
	 * de l'énoncé
	 * 
	 */

	public void ptimosReactionPokrand(Ptimos target, int distance, Juliette juju) {
		if (distance < 3) {
			distance3Pokrand(target, juju);
		} else if (distance > 10) {
			distance10Pokrand(target, distance, juju);
		} else {
			getAwayOrAttackOrRoarPokrand(target, distance, juju);
		}
	}

	public void distance3Pokrand(Ptimos target, Juliette juju) {
		alea = (int) (Math.random() * 100);
		if (alea > 80) {
			target.attack(juju);
		} else if (alea > 40 && alea < 71) {
			target.roar();
		} else {
			resultMAPokrand(target, juju);
		}
	}

	public void distance10Pokrand(Ptimos target, int distance, Juliette juju) {
		alea = (int) (Math.random() * 100);
		if (alea > 50) {
			getAway(distance, juju);
		} else if (alea > 20 && alea < 51) {
			getAwayOrAttackOrRoar(target, distance, juju);
		} else {
			resultMAPokrand(target, juju);
		}
	}

	private void getAwayOrAttackOrRoarPokrand(Ptimos target, int distance, Juliette juju) {

		alea = (int) Math.random() * 100;
		if (alea > 70) {
			resultMAPokrand(target, juju);
		} else {
			if (target.getStress() > 74 && target.getStress() < 85 && target.getDominance() > 30
					&& target.getDominance() < 56) {
				alea = (int) Math.random() * 100;
				if (alea < 51) {
					target.attack(juju);
				} else {
					getAway(distance, juju);
				}
			} else if (target.getStress() > 54 && target.getStress() < 75 && target.getDominance() > 0
					&& target.getDominance() < 30) {
				alea = (int) Math.random() * 100;
				if (alea < 51) {
					target.attack(juju);
				} else {
					getAway(distance, juju);
				}
			} else if (target.getDominance() > 55) {
				alea = (int) Math.random() * 100;
				if (alea > 74) {
					target.attack(juju);
				} else {
					getAway(distance, juju);
				}
			}
		}
	}

	public void ptimosReaction(Ptimos target, int distance, Juliette juju) {
		if (distance < 3) {
			distance3(target, juju);
		} else if (distance > 10) {
			distance10(target, distance, juju);
		} else {
			getAwayOrAttackOrRoar(target, distance, juju);
		}
	}

	public void distance3(Ptimos target, Juliette juju) {
		alea = (int) (Math.random() * 100);
		if (alea > 80) {
			target.attack(juju);
		} else if (alea > 40 && alea < 71) {
			target.roar();
		} else {
			target.magicAttack(juju, target);
		}
	}

	public void distance10(Ptimos target, int distance, Juliette juju) {
		alea = (int) (Math.random() * 100);
		if (alea > 50) {
			getAway(distance, juju);
		} else if (alea > 20 && alea < 51) {
			getAwayOrAttackOrRoar(target, distance, juju);
		} else {
			target.magicAttack(juju, target);
		}
	}

	private void getAwayOrAttackOrRoar(Ptimos target, int distance, Juliette juju) {

		alea = (int) Math.random() * 100;
		if (alea > 70) {
			target.magicAttack(juju, target);
		} else {
			if (target.getStress() > 74 && target.getStress() < 85 && target.getDominance() > 30
					&& target.getDominance() < 56) {
				alea = (int) Math.random() * 100;
				if (alea < 51) {
					target.attack(juju);
				} else {
					getAway(distance, juju);
				}
			} else if (target.getStress() > 54 && target.getStress() < 75 && target.getDominance() > 0
					&& target.getDominance() < 30) {
				alea = (int) Math.random() * 100;
				if (alea < 51) {
					target.attack(juju);
				} else {
					getAway(distance, juju);
				}
			} else if (target.getDominance() > 55) {
				alea = (int) Math.random() * 100;
				if (alea > 74) {
					target.attack(juju);
				} else {
					getAway(distance, juju);
				}
			}
		}
	}

	/**
	 * Méthode void capture
	 * 
	 * @param target, juju (comme d'habitude)
	 * 
	 * Enlève une cage à Juliette Affiche le message de capture Redirie sur la
	 * méthode apparition
	 */

	public void capture(Ptimos target, Juliette juju) {
		numberCages--;
		System.out
				.println("Vous avez capturé le " + target.name + " ! Bravo ! Il vous reste " + numberCages + " cages.");
		ptimodex.add(target.name);
		apparition(juju);
	}

	/**
	 * Méthode void getCloser
	 * 
	 * @param juju = Juliette
	 * 
	 * Réduit de 3 à 8 mètres la distance
	 */

	public void getCloser(Juliette juju) {

		Random nbreAlea = new Random();
		int alea = nbreAlea.nextInt(5) + 3;
		setDistance(getDistance() - alea, juju);
	}

	/**
	 * Méthode void getAway
	 * 
	 * @param1 distance = distance
	 * 
	 * @param2 juju = Juliette
	 * 
	 * Augmente la distance de 2 à 5 mètres
	 */

	public void getAway(int distance, Juliette juju) {

		Random nbreAlea = new Random();
		int alea = nbreAlea.nextInt(3) + 2;
		distance += alea;
		System.out.println("Le ptimo s'éloigne de " + alea + "\n");
		setDistance(distance, juju);
	}

	/**
	 * Méthode void bag
	 * 
	 * @param ptimodex = Contient les ptimos capturés
	 * 
	 * Si le sac est vide, il le dit Affiche les Ptimos capturés Vérifie si la
	 * condition d'apparition du Pokrand est true ou false
	 */

	public void bag(ArrayList ptimodex) {
		if (ptimodex.size() == 0) {
			System.out.println("Votre sac ne contient pas de Ptimos");
		} else {
			System.out.println("Votre sac contient :" + ptimodex);
			if (ptimodex.contains("Sacbleu") && ptimodex.contains("Pyralia")) {
				legendaryCondition = true;
			}
		}
	}
	
	/**
	 * Méthode finDeJeu
	 * 
	 * Censée arrêter le jeu mais pas réussi
	 */

	private void finDeJeu(Juliette juju) {
		System.out.println(
				"Bravo ! Vous avez réussi à capturer tous les Ptimos ! Vous pouvez enfin faire la recette du ragoût aux Ptimos !!\n");
		System.out.println("Voulez-vous refaire une partie ?\nOui [1] \nNon [2] \n");
		Scanner userTapping = new Scanner(System.in);
		int str = userTapping.nextInt();
		if (str == 1) {
			distance = 8;
			ptimodex.clear();
			apparition(juju);
		} else if (str == 2){
			System.out.println("La partie est finie");
		}
	}
}

