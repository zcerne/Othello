package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class ZaslonMenu extends Zaslon {
	
	public ZaslonMenu(int w, int h) {
		super(w, h);
		gumbi.add(new Gumb(VrstaGumba.II, w, h, (double)1/4, (double)1/10, (double)1/2, (double)1/16, 0, 30));
		
		gumbi.add(new Gumb(VrstaGumba.IR, w, h, (double)1/4, (double)2/10, (double)1/2, (double)1/16, 0, 30));
		gumbi.add(new Gumb(VrstaGumba.RI, w, h, (double)1/4, (double)3/10, (double)1/2, (double)1/16,0, 30));
		gumbi.add(new Gumb(VrstaGumba.RR, w, h, (double)1/4, (double)4/10, (double)1/2, (double)1/16,0, 30));
		
		gumbi.add(new Gumb(VrstaGumba.RAND_C, w, h, (double)1/8, (double)6/10, (double)1/4, (double)1/16, 0, 30));
		gumbi.add(new Gumb(VrstaGumba.MINIMAX_C, w, h, (double)1/8, (double)7/10, (double)1/4, (double)1/16, 0, 30));
		gumbi.add(new Gumb(VrstaGumba.MCTS_C, w, h, (double)1/8, (double)8/10, (double)1/4, (double)1/16,0, 30));
		
		gumbi.add(new Gumb(VrstaGumba.RAND_B, w, h, (double)5/8, (double)6/10, (double)1/4, (double)1/16,0, 30));
		gumbi.add(new Gumb(VrstaGumba.MINIMAX_B, w, h, (double)5/8, (double)7/10, (double)1/4, (double)1/16,0, 30));
		gumbi.add(new Gumb(VrstaGumba.MCTS_B, w, h, (double)5/8, (double)8/10,(double)1/4, (double)1/16, 0, 30));
		

	}

	public void narisiNaslov(Graphics2D g) {
		g.setFont(new Font("Sitka Heading", Font.PLAIN, (int)Math.round(height/10)));
		g.setColor(Color.black);
		int w = g.getFontMetrics().stringWidth("OTHELLO");
		g.drawString("OTHELLO", (width - w)/2, height/10);
		
		g.setFont(new Font("Sitka Heading", Font.PLAIN, (int)Math.round(height/20)));
		w = g.getFontMetrics().stringWidth("Težavnost ČRN");
		g.drawString("Težavnost ČRN", 2*width/8 - w/2, 6*height/10);
		g.drawString("Težavnost BEL", 6*width/8 - w/2, 6*height/10);
		
	}
	
	public void posodobiZaslon(Graphics2D g) {
		
		narisiNaslov(g);
		posodobiGumbe();
		narisiGumbe(g);
	}

}
