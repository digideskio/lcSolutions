/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        
        ListNode p = head;
        
        while (p != null) {
            ListNode next = p.next;
            dummy = insert(dummy, p);
            p = next;
        }
        
        return dummy.next;
    }
    
    public ListNode insert(ListNode head, ListNode node) {
        if (head.next == null) {
            head.next = node;
            node.next = null;
            return head;
        }
        
        ListNode p = head;
        while (p.next != null) {
            if (node.val < p.next.val) {
                ListNode temp = p.next;
                p.next = node;
                node.next = temp;
                return head;
            }
            p = p.next;
        }
        
        p.next = node;
        node.next = null;
        return head;
    }
}