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

public class Platno2 extends JPanel implements MouseListener, MouseMotionListener{
	
	int sizeX, sizeY;
	int a;

	
	Igra igra;
	Color barvaOzadja;
	
	ArrayList<Poteza> izberi;
	
	public Platno2() {
		super();
		
		this.igra = new Igra();
		
		sizeX = sizeY = 800; //velikost okna
		a = sizeX/8; //velikost kvadratka
		
		setPreferredSize(new Dimension(sizeX, sizeY));
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		izberi = igra.dovoljenePoteze(); //poišče možne poteze igralca na vrsti in jih shrani v array kot koordionate (x,y)
		barvaOzadja = Color.green;

		naPotezi(); //pove ti kdo je na potezi
		
		repaint();

	}
	
	public void naPotezi() {
		if (igra.naVrsti == Polje.BEL) System.out.println("Beli"); 
		if (igra.naVrsti == Polje.CRN) System.out.println("Črni");
	}
	
	protected void paintComponent(Graphics g1) {
		
		Graphics2D g = (Graphics2D)g1;
		
		//vse kar je traba pač narisat
		narisiPlosco(g);
		narisiMoznosti(g);
		
	}
	
	public void narisiPlosco(Graphics2D g) {
		//gre čez celotno ploščo in nariše krog z barvo glede na vrednost v matriki pl0šče....................
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				g.setColor(barvaOzadja);
				g.fillRect(i*a, j*a, a, a);
				g.setColor(Color.black);
				g.drawRect(i*a, j*a, a, a);
				
				
				if (igra.polja[i][j] == Polje.BEL) {
					g.setColor(Color.white);
					g.fillOval(i*a + a/4, j*a + a/4, a/2, a/2);
				}
				else if (igra.polja[i][j] == Polje.CRN) {
					g.setColor(Color.black);
					g.fillOval(i*a + a/4, j*a + a/4, a/2, a/2);
				}
				
				
			}
		}
		
	}
	public void narisiMoznosti(Graphics2D g) {
		//gre čez vse koordinate v izberi in jih nariše
		
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
		
		//za vsako polje preveri ali smo klikniloi nanj in potem kliče izvediPotezo, 
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i*a <= moseX && moseX < i*a+a) && (j*a <= moseY && moseY < j*a + a)) {
					//System.out.println("Pred iskanjem");
					Poteza poteza = new Poteza(i,j);
					ArrayList<Poteza> izbrani = igra.izvediPotezo(poteza);
					
					
					if(izbrani.size() != 0) {
						igra.polja[i][j] = igra.naVrsti; //ki vrne vse krogce na katere poteza vpliva 
						for(Poteza izb : izbrani) {
							int x = izb.getX();
							int y = izb.getY();
							igra.polja[x][y] = igra.naVrsti; //in jih nato obrne 
						}
						
						igra.naVrsti = igra.naVrsti.obrat(); // zamenja igralca
					}
					//System.out.println("Po iskanju");	
				}
			}
		}
		
		if(izberi.size() == 0) { // če pač ni možnosti ni možnosti in spet zamenja igralca
			System.out.println("Nimaš možnosti");
			igra.naVrsti = igra.naVrsti.obrat();	
		}
		
		izberi = igra.dovoljenePoteze(); //posodobi možnosti
		this.repaint();
		naPotezi();
		
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
