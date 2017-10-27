/**
 * This class represents the basic type TermWork in the CS Department
 * Specification. Must be comparable by name
 * 
 * Written by : Abe Gustafson
 */
public class TermWork implements Comparable{

	// 
    // Each TermWork has a unique name.
    //
    
    private String name;
	
    /**
     * This is the constructor method. 
     * @param termWorkName the name of the TermWork.
     */

    public TermWork (String termWorkName) {
	name = termWorkName;
    }

    /**
     * This method compares an object of type 'TermWork' with the 
     * current object. They are compared based on the names in 
     * the objects.
     * @param otherTermWork the other termWork object being 
     * compared.
     * @return integer indicating the result of comparison
     *    0 - indicates both objects are equal
     *    negative - indicates that the current object is 
     *       lexicographically less than the parameter
     *    positive - indicates the current object is 
     *       lexicographically greater than the parameter. 
     */
    
     public int compareTo (Object otherTermWork) {
         return this.name.compareToIgnoreCase 
              (((TermWork) otherTermWork).getName());
     }
    
    /**
     * This is a redefinition of the 'equals' methods defined in 
     * the class Object. Basically, it asserts that two TermWorks 
     * are equal if their names are equal.
     * @param otherStudent the name of the other TermWork.
     * @return the boolean result indicating whether or not the 
     *   current TermWOrk represented by this object is equal to 
         the other TermWork object.
     */
    
     public boolean equals (TermWork otherTermWork) {
        return this.name.equalsIgnoreCase
               (otherTermWork.getName());
     }
    
    /**
     * Method to get the value of the private attribute 'name'.
     * @return the name of the TermWork.
     */

    public String getName() {
	return name;
    }
}
