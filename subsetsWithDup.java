public class Solution {
    public List<List<Integer>> subsetsWithDup(int[]) {
        Arrays.sort(S);
        return subsets(S, 0);
    }
    
    List<List<Integer>> subsets(int[] S, int index) {
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        if (S.length == index) {
            set.add(Collections.<Integer>emptyList());
            List<List<Integer>> lst = new LinkedList<List<Integer>>(set);
            return lst;
        }
        
        List<List<Integer>> rest = subsets(S, index + 1);
        for (List<Integer> list : rest) {
            List<Integer> nList = new LinkedList<Integer>(list);
            nList.add(0, S[index]);
            set.add(nList);
        }
        set.addAll(rest);
        
        List<List<Integer>> lst = new LinkedList<List<Integer>>(set);
            return lst;
    }
}