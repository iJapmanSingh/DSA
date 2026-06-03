// class Solution {
//     public int maxProfit(int k, int[] prices) {
//         return recursion(0 , 1 , prices , k);
//     }
//     public int recursion(int index , int buy , int[] prices , int cap){
//         int n = prices.length ;
//         int profit = 0 ;
//         //base case 
//         if(index == n) return 0 ;
//         if(cap == 0) return 0 ;

//         //DP states - 
//         if(buy == 1){
//             int Bought = - prices[index] + recursion(index + 1 , 0 , prices , cap);     //take case
//             int notBought = 0 + recursion(index + 1 , 1 , prices , cap);                //not take case
//             profit = Math.max(Bought , notBought);
//         }else{
//             int Selled = prices[index] + recursion(index + 1 , 1 , prices , cap - 1);       //take case
//             int notSelled = 0 + recursion( index + 1 , 0 , prices , cap) ;              //not take case
//             profit = Math.max(Selled , notSelled);
//         }
//         return profit ;
//     }
// }



// memoization solution ---------------------------------------------------------------------------------

// class Solution {
//     public int maxProfit(int k , int[] prices) {
//         int n = prices.length;
//         int[][][] dp = new int[n][2][k+1];
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < 2; j++) {
//                 for (int l = 0; l <= k; l++) {
//                     dp[i][j][l] = -1;
//                 }
//             }
//         } 
//         return memoization(0, 1, prices, dp, k);
//     }

//     public int memoization(int index, int buy, int[] prices, int[][][] dp, int cap) {
//         int n = prices.length;
//         int profit = 0;
//         //base case 
//         if(cap == 0) return 0 ;
//         if (index == n)
//             return 0;

//         if(dp[index][buy][cap] != -1) return dp[index][buy][cap];

//         //DP states - 
//         if (buy == 1) {
//             int Bought = -prices[index] + memoization(index + 1, 0, prices, dp, cap); //take case
//             int notBought = 0 + memoization(index + 1, 1, prices, dp, cap); //not take case
//             profit = Math.max(Bought, notBought);
//         } else {
//             int Selled = prices[index] + memoization(index + 1, 1, prices, dp, cap - 1); //take case
//             int notSelled = 0 + memoization(index + 1, 0, prices, dp, cap); //not take case
//             profit = Math.max(Selled, notSelled);
//         }
//         return dp[index][buy][cap] = profit;
//     }
// }




//tabulation solution -------------------------------------------------------------------------------------------

// class Solution {
//     public int maxProfit(int k , int[] prices) {
//         int n = prices.length;
//         int[][][] dp = new int[n+1][2][k+1];

//         //base cases - 
//         // 1st base case - if cap == 0 return 0 , buy and ind can be anything
//         for(int index = 0 ; index < n ; index++){
//             for(int buy = 0 ; buy <= 1 ; buy++){
//                 dp[index][buy][0] = 0 ;
//             }
//         }

//         //2nd base case - if index == n return 0 , cap and buy can be anything 
//         for(int buy = 0 ; buy <= 1 ; buy++){
//             for(int cap = 0 ; cap <= k ; cap++){
//                 dp[n][buy][cap] = 0;
//             }
//         }

//         //run state loops
//         for(int index = n-1 ; index >= 0 ; index--){
//             for(int buy = 0 ; buy <= 1 ; buy++){
//                 for(int cap = 1 ; cap <= k ; cap++){
//                      if (buy == 1) {
//                         int Bought = -prices[index] + dp[index + 1][0][cap]; //take case
//                         int notBought = 0 + dp[index + 1][1][cap]; //not take case
//                         dp[index][buy][cap] = Math.max(Bought, notBought);
//                     } else {
//                         int Selled = prices[index] + dp[index + 1][1][cap - 1]; //take case
//                         int notSelled = 0 + dp[index + 1][0][cap]; //not take case
//                         dp[index][buy][cap] = Math.max(Selled, notSelled);
//                     }
//                 }
//             }
//         }
//         return dp[0][1][k] ;
//     }
// }




//space optimized --------------------------------------------------------------

// Space Optimization Logic:
//
// dp[index][buy][cap] depends only on dp[index+1][buy][cap].
//
// Therefore we do not need the entire 3D DP table.
// We only keep:
//
// ahead = dp[index+1]
// curr  = dp[index]
//
// Compute curr using ahead.
// After finishing one index:
//
// ahead = curr
//
// Time  : O(n * 2 * 3)
// Space : O(2 * 3)


class Solution {
    public int maxProfit(int k , int[] prices) {

        int n = prices.length;
        int[][] ahead = new int[2][k+1];

        for(int index = n - 1; index >= 0; index--) {
            int[][] curr = new int[2][k+1];
            for(int buy = 0; buy <= 1; buy++) {
                for(int cap = 1; cap <= k; cap++) {
                    if(buy == 1) {

                        int bought =
                                -prices[index] + ahead[0][cap];

                        int notBought =
                                ahead[1][cap];

                        curr[buy][cap] =
                                Math.max(bought, notBought);

                    }
                    else {

                        int sold =
                                prices[index] + ahead[1][cap - 1];

                        int notSold =
                                ahead[0][cap];

                        curr[buy][cap] =
                                Math.max(sold, notSold);
                    }
                }
            }

            ahead = curr;
        }

        return ahead[1][k];
    }
}