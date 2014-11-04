// use queue to do bfs traversal with set to mark visited node
// use map to map already cloned nodes

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return node;
        HashMap<Integer,UndirectedGraphNode> map = new HashMap<Integer,UndirectedGraphNode>();
        Set<UndirectedGraphNode> set = new HashSet<UndirectedGraphNode>();
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        queue.add(node);
        
        
        while (!queue.isEmpty()) {
            UndirectedGraphNode n = queue.poll();
            if (set.contains(n)) continue;
            set.add(n);
            UndirectedGraphNode nNode;
            if (map.containsKey(n.label)) {
                nNode = map.get(n.label);
            } else {
                nNode = new UndirectedGraphNode(n.label);
                map.put(n.label, nNode);
            }
            for (UndirectedGraphNode child : n.neighbors) {
                if (map.containsKey(child.label)) {
                    nNode.neighbors.add(map.get(child.label));
                } else {
                    UndirectedGraphNode newNode = new UndirectedGraphNode(child.label);
                    nNode.neighbors.add(newNode);
                    map.put(child.label, newNode);
                }
                queue.add(child);
            }
            
        }
        
        return map.get(node.label);
        
    }
}