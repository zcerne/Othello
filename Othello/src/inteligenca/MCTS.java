package inteligenca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import logika.Igra;
import logika.Igralec;
import logika.Stanje;
import splosno.Poteza;


public class MCTS extends Inteligenca {
	
	private static Random random = new Random ();
	
	private int obseg, count;
	
	
	public MCTS (int obseg) {
		this.obseg = obseg;
	}
	
	

	@Override
	public Poteza izberiPotezo (Igra igra) {
		this.count = 0;
		Poteza najboljsaPoteza = monte_carlo_tree_search(igra, this.obseg, igra.naVrsti);
		return najboljsaPoteza;	
	}
	
	public Poteza monte_carlo_tree_search(Igra igra, int globina, Igralec jaz) {
		Veja root = new Veja(igra, 0 , 0, null);
		while (count < obseg) {
			//System.out.println(0);
	        Veja list = izberivejo(root);
	        //System.out.println(1);
	        Stanje rezultat = simulacija(list);
	        //System.out.println(2);
	        backpropagate(list, rezultat);
	        //System.out.println(3);
	        count++;}
	    Veja node = best_child(root);
	    //System.out.println(node.getPoteza());
	    return node.getPoteza();
	    

	}
	    
	public Veja izberivejo(Veja node){
//		System.out.println(node.otroci.size());
//		
//		System.out.println(node.fullyexpanded());
	    while (node.fullyexpanded()) {
	        node = UCT.findBestNodeWithUCT(node);
	    }
	    return pick_univisted(node); // in case no children are present / node is terminal 
 }
	public Veja pick_univisted(Veja node) {
		if (!node.isterminal() && node.otroci.size() == 0) { 
			node.makebaby();
			//System.out.println("ali se razsiri" + node.otroci.size());
			return node.otroci.get(0);
			
		}
    	else {
    		for (Veja sin : node.otroci) {
        		if (sin.visits == 0) return sin;
        		//imam pripombe, bo žiga razmislil
        		// ga bom prepričal
        	
        	}
    	return node;
    	
		/*
		 * System.out.println("Neki je zelo narobe"); return null;
		 */
    	}
	}
	    
	
	public Stanje simulacija(Veja node) {
		
	
		Igra odigraj = new Igra(node.getIgra());
		
		//System.out.println(odigraj.stanjeIgre());
		while (odigraj.stanjeIgre() == Stanje.V_TEKU) {
			ArrayList<Poteza> na_voljo = odigraj.dovoljenePoteze();
//			System.out.println("Dovoljene potezeee " + na_voljo.size());
			int dolzina = na_voljo.size();
			Poteza poteza = null;
			if(dolzina != 0) {
				int randomIndex = random.nextInt(dolzina);
				poteza = na_voljo.get(randomIndex);
			}
			
			odigraj.odigraj(poteza);
			}
		return odigraj.stanjeIgre();
		}
	
	
	public void backpropagate(Veja node, Stanje stanje) {
		while (node.tata != null) {
			if (node.igra.naVrsti == Igralec.CRN && stanje == Stanje.ZMAGA_CRN) node.wins++;
			else if (node.igra.naVrsti == Igralec.BEL && stanje == Stanje.ZMAGA_BEL) node.wins++;
			else if (stanje == Stanje.NEODLOCENO) node.wins += 0.5;
			node.visits++;
			node = node.tata;
			
		}
		if (node.igra.naVrsti == Igralec.CRN && stanje == Stanje.ZMAGA_CRN) node.wins++;
		else if (node.igra.naVrsti == Igralec.BEL && stanje == Stanje.ZMAGA_BEL) node.wins++;
		else if (stanje == Stanje.NEODLOCENO) node.wins += 0.5;
		node.visits++;
	}
	
	public Veja best_child(Veja root) {
		//System.out.println("velikost otrok pred izbiro" + root.otroci.size());
	
		 return Collections.max(
		          root.otroci,
		          Comparator.comparing(c -> c.visits));
		    }
	}
	