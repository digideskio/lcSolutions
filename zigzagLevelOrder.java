// like usual level traversal but instead of queue, used stack 
// don't know if there is a better solution to get rid of fromLeft

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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new LinkedList<List<Integer>>();
        if (root == null) {
            return list;
        }
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        boolean fromLeft = true;
        
        while (!stack.isEmpty()) {
            Stack<TreeNode> next = new Stack<TreeNode>();
            List<Integer> line = new LinkedList<Integer>();
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                line.add(node.val);
                if (fromLeft) {
                    if (node.left != null) next.push(node.left);
                    if (node.right != null) next.push(node.right);
                } else {
                    if (node.right != null) next.push(node.right);
                    if (node.left != null) next.push(node.left);
                }
            }
            fromLeft = !fromLeft;
            list.add(line);
            stack = next;
        }
        
        return list;
    }
}