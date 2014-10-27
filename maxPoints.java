// use comapre when comparing double
// -0.0 and 0.0 has different hashcode in java (?)
// o(N^2) solution


/**/
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
    public int maxPoints(Point[] points) {
        if(points.length <= 2)
            return points.length;

        int n = points.length;
        int globalMax = 2;
        for(int i = 0; i < n - 1; i++){
            HashMap<Double, Integer> mp = new HashMap<Double, Integer>();
            int dup = 0;
            for(int j = i + 1; j < n; j++){
                int dy = points[j].y - points[i].y;
                int dx = points[j].x - points[i].x;
                double k; 
                if(dy == 0 && dx == 0){
                    dup++;
                    continue;
                }
                else if(dx == 0)
                    k = Double.MAX_VALUE;
                else
                    k = ((double)dy) / ((double)dx) + 0.0;  
            

                if(!mp.containsKey(k))
                    mp.put(k, 2);
                else
                    mp.put(k, mp.get(k)+1);
            }
            int localMax = 1;
            for(Map.Entry<Double, Integer> entry : mp.entrySet()){
                double key = entry.getKey();
                if(mp.get(key) > localMax)
                    localMax = mp.get(key);
            }
            localMax += dup;
            if(localMax > globalMax)
                globalMax = localMax;
        }

        return globalMax;
    }
}