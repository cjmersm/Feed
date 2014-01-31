/**
 * CS 200 Colorado State University, Fall 2011
 */

public class Member {

	private String userID;
	private String first;
	private String last;
	private EdgeStack edgeStack;
	
	//constructor
	public Member(){
		userID = "000000";
		first = "noName";
		last = "noName";
	}
	
	public Member(String userID, String first, String last){
		
		this.userID = userID;
		this.first = first;
		this.last = last;
	}
	
	public String getUserID(){
		return userID;
	}

	public String getFirst(){
		return first;
	}
	
	public String getLast(){
		return last;
	}
	
	public EdgeStack getEdgeStack(){
		return edgeStack;
	}	
	
	public void setUserID(String _userID){
		userID = _userID;
	}
	
	public void setFirst(String _first){
		first = _first;
	}
	
	public void setLast(String _last){
		last = _last;
	}
	
	public void setEdgeStack(EdgeStack _edgeStack){
		edgeStack = _edgeStack;
	}
	
}