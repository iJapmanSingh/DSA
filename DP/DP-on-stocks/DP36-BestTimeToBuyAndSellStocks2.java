//recursive solution ----------------------------------------------------------------------------------

// class Solution {
//     public int maxProfit(int[] prices) {
//         return recursion(0 , 1 , prices);
//     }
//     public int recursion(int index , int buy , int[] prices){
//         int n = prices.length ;
//         int profit = 0 ;
//         //base case 
//         if(index == n) return 0 ;

//         //DP states - 
//         if(buy == 1){
//             int Bought = - prices[index] + memoization(index + 1 , 0 , prices);     //take case
//             int notBought = 0 + memoization(index + 1 , 1 , prices);                //not take case
//             profit = Math.max(Bought , notBought);
//         }else{
//             int Selled = prices[index] + memoization(index + 1 , 1 , prices);       //take case
//             int notSelled = 0 + memoization( index + 1 , 0 , prices) ;              //not take case
//             profit = Math.max(Selled , notSelled);
//         }
//         return profit ;
//     }
// }

//memoization solution ---------------------------------------------------------------------------------



// class Solution {
//     public int maxProfit(int[] prices) {
//         int n = prices.length ;
//         int[][] dp = new int[n][2] ;
//         for(int i = 0 ; i < n ; i++){
//             for(int j = 0 ; j < 2 ; j++){
//                 dp[i][j] = -1 ;
//             }
//         }
//         return memoization(0 , 1 , prices , dp);
//     }
//     public int memoization(int index , int buy , int[] prices , int[][] dp){
//         int n = prices.length ;
//         int profit = 0 ;
//         //base case 
//         if(index == n) return 0 ;

//         if(dp[index][buy] != -1) return dp[index][buy] ;

//         //DP states - 
//         if(buy == 1){
//             int Bought = - prices[index] + memoization(index + 1 , 0 , prices , dp);     //take case
//             int notBought = 0 + memoization(index + 1 , 1 , prices , dp);                //not take case
//             profit = Math.max(Bought , notBought);
//         }else{
//             int Selled = prices[index] + memoization(index + 1 , 1 , prices , dp);       //take case
//             int notSelled = 0 + memoization( index + 1 , 0 , prices , dp) ;              //not take case
//             profit = Math.max(Selled , notSelled);
//         }
//         return dp[index][buy] =  profit ;
//     }
// }



// class Solution {
//     public int maxProfit(int[] prices) {
//         int n = prices.length ;
//         int[][] dp = new int[n + 1][2] ;

//         //base case -
//         dp[n][0] =  0 ;
//         dp[n][1] =  0 ;

//         //loop 
//         int profit = 0 ;
//         for(int index = n-1 ; index >= 0 ; index--){
//             for(int buy = 0 ; buy <= 1 ; buy++){
//                 if(buy == 1){
//                     int Bought = - prices[index] + dp[index + 1][0] ;     //take case
//                     int notBought = 0 + dp[index + 1][1] ;                //not take case
//                     profit = Math.max(Bought , notBought);
//                 }else{
//                     int Selled = prices[index] + dp[index + 1][1] ;       //take case
//                     int notSelled = 0 + dp[index + 1][0] ;              //not take case
//                     profit = Math.max(Selled , notSelled);
//                 }
//                 dp[index][buy] =  profit ;
//             }
//         }
//         return dp[0][1] ;
//     }
// }







//space optimized 


class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int[] ahead = new int[2]; // dp[index + 1]
        int[] curr = new int[2];  // dp[index]

        for (int index = n - 1; index >= 0; index--) {
            for (int buy = 0; buy <= 1; buy++) {

                int profit = 0;

                if (buy == 1) {
                    int Bought = -prices[index] + ahead[0];
                    int notBought = ahead[1];

                    profit = Math.max(Bought, notBought);
                } else {
                    int Selled = prices[index] + ahead[1];
                    int notSelled = ahead[0];

                    profit = Math.max(Selled, notSelled);
                }

                curr[buy] = profit;
            }

            ahead = curr.clone();
        }

        return ahead[1];
    }
}