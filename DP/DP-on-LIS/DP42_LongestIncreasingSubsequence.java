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

// class Solution {
//     public int lengthOfLIS(int[] nums) {
//         int n = nums.length ;
//         int[][] dp = new int[n][n+1] ;
//         for(int i = 0 ; i < n ; i++){
//             for(int j = 0 ; j < n+1 ; j++){
//                 dp[i][j] = -1 ;
//             }
//         }
//         return memoization(0 , -1 , nums , dp) ;
//     }
//     public int memoization(int index , int prev_index , int[] nums , int[][] dp){
//         //base case 
//         int n = nums.length ;
//         if(index == n) return 0 ;
//         if(dp[index][prev_index + 1] != -1) return dp[index][prev_index + 1] ;

//         int notTake = 0 + memoization(index + 1 , prev_index , nums , dp);
//         int Take = 0 ;
//         if(prev_index == -1 || nums[index] > nums[prev_index]){
//             Take = 1 + memoization(index + 1 , index , nums , dp);
//         }
//         int length = Math.max(notTake , Take);
//         return dp[index][prev_index + 1] = length ;
//     }
// }



//space optimization -------

// class Solution{
//     public int lengthOfLIS(int[] nums){
//         int n = nums.length ;

//         int[] next = new int[n+1];

//         for(int index = n-1 ; index >= 0 ; index--){

//             int[] curr = new int[n+1];

//             for(int prev_index = index-1 ; prev_index >= -1 ; prev_index--){
//                 int notTake = 0 + next[prev_index+1] ;
//                 int Take = 0 ;
//                 if(prev_index == -1 || nums[index] > nums[prev_index]){
//                     Take = 1 + next[index+1] ;
//                 }
//                 int length = Math.max(notTake , Take);
//                 curr[prev_index + 1] = length ;
//             }
//             next = curr ; 
//         }
//         return next[-1+1] ;
//     }
// }


//more optimized tabulation -


// class Solution{
//     public int lengthOfLIS(int[] nums){
//         int n = nums.length ;
//         int maxi = 1 ;
//         int[] dp = new int[n];
//         for(int i = 0 ; i < n ; i++){
//             dp[i] = 1 ;
//         }
//         for(int i = 0 ; i < n ; i++){
//             for(int prev = 0 ; prev < i ; prev++){
//                 if(nums[prev] < nums[i]){
//                     dp[i] = Math.max(1 + dp[prev] , dp[i]);
//                 }
//             }
//             maxi = Math.max(maxi , dp[i]);
//         }
//         return maxi ;
//     }
// }


//Printing LIS 

import java.util.*;

class Solution {
    public List<Integer> printLIS(int[] nums) {

        int n = nums.length;
        int[] dp = new int[n];
        int[] hash = new int[n];
        Arrays.fill(dp, 1);

        int maxi = 1;
        int lastIndex = 0;

        for (int i = 0; i < n; i++) {
            hash[i] = i;
            for (int prev = 0; prev < i; prev++) {
                if (nums[prev] < nums[i] &&
                        dp[prev] + 1 > dp[i]) {

                    dp[i] = dp[prev] + 1;
                    hash[i] = prev;
                }
            }
            if (dp[i] > maxi) {
                maxi = dp[i];
                lastIndex = i;
            }
        }
        List<Integer> lis = new ArrayList<>();
        // start from last element of LIS
        lis.add(nums[lastIndex]);

        while (hash[lastIndex] != lastIndex) {
            lastIndex = hash[lastIndex];
            lis.add(nums[lastIndex]);
        }
        Collections.reverse(lis);
        return lis;
    }
}


