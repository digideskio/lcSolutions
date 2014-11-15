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