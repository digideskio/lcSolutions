// excellent solution

// 

public int jump(int[] A) {
    int maxReach = A[0];
    int edge = 0;
    int minstep = 0;
    
    for(int i = 1; i < A.length; i++) {
        if (i > edge) {
            minstep += 1;
            edge = maxReach;
            if(edge > A.length - 1)
                return minstep;
        }
        maxReach = Math.max(maxReach, A[i] + i);
    }
    
    return minstep;
} 

// another solution from the end of array, don't know why from the start got TLEed
public int jump(int[] A) {
   int counter = 0;
        if (A == null || A.length == 0) return counter;

        int start = A.length-1;
        while (start > 0) {
            int index = start;
            int lowSoFar = start;
            while (index >= 0) {
                if (A[index] >= start - index) {
                    if (lowSoFar > index) lowSoFar = index;
                }
                index--;
            }
            start = lowSoFar;
            counter++;
        }


        return counter;
    }