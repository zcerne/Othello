package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import logika.Igra;
import logika.Polje;
import splosno.Poteza;
import vodja.Vodja;

public class PlatnoIgra extends JPanel{
	public ArrayList<Gumb> gumbi;
	public int width, height;
	public ArrayList<Poteza> izberi;
	
	public PlatnoIgra(int w, int h) {
		width = w;
		height = h;
		gumbi = new ArrayList<Gumb>();
	}
	
	public int kvadratek() {
		return Math.min(width, height) / Igra.N;
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
	
	public void posodobiZaslon(Graphics2D g) {
		posodobiGumbe();
		
		narisiPlosco(g);
		narisiMoznosti(g);
		narisiGumbe(g);
		
	}

	public void klik(int moseX, int moseY) {
		for(Gumb g : gumbi) {
			if(g.klik(moseX, moseY)) {
				Vodja.gumb(g.ime);
			}
		}
		
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
	}
	
	public void narisiGumbe(Graphics2D g) {
		for(Gumb gu : gumbi) {
			gu.narisi(g);
		}
	}
	
	public void posodobiGumbe() {
		Gumb gu;
		
		for(int i = 1; i < gumbi.size()+1; i++) {
			gu = gumbi.get(i-1);
			gu.x = width/4;
			gu.y = i*height/5;
			gu.a = width/2;
			gu.b = height/16;
		}
	}

}
