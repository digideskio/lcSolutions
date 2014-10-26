// start from right, find the one that can make the switch
// be careful: after switch, do a sort for the right subarray.

public class Solution {
    public void nextPermutation(int[] num) {
        if (num.length < 2) return;
        boolean flag = false;
        int pos = 0;
        for (int i = num.length - 2; i >= 0; i--) {
            int leastLarge = Integer.MAX_VALUE;
            int leastLargetIndex = i;
            for (int j = i + 1; j < num.length; j++) {
                if (num[j] > num[i]) {
                    flag = true;
                    if (num[j] <= leastLarge) {
                        leastLargetIndex = j;
                        leastLarge = num[j];
                    }
                }
            }
            if (flag) {
                int temp = num[i];
                num[i] = num[leastLargetIndex];
                num[leastLargetIndex] = temp;
                pos = i;
                break;
            }
        }

        if (flag) {
            int arrayToSort[] = Arrays.copyOfRange(num, pos + 1, num.length);
            Arrays.sort(arrayToSort);
            for (int i = pos + 1; i < num.length; i++) {
                num[i] = arrayToSort[i-pos-1];
            }
        } else {
            Arrays.sort(num);
        }
    }

// find another solution that has a better time complexity
    public static void nextPermutation(int[] num) {
                int i = num.length - 2;
                for(; i >= 0 && num[i] >= num[i+1]; i--);

                if(i >= 0) {
                    int j = i + 1;
                    for(; j<num.length && num[i] < num[j]; j++) ;
                    exchange(num, i, j-1);
                }

                i ++ ; 
                int k = num.length - 1;
                for(; i<k; i++, k--)
                    exchange(num, i, k);
            }

            private static void exchange(int[] num, int i, int j) {
                int t = num[i];
                num[i] = num[j];
                num[j] = t;
            }
}