package presentacion;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SignIn {
	public static void main(String[] args) {
		
		miMarco miMarco1=new miMarco();

		
		miMarco1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class marcoSignIn extends JFrame{
	
	public marcoSignIn() {
		setVisible(true);
		setBounds(500,200,700,600);
		setResizable(false);
		setTitle("Crud Java");
		
		Lamina miLamina = new Lamina();
		miLamina.setBackground(Color.white);
		
		add(miLamina);
		
	}
}

class LaminaSignIn extends JPanel{
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		Rectangle2D rectanguloExt = new Rectangle2D.Double(0,0,800,800);
		
		g2.draw(rectanguloExt);
		
		g2.setColor(new Color(130,130,130));
		
		g2.fill(rectanguloExt);	
		
		
		Rectangle2D rectangulo = new Rectangle2D.Double(150,70,400,400);
		
		g2.draw(rectangulo);
		
		g2.setColor(new Color(230,132,80));
		
		g2.fill(rectangulo);
		
		
		
	}
	
}