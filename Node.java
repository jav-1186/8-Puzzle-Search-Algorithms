import java.util.ArrayList;

public class Node {
	
/**
 * Node bookkeeping per the assignment slides
 */
public String state;
public ArrayList<Node> children_nodes = new ArrayList<Node>();
public int depth = 0;
public String move;
public boolean expanded = false;
public Node parent;
public int cost;
public int total_cost;
public int greedy_cost;
public int heuristic_cost;
	
	
/**
 * Constructor for the node class. Takes in a String
 */
public Node (String state)
	{
		this.state = state;
	}
	
/**
 * Return current state
 */
public String getState ()
{
	return state;
}

/**
 * Returns the children nodes of the current node
 */
public ArrayList<Node> getChildren()
{
	return children_nodes;
}

/**
 * Return parent of the current node
 */
public Node getParent ()
{
	return parent;
}

/**
 * Return depth of the current node
 */
public int getDepth()
{
	return depth;
}

/**
 * Set depth of current node
 */
public void setDepth(int input)
{
	depth = input;
}

/**
 * Return total cost of the current node. Used for UCS and GBFS
 */
public int getTotalCost()
{
	return total_cost;
}

/**
 * Set total cost for current node
 */
public void setTotalCost(int input)
{
	total_cost = input;
}

/**
 * Return heuristic cost value
 */
public int getHeuristicCost()
{
	return heuristic_cost;
}

/**
 * Set heuristic cost value
 */
public void setHeuristicCost(int input)
{
	heuristic_cost = input;
}

}
