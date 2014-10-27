// very good solution
// check local peak node (there could be only one peak node)
// recursive approach to check every peak node

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
    
    public int maxPathSum(TreeNode root) {
        int maxValue = Integer.MIN_VALUE;
        maxPathDown(root, maxValue);
        return maxValue;
    }

    private int maxPathDown(TreeNode node, int max) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left, max));
        int right = Math.max(0, maxPathDown(node.right, max));
        int maxValue = Math.max(max, left + right + node.val);
        return Math.max(left, right) + node.val;
    }
}