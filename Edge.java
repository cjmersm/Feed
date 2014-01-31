
/**
 * CS 200 Colorado State University, Fall 2011
 */

public class Edge {

	private String edgeID;
	private int type;
	
	private NFObject postingObject;
	private NFObject targetObject;
	
	
	//constructor
	public Edge(){
		
	}
	
	public Edge(String edgeID, int type, NFObject postingObject, NFObject targetObject){
		this.edgeID = edgeID;
		this.type = type;
		this.postingObject = postingObject;
		this.targetObject = targetObject;
		
	}
	
	public String getEdgeID(){
		return edgeID;
	}
	
	public int getEdgeType(){
		return type;
	}
	
	public NFObject getPostingObject(){
		return postingObject;
	}
	
	public NFObject getTargetObject(){
		return targetObject;
	}
		
	public void setType(int type){
		this.type = type;
	}
	
	public void setPostingObject(NFObject posting){
		postingObject = posting;
	}

	public void setTargetObject(NFObject target){
		targetObject = target;
	}
	
	public String toString(){
		return "ID = "+edgeID+ ", Type = "+type;
	}
	
}