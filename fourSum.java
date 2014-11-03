// a solution from two sums

public class Solution {
    public List<List<Integer>> fourSum(int[] num, int target) {
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        Arrays.sort(num);
        for (int i = 0; i < num.length; i++) {
            for (int j = i + 1; j < num.length; j++) {
                int start = j + 1;
                int end = num.length - 1;
                while (start < end) {
                    int sum = num[i] + num[j] + num[start] + num[end];
                    if (sum == target) {
                        List<Integer> l = new LinkedList<Integer>();
                        l.add(num[i]);
                        l.add(num[j]);
                        l.add(num[start]);
                        l.add(num[end]);
                        set.add(l);
                        end--;
                        start++;
                    } else if (sum > target) {
                        end--;
                    } else {
                        start++;
                    }
                }
            }
        }
        List<List<Integer>> lst = new LinkedList<List<Integer>>();
        lst.addAll(set);
        
        return lst;
    }
}