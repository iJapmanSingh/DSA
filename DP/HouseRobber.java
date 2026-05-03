//memoization------------------

// class Solution {
//     public int rob(int[] nums) {
//         int n = nums.length - 1 ;
//         int[] dp = new int[n+1];
//         for(int i = 0 ; i < n+1 ; i++) dp[i] = -1 ;
//         return robber(n , nums , dp);
//     }
//     public int robber(int i  , int[] nums , int[] dp){
//         if(i == 0) return nums[i];
//         if(i < 0) return 0 ;
//         if(dp[i] != -1) return dp[i] ;
//         int take = nums[i] + robber(i - 2 , nums , dp); 
//         int notTake = robber(i - 1 , nums , dp);
//         return dp[i] = Math.max(take , notTake);
//     }
// }

//tabulation ----------------------------

// class Solution {
//     public int rob(int[] nums) {
//         int n = nums.length - 1 ;
//         int[] dp = new int[n+1];
//         for(int i = 0 ; i < n+1 ; i++) dp[i] = -1 ;
//         return robber(n , nums , dp);
//     }
//     public int robber(int i  , int[] nums , int[] dp){
//         dp[0] = nums[0];

//         for(int j = 1 ; j< nums.length ; j++){
//             int take = nums[j];
//             if(j > 1){
//                 take += dp[j-2];
//             }
//             int notTake = dp[j-1];
//             dp[j] = Math.max(take , notTake);
//         }
//         return dp[nums.length-1];
//     }
// }



//space optimized -----------------------------------------------

class Solution {
    public int rob(int[] nums) {
        int prev = nums[0];
        int prev2 = 0 ;
        for(int i = 1 ; i < nums.length ; i++){
            int take = nums[i] ;
            if(i > 1){
                take += prev2 ;
            }
            int notTake = prev ;
            int curr = Math.max(take , notTake);
            prev2 = prev ;
            prev = curr ;
        }
        return prev ;
    }
}