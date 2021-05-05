package Ptimos;

import java.awt.*;

import javax.swing.*;

public class Text extends JPanel{
	
		public JTextArea dialogue;
		
	Text () {
		
		setLocation(175,155);
		setSize(400,200);
		setOpaque(false);
		
		dialogue = new JTextArea();
		
		dialogue.setOpaque(false);
		dialogue.setEditable(false);
		dialogue.setSize(300, 150);
		dialogue.setLocation(200, 100);
		dialogue.setLineWrap(true);
		dialogue.setText("BONJOUR JE SUIS UN PTIMOS");
		dialogue.setFont(new Font("SERIF",Font.BOLD,13));
		dialogue.setForeground(Color.WHITE);
		
		add(dialogue);
			
		}
	
	public JTextArea getDialogue() {
		return dialogue;
	}
		
	}

