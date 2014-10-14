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

        if (root==null) return Collections.emptyList();

        List<List<Integer> > results = new LinkedList<List<Integer> >();

        List<TreeNode> curr = new LinkedList<TreeNode>();

        curr.add(root);

        while(!curr.isEmpty()){

            //iterate one level at a time, without extra space to record level!

            List<TreeNode> next= new LinkedList<TreeNode>();

            List<Integer> r = new LinkedList<Integer>();

            for (TreeNode node:curr){
                if (node.left!=null) 
                    next.add(node.left);

                if (node.right!=null) 
                    next.add(node.right);

                r.add(node.val);
            }
                results.add(0,r);

            curr=next;
        }
        return results;
    }
}