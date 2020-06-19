import java.util.Comparator;

/**
 * Custom comparator that overrides the compare method and looks at total cost value.
 * It sorts nodes based on increasing order (Minimal value to the front).
 */
public class NodeComparator implements Comparator<Node>{
	
	public int compare(Node a, Node b) 
	{
        if (a.getTotalCost() < b.getTotalCost()) {
            return -1;
        }
        if (a.getTotalCost() > b.getTotalCost()) {
            return 1;
        }
        return 0;
    }


}
