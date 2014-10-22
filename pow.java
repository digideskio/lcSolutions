// pow use recursive call
// also include a iterative solution

public class Solution {
    public double pow(double x, int n) {
        if (x == 0.0) return 0;
        if (n < 0) return 1.0 / power(x, n);
        return power(x, n);
    }
    
    public double power(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double v = power(x, n / 2);
        if (n % 2 != 0) {
            v = v * v * x;
        } else {
            v = v * v;
        }
        
        return v;
    }

    public double powAlt(double x, int n) {
      double result = 1.0;
      for(int i = n; i != 0; i /= 2, x *= x) {
          if( i % 2 != 0 ) {
              result *= x;
          }
      }
      return n < 0 ? 1.0 / result : result;
    }
}