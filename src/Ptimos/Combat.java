package Ptimos;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Combat {

	private static Combat partie; // D�claration variable partie de type Combat
	private int distance; // Distance qui s�pare Juliette du Ptimo qui apparait
	private int alea; // Nombre aleatoire qui sert dans plusieurs fonction
	private int apparition; // Variable pour le taux d'apparition des Ptimos
	private int legendaryApparition; // Variable pour le taux d'apparition du Pokrand
	private int numberCages = 10; // Nombres de cages que poss�de Juliette
	ArrayList<String> ptimodex = new ArrayList(); // Pokedex version Ptimos
	public boolean legendaryCondition = false; // Variable qui v�rifie si les conditions sont r�unies pour que le
												// pokrand apparaisse
	ArrayList<String> main = new ArrayList<String>(); // Main de 5 cartes tir�es par le Pokrand
	private int magicDamageResult; // R�sultat obtenu par la fonction de l'attaque puissante du Pokrand
	private ArrayList<String> ptimodexComplete = new ArrayList<String>(); // Ptimodex qu'il faut obtenir � la fin
	private boolean captured; // Variable qui dit si le Ptimo a �t� captur� ou non

	/*
	 * Constructeur de l'objet Combat
	 * 
	 * Ajoute au Ptimodex les trois Ptimos � obtenir
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
	 * Si partie existe, la m�thode renvoie partie sinon elle le cr��
	 * 
	 */

	static Combat getCombatInstance() {
		if (partie == null) {
			return new Combat();
		}
		return partie;
	}

	/**
	 * M�thode void play
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
			System.out.println("Le Ptimo s'est �chapp� ! Dommage !");
			apparition(juju);
		}
		this.distance = dis;
	}

	/**
	 * M�thode void apparition
	 * 
	 * @param juju = Juliette
	 * 
	 * G�re l'apparition d'un Ptimo : Les Sacbleu ont 2x plus de chances
	 * d'appara�tre par rapport � un Pyralia
	 * 
	 * Si le sac de Juliette contient 5 Ptimos et qu'il y a au moins un Pyralia et
	 * un Sacbleu, le Pokrand peut appara�tre
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
	 * M�thode void approach
	 * 
	 * @param1 target = Ptimo apparu et approch�
	 * 
	 * @param2 juju = la fameuse Juliette
	 * 
	 * Boucle While qui permet de rep�ter un tour de jeu tant que Juliette � plus de
	 * 0 de vie
	 * 
	 * V�rifie si il reste des cages. Si 0 cages, propose d'en recharger 10 mais
	 * co�te 10pv
	 * 
	 * V�rifie si le sac est plein. Si oui, propose de le vider mais co�te 10pv
	 * 
	 * Propose 6 choix � Juliette
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

		System.out.println("Il reste " + juju.getLife() + " pdv � Juju");

		while (juju.getLife() > 0) {

			if (numberCages == 0) {
				System.out.println("Vous n'avez plus de cages ! Voulez-vous en racheter ? \nOui [1] (co�t : 10pv) \n");
				Scanner userTappingCage = new Scanner(System.in);
				int strCage = userTappingCage.nextInt();
				if (strCage == 1) {
					numberCages = 10;
					juju.setLife(juju.getLife() - 10);
				}
			}
			if (ptimodex.size() == 10) {
				System.out.println(
						"Vous n'avez plus de place dans votre sac ! Voulez-vous relacher vos Ptimos ? \nOui [1] (co�t : 10pv) \nNon [2] \n");
			}

			if (distance < 1) {
				capture(target, juju);
			}

			System.out.println("Vous �tes � " + this.distance + " m�tres d'un " + target.name
					+ " sauvage, que souhaitez-vous faire ?\n");
			System.out
					.println("[1] - Observer \n[2] - Se rapprocher \n[3] - Lancer une friandise " + juju.getFriandises()
							+ "\n" + "[4] - Faire une danse impressionnante \n[5] - Tirer une flechette endormante "
							+ juju.getFlechette() + " \n[6] - Boire une potion \n" + "\n[0] - Laisser le " + target.name
							+ " en libert�");

			Scanner userTapping = new Scanner(System.in); // Permet au joueur d'entrer un chiffre
			int str = userTapping.nextInt(); // R�cup�re l'entr�e du joueur

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
		 * Si Juliette � 0 de vie, affiche le message de d�faite, recharge toutes les
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
	 * M�thode void reaction
	 * 
	 * @param1 target = Ptimo cibl� par Juliette
	 * 
	 * @param2 distance = distance entre Juliette et le Ptimo
	 * 
	 * @param3 juju = Juliette l'unique, la vraie
	 * 
	 * Si la Dominance du Ptimo est �gale � 100, d�clanche son attaque magique
	 * 
	 * Si le Ptimo est un Pokrand, d�clanche les r�actions possibles du Pokrand
	 * Sinon d�clanche les r�actions possibles du Ptimo
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
	 * @param1 et @param2 d�j� dit plus haut
	 * 
	 * R�cup�re le r�sultat de la fonction pokrandSpecialMagicAttack() de l'objet
	 * Pokrand Si 5 : Le Pokrand lib�re les Ptimos captur�s et s'enfuit Si 4 : Le
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
	 * M�thodes qui determines les actions des Ptimos en fonction de la distance, de
	 * la valeur du Stress et de la Dominance des Ptimos. Fait � peur pr�s � partir
	 * de l'�nonc�
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
	 * M�thode void capture
	 * 
	 * @param target, juju (comme d'habitude)
	 * 
	 * Enl�ve une cage � Juliette Affiche le message de capture Redirie sur la
	 * m�thode apparition
	 */

	public void capture(Ptimos target, Juliette juju) {
		numberCages--;
		System.out
				.println("Vous avez captur� le " + target.name + " ! Bravo ! Il vous reste " + numberCages + " cages.");
		ptimodex.add(target.name);
		apparition(juju);
	}

	/**
	 * M�thode void getCloser
	 * 
	 * @param juju = Juliette
	 * 
	 * R�duit de 3 � 8 m�tres la distance
	 */

	public void getCloser(Juliette juju) {

		Random nbreAlea = new Random();
		int alea = nbreAlea.nextInt(5) + 3;
		setDistance(getDistance() - alea, juju);
	}

	/**
	 * M�thode void getAway
	 * 
	 * @param1 distance = distance
	 * 
	 * @param2 juju = Juliette
	 * 
	 * Augmente la distance de 2 � 5 m�tres
	 */

	public void getAway(int distance, Juliette juju) {

		Random nbreAlea = new Random();
		int alea = nbreAlea.nextInt(3) + 2;
		distance += alea;
		System.out.println("Le ptimo s'�loigne de " + alea + "\n");
		setDistance(distance, juju);
	}

	/**
	 * M�thode void bag
	 * 
	 * @param ptimodex = Contient les ptimos captur�s
	 * 
	 * Si le sac est vide, il le dit Affiche les Ptimos captur�s V�rifie si la
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
	 * M�thode finDeJeu
	 * 
	 * Cens�e arr�ter le jeu mais pas r�ussi
	 */

	private void finDeJeu(Juliette juju) {
		System.out.println(
				"Bravo ! Vous avez r�ussi � capturer tous les Ptimos ! Vous pouvez enfin faire la recette du rago�t aux Ptimos !!\n");
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

