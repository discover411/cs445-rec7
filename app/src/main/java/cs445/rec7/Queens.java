package cs445.rec7;

import java.util.Arrays;

public class Queens {
    /**
     * Checks if a partial solution is a complete solution.
     * @param partial The partial solution
     * @return true if the partial solution is complete, false otherwise.
     */
    public static boolean isFullSolution(int[] partial) {
        // TODO: Implement this method
    for (int i =7; i>=0; i--)
    { 	
    	if (partial[i] == 0) 
    	{
    		return false;
    	}

    }   	
    //if ( partial[7] ==0, return false;
        return true;
        
    }

    /**
     * Checks if a partial solution should be rejected because it can never be extended to a
     * complete solution.
     * @param partial The partial solution
     * @return true if the partial solution should be rejected, false otherwise.
     */
    public static boolean reject(int[] partial) { //check if 2 queens in same row
        // TODO: Implement this method			//checking queens along diagonals
    	for (int i =0; i < 8; i++)
    	{
    		for (int j = 0; j<i; j++)
    		{
    			//this checks the rows
    			//if the slope or diagonal between queens
    			
    			if (partial[i] ==0 || partial[j] ==0) {
    				return false;
    			}
    			else if (i !=j && partial[i]==partial[j]) //this means that it exists on the same row? <<CHECK!!
    			{
    				//it's in the same row!
    				return true;
    			} 
    			else if (partial[j] - partial[i]== j-i)
    				{
    				//Along the positive diagonal
    				return true;
    				} else if (partial[j] - partial[i] == i-j) {
    					//Along the negative diagonal
    					return true;
    					
    				}
    			
    		}
    		
    	}
    	
    	//no reason to reject; queens do not exists on same row or diagonals
        return false;
    }

    /**
     * Extends a partial solution by adding one additional queen.
     * @param partial The partial solution
     * @return a partial solution with one more queen added, or null if no queen can be added.
     */
    public static int[] extend(int[] partial) {
        // TODO: implement this method
    	int[] temp = new int[8];  //Note: we need the copy because we need to keep track of what we are doing
    	for(int i =0; i<8; i++) {
    		if(partial[i] ==0) {
    			temp[i] =1;
    			return temp;
    			//one issue this method will cause is that we are only adding the new queen to the copy; 
    			//so all of the old queens still need to be added over
    		} else {
    			temp [i] = partial [i];
    		}
    		
    	}
    	//Nowhere to put a new Queen, we CANNOT extend
        return null;
    }

    /**
     * Moves the most recently-placed queen to its next possible position.
     * @param partial The partial solution
     * @return a partial solution with the most recently-placed queen moved to its next possible
     * position, or null if we are out of options for the most recent queen.
     */
    public static int[] next(int[] partial) { 
        // TODO: implement this method
    	/*
    	 TO find the most recent queen, use the while loop below; 
    	 if  at the last index (the int = 7) or where we are currently at is 0, 
    	 then that's where the most recent queen is at! 
		(i.e. it's at the last column or inbetween)
		Otherwise (the else), it's not the last queen, 
		then just copy the values of the old queens over

    	 */
    	
    	int[] temp = new int[8];
    	int i = 0;
    	while (i<8) {
    		if (i == 7 || partial[i +1] ==0) {
    				if (partial[i]>=8)
    				{
    					return null;
    					//queen will go off the board
    				} else 
    					{
    					//move the queen down a row
    					temp[i] = partial[i] + 1;
    					break;
    					}
    			
    		}else {
    			//this is not the last queen
    			temp[i] = partial[i];
    		}
    		i++;   		   		
    	}
    	
        return temp;
    }

    /**
     * Solves the 8-queens problem and outputs all solutions
     * @param partial The partial solution
     */
    public static void solve(int[] partial) {
        if (reject(partial)) return;
        if (isFullSolution(partial)) System.out.println(Arrays.toString(partial));
        int[] attempt = extend(partial);
        while (attempt != null) {
            solve(attempt);
            attempt = next(attempt);
        }
    }

    /**
     * Solves the 8-queens problem and returns one solution
     * @param partial The partial solution
     * @return A full, correct solution
     */
    public static int[] solveOnce(int[] partial) {
        if (reject(partial)) return null;
        if (isFullSolution(partial)) return partial;
        int[] attempt = extend(partial);
        while (attempt != null) {
            int[] solution = solveOnce(attempt);
            if (solution != null) return solution;
            attempt = next(attempt);
        }
        return null;
    }



    public static void main(String[] args) {
        if (args.length >= 1 && args[0].equals("-a")) {
            // Find all solutions starting from the empty solution
            solve(new int[] {0, 0, 0, 0, 0, 0, 0, 0});
        } else {
            // Find one solution starting from the empty solution
            int[] solution = solveOnce(new int[] {0, 0, 0, 0, 0, 0, 0, 0});
            System.out.println(Arrays.toString(solution));
        }
    }
}

