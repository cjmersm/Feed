
/**
 * CS 200 Colorado State University, Fall 2011
 */

public class NFObject {
	
	private String objectID;
	private int type;
	private String creator;
	private String body;
	private long timestamp;
	private int edgerank = 0;
	private int weight;
	private int affinScore;
	
	public NFObject (){
		
	}
	
	public NFObject (String _objectID,int _type, String _creator, String _body,long _timestamp){
		objectID = _objectID;
		type = _type;
		creator = _creator;
		body = _body;
		timestamp = _timestamp;		
	}
	
	/**
	 * Sets the wait of the object with type type
	 * 
	 * @param type
	 */
	public void setWeight(int type){
		if(type == 0) 		//visit
			weight = 1;
		else if(type == 1) 	// wall posting
			weight = 4;
		else if(type == 2)	// comment
			weight = 3;
		else if(type == 3 ) // homepage
			weight = 1;
		else if(type == 4) 	// status change
			weight = 4;
		else if(type == 5) 	// like
			weight = 2;
	}
	
	public int getWeight(){
		return weight;
	}
	
	
	public String getObjectID(){
		return objectID;
	}
	
	public int getType(){
		return type;
	}
	
	public String getCreator(){
		return creator;
	}
	
	public String getBody(){
		return body;
	}
	
	public long getTimestamp(){
		return timestamp;
	}
	
	
	
	public void setObjectID(String _objectID){
		objectID = _objectID;
	}
	
	public void setType(int _type){
		type = _type;
	}
	
	public void setCreator(String _creator){
		creator = _creator;
	}
	
	public void setBody(String _body){
		body = _body;
	}
	
	public void setTimestamp(long _timestamp){
		timestamp = _timestamp;
	}
	
	/**
	 * Returns the time decay weight that is used in the
	 * EdgeRank Algorithm
	 * 
	 * @return 0-6
	 */
	public int timeDecay(){
		if(timestamp <= 360 && timestamp > 0)
			return 6;
		else if(timestamp <= 720)
			return 5;
		else if(timestamp <= 1080)
			return 4;
		else if(timestamp <= 1440)
			return 3;
		else if(timestamp <= 2880)
			return 2;
		else if(timestamp <= 5760)
			return 1;
		else 
			return 0;
	}
	
	/**
	 * This method sets the EdgeRank by using the first part of 
	 * the EdgeRank Algorithm
	 * @param type
	 */
	public void setEdgeRank(int type){
		setWeight(type);
		edgerank += affinScore*weight*timeDecay();
	}
	
	public void setAffinScore(int score){
		affinScore = score;
	}
	
	public int getEdgeRank(){
		return edgerank;
	}
	
	

}