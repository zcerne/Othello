package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import logika.Igra;
import logika.Polje;
import splosno.Poteza;
import vodja.Vodja;

public class Platno extends JPanel implements MouseListener, MouseMotionListener{
	
	int sizeX, sizeY;
	
	Igra igra;
	Color barvaOzadja;
	
	ArrayList<Poteza> izberi;

	public Platno() {
		super();
		
		igra = new Igra();
		
		sizeX = sizeY = 800; //velikost okna
		
		setPreferredSize(new Dimension(sizeX, sizeY));
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		barvaOzadja = Color.green;
		
		repaint();

	}
	
	private int kvadratek() {
		return Math.min(getWidth(), getHeight()) / Igra.N;
	}
	
	protected void paintComponent(Graphics g1) {
		
		Graphics2D g = (Graphics2D)g1;

		narisiPlosco(g);
		narisiMoznosti(g);
		
	}
	
	public void narisiPlosco(Graphics2D g) {
		//gre čez celotno ploščo in nariše krog z barvo glede na vrednost v matriki pl0šče....................
		
		int a = kvadratek();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				g.setColor(barvaOzadja);
				g.fillRect(i*a, j*a, a, a);
				g.setColor(Color.black);
				g.drawRect(i*a, j*a, a, a);
				
				//uglavnem rišem ploščo
				//to je mal chonky narjen ker ne razumem kaj pomeni statična metoda. Prej je pisal Vodja.igra[i][j]
				if (Vodja.igra.getPlosca()[i][j] == Polje.BEL) {
					g.setColor(Color.white);
					g.fillOval(i*a + a/4, j*a + a/4, a/2, a/2);
				}
				else if (Vodja.igra.getPlosca()[i][j] == Polje.CRN) {
					g.setColor(Color.black);
					g.fillOval(i*a + a/4, j*a + a/4, a/2, a/2);
				}
				
				
			}
		}
		
	}
	
	//narišem kam lahko uturim krogec
	public void narisiMoznosti(Graphics2D g) {
		//gre čez vse koordinate v izberi in jih nariše
		int a = kvadratek();
		izberi = Vodja.igra.dovoljenePoteze();
		if (izberi != null) {
			
		
			for(Poteza pol : izberi) {
				int i = pol.getX();
				int j = pol.getY();
				g.setColor(Color.black);
				g.drawOval(i*a + a/4, j*a + a/4, a/2, a/2);
			}
		}
	}
			
			
			
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int a = kvadratek();
		int moseX = e.getX();
		int moseY = e.getY();
		
		//za vsako polje preveri ali smo kliknili nanj in potem kliče izvediPotezo, 
		if(Vodja.clovekNaPotezi) {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if ((i*a <= moseX && moseX < i*a+a) && (j*a <= moseY && moseY < j*a + a)) {
						//System.out.println("Pred iskanjem");
						Poteza poteza = new Poteza(i,j);
						
						
						Vodja.igrajPotezo(poteza);
	
					}
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
