public class Solution {
        public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> list = new LinkedList<List<Integer>>();
        if (k == 0) {
            list.add(Collections.<Integer>emptyList());
            return list;
        }
        List<Integer> item = new ArrayList<Integer>();

        combine(n, k, list, item);
        return list;
    }

    public void combine(int n, int k, List<List<Integer>> list, List<Integer> item) {
        
        if (k == 0) {
            list.add(item);
            return;
        }

        for (List<Integer> i : getAllPosibilities(n, item)) {
            combine(n, k - 1, list, i);
        }

        
    }

    public List<List<Integer>> getAllPosibilities(int n, List<Integer> item) {
        List<List<Integer>> list = new LinkedList<List<Integer>>();

        int last = 0;
        if (!item.isEmpty()) {
            last = item.get(item.size() - 1);
        }
        for (int i = last + 1; i <= n; i++) {
            List<Integer> newItem = new ArrayList<Integer>(item);
            newItem.add(i);
            list.add(newItem);
        }

        return list;
    }
}