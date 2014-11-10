// carefull with dummy's next pointer

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
      public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummyHead = new ListNode(0);
        ListNode dummy = dummyHead;
        ListNode fast = head;
        Set<Integer> set = new HashSet<Integer>();
        ListNode preNode = null;
        while (fast != null) {
            ListNode next = fast.next;
            if (preNode != null && preNode.val != fast.val) {
                if (!set.contains(preNode.val)) {
                    dummy.next = preNode;
                    dummy = dummy.next;
                    dummy.next = null;
                }    
            }
            if (preNode != null)
                set.add(preNode.val);
            preNode = fast;
            fast = next;
        }   
        
        if (!set.contains(preNode.val)) {
            dummy.next = preNode;
        }
        
        return dummyHead.next;
    }
}