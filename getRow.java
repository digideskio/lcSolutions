// O(N) solution

public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        
        if (rowIndex == 0) {
            return list;    
        }
        
        for (int i = 1; i <= rowIndex; i++) {
            list.add(0);
            for (int j = i; j >= 1; j--) {
                list.set(j, list.get(j - 1) + list.get(j));
            }
        }
        return list;
    }
}