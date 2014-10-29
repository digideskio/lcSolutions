// be careful with int overflow issues
// any other solution than long?

public class Solution {
    public int sqrt(int x) {
        if (x <= 0) return 0;

        long root = 1;
        long lastRoot = 0;
        long newRoot = 0;
        do {
            long base = lastRoot;
            newRoot = lastRoot;
            root = 1;
            while (newRoot * newRoot <= x) {
                lastRoot = newRoot;
                newRoot = (root + base);
                root *= 2;
            }
        } while (newRoot - lastRoot > 1);

        return (int)lastRoot;


    }
}

// second approach
public class Solution {
    public int sqrt(int x) {
        if (x <= 0) return 0;

        long low = 0;
        long high = x;

        while (low <= high) {
            long mid = (low + high) / 2;
            // can use x/mid and mid to avoid overflowing
            if (mid * mid > x) high = mid - 1;
            else if (mid * mid < x) low = mid + 1;
            else return (int)mid;
        }
        
        return (int)high;,
    }
}