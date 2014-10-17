package battleship2000.programlogic.control;

/**
 * A controller interface that all the program logic package's control classes implement.
 * 
 * @author Petri Kallio
 */
public interface Controller {
    
    /**
     * Method to execute the necessary actions of the controller class, returning
     * an object needed by the class that calls the method.
     * 
     * @return 
     */
    public Object execute();
}
