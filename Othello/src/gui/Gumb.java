package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Gumb {
	int x, y, a, b;
	Color color;
	VrstaGumba ime;
	public Gumb(VrstaGumba ime, int x, int y, int a, int b) {
		this.ime = ime;
		this.x = x;
		this.y = y;
		this.a = a;
		this.b = b;
		this.color = Color.red;	
	}
	
	public boolean klik(int x, int y) {
		int dx = x - this.x;
		int dy = y - this.y;
		if(dx > 0 && dx < this.a && dy > 0 && dy < this.b) {
			color = Color.red;
			return true;
		}
		return false;
	}
	
	
	public void narisi(Graphics2D g) {
		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.BOLD, (int)Math.round(0.68*b)));
		int width = g.getFontMetrics().stringWidth(this.ime.toString());
		if(width>a)this.a = width + b;
		g.setStroke(new BasicStroke(5));
		
		g.drawRect(x, y, a, b);

		g.drawString(this.ime.toString(), x + (a-width)/2, y + (int)Math.round(0.7*b));
	}
}