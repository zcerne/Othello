import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Okno extends JFrame {
	
	private Platno2 platno;
	
	//platno ane...
	public Okno() {
		this.setTitle("Othello");
		platno = new Platno2();
		this.add(platno);
	}
		
}


