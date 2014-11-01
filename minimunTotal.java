// a rather complicated solution, but got a chance to code the construction of complete binary tree
public class Solution {
   class Node {
        int val;
        Node right = null, left = null;

        Node(int val) {
            this.val = val;
        }
    }

    class Tree {
        Node head;
        int count = 0;
        int level = 0;
        Queue<Node> queue = new LinkedList<Node>();

        void insert(Node node) {
            count++;
            if (head == null) {
                head = node;
                level++;
            } else {
                if (((level + 1) * level / 2) < count) {
                    level++;
                    Node pCur = queue.peek();
                    pCur.left = node;
                } else if (((level + 1) * level / 2) == count) {
                    Node pCur = queue.poll();
                    pCur.right = node;
                } else if (count >2) {
                    Node pCur = queue.poll();
                    pCur.right = node;
                    pCur = queue.peek();
                    pCur.left = node;
                }

            }
            queue.add(node);

        }
    }

    // could build up tree using dfs or bfs
    // or could come up with formula to transfer level, index to pos in list
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0) return 0;
        Tree tree = new Tree();
        for (List<Integer> lst : triangle) {
            for (Integer val : lst) {
                Node node = new Node(val);
                tree.insert(new Node(val));
            }
        }

        return findMin(tree.head);
    }
    HashMap<Node,Integer> map = new HashMap<Node,Integer>();
    public int findMin(Node root) {
        if (map.containsKey(root)) return map.get(root);
        int left = 0, right = 0;
        if (root.left != null)
            left = findMin(root.left);
        if (root.right != null)
            right = findMin(root.right);
        map.put(root, Math.min(left, right) + root.val);
        return Math.min(left, right) + root.val;
    }
}