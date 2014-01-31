import java.util.*;


                                                                                                                                                                        /**
 * CS 200 Colorado State University, Fall 2011
 * This is the base class for PA3. 
 * DO NOT MODYFY main(). Grading will be based on main() of this class.
 */

public class PA3{
	private String input_file;
	private MemberTree mTree;

    public PA3(String _input_file){
    	input_file = _input_file;	

		InformationParser ip = InformationParser.getInstance();
		ip.parseFile(input_file); 		
		
		mTree = ip.readMembers();
   }

    public void build_tree(int option){
	// build a tree from the example data file, and
	// if the option is 1, print the user ids following the inorder traversal
	// algorithm. if the option is 2, print the user ids folloiwng the preorder
	// traverdal algorithm. if hte options is 3, print the user ids folloiwng 
	// the postorder traversal algorithm

		if(option==1){
			
			mTree.printMemberTreeInOrder(mTree.root);
			System.out.println();
			
		}
		else if(option == 2){
			
			mTree.printMemberTreePreOrder(mTree.root);
			System.out.println();
			
		}
		else if(option == 3){
			
			mTree.printMemberTreePostOrder(mTree.root);
			System.out.println();
			
		}
		else{
			System.out.println("Hmmmm Thats mean of you...");
		}
    }

    public void add_member(String member_info){
		// build a tree from the example data file, and
		// add a member to the tree (binery search tree)
		// print out the user's ids following the inorder traversal algorithm
    	// System.out.println(member_info);
		Scanner memScan = new Scanner(member_info);
		memScan.useDelimiter(" ");
		
		Member newMem;
		
		String id = memScan.next();
		String first = memScan.next();
		String last = memScan.next();
		
		newMem = new Member(id,first,last);
		
		MemberNode left = null;
		MemberNode right = null;
		
		MemberNode newMember = new MemberNode(newMem,left,right);
		
		mTree.insertMember(newMember);
		mTree.printMemberTreeInOrder(mTree.root);
		System.out.println();
    }

    public void retrieve_memberInfo(String user_id){
	// build a tree from the example data file, and
	// retrieve information of a member from the tree (who has the specified user_id)
	// print first name and last name. (first name and last name are separated by a space)

    	MemberNode temp = mTree.root;
    	boolean found = false;
    	
    	while(temp!=null){
    		String id = temp.getItem().getUserID();
    		
    		if(user_id.equals(id)){
    			System.out.println(temp.getItem().getFirst()+" "+temp.getItem().getLast());
    			temp = null;
    			found = true;
    		}
    		else if(user_id.compareTo(id)<0){
    			temp = temp.getLeftChild();
    		}
    		else if(user_id.compareTo(id)>0){
    			temp = temp.getRightChild();
    		}
    	}
    	
    	if(found == false){
    		System.out.println("No such Member");
    	}
    }

    public void remove_member(String user_id){
	// build a tree from the example data file, and
	// delete a member from the tree (who has the specified user_id)
	// print your tree (user ids following the inorder traversal algorithm)

    	mTree.remove(user_id);
    	mTree.printMemberTreeInOrder(mTree.root);
    	System.out.println();
    }




    //NOTE: DO NOT MODIFY main().
    public static void main(String args[]){
	if (args.length < 3){
	    System.out.println("Please enter your input file and command.");
	    System.out.println("e.g. java PS3 [input_file][command1][command2]");
	}else{
	    
	    String input_file = args[0];
	    String cmd = args[1];
	    

	    PA3 pa3 = new PA3(input_file);
			
	   
	    if (cmd.equals("build_tree")){
			int cmd2 = Integer.parseInt(args[2]);
			pa3.build_tree(cmd2);
			}else if (cmd.equals("add_member")){
				pa3.add_member(args[2]);
			}else if (cmd.equals("remove_member")){
				pa3.remove_member(args[2]);
			}else if (cmd.equals("retrieve_memberInfo")){
				pa3.retrieve_memberInfo(args[2]);
			}
	
		}
    }
}                            