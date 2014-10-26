// think twice before using recursion
// problems like this could get stack overflowed easily

public class Solution {
    
    public boolean canJump(int[] A) {
        int max = 0;
        for (int i=0; i<=max && i<A.length; i++) {
            max = Math.max(A[i] + i, max);
        }
        return max >= A.length - 1;
    }
    
   
    // recursive solution which got TLEed :(
   static Set<Integer> map = new HashSet<Integer>();
    public static boolean canJump(int[] A) {
        if (A == null || A.length == 0) return true;
        return canJump(A, 0)
;    }

    public static boolean canJump(int[] A, int index) {
        if (map.contains(index)) return false;
        if (index >= A.length - 1) return true;

        int jumps = A[index];
        if (A.length - 1 - index <= jumps) return true;
        else {
            for (int i = index + jumps; i > index; i--) {
                if(canJump(A, i)) return true;
            }
        }

        map.add(index);
        return false;
    }
}