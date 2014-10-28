// use HashSet, no comments.....

public class Solution {
      public List<List<Integer>> permuteUnique(int[] num) {
        List<List<Integer>> returnList = new ArrayList<List<Integer>>();
        returnList.add(new ArrayList<Integer>());

        for (int i = 0; i < num.length; i++) {
            Set<List<Integer>> currentSet = new HashSet<List<Integer>>();
            for (List<Integer> l : returnList) {
                for (int j = 0; j < l.size() + 1; j++) {
                    ArrayList<Integer> T = new ArrayList<Integer>(l);
                    T.add(j, num[i]);
                    currentSet.add(T);
                }
            }
            returnList = new ArrayList<List<Integer>>(currentSet);
        }

        return returnList;
    }
    
}