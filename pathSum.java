// recursive approach

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
       public List<List<Integer>> pathSum(TreeNode root, int sum) {
        LinkedList<Integer> path = new LinkedList<Integer>();

        if (root == null) {
            List<List<Integer>> paths = new LinkedList<List<Integer>>();
            return paths;
        }

        return pathSum(root, sum, path);
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum, LinkedList<Integer> path) {
        LinkedList<List<Integer>> paths = new LinkedList<List<Integer>>();
        if (root.left == null && root.right == null) {
            if (sum - root.val == 0) {
                path.add(root.val);
                List<Integer> p = new LinkedList<Integer>(path);
                paths.add(p);
                path.removeLast();
                return paths;
            }
        }

        path.add(root.val);
        if (root.left != null) paths.addAll(pathSum(root.left, sum - root.val, path));
        if (root.right != null) paths.addAll(pathSum(root.right, sum - root.val, path));
        path.removeLast();

        return paths;
    }
}