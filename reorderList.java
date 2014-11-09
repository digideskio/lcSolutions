// TWO important lesssons:
// 1. mind reverse func, break cycle!
// 2. make sure next pointer is correct!

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
     public void reorderList(ListNode head) {
        if (head == null) return;
        ListNode p1 = head;
        ListNode mid = findMid(head);
        ListNode p2 = mid.next;

        if (p2 == null) return;

        mid.next = null;

        // reverse p2
        p2 = reverse(p2);
        ListNode dummyHead = new ListNode(0);
        ListNode dummy = dummyHead;
        while (p1 != null && p2 != null) {
            ListNode p1Next = p1.next;
            ListNode p2Next = p2.next;
            dummy.next = p1;
            dummy = dummy.next;
            dummy.next = p2;
            dummy = dummy.next;
            p1 = p1Next;
            p2 = p2Next;
        }

        if (p1 != null) {
            dummy.next = p1;
            p1.next = null;
        }
    }

    ListNode reverse(ListNode head) {
        ListNode p = head;
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
        return last != null ? last : head;
    }

    ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }
}