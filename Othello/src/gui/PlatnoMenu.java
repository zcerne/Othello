package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

import vodja.Vodja;

public class PlatnoMenu extends JPanel{
	public ArrayList<Gumb> gumbi;
	public int width, height;
	
	public PlatnoMenu(int w, int h){
		width = w;
		height = h;
		setPreferredSize(new Dimension(w, h));
		gumbi = new ArrayList<Gumb>();
		
		gumbi.add(new Gumb(VrstaGumba.II, w/4, 1*h/5, w/2, h/16));
		gumbi.add(new Gumb(VrstaGumba.IR, w/4, 2*h/5, w/2, h/16));
		gumbi.add(new Gumb(VrstaGumba.RI, w/4, 3*h/5, w/2, h/16));
		gumbi.add(new Gumb(VrstaGumba.RR, w/4, 4*h/5, w/2, h/16));
	}
	
	public void narisiNaslov(Graphics2D g) {
		g.setFont(new Font("Sitka Heading", Font.PLAIN, (int)Math.round(height/10)));
		g.setColor(Color.black);
		int w = g.getFontMetrics().stringWidth("OTHELLO");
		g.drawString("OTHELLO", (width - w)/2, height/10);
	}
	
	public void posodobiZaslon(Graphics2D g) {
		narisiNaslov(g);
		posodobiGumbe();
		narisiGumbe(g);
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
	
	public void klik(int moseX, int moseY) {
		System.out.println("klik");
		System.out.println("X " + moseX + " Y " + moseY);
		for(Gumb g : gumbi) {
			
			if(g.klik(moseX, moseY)) {
				System.out.println("klik2");
				Vodja.gumb(g.ime);
			}
		}
	}
	
	protected void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		width = this.getSize().width;
		height = this.getSize().height;
		Graphics2D g = (Graphics2D)g1;
		posodobiZaslon(g);
	}
}
