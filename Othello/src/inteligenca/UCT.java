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
          Comparator.comparing(c -> uctValue(parentVisit, (c.visits - c.wins), c.visits)));
    }
}