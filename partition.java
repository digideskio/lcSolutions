/* TRAPS:
    1. head and tail: special care
    2. when combining: avoid cycle and set tail.next to null!!!!!!
*/
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
     public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;

        ListNode lastHead = new ListNode(0);
        ListNode lastP = lastHead;

        ListNode p = head;

        // deal with head
        while (p != null && p.val >= x) {
            lastP.next = p;
            lastP = lastP.next;

            p = p.next;
        }
        if (p == null) {
            return lastHead.next;
        }

        head = p;
        ListNode last = p;
        p = p.next;

        while (p != null) {
            if (p.val >= x) {
                last.next = p.next;
                lastP.next = p;
                lastP = lastP.next;
            } else {
                last = last.next;
            }
            p = p.next;
        }

        lastP.next = null;
        last.next = lastHead.next;
        return head;

    }
}