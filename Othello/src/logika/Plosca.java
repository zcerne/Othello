package logika;

import java.util.ArrayList;

public class Plosca {
	public int[][] polja;
	public int test;
	public int naVrsti;
	
	public Plosca() {
		
		polja = new int[8][8];
		polja[3][3] = -1;
		polja[3][4] = 1;
		polja[4][3] = 1;
		polja[4][4] = -1;
		test = 0;
		naVrsti = -1;
		
	}
	
	public ArrayList<int[]> izvediPotezo(int ii, int jj) {
		ArrayList<int[]> izbrani = new ArrayList<>();
		if (polja[ii][jj] == 0) {
			//System.out.println("Glavni loop");
			int[][] smeri = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1,1}, {-1,1}, {1,-1}, {-1,-1}};
			
			for(int[] smer : smeri) {
				int smerX = smer[0];
				int smerY = smer[1];
				int premik = 1;
				ArrayList<int[]> izbraniVSmeri = new ArrayList<>();
				//System.out.println("smer" + smerX + "  " + smerY);
				while (ii + premik*smerX >= 0 && ii + premik*smerX < 8 && jj + premik*smerY >= 0 && jj + premik*smerY < 8 && polja[ii + smerX][jj + smerY] == naVrsti*-1) {	
					int[] izb = {ii + premik*smerX, jj + premik*smerY};
					izbraniVSmeri.add(izb);
					if (polja[ii + premik*smerX][jj + premik*smerY] == 0) {
						izbraniVSmeri.clear();
						break;
					}
					else if (polja[ii + premik*smerX][jj + premik*smerY] == naVrsti*-1) {
						premik ++;
					}
					else if (polja[ii + premik*smerX][jj + premik*smerY] == naVrsti) {
						izbrani.addAll(izbraniVSmeri);
						break;
					}
				}
			}	
			
		}
		return izbrani;
	}
	
	public ArrayList<int[]> dovoljenePoteze(){
		ArrayList<int[]> volni = new ArrayList<>();
		ArrayList<ArrayList<int[]>> listList = new ArrayList<>();
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				ArrayList<int[]> izbrani = izvediPotezo(i, j);
				int[] izb = {i, j};
				if (izbrani.size() != 0) {
					volni.add(izb);
					listList.add(izbrani);
				}
			}
		}
		
		return volni;
	}
}
