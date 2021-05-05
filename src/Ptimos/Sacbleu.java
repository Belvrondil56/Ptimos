package Ptimos;

public class Sacbleu extends Ptimos {

	public Sacbleu() {
		this.dominance = 50; // Fixe la Dominance de départ d'un Sacbleu à 50
		this.stress = 75; // Fixe le Stress de départ d'un Sacbleu à 75
		this.name = "Sacbleu"; // Donne le nom Sacbleu au Sacbleu
	}
	
	/*
	 * Méthode void magicAttack (juju et ptimo en argument comme les autres méthodes)
	 * Basse la vie de Juliette de 35
	 * Réduit le Stress du Ptimo qui attaque de 30
	 * Remet la dominance du Ptimo qui a attaqué à 50 si elle était de 100
	 * 
	 */
	
	public void magicAttack(Juliette juju, Ptimos ptimo) {
		juju.setLife(juju.getLife()-35);
		ptimo.setStress(ptimo.getStress()-30);
		if (ptimo.getDominance()==100) {
			ptimo.setDominance(50);
		}
		System.out.println(ptimo.name + " vous inflige un puissant burst grâce à sa puissante magie !");
	}
	
	@Override
	public int pokrandSpecialMagicAttack(Ptimos ptimo, Juliette juju) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
