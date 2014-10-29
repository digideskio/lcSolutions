// be carefull with carry!

public class Solution {
      public String addBinary(String a, String b) {
        int lenA = a.length();
        int lenB = b.length();

        int lenMin = Math.min(lenA, lenB);

        a = new StringBuffer(a).reverse().toString();
        b = new StringBuffer(b).reverse().toString();

        String returnStr = "";
        int carry = 0;
        for (int i = 0; i < lenMin; i++) {
            int digitA = a.charAt(i) - '0';
            int digitB = b.charAt(i) - '0';
            int sum = digitA + digitB + carry;
            if (sum > 1) {
                sum -= 2;
                carry = 1;
            } else {
                carry = 0;
            }
            returnStr += String.valueOf(sum);
        }

        String remaining = a;
        if (a.length() < b.length()) {
            remaining = b;
        }
        while (lenMin < remaining.length()) {
            int digit = remaining.charAt(lenMin) - '0';
            int sum = digit + carry;
            if (sum > 1) {
                sum -= 2;
                carry = 1;
            } else {
                carry = 0;
            }
            returnStr += String.valueOf(sum);
            lenMin++;
        }
        if (carry != 0) {
            returnStr += "1";
        }

        return new StringBuffer(returnStr).reverse().toString();
    }


    // anther short and neat solution
    public String addBinaryAlt(String a, String b) {
    String result = "";
    int m = a.length();
    int n = b.length();

    int tmp = 0;

    while (m+n >0){
        tmp += m>0? a.charAt(--m) - '0': 0;
        tmp += n>0? b.charAt(--n) - '0': 0;

        result = tmp%2 +result;
        tmp /= 2;
    }
    return (tmp == 0)? result: "1"+result;
}
}



