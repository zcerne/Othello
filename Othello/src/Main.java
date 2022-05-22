import java.awt.GraphicsEnvironment;
import java.util.EnumMap;

import gui.Okno;
import gui.StanjeZaslona;
import logika.Igra;
import logika.Igralec;
import vodja.Vodja;
import vodja.VrstaIgralca;

public class Main {

	public static void main(String[] args) {
		
		Okno okno = new Okno();
		
		//da se scene vidjo
		okno.pack();
		okno.setVisible(true);
		
		Vodja.okno = okno;
		Vodja.stanjeZaslona = StanjeZaslona.MENU;
		
		//for(String fomt : GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()) System.out.println(fomt);;
		
	}

}
