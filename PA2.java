import java.util.*;

/**
 * CS 200 Colorado State University, Fall 2011
 * This is the base class for PA2. 
 * DO NOT MODYFY main(). Grading will be based on main() of this class.
 */

public class PA2{
	String input_file;
	EdgeStack eStack = new EdgeStack();
	Member member;
	LinkedList<Edge> elist = new LinkedList<Edge>();

	/**
	 * This constructor calls the instance of the InformationParser and creates
	 * the member, the stack, and an LinkedList call elist with the edges in it
	 * @param _input_file
	 */
	public PA2(String _input_file){
		input_file = _input_file;	

		InformationParser ip = InformationParser.getInstance();

		Member onlyMember = ip.parseFile(input_file);
		member = onlyMember;
		eStack = InformationParser.getEdgeStack();
		
		while(!eStack.isEmpty()){
			elist.add(eStack.pop());
		}
	}

	/**
	 * The method gets the TargetingObjects id's based off of the the Posting 
	 * objects id so that we will be able to build the edgeRankScore
	 * @param id
	 */
	public void get_TargetingObjects(int id){
		LinkedList<Edge> temp = elist;    	
		String rev = "";
		for(int i = 0;i<temp.size();i++){
			Edge e = temp.get(i);
			NFObject targetObj = e.getTargetObject();

			if(Integer.parseInt(targetObj.getObjectID())==id){
				NFObject postingObj= e.getPostingObject();

				rev = (postingObj.getObjectID()+" ") + rev;

			}
		}
		rev = rev.trim(); // no extra white space
		System.out.println(rev);

	}

	/**
	 * This is the same method as the one before; however, it has a return type
	 * instead of being void so that I can use it for the later method of
	 * get_EdgeRankScore.
	 *  
	 * @param id
	 * @return
	 */
	public ArrayList<Integer> getTargetIds(int id){
		LinkedList<Edge> temp = elist;    	
		String rev = "";
		ArrayList<Integer> arr = new ArrayList<Integer>();

		for(int i = 0;i<temp.size();i++){
			Edge e = temp.get(i);
			NFObject targetObj = e.getTargetObject();

			if(Integer.parseInt(targetObj.getObjectID())==id){
				NFObject postingObj= e.getPostingObject();

				rev = (postingObj.getObjectID());
				arr.add(Integer.parseInt(rev));
			}
		}

		return arr;
	}

	/**
	 * This void method gets the EdgeRankScore and prints it out based on
	 * the NFObject id that is input.  It uses the edgeRank calculation 
	 * Algorithm multiplying the Affinity Score * Weight * Time Decay which is 
	 * compiled and built over the use of several NFObjects.
	 * 
	 * @param id
	 */
	public void get_EdgeRankScore(int id){
		// Add your code here
		LinkedList<Edge> temp = elist;
		int edgeScore = 0;
		
		ArrayList <Integer> tempArr = new ArrayList<Integer>();

		tempArr = getTargetIds(id);

		for(int j = 0;j<temp.size();j++){
			Edge e = temp.get(j);
			NFObject rev = e.getPostingObject();
			
			if(Integer.parseInt(rev.getObjectID())==id){
				edgeScore += rev.getEdgeRank();
			}
			
			for(int i = 0;i<tempArr.size();i++){
				if(tempArr.get(i)==Integer.parseInt(rev.getObjectID())){
					edgeScore += rev.getEdgeRank();
				}
			}
		}

		System.out.println(edgeScore);
	}
	
	/**
	 * This method is used later within the this same class to be used
	 * for getting NFObject_ranked_at.  I had to make another getEdgeRankScore
	 *  that wasn't void so that I could use it instead it being a void method.
	 *   
	 * @param id
	 * @return edgeRankScore
	 */
	public int getEdgeRankScore(int id){
		LinkedList<Edge> temp = elist;
		int edgeScore = 0;
		
		ArrayList <Integer> tempArr = new ArrayList<Integer>();

		tempArr = getTargetIds(id);

		for(int j = 0;j<temp.size();j++){
			Edge e = temp.get(j);
			NFObject rev = e.getPostingObject();
			
			if(Integer.parseInt(rev.getObjectID())==id){
				edgeScore += rev.getEdgeRank();
			}
			
			for(int i = 0;i<tempArr.size();i++){
				if(tempArr.get(i)==Integer.parseInt(rev.getObjectID())){
					edgeScore += rev.getEdgeRank();
				}
			}
		}
		
		return edgeScore;
	}

	/**
	 * Based off of the rank given, this method prints out the 
	 * object with that rank of a EdgeScore.
	 * 
	 * @param rank
	 */
	public void get_NFObject_ranked_at(int rank){
		// This method prints the ID, owner's userID, and body of the NFobject that 
		// is ranked at the specified input parameter:rank. 
		// e.g. get_NFObject_with_biggest_ERscore(1) will return the ID of the NFObject
		// that has the biggest ERScore. get_NFObject_with_biggest_ERscore(2) will return
		// the ID of the NFObject that has second biggest ERScore.
		// Add your code here
		
		LinkedList<Edge> llist = new LinkedList<Edge>();

		llist = MergeSort(elist);
		
		System.out.print(llist.get(rank-1).getPostingObject().getObjectID() + " ");
		System.out.print(llist.get(rank-1).getPostingObject().getCreator()+ " ");
		System.out.print(llist.get(rank-1).getPostingObject().getBody());
		System.out.println();
	}
	
	/**
	 * Used to sort the LinkedList of edges based on their EdgeScoreRank.
	 * using two for loops to distribute each occurance into two different
	 * linkedlists that are to be merged and sorted later.
	 * 
	 * @param alist
	 * @return sorted arraylist.
	 */
	public LinkedList <Edge> MergeSort(LinkedList<Edge> alist){
		LinkedList<Edge> llist = new LinkedList<Edge>();
		
		if(alist.size()==1){
			llist = alist;
		}
		else{
			int mid = alist.size()/2;
			
			LinkedList<Edge> left = new LinkedList<Edge>();
			LinkedList<Edge> right = new LinkedList<Edge>();
			
			int i = 0;
			for(;i<mid;i++){
				left.add(alist.get(i));
			}
			for(;i<alist.size();i++){
				right.add(alist.get(i));
			}
			left = MergeSort(left); // cool recursion
			right = MergeSort(right); // cooler recursion
			llist = merge(left,right);
		}
		
		return llist;
	}
	
	/** merge
	 * 
	 * This function is called by mergeSort to bring the two linked lists back together
	 * so that they are sorted by the divide and conquer method.
	 * 
	 * @param left
	 * @param right
	 * @return merged linked list
	 */
	public LinkedList<Edge> merge(LinkedList<Edge> left, LinkedList<Edge> right){
		LinkedList<Edge> merged = new LinkedList<Edge>();
		
		while (!left.isEmpty() || !right.isEmpty()){
			if(left.isEmpty()){
				merged.add(right.remove());
			}
			else if(right.isEmpty()){
				merged.add(left.remove());
			}
			else{
				
				int a = getEdgeRankScore(Integer.parseInt(left.get(0).getPostingObject().getObjectID()));
				int b = getEdgeRankScore(Integer.parseInt(right.get(0).getPostingObject().getObjectID()));
				
				// compares all the edge ranks
				if(a>b){
					merged.add(left.remove(0));
				}
				else if(b>a){
					merged.add(right.remove(0));
				}
				else{
					merged.add(left.remove(0));
				}
			}
		}
		
		return merged;
	}


	//NOTE: DO NOT MODIFY main().
	public static void main(String args[]){

		if (args.length < 3){
			System.out.println("Please enter your input file and command.");
			System.out.println("e.g. java PA2 [input_file][command1][command2]");
		}else{

			String input_file = args[0];
			String cmd1 = args[1];
			int cmd2 = Integer.parseInt(args[2]);

			PA2 pa2 = new PA2(input_file);

			// test case 1,2: print the entire object IDs targeting NFObject 
			// cmd2 is the object id of the targeted NFObject
			// e.g. pa2.get_targetingObjects(2) will return all of the
			// object ids of NFObjects those have NFObject with id 2 as their
			// target object. For instance, if NFObject with the object ID 2 
			// is a status change posting, this method will return all of the 
			// IDs of comments, visit and like object added to this posting.
			if (cmd1.equals("get_TargetingObjects")){
				pa2.get_TargetingObjects(cmd2);
			}else if (cmd1.equals("get_EdgeRankScore")){
				// test case 3,4: print the EdgeRankScore of NFObject with 
				// specified id.
				pa2.get_EdgeRankScore(cmd2);
			}else if (cmd1.equals("get_NFObject_ranked_at")){
				// test case 5,6: Print ID, owner's userID, and body of NFObject 
				// that is ranked at cmd2 in the sorted list.
				// e.g. get_NFObject_ranked_at(1) is the NFObject with the biggest
				// EdgeRank score.

				pa2.get_NFObject_ranked_at(cmd2);
			}
		}	
	}
}  