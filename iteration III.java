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