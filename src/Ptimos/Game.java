package Ptimos;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.*;

public class Game extends JFrame {
	
	public JFrame frame;
	public JPanel panneauText;
	
	public Game() {
	
	}

	public static void main(String[] args) {
		
		/*frame = new JFrame("Le Jeu des Ptimos");
		frame.setSize(800,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(550, 250);
		frame.setLayout(null);
		frame.getContentPane().setBackground(new Color(200,200,200));

		Background nitendoSwitch = new Background();
		panneauText = new Text();
		
		frame.add(panneauText);
		frame.add(nitendoSwitch);
	
		
		frame.setVisible(true);*/
		
		
		Juliette juju = Juliette.getJulietteInstance(); // Instanciation de Juliette
		Combat partie = juju.partie; // Creation de la partie
		partie.play(juju); // Lancement de la partie
		
		
	}
	
	public static void endGame (){
		System.out.println("Fin de partie");
	}
	
	public JPanel textArea() {
		return panneauText;
	}
	
	
	
}
