import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import logika.Plosca;

public class Platno extends JPanel implements MouseListener, MouseMotionListener{
	
	int sizeX, sizeY;
	int a;

	
	Plosca plosca;
	Color barvaOzadja;
	
	ArrayList<int[]> izberi;
	
	public Platno() {
		super();
		this.plosca = new Plosca();
		sizeX = sizeY = 800;
		a = sizeX/8;
		setPreferredSize(new Dimension(sizeX, sizeY));
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		izberi = plosca.dovoljenePoteze();
		barvaOzadja = Color.green;

		naPotezi();
		
		repaint();

	}
	
	public void naPotezi() {
		if (plosca.naVrsti == 1) System.out.println("Beli");
		if (plosca.naVrsti == -1) System.out.println("Črni");
	}
	protected void paintComponent(Graphics g1) {
		
		Graphics2D g = (Graphics2D)g1;
		narisiPlosco(g);
		narisiMoznosti(g);
		
	}
	
	public void narisiPlosco(Graphics2D g) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				g.setColor(barvaOzadja);
				g.fillRect(i*a, j*a, a, a);
				g.setColor(Color.black);
				g.drawRect(i*a, j*a, a, a);
				
				
				if (plosca.polja[i][j] == 1) {
					g.setColor(Color.white);
					g.fillOval(i*a + a/4, j*a + a/4, a/2, a/2);
				}
				else if (plosca.polja[i][j] == -1) {
					g.setColor(Color.black);
					g.fillOval(i*a + a/4, j*a + a/4, a/2, a/2);
				}
				
				
			}
		}
		
	}
	public void narisiMoznosti(Graphics2D g) {
		if (izberi != null) {
			
		
			for(int[] pol : izberi) {
				int i = pol[0];
				int j = pol[1];
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
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i*a <= moseX && moseX < i*a+a) && (j*a <= moseY && moseY < j*a + a)) {
					//System.out.println("Pred iskanjem");
					ArrayList<int[]> izbrani = plosca.izvediPotezo(i, j);
					
					
					if(izbrani.size() != 0) {
						plosca.polja[i][j] = plosca.naVrsti;
						for(int[] izb : izbrani) {
							int x = izb[0];
							int y = izb[1];
							plosca.polja[x][y] = plosca.naVrsti;
						}
						
						plosca.naVrsti *= -1;
					}
					//System.out.println("Po iskanju");	
				}
			}
		}
		if(izberi.size() == 0) {
			System.out.println("Nimaš možnosti");
			plosca.naVrsti *= -1;	
		}
		izberi = plosca.dovoljenePoteze();
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
