public class Solution {
    public List<List<Integer>> subsets(int[] S) {
        Arrays.sort(S);
        return subsets(S, 0);
    }
    
    List<List<Integer>> subsets(int[] S, int index) {
        List<List<Integer>> lst = new LinkedList<List<Integer>>();
        if (S.length == index) {
            lst.add(Collections.<Integer>emptyList());
            return lst;
        }
        
        List<List<Integer>> rest = subsets(S, index + 1);
        for (List<Integer> list : rest) {
            List<Integer> nList = new LinkedList<Integer>(list);
            nList.add(0, S[index]);
            lst.add(nList);
        }
        lst.addAll(rest);
        
        return lst;
    }
}