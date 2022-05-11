import java.util.EnumMap;

import gui.Okno;
import logika.Igra;
import logika.Igralec;
import vodja.Vodja;
import vodja.VrstaIgralca;

public class Main {

	public static void main(String[] args) {
		
		/*Okno okno = new Okno();
		
		//da se scene vidjo
		okno.pack();
		okno.setVisible(true);
		
		Vodja.okno = okno;*/
		//vrsta igralca kot je to storil profesor
		System.out.println("zacetek");
		long t1 = System.currentTimeMillis();
		Vodja.vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
		
		// za to bi blo fajn nardit GUI za izbiro različnih opcij
		Vodja.vrstaIgralca.put(Igralec.CRN, VrstaIgralca.R);
		Vodja.vrstaIgralca.put(Igralec.BEL, VrstaIgralca.R);
		
		//poženi da se igra začne
		Vodja.igramoNovoIgro();
		long t2 = System.currentTimeMillis();
		System.out.println("konec: " + (t2 - t1));
	}

}
