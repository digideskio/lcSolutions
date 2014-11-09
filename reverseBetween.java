// break the case when m == 1 or m != 1

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
       public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n) return head;

        ListNode p = head;
        ListNode left = null;
        if (m >= 2) {
            int count = m - 2;
            while (count > 0) {
                p = p.next;
                count--;
            }
            left = p;
            p = p.next;
        }

        ListNode start = p;
        ListNode last = null;
        int count = n - m;
        while (count >= 0) {
            ListNode next = p.next;
            if (last != null) {
                p.next = last;
            } else {
                p.next = null;
            }
            last = p;
            p = next;
            count--;
        }
        start.next = p;
        if (left != null) {
            left.next = last;
            return head;
        } else {
            return last;
        }

    }
}