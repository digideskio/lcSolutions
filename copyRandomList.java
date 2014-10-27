// one pass solution with O(n) complexity
// essence is to use hashmap to map original node to new copied node

public RandomListNode copyRandomList(RandomListNode head) {
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode p = head;
        RandomListNode newHead = null;
        RandomListNode last = null;
        while (p != null) {
            RandomListNode node;
            if (map.containsKey(p)) {
                node = map.get(p);
            } else {
                node = new RandomListNode(p.label);
                map.put(p, node);

            }
            if (last != null) {
                last.next = node;
            } else {
                newHead = node;
            }

            //take care of random pointer
            if (p.random != null) {
                if (map.containsKey(p.random)) {
                    node.random = map.get(p.random);
                } else {
                    RandomListNode random = new RandomListNode(p.random.label);
                    map.put(p.random, random);
                    node.random = random;
                }
            }


            p = p.next;
            last = node;
        }


        return newHead;
    }
