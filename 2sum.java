public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int index1 = 0;
        int index2 = numbers.length - 1;
        HashMap<Integer,List<Integer>> map = new HashMap<Integer,List<Integer>>();
        for (int i = 0; i < numbers.length; i++) {
            List<Integer> lst = map.get(numbers[i]);
            if (lst != null) {
                lst.add(i);
            } else {
                lst = new LinkedList<Integer>();
                lst.add(i);
                map.put(numbers[i], lst);
            }
        }
        Arrays.sort(numbers);
        while (index1 < index2) {
            int sum = numbers[index1] + numbers[index2];
            if (sum < target) {
                index1++;
            } else if (sum > target) {
                index2--;
            } else break;
        }
        
        int i1 = numbers.length;
        int i2 = -1;
        for (Integer index : map.get(numbers[index1])) {
            if (index < i1) {
                i1 = index;
            }
        }
        for (Integer index : map.get(numbers[index2])) {
            if (index > i2) {
                i2 = index;
            }
        }
        int min = Math.min(i1, i2);
        int max = Math.max(i1, i2);
        int[] toReturn = {min + 1, max + 1};
        return toReturn;
    }
}