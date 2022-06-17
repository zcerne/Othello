package inteligenca;

import logika.Igra;
import logika.Igralec;
import logika.Polje;

public class OceniPozicijo {
	
	// Metoda oceniPozicijo za igro TicTacToe
	
	public static int oceniPozicijo(Igra igra, Igralec jaz) {
		int crni = igra.rezultat.get(Polje.CRN);
		int beli = igra.rezultat.get(Polje.BEL);
		
		if (jaz == Igralec.CRN) return crni - beli;
		else return beli - crni;
			
	}
	

}


