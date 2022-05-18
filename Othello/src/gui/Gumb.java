package gui;

import java.awt.Color;
import java.awt.Graphics;

public class Gumb {
	int x, y, a, b;
	Color color;
	String ime;
	public Gumb(String ime, int x, int y, int a, int b) {
		this.ime = ime;
		this.x = x;
		this.y = y;
		this.a = a;
		this.b = b;
		this.color = Color.white;
		
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
	
	public void narisi(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(x, y, a, b);
	}
}
