/**
 * This class represents the basic type STUDENT in the CS Department
 * Specification. Must be comparable by name
 * 
 * Written by : Abe Gustafson
 */


public class Student implements Comparable {
    // 
    // Each student has a unique name.
    //
    
    private String name;
	
    /**
     * This is the constructor method. 
     * @param studentName the name of the student.
     */

    public Student (String studentName) {
	name = studentName;
    }

    /**
     * This method compares an object of type 'Student' with the 
     * current object. They are compared based on the names in 
     * the objects.
     * @param otherStudent the other student object being 
     * compared.
     * @return integer indicating the result of comparison
     *    0 - indicates both objects are equal
     *    negative - indicates that the current object is 
     *       lexicographically less than the parameter
     *    positive - indicates the current object is 
     *       lexicographically greater than the parameter. 
     */
    
     public int compareTo (Object otherStudent) {
         return this.name.compareToIgnoreCase 
              (((Student) otherStudent).getName());
     }
    
    /**
     * This is a redefinition of the 'equals' methods defined in 
     * the class Object. Basically, it asserts that two students 
     * are equal if their names are equal.
     * @param otherStudent the name of the other student.
     * @return the boolean result indicating whether or not the 
     *   current student represented by this object is equal to 
         the other student object.
     */
    
     public boolean equals (Student otherStudent) {
        return this.name.equalsIgnoreCase
               (otherStudent.getName());
     }
    
    /**
     * Method to get the value of the private attribute 'name'.
     * @return the name of the student.
     */

    public String getName() {
	return name;
    }
}
