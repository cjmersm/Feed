
        /**
 * CS 200 Colorado State University, Fall 2011
 */

public class MemberTree{
	
    public MemberNode root;

    public MemberTree(){
		root = null; 
    }

    public MemberNode getRoot(){
		return root; 
    }

    public void setRoot(MemberNode _root){
		root = _root;
    }

	
    public void insertMember(MemberNode newMember){
    	
    	if(root == null){
			root = newMember;
		}
    	
		else{
			insertMember(newMember,root);
		}
    	
    }
    
    public void insertMember(MemberNode newMember, MemberNode tooCompare){
    	String id = newMember.getItem().getUserID();
    	String com= tooCompare.getItem().getUserID();
    	
    	if(id.compareTo(com)<0){
    		if(tooCompare.getLeftChild()==null){
    			tooCompare.setLeftChild(newMember);
    		}
    		else{
    			insertMember(newMember,tooCompare.getLeftChild());
    		}
    	}
    	
    	else if(id.compareTo(com)>0){
    		if(tooCompare.getRightChild()==null){
    			tooCompare.setRightChild(newMember);
    		}
    		else{
    			insertMember(newMember,tooCompare.getRightChild());
    		}
    	}
    }
    
    public void remove(String searchKey){
    	removeMember(searchKey,root);
    }

    public MemberNode removeMember(String searchKey, MemberNode m ){
		
    	String id = m.getItem().getUserID();
    	
    	if(m == null){
    		
    	}
    	
    	else if(searchKey.compareTo(id)<0){
    		m.leftchild=removeMember(searchKey,m.leftchild);
    	}
    	
    	else if(searchKey.compareTo(id)>0){
    		m.rightchild=removeMember(searchKey,m.rightchild);
    	}
    	
    	else{
    		if(m.rightchild == null){
    			m = m.leftchild;
    		}
    		else{
    			String successor = min(m.rightchild);
    			m.item.setUserID(successor);
    			
    			m.rightchild = removeMember(successor,m.rightchild);
    		}
    	}
    	return m;
    }
    
    public String min(MemberNode t){
    	if(t.leftchild==null){
    		return t.item.getUserID();
    	}
    	else
    		return min(t.leftchild);
    }


    public void printMemberTreeInOrder(MemberNode start){	
    	if( start.getLeftChild() != null)
            this.printMemberTreeInOrder(start.getLeftChild());
    	
        System.out.print(start.getItem().getUserID()+" ");

        if( start.getRightChild() != null)
            this.printMemberTreeInOrder(start.getRightChild());
    }

    public void printMemberTreePreOrder(MemberNode start){
    	
    	System.out.print(start.getItem().getUserID()+ " ");
    	
    	if(start.getLeftChild() != null) 
    		printMemberTreePreOrder(start.getLeftChild());
    	
    	if(start.getRightChild() != null) 
    		printMemberTreePreOrder(start.getRightChild());
    	
    }

    public void printMemberTreePostOrder(MemberNode start){
    	if(start.getLeftChild() != null) 
    		printMemberTreePostOrder(start.getLeftChild());
    	
    	if(start.getRightChild() != null) 
    		printMemberTreePostOrder(start.getRightChild());
    	
    	System.out.print(start.getItem().getUserID()+" ");
    }
     
   

}