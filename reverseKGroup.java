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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        if (k < 2) return head;
        
        int cnt = k - 1;
        ListNode p = head;
        while (p.next != null && cnt > 0) {
            p = p.next;
            cnt--;
        }
        
        if (cnt != 0) return head;
        ListNode rest = p.next;
        p.next = null;
        ListNode tail = head;
        head = reverse(head);
        tail.next = reverseKGroup(rest, k);
        
        return head;
    }
    
    ListNode reverse(ListNode head) {
        ListNode p = head;
        if (head == null || head.next == null) return head;
        
        ListNode last = null;
        while (p != null) {
            ListNode next = p.next;
            if (last != null) {
                p.next = last;
            } else {
                p.next = null;
            }
            last = p;
            p = next;
        }
        
        return last;
    }
}