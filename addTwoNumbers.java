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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode fakeHead = new ListNode(0);
        ListNode p = fakeHead;
        while (l1 != null && l2 != null) {
            int sum = (l1.val + l2.val + carry) % 10;
            carry = (l1.val + l2.val + carry) / 10;
            p.next = new ListNode(sum);
            p = p.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        
        while (l1 != null) {
            int sum = (l1.val + carry) % 10;
            carry = (l1.val + carry) / 10;
            p.next = new ListNode(sum);
            p = p.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            int sum = (l2.val + carry) % 10;
            carry = (l2.val + carry) / 10;
            p.next = new ListNode(sum);
            p = p.next;
            l2 = l2.next;   
        }
        if (carry != 0) {
            p.next = new ListNode(1);
        }
        return fakeHead.next;
    }
}