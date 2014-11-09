// add null item to avoid empty list;


/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; left = null; right = null; }
 * }
 */
public class Solution {
    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }

    List<TreeNode> generateTrees(int low, int high) {
        List<TreeNode> lst = new LinkedList<TreeNode>();
        if (low > high) {
            lst.add(null);
        }

        for (int i = low; i <= high; i++) {

            List<TreeNode> leftTrees =  generateTrees(low, i - 1);
            List<TreeNode> rightTrees =  generateTrees(i + 1, high);

            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode head = new TreeNode(i);
                    head.left = left;
                    head.right = right;
                    lst.add(head);
                }
            }


        }

        return lst;
    }
}