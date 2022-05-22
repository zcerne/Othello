package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class ZaslonMenu extends Zaslon {
	
	public ZaslonMenu(int w, int h) {
		super(w, h);
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

}
