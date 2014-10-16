// be carefull with head, tail case!

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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slower = head;
        ListNode faster = head;
        while (n > 0) {
            faster = faster.next;
            n--;
        }
        
        if (faster == null) {
            return head.next;
        }
        
        while (faster.next != null) {
            faster = faster.next;
            slower = slower.next;
        }
        
        
        if (slower.next != null) {
            slower.next = slower.next.next;
        } else {
            slower.next = null;
        }
        
        
        return head;
    }
}