// be carefull with the removal part
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
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<Integer> val = new LinkedList<Integer>();
        
        return sumNumbers(root, val);
    }
    
    public int sumNumbers(TreeNode node, LinkedList<Integer> val) {
        if (node.left == null && node.right == null) {
            val.add(node.val);
            int num = convertToNum(val);
            val.remove(val.size() - 1);
            return num;
        }
        
        int sum = 0;
        val.add(node.val);
        if (node.left != null) {
            sum += sumNumbers(node.left, val);
           
        }
        if (node.right != null) {
            sum += sumNumbers(node.right, val);
            
        }
        val.remove(val.size() - 1);
        return sum;
    }
    
    public int convertToNum(List<Integer> val) {
        int num = 0;
        for (Integer i : val) {
            num *= 10;
            num += i;
        }
        
        return num;
    }
}