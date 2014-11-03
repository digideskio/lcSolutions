public class Solution {
    public List<List<Integer>> threeSum(int[] num) {
        List<List<Integer>> lst = new LinkedList<List<Integer>>();
        if (num.length < 3) return lst;
        
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        Arrays.sort(num);
        
        for (int i = 0; i < num.length - 2; i++) {
            int start = i + 1;
            int end = num.length - 1;
            while (start < end) {
                int sum = num[i] + num[start] + num[end];
                if (sum > 0) {
                    end--;
                } else if (sum < 0) {
                    start++;
                } else {
                    List<Integer> l = new ArrayList<Integer>();
                    l.add(num[i]);
                    l.add(num[start]);
                    l.add(num[end]);
                    set.add(l);
                    start++;
                    end--;
                }
            }
        }
        lst.addAll(set);
        return lst;
    }
}