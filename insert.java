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
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals.size() == 0) {
            intervals.add(newInterval);
            return intervals;
        }

        int index = findIndex(intervals, newInterval.start, 0, intervals.size() - 1);
     
        intervals.add(index, newInterval);
        // post
        ListIterator<Interval> it = intervals.listIterator(index + 1);
        while (it.hasNext()) {
            Interval next = it.next();
            if (next.start <= newInterval.end) {
                newInterval.end = Math.max(next.end, newInterval.end);
                it.remove();
            } else {
                break;
            }
        }
        // pre
        if (index > 0) {
            Interval pre = intervals.get(index - 1);
            if (pre.end >= newInterval.start) {
                pre.end = Math.max(pre.end, newInterval.end);
                intervals.remove(newInterval);
            }
        }
        return intervals;
    }

    int findIndex(List<Interval> intervals, int target, int low, int high) {
        if (low > high) return low;

        int mid = (low + high) / 2;
        if (intervals.get(mid).start == target) return mid;
        else if (intervals.get(mid).start > target) return findIndex(intervals, target, low, mid - 1);
        else return findIndex(intervals, target, mid + 1, high);
    }
}