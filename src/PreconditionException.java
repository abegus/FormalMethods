

/**
 * This class represents precondition violation in the Student Council Program.
 * An object of this class is created when a precondition in an operation is violated.
 * @author kasi
 */

public class PreconditionException extends Exception {

    
    /** 
     * Customized custructor.
     * @param className The name of the class from which the exception is thrown.
     * @param method The name of the method from which the exception if thrown.
     * @param message The additional message to be displayed.
     */
    
    public PreconditionException (String className, String method, String message) {
        System.out.println ("\n\n Class " + className + "::" + " Method " + method + ":" +
                            " Precondition for this method is violated\n " + message + "\n\n");
    }
}
