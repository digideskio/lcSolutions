public class Solution {
    
       public List<List<Integer>> combinationSum2(int[] num, int target) {
        Arrays.sort(num);
        return combinationSum2(num, target, 0);
    }

    List<List<Integer>> combinationSum2(int[] num, int target, int index) {
        List<List<Integer>> returnLst = new LinkedList<List<Integer>>();
        if (num.length == index) {
            return Collections.emptyList();
        }

        for (int i = index; i < num.length; i++) {
            int newTarget = target - num[i];
            if (newTarget == 0) {
                List<Integer> l = new LinkedList<Integer>();
                l.add(num[i]);
                if (!returnLst.contains(l))
                    returnLst.add(l);
            } else if (newTarget > 0) {
                List<List<Integer>> rest = combinationSum2(num, newTarget, i + 1);
                for (List<Integer> restLst : rest) {
                    List<Integer> l = new LinkedList<Integer>(restLst);
                    l.add(0, num[i]);
                    if (!returnLst.contains(l))
                        returnLst.add(l);
                }
            }

        }
        return returnLst;
    }
}