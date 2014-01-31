

/**
 * CS 200 Colorado State University, Fall 2011
 */

import java.io.*;
import java.util.*;

public class InformationParser{
	private static InformationParser informationParser = null;
	public static EdgeStack eStack;

	protected InformationParser(){
		eStack = new EdgeStack();
	}

	public static InformationParser getInstance(){
		if (informationParser==null) {
			informationParser = new InformationParser();
		}
		return informationParser;
	}

	public static EdgeStack getEdgeStack(){
		return eStack;
	}

	public Member parseFile(String file_loc){
		Member member = new Member();
		String ID;
		String first;
		String last;

		Edge e;
		String eID = "";
		int eType = -1;

		NFObject postingObject = new NFObject();
		NFObject targetObject = new NFObject();

		File file = new File (file_loc);

		Scanner fileScan;

		try{
			fileScan = new Scanner (file);
			fileScan.useDelimiter("\n");

		}
		catch(FileNotFoundException error){
			return null;
		}

		/***********************************************************/

		while(fileScan.hasNext()){
			String line = fileScan.next();
			Scanner lineScan = new Scanner (line);
			//lineScan.useDelimiter(" ");
			while(lineScan.hasNext()){
				String word = lineScan.next();



				/*
				 * If a member, great a new member with the right args
				 */
				if(word.equals("member")){

					ID = lineScan.next();
					first = lineScan.next();
					last = lineScan.next();

					member = new Member(ID,first,last);
				}

				/*
				 * If an edge, create an edge and put it onto the stack
				 */
				if(word.equals("edge")){

					eID = lineScan.next();
					eType = lineScan.nextInt();

				}

				////////////////////////////////////////////////////////////////////////////////

				if(word.equals("postingobject")){
					String NFobjectID = lineScan.next();
					int type = lineScan.nextInt();

					if(type == 0){
						String memberID = lineScan.next();
						String body = "nobody";
						long timestamp = lineScan.nextLong();

						postingObject = new NFObject(NFobjectID,type,memberID,body,timestamp);
						postingObject.setEdgeRank(type);
					}
					else{
						String memberID = lineScan.next();
						String body = lineScan.next();
						long timestamp = lineScan.nextLong();

						postingObject = new NFObject(NFobjectID,type,memberID,body,timestamp);
						postingObject.setEdgeRank(type);
					}

				}

				////////////////////////////////////////////////////////////////////////////////

				if(word.equals("targetobject")){
					String NFobjectID = lineScan.next();
					int type = lineScan.nextInt();
					String memberID = lineScan.next();
					String body = lineScan.next();
					long timestamp = lineScan.nextLong();

					targetObject = new NFObject(NFobjectID,type,memberID,body,timestamp);
					targetObject.setEdgeRank(type);

					e = new Edge(eID,eType,postingObject,targetObject);
					eStack.push(e);
				}

				/***********************************************************/



			}

		}//while





		return member; 
	}

}