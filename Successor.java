import java.util.ArrayList;

/**
 * Successor function that determines the child nodes of the current node
 */
public class Successor {
	
	/**
	 * The index of the character "0" represents the blank space. Possible successors of that index are put into an ArrayList and returned.
	 * Index cheat sheet (012345678) corresponds to (0 1 2
	 * 												 3 4 5
	 * 												 6 7 8)
	 * 
	 * Returns an ArrayList of children nodes to the current node. It works by finding
	 * the index of the character 0 and then finding all possible moves on the board.
	 * It then creates new Strings by replacing the character at the index and possible 
	 * moves.
	 */
	public static ArrayList<String> findChildren(String state)
	{
		ArrayList<String> successor = new ArrayList<String>();
		int index = state.indexOf("0");
		
		/**
		 * Two possible moves, DOWN or RIGHT
		 */
		if (index == 0)
		{
			String child_1 = state.replace(state.charAt(0), 'x').replace(state.charAt(3), state.charAt(0)).replace('x', state.charAt(3));
			String child_2 = state.replace(state.charAt(0), 'x').replace(state.charAt(1), state.charAt(0)).replace('x', state.charAt(1));
			
			successor.add(child_1);
			successor.add(child_2);
			
		}
		
		/**
		 * Three possible moves, DOWN, LEFT or RIGHT
		 */
		else if (index == 1)
		{
			String child_1 = state.replace(state.charAt(1), 'x').replace(state.charAt(2), state.charAt(1)).replace('x', state.charAt(2));
			String child_2 = state.replace(state.charAt(1), 'x').replace(state.charAt(4), state.charAt(1)).replace('x', state.charAt(4));
			String child_3 = state.replace(state.charAt(1), 'x').replace(state.charAt(0), state.charAt(1)).replace('x', state.charAt(0));
			
			successor.add(child_1);
			successor.add(child_2);
			successor.add(child_3);
			
		}
		
		/**
		 * Two possible moves, DOWN or LEFT
		 */
		else if (index == 2)
		{
			String child_1 = state.replace(state.charAt(2), 'x').replace(state.charAt(5), state.charAt(2)).replace('x', state.charAt(5));
			String child_2 = state.replace(state.charAt(2), 'x').replace(state.charAt(1), state.charAt(2)).replace('x', state.charAt(1));
			
			successor.add(child_1);
			successor.add(child_2);
			
		}
		
		/**
		 * Two possible moves, UP or RIGHT
		 */
		else if (index == 3)
		{
			String child_1 = state.replace(state.charAt(3), 'x').replace(state.charAt(0), state.charAt(3)).replace('x', state.charAt(0));
			String child_2 = state.replace(state.charAt(3), 'x').replace(state.charAt(4), state.charAt(3)).replace('x', state.charAt(4));
			
			successor.add(child_1);
			successor.add(child_2);
			
		}
		
		/**
		 * Four possible moves, UP, DOWN, LEFT, or RIGHT
		 */
		else if (index == 4)
		{
			String child_1 = state.replace(state.charAt(4), 'x').replace(state.charAt(1), state.charAt(4)).replace('x', state.charAt(1));
			String child_2 = state.replace(state.charAt(4), 'x').replace(state.charAt(5), state.charAt(4)).replace('x', state.charAt(5));
			String child_3 = state.replace(state.charAt(4), 'x').replace(state.charAt(3), state.charAt(4)).replace('x', state.charAt(3));
			String child_4 = state.replace(state.charAt(4), 'x').replace(state.charAt(7), state.charAt(4)).replace('x', state.charAt(7));
			
			successor.add(child_1);
			successor.add(child_2);
			successor.add(child_3);
			successor.add(child_4);
			
			
		}
		
		/**
		 * Two possible moves, UP or LEFT
		 */
		else if (index == 5)
		{
			String child_1 = state.replace(state.charAt(5), 'x').replace(state.charAt(2), state.charAt(5)).replace('x', state.charAt(2));
			String child_2 = state.replace(state.charAt(5), 'x').replace(state.charAt(4), state.charAt(5)).replace('x', state.charAt(4));
			
			successor.add(child_1);
			successor.add(child_2);
			
			
		}
		
		/**
		 * Two possible moves, UP or RIGHT
		 */
		else if (index == 6)
		{
			String child_1 = state.replace(state.charAt(6), 'x').replace(state.charAt(3), state.charAt(6)).replace('x', state.charAt(3));
			String child_2 = state.replace(state.charAt(6), 'x').replace(state.charAt(7), state.charAt(6)).replace('x', state.charAt(7));
			
			successor.add(child_1);
			successor.add(child_2);
			
			
		}
		
		/**
		 * Three possible moves, UP, LEFT, or RIGHT
		 */
		else if (index == 7)
		{
			String child_1 = state.replace(state.charAt(7), 'x').replace(state.charAt(4), state.charAt(7)).replace('x', state.charAt(4));
			String child_2 = state.replace(state.charAt(7), 'x').replace(state.charAt(6), state.charAt(7)).replace('x', state.charAt(6));
			String child_3 = state.replace(state.charAt(7), 'x').replace(state.charAt(8), state.charAt(7)).replace('x', state.charAt(8));
			
			successor.add(child_1);
			successor.add(child_2);
			successor.add(child_3);
			
		}
		
		/**
		 * Two possible moves, UP or LEFT
		 */
		else if (index == 8)
		{
			String child_1 = state.replace(state.charAt(8), 'x').replace(state.charAt(5), state.charAt(8)).replace('x', state.charAt(5));
			String child_2 = state.replace(state.charAt(8), 'x').replace(state.charAt(7), state.charAt(8)).replace('x', state.charAt(7));
			
			successor.add(child_1);
			successor.add(child_2);
			
		}
		
		return successor;
	}

}
