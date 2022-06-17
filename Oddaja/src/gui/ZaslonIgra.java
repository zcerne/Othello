package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import logika.Igra;
import logika.Polje;
import splosno.Poteza;
import vodja.Vodja;

public class ZaslonIgra extends Zaslon{
	
	public ArrayList<Poteza> izberi;
	
	public ZaslonIgra(int w, int h) {
		super(w, h);
		
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
		//narisiMoznosti(g);
		narisiGumbe(g);
		
	}
	
	@Override
	public void klik(int moseX, int moseY) {
		super.klik(moseX, moseY);
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
}
	


