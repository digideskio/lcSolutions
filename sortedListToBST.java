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
        ListNode node = findMid(head);
        TreeNode headNode = new TreeNode(node.val);
        ListNode next = node.next;
        if (next == null) return headNode;
        node.next = null;
        if (head != node)
            headNode.left = sortedListToBST(head);
        headNode.right = sortedListToBST(next);
        return headNode;
    }
    ListNode findMid(ListNode node) {
        if (node == null) return null;
        ListNode slow = node;
        ListNode fast = node;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}