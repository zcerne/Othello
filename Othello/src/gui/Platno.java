package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.EnumMap;

import javax.swing.JPanel;

import logika.Igra;
import logika.Igralec;
import logika.Polje;
import splosno.Poteza;
import vodja.Vodja;
import vodja.VrstaIgralca;

public class Platno extends JPanel implements MouseListener, MouseMotionListener{
	
	int sizeX, sizeY;
	
	Igra igra;
	Color barvaOzadja;
	
	ArrayList<Poteza> izberi;
	
	ArrayList<Gumb> gumbi;
	public Platno() {
		super();
		
		//igra = new Igra();
		
		sizeX = sizeY = 600; //velikost okna
		
		setPreferredSize(new Dimension(sizeX, sizeY));
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		barvaOzadja = Color.BLUE;
		
		this.setBackground(barvaOzadja);
		
		gumbi = new ArrayList<>();
		
		repaint();
		

	}
	
	private int kvadratek() {
		return Math.min(getWidth(), getHeight()) / Igra.N;
	}
	
	protected void paintComponent(Graphics g1) {
		
		Graphics2D g = (Graphics2D)g1;
		super.paintComponent(g);
		
		if(Vodja.stanjeZaslona == StanjeZaslona.MENU) {
			narisiMenu(g);
			
		}
		
		else if(Vodja.stanjeZaslona == StanjeZaslona.IGRA) {
			narisiPlosco(g);
			narisiMoznosti(g);
		}
	}
	
	public void narisiMenu(Graphics2D g) {
		Gumb gumbIgra1 = new Gumb("IGRA1", this.getSize().width/5, this.getSize().height/2, this.getSize().width/8, this.getSize().height/16);
		gumbIgra1.narisi(g);
		
		Gumb gumbIgra2 = new Gumb("IGRA2", 2*this.getSize().width/5, this.getSize().height/2, this.getSize().width/8, this.getSize().height/16);
		gumbIgra2.narisi(g);
		
		Gumb gumbIgra3 = new Gumb("IGRA3", 3*this.getSize().width/5, this.getSize().height/2, this.getSize().width/8, this.getSize().height/16);
		gumbIgra3.narisi(g);
		
		Gumb gumbIgra4 = new Gumb("IGRA4", 4*this.getSize().width/5, this.getSize().height/2, this.getSize().width/8, this.getSize().height/16);
		gumbIgra4.narisi(g);
		
		gumbi.add(gumbIgra1);
		gumbi.add(gumbIgra2);
		gumbi.add(gumbIgra3);
		gumbi.add(gumbIgra4);
	}
	
	
	public void narisiPlosco(Graphics2D g) {
		//gre čez celotno ploščo in nariše krog z barvo glede na vrednost v matriki pl0šče....................
		
		int a = kvadratek();
		for (int i = 0; i < Igra.N; i++) {
			for (int j = 0; j < Igra.N; j++) {
				//g.setColor(barvaOzadja);
				//g.fillRect(i*a, j*a, a, a);
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
		int moseX = e.getX();
		int moseY = e.getY();
		
		switch(Vodja.stanjeZaslona) {
		case MENU : 
			for(Gumb g : gumbi) {
				if(g.klik(moseX, moseY)) {
					Vodja.dolociIgralce(g.ime);
					
				}
			}
			
			break;
			
		case IGRA :

			int a = kvadratek();

			
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
			break;
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
