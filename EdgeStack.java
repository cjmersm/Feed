import java.util.LinkedList;


/**
 * CS 200 Colorado State University, Fall 2011
 */

public class EdgeStack {
	
	private LinkedList<Edge> alist;
	private int size;
	
	
	public EdgeStack(){
		alist = new LinkedList<Edge>();
		size = 0;
	}
	
	/**
	 * If the Linked List is empty then the stack is empty.
	 */
	public boolean isEmpty(){
		if(alist.isEmpty())
			return true;
		else
			return false;
	}
	
	/**
	 * Just adds Edge e to the Linked list.
	 * @param e
	 */
	public void push(Edge e){
		alist.add(e);
		size++;
	}

	/**
	 * Takes the last instance of the LinkedList so that
	 * the stack keeps the First In Last Out system
	 * @return e - the edge that's popped
	 */
	public Edge pop(){
		Edge e = alist.get(alist.size()-1);
		alist.remove(alist.size()-1);
		return e;
	}
	
	/**
	 * Looks at the top of the stack or the most recently pushed 
	 * thing on the stack
	 */
	public void peek(){
		System.out.println(alist.get(alist.size()-1));
	}
	
	/**
	 * I'm not sure what the use of this is and if it is
	 * really supposed to be void or not but it just goes through
	 * the list until it is all removed.
	 * @return nAlist
	 */
	public LinkedList<Edge> popAll(){
		LinkedList<Edge> nAlist = alist;
		for(int i = 0;i<alist.size();i++){
			alist.remove(0);
		}
		return nAlist;
	}
	
	/**
	 * Just in case i need to get the size
	 * @return size
	 */
	public int getSize(){
		return size;
	}
	

}
