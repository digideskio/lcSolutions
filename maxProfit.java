public class Solution {
    public int maxProfit(int[] prices) {
         int profit = 0;
         if (prices.length == 0) {
             return profit;
         }
         
         int min = prices[0];
         
         for (int i = 0; i < prices.length; i++) {
             if (min > prices[i]) {
                 min = prices[i];
             } else {
                 if (profit < prices[i] - min) {
                     profit = prices[i] - min;
                 }
             }
         }
         
         return profit;
    }
}