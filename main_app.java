import java.util.Scanner;

/**
 * @author Jeffrey Veit, CSC 480 Assignment 1, 4/8/2020
 * Compile with "javac *java" and run with "java main_app"
 */

public class main_app {
	
	/**
	 * All states given in the assignment overview
	 */
	public static String goal_state = "123804765";
	public static String easy_state = "134862705";
	public static String medium_state = "281043765";
	public static String hard_state = "567408321";	
	
	
/**
 * Main method. Provides a text interface for the user to select what they would like to run.
 * Loops infinitely. 
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		Search search = new Search();
		
		while (true)
		{
			System.out.println("-- Menu --");
			System.out.println("1. BFS-Easy, 2. BFS-Medium, 3. BFS-Hard");
			System.out.println("4. DFS-Easy, 5. DFS-Medium, 6. DFS-Hard");
			System.out.println("7. IDS-Easy, 8. IDS-Medium, 9. IDS-Hard");
			System.out.println("10. UCS-Easy, 11. UCS-Medium, 12. UCS-Hard");
			System.out.println("13. GBFS-Easy, 14. GBFS-Medium, 15. GBFS-Hard");
			System.out.println("16. A*-Easy, 17. A*-Medium, 18. A*-Hard");
			System.out.println("19. A*2-Easy, 20. A*2-Medium, 21. A*2-Hard");
			
			System.out.println("Enter the number: ");
			int selection = input.nextInt();
			input.nextLine();
			
			switch (selection)
			{
			case 1:
			  search.bfs(easy_state);
			  break;
			  
			case 2:
				  search.bfs(medium_state);
				  break;
			
			case 3:
				  search.bfs(hard_state);
				  break;
				 
			case 4:
				  search.dfs(easy_state);
				  break;
				  
			case 5:
				  search.dfs(medium_state);
				  break;
				  
			case 6:
				  search.dfs(hard_state);
				  break;
			
			case 7:
				  search.ids(easy_state, 9);
				  break;
				  
			case 8:
				  search.ids(medium_state, 9);
				  break;
				  
			case 9:
				  search.ids(hard_state, 20);
				  break;
				  
			case 10:
				  search.ucs(easy_state);
				  break;
				  
			case 11:
				  search.ucs(medium_state);
				  break;
				  
			case 12:
				  search.ucs(hard_state);
				  break;
				  
			case 13:
				  search.greedyBFS(easy_state);
				  break;
				  
			case 14:
				  search.greedyBFS(medium_state);
				  break;
				  
			case 15:
				  search.greedyBFS(hard_state);
				  break;
				  
			case 16:
				  search.aEstrella(easy_state);
				  break;
				  
			case 17:
				  search.aEstrella(medium_state);
				  break;
				  
			case 18:
				  search.aEstrella(hard_state);
				  break;
				  
			case 19:
				  search.aEstrella2(easy_state);
				  break;
				  
			case 20:
				  search.aEstrella2(medium_state);
				  break;
				  
			case 21:
				  search.aEstrella2(hard_state);
				  break;
				  
			default:
			  System.out.println("Invalid selection.");
			  break;
			}
		}
		
	}

}
