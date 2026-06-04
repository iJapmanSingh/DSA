//recursion ------------------------------------------------------------------------------------------------

// class Solution {
//     public int lengthOfLIS(int[] nums) {
//         return memoization(0 , -1 , nums) ;
//     }
//     public int memoization(int index , int prev_index , int[] nums){
//         //base case 
//         int n = nums.length ;
//         if(index == n) return 0 ;

//         int notTake = 0 + memoization(index + 1 , prev_index , nums);
//         int Take = 0 ;
//         if(prev_index == -1 || nums[index] > nums[prev_index]){
//             Take = 1 + memoization(index + 1 , index , nums);
//         }
//         int length = Math.max(notTake , Take);
//         return length ;
//     }
// }

//memoization ---------------------------------------------------------------------------------------------------------

class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length ;
        int[][] dp = new int[n][n+1] ;
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n+1 ; j++){
                dp[i][j] = -1 ;
            }
        }
        return memoization(0 , -1 , nums , dp) ;
    }
    public int memoization(int index , int prev_index , int[] nums , int[][] dp){
        //base case 
        int n = nums.length ;
        if(index == n) return 0 ;
        if(dp[index][prev_index + 1] != -1) return dp[index][prev_index + 1] ;

        int notTake = 0 + memoization(index + 1 , prev_index , nums , dp);
        int Take = 0 ;
        if(prev_index == -1 || nums[index] > nums[prev_index]){
            Take = 1 + memoization(index + 1 , index , nums , dp);
        }
        int length = Math.max(notTake , Take);
        return dp[index][prev_index + 1] = length ;
    }
}