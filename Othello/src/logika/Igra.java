package logika;

import java.util.ArrayList;

import splosno.Poteza;

public class Igra {
	
	public int[][] polja;
	public int naVrsti;
	
	public Igra() {
		polja = new int[8][8];
		
		polja[3][3] = -1;
		polja[3][4] = 1;
		polja[4][3] = 1;
		polja[4][4] = -1;
		naVrsti = -1;
		
	}
	
	public boolean odigraj(Poteza poteza) {
		int ii = poteza.getX();
		int jj = poteza.getY();
		
		ArrayList<int[]> izbrani = new ArrayList<>();
		if (polja[ii][jj] == 0) {
			//System.out.println("Glavni loop");
			int[][] smeri = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1,1}, {-1,1}, {1,-1}, {-1,-1}};
			
			for(int[] smer : smeri) {
				int smerX = smer[0];
				int smerY = smer[1];
				int premik = 1;

				while (ii + premik*smerX >= 0 && ii + premik*smerX < 8 && jj + premik*smerY >= 0 && jj + premik*smerY < 8 && polja[ii + smerX][jj + smerY] == naVrsti*-1) {	
					if (polja[ii + premik*smerX][jj + premik*smerY] == 0) {
						break;
					}
					else if (polja[ii + premik*smerX][jj + premik*smerY] == naVrsti*-1) {
						premik ++;
					}
					else if (polja[ii + premik*smerX][jj + premik*smerY] == naVrsti) {
						return true;
					}
				}
			}	
			
		}
		
		return false;
	}
}
