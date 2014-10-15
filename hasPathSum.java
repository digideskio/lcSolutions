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
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        
        sum -= root.val;
        if (sum == 0) {
            if (root.left == null && root.right == null)
            return true;
        }
        boolean left = false;
        boolean right = false;        
        
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }
}