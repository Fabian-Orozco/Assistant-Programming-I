package Practice2;

import java.util.Scanner;

import javax.swing.tree.TreePath;

/**
 * Calculates the next player after the music stops in the hot potato game
 */
public class Solution2_3
{
	/**
	 * Gets data from standard input
	 */
	private Scanner input = null;

	/**
	 * Start the execution of the solution
	 * @param args Command line arguments
	 */
	public static void main(String args[])
	{
		Solution2_3 solution = new Solution2_3();
		solution.run();
	}

	/**
	 * Run the solution. This method is called from main()
	 */
	public void run()
	{
		// Create object to read data from standard input
		this.input = new Scanner(System.in);


        // Modify this code to solve the problem
        
        long total_players = 0, player = 0, seconds = 0;
        Boolean invalid_data = false;
        if (this.input.hasNextLong()) {
            total_players = this.input.nextLong();
            if (this.input.hasNextLong()) {
                player = this.input.nextLong();
                if (this.input.hasNextLong()) {
                    seconds = this.input.nextLong();
                } else {
                    invalid_data = true;
                }
            } else {
                invalid_data = true;
            }
        } else {
            invalid_data = true;
        }

        if (invalid_data || total_players < 1 || player < 1 || total_players < player || seconds < 1) {
            System.out.printf("invalid data");
        } else {
            long next_player = (player + seconds - 1) % total_players;
            if (next_player == 0) {
                next_player = total_players;  
            }
            System.out.printf("%d", next_player);
        }
        
		// Close the standard input
		this.input.close();
	}
}