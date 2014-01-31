
/**
 * CS 200 Colorado State University, Fall 2011
 */

public class MemberNode{
    public Member item;
    public MemberNode leftchild;
    public MemberNode rightchild;
   
    public MemberNode(){
		item = null;
		rightchild = null;
		leftchild = null;
    }  

    public MemberNode (Member _item, MemberNode _leftchild, MemberNode _rightchild) { 
		item = _item;
		leftchild = _leftchild;
		rightchild = _rightchild;
    }	   

    public void setLeftChild(MemberNode _left){
		leftchild = _left;
    }

    public void setRightChild(MemberNode _right){
		rightchild = _right;
    }

    public void setItem(Member _item){
		item = _item;
    }
    public MemberNode getLeftChild (){
		return leftchild;
    }

    public MemberNode getRightChild(){
		return rightchild;
    }

    public Member getItem(){
    	return item;
    }



}
