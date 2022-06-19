<<<<<<< HEAD
package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import vodja.Vodja;

public abstract class Zaslon {
	public ArrayList<Gumb> gumbi;
	public int width, height;
	
	public Zaslon(int w, int h) {
		width = w;
		height = h;
		gumbi = new ArrayList<Gumb>();
	}
	
	public void narisiGumbe(Graphics2D g) {
		for(Gumb gu : gumbi) {
			
			gu.narisi(g);
		}
	}
	
	public void posodobiGumbe() {
		pobarvajVseGumbe();
		Gumb gu;
		
		for(int i = 1; i < gumbi.size()+1; i++) {
			gu = gumbi.get(i-1);
			gu.posodobiPolozaj(width, height);
			
		}
	}
	
	public void klik(int moseX, int moseY) {
		for(Gumb g : gumbi) {
			if(g.klik(moseX, moseY)) {
				
				Vodja.gumb(g.ime);
			}
		}
	}
	
	public void pobarvajVseGumbe() {
		for(Gumb g : gumbi) {
			if(Vodja.kdoIgraB == g.ime) g.color = Color.red;
			else if(Vodja.kdoIgraC == g.ime) g.color = Color.red;
			else g.color = Color.black;
		}
	}

=======
package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import vodja.Vodja;

public abstract class Zaslon {
	public ArrayList<Gumb> gumbi;
	public int width, height;
	
	public Zaslon(int w, int h) {
		width = w;
		height = h;
		gumbi = new ArrayList<Gumb>();
	}
	
	public void narisiGumbe(Graphics2D g) {
		for(Gumb gu : gumbi) {
			
			gu.narisi(g);
		}
	}
	
	public void posodobiGumbe() {
		pobarvajVseGumbe();
		Gumb gu;
		
		for(int i = 1; i < gumbi.size()+1; i++) {
			gu = gumbi.get(i-1);
			gu.posodobiPolozaj(width, height);
			
		}
	}
	
	public void klik(int moseX, int moseY) {
		for(Gumb g : gumbi) {
			if(g.klik(moseX, moseY)) {
				
				Vodja.gumb(g.ime);
			}
		}
	}
	
	public void pobarvajVseGumbe() {
		for(Gumb g : gumbi) {
			if(Vodja.kdoIgraB == g.ime) g.color = Color.red;
			else if(Vodja.kdoIgraC == g.ime) g.color = Color.red;
			else g.color = Color.black;
		}
	}

>>>>>>> refs/remotes/origin/main
}