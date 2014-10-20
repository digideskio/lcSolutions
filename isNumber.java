// 1. trim white spaces
// 2. get positions of e and .
// 3. valid the order of e and .
// 4. separate numbers: if . exists: digit must show at least one side of .; if e exists, digit must show after e
public class Solution {
public boolean isNumber(String s) {
       // remove left whitespaces
        int left = 0;
        while (left < s.length() - 1 && s.charAt(left) == ' ') {
            left++;
        }
        if (s.charAt(left)=='+' || s.charAt(left)=='-'){	
            left++;
        }
        // remove right whitespaces
        int right = s.length() - 1;
        while (right >= 0 && s.charAt(right) == ' ') {
            right--;
        }
        if (left > right) return false;
        s = s.substring(left, right + 1);

        int dot = -1;
        int e = -1;
        for (int i = 0; i < s.length(); i++){
            if (dot==-1 && s.charAt(i) == '.'){
                dot = i;
            } else if (e == -1 && s.charAt(i) == 'e'){
                e = i;
                if (i+1 < s.length() && (s.charAt(i + 1) == '-' || s.charAt(i + 1) == '+')){
                    i++;
                }
            }else{
                if (Character.isDigit(s.charAt(i))){
                    continue;
                } else {
                    return false;
                }
            }
        }

        //xxx.xxexx
        String startStr, midStr, lastStr;
        if(dot==-1 && e==-1){	//xxx
            startStr = s;	// xxx
            if(startStr.length()<1){
                return false;
            }
        }else if(dot!=-1 && e==-1){	//xxx.yyy
            startStr = s.substring(0, dot);	// xxx
            midStr = s.substring(dot+1);		// yyy
            if(startStr.length()<1 && midStr.length()<1){
                return false;
            }
        }else if(dot==-1 && e!=-1){	// xxxeyyy
            startStr = s.substring(0, e);	// xxx
            if(startStr.length()<1){
                return false;
            }
            if(e+1<s.length() && (s.charAt(e+1)=='-' || s.charAt(e+1)=='+')){	// xxxe-zz
                lastStr = s.substring(e+2);	// zz
            }else{
                lastStr = s.substring(e+1);
            }
            if(lastStr.length() < 1){
                return false;
            }
        }else{		//xxx.yyezz
            if(dot>e){		// 位置不对
                return false;
            }
            startStr = s.substring(0, dot);	// xxx
            midStr = s.substring(dot+1, e);	// yy
            if(startStr.length()<1 && midStr.length()<1){
                return false;
            }
            if(e+1<s.length() && (s.charAt(e+1)=='-' || s.charAt(e+1)=='+')){
                lastStr = s.substring(e+2);	// zz
            }else{
                lastStr = s.substring(e+1);
            }
            if(lastStr.length() < 1){
                return false;
            }
        }
        return true;
}
}