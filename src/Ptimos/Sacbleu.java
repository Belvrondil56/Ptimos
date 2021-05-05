package Ptimos;

public class Sacbleu extends Ptimos {

	public Sacbleu() {
		this.dominance = 50; // Fixe la Dominance de d�part d'un Sacbleu � 50
		this.stress = 75; // Fixe le Stress de d�part d'un Sacbleu � 75
		this.name = "Sacbleu"; // Donne le nom Sacbleu au Sacbleu
	}
	
	/*
	 * M�thode void magicAttack (juju et ptimo en argument comme les autres m�thodes)
	 * Basse la vie de Juliette de 35
	 * R�duit le Stress du Ptimo qui attaque de 30
	 * Remet la dominance du Ptimo qui a attaqu� � 50 si elle �tait de 100
	 * 
	 */
	
	public void magicAttack(Juliette juju, Ptimos ptimo) {
		juju.setLife(juju.getLife()-35);
		ptimo.setStress(ptimo.getStress()-30);
		if (ptimo.getDominance()==100) {
			ptimo.setDominance(50);
		}
		System.out.println(ptimo.name + " vous inflige un puissant burst gr�ce � sa puissante magie !");
	}
	
	@Override
	public int pokrandSpecialMagicAttack(Ptimos ptimo, Juliette juju) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
