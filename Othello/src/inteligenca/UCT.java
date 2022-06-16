package inteligenca;


import java.util.Collections;
import java.util.Comparator;



public class UCT {

    public static double uctValue(int totalVisit, double nodeWinScore, int nodeVisit) {
   
        return (nodeWinScore / (double) nodeVisit) + 1.41 * Math.sqrt(Math.log(totalVisit) / (double) nodeVisit);
    }

    static Veja findBestNodeWithUCT(Veja node) {
        int parentVisit = node.visits;
       return Collections.max(
          node.otroci,
          Comparator.comparing(c -> uctValue(parentVisit, c.wins, c.visits)));
        
        //scene za MCTS2
        /*double najbolsiScore = 0;
        Veja najjacaVeja = node;
        for(Veja v : node.otroci) {
        	
        	double uctV = uctValue(parentVisit, v.wins, v.visits);
        	if(najbolsiScore <= uctV) {
        		najbolsiScore = uctV;
        		najjacaVeja = v;
        	}
        }
        
        return najjacaVeja;*/
    }
    
}