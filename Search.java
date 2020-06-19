import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.PriorityQueue;

/**
 * The search class holds all of the search algorithms and helper functions. The goal booleans
 * are for each algorithm and labeled accordingly. Once an algorithm solves the problem they change
 * to true. After the solution is printed, they are changed back to false for another run. 
 *
 */

public class Search {
	
	public static boolean goal_bfs = false;
	public static boolean goal_dfs = false;
	public static boolean goal_ids = false;
	public static boolean goal_ucs = false;
	public static boolean goal_gbfs = false;
	public static boolean goal_aEstrella = false;
	public static boolean goal_aEstrella2 = false;
	
	
	/**
	 * 
	 * This is the breadth first search algorithm. It takes in a string from the main method
	 * and creates the root node. Two array lists are used to track visited states and also children
	 * of expanded nodes. The queue stores nodes to be examined for the goal state. The algorithm
	 * works by examining the current node to see if it matches the goal state. If it doesn't match,
	 * its added to the visited list and the successor function used to find its children. Those are then
	 * added to the queue and examined. This loops until a successor is found.
	 */
	public static void bfs (String input)
	{
		Node root_bfs = new Node (input);
		Node current = new Node(root_bfs.getState());
		
		ArrayList<String> visited = new ArrayList<String>();
		ArrayList<String> children = new ArrayList<String>();
		Queue<Node> queue_bfs = new LinkedList<Node>();
		
		int nodes_popped = 0;
		int queue_max_size = 0;
		int queue_size = 0;
		current.cost = 0;
		
		// Initial check for goal state
		goal_bfs = isGoal(current.getState());
		
		while(!goal_bfs)
		{
			// Add the current node to the visited array
			visited.add(current.getState());
			// Get the nodes children from the successor function
			children = Successor.findChildren(current.getState());
			
			for (String a : children)
			{
				// State checking, don't add already visited nodes to the queue
				if (visited.contains(a))
				{
					continue;
				}
				
					// Create child node from the children array and add it to the current node
					Node nino = new Node(a);
					current.children_nodes.add(nino);
					
					// Obtaining the path cost
					int cost = isCost(current.getState(), nino.getState());
					nino.cost = cost;
					nino.parent = current;
					
					// State check and adding the child to the queue. Increasing size counter
					if (!queue_bfs.contains(nino))
					{
						queue_bfs.add(nino);
						queue_size++;
					}
					
					
			}
			
			// Pop a node off the queue
			current = queue_bfs.poll();
			nodes_popped++;
			
			// Added this because my queue size variable was always one off based on where my goal check is
			if (queue_size > queue_max_size)
			{
				queue_max_size = queue_size;
			}
			
			// Decrease queue size because a node has been popped and check for goal state
			queue_size--;
			goal_bfs = isGoal(current.getState());
			
		}
		
		// Now that a solution has been found, set the boolean back to false for another run
		goal_bfs = false;
		System.out.println("BFS Solved!!");
		
		// Send metrics to be printed to the console
		Solution.performSolution(current, root_bfs, nodes_popped, queue_max_size);
			
	}
	
	/////////////////////////////////////////////////////////////////////////////
	/**
	 * The depth first search algorithm is set up very similar to breadth first search.
	 * I will not add all of the same comments as above unless it is a different implementation.
	 * Instead of using a queue, a stack is used because DFS is built upon LIFO. It traverses
	 * a tree down one branch as far as it can go until it has to backtrack.
	 */
	  public void dfs (String input)
	  {
		  	Node root_dfs = new Node (input);
		  	Node current = new Node(root_dfs.getState());
		  	
			ArrayList<String> visited = new ArrayList<String>();
			ArrayList<String> children = new ArrayList<String>();
			Stack<Node> stack_dfs = new Stack<Node>();
			
			int nodes_popped = 0;
			int stack_max_size = 0;
			int stack_size = 0;
			current.cost = 0;
			
			goal_dfs = isGoal(current.getState());
			
			while(!goal_dfs)
			{
				visited.add(current.getState());
				children = Successor.findChildren(current.getState());
				
				for (String a : children)
				{
					
					if (visited.contains(a))
					{
						continue;
					}
					
						Node nino = new Node(a);
						current.children_nodes.add(nino);
						
						int cost = isCost(current.getState(), nino.getState());
						nino.cost = cost;
						nino.parent = current;
						
						// Repeated state check
						if (!stack_dfs.contains(nino))
						{
							stack_dfs.add(nino);
							stack_size++;
						}
							
				}
				
				// Popping off the stack. LIFO architecture
				current = stack_dfs.pop();
				nodes_popped++;
				
				if (stack_size > stack_max_size)
				{
					stack_max_size = stack_size;
				}
				
				stack_size--;
				goal_dfs = isGoal(current.getState());
				
			}
			
			goal_dfs = false;
			System.out.println("DFS Solved!!");
			Solution.performSolution(current, root_dfs, nodes_popped, stack_max_size);
	  }
	
	/////////////////////////////////////////////////////////////////////////////
	/**
	 * Iterative deepening. This is DFS but with a depth limit. I struggled to get this working
	 * for a little while. I have two checks on the limit, the outer loop and also within the node itself.
	 * If the node depth is greater than the limit, than it will not be added to the stack.
	 */
	  public void ids (String input, int limit)
		{
			Node root_ids= new Node (input);
			Node current = new Node(root_ids.getState());
			
			ArrayList<String> visited = new ArrayList<String>();
			ArrayList<String> children = new ArrayList<String>();
			Stack<Node> stack_dfs = new Stack<Node>();
			
			int nodes_popped = 0;
			int stack_max_size = 0;
			int stack_max_total = 0;
			int depth = 0;
			
			current.cost = 0;
			current.depth = 0;
			
			goal_ids = isGoal(current.getState());
			
			while(depth <= limit)
			{
				if (goal_ids == true)
				{
					break;
				}
				
				// Clear the visited array for backtracking purposes
				visited.clear();
				
				while(!goal_ids)
				{
					visited.add(current.getState());
					children = Successor.findChildren(current.getState());
				
					// Depth limit check. This loop never runs if the node depth is greater then the limit
					if (current.getDepth() < limit)
					{
						for (String a : children)
						{
							if (visited.contains(a))
							{
								continue;
							}
							
							Node nino = new Node(a);
							current.children_nodes.add(nino);
						
							int cost = isCost(current.getState(), nino.getState());
							nino.cost = cost;
							nino.parent = current;
						
							// Repeated state check
							if (!stack_dfs.contains(nino))
							{
								stack_dfs.add(nino);
								stack_max_size++;
							}
							
						}
					}
					
					if (current.getDepth() >= limit - 1)
					{
						int copy = stack_max_size;
						
						if (copy > stack_max_total)
						{
							stack_max_total = copy;
						}
						
					}
					
					depth++;
					
					// If there is no solution found at the depth limit, return no solution
					if (stack_dfs.empty() == true)
						{
							Solution.noSolution(limit);	
							return;
						}
					
					current = stack_dfs.pop();
					
					// Set depth of node so it can be checked in next iteration
					current.setDepth(current.parent.getDepth() + 1);
					nodes_popped++;
					stack_max_size--;
					goal_ids = isGoal(current.getState());
				
				}
			}
			
			goal_ids = false;
			System.out.println("IDS Solved!!");
			
			if (stack_max_total > stack_max_size)
			{
				stack_max_size = stack_max_total;
			}
			
			Solution.performSolution(current, root_ids, nodes_popped, stack_max_size);
		}
	/////////////////////////////////////////////////////////////////////////////
	 /**
	  * Uniformed cost search. Uses a Priority Queue so that it is sorted with the comparator object.
	  * The pq is sorted based on least total cost of a node. The total cost is calculated by parent
	  * cost and child cost together.
	  */
	public void ucs (String input)
	{
		Node root_ucs = new Node (input);
		Node current = new Node(root_ucs.getState());
		
		NodeComparator ucs_comparator = new NodeComparator();
		PriorityQueue<Node> queue_ucs = new PriorityQueue<Node>(12, ucs_comparator);
		ArrayList<String> visited = new ArrayList<String>();
		ArrayList<String> children = new ArrayList<String>();
		
		int nodes_popped = 0;
		int pqueue_max_size = 0;
		int pq_size = 0;
		
		current.cost = 0;
		current.setTotalCost(0);
		
		goal_ucs = isGoal(current.getState());
		
		while(!goal_ucs)
		{
			visited.add(current.getState());
			children = Successor.findChildren(current.getState());
			
			for (String a : children)
			{
				
				if (visited.contains(a))
				{
					continue;
				}
				
					Node nino = new Node(a);
					current.children_nodes.add(nino);
					
					int cost = isCost(current.getState(), nino.getState());
					nino.cost = cost;
					nino.parent = current;
					
					// Setting total cost used for the priority queue
					nino.setTotalCost(nino.parent.getTotalCost() + nino.cost);
					
					if (!queue_ucs.contains(nino))
					{
						queue_ucs.add(nino);
						pq_size++;
					}
					
					// Repeated State checking. Removing the same node from PQ if the path cost is less then the current one 
					else if (queue_ucs.contains(nino))
					{
						for (Node original : queue_ucs)
						{
							if (original.equals(nino))
							{
								Node copy = original;
								if (nino.getTotalCost() < copy.getTotalCost())
								{
									queue_ucs.remove(copy);
									queue_ucs.add(nino);
								}
							}
						}
					}
			}
			
			current = queue_ucs.poll();
			nodes_popped++;
			
			if (pq_size > pqueue_max_size)
			{
				pqueue_max_size = pq_size;
			}
			
			pq_size--;
			goal_ucs = isGoal(current.getState());
			
		}
		
		goal_ucs = false;
		System.out.println("UCS Solved!!");
		Solution.performSolution(current, root_ucs, nodes_popped, pqueue_max_size);
	}
	/////////////////////////////////////////////////////////////////////////////////
	/**
	 * Set up in a very similar way to UCS. A priority queue is used and a comparator passed to it.
	 * The comparator sorts the queue based on total cost. However, in this method total cost is
	 * determined with a greedy cost method computing a difference value. 
	 */
	public void greedyBFS(String input)
	{
		Node root_gbfs = new Node (input);
		Node current = new Node(root_gbfs.getState());
		
		NodeComparator gbfs_comparator = new NodeComparator();
		PriorityQueue<Node> queue_gbfs = new PriorityQueue<Node>(12, gbfs_comparator);
		ArrayList<String> visited = new ArrayList<String>();
		ArrayList<String> children = new ArrayList<String>();
		
		int nodes_popped = 0;
		int pqueue_max_size = 0;
		int pq_size = 0;
		
		current.cost = 0;
		current.total_cost = 0;
		
		while(!goal_gbfs)
		{
			visited.add(current.getState());
			children = Successor.findChildren(current.getState());
			
			for (String a : children)
			{
				
				if (visited.contains(a))
				{
					continue;
				}
				
				
					Node nino = new Node(a);
					current.children_nodes.add(nino);
					visited.add(nino.getState());
					
					int cost = isCost(current.getState(), nino.getState());
					nino.cost = cost;
					nino.parent = current;
					int greedy_cost = greedybfsCost(nino.getState(), root_gbfs.getState());
					nino.setTotalCost(greedy_cost);
					
					if (!queue_gbfs.contains(nino))
					{
						queue_gbfs.add(nino);
						pq_size++;
					}
					
					// Repeated State checking. Removing the same node from PQ if the path cost is less then the current one 
					else if (queue_gbfs.contains(nino))
					{
						for (Node original : queue_gbfs)
						{
							if (original.equals(nino))
							{
								Node copy = original;
								if (nino.getTotalCost() < copy.getTotalCost())
								{
									queue_gbfs.remove(copy);
									queue_gbfs.add(nino);
								}
							}
						}
					}
					
					
			}
			
			current = queue_gbfs.poll();
			nodes_popped++;
			
			if (pq_size > pqueue_max_size)
			{
				pqueue_max_size = pq_size;
			}
			
			pq_size--;
			goal_gbfs = isGoal(current.getState());
			
		}
		
		goal_gbfs = false;
		System.out.println("GBFS Solved!!");
		Solution.performSolution(current, root_gbfs, nodes_popped, pqueue_max_size);
	}
	  
	/////////////////////////////////////////////////////////////////////////////
	/**
	 * The A* search algorithm. It is very similar to GBFS but it uses a different heuristic.
	 * The totalHeuristic cost is determined by total path cost and difference from the node to the 
	 * goal node. The comparator uses the totalHeuristic count to sort the Priority Queue
	 * unlike GBFS which uses totalCost only.
	 * 
	 * f(n) = g(n) + h(n)
	 */
	public void aEstrella (String input)
	{
		Node root_aEstrella = new Node (input);
		Node current = new Node(root_aEstrella.getState());
		
		HeuristicComparator aEstrella_comparator = new HeuristicComparator();
		PriorityQueue<Node> queue_gbfs = new PriorityQueue<Node>(12, aEstrella_comparator);
		ArrayList<String> visited = new ArrayList<String>();
		ArrayList<String> children = new ArrayList<String>();
		
		int nodes_popped = 0;
		int pqueue_max_size = 0;
		int pq_size = 0;
		
		current.cost = 0;
		current.setTotalCost(0);
		
		while(!goal_aEstrella)
		{
			visited.add(current.getState());
			children = Successor.findChildren(current.getState());
			
			for (String a : children)
			{
				
				if (visited.contains(a))
				{
					continue;
				}
				
				
					Node nino = new Node(a);
					current.children_nodes.add(nino);
					visited.add(nino.getState());
					
					int cost = isCost(current.getState(), nino.getState());
					nino.cost = cost;
					nino.parent = current;
					int greedy_cost = greedybfsCost(nino.getState(), root_aEstrella.getState());
					nino.setTotalCost(nino.parent.getTotalCost() + nino.cost);
					
					// f(n) = g(n) + h(n)
					nino.setHeuristicCost(nino.getTotalCost() + greedy_cost);
					
					if (!queue_gbfs.contains(nino))
					{
						queue_gbfs.add(nino);
						pq_size++;
					}
					
					// Repeated State checking. Removing the same node from PQ if the path cost is less then the current one 
					else if (queue_gbfs.contains(nino))
					{
						for (Node original : queue_gbfs)
						{
							if (original.equals(nino))
							{
								Node copy = original;
								if (nino.getHeuristicCost() < copy.getHeuristicCost())
								{
									queue_gbfs.remove(copy);
									queue_gbfs.add(nino);
								}
							}
						}
					}
					
			}
			
			current = queue_gbfs.poll();
			nodes_popped++;
			
			if (pq_size > pqueue_max_size)
			{
				pqueue_max_size = pq_size;
			}
			
			pq_size--;
			goal_aEstrella = isGoal(current.getState());
			
		}
		
		goal_aEstrella = false;
		System.out.println("A* Solved!!");
		Solution.performSolution(current, root_aEstrella, nodes_popped, pqueue_max_size);
	}
	/////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * The A*2 search algorithm. It is identical to A* but uses Manhattan Distance algorithm
	 * to compute the heuristic. 
	 */
	public void aEstrella2 (String input)
	{
		Node root_aEstrella2 = new Node (input);
		Node current = new Node(root_aEstrella2.getState());
		
		HeuristicComparator aEstrella_comparator = new HeuristicComparator();
		PriorityQueue<Node> queue_gbfs = new PriorityQueue<Node>(12, aEstrella_comparator);
		ArrayList<String> visited = new ArrayList<String>();
		ArrayList<String> children = new ArrayList<String>();
		
		int nodes_popped = 0;
		int pqueue_max_size = 0;
		int pq_size = 0;
		
		current.cost = 0;
		
		while(!goal_aEstrella2)
		{
			visited.add(current.getState());
			children = Successor.findChildren(current.getState());
			
			for (String a : children)
			{
				
				if (visited.contains(a))
				{
					continue;
				}
				
				
					Node nino = new Node(a);
					current.children_nodes.add(nino);
					visited.add(nino.getState());
					
					int cost = isCost(current.getState(), nino.getState());
					nino.cost = cost;
					nino.parent = current;
					int manhattanD = manhattanDistance(nino.getState(), root_aEstrella2.getState());
					nino.setTotalCost(nino.parent.getTotalCost() + nino.cost);
					
					// f(n) = g(n) + h(n)
					nino.setHeuristicCost(nino.getTotalCost() + manhattanD);
					
					if (!queue_gbfs.contains(nino))
					{
						queue_gbfs.add(nino);
						pq_size++;
					}
					
					// Repeated State checking. Removing the same node from PQ if the path cost is less then the current one 
					else if (queue_gbfs.contains(nino))
					{
						for (Node original : queue_gbfs)
						{
							if (original.equals(nino))
							{
								Node copy = original;
								if (nino.getHeuristicCost() < copy.getHeuristicCost())
								{
									queue_gbfs.remove(copy);
									queue_gbfs.add(nino);
								}
							}
						}
					}
					
			}
			
			current = queue_gbfs.poll();
			nodes_popped++;
			
			if (pq_size > pqueue_max_size)
			{
				pqueue_max_size = pq_size;
			}
			
			pq_size--;
			goal_aEstrella2 = isGoal(current.getState());
			
		}
		
		goal_aEstrella2 = false;
		System.out.println("A*2 Solved!!");
		Solution.performSolution(current, root_aEstrella2, nodes_popped, pqueue_max_size);
	}
	
	/**
	 * Simple helper method that checks if the current node is equal to the goal state.
	 */
	public static boolean isGoal(String check)
	{
		if (check.equalsIgnoreCase(main_app.goal_state))
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Determines the path cost of a move by looking at the parent node and child node.
	 * Based on where the 0 moves, the tile that replaced it is the cost value. This is based
	 * off the directions for the homework.
	 */
	public static int isCost(String parent, String child)
	{
		int index_parent = parent.indexOf("0");
		int cost = Character.getNumericValue(child.charAt(index_parent));
		
		return cost;
	}
	
	/**
	 * Method to determine the total cost for Greedy Best First Search. It takes the current
	 * node and the parent node and compares character to character in the same place. For 
	 * every difference between them, the accumulator is increased. This is done because
	 * GBFS determines cost by looking at the entire state and how different it is from the
	 * goal.
	 * 
	 * f(n) = h(n), where h(n) estimates the cost of getting from the node n to the goal
	 */
	public static int greedybfsCost(String state, String goal)
	{
		int cost = 0;
		
		for (int i = 0; i < goal.length(); i++)
		{
			// We don't count 0 as a tile
			if (state.charAt(i) == 0)
			{
				continue;
			}
			
			if (state.charAt(i) != goal.charAt(i))
			{
				cost++;
			}
		}
		
		return cost;
	}
	
	/**
	 * In this calculation, the outer for loop is the current state and the inner loop represents
	 * our goal state. So when the character in the outer loop equals the expected placement in the goal state
	 * i = j. If they are identical and not 0 (zero never actually moves) then the difference from that tile to its place is performed.
	 * The % operator represents the columns and / represents the rows. The 3 represents the three different tiles
	 * it can be in the row or column. The calculation is the expected distance from the current tile to the goal.
	 */
	public int manhattanDistance(String current, String goal) 
	{
        int cost = 0;
        
        for (int i = 0; i < current.length(); i ++)
        {
        	for (int j = 0; j < goal.length(); j ++)
        	{
        		 if (current.charAt(i) == goal.charAt(j) && current.charAt(i) != 0)
                 {
                     cost = cost + ((Math.abs(i % 3 - j % 3)) + Math.abs(i / 3 + j / 3));
                 }
        	}
        }
            
        return cost;
    }
	

}
