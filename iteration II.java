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
@note: Pay attention to different starting positions(from head or mid)
*/
public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) return head;
        
        ListNode left = null;
        if (m > 1) {
            left = head;
            int cnt = m - 2;
            while (cnt > 0) {
                left = left.next;
                cnt--;
            }
        }
        
        ListNode reverseHead = (left == null ? head : left.next);
        ListNode p = reverseHead;
        ListNode last = null;
        int cnt = n - m + 1;
        while (cnt > 0) {
            ListNode next = p.next;
            if (last != null) {
                p.next = last;
            } else {
                p.next = null;
            }
            last = p;
            p = next;
            cnt--;
        }
        
        reverseHead.next = p;
        if (left == null) {
            return last;
        } else {
            left.next = last;
            return head;
        }
        
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