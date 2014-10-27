// use priorityqueue (heap structure)

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
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists==null || lists.size()==0) return null;
        
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.size(), new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.val < o2.val) return -1;
                else if (o1.val > o2.val) return 1;
                return 0;
            }
        });

        for (ListNode node : lists) {
            if (node!=null)
                queue.add(node);
        }

        ListNode fakeHead = new ListNode(0);
        ListNode p = fakeHead;
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            p.next = node;
            if (node.next != null)
                queue.add(node.next);
            p = node;
        }
        
        return fakeHead.next;
    }
}