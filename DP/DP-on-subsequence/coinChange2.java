//exactly same solution of coin change 1 , slight differences

class Solution {
    public int change(int amount, int[] coins) {

        return coinChange(coins , amount);
    }


    public int coinChange(int[] coins, int amount) {
        int n = coins.length ;
        int[][] dp = new int[n][amount+1] ;
        for(int i =0 ; i < n ; i++){
            for(int j = 0 ; j <= amount ; j++){
                dp[i][j] = -1 ;
            }
        }
        int ans = memoization(n-1 , amount , coins , dp);
        return ans;
    }
    public static int memoization(int index , int target , int[] coins , int[][] dp){
        //base case 
        if(index == 0 ){
            if(target % coins[0] == 0) return 1 ;
            return 0 ;
        }
        if(dp[index][target] != -1) return dp[index][target] ;
        int notTake =  memoization(index - 1 , target , coins , dp ) ;
        int take = 0 ;
        if(target >= coins[index]) {
            take =  memoization(index , target - coins[index] , coins , dp) ;
        }
        return dp[index][target] =  notTake + take  ;
    }
}

//this below code is of coin change 1 
// coin change 2 has exact same solution of coin change 1 , 
// just slight difference , return notTake+take , instead of their minimum

//tabulation solution -------------------------------------------------------------------------------------------


// class Solution {
//     public int coinChange(int[] coins, int amount) {
//         int n = coins.length ;
//         int target = amount ;
//         int[][] dp = new int[n][amount+1] ; 

//         //base case 
//         for(int i = 0 ; i <= target ; i++){
//             if(i % coins[0] == 0) dp[0][i] = i/coins[0] ;
//             else dp[0][i] = (int)1e9 ;
//         }

//         for(int i = 1 ; i < n ; i++){
//             for(int j = 0 ; j <= target ; j++){
//                 int notTake = 0 + dp[i - 1][j] ;
//                 int take = (int)1e9 ;
//                 if(j >= coins[i]) {
//                     take = 1 + dp[i][j - coins[i]] ; 
//                 }

//                 dp[i][j] =  Math.min(notTake , take) ;
//             }
//         }
//         int ans = dp[n - 1][amount];

//         if(ans >= (int)1e9) return -1;

//         return ans;
//     }
// }




//space - optimized ---------------------------------------------------------------------------------------------------

// class Solution {
//     public int coinChange(int[] coins, int amount) {
//         int n = coins.length ;
//         int target = amount ;
//         int[] prev = new int[target + 1];

//         //base case 
//         for(int i = 0 ; i <= target ; i++){
//             if(i % coins[0] == 0) prev[i] = i/coins[0] ;
//             else prev[i] = (int)1e9 ;
//         }
//         for(int i = 1 ; i < n ; i++){
//             int[] curr = new int[target + 1];
//             for(int j = 0 ; j <= target ; j++){
//                 int notTake = 0 + prev[j] ;
//                 int take = (int)1e9 ;
//                 if(j >= coins[i]) {
//                     take = 1 + curr[j - coins[i]] ; 
//                 }
//                 curr[j] =  Math.min(notTake , take) ;
//             }
//             prev = curr ;
//         }
//         int ans = prev[amount];

//         if(ans >= (int)1e9) return -1;

//         return ans;
//     }
// }
