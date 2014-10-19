// use hashset to solve within O(n)

public class Solution {
        public int longestConsecutive(int[] num) {
        if (num.length == 0) return 0;
        HashSet<Integer> set = new HashSet<Integer>();

        for (int n: num) {
            set.add(n);
        }

        int maxLength = 1;
        

        for (int number: num) {
            
            int n = number + 1;
            int length = 1;
            while(set.contains(n)) {
                set.remove(n);
                length++;
                n++;
            }
            
            n = number - 1;
            while (set.contains(n)) {
                set.remove(n);
                n--;
                length++;
            }
            if (length > maxLength) maxLength = length;
        }

        return maxLength;
    }
}