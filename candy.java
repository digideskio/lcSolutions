// O(1) space O(n) time solution

public class Solution {
    public int candy(int[] ratings) {
        
        int length = 0;
        int preCanCnt = 1;
        int beforeDenc = preCanCnt;
        
        if (ratings.length == 0) return 0;
        if (ratings.length == 1) return 1;
        int total = 1;
        for (int i = 1; i < ratings.length; i++) {
            
            if (ratings[i] < ratings[i-1]) {
                length++;
                if (beforeDenc <= length) {
                    total++;
                }
                total += length;
                preCanCnt = 1;
            } else {
                int current = 0;
                if (ratings[i] > ratings[i-1]) {
                    current = (preCanCnt + 1);
                } else {
                    current = 1;
                }
                total += current;
                preCanCnt = current;
                length = 0;
                beforeDenc = current;
            }
        }
        
        return total;
    }
}