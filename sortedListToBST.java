// remember to delete the mid node and break list to two
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
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
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        ListNode pre = findMid(head);
        ListNode node = pre.next;
        if (node == null) return new TreeNode(pre.val);
        TreeNode headNode = new TreeNode(node.val);
        ListNode next = node.next;
        pre.next = null;
        headNode.left = sortedListToBST(head);
        headNode.right = sortedListToBST(next);
        return headNode;
    }
    ListNode findMid(ListNode node) {
        if (node == null) return null;
        ListNode slow = node;
        ListNode fast = node;
        ListNode pre = node;
        while (fast.next != null && fast.next.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return pre;
    }
}