package Ptimos;

import java.awt.*;
import javax.swing.*;

public class Background extends JPanel{
	ImageIcon image = new ImageIcon("Ressources/nitendo.jpg");
	
	Background () {
		
		
		JLabel label = new JLabel(image,JLabel.CENTER);
		
		setBounds(0, 0, 800, 500);
		setBackground(new Color(150,150,150,0));
		//add(label);
		
	}
	
	public void paint (Graphics g) {
		g.drawImage(image.getImage(),0,0,getWidth(),getHeight(),this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
