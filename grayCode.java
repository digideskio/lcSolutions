//Using BFS search

public class Solution {
    public List<Integer> grayCode(int n) {
        Set<Integer> includes = new HashSet<Integer>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(0);
        includes.add(0);
        list.addAll(grayCode(n, 0, includes));
        return list;
    }
    
    public List<Integer> grayCode(int n, int num, Set<Integer> includes) {
         ArrayList<Integer> list = new ArrayList<Integer>();
        if (n == 0) {
            return list;
        }
        
        for (int i = 0; i < n; i++) {
            int newNum = changeOneBit(num, i);
            if (!includes.contains(newNum)) {
                list.add(newNum);
                includes.add(newNum);
                list.addAll(grayCode(n, newNum, includes));
            }
        }
        
        return list;
        
    }
    
    public int changeOneBit(int num, int index) {
        if (((num >> index) & 1) != 0) {
            num &= ~(1 << index);
        } else {
            num |= 1 << index;
        }
        
        return num;
    }
}