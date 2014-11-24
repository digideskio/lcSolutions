/* 
@Q: Word Break
@Method: DP
@Complexity: Time O(n^2) worst case; Space O(n)
@note: 
*/
public class Solution {
     public boolean wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0) return true;
        boolean[] marks = new boolean[s.length()];
        marks[0] = true;
        
        for (int i = 0; i < s.length(); i++) {
            if (marks[i]) {
                for (int j = i + 1; j <= s.length(); j++) {
                    String str = s.substring(i, j);
                    if (dict.contains(str)) {
                        if (j == s.length()) return true;
                        else marks[j] = true;
                    }
                }
            }
        }
        
        return false;
    }
}


/* 
@Q: Palindrome Partitioning
@Method: DP
@Complexity: Time O(n^2); Space O(n^2)
@note: 
*/
public class Solution {
    public int minCut(String s) {
        if (s == null || s.length() == 0) return 0;
        // minCuts[i] stores s[0..i]'s min cuts 
        int[] minCuts = new int[s.length()];
        // isPan[i][j] stores whether s[i..j] is palindrome
        boolean[][] isPan = new boolean[s.length()][s.length()];
        isPan[0][0] = true;
        
        for (int i = 0; i < s.length(); i++) {
            int min = Integer.MAX_VALUE;
            for (int j = i; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j) && ((i - j < 2) || isPan[j+1][i-1])) {
                    isPan[j][i] = true;
                } 
                if (isPan[j][i]) {
                    if (j != 0) {
                        min = Math.min(min, minCuts[j-1] + 1);    
                    } else {
                        min = 0;
                    }
                }
                minCuts[i] = min;
                
            }
        }
        
        return minCuts[s.length()-1];
    }
}



/* 
@Q: Median of Two Array
@Method: binary search
@Complexity: Time O(log (m+n)); 
@note: Base cases and calculation of middle
*/
public class Solution {
    public static double findMedianSortedArrays(int A[], int B[]) {
    int m = A.length;
    int n = B.length;
 
    if ((m + n) % 2 != 0) // odd
        return (double) findKth(A, B, (m + n) / 2, 0, m - 1, 0, n - 1);
    else { // even
        return (findKth(A, B, (m + n) / 2, 0, m - 1, 0, n - 1) 
            + findKth(A, B, (m + n) / 2 - 1, 0, m - 1, 0, n - 1)) * 0.5;
    }
}
 
public static int findKth(int A[], int B[], int k, 
    int aStart, int aEnd, int bStart, int bEnd) {
 
    int aLen = aEnd - aStart + 1;
    int bLen = bEnd - bStart + 1;
 
    // Handle special cases
    if (aLen == 0)
        return B[bStart + k];
    if (bLen == 0)
        return A[aStart + k];
    if (k == 0)
        return A[aStart] < B[bStart] ? A[aStart] : B[bStart];
 
    int aMid = aLen * k / (aLen + bLen); // a's middle count
    int bMid = k - aMid - 1; // b's middle count
 
    // make aMid and bMid to be array index
    aMid = aMid + aStart;
    bMid = bMid + bStart;
 
    if (A[aMid] > B[bMid]) {
        k = k - (bMid - bStart + 1);
        aEnd = aMid;
        bStart = bMid + 1;
    } else {
        k = k - (aMid - aStart + 1);
        bEnd = bMid;
        aStart = aMid + 1;
    }
 
    return findKth(A, B, k, aStart, aEnd, bStart, bEnd);
}
}

/* 
@Q: Trapping Rain Water 
@Method: Two pointers
@Complexity: Time O(n); 
@note: 
*/

public class Solution {
    public int trap(int[] A) {
        if (A == null || A.length == 0) return 0;
        int water = 0;
        int leftHeight = 0, rightHeight = 0;
        int left = 0, right = A.length - 1;
        while (left <= right) {
            if (A[left] < A[right]) {
                water += Math.max(leftHeight - A[left], 0);
                leftHeight = Math.max(A[left], leftHeight);
                left++;
            } else {
                water += Math.max(rightHeight - A[right], 0);
                rightHeight = Math.max(A[right], rightHeight);
                right--;
            }
        }
        
        return water;
    }
}


/* 
@Q: Largest Rectangle in Histogram  
@Method: stack to store height
@Complexity: Time O(n); 
@note: Pay attention to iterations: everything new heigh must be pushed to stack!
*/
public class Solution {
    public int largestRectangleArea(int[] height) {
        int max = 0;
        if (height == null || height.length == 0) return 0;
        // store non-descending height indeces
        Stack<Integer> stack = new Stack<Integer>();
        
        for (int i = 0; i < height.length; i++) {
            if (!stack.empty() && height[stack.peek()] > height[i]) {
                while (!stack.empty() && height[stack.peek()] > height[i]) {
                    int index = stack.pop();
                    max = Math.max(max, (stack.empty() ? i : i - stack.peek() - 1) * height[index]);
                }
                stack.push(i);
            } else {
                stack.push(i);
            }
        }
        
        while (!stack.empty()) {
            int index = stack.pop();
            max = Math.max(max, (stack.empty() ? height.length : height.length - stack.peek() - 1) * height[index]);
        }
        
        return max;
        
    }
}

/* 
@Q: Construct Binary Tree from Preorder and Inorder Traversal   
@Method: recursive
@Complexity: Time O(n); 
@note: Pay attention to difference of preorder and inorder!!
*/
public class Solution {
    Map<Integer,Integer> map = new HashMap<Integer,Integer>();
    public TreeNode buildTree(int[] preorder, int[] inorder) { 
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    TreeNode buildTree(int[] preorder, int ps, int pe, int[] inorder, int is, int ie) {
        if (ps > pe) return null;

        TreeNode head = new TreeNode(preorder[is]);
        int preIdx = map.get(preorder[is]);
        head.left = buildTree(preorder, ps, preIdx - 1, inorder, is + 1, is + preIdx - ps);
        head.right = buildTree(preorder, preIdx + 1, pe, inorder, ie - (pe - preIdx) + 1, ie);

        return head;
    }
}


/* 
@Q: Unique Paths
@Method: recursive
@Complexity: Time O(n); 
@note: Base case should return 1 instead of 0
*/
public class Solution {
    public int uniquePaths(int m, int n) {
        return uniquePaths(m - 1, n - 1, new int[m][n]);
    }
    
    int uniquePaths(int m, int n, int[][] map) {
        if (m == 0 && n == 0) return 1;
        
        if (map[m][n] != 0) return map[m][n];
        
        int ways = 0;
        if (m > 0) {
            ways += uniquePaths(m - 1, n, map);
        }
        if (n > 0) {
            ways += uniquePaths(m, n - 1, map);
        }
        
        map[m][n] = ways;
        return ways;
    }
}



/* 
@Q: Level Order Traversal II
@Method: recursive
@Complexity: Time O(n); 
@note: 
*/
public class Solution {
    List<List<Integer>> lst = new LinkedList<List<Integer>>();
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        levelOrderBottom(root, 0);
        return lst;
    }
    
    void levelOrderBottom(TreeNode root, int level) {
        if (root == null) return;
        if (lst.size() < level + 1) {
            List<Integer> l = new LinkedList<Integer>();
            l.add(root.val);
            lst.add(0, l);
        } else {
            List<Integer> l = lst.get(lst.size() - level - 1);
            l.add(root.val);
        }
        levelOrderBottom(root.left, level + 1);
        levelOrderBottom(root.right, level + 1);
    }
}



/* 
@Q: remove elements
@Method: iterative
@Complexity: Time O(n); 
@note: 
*/
public class Solution {
    public int removeElement(int[] A, int elem) {
       int pointer = 0;
       for (int i = 0; i < A.length; i++) {
           if (A[i] != elem) {
               A[pointer] = A[i];
               pointer++;
           } 
       }
       
       return pointer;
    }
}

/* 
@Q: Reverse Linked List II 
@Method: iterative
@Complexity: Time O(n); 
@note: add dummy to the head to avoid so many corner cases
*/
public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {  
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode dummy = dummyHead;
        
        for (int i = 1; i < m; i++) {
            dummy = dummy.next;
        }
        
        ListNode reverse = dummy.next;
        ListNode reverseTail = reverse;
        ListNode last = null;
        for (int i = m; i <= n; i++) {
            ListNode next = reverse.next;
            if (last == null) {
                reverse.next = null;
            } else {
                reverse.next = last;
            }
            last = reverse;
            reverse = next;
        }
        
        dummy.next = last;
        reverseTail.next = reverse;
        
        return dummyHead.next;
        
    }
}



/* 
@Q: Remove Duplicates from Sorted List 
@Method: two pointers, only advance new p when val is unique
@Complexity: Time O(n); 
@note: 
*/
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        Set<Integer> dups = new HashSet<Integer>();
        ListNode p = head;
        ListNode dummyHead = new ListNode(0);
        ListNode np = dummyHead;
        while (p != null) {
            ListNode next = p.next;
            if (!dups.contains(p.val)) {
              np.next = p;
              p.next = null;
              dups.add(p.val);
              np = np.next;
            }
            p = next;
        }
        
        return dummyHead.next;
    }
}


/* 
@Q: Minimum Window Substring
@Method: two pointers, maintain the min window and shift forward till the end
@Complexity: Time O(2n); 
@note: 
*/
public class Solution {
    public String minWindow(String S, String T) {
       int[] count = new int[256];
       int[] pattern = new int[256];
       for (int i = 0; i < T.length(); i++) {
           pattern[T.charAt(i)]++;
           count[T.charAt(i)]++;
       }
       
       int minWin = Integer.MAX_VALUE;
       int minStart = 0;
       int start = 0;
       int cntNum = T.length();
       for (int i = 0; i < S.length(); i++) {
           if (pattern[S.charAt(i)] != 0 && count[S.charAt(i)] > 0) {
               cntNum--;
           }
           count[S.charAt(i)]--;
           if (cntNum == 0) {
               while (start <= i) {
                   if (pattern[S.charAt(start)] != 0 && count[S.charAt(start)] == 0) {
                       if (minWin > (i - start + 1)) {
                           minWin = i - start + 1;
                           minStart = start;
                       }
                       break;
                   }
                   count[S.charAt(start)]++;
                   start++;
               }
           }
       }
       
       return minWin == Integer.MAX_VALUE ? "" : S.substring(minStart, minStart + minWin); 
       
    }
}


/* 
@Q: Binary Tree Maximum Path Sum 
@Method: recursive, calculate each peak point's max value;
@Complexity: Time O(n); 
@note: 
*/
public class Solution {
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        
        maxSum(root);
        return max;
    }
    
    int maxSum(TreeNode root) {
        if (root == null) return 0;
        
        int left = Math.max(maxSum(root.left), 0);
        int right = Math.max(maxSum(root.right), 0);
        int rootMax = Math.max(Math.max(left, right), left + right) + root.val;
        max = Math.max(max, rootMax);
        return Math.max(left, right) + root.val;
    }
}

/* 
@Q: Combination Sum II
@Method: use hashmap to store unique lists
@Complexity: ??? Time O(n!); 
@note: 
*/
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

        Set<List<Integer>> unique = new HashSet<List<Integer>>();
        for (int i = index; i < num.length; i++) {

            List<List<Integer>> rest = comSum(num, target - num[i], i + 1);
            for (List<Integer> r : rest) {
                List<Integer> l = new LinkedList<Integer>(r);
                l.add(0, num[i]);
                if (!unique.contains(l)) {
                    unique.add(l);
                }
            }
        }
        lst.addAll(unique);
        return lst;

    }
}


/* 
@Q: Four sum
@Method: use hashmap to store unique lists
@Complexity: Time O(n^3); 
@note: 
*/
public class Solution {
    public List<List<Integer>> fourSum(int[] num, int target) {
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        Arrays.sort(num);
        for (int i = 0; i < num.length; i++) {
            for (int j = i + 1; j < num.length; j++) {
                int start = j + 1;
                int end = num.length - 1;
                while (start < end) {
                    int sum = num[i] + num[j] + num[start] + num[end];
                    if (sum == target) {
                        List<Integer> l = new LinkedList<Integer>();
                        l.add(num[i]);
                        l.add(num[j]);
                        l.add(num[start]);
                        l.add(num[end]);
                        set.add(l);
                        end--;
                        start++;
                    } else if (sum > target) {
                        end--;
                    } else {
                        start++;
                    }
                }
            }
        }
        List<List<Integer>> lst = new LinkedList<List<Integer>>();
        lst.addAll(set);
        
        return lst;
    }
}

/* 
@Q: Convert Sorted Array to Binary Search Tree 
@Method: 
@Complexity: Time O(n); 
@note: 
*/
public class Solution {
    public TreeNode sortedArrayToBST(int[] num) {
        return sortedArrayToBst(num, 0, num.length - 1);
    }
    
    TreeNode sortedArrayToBst(int[] num, int low, int high) {
        if (low > high) return null;
        int mid = (low + high) / 2;
        TreeNode node = new TreeNode(num[mid]);
        node.left = sortedArrayToBst(num, low, mid - 1);
        node.right = sortedArrayToBst(num, mid + 1, high);
        return node;
    }
}

/* 
@Q: Longest Consecutive Sequence
@Method: 
@Complexity: Time O(n); 
@note: 
*/
public class Solution {
    public int longestConsecutive(int[] num) {
        if (num.length == 0) return 0;
        Set<Integer> record = new HashSet<Integer>();
        for (int n : num) {
            record.add(n);
        }
        int cnt = 0;
        for (int number : num) {
            int count = 1;
            int n = number + 1;
            while (record.contains(n)) {
                record.remove(n);
                n++;
                count++;
            }
            
            n = number - 1;
             while (record.contains(n)) {
                record.remove(n);
                n--;
                count++;
            }
            
            cnt = Math.max(count, cnt);
        }
        
        return cnt;
    }
}

/* 
@Q: Pow(x, n) 
@Method: binary search
@Complexity: Time O(logn); 
@note: 
*/
public class Solution {
    public double pow(double x, int n) {
        if (n > 0) return power(x, n);
        else return 1 / power(x, -n);
    }
    
    public double power(double x, int n) {
        if (n == 0) return 1;
        
        double v = power(x, n / 2);
        if (n % 2 == 0) {
            return v * v;
        } else {
            return v * v * x;
        }
    }
}
/* 
@Q: Pow(x, n) 
@Method: binary search
@Complexity: Time O(logn) Space O(1); 
@note: 
*/
class Solution {
    double power(double x, int n) {
        double val = x;
        int start = 0;
        double result = 1;
        while (start < n) {
            int pow = 1;
            x = val;
            while (start <= n - pow) {
                result *= x;
                x *= x;
                start += pow;
                pow *= 2;
            }
        }
        return result;
    }
}

/* 
@Q: Valid Palindrome 
@Method: 
@Complexity: Time O(n); 
@note: 
*/
public class Solution {
     public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        
        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) j--;
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) i++;
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) return false;
            i++;
            j--;
        }
        
        return true;
     }
}

/* 
@Q: Word Ladder II 
@Method: BFS search, the point is to trim branches
@Complexity: ???Time O(26^n); 
@note: 
*/
public class Solution {
     public List<List<String>> findLadders(String start, String end, Set<String> dict) {

         Map<String,Integer> ladderCnt = new HashMap<String,Integer>();
         Map<String,List<String>> backtrack = new HashMap<String,List<String>>();
         for (String s : dict) {
             ladderCnt.put(s, Integer.MAX_VALUE);
         }
         ladderCnt.put(end, Integer.MAX_VALUE);
         ladderCnt.put(start, 0);
         Queue<String> queue = new LinkedList<String>();
         queue.add(start);
         int min=Integer.MAX_VALUE;
         boolean firstFound = false;
         while (!queue.isEmpty()) {
             String word = queue.poll();
             int step = ladderCnt.get(word) + 1;
             for (int i = 0; i < word.length(); i++) {
                 for (char c = 'a'; c <= 'z'; c++) {
                     String newWord  =word.substring(0, i) + c + word.substring(i + 1);
                     if (newWord.equals(end)) {
                         firstFound = true;
                     }
                     if (ladderCnt.containsKey(newWord) && ladderCnt.get(newWord) >= step + 1) {
                         if (!firstFound && ladderCnt.get(newWord) > step + 1) {
                             queue.add(newWord);
                         }
                         List<String> lst = backtrack.get(newWord);
                         if (lst == null) {
                             lst = new LinkedList<String>();
                             backtrack.put(newWord, lst);
                         }
                         lst.add(word);

                         ladderCnt.put(newWord, step + 1);
                     }
                 }
             }
         }
        if (!firstFound) return new LinkedList<List<String>>();
         return getAllLadders(backtrack, end);
     }

     List<List<String>> getAllLadders(Map<String,List<String>> backtrack, String word) {
         List<List<String>> lst = new LinkedList<List<String>>();
         List<String> words = backtrack.get(word);
         if (words == null) {
             List<String> startLst = new LinkedList<String>();
             startLst.add(word);
             lst.add(startLst);
             return lst;
         }
         for (String w : words) {
             List<List<String>> rest = getAllLadders(backtrack, w);
             for (List<String> ladder : rest) {
                 List<String> newLadder = new LinkedList<String>(ladder);
//                 newLadder.add(w);
                 newLadder.add(word);
                 lst.add(newLadder);
             }
         }
         return lst;

     }
}

/* 
@Q: Populating Next Right Pointers in Each Node II 
@Method: iterative approach
@Complexity: Time O(n); 
@note: 
*/
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        
        TreeLinkNode pre = root;
        TreeLinkNode cur = null;
        TreeLinkNode head = null;
        while (pre != null) {
            if (pre.left != null) {
                if (head == null) {
                    head = pre.left;
                    cur = pre.left;
                } else {
                    cur.next = pre.left;
                    cur = cur.next;
                }
                
            }
            if (pre.right != null) {
                if (head == null) {
                    head = pre.right;
                    cur = pre.right;
                } else {
                    cur.next = pre.right;
                    cur = cur.next;
                }
            }
            if (pre.next == null) {
                pre = head;
                cur = null;
                head = null;
            } else {
                pre = pre.next;
            }
        }
        
    }

}



/* 
@Q: insert into a cyclic sorted list
@Method: 
@Complexity: Time O(n); 
@note: very neat solution 
*/

public ListNode insert(ListNode node, int x) {
    if (node == null) {
        node = new ListNode(x);
        node.next = node;
        return node;
    }

    ListNode cur = node;
    ListNode pre = null;
    do {
        pre = cur;
        cur = cur.next;
        if (pre.val <= x && cur.val >= x) {
            break;
        }
        if (pre.val > cur.val) {
            break;
        } 
    } while (cur != node);

    ListNode n = new ListNode(x);
    n.next = cur;
    pre.next = n;
    return node;
}

/* 
@Q: Edit Distance 
@Method: 
@Complexity: Time O(n^2); 
@note: careful with initial cases of starter row and column
*/
public class Solution {
    public int minDistance(String word1, String word2) {
       int[][] minDis = new int[word1.length()+1][word2.length()+1];
       for (int i = 1; i <= word2.length(); i++) {
           minDis[0][i] = i;
       }
       for (int i = 1; i <= word1.length(); i++) {
           minDis[i][0] = i; 
       }
       
       for (int i = 1; i <= word1.length(); i++) {
           for (int j = 1; j <= word2.length(); j++) {
               if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                   minDis[i][j] = minDis[i-1][j-1];
               } else {
                   minDis[i][j] = Math.min(minDis[i][j-1], minDis[i-1][j], minDis[i-1][j-1]) + 1;
               }
           }
       }
       
       return minDis[word1.length()][word2.length()];
    }
}


/* 
@Q: Longest Substring Without Repeating Characters 
@Method: 
@Complexity: Time O(n); 
@note: careful with start pos after dup found
*/
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int len = 0;
        int start = 0;
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i)) && map.get(s.charAt(i)) >= start) {
                start = map.get(s.charAt(i)) + 1;
            }
            len = Math.max(len, i - start + 1);
            map.put(s.charAt(i), i);
        }
        
        return len;
    }
}

/* 
@Q: Add Binary 
@Method: 
@Complexity: Time O(n); 
@note: 
*/
public class Solution {
    public String addBinary(String a, String b) {
        a = new StringBuffer(a).reverse().toString();
        b = new StringBuffer(b).reverse().toString();
        
        int index = 0;
        int carry = 0;
        StringBuffer sum = new StringBuffer();
        while (index < a.length() && index < b.length()) {
            int num = a.charAt(index) - '0' + b.charAt(index) - '0' + carry;
            sum.append(((num % 2)));
            carry = num / 2;
            index++;
        }
        while (index < a.length()) {
            int num = a.charAt(index) - '0' + carry;
            sum.append(((num % 2)));
            carry = num / 2;
            index++;
        }
        while (index < b.length()) {
            int num = b.charAt(index) - '0' + carry;
            sum.append(((num % 2)));
            carry = num / 2;
            index++;
        }
        if (carry != 0) {
            sum.append('1');
        }
        return sum.reverse().toString();
    }
}


/* 
@Q: Sliding window
@Method: 
@Complexity: Time O(n); 
@note: 
*/
public class Solution {
    public int[] maxSlidingWindow(int[] A, int w) {
        int[] max = new int[A.length-w];
        Deque<Integer> window = new ArrayDeque<>();
        int winSize = 0;
        while (winSize < w) {
            if (!window.isEmpty() && A[window.peekLast()] <= A[winSize]) {
                window.removeLast();
            } 
            window.addLast(winSize);
            winSize++;
        }

        for (int i = w; i < A.length; i++) {
            max[i-w] = window.peekFirst();
            while (!window.isEmpty() && A[window.peekLast()] <= A[winSize]) {
                window.removeLast();
            }
            while (!window.isEmpty() && A[window.peekFirst()] <= i - w) {
                window.removeFirst();
            }
            window.addLast(i);
        }

        max[A.length-w] = A[window.peekFirst()];
    }
}

/* 
@Q: The Painter’s Partition Problem
@Method: 
@Complexity: Time O(n^2); 
@note: 
*/

class Solution {

    public int maxPainter(int[] A, int ptNum) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int[][] map = new int[ptNum+1][A.length+1];
        maxPainter(A, ptNum, 0, map);
        return 3;
    }


    int maxPainter(int[] A, int ptNum, int from, int[][] map) {
        if (map[ptNum][from] != 0) {
            return map[ptNum][from];
        }
        if (ptNum == 1) {
            return sum(A, from, A.length);
        }
        if (from == A.length - 1) {
            return A[from];
        }

        int bestMin = Integer.MAX_VALUE;
        for (int i = from; i <= A.length; i++) {
            int sum = sum(A, from, i);
            int rest = maxPainter(A, ptNum - 1, i, map);
            bestMin = Math.min(Math.max(sum, rest), bestMin);

        }
        map[ptNum][from] = bestMin;
        return bestMin;
    }

    int sum(int[] A, int from, int to) {
        int sum = 0;
        for (int i = from; i < to; i++) {
            sum += A[i];
        }
        return sum;
    }
}

/* 
@Q: Pascal's triangle II
@Method: 
@Complexity: Time O(n^2), Space O(k); 
@note: 
*/
public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> lst = new LinkedList<>();
        lst.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            lst.add(0);
            for (int j = i; j >= 1; j--) {
                lst.set(j, lst.get(j - 1) + lst.get(j));
            }
        }
        return lst;
    }
}

/* 
@Q: Linked List Cycle II
@Method: 
@Complexity: Time O(n), Space O(1); 
@note: 
*/
pub
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode fast = head;
        ListNode slow = head;
        do {
            slow = slow.next;
            fast = fast.next.next;
        } while (fast != null && fast.next != null && fast != slow);
        
        if (fast == slow) {
            fast = head;
            while (fast != slow) {
                fast = fast.next;
                slow = slow.next;
            }
            return fast;
        } else {
            return null;
        }
    }
}

/* 
@Q: sqrt
@Method: careful with overflow
@Complexity: Time O(log n) 
@note: 
*/
public class Solution {
    public int sqrt(int x) {
        if (x <= 0) return 0;
        return sqrt(x, 1, x);
    }
    
    int sqrt(int x, int start, int end) {
     if (start > end) return end;
        int mid = (start + end) / 2;
        if (mid > Integer.MAX_VALUE / mid) return sqrt(x, start, mid - 1);
        if (mid * mid == x) return mid;
        else if (mid * mid > x) return sqrt(x, start, mid - 1);
        else return sqrt(x, mid + 1, end);
    }
}

/* 
@Q: Remove Nth Node From End of List 
@Method: careful with head removal case
@Complexity: Time O(n) 
@note: 
*/
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
       ListNode fast = head;
       while (n > 0) {
           fast = fast.next;
           n--;
       }
       if (fast == null) {
           return head.next;
       }
       ListNode slow = head;
       while (fast.next != null) {
           slow = slow.next;
           fast = fast.next;
       }
       
       slow.next = slow.next.next;
       return head;
       
    }
}

/* 
@Q: Unique Binary Search Trees II 
@Method: careful with base case
@Complexity: Time O(n!) 
@note: 
*/
public class Solution {
    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }

    List<TreeNode> generateTrees(int low, int high) {
        List<TreeNode> lst = new LinkedList<TreeNode>();
        if (low > high) {
            lst.add(null);
        }

        for (int i = low; i <= high; i++) {

            List<TreeNode> leftTrees =  generateTrees(low, i - 1);
            List<TreeNode> rightTrees =  generateTrees(i + 1, high);

            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode head = new TreeNode(i);
                    head.left = left;
                    head.right = right;
                    lst.add(head);
                }
            }


        }

        return lst;
    }
}


/* 
@Q: Remove Duplicates from Sorted List II 
@Method: 
@Complexity: Time O(n) 
@note: good do while demo
*/
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode dummyHead = new ListNode(0);
        ListNode dummy = dummyHead;
        ListNode p = head;
        Set<Integer> set = new HashSet<Integer>();
        ListNode pre = null;
       
        do {
            pre = p;
            p = p.next;
            if ((p == null || pre.val != p.val) && !set.contains(pre.val)) {
                dummy.next = pre;
                dummy = dummy.next;
            }
            set.add(pre.val);
        } while (p != null);
        
        dummy.next = null;
        return dummyHead.next;
    }
}

/* 
@Q: Merge Intervals
@Method: 
@Complexity: Time O(n log n) 
@note: good do while demo; inheriting Comparator as anonymous class
*/
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1) return intervals;
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.start < o2.start) return -1;
                else if (o1.start > o2.start) return 1;
                else return 0;
            }
        });
        Iterator<Interval> it = intervals.iterator();
        Interval last = null;
        Interval cur = it.next();
        do {
            last = cur;
            cur = it.next();
            if (cur != null && cur.start <= last.end) {
                last.end = Math.max(last.end, cur.end);
                it.remove();
                cur = last;
            } 
        } while (it.hasNext());
        
        return intervals;
    }
}

/* 
@Q: Insert Interval 
@Method: 
@Complexity: Time O(n) worst case
@note: 
*/
public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        int index = findInsertPos(intervals, newInterval.start, 0, intervals.size() - 1);
        intervals.add(index, newInterval);
        // handle left
        if (index > 0) {
            Interval pre = intervals.get(index - 1);
            if (pre.end >= newInterval.start) {
                pre.end = Math.max(pre.end, newInterval.end);
                intervals.remove(newInterval);
                newInterval = pre;
                index--;
            }
        } 
        // handle right
        
        Iterator<Interval> it = intervals.listIterator(index + 1);
        while (it.hasNext()) {
            Interval itv = it.next();
            if (itv.start <= newInterval.end) {
                newInterval.end = Math.max(itv.end, newInterval.end);
                it.remove();
            } else {
                break;
            }
        }
        
        return intervals;
    }
    
    int findInsertPos(List<Interval> intervals, int target, int start, int end) {
        if (start > end) return start;
        int mid = (start + end) / 2;
        int itvStart = intervals.get(mid).start;
        if (itvStart == target) return mid;
        else if (itvStart > target) return findInsertPos(intervals, target, start, mid - 1);
        else return findInsertPos(intervals, target, mid + 1, end);
    }
}


/* 
@Q: Clone Graph  
@Method: 
@Complexity: Time O(n)
@note: 
*/
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return node;
        Queue<UndirectedGraphNode> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer,UndirectedGraphNode> map = new HashMap<>();
        queue.add(node);
        
        while (!queue.isEmpty()) {
            UndirectedGraphNode n = queue.poll();
            if (visited.contains(n.label)) continue;
            UndirectedGraphNode newNode = null;
            if (map.containsKey(n.label)) {
                newNode = map.get(n.label);
            } else {
                newNode = new UndirectedGraphNode(n.label);
                map.put(n.label, newNode);
            }
            
            for (UndirectedGraphNode child : n.neighbors) {
                if (map.containsKey(child.label)) {
                    newNode.neighbors.add(map.get(child.label));
                } else {
                    UndirectedGraphNode newChild = new UndirectedGraphNode(child.label);
                    newNode.neighbors.add(newChild);
                    map.put(child.label, newChild);
                }
                queue.add(child);
            }
            
            visited.add(newNode.label);
        }
        
        return map.get(node.label);
    }
}


/* 
@Q: Set Matrix Zeroes
@Method: 
@Complexity: Time O(n^2)
@note: 
*/
public class Solution {
    public void setZeroes(int[][] matrix) {
       if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return;
       
       boolean firstRow = false, firstCol = false;
       int n = matrix.length;
       int m = matrix[0].length;
       int max = Math.max(n, m);
       
       for (int i = 0; i < max; i++) {
           if (i < m && matrix[0][i] == 0) {
               firstRow = true;
           }
           if (i < n && matrix[i][0] == 0) {
               firstCol = true;
           }
       }
       
       for (int i = 1; i < n; i++) {
           for (int j = 1; j < m; j++) {
               if (matrix[i][j] == 0) {
                   matrix[i][0] = 0;
                   matrix[0][j] = 0;
               }
           }
       }
       
       for (int i = 1; i < max; i++) {
           if (i < m && matrix[0][i] == 0) {
               paintCol(matrix, i);
           }
           if (i < n && matrix[i][0] == 0) {
               paintRow(matrix, i);
           }
       }
       
       if (firstRow) paintRow(matrix, 0);
       if (firstCol) paintCol(matrix, 0);
    }
    
    void paintRow(int[][] matrix, int row) {
       int m = matrix[0].length;
       for (int i = 0; i < m; i++) {
           matrix[row][i] = 0;
       }
    }
    
    void paintCol(int[][] matrix, int col) {
        int n = matrix.length;
       for (int i = 0; i < n; i++) {
           matrix[i][col] = 0;
       }
    }
}

/* 
@Q: Jump Game 
@Method: 
@Complexity: Time O(n)
@note: 
*/
public class Solution {
    public boolean canJump(int[] A) {
        int max = 0; 
        int step = 0;
        for (; step < A.length && step <= max; step++) {
            max = Math.max(max, step + A[step]);
        }
        return step == A.length;
    }
}


/* 
@Q: Word Break II 
@Method: 
@Complexity: Time O(n)
@note: 
*/
public class Solution {
    Map<String,List<String>> map = new HashMap<>();
    public List<String> wordBreak(String s, Set<String> dict) {
        if (map.containsKey(s)) return map.get(s);
       List<String> lst = new LinkedList<>();
       if (s.isEmpty()) {
           lst.add("");
           return lst;
       }
       
       for (int i = 0; i <= s.length(); i++) {
           String str = s.substring(0, i);
           if (dict.contains(str)) {
               List<String> rest = wordBreak(s.substring(i), dict);
               for (String restStr : rest) {
                   String right = restStr.length() > 0 ? " " + restStr : "";
                   lst.add(str + right);
               }
           }
       }
       
       map.put(s, lst);
       return lst;
    }
}

/* 
@Q: Path Sum II
@Method: 
@Complexity: Time O(n) ???
@note: 
*/
public class Solution {
       public List<List<Integer>> pathSum(TreeNode root, int sum) {
            List<List<Integer>> lst = new LinkedList<>();
            if (root == null) return lst;
            else if (root.left == null && root.right == null && root.val == sum) {
                List<Integer> l = new LinkedList<>();
                l.add(root.val);
                lst.add(l);
                return lst;
            }
            
            List<List<Integer>> rest = pathSum(root.left, sum - root.val);
            for (List<Integer> l : rest) {
                List<Integer> newList = new LinkedList<>(l);
                newList.add(0, root.val);
                lst.add(newList);
            }
            rest = pathSum(root.right, sum - root.val);
            for (List<Integer> l : rest) {
                List<Integer> newList = new LinkedList<>(l);
                newList.add(0, root.val);
                lst.add(newList);
            }
            
            return lst;
    }
}

/* 
@Q: Same Tree 
@Method: 
@Complexity: Time O(n) 
@note: 
*/
public class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
       if (p == null && q == null) return true;
       else if (p == null || q == null) return false;
       if (p.val == q.val)
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
       else return false;
    }
}

/* 
@Q: Swap Nodes in Pairs
@Method: 
@Complexity: Time O(n) 
@note: 
*/
public class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        ListNode dummy = dummyHead;
        ListNode p = head;
        while (p != null && p.next != null) {
            ListNode right = p.next.next;
            dummy.next = p.next;
            dummy = dummy.next;
            dummy.next = p;
            dummy = dummy.next;
            dummy.next = null;
            p = right;
        }
        if (p != null) {
            dummy.next = p;
        }
        
        return dummyHead.next;
    }
}

/* 
@Q: Multiply Strings 
@Method: 
@Complexity: Time O(n^2) 
@note: 
*/
public class Solution {
    public String multiply(String num1, String num2) {
        int[] result = new int[num1.length()+num2.length()];
        num1 = new StringBuffer(num1).reverse().toString();
        num2 = new StringBuffer(num2).reverse().toString();
        
        for (int i = 0; i < num1.length(); i++) {
            int a = num1.charAt(i) - '0';
            int carry = 0;
            for (int j = 0; j < num2.length(); j++) {
                int b = num2.charAt(j) - '0';
                result[i+j] += a * b + carry;
                carry = result[i+j] / 10;
                result[i+j] %= 10;
            }
            result[i+num2.length()] += carry;
        }
         StringBuffer sb=new StringBuffer();
        int i=result.length-1;
        while(i>0 && result[i]==0){
            i--;
        }
        
        while(i >= 0){
            sb.append(result[i]);
            i--;
        }
        
        return sb.toString();
    }
}

/* 
@Q: Maximal Rectangle 
@Method: 
@Complexity: Time O(n^2) 
@note: 
*/
public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int j = 0; j < n; j++) {
            for (int i = 1; i < m; i++) {
                if (matrix[i][j] == '1') {
                    matrix[i][j] = (char)(matrix[i-1][j] + 1);
                }
            }
        }
        int globalMax = 0;
        for (int i = 0; i < m; i++) {
            int max = computeMax(matrix, i);
            globalMax = Math.max(max, globalMax);
        }

        return globalMax;
    }

    int computeMax(char[][] matrix, int row) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int j = 0; j < n; j++) {
            if (!stack.isEmpty() && (matrix[row][stack.peek()] - '0') > (matrix[row][j] - '0')) {
                int index = stack.pop();
                int length = !stack.isEmpty() ? j - stack.peek() - 1 : j;
                maxArea = Math.max(maxArea, (matrix[row][index] - '0') * length);
                j--;
            } else {
                stack.push(j);
            }
        }

        while (!stack.isEmpty()) {
            int index = stack.pop();
            int length = !stack.isEmpty() ? n - stack.peek() - 1 : n;
            maxArea = Math.max(maxArea, (matrix[row][index] - '0') * length);
        }
        return maxArea;
    }
}


/* 
@Q: Best Time to Buy and Sell Stock II 
@Method: 
@Complexity: Time O(n) 
@note: 
*/
public class Solution {
    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            max += Math.max(prices[i] - prices[i-1], 0);
        }
        return max;
    }
}


/* 
@Q: Permutation Sequence
@Method: 
@Complexity: Time O(n) 
@note: 
*/
public class Solution {
    public String getPermutation(int n, int k) {
        int[] num = new int[n];
        int count = 1;
        for (int i = 0; i < n; i++) {
            num[i] = i + 1;
            count *= (i + 1);
        }
        
        k--;
        StringBuffer sb = new StringBuffer();
        for (int i = n - 1; i >= 0; i--) {
            count /= (i + 1);
            int index = k / count;
            k %= count;
            sb.append(num[index]);
            deletePos(num, index);
            
        }
        return sb.toString();
    }
    
    void deletePos(int[] num, int pos) {
        for (int i = pos; i < num.length - 1; i++) {
            num[i] = num[i+1];
        }
    }
}

/* 
@Q: Search in Rotated Sorted Array II 
@Method: 
@Complexity: Time O(n) worst time ???
@note: 
*/
public class Solution {
    public boolean search(int[] A, int target) {
        if (A == null || A.length == 0) return false;
        return search(A, target, 0, A.length - 1);
    }
    
    public boolean search(int[] A, int target, int low, int high) {
        if (low > high) return false;
        
        int mid = (low + high) / 2;
        if (A[mid] == target) return true;
        else if (A[low] < A[mid]) {
            if (target >= A[low] && target < A[mid]) return search(A, target, low, mid - 1);
            else return search(A, target, mid + 1, high);
        } else if (A[low] > A[mid]) {
            if (target > A[mid] && target <= A[high]) return search(A, target, mid + 1, high);
            else return search(A, target, low, mid - 1);
        } else {
            if (A[low] == A[high]) {
                return search(A, target, low, mid - 1) || search(A, target, mid + 1, high);
            } else {
                return search(A, target, mid + 1, high);
            }
        }
    }
}

/* 
@Q: Integer to Roman
@Method: 
@Complexity: Time O(n) worst time ???
@note: 
*/
public class Solution {
  
   public String intToRoman(int num)
{
    char[] symbol = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        int scale = 1000;
        StringBuilder roman = new StringBuilder("");
        for(int i = 6; i >= 0; i -= 2)
        {
            int digit = num/scale;
            if(digit != 0)
            {
                if(digit <= 3)
                {
                    while (digit > 0) {
                        roman.append(symbol[i]);
                        digit--;
                    }
                }
                else if(digit == 4)
                {
                    roman.append(symbol[i]);
                    roman.append(symbol[i + 1]);
                }
                else if(digit == 5)
                {
                    roman.append(symbol[i + 1]);
                }
                else if(digit<=8)
                {
                    roman.append(symbol[i + 1]);
                    digit -= 5;
                    while (digit > 0) {
                        roman.append(symbol[i]);
                        digit--;
                    }
                }
                else if(digit == 9)
                {
                    roman.append(symbol[i]);
                    roman.append(symbol[i + 2]);
                }
            }
            num = num % scale;
            scale /= 10;
        }

        return roman.toString();
}

}

/* 
@Q: Palindrome Number 
@Method: 
@Complexity: Time O(n)
@note: 
*/
public class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int high = 1;
        while (x / high >= 10) {
            high *= 10;
        }


        while (high >= 10) {
            if (x / high != x % 10) return false;
            x %= high;
            x /= 10;
            high /= 100;
        }

        return true;
    }
}



/* 
@Q: Find Minimum in Rotated Sorted Array 
@Method: 
@Complexity: Time O(log n)
@note: 
*/
public class Solution {
    public int findMin(int[] num) {
        if (num == null || num.length == 0) {
            return 0;
        }
        return findMin(num, 0, num.length - 1);
    }
    
    public int findMin(int[] num, int low, int high) {
        if (low == high) {
            return num[low];
        }
        
        int mid = (low + high) / 2;
        if (num[mid] < num[high]) { // pivot is at left
            return findMin(num, low, mid);
        } else return findMin(num, mid + 1, high); //pivot is at right
    }
}


/* 
@Q: Subsets II 
@Method: 
@Complexity: Time O(2^n)
@note: 
*/
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] num) {
        Arrays.sort(num);
        return subsets(num, 0);
    }

    List<List<Integer>> subsets(int[] S, int index) {
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        if (S.length == index) {
            set.add(Collections.<Integer>emptyList());
            List<List<Integer>> lst = new LinkedList<List<Integer>>(set);
            return lst;
        }

        List<List<Integer>> rest = subsets(S, index + 1);
        for (List<Integer> list : rest) {
            List<Integer> nList = new LinkedList<Integer>(list);
            nList.add(0, S[index]);
            set.add(nList);
        }
        set.addAll(rest);

        List<List<Integer>> lst = new LinkedList<List<Integer>>(set);
        return lst;
    }
}

/* 
@Q: 3Sum Closest  
@Method: 
@Complexity: Time O(2^n)
@note: 
*/
public class Solution {
    public int threeSumClosest(int[] num, int target) {
        Arrays.sort(num);
        int closest = Integer.MAX_VALUE;
        int val = 0;
        for (int i = 0; i < num.length - 2; i++) {
            int start = i + 1;
            int end = num.length - 1;

            while (start < end) {
                int sum = num[i] + num[start] + num[end];
                if (Math.abs(target - sum) < closest) {
                    closest = Math.abs(target - sum);
                    val = sum;
                }
                if (target == sum) return sum;
                else if (target > sum) {
                    start++;
                } else {
                    end--;
                }
            }

        }
        return val;
    }
}