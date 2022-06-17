package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
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
	
	public ZaslonMenu zMenu;
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
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			    RenderingHints.VALUE_ANTIALIAS_ON);
		
		super.paintComponent(g);
		if(Vodja.stanjeZaslona == StanjeZaslona.MENU) {
			
			this.setBackground(new Color(31, 255, 189));
			zMenu.width = this.getSize().width;
			zMenu.height = this.getSize().height;
			zMenu.posodobiZaslon(g);
			
		}
		
		else if(Vodja.stanjeZaslona == StanjeZaslona.IGRA) {
			
			this.setBackground(new Color(0, 204, 0));
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
			zMenu.klik(moseX, moseY);
			
			break;
			
		case IGRA :
			zIgra.klik(moseX, moseY);

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
