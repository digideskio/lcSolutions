// Preorder traversal
// two ways;
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
    public void flattenAlt(TreeNode root) {
       if (root == null) {
           return;
       } 
       
       Stack<TreeNode> stack = new Stack<TreeNode>();
       stack.push(root);
       TreeNode last = null;
       
       while (!stack.isEmpty()) {
           TreeNode p = stack.pop();
           if (last != null) {
               last.right = p;
               last.left = null;
           }
           last = p;
           if (p.right != null) {
               stack.push(p.right);
           }
           if (p.left != null) {
               stack.push(p.left);
           }
       }
       
    }
    public void flatten(TreeNode root) {
       if (root == null) {
           return;
       } 
       
       Stack<TreeNode> stack = new Stack<TreeNode>();
       TreeNode p = root;
       TreeNode last = null;
       
       while (!stack.isEmpty() || p != null) {
           if (p != null) {
               if (last != null) {
                   last.right = p;
                   last.left = null;
                   last = p;
               } else {
                   last = p;
               }
               if (p.right != null) stack.push(p.right);
               p = p.left;
           } else {
               p = stack.pop();
           }
       }
       
    }
}