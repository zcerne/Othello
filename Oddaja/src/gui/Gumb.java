package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import vodja.Vodja;

public class Gumb {
	double kx, ky, ka, kb;
	int konstX, konstY, x, y, a, b, oznaka;
	Color color;
	VrstaGumba ime;
	public Gumb(VrstaGumba ime, int w, int h, double kx, double ky, double ka, double kb, int konstX, int konstY) {
		this.ime = ime;
		this.kx = kx;
		this.ky = ky;
		this.ka = ka;
		this.kb = kb;
		this.konstX = konstX;
		this.konstY = konstY;
		posodobiPolozaj(w, h);
		this.color = Color.black;	
	}
	
	public boolean klik(int x, int y) {
		
		int dx = x - this.x;
		int dy = y - this.y;
		if(dx > 0 && dx < this.a && dy > 0 && dy < this.b) {
			return true;
		}
		return false;
	}

	public void narisi(Graphics2D g) {
		g.setColor(color);
		g.setFont(new Font("Arial", Font.BOLD, (int)Math.round(0.68*b)));
		int width = g.getFontMetrics().stringWidth(this.ime.toString());
		if(width>a)this.a = width + b;
		g.setStroke(new BasicStroke(5));
		
		g.drawRect(x, y, a, b);

		g.drawString(this.ime.toString(), x + (a-width)/2, y + (int)Math.round(0.7*b));
	}
	
	public void posodobiPolozaj(int w, int h) {
		x = (int) (w*kx + konstX);
		
		y = (int) (h*ky + konstY);
		a =(int) (w*ka);
		b = (int) (h*kb);
	}
=======
package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import vodja.Vodja;

public class Gumb {
	double kx, ky, ka, kb;
	int konstX, konstY, x, y, a, b, oznaka;
	Color color;
	VrstaGumba ime;
	public Gumb(VrstaGumba ime, int w, int h, double kx, double ky, double ka, double kb, int konstX, int konstY) {
		this.ime = ime;
		this.kx = kx;
		this.ky = ky;
		this.ka = ka;
		this.kb = kb;
		this.konstX = konstX;
		this.konstY = konstY;
		posodobiPolozaj(w, h);
		this.color = Color.black;	
	}
	
	public boolean klik(int x, int y) {
		
		int dx = x - this.x;
		int dy = y - this.y;
		if(dx > 0 && dx < this.a && dy > 0 && dy < this.b) {
			return true;
		}
		return false;
	}

	public void narisi(Graphics2D g) {
		g.setColor(color);
		g.setFont(new Font("Arial", Font.BOLD, (int)Math.round(0.68*b)));
		int width = g.getFontMetrics().stringWidth(this.ime.toString());
		if(width>a)this.a = width + b;
		g.setStroke(new BasicStroke(5));
		
		g.drawRect(x, y, a, b);

		g.drawString(this.ime.toString(), x + (a-width)/2, y + (int)Math.round(0.7*b));
	}
	
	public void posodobiPolozaj(int w, int h) {
		x = (int) (w*kx + konstX);
		
		y = (int) (h*ky + konstY);
		a =(int) (w*ka);
		b = (int) (h*kb);
	}
}