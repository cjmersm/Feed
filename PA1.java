
/**
 * CS 200 Colorado State University, Fall 2011
 * This is the base class for PA1. 
 * DO NOT MODYFY main(). Grading will be based on main() of this class.
 */

public class PA1{
    String input_file;
    Member member;
    EdgeStack eStack;
    
    public PA1(String _input_file){
		input_file = _input_file;	
	
		InformationParser ip = InformationParser.getInstance();
		
		Member onlyMember = ip.parseFile(input_file);
		member = onlyMember;
		eStack = InformationParser.getEdgeStack();
    }

    public void print_last_name(){
    	System.out.println(member.getLast());
    }
	
    public void print_userID(){
 		System.out.println(member.getUserID());
    }
	
    public void has_any_edge(){
 		if(eStack.isEmpty()){
 			System.out.println("False");
 		}
 		else{
 			System.out.println("True");
 		}
    }
    
    public void print_most_recent_edgeID(){
    	Edge e = eStack.pop();
    	System.out.println(e.getEdgeID());
    	eStack.push(e);
    }
    
    public void print_third_most_recent_edgeID(){
 		Edge e1 = eStack.pop();
 		Edge e2 = eStack.pop();
 		Edge e3 = eStack.pop();
 		System.out.println(e3.getEdgeID());
 		eStack.push(e3);
 		eStack.push(e2);
 		eStack.push(e1);
    }


    public static void main(String args[]){
	
		if (args.length<2){
			System.out.println("Please enter your input file and command.");
			System.out.println("e.g. java PA1 [input_file] [command]");
		}else{
	    
			String input_file = args[0];
			String cmd = args[1];
	    
			PA1 pa1 = new PA1(input_file);
			
			//test case 1: print last name
			if (cmd.equals("print_last_name")){
				pa1.print_last_name();
			}else if (cmd.equals("print_userID")){
				//test case 2: print user ID
				pa1.print_userID();
			}else if (cmd.equals("has_any_edge")){
				//test case 3: print whether the edge stack is empty
				pa1.has_any_edge();
			}else if(cmd.equals("print_most_recent_edgeID")){
				//test case 4: retrieve the most recently added edge and print ID
				pa1.print_most_recent_edgeID();
			}else if (cmd.equals("print_third_most_recent_edgeID")){
				//test case 5: retrieve third most recently added edge and print ID.
				pa1.print_third_most_recent_edgeID();
			}
		}	
	}
}