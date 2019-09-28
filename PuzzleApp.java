import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * @author Gino Ureta
 */

public class PuzzleApp 
{
    public static void main( String[] args ){
        PuzzleApp app = new PuzzleApp();
        try {
            
            //String initial = "1 0 2 4 5 7 3 8 9 6 11 12 13 10 14 15";
            //String initial = "1 0 3 4 5 2 6 8 9 10 7 11 13 14 15 12";
            //String initial = "1 2 3 4 5 6 8 0 9 11 7 12 13 10 14 15";
            //String initial = "1 0 2 4 5 7 3 8 9 6 11 12 13 10 14 15";
            //String initial = "1 2 0 4 6 7 3 8 5 9 10 12 13 14 11 15";
            //String initial = "1 3 4 8 5 2 0 6 9 10 7 11 13 14 15 12";

        	//Reading input
            System.out.print("Enter initial state (Default:1 0 2 4 5 7 3 8 9 6 11 12 13 10 14 15): ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String initial = reader.readLine();
            if(initial == null || initial.isEmpty() || initial.length()==0) {
            	initial = "1 0 2 4 5 7 3 8 9 6 11 12 13 10 14 15";
            }
            reader.close(); 


            //Parsing the input
            Node initialNode = new Node();
            int[][] initial_state = app.parseProblemString( initial );
            initialNode.setState( initial_state );
            
             
            //Setting initial time and memory
            long startTime = System.currentTimeMillis();
            long startMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

            //Running Breadth First Search
            Node result = new TreeSearch().breadthFirstSearch( initialNode.getState() );
            
            //Setting final time and memory
            long endTime= System.currentTimeMillis();
            long timeElapsed = endTime - startTime;
            long endMemory=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
            long memoryUsed=((endMemory-startMemory)/1024);

            //Results
            System.out.println("\n\nMoves: "+result.moves);
            System.out.println("Nodes Expanded: "+result.getNumberOfNodesExpanded());
            System.out.println("Time taken: "+timeElapsed);
            System.out.println("Memory: "+ memoryUsed);

        } catch (Exception e) {
            System.out.println("Something went wrong, re-run the App");
        } finally{
        	System.out.println("\n\n"
        			+ "End of the program");
        	System.exit(0);	
        }
    }

    /**
     * Parse the input string to bi-dimensional array
     * @param string
     * @return
     */
    public int[][] parseProblemString(String string){
        int[][] problem = new int[4][4];
        String[] numbersarray = string.split(" ");
        int counter=0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                problem[i][j] = Integer.parseInt(numbersarray[counter]);
                counter++;
            }   
        }
        return problem;
    }

}
