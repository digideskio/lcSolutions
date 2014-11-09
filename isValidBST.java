/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        
        TreeNode p = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        Integer pre = null;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                p = p.left;
                
            } else {
                p = stack.pop();
                if (pre != null && pre >= p.val) return false;
                pre = p.val;
                p = p.right;
            }
        }
        return true;
    }
}