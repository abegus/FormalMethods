import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;


public class CSmain {

	public CSDepartment department;
	
	public CSmain(){
		try {
			department = new CSDepartment();
		
		
			System.out.println("=====Initialize=====");
			//Students
			OpenStudents();
			//REQUIRED / already in
			OpenFiles("CS741",true,true);
			OpenFiles("CS742",true,true);
			OpenFiles("CS743",true,true);
			OpenFiles("CS521",false,true); //elective
			//ELECTIVES
			OpenFiles("CS502",false,false);
			OpenFiles("CS519",false,false);
			OpenFiles("CS544",false,false);
			OpenFiles("CS564",false,false); //EMPTY*/
			
			PrintCommands();
			StartInput();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void StartInput(){
		
		
		ArrayList<String> names = new ArrayList<String>();
		Scanner scanner = new Scanner(System.in);
		String userInput = "";
		try{
			while (!userInput.equals( "-quit") ){
				System.out.print("> ");
			    userInput = scanner.nextLine();
			    String[] words = userInput.split(" ");
			    if (words[0].equals("-help")){
			    	PrintCommands();
			    }
			    else if (words[0].equals("-Enroll")){
			    	Student who = department.getStudent(words[1] + " " + words[2]);
			    	//Student who = new Student(words[1] + " " + words[2]);
			    	//Course which = department.getCourse(words[3]);
			    	Course which = department.getCourse(words[3]);
					department.EnrollStudentInCourse(which, who);
			    }
			    else if (words[0].equals("-Drop")){
			    	Student who = department.getStudent(words[1] + " " + words[2]);
			    	Course which = department.getCourse(words[3]);
					department.DropStudentFromCourse(which, who);
			    }
			    else if (words[0].equals("-AddTermWork")){
			    	Course which = department.getCourse(words[1]);
			    	TermWork tw = new TermWork(words[2]);
					department.AddTermWorkToCourse(which, tw);
			    }
			    else if (words[0].equals("-ChangeMark")){
			    	Student who = department.getStudent(words[1] + " " + words[2]);
			    	Course which = department.getCourse(words[3]);
			    	TermWork tw = new TermWork(words[4]);
			    	/*TermWork tw = which.getTermWork(words[4]);*/
			    	//if(tw == null)
			    	if(which != null)
			    		tw = which.getTermWork(words[4]);
			    		//tw = new TermWork(words[4]);
			    	Mark m = new Mark(Double.parseDouble(words[5]));
					department.ChangeMarkForStudentInCourse(which, who, m, tw);
			    }
			    else if (words[0].equals("-ReportGrade")){
			    	Student who = department.getStudent(words[1] + " " + words[2]);
			    	Course which = department.getCourse(words[3]);
					Course.Grade g = department.ReportGradeForStudentInCourse(which, who);
					if(g.equals(Course.Grade.PASS))
						System.out.println("PASSED");
					else
						System.out.println("FAILED");
			    }
			    else if (words[0].equals("-TopStudent")){
			    	Course which = department.getCourse(words[1]);
					Student s = department.TopStudentInCourse(which);
					System.out.println(s.getName());
			    }
			    else if (words[0].equals("-AddElective")){
			    	Course which = new Course(words[1],false);
					department.addNewElective(which);
			    }
			    else if (words[0].equals("-DeleteElective")){
			    	Course which = department.getCourse(words[1]);
					department.deleteElective(which);
			    }
			    else if (words[0].equals("-AddStudent")){
			    	Student stud = new Student(words[1] + " " + words[2]);
					department.AddNewStudent(stud);
			    }
			    else if (words[0].equals("-DeleteStudent")){
			    	Student who = department.getStudent(words[1] + " " + words[2]);
					department.DeleteStudent(who);
			    }
			    else if (words[0].equals("-Requirements")){
			    	Student who = department.getStudent(words[1] + " " + words[2]);
					boolean completed = department.ReportStudentCompletingRequirements(who);
					if(completed){
						System.out.println("Passed");
					}
					else{
						System.out.println("failed");
					}
			    }
			    else if (words[0].equals("-Print")){
			    	Course course = department.getCourse(words[1]);
			    	ArrayList<TermWork> works = course.getTermWorks();
			    	TreeSet<Student> studs = course.getStudents();
			    	TreeMap<Student, ArrayList<Mark>> marks = course.getMarks();
			    	System.out.println("======================"+course.getCourseNumber()+"======================");
			    	String s = String.format("%1$20s", "Students");
			    	System.out.print(s);
			    	for(TermWork w : works){
			    		s=String.format("%1$8s", w.getName());
			    		System.out.print(s);
			    	}
			    	System.out.println();
			    	for(Student stud : studs){
			    		s=String.format("%1$20s", stud.getName());
			    		System.out.print(s);
			    		ArrayList<Mark> m = marks.get(stud);
			    		for(Mark ms : m){
			    			s=String.format("%1$8s", ms.getMark());
			    			System.out.print(s);
			    		}
			    		System.out.println();
			    	}
			    }
			    else if (words[0].equals("-Courses")){
			    	Student stud = department.getStudent(words[1] + " "+words[2]);
			    	TreeSet<Course> courses = department.getCourses();
			    	ArrayList<String> involved = new ArrayList<String>();
			    	for(Course c : courses){
			    		if(c.getStudents().contains(stud))
			    			involved.add(c.getCourseNumber());
			    	}
			    	System.out.println("=====Courses=====");
			    	for(String s: involved){
			    		System.out.println(s);
			    	}
			    	
			    }
			    else{
			    	System.out.println("Invalid command");
			    }
			    
			    
			    
			}    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//System.out.println(e.getMessage());
			StartInput();
		}
		scanner.close();
	}
	private void PrintCommands(){
		System.out.println("===Commands===");
		System.out.println("-help");
		System.out.println("-quit");
		System.out.println("---OPERATION COMMANDS---");
		System.out.println("-Enroll Peter Parker CS741");
		System.out.println("-Drop Peter Parker CS741");
		System.out.println("-AddTermWork CS741 Term1");
		System.out.println("-ChangeMark Peter Parker CS741 Term1 84.0");
		System.out.println("-ReportGrade Peter Parker CS741");
		System.out.println("-TopStudent CS741");
		System.out.println("-AddElective CS500");
		System.out.println("-DeleteElective CS500");
		System.out.println("-AddStudent Peter Parker");
		System.out.println("-DeleteStudent Peter Parker");
		System.out.println("-Requirements Peter Parker");
		System.out.println("---CONTENT COMMANDS---");
		System.out.println("-Print CS741");
		System.out.println("-Courses Peter Parker");
		System.out.println("=====End======");
		return;
	}
	
	private void OpenStudents(){
		try{
            Scanner scan = new Scanner(new File("/Users/abegustafson/Desktop/742Data/Students.txt"));
            //this will be the "Students" line
            String lineJustFetched = scan.nextLine();//buf.readLine();

            while(scan.hasNextLine()){
            	lineJustFetched = scan.nextLine();//buf.readLine();
            	Scanner se = new Scanner(lineJustFetched);
            	String name = se.next() + " " + se.next();
                //create a new student object and add it
            	Student s = new Student(name);
            	//System.out.println(" -Just added "+name);
                
                department.AddNewStudent(s);
                se.close();
            }
            

            scan.close();

        }catch(Exception e){
            //e.printStackTrace();
        }
	}
	
	private void OpenFiles(String className, boolean required, boolean exists){
		try{
			Scanner scan = new Scanner(new File("/Users/abegustafson/Desktop/742Data/" + className + ".txt"));
            //this will be the "Students" line
            String lineJustFetched = scan.nextLine();//buf.readLine();
            
            Course course;
            /* Check to see if it already exists */
            if(exists){
            	course = department.getCourse(className);
            }
            else{
            	course = new Course(className, required); //assumes all the electives are being added 
            	department.addNewElective(course);
            }
            
            //TermWork array to store the termworks
            TermWork[] works = new TermWork[10];
            while(scan.hasNextLine()){
            	lineJustFetched = scan.nextLine();//buf.readLine();
            	//set array of values
            	String[] wordsArray = lineJustFetched.split("\t");
            	
            	//if the first word is "Students", add the term works
            	if(wordsArray[0].equals("Students")){
            		works = new TermWork[wordsArray.length];
            		for(int i = 1; i < wordsArray.length; i++){
            			TermWork newWork = new TermWork(wordsArray[i]);
            			department.AddTermWorkToCourse(course, newWork);
            			//System.out.println(" -Adding term work " + wordsArray[i] + " to " +className);
            			works[i] = newWork;
            		}
            	}
            	else{
            		Scanner se = new Scanner(lineJustFetched);
                	String name = se.next() + " " + se.next();
                	Student student = department.getStudent(name);
                	//System.out.println(name);
                	
                	//Enroll student in the course
                	department.EnrollStudentInCourse(course, student);
                	
                	//loop through and add courseworks for the student 
                	for(int i = 1; i < wordsArray.length; i++){
                		Mark m = new Mark(Double.parseDouble(wordsArray[i]));
                		department.ChangeMarkForStudentInCourse(course, student, m, works[i]);
                		//System.out.println(" -Adding coursework for "+name + " mark: "+m.getMark()+" work:"+works[i].getName());
                	}
                	
                    
                    se.close();
            	}
            	
            	
            }
			
            scan.close();

        }catch(Exception e){
            e.printStackTrace();
        }
	}
	
	
	public static void main(String[] args) {
		CSmain m = new CSmain();
	}

}
