package dsaProblems.tree;

public class BinarySearchTree {
	
	class Node{
		int val;
		Node left;
		Node right;
		Node(int val){
			this.val=val;
		}
	}

	public void buildTree(int[] arr) {
		if(arr.length==0) {
			return;
		}
		Node root=new Node(arr[0]);
		Node temp=root;
		for(int i=0;i<arr.length;i++) {
			if(arr[i]<=temp.val) {
			temp.left=new Node(arr[i]);
			temp=temp.left;
			++i;
			}else {
			
			if(i<arr.length) {
				temp.right=new Node(arr[i]);
				temp=temp.right;
			}
			}
		}
		
	}
}
