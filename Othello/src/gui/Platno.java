package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import logika.Igra;
import logika.Igralec;
import logika.Polje;
import splosno.Poteza;
import vodja.Vodja;
import vodja.VrstaIgralca;

public class Platno extends JPanel implements MouseListener, MouseMotionListener, KeyListener{
	
	int sizeX, sizeY;
	
	Igra igra;
	Color barvaOzadja;
	
	ArrayList<Poteza> izberi;
	
	ZaslonMenu zMenu;
	ZaslonIgra zIgra;

	Font newFont;
	public Platno() {
		super();
		
		//igra = new Igra();
		
		sizeX = sizeY = 600; //velikost okna
		
		setPreferredSize(new Dimension(sizeX, sizeY));
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addKeyListener(this);
		this.setFocusable(true);
		
		barvaOzadja = Color.BLUE;
		
		this.setBackground(barvaOzadja);
		
		zMenu = new ZaslonMenu(sizeX, sizeX);
		zIgra = new ZaslonIgra(sizeX, sizeX);
	}
	
	protected void paintComponent(Graphics g1) {
		
		Graphics2D g = (Graphics2D)g1;
		super.paintComponent(g);
		if(Vodja.stanjeZaslona == StanjeZaslona.MENU) {
			zMenu.width = this.getSize().width;
			zMenu.height = this.getSize().height;
			zMenu.posodobiZaslon(g);
			
		}
		
		else if(Vodja.stanjeZaslona == StanjeZaslona.IGRA) {
			zIgra.width = this.getSize().width;
			zIgra.height = this.getSize().height;
			zIgra.posodobiZaslon(g);
		}
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		int moseX = e.getX();
		int moseY = e.getY();
		
		switch(Vodja.stanjeZaslona) {
		case MENU : 
			for(Gumb g : zMenu.gumbi) {
				if(g.klik(moseX, moseY)) {
					Vodja.gumb(g.ime);
				}
			}
			
			break;
			
		case IGRA :

			int a = zIgra.kvadratek();
			
			for(Gumb g : zIgra.gumbi) {
				if(g.klik(moseX, moseY)) {
					Vodja.gumb(g.ime);	
				}
			};
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
				
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
