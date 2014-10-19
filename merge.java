/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    
    class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval o1, Interval o2) {
            if (o1.start < o2.start) return -1;
            else if (o1.start == o2.start) return 0;
            else return 1;
        }
    }

    
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() < 2) {
            return intervals;
        }

        Collections.sort(intervals, new IntervalComparator());

        Interval last = intervals.get(0);

        Iterator<Interval> iter = intervals.iterator();

        while (iter.hasNext()) {
            Interval in = iter.next();
            if (last != in) {
                if (last.end >= in.start) {
                    last.end = Math.max(in.end, last.end);
                    iter.remove();
                } else {
                    last = in;
                }
            }
        }
        return intervals;
    }
}