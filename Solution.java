import java.util.ArrayList;

/**
 * This class was made for presenting the solution, or no solution, to the user.
 */
public class Solution {
	
	/**
	 * Takes in parameters from the various search algorithms and prints the solution.
	 */
	public static void performSolution(Node goal, Node root, int nodes_popped, int queue_max_size)
	{
		ArrayList<Node> solutionPath = new ArrayList<Node>();
		
		int costTotal = 0;
		int path_length = 0;
		String start = root.getState();
		
		solutionPath.clear();
		solutionPath.add(goal);
		
		// Determining the path length with an accumulator loop
		while(!goal.getState().equals(root.getState()))
		{
			solutionPath.add(goal.getParent());
			goal = goal.getParent();	
			path_length++;
		}
		
		// Runs through the ArrayList and accumulates total cost. Starts from the root state to the goal state
		for (int i = solutionPath.size() - 1; i >= 0; i--)
		{
			costTotal += solutionPath.get(i).cost;
			String end = solutionPath.get(i).getState();
			String move = moveMade(start, end);
			start = end;
			
			System.out.println(solutionPath.get(i).getState());
			System.out.println("MOVE: " + move + " COST: " + solutionPath.get(i).cost + " TOTALCOST: " + costTotal);
		}
		
		System.out.println("Total Nodes popped off the queue: " + nodes_popped);
		System.out.println("Queue max size: " + queue_max_size);
		System.out.println("Path Length: " + path_length);
		System.out.println("----------------------------------------------------------");
	}
	
	/**
	 * Used for iterative deepening. Prints no solution
	 */
	public static void noSolution(int limit)
	{
		System.out.println("No solution found at depth: " + limit);
	}
	
	/**
	 * Determines the move from the start state to the next state by
	 * comparing the index of the 0 character. There is a pattern and based
	 * on the difference between them determines the move made.
	 */
	public static String moveMade (String start, String end)
	{
		int subtracted_num = end.indexOf('0') - start.indexOf('0');
		
		if (subtracted_num == 1)
		{
			return "Left";
		}
		
		else if (subtracted_num == 3)
		{
			return "Up";
		}
		
		else if (subtracted_num == -1)
		{
			return "Right";
		}
		
		else if (subtracted_num == -3)
		{
			return "Down";
		}
		
		return null;
	}

}
