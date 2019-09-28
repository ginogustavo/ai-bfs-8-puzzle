import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class TreeSearch{

    Queue<Node> frontier = new LinkedList<Node>();  // FIFO_Queue
    int[][] goal_state = new int[4][4];             // Target goal configuration

    //Initializing the Target goal configuration
    public TreeSearch(){
        int tileNumber = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(i==3 && j ==3){
                    goal_state[i][j] = 0;
                }else
                    goal_state[i][j] = tileNumber;
                tileNumber++;        
            }
        }
    }

    int numberOfNodes =0;
    
    public Node breadthFirstSearch(int[][] initialState){

        Node node = new Node();
        
        node.setState( initialState );

        if ( goalTest(initialState) ) {
            return node;
        }
        
        frontier.add(node);   // Add first unique element

        //TODO: add an  "explored" empty set.

        while( !frontier.isEmpty() ) {  // loop until frontier is empty
        	
            Node shallowestNode = frontier.poll();
            numberOfNodes++;
            
            int[][] pState = shallowestNode.getState();
            String pMoves = shallowestNode.getMoves();
            
            //TODO: add this node.state to explored set

            //Getting the list of actions available per Node
            List<Action> actions =  shallowestNode.getActions( shallowestNode.getState() );
            
            for (Action action : actions) {
            
            	//Per each Action, generate the next Child Node
                Node childNode = getChildNode( pState, pMoves, action);
                
                //TODO: add node chide to the explorer
                //Do not repeat and state already in the Queue.
                if (!frontier.contains(childNode)){
                    
                	//Validate GOAL TEST
                     if( goalTest(childNode.getState()) ){
                    	 childNode.setNumberOfNodesExpanded( numberOfNodes );
                         return childNode;
                     }
                     frontier.add(childNode);  
                }
                

            }

        }

        return node;

    }

    //TODO why send this the problem?
    /**
     * New Child with state already updated
     * @param parent
     * @param action
     * @return
     */
    public Node getChildNode(int[][] pState, String pMoves, Action action){

        Node newNode = new Node();
        newNode.moves = pMoves;

        int[][] xyPosition =  newNode.getPositionZero( pState );
        int oldXPosition = xyPosition[0][0]; int newXPosition = xyPosition[0][0]; //i
        int oldYPosition = xyPosition[0][1]; int newYPosition = xyPosition[0][1]; //j

        //find the new position
        switch (action) {
            case UP:
                newXPosition--;
                newNode.moves+= "U";
                break;
            case DOWN:
                newXPosition++;
                newNode.moves+= "D";
                break;
            case LEFT:
                newYPosition--;   
                newNode.moves+= "L"; 
                break;
            case RIGHT:
                newYPosition++;
                newNode.moves+= "R";
                break;
            default:
                break;
        }

        int valueToExchange = pState[newXPosition][newYPosition];

        //make copy of the state to process it
        int[][] tempNew = new int[4][4];
        for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				tempNew[i][j] = pState[i][j]; 
			}
		}
        
      //Exchange values
        tempNew[newXPosition][newYPosition] = 0;
        tempNew[oldXPosition][oldYPosition] = valueToExchange;
        
        newNode.setState(tempNew);
        return newNode;
    }

	//Test the goal by comparing the given state(array) with the target
    public boolean goalTest(int[][] nodeState){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if ( nodeState[i][j] != goal_state[i][j]){    // if only one is different
                    return false;                               // goal test failed
                }
            }
        }
        return true; // by default
    }

}
