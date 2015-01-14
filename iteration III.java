// painter
int minCost(int[] colors, int houseNum) {
    int numColors = colors.length;
    if (numColors == 0 || houseNum == 0) return 0;
    int[][] cost = new int[houseNum][numColors];
    
    for (int i = 1; i < houseNum; i++) {
        for (int j = 0; j < numColors; j++) {
            int minCost = Integer.MAX_VALUE;
            for (int color = 0; color < numColors; color++) {
                if (color != j) {
                    minCost = Math.min(minCost, cost[i - 1][color] + colors[j]);
                }
            }
            cost[i][j] = minCost;
        }
    }
    
    return cost[houseNum - 1][numColors - 1];
}


// backpack 
int maxValue(int left, int[] weights, int[] values) {
    int numPacks = weights.length;
    if (numPacks == 0) return 0;
    int[][] pack = new int[left][numPacks];
   
    for (int i = 1; i < left; i++) {
        if (i >= weights[0]) pack[i][0] = values[0];
        for (int j = 1; j < numPacks; j++) {
            pack[i][j] = pack[i][j - 1];
            if (i >= weights[j]) {
                pack[i][j] = Math.max(pack[i][j - 1], pack[i - weights[j]][j - 1] + values[j]);
            }
        }
    }
    
    return pack[left - 1][numPacks - 1];
}

class Solution {
    public static Set<Integer> findMissingNumbers(int arr[], int m) {

        Set<Integer> set = new HashSet<Integer>();
        // dealing with array that does not start with 1
        for (int i = 1; i < arr[0]; i++) {
            set.add(i);
        }
        // dealing with the middle elements
        findMissingNumbersBetweenToIndexes(arr, set, 0, arr.length - 1);
        // recalculate m for any extra tail's numbers
        m = m - (((arr[arr.length - 1] - arr[0] + 1) - arr.length))
                - (arr[0] - 1);
        // dealing with the any extra tail's numbers
        for (int i = 1; i <= m; i++) {
            set.add(arr[arr.length - 1] + i);
        }

        return set;
    }

    public static void findMissingNumbersBetweenToIndexes(int[] arr,
                                                          Set<Integer> set, int start, int finish) {
        if (arr.length == 1) {
            return;
        }
        if ((arr[finish] - arr[start] + 1) - (finish - start) == 0) {
            return;
        }
        if (start + 1 == finish) {
            for (int i = arr[start] + 1; i < arr[finish]; i++) {
                set.add(i);
            }
            return;
        }
        int middle = (start + finish) / 2;
        // right
        findMissingNumbersBetweenToIndexes(arr, set, start, middle);

        // left
        findMissingNumbersBetweenToIndexes(arr, set, middle, finish);
    }
}




// ransom question
class Solution {
    boolean canRansom(String note, String magStr) {
        int[] count = new int[256];
        if (note.length() == 0) return true;
        int pNote = 0, pMag = 0;

        while (pNote < note.length()) {
            if (note.charAt(pNote) == ' ') {
                pNote++;
            } else if (count[note.charAt(pNote)] > 0) {
                count[note.charAt(pNote)]--;
                pNote++;
            } else {
                if (pMag == magStr.length()) return false;
                count[magStr.charAt(pMag)]++;
                pMag++;
            }
            
        }

        return pNote == note.length();
    }
}



 void levelOrder(TreeNode root) {
    if (root == null) return;

    List<List<Integer>> lst = new ArrayList<>();
    List<Integer> curLst = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    int curCnt = 1;
    int level = 0;
    boolean isEnd = true;
    while (!queue.isEmpty()) {
        TreeNode node = queue.poll();
        curCnt--;

        if (node != null) {
            curLst.add(node.val);
            queue.add(node.left);
            queue.add(node.right);
            isEnd = false;
        } else {
            curLst.add(null);
            queue.add(null);
            queue.add(null);
        }

        if (curCnt == 0) {
            if (!isEnd) {
                lst.add(curLst);
                curLst = new ArrayList<>();
                curCnt = queue.size();
                level++;
                isEnd = true;
            } else {
                break;
            }

        }
    }
}

// find peak element
public class Solution {
    public int findPeakElement(int[] num) {
       int n = num.length;
        if (n <= 1) return 0;
        // handle the first and last element in num[]
        if (num[0] > num[1]) return 0;
        if (num[n - 1] > num[n - 2]) return n - 1;
        int left = 1, right = n - 2;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (num[mid] > num[mid - 1] && num[mid] > num[mid + 1]) {
                return mid;
            } else if (num[mid] > num[mid + 1]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}



void cashWays(int value, int[] bills, int index) {
    List<List<Integer>> lst = new ArrayList<>();
    
    if (value == 0) {
        List<Integer> oneWay = new LinkedList<>();
        lst.add(oneWay);
        return lst;
    } 
    
    if (index == bills.length) return lst;
    
    for (int i = 0; i * bills[index] <= value; i++) {
        List<List<Integer>> rest = cashWays(value - i * bills[index], bills, index + 1);
        for (List<Integer> oneWay : rest) {
            List<Integer> newWay = new LinkedList<>(oneWay);
            for (int j = 0; j < i; j++) {
                newWay.add(0, bills[index]);
            }
            lst.add(newWay);
        }
    }
    
    return lst;
}



void moveZeros(int[] arr) {
    if (arr.length == 0) return;
    
    int start = 0;
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] != 0) {
            swap(arr, start, i);
            start++;
        } 
    }
}

void swap(int[] arr, int i, int j) {
    if (i == j) return;
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
}


public int maxProfit(int[] prices) {
        int k = 4;
        int n = prices.length;
        // max profit from i to j
        int profit[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            int min = prices[i];
            int maxProfit = 0;
            for (int j = i + 1; j < n; j++) {
                min = Math.min(prices[j], min);
                maxProfit = Math.max(prices[j] - min, maxProfit);
                profit[i][j] = maxProfit;
            }
        }

        int cache[][] = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                int max = cache[i - 1][j];
                for (int m = 0; m < i; m++) {
                    max = Math.max(max, cache[i - m][j - 1] + profit[i - m - 1][i - 1]);
                }
                cache[i][j] = max;
            }
        }

        return cache[n][k];
    }








//isMatch Regular Expression Matching 
public class Solution {
   public boolean isMatch(String s, String p) {
        if (s.length() == 0 && p.length() == 0) return true;
        else if (p.length() == 0) return false;

        if (s.length() == 0) {
            if (p.length() > 1 && p.charAt(1) == '*') {
                return isMatch(s, p.substring(2));
            } else {
                return false;
            }
        } else {
            if (p.length() > 1 && p.charAt(1) == '*') {
                 String ss = s;
                char match = p.charAt(0);
                if(isMatch(ss, p.substring(2))) return true;
                while (ss.length() > 0 && ((ss.charAt(0) == match) || match == '.')) {
                    ss = ss.substring(1);
                    if(isMatch(ss, p.substring(2))) return true;
                }
                return false;
            } else if (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') {
                return isMatch(s.substring(1), p.substring(1));
            } else   {
                return false;
            }
        }
    }
}


//Longest Common Prefix 
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        StringBuffer sb = new StringBuffer();
        if (strs == null || strs.length == 0) return sb.toString();
        if (strs.length == 1) return strs[0];
        
        for (int i = 0; ; i++) {
            boolean success = true;
            for (int j = 0; j < strs.length - 1; j++) {
                if (strs[j].length() <= i || strs[j + 1].length() <= i || strs[j].charAt(i) != strs[j+1].charAt(i)) {
                    success = false;
                    break;
                }
            }
            if (success) {
                sb.append(strs[0].charAt(i));
            } else {
                break;
            }
        }
        
        return sb.toString();
    }
}

//combination sum II
public class Solution {
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        Arrays.sort(num);
        return comSum(num, target, 0);
    }

    List<List<Integer>> comSum(int[] num, int target, int index) {
        List<List<Integer>> lst = new LinkedList<List<Integer>>();
        if (target == 0) {
            lst.add(Collections.<Integer>emptyList());
            return lst;
        } else if (target < 0) {
            return lst;
        }

        for (int i = index; i < num.length; i++) {
            if (i != index && num[i] == num[i - 1]) continue;
            List<List<Integer>> rest = comSum(num, target - num[i], i + 1);
            for (List<Integer> r : rest) {
                List<Integer> l = new LinkedList<Integer>(r);
                l.add(0, num[i]);
                lst.add(l);
            }
        }
        return lst;

    }
}

// wildcard matching
public class Solution {
    Set<String> set = new HashSet<String>();
    public boolean isMatch(String s, String p) {
        int ss = 0, pp = 0, match = 0, starIdx = -1;
        while (ss < s.length()){
            // advancing both pointers
            if (pp < p.length()  && (p.charAt(pp) == '?' || s.charAt(ss) == p.charAt(pp))){
                ss++;
                pp++;
            }
            // * found, only advancing p pointer
            else if (pp < p.length() && p.charAt(pp) == '*'){
                starIdx = pp;
                match = ss;
                pp++;
            }
            // last p pointer was *, advancing string pointer
            else if (starIdx != -1){
                pp = starIdx + 1;
                match++;
                ss = match;
            }
            //current p pointer is not star, last patter pointer was not *
            //characters do not match
            else return false;
        }

        //check for remaining characters in p
        while (pp < p.length() && p.charAt(pp) == '*')
            pp++;

        return pp == p.length();

    }
}

// reversewords: Reverse Words in a String
// two sols, the second of which used regular expression

class Solution {
    public String reverseWords(String s) {
       StringBuilder sb = new StringBuilder();
       
       int strEnd = s.length() - 1;
       while (strEnd >= 0 && s.charAt(strEnd) == ' ') {
           strEnd--;
       }
       if (strEnd < 0) return sb.toString();
       
       int left = strEnd,
           right = strEnd;
           
       while (left >= 0) {
           boolean isWord = false;
            while (left >= 0 && s.charAt(left) != ' ') {
                left--;
                isWord = true;
            }
            
            if (isWord) {
                sb.append((sb.length() == 0 ? "" : " ") + s.substring(left + 1, right + 1));
            }
            
            while (left >= 0 && s.charAt(left) == ' ') {
                left--;
            }
            
            right = left;
       }
       
       return sb.toString();
    }
  
        

}


//Insert Interval 
public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if(intervals.isEmpty()){
            intervals.add(newInterval);
            return intervals;
        }

        int start = newInterval.start;
        int end = newInterval.end;
        ListIterator<Interval> li = intervals.listIterator();
        boolean inserted = false;
        while(li.hasNext()){
            Interval itv = li.next();
            if(start <= itv.end){
                if(end < itv.start){ //newInterval does not overlap with current itv, time to insert
                    li.previous();
                    li.add(new Interval(start, end));
                    
                    inserted = true;
                    break;
                }

                // still some overlap so compare start & end
                start = Math.min(start, itv.start);
                end = Math.max(end, itv.end);
                li.remove();
            }
        }

        if(!inserted){
            intervals.add(new Interval(start, end));
        }

        return intervals;
    }
}