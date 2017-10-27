import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * This program represents the prototype of the Course specification.
 * The basic type STUDENT,TermWork, and Mark is implemented as a class. See the   
 * class 'Student', 'TermWork', 'Mark' for its internal details.
 * This program uses built in datatypes of TreeSets, ArrayLists, and TreeMaps to keep track
 * of the Sets, Sequences, and Functions respectively. There is also an enum Grade
 *
 * Written by : Abe Gustafson
 * Date : April 27, 2017
 */
public class Course implements Comparable {

	private String courseNumber;
	//All of the Students
	private TreeSet<Student> students;
	//All of the TermWorks
	private ArrayList<TermWork> termworks;
	//All of the Marks
	private TreeMap<Student, ArrayList<Mark>> marks;
	//Is the course Required. Replaced the Core/Elective enum to a Boolean because it is 
	//A binary relationship..
	private boolean isRequired;
	//Enum for the Grade.
	public enum Grade {
	    PASS,FAIL
	}
	
	/*
	 * Implements the Sum function
	 * Takes an arraylist of marks as input,
	 * returns the sum as output
	 */
	private double sum(ArrayList<Mark> ms){
		
		if( ms.size() == 0){
			return 0;
		}
		else{
			double theSum = ms.get(0).getMark();
			ms.remove(0);
			return theSum + sum(ms);
		}
	}
	
	/*
	 * Implements the Passed function
	 * Takes an arraylist of marks input
	 * returns true or false
	 */
	private boolean passed(ArrayList<Mark> ms){
		if( ms.size() > 0 && (sum((ArrayList<Mark>) ms.clone())/ ms.size()) >= 50.0 ){
			return true;
		}
		return false;
	}
	
	/*
	 * Constructor. courseNum is courseNumber, courseType is true for Required
	 * Instantiates all of the data structures
	 */
	public Course(String courseNum, boolean courseType){
		students = new TreeSet<Student>();
		termworks = new ArrayList<TermWork>();
		courseNumber = courseNum;
		marks = new TreeMap<Student, ArrayList<Mark>>();
		isRequired = courseType;
	}
     
     /*	
      * This method implements EnrollStudent
      * Input: the new student
      * no output
      */
     public void EnrollStudent(Student newStudent) throws Exception{
    	 //State Invariant
    	 if (!stateInvariantCheck()) 
             throw new InvariantException ("Course", "EnrollStudent", "before");
    	 
    	 //Precondition student not a member of students
    	 if (students.contains (newStudent)) 
             throw new PreconditionException
               ("Course", "EnrollStudent", "New Student is already a member students");
    	 //Precondition: student is not a member of the domain of marks
    	 if (marks.keySet().contains (newStudent)) 
             throw new PreconditionException
               ("Course", "EnrollStudent", "New Student is already a member dom marks");
    	 
    	 //PostCondition says that students' = students U newStudent
    	 students.add(newStudent);
    	 //PostCondition. Create new sequence of Marks == size of #termworks 
    	 // add newStudent -> seqMarks to the marks'
    	 ArrayList<Mark> smarks = new ArrayList<Mark>();
    	 for(int i = 0; i < termworks.size(); i++){
    		 Mark m = new Mark(0);
    		 smarks.add(m);
    	 }
    	 marks.put(newStudent, smarks);
    	 
    	 //State Invariant
    	 if (!stateInvariantCheck()) 
             throw new InvariantException ("Course", "EnrollStudent", "after");
    	 
    	 return;
     }
     
     /*
      * This Method implements DropSTudent
      * Input: Student
      * Output: none
      */
     public void DropStudent(Student whichStudent) throws Exception{
    	//State Invariant
    	 if (!stateInvariantCheck()) 
             throw new InvariantException ("Course", "DropStudent", "before");
    	 
    	 //Precondition student isnt a member of students
    	 if (!students.contains (whichStudent)) 
             throw new PreconditionException
               ("Course", "DropStudent", "New Student isnt already a member students");
    	 //Precondition: Student isnt a member of the domain of marks
    	 if (!marks.keySet().contains (whichStudent)) 
             throw new PreconditionException
               ("Course", "DropStudent", "New Student isnt already a member dom marks");
    	 
    	 //Postcondition: remove whichStudent from students and marks
    	 students.remove(whichStudent);
    	 marks.remove(whichStudent);
    	 
    	//State Invariant
    	 if (!stateInvariantCheck()) 
             throw new InvariantException ("Course", "DropStudent", "after");
    	 
    	 return;
     }
     
     /*
      * This Method implements AddTermWork
      * Input: new termwork
      */
     public void AddTermWork(TermWork newTermWork) throws Exception{
    	//State Invariant
    	 if (!stateInvariantCheck()) 
             throw new InvariantException ("Course", "AddTermWork", "before");
    	 
    	 //Precondition: newTermWork isnt in termworks
    	 for(TermWork tw : termworks){
    		 if(tw.equals(newTermWork)){
    			 throw new PreconditionException
                 ("Course", "AddTermWork", "New termwork already exists in termworks");
    		 }
    	 }
    	 
    	 //Postcondition: add newTermWork to termworks
    	 termworks.add(newTermWork);
    	 //Postcondition: add a new mark of zero to each mark[] in marks
    	 Set<Student> domMarks = marks.keySet();
    	 for(Student stud : domMarks){
    		 //get the set of marks for the given student, add 0 to it
    		 ArrayList<Mark> studMarks = marks.get(stud);
    		 Mark m = new Mark(0);
    		 studMarks.add(m);
    		 //should overwrite the existing arraylist
    		 marks.put(stud, studMarks);
    	 }
    	 
    	//State Invariant
    	 if (!stateInvariantCheck()) 
             throw new InvariantException ("Course", "AddTermWork", "After");
    	 
    	 return;
     }
     
     /*
      * This method implements ChangeTermWork
      * Input: Student , Mark, Termwork
      * Changes a grade on a specific termwork for the student
      */
     public void ChangeTermWork(Student forWho, Mark newMark, TermWork whichTermWork) throws Exception{
    	//State Invariant
    	 if (!stateInvariantCheck()) 
             throw new InvariantException ("Course", "ChangeTermWork", "Before");
    	 
    	 //Precondition: forWho isnt in students or dom marks 
    	 if (!students.contains (forWho)) 
             throw new PreconditionException
               ("Course", "ChangeTermWork", "Student isnt a member students");
    	 if (!marks.keySet().contains (forWho)) 
             throw new PreconditionException
               ("Course", "ChangeTermWork", "Student isnt a member dom marks");
    	 //Precondition: Termwork isnt in termworks
    	 if(!termworks.contains(whichTermWork))
    		 throw new PostconditionException
             ("Course", "ChangeTermWork", "Termwork does not exist in termworks");
    	 
    	 //Postcondition: change the mark for forWho in marks
    	 //index of Marks found by being the same as the index for termworks
    	 int whichIndex = termworks.indexOf(whichTermWork);
    	 //get the set of marks for the given student, update the mark
		 ArrayList<Mark> studMarks = marks.get(forWho);
		 studMarks.set(whichIndex, newMark);
		 
		 //should overwrite the existing arraylist
		 marks.put(forWho, studMarks);
		 
		//State Invariant
    	 if (!stateInvariantCheck()) 
             throw new InvariantException ("Course", "ChangeTermWork", "After");
    	 
    	 return;
     }
     
     /*
      * Method for Report Grade
      * Input: Student
      * Reports the grade for the given student
      * Output: Grade. Either PASS or FAIL
      */
     public Grade ReportGrade(Student forWho) throws Exception{
    	//State Invariant
    	 if (!stateInvariantCheck()) 
             throw new InvariantException ("Course", "ReportGrade", "Before");
    	 
    	 //Precondition: forWho mem students and dom marks
    	 //Precondition forWho is in students and dom marks
    	 if (!students.contains (forWho)) 
             throw new PreconditionException
               ("Course", "ReportGrade", "Student isnt a member students");
    	 if (!marks.keySet().contains (forWho)) 
             throw new PreconditionException
               ("Course", "ReportGrade", "Student isnt a member dom marks");
    	 //PreCondition: student has at least 1 mark
    	 if(marks.get(forWho).size() < 1)
    		 throw new PreconditionException
             	("Course", "ReportGrade", "Student doesnt have more than 0 marks");
    	 
    	 //Postcondition: Grade is pass if passed == true, else fail
    	 Grade g = Grade.FAIL;
    	 ArrayList<Mark> ms = (ArrayList<Mark>) ( marks.get(forWho)).clone();
    	 if(passed(ms))
    		 g = Grade.PASS;
    	 
    	//State Invariant
    	 if (!stateInvariantCheck()) 
             throw new InvariantException ("Course", "ReportGrade", "After");
    	 
    	 return g;
     }
     
     /*
      * Method for WhoGotMaximum
      * Output: Student who got the highest grade in the class
      */
     public Student WhoGotMaximum() throws Exception{
    	//State Invariant
    	 if (!stateInvariantCheck()) 
             throw new InvariantException ("Course", "WhoGotMaximum", "Before");
    	 
    	 //Precondition: THere must be more than one student in the marks
    	 if(marks.size() < 1)
    		 throw new PreconditionException
          		("Course", "WhoGotMaximum", "No Students Exist");
    	 
    	 //Set the top student to the first student
    	// Student[] studs = (Student[]) students.toArray();
    	 Iterator<Student> iter =  students.iterator();
    	 int topStudentIndex = 0;
    	// double topMarks = sum(marks.get(studs[0]));
    	 
    	 //loop through and find the highest grade (the student)
    	 Student topStudent = students.first();
    	 while(iter.hasNext()){
    		 Student stu = iter.next();
    		 double curTop = sum((ArrayList<Mark>) marks.get(topStudent).clone());
    		 double curStud = sum((ArrayList<Mark>) marks.get(stu).clone());
    		 if( curTop < curStud){
    			 topStudent = stu;
    		 }
    	 }
    	 
    	//State Invariant
    	 if (!stateInvariantCheck()) 
             throw new InvariantException ("Course", "WhoGotMaximum", "After");
    	 
    	 //PostCondition: return the topStudent
    	 return topStudent;
     }
     
     
     
     
     /*
      * (1) dom marks = students
      * (2) forall students in marks, (s.t s1 ≠ s2) #(marks(s1)) == #(marks(s2))
      * (3) all s: dom marks • #(makrs(s)) = #termwork
      * i.e. every seq Mark.length in the marks must be the length of termworks
      */
     private boolean stateInvariantCheck(){
 		Set<Student> domMarks = marks.keySet();
 		// (1)
 		if( domMarks != null && !domMarks.equals(students) ){
 			return false;
 		}
 		
 		// (2), (3)
 		int size = termworks.size(); //set the size to #termworks
 		for(Student stud : domMarks){
 			ArrayList<Mark> marksForStudent = marks.get(stud);
 			//Both 2 and 3 (one or the other isn't equal)
 			if( size != marksForStudent.size()  ){
 				return false;
 			}
 		}
 		
 		return true;
     }   
     
     
     /**
      * Compares Courses based on courseNumbers
      * @param otherCourse the other Course object being 
      * compared.
      * @return integer indicating the result of comparison
      *    0 - indicates both objects are equal
      *    negative - indicates that the current object is 
      *       lexicographically less than the parameter
      *    positive - indicates the current object is 
      *       lexicographically greater than the parameter. 
      */
     
      public int compareTo (Object otherCourse) {
          return this.courseNumber.compareToIgnoreCase 
               (((Course) otherCourse).getCourseNumber());
      }
      
      public boolean equals (Course otherCourse) {
          return this.courseNumber.equalsIgnoreCase
                 (otherCourse.getCourseNumber());
       }
      
      public String getCourseNumber(){
     	 return courseNumber;
      }
      public boolean Required(){
    	  return isRequired;
      }
      
      /*
       * Used to get the students by CSDepartment class
       */
      public TreeSet<Student> getStudents(){
     	 return students;
      }
      /*
       * Used to get the marks by the CSDepartment class
       */
      public TreeMap<Student, ArrayList<Mark>> getMarks(){
    	  return marks;
    	  
      }
      /*
       * Used to get the termworks by the CSDepartnemtn class
       */
      public ArrayList<TermWork> getTermWorks(){
    	  return termworks;
      }
      /*
       * Used to get a single termwork by name in CSDepartment class
       */
      public TermWork getTermWork(String name){
    	  for(TermWork c : termworks){
  			if(c.getName().equals(name) ){
  				return c;
  			}
  		}
  		return null;
      }
}
