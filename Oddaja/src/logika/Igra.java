<<<<<<< HEAD
package logika;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

import splosno.Poteza;

public class Igra {
	
	public Polje[][] polja;
	public Igralec naVrsti;
	private static Random random = new Random ();
	public ArrayList<Poteza> dovoljenePoteze;
	public static int N;
	public int stejMoznosti;
	
	public Stanje stanjeIgre;
	public Map<Polje, Integer> rezultat;
	
	public Igra() {
		
		N = 8;
		polja = new Polje[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				polja[i][j] = Polje.PRAZNO;
			}
		}
		
		polja[N/2-1][N/2-1] = Polje.CRN;
		polja[N/2][N/2-1] = Polje.BEL;
		polja[N/2-1][N/2] = Polje.CRN;
		polja[N/2][N/2] = Polje.BEL;

		naVrsti = Igralec.CRN;
		
		stejMoznosti = 0; //steje igre ko nihče nima na voljo poteze. Gre le do 2
		
		rezultat = new EnumMap<Polje, Integer>(Polje.class); //spremlja rezultat
		rezultat.put(Polje.CRN, 2);
		rezultat.put(Polje.BEL, 2);
		dovoljenePoteze = dovoljenePoteze(); //po vsaki poteze izračuna vse poteze ki so na voljo
		
		//moznePoteze();
		stanjeIgre = Stanje.V_TEKU; //stanje igre pač
		
	}
	
	//ustvarjanje nove igre. Kopiram vsako stvar
	public Igra(Igra igra) {
		this.polja = new Polje[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				this.polja[i][j] = igra.polja[i][j];
			}
		}
		this.stejMoznosti = igra.stejMoznosti;
		this.rezultat = igra.rezultat;
		this.naVrsti = igra.naVrsti;
		this.rezultat = new EnumMap<Polje, Integer>(Polje.class);
		rezultat.put(Polje.CRN, igra.rezultat.get(Polje.CRN));
		rezultat.put(Polje.BEL, igra.rezultat.get(Polje.BEL));
		this.stanjeIgre = igra.stanjeIgre;
		dovoljenePoteze = igra.dovoljenePoteze();
		moznePoteze();//preveri ali imata igralca sploh kakšno možnost
		
	}
	
	// odigram potezo
	public boolean odigraj(Poteza poteza) {
		if(dovoljenePoteze.contains(poteza)) { //preverim ali je poteza sploh izvedljiva
			ArrayList<Poteza> dobljeniZetoni = dobljeni(poteza); //dobljeni žetoni, ki jih dobim s potezo
			int i = poteza.getX();
			int j = poteza.getY();
			polja[i][j] = naVrsti.dobiPolje(); //spremenim vrednost polj
			for(Poteza izb : dobljeniZetoni) {
				int x = izb.getX();
				int y = izb.getY();
				polja[x][y] = naVrsti.dobiPolje(); //in jih nato obrne
			}
			posodobiRezultat(dobljeniZetoni.size()); //posodobim rezultat
			naVrsti = naVrsti.obrat(); //zamenjam igralca
			dovoljenePoteze = dovoljenePoteze(); //posodobim dovoljene poteze
			moznePoteze(); //preverim ali so poteze mozne
			stanjeIgre = stanjeIgre(); //posodobim stanje igre
			return true;
		}
		
		else {
			return false;
		}
		
	}
	
	//ali so poteze mozne
	private void moznePoteze() {
		
		if(dovoljenePoteze.size() == 0) {
			stejMoznosti +=1;
			naVrsti = naVrsti.obrat();
			dovoljenePoteze = dovoljenePoteze();
			if(dovoljenePoteze.size() == 0) {
				stejMoznosti +=1;
				naVrsti = naVrsti.obrat();
			}
			else stejMoznosti = 0;
		}
		else stejMoznosti = 0;
	}
	
	//ali je potezo mozno odigrati
	public boolean lahkoOdigram(Poteza poteza) {
		ArrayList<Poteza> dobljeniZetoni = dobljeni(poteza);
		if(dobljeniZetoni.size() != 0) {//vrne vse krogce na katere poteza vpliva 
			
			return true;
		}
		
		return false;
	}
	
	//najdi vse dovoljene poteze
	public ArrayList<Poteza> dovoljenePoteze(){
		ArrayList<Poteza> volni = new ArrayList<Poteza>();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				Poteza poteza = new Poteza(i,j);
				boolean dovoljena = lahkoOdigram(poteza);
				if (dovoljena) {
					volni.add(poteza);
				}
			}
		}
		return volni;
	}
	
	//vrne žetone ki jih dobim s potezo
	public ArrayList<Poteza> dobljeni(Poteza poteza) {
		ArrayList<Poteza> izbrani = new ArrayList<>();
		int ii = poteza.getX();
		int jj = poteza.getY();
		
		
		if (polja[ii][jj] == Polje.PRAZNO) {
			int[][] smeri = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1,1}, {-1,1}, {1,-1}, {-1,-1}}; // vse smeri pregleda
			
			for(int[] smer : smeri) { //ppregled v vseh smereh
				int smerX = smer[0];
				int smerY = smer[1];
				int premik = 1;
				ArrayList<Poteza> izbraniVSmeri = new ArrayList<>(); // seznam vseh dobljenih krogcev/točk... v tej smeri
				while (ii + premik*smerX >= 0 && ii + premik*smerX < N && jj + premik*smerY >= 0 && jj + premik*smerY < N){
					// ustvarim koordinato
					Poteza izb = new Poteza(ii + premik*smerX, jj + premik*smerY); 
					
					//če je nasledji prazen pogledam nasledjo vrstico
					if (polja[ii + premik*smerX][jj + premik*smerY] == Polje.PRAZNO) {
						izbraniVSmeri.clear();
						break;
					}
					//če je nasledji od nasprotnika si ga shranim in nadaljujem v liniji
					else if (polja[ii + premik*smerX][jj + premik*smerY] == naVrsti.obrat().dobiPolje()) {
						izbraniVSmeri.add(izb); 
						premik ++;
					}
					//če je naslednji moj, zaključim s to linijo in dodam vse, dobljene točke v izbrani
					else if (polja[ii + premik*smerX][jj + premik*smerY] == naVrsti.dobiPolje()) {
						izbrani.addAll(izbraniVSmeri);
						break;
					}
				}
			}	
			
		}
		return izbrani; //vrnem vse žetone, ki jih s to potezo dobim
	}
	
	
	public void posodobiRezultat(int razlika) {
		Polje p1 = naVrsti.dobiPolje();
		Polje p2 = naVrsti.obrat().dobiPolje();
		int r1 = rezultat.get(p1);
		int r2 = rezultat.get(p2);
		rezultat.put(p1, r1 + razlika+1);
		rezultat.put(p2, r2-razlika);
	}
	
	public String rezultat() {
		//System.out.println("CRNI: " + rezultat.get(Polje.CRN) + "  BELI: " + rezultat.get(Polje.BEL));
		return  "CRNI: " + rezultat.get(Polje.CRN) + "       BELI: " + rezultat.get(Polje.BEL);
		
	}
	
	//vrne stanje igre. Očitno je mogoče da se celotna plošča sploh ne zapolni. Zato tok komplikacij. Za primer če noben nima možnosti sem "preprosto" (lol) vpelajl counter nemožnosti.
	public Stanje stanjeIgre() {
		if(rezultat.get(Polje.BEL) + rezultat.get(Polje.CRN) == N*N || stejMoznosti >= 2) {
			
			if(rezultat.get(Polje.CRN) < rezultat.get(Polje.BEL)) return Stanje.ZMAGA_BEL;
			else if (rezultat.get(Polje.CRN) > rezultat.get(Polje.BEL)) return Stanje.ZMAGA_CRN;
			else if (rezultat.get(Polje.CRN) == rezultat.get(Polje.BEL)) return Stanje.NEODLOCENO;
			
		}
		else if(rezultat.get(Polje.BEL) + rezultat.get(Polje.CRN) < N*N ) return Stanje.V_TEKU;
		System.out.println("Neki ne dela v stanjeIgre. Vračam NULL.");
		return null;
	}

	public Polje[][] getPlosca() {
		
		return polja;
	}
	
	
}
=======
package logika;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

import splosno.Poteza;

public class Igra {
	
	public Polje[][] polja;
	public Igralec naVrsti;
	private static Random random = new Random ();
	public ArrayList<Poteza> dovoljenePoteze;
	public static int N;
	public int stejMoznosti;
	
	public Stanje stanjeIgre;
	public Map<Polje, Integer> rezultat;
	
	public Igra() {
		
		N = 8;
		polja = new Polje[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				polja[i][j] = Polje.PRAZNO;
			}
		}
		
		polja[N/2-1][N/2-1] = Polje.CRN;
		polja[N/2][N/2-1] = Polje.BEL;
		polja[N/2-1][N/2] = Polje.CRN;
		polja[N/2][N/2] = Polje.BEL;

		naVrsti = Igralec.CRN;
		
		stejMoznosti = 0; //steje igre ko nihče nima na voljo poteze. Gre le do 2
		
		rezultat = new EnumMap<Polje, Integer>(Polje.class); //spremlja rezultat
		rezultat.put(Polje.CRN, 2);
		rezultat.put(Polje.BEL, 2);
		dovoljenePoteze = dovoljenePoteze(); //po vsaki poteze izračuna vse poteze ki so na voljo
		
		//moznePoteze();
		stanjeIgre = Stanje.V_TEKU; //stanje igre pač
		
	}
	
	//ustvarjanje nove igre. Kopiram vsako stvar
	public Igra(Igra igra) {
		this.polja = new Polje[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				this.polja[i][j] = igra.polja[i][j];
			}
		}
		this.stejMoznosti = igra.stejMoznosti;
		this.rezultat = igra.rezultat;
		this.naVrsti = igra.naVrsti;
		this.rezultat = new EnumMap<Polje, Integer>(Polje.class);
		rezultat.put(Polje.CRN, igra.rezultat.get(Polje.CRN));
		rezultat.put(Polje.BEL, igra.rezultat.get(Polje.BEL));
		this.stanjeIgre = igra.stanjeIgre;
		dovoljenePoteze = igra.dovoljenePoteze();
		moznePoteze();//preveri ali imata igralca sploh kakšno možnost
		
	}
	
	// odigram potezo
	public boolean odigraj(Poteza poteza) {
		if(dovoljenePoteze.contains(poteza)) { //preverim ali je poteza sploh izvedljiva
			ArrayList<Poteza> dobljeniZetoni = dobljeni(poteza); //dobljeni žetoni, ki jih dobim s potezo
			int i = poteza.getX();
			int j = poteza.getY();
			polja[i][j] = naVrsti.dobiPolje(); //spremenim vrednost polj
			for(Poteza izb : dobljeniZetoni) {
				int x = izb.getX();
				int y = izb.getY();
				polja[x][y] = naVrsti.dobiPolje(); //in jih nato obrne
			}
			posodobiRezultat(dobljeniZetoni.size()); //posodobim rezultat
			naVrsti = naVrsti.obrat(); //zamenjam igralca
			dovoljenePoteze = dovoljenePoteze(); //posodobim dovoljene poteze
			moznePoteze(); //preverim ali so poteze mozne
			stanjeIgre = stanjeIgre(); //posodobim stanje igre
			return true;
		}
		
		else {
			return false;
		}
		
	}
	
	//ali so poteze mozne
	private void moznePoteze() {
		
		if(dovoljenePoteze.size() == 0) {
			stejMoznosti +=1;
			naVrsti = naVrsti.obrat();
			dovoljenePoteze = dovoljenePoteze();
			if(dovoljenePoteze.size() == 0) {
				stejMoznosti +=1;
				naVrsti = naVrsti.obrat();
			}
			else stejMoznosti = 0;
		}
		else stejMoznosti = 0;
	}
	
	//ali je potezo mozno odigrati
	public boolean lahkoOdigram(Poteza poteza) {
		ArrayList<Poteza> dobljeniZetoni = dobljeni(poteza);
		if(dobljeniZetoni.size() != 0) {//vrne vse krogce na katere poteza vpliva 
			
			return true;
		}
		
		return false;
	}
	
	//najdi vse dovoljene poteze
	public ArrayList<Poteza> dovoljenePoteze(){
		ArrayList<Poteza> volni = new ArrayList<Poteza>();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				Poteza poteza = new Poteza(i,j);
				boolean dovoljena = lahkoOdigram(poteza);
				if (dovoljena) {
					volni.add(poteza);
				}
			}
		}
		return volni;
	}
	
	//vrne žetone ki jih dobim s potezo
	public ArrayList<Poteza> dobljeni(Poteza poteza) {
		ArrayList<Poteza> izbrani = new ArrayList<>();
		int ii = poteza.getX();
		int jj = poteza.getY();
		
		
		if (polja[ii][jj] == Polje.PRAZNO) {
			int[][] smeri = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1,1}, {-1,1}, {1,-1}, {-1,-1}}; // vse smeri pregleda
			
			for(int[] smer : smeri) { //ppregled v vseh smereh
				int smerX = smer[0];
				int smerY = smer[1];
				int premik = 1;
				ArrayList<Poteza> izbraniVSmeri = new ArrayList<>(); // seznam vseh dobljenih krogcev/točk... v tej smeri
				while (ii + premik*smerX >= 0 && ii + premik*smerX < N && jj + premik*smerY >= 0 && jj + premik*smerY < N){
					// ustvarim koordinato
					Poteza izb = new Poteza(ii + premik*smerX, jj + premik*smerY); 
					
					//če je nasledji prazen pogledam nasledjo vrstico
					if (polja[ii + premik*smerX][jj + premik*smerY] == Polje.PRAZNO) {
						izbraniVSmeri.clear();
						break;
					}
					//če je nasledji od nasprotnika si ga shranim in nadaljujem v liniji
					else if (polja[ii + premik*smerX][jj + premik*smerY] == naVrsti.obrat().dobiPolje()) {
						izbraniVSmeri.add(izb); 
						premik ++;
					}
					//če je naslednji moj, zaključim s to linijo in dodam vse, dobljene točke v izbrani
					else if (polja[ii + premik*smerX][jj + premik*smerY] == naVrsti.dobiPolje()) {
						izbrani.addAll(izbraniVSmeri);
						break;
					}
				}
			}	
			
		}
		return izbrani; //vrnem vse žetone, ki jih s to potezo dobim
	}
	
	
	public void posodobiRezultat(int razlika) {
		Polje p1 = naVrsti.dobiPolje();
		Polje p2 = naVrsti.obrat().dobiPolje();
		int r1 = rezultat.get(p1);
		int r2 = rezultat.get(p2);
		rezultat.put(p1, r1 + razlika+1);
		rezultat.put(p2, r2-razlika);
	}
	
	public String rezultat() {
		//System.out.println("CRNI: " + rezultat.get(Polje.CRN) + "  BELI: " + rezultat.get(Polje.BEL));
		return  "CRNI: " + rezultat.get(Polje.CRN) + "       BELI: " + rezultat.get(Polje.BEL);
		
	}
	
	//vrne stanje igre. Očitno je mogoče da se celotna plošča sploh ne zapolni. Zato tok komplikacij. Za primer če noben nima možnosti sem "preprosto" (lol) vpelajl counter nemožnosti.
	public Stanje stanjeIgre() {
		if(rezultat.get(Polje.BEL) + rezultat.get(Polje.CRN) == N*N || stejMoznosti >= 2) {
			
			if(rezultat.get(Polje.CRN) < rezultat.get(Polje.BEL)) return Stanje.ZMAGA_BEL;
			else if (rezultat.get(Polje.CRN) > rezultat.get(Polje.BEL)) return Stanje.ZMAGA_CRN;
			else if (rezultat.get(Polje.CRN) == rezultat.get(Polje.BEL)) return Stanje.NEODLOCENO;
			
		}
		else if(rezultat.get(Polje.BEL) + rezultat.get(Polje.CRN) < N*N ) return Stanje.V_TEKU;
		System.out.println("Neki ne dela v stanjeIgre. Vračam NULL.");
		return null;
	}

	public Polje[][] getPlosca() {
		
		return polja;
	}
	
	
}
>>>>>>> refs/remotes/origin/main
