// first is naive O(n^2) solution

public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; i++) {
            if (canCompleteCircuit(gas, cost, i)) {
                return i;
            }
        }
        return -1;
    }
    
    boolean canCompleteCircuit(int[] gas, int[] cost, int start) {
        if (gas[start] < cost[start]) return false;
        int curGas = gas[start] - cost[start];
        int length = gas.length;
        int step = start + 1;
        if (step == length) step = 0;
        boolean success = true;
        while (step != start) {
            if (curGas + gas[step] >= cost[step]) {
                curGas += gas[step] - cost[step];
                step++;
                if (step == length) {
                    step = 0;
                }
            } else {
                success = false;
                break;
            } 
        }
        return success;
    }
}