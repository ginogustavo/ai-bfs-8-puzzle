import java.util.ArrayList;
import java.util.List;

/**
 * @author Gino Ureta
 */

public class Node {

    private Node parent = null;
    //private Node[] children = null;
    private int numberOfNodesExpanded = 0;
    
    public int getNumberOfNodesExpanded() {
		return numberOfNodesExpanded;
	}
	public void setNumberOfNodesExpanded(int numberOfNodesExpanded) {
		this.numberOfNodesExpanded = numberOfNodesExpanded;
	}
	public Node getParent(){return this.parent;}
    public void setParent(Node parent){this.parent = parent;}

    public String moves = "";

    public String getMoves(){return this.moves;}
    public void setMoves(String moves){this.moves = moves;}

    
    private int[][] state = null;
    public Node() {
    }
    public Node(Node newNode) {
        this.moves = newNode.moves;
    }
    

    public int[][] getState() {
        return state;
    }

    public void setState(int[][] state) {
        this.state = state;
    }


    public List<Action> getActions(int[][] state) {

        List<Action> possibleActions = new ArrayList<>();

        // Get Position of Zero in the Matrix
        int[][] xyPosition = getPositionZero(state);
        int xPosition = xyPosition[0][0];
        int yPosition = xyPosition[0][1];

        /*
         * According to the position of tile 0 and the limits in a 4x4 matrix (2d array)
         * possible actions can be added
         */
        if (xPosition >= 1) // if row(i) >=1, then 0 can move UP
            possibleActions.add(Action.UP);

        if (xPosition <= 2) // if row(i) <=2, then 0 can move DOWN
            possibleActions.add(Action.DOWN);

        if (yPosition >= 1) // if colum(j) >=1, then 0 can move LEFT
            possibleActions.add(Action.LEFT);

        if (yPosition <=2) // if colum(j) <=2, then 0 can move RIGHT
            possibleActions.add(Action.RIGHT);

        return possibleActions;
    }

    public int[][] getPositionZero(int[][] state) {
        int matrixRow = 4;
        int matrixColumn = 4;

        int[][] position0 = new int[1][2];

        for (int i = 0; i < matrixRow; i++) {
            for (int j = 0; j < matrixColumn; j++) {
                if (state[i][j] == 0) {
                    position0[0][0] = i;
                    position0[0][1] = j;
                    return position0;
                }
            }
        }
        return position0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        //if (obj != this)
        //    return false;

        final Node other = (Node) obj;
        if (other.getState() == null) {
            return false;
        }
        if (getState() == null) {
            return false;
        }

        if (other.getState().length != getState().length) {
            return false;
        }

        for (int i = 0; i < other.getState().length; i++) {
            if (!java.util.Arrays.equals(other.getState()[i], this.getState()[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        long newdate = new java.util.Date().getTime();
        String str = String.valueOf(newdate);
        String sub = str.substring( str.length()-3 );
        int ramdondata = Integer.parseInt(sub);
        
        hash = 23 * hash + (getState()!= null ? this.getState()[0][0]: 0);
        hash = 23 * ramdondata;
        return hash;
    }

}