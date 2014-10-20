

public class Solution {
   public String multiply(String num1, String num2) {
        if (num1.length()==0 || num2.length()==0){
            return null;
        }
        
        int[] nums=new int[num1.length()+num2.length()];
        
        for (int i=0; i<num2.length(); i++){
            int carry=0;
            int a=num2.charAt(num2.length()-i-1)-'0';
            for (int j=0; j<num1.length(); j++){
                int b=num1.charAt(num1.length()-1-j)-'0';
                nums[i+j]+=a*b+carry;
                carry=nums[i+j]/10;
                nums[i+j]%=10;
                
            }
            nums[i+num1.length()]+=carry;
        }
        
        StringBuffer result=new StringBuffer();
        int i=nums.length-1;
        while(i>0&&nums[i]==0){
            i--;
        }
        
        while(i>=0){
            result.append(nums[i]);
            i--;
        }
        
        return result.toString();
        
    }

}
