package inteligenca;

import java.util.ArrayList;

import logika.Igra;
import logika.Igralec;
import logika.Stanje;
import splosno.Poteza;

public class Veja {
	
	public Igra igra;
	public double wins, porazi;
	public int visits;
	public Veja tata;
	public ArrayList<Veja> otroci;
	public Poteza poteza = null;

	public Veja(Igra igra, int visits, double wins, double porazi, Veja stars) {
		this.igra = igra;
		this.visits = visits;
		this.wins = wins;
		this.porazi = porazi;
		this.tata = stars;
		this.otroci = new ArrayList<Veja>();
		
		
	}
	public void makebaby() {
		for (Poteza p : igra.dovoljenePoteze()) {
			Igra od_p = new Igra(igra);
			od_p.dobljeni(p);
			Veja baby = new Veja(od_p, 0 ,0,0, this);
			baby.poteza = p;
			this.otroci.add(baby);
		}
	}
	
    public Igra getIgra() {
        return igra;
    }
    
    public boolean isterminal() {
    	if (igra.dovoljenePoteze().size() == 0) return true;
    	else return false;
    }
    
    public boolean fullyexpanded() {
    	
    	if (this.otroci.size() == 0) return false;
    	else if(this.otroci.size() != 0) {
    		for (Veja sin : this.otroci) {
        		if (sin.visits == 0) return false;
    			}
    	}
    	
    	return true;
    }
    
    void setIgra(Igra Igra) {
        this.igra = Igra;
    }

    public Igralec getPlayer() {
        return igra.naVrsti;
    }
    public Poteza getPoteza() {
    	return poteza;
    }

//    public int visits() {
//        return visits;
//    }

    public void setvisits(int visits) {
        this.visits = visits;
    }

    double wins() {
        return wins;
    }

    void setwins(double wins) {
        this.wins = wins;
    }

}
