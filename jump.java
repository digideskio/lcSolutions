// excellent solution

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