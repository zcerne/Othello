package inteligenca;
import java.util.ArrayList;
import java.util.Random;

import logika.Igra;
import splosno.KdoIgra;
import splosno.Poteza;

public class Inteligenca extends KdoIgra{
	private static Random random = new Random ();
	public Inteligenca() {
		super("Ekipa neverjetnosi");
	}
	
	public Poteza izberiPotezo(Igra igra) {
		ArrayList<Poteza> izbira = igra.dovoljenePoteze();
		if(izbira == null) return null; 
		
		else {
			int randomIndex = random.nextInt(izbira.size());
			//System.out.println(randomIndex);
			return izbira.get(randomIndex);
		}
			
	}
}

