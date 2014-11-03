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


// O(n) * 2 solution, iterate twice.
    public int candy(int[] ratings) {


    int result=0;
    int l=ratings.length;
    int[] values=new int[l];
    // In order
    for(int i=0;i<l;i++){
        if(i-1>=0&&ratings[i]>ratings[i-1]){
            values[i]=values[i-1]+1;
        }
        else{
            values[i]=1;
        }
    }
    // Reverse order
    for(int i=l-1;i>=0;i--){
        if(i+1<l&&ratings[i]>ratings[i+1]){
            if(values[i]<values[i+1]+1){
                values[i]=values[i+1]+1;
            }
        }
        result+=values[i];
    }

    return result;
}
}