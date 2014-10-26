public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        return combinationSum(candidates, target, 0);
    }
    
    public List<List<Integer>> combinationSum(int[] candidates, int target, int index) {
        List<List<Integer>> lst = new LinkedList<List<Integer>>();
        if (target < 0 || index >= candidates.length) {
            return lst;
        }
        
        while (index < candidates.length && candidates[index] <= target) {
            if (candidates[index] == target) {
                List<Integer> sol = new LinkedList<Integer>();
                sol.add(candidates[index]);
                lst.add(sol);
                return lst;
            } else {
                List<List<Integer>> rest = combinationSum(candidates, target - candidates[index], index);
                for (List<Integer> item : rest) {
                    item.add(0, candidates[index]);
                    lst.add(item);
                }
            }
            index++;
        }
        return lst;
    }
}