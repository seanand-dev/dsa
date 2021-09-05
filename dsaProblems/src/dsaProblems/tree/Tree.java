package dsaProblems.tree;

import java.util.Stack;

public class Tree {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	public TreeNode str2tree(String s) {
		Stack<TreeNode> stack = new Stack<>();
		for (int i = 0, j = i; i < s.length(); i++, j = i) {
			char c = s.charAt(i);
			if (c == ')') {
				stack.pop();
			} else if (c >= '0' && c <= '9' || c == '-') {
				while (i + 1 < s.length() && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9') {
					i++;
				}
				TreeNode currentNode = new TreeNode(Integer.valueOf(s.substring(j, i + 1)));
				if (!stack.isEmpty()) {
					TreeNode parent = stack.peek();
					if (parent.left != null)
						parent.right = currentNode;
					else
						parent.left = currentNode;
				}
				stack.push(currentNode);
			}
		}
		return stack.isEmpty() ? null : stack.peek();
	}
	
	 public TreeNode[] splitBST(TreeNode root, int V) {
	        if(root==null) 
	        	return new TreeNode[]{null, null};
	        
	        TreeNode[] splitted;
	        if(root.val<= V) {
	            splitted = splitBST(root.right, V);
	            root.right = splitted[0];
	            splitted[0] = root;
	        } else {
	            splitted = splitBST(root.left, V);
	            root.left = splitted[1];
	            splitted[1] = root;
	        }
	        
	        return splitted;
	    }

}
