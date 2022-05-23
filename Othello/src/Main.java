import java.awt.GraphicsEnvironment;
import java.util.EnumMap;

import gui.Okno;
import gui.StanjeZaslona;
import logika.Igra;
import logika.Igralec;
import logika.Polje;
import vodja.Vodja;
import vodja.Vodja2;
import vodja.VrstaIgralca;

public class Main {

	public static void main(String[] args) {
		//normalnaIgra();
		testHitrosti();
		
		

		
	}
	
	
	
	
	public static void normalnaIgra() {
		Okno okno = new Okno();
		
		//da se scene vidjo
		okno.pack();
		okno.setVisible(true);
		
		Vodja.okno = okno;
		Vodja.stanjeZaslona = StanjeZaslona.MENU;
	}
	
	public static void testHitrosti() {
		
		Vodja2.vrstaIgralca = new EnumMap<Igralec,VrstaIgralca>(Igralec.class);
		Vodja2.vrstaIgralca.put(Igralec.CRN, VrstaIgralca.R); 
		Vodja2.vrstaIgralca.put(Igralec.BEL, VrstaIgralca.R);
		
		int crni, beli;
		crni = 0;
		beli = 0;
		int i = 0;
		while (i < 300) {

		
		//System.out.println("ZAČETEK");
		long t1 = System.currentTimeMillis();
		
		Vodja2.igramoNovoIgro();
		if (Vodja2.igra.rezultat.get(Polje.BEL) < Vodja2.igra.rezultat.get(Polje.CRN)) crni++;
		else beli++;
		System.out.println("Crni: " + crni + " : " + "Beli: " + beli);
		long t2 = System.currentTimeMillis();
		
		System.out.println(t2 - t1);
		//System.out.println("KONEC");
		//System.out.println();
		i++;
		}
		System.out.println("Crni: " + crni + " : " + "Beli: " + beli);
		
	}

}
