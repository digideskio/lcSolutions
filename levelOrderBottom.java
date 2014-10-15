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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new LinkedList<List<Integer>>();
        
        if (root == null) {
            return list;
        }
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            Queue<TreeNode> next = new LinkedList<TreeNode>();
            List<Integer> ls = new LinkedList<Integer>();
            for (TreeNode node : queue) {
                ls.add(node.val);
                if (node.left!=null) 
                    next.add(node.left);
                if (node.right!=null) 
                    next.add(node.right);
            }
            list.add(0, ls);
            queue = next;
            
        }
        
        return list;
    }
}