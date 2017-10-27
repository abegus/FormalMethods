
import java.util.TreeSet;


/**
 * This program represents the prototype of the Course specification.
 * The basic type STUDENT is implemented as a class, Also Courses is a class. See the   
 * class 'Student', and 'CSDepartment' for its internal details.
 * This program uses built in datatypes of HashSets to keep track of the sets
 *
 * Written by : Abe Gustafson
 * Date : April 27, 2017
 */
public class CSDepartment {

	private TreeSet<Student> students;
	private TreeSet<Course> courses;
	
	
	/*
	 * The constructor method that implements init
	 */
	public CSDepartment() throws Exception{
		students = new TreeSet<Student>();
		courses = new TreeSet<Course>();
		
		/* create 3 required courses and 1 elective */
		Course r1 = new Course("CS741",true);
		Course r2 = new Course("CS742",true);
		Course r3 = new Course("CS743",true);
		Course e1 = new Course("CS521",false);
		
		courses.add(r1);
		courses.add(r2);
		courses.add(r3);
		courses.add(e1);
		
		//test (duplicate will overwrite...
		//courses.add(r1);
		//State Invariant
   	 	if (!stateInvariantCheck()) 
            throw new InvariantException ("CSDepartment", "Constructor", "After");
		
		return;
	}
	
	/*
	 * Method for EnrollStudentInCourse Operation
	 * Input: Course and Student to enroll
	 */
	public void EnrollStudentInCourse(Course course, Student newStudent) throws Exception{
		//State Invariant
   	 	if (!stateInvariantCheck()) 
            throw new InvariantException ("CSDepartment", "EnrollStudentInCourse", "Before");
   	 	
   	 	//Precondition: Course must be a member of Courses
   	 	if(course == null || !courses.contains(course))
   	 		throw new PreconditionException
   	 			("CSDepartment", "EnrollStudentInCourse", "course isnt a member courses");
   	 	//Precondition: newStudent must be a member of Students
	   	 if(newStudent == null || !students.contains(newStudent))
		 		throw new PreconditionException
		 			("CSDepartment", "EnrollStudentInCourse", "newStudent isnt a member students");
	   	
	   	 //PostCondition: remove the course from the courses, perform enrollment, re add course
	   	 course.EnrollStudent(newStudent);
	   	 courses.remove(course);
	   	 //course.EnrollStudent(newStudent);
	   	 courses.add(course);
	   	 
	   //State Invariant
	   	 if (!stateInvariantCheck()) 
	          throw new InvariantException ("CSDepartment", "EnrollStudentInCourse", "After");
	   	 	
	   	 return;
	}
	
	/*
	 * Method for DropStudentFromCourse operation
	 * Input: Course and student to drop from the course
	 */
	public void DropStudentFromCourse(Course course, Student whichStudent) throws Exception{
		//State Invariant
   	 	if (!stateInvariantCheck()) 
            throw new InvariantException ("CSDepartment", "DropStudentFromCourse", "Before");
   	 	
   	 	//Precondition: Course must be a member of Courses
   	 	if(course == null || !courses.contains(course))
   	 		throw new PreconditionException
   	 			("CSDepartment", "DropStudentFromCourse", "course isnt a member courses");
   	 	//Precondition: newStudent must be a member of Students
	   	 if(whichStudent == null || !students.contains(whichStudent))
		 		throw new PreconditionException
		 			("CSDepartment", "DropStudentFromCourse", "whichStudent isnt a member students");
	   	
	   //remove the course from the courses, perform drop, re add course
	   	 course.DropStudent(whichStudent);
	   	 courses.remove(course);
	   	 //course.DropStudent(whichStudent);
	   	 courses.add(course);
	   	 
	   //State Invariant
	   	 if (!stateInvariantCheck()) 
	          throw new InvariantException ("CSDepartment", "dropStudentFromCourse", "After");
	   	 	
	   	 return;
	}
	
	/*
	 * method for AddTermWorkToCourse operation
	 * Input: Adds a termwork to the Course
	 */
	public void AddTermWorkToCourse(Course course, TermWork tw) throws Exception{
		//State Invariant
   	 	if (!stateInvariantCheck()) 
            throw new InvariantException ("CSDepartment", "AddTermWorkToCourse", "Before");
   	 	
   	 	//Precondition: Course must be a member of Courses
   	 	if(course == null || !courses.contains(course))
   	 		throw new PreconditionException
   	 			("CSDepartment", "AddTermWorkToCourse", "course isnt a member courses");
   	 	
   	 	//PostCondition: remove the course from the courses, perform addition, re add course
	   	 course.AddTermWork(tw);
	   	 courses.remove(course);
	   	 courses.add(course);
	   	 
	   //State Invariant
	   	 if (!stateInvariantCheck()) 
	          throw new InvariantException ("CSDepartment", "AddTermWorkToCourse", "After");
	   	 	
	   	 return;
	}
	
	/*
	 * Method for ChangeMarkForStudentInCourse
	 * Input: Course, Student, Mark, Termwork
	 * Change the mark for the student for the termwork in the given course
	 */
	public void ChangeMarkForStudentInCourse(Course course, Student forWho, Mark newMark, TermWork whichTermWork) throws Exception{
		//State Invariant
   	 	if (!stateInvariantCheck()) 
            throw new InvariantException ("CSDepartment", "ChangeMarkForStudentInCourse", "Before");
   	 	
   	 	//Precondition: Course must be a member of Courses
   	 	if(course == null || !courses.contains(course))
   	 		throw new PreconditionException
   	 			("CSDepartment", "ChangeMarkForStudentInCourse", "course isnt a member courses");
   	 	//Precondition: forWho must be a member of students
   	 	if(forWho == null || !students.contains(forWho))
   	 		throw new PreconditionException
   	 			("CSDepartment", "ChangeMarkForStudentInCourse", "forWho isnt a member students");
   	 	/*//(MY)Precondition: whichTermWork must be a term work in the course
   	 	if(!course.getTermWorks().contains(whichTermWork))
	   	 	throw new PreconditionException
				("CSDepartment", "ChangeMarkForStudentInCourse", "which termwork is not ");*/
		
   	 	//Postcondition: remove the course from the courses, perform drop, re add course
	   	 course.ChangeTermWork(forWho, newMark, whichTermWork); 
	   	 courses.remove(course);
	   	 courses.add(course);
	   	 
	   //State Invariant
	   	 if (!stateInvariantCheck()) 
	          throw new InvariantException ("CSDepartment", "ChangeMarkForStudentInCourse", "After");
	   	 	
	   	 return;
	}
	
	/*
	 * Method for ReportGradeForStudentInCourse
	 * Input: Course, Student
	 * Ouptut: The Grade (PASS/FAIL) for the given student in the course
	 */
	public Course.Grade ReportGradeForStudentInCourse(Course course, Student forWho) throws Exception{
		//State Invariant
	   	 if (!stateInvariantCheck()) 
	          throw new InvariantException ("CSDepartment", "ReportGradeForStudentInCourse", "Before");
	   	 	
	   //Precondition: Course must be a member of Courses
   	 	if(course == null || !courses.contains(course))
   	 		throw new PreconditionException
   	 			("CSDepartment", "ReportGradeForStudentInCourse", "course isnt a member courses");
   	 	//Precondition: forWho must be a member of students
   	 	if(forWho == null || !students.contains(forWho))
   	 		throw new PreconditionException
   	 			("CSDepartment", "ReportGradeForStudentInCourse", "forWho isnt a member students");
   	 	
   	 	//Return value;
   	 	Course.Grade grade = course.ReportGrade(forWho);
	   	 
   	 	//State Invariant
	   	 if (!stateInvariantCheck()) 
	          throw new InvariantException ("CSDepartment", "ReportGradeForStudentInCourse", "After");
	   	 
	   	 return grade;
	}
	
	/*
	 * Method for TopStudentInCourse operation
	 * input: Course
	 * Output: Student (the top student in the course)
	 */
	public Student TopStudentInCourse(Course course) throws Exception{
		 //State Invariant
	   	 if (!stateInvariantCheck()) 
	          throw new InvariantException ("CSDepartment", "TopStudentInCourse", "Before");
	   	 
	   	 //Precondition: Course must be a member of Courses
	   	 if(course == null || !courses.contains(course))
	   	 	throw new PreconditionException
	   	 		("CSDepartment", "ReportGradeForStudentInCourse", "course isnt a member courses");
	   	 
	   	 //Postcondition: topStudent is the top student in the course
	   	 Student topStudent = course.WhoGotMaximum();
	   	 
	   	 //State Invariant
	   	 if (!stateInvariantCheck()) 
	          throw new InvariantException ("CSDepartment", "TopStudentInCourse", "After");
	   	 
	   	 return topStudent;
	}
	
	
	/*
	 * Method for AddNewElective
	 * input: Course
	 */
	public void addNewElective(Course newCourse) throws Exception{
		//State Invariant
	   	 if (!stateInvariantCheck()) 
	          throw new InvariantException ("CSDepartment", "AddNewElective", "Before");
	   	 
	   	 //Precondition: there does not exist a course in courses where newCourse.courseNumber ==
	   	 //
	   	 boolean alreadyExists = false;
	   	 for(Course c : courses){
	   		 //the comparison already compares the courseNumber values
	   		 if(c.equals(newCourse)){
	   			 alreadyExists = true;
	   		 }
	   	 }
	   	 if(alreadyExists)
	   		throw new PreconditionException
	 			("CSDepartment", "AddNewElective", "courseNumber already exists in courses");
	   	 
	   	 //Precondition: newCourse.type = Elective
	   	 if(newCourse.Required())
	   		throw new PreconditionException
	   			("CSDepartment", "AddNewElective", "newCourse is not an elective");
	   	 
	   //Precondition: newCourse.students = emptyset
	   	 if(newCourse.getStudents().size() != 0)
	   		throw new PreconditionException
	   			("CSDepartment", "AddNewElective", "newCourse.students is not the emptyset");
	   	 
	   //Precondition: newCourse.marks = emptyset
	   	 if(newCourse.getMarks().size() != 0)
	   		throw new PreconditionException
	   			("CSDepartment", "AddNewElective", "newCourse.marks is not the emptyset");
	   	 
	   //Precondition: newCourse.termworks = emptysequence
	   	 if(newCourse.getTermWorks().size() != 0)
	   		throw new PreconditionException
	   			("CSDepartment", "AddNewElective", "newCourse.termworks is not the empty sequence");
   	 
	 	//Postcondition: courses' = courses + newCourse
	   	 courses.add(newCourse);
	   	 
	   //State Invariant
	   	 if (!stateInvariantCheck()) 
	          throw new InvariantException ("CSDepartment", "AddNewElective", "After");
	   	 
	   	 return;
	}
	
	/*
	 * Method for DeleteElective
	 * input: Course
	 */
	public void deleteElective(Course whichCourse) throws Exception{
		//State Invariant
	   	 if (!stateInvariantCheck()) 
	          throw new InvariantException ("CSDepartment", "deleteElective", "Before");
	   	 
	   	 //Precondition: there exists a course in courses where the coursenum == whichCourse.coursenum
	   	 //and the course is an elective
	   	 if(whichCourse == null || !courses.contains(whichCourse) || whichCourse.Required() )
	   		throw new PreconditionException
   			("CSDepartment", "deleteElective", "whichCourse does not exist and/or is not an elective");
	 
	   	 //Precondition: the number of elective courses is > 1
	   	 int electiveCount = 0;
	   	 for(Course c: courses){
	   		 if(!c.Required()){
	   			 electiveCount++;
	   		 }
	   	 }
	   	 if(electiveCount <= 1)
	   		throw new PreconditionException
   			("CSDepartment", "deleteElective", "Less than or equal to one elective in courses");
	 
	   	 //Postcondition: set courses' = courses - whichCourse
	   	 courses.remove(whichCourse);
	   	 
	   //State Invariant
	   	 if (!stateInvariantCheck()) 
	          throw new InvariantException ("CSDepartment", "deleteElective", "After");
	
	   	 return;
	}
	
	/*
	 * Method for AddNewStudent
	 * input: student
	 */
	public void AddNewStudent(Student newStudent) throws Exception{
		//State Invariant
	   	 if (!stateInvariantCheck()) 
	          throw new InvariantException ("CSDepartment", "AddNewStudent", "Before");
	   	 
	   	 //Precondition: newStudent not a member of students
	   	 if(newStudent == null || students.contains(newStudent))
	   		throw new PreconditionException
   				("CSDepartment", "AddNewStudent", "newStudent already exists in CSDepartment");
	 
	   	 //PostConditoin: students' = students + newStudent
	   	 students.add(newStudent);
	   	 
	     //State Invariant
	   	 if (!stateInvariantCheck()) 
	          throw new InvariantException ("CSDepartment", "AddNewStudent", "After");
	   	 
	   	 return;
	}
	
	/*
	 * Method for Delete Student
	 * input: student
	 */
	public void DeleteStudent(Student whichStudent) throws Exception{
		//State Invariant
	   	 if (!stateInvariantCheck()) 
	          throw new InvariantException ("CSDepartment", "DeleteStudent", "Before");
	   	 
	   	 //Precondition: whichStudent is a member of students
	   	if(whichStudent == null || !students.contains(whichStudent))
	   		throw new PreconditionException
   				("CSDepartment", "DeleteStudent", "whichStudent does not exist in students");
	 
	   	//PostCondition: students' = students - whichStudent
	   	students.remove(whichStudent);
	   	
	   	//PostCondition: courses' = (all courses without whichStudent) U 
	   	//  (all courses with whichStudent such that all these courses  have the whichStudent
	   	//    removed from their students and from their marks)
	   	TreeSet<Course> updatedCourses = new TreeSet<Course>();
	   	for(Course c : courses){
	   		//if the course contains the student..
	   		if(c.getStudents().contains(whichStudent)){
	   			//drop student from course because it already contains all of the desired operations
	   			c.DropStudent(whichStudent);
	   		}
	   		updatedCourses.add(c);
	   	}
	   	courses = updatedCourses;
	   	
	   	//State Invariant
	   	 if (!stateInvariantCheck()) 
	          throw new InvariantException ("CSDepartment", "DeleteStudent", "After");
	   	 
	   	 return;
	}
	
	/*
	 * Method for ReportStudentCompletingRequirements
	 * input; student
	 * output: boolean (if the student completed the graduation requirements or not)
	 */
	public boolean ReportStudentCompletingRequirements(Student whichStudent) throws Exception{
		//State Invariant
	   	 if (!stateInvariantCheck()) 
	          throw new InvariantException ("CSDepartment", "ReportStudentCompletingRequirements", "Before");
	   	 
	   //Precondition: whichStudent is a member of students
	   	if(whichStudent == null || !students.contains(whichStudent))
	   		throw new PreconditionException
   				("CSDepartment", "ReportStudentCompletingRequirements", "whichStudent does not exist in students");
		 
	   	 
	   	 //Postcondition: there exist cores and elecs(constrain by cores and elecs subset of courses AND
	   	 // #cores = 3 and #elecs >= 1) SUCH THAT all of the courses (cores U elecs) have been passed
	   	 int numCores = 0;
	   	 boolean allPassed = true;
	   	 //List of all the courses taken
	   	 TreeSet<Course> coursesTaken = new TreeSet<Course>();
	   	 for(Course c : courses){
	   		 //if the student is enrolled in the course c... add c to their coursesTaken
	   		 if(c.getStudents().contains(whichStudent)){
	   			 if(c.Required())
	   				 numCores ++;
	   			 //if they didnt pass the course
	   			 if(c.ReportGrade(whichStudent) == Course.Grade.FAIL){
	   				 allPassed = false;
	   			 }
	   			 coursesTaken.add(c);
	   		 }
	   	 }
	   	 //PostCondition: there must be 3 cores and at least one elective
	   	 if(numCores != 3)
	   		 return false;
	   		/*throw new PostconditionException
				("CSDepartment", "ReportStudentCompletingRequirements", "Hasn't taken 3 core courses");*/
	   	 //PostCondition: Must have at least one elective
	   	 if( (coursesTaken.size() - numCores) < 1 ){
	   		return false;
	   		 /*throw new PostconditionException
	   			("CSDepartment", "ReportStudentCompletingRequirements", "Hasn't taken at least 1 elective");*/
	   	 }
	   	 
	   	 //PostCondition: coursesTaken must be passed
	   	 if(!allPassed){
	   		 return false;
	   		/*throw new PostconditionException
   				("CSDepartment", "ReportStudentCompletingRequirements", "Hasn't passed all courses taken");*/
	   	 }
	   	 
	   		 
		return true;
	}
	
	/*
	 * Used to get the specific course by name from the Main
	 */
	public Course getCourse(String name){
		for(Course c : courses){
			if(c.getCourseNumber().equals(name) ){
				return c;
			}
		}
		return null;
	}
	/*
	 * Used to get the specific Student by name from the Main
	 */
	public Student getStudent(String name){
		for(Student c : students){
			if(c.getName().equals(name) ){
				return c;
			}
		}
		return null;
	}
	/*
	 * Used to get the courses for use in printing in Main
	 */
	public TreeSet<Course> getCourses(){
		return courses;
	}
	
	
	/*
	 * State invariant check
	 */
	private boolean stateInvariantCheck(){
		boolean retValue = true;
		//the distributed union of all the courses students  is a subset of students
		TreeSet<Student> studsInCourses = new TreeSet<Student>();
		for(Course co : courses){
			//should ignore duplicate students
			studsInCourses.addAll(co.getStudents());
		}
		//if studsInCourses is not a subset of students
		if(!students.containsAll(studsInCourses)){
			retValue = false;
		}
		
		//HASHSETS automatically don't allow duplicate entries.
		//Since Courses are comparable by name, it will handle the implication that
		//if c1=c2, then c1.courseNumber = c2.courseNumber.
		
		//if the number of courses is less than 4
		if(courses.size() < 4){
			retValue = false;
		}
		
		//make sure that the number of courses that are core are = 3, the rest are not core courses
		int coreCount = 0;
		for(Course co : courses){
			if(co.Required()){
				coreCount++;
			}
		}
		//Coure count must be 3
		if(coreCount != 3){
			retValue = false;
		}
		
		return retValue;
	}
}
