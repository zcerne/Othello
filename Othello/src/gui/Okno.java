package gui;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Okno extends JFrame {
	
	private Platno platno;
	
	//platno ane...
	public Okno() {
		//ko zapreš okno zapri program
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Othello");
		//ustvari novo platno
		platno = new Platno();
		this.add(platno);

	}
		
}


