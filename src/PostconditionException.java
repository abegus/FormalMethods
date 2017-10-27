

/**
 * This class represents the post-condition exception in the Student Council program.
 * An object of this class is created when a postcondition in any operation is violated.
 *
 * Written by: Kasi Periyasamy
 * Date: April 21, 2009
 */

public class PostconditionException extends Exception {

    /** 
     * Customized custructor.
     * @param className The name of the class from which the exception is thrown.
     * @param method The name of the method from which the exception if thrown.
     */
    
    public PostconditionException (String className, String method, String message) {
        System.out.println ("\n\n Class " + className + "::" + " Method " + method + ":" +
                            " Postcondition for this method is violated\n " + message + "\n\n");
    }
}
