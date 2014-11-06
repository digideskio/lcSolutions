/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// inorder traversal to find descending nodes
public class Solution {
    public void recoverTree(TreeNode root) {
        TreeNode[] nodes = new TreeNode[2];
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;
        TreeNode pre = null;
        boolean flag = false;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                if (pre != null && pre.val > p.val) {
                    if (!flag) {
                        nodes[0] = pre;
                        nodes[1] = p;
                        flag = true;
                    } else {
                        nodes[1] = p;
                    }
                    
                } 
                pre = p;
                p = p.right;
                
            }
        }
        
        int temp = nodes[0].val;
        nodes[0].val = nodes[1].val;
        nodes[1].val = temp;
        
    }
}