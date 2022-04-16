import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Okno extends JFrame {
	
	private Platno platno;
	
	//platno ane...
	public Okno() {
		this.setTitle("Othello");
		platno = new Platno();
		this.add(platno);
	}
		
}


