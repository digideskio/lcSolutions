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
 public ListNode rotateRight(ListNode head, int n) {
        int len = 0;
        ListNode p = head;
        while (p != null) {
            p = p.next;
            len++;
        }

        if (len <= 1) return head;

        n = n % len;
        
        if (n == 0) return head;

        int cnt = n;
        ListNode fast = head;
        while (cnt > 0) {
            fast = fast.next;
            cnt--;
        }
        ListNode slow = head;
        
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        ListNode returnHead = slow.next;
        slow.next = null;
        fast.next = head;
        return returnHead;
    }
}