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
    Map<Integer,Integer> map;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode head = null;
        if (preorder == null || preorder.length == 0) return head;
        if (preorder.length == 1) {
            head = new TreeNode(preorder[0]);
            return head;
        }       
        
        map = new HashMap<Integer,Integer>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        int len = preorder.length;
        return buildTree(preorder, 0, len - 1, inorder, 0, len -1);
    }
    
    TreeNode buildTree(int[] preorder, int pstart, int pend, int[] inorder, int istart, int iend) {
        if (pstart > pend || istart > iend) return null;
        TreeNode node = new TreeNode(preorder[pstart]);
        if (pstart == pend) return node;
        int rootIdx = map.get(preorder[pstart]);
        int leftLen = rootIdx - istart;
        int rightLen = iend - rootIdx;
        node.left = buildTree(preorder, pstart + 1, pstart + leftLen, inorder, istart, rootIdx - 1);
        node.right = buildTree(preorder, pend - rightLen + 1, pend, inorder, rootIdx + 1, iend);
        return node;
    }
}