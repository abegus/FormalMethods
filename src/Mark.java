/**
 * The Mark's are used to keep track of the students grades
 * they have to be between 0.0 and 100.0
 * 
 * @author abegustafson
 *
 */
public class Mark {
	/*
	 * Each Mark has a mark between 0.0 and 100.0
	 */
	private double mark;
	private double low = 0.0;
	private double high = 100.0;
	
	public Mark(double theMark) throws InvariantException{
		mark = theMark;
		if(!stateInvariantCheck()){
			 throw new InvariantException ("Mark", "Constructor", "After");
		}
	}
	
	public void updateMark(double theMark) throws InvariantException{
		if(!stateInvariantCheck()){
			 throw new InvariantException ("Mark", "updateMark", "Before");
		}
		mark = theMark;
		if(!stateInvariantCheck()){
			 throw new InvariantException ("Mark", "updateMark", "After");
		}
		
	}
	
	private boolean stateInvariantCheck(){
		if( mark >= low && mark <= high){
			return true;
		}
		else{
			return false;
			
		}
	}
	
	public double getMark(){
		return mark;
	}
}
