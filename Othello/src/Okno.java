import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Okno extends JFrame {
	
	private Platno platno;
	
	int test;
	public Okno() {
		this.setTitle("Othello");
		platno = new Platno();
		this.add(platno);
		test = 0;
	}
		
}


