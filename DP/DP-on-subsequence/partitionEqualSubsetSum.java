//solution is similar to the subset sum problem on GFG - https://www.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1
// and the target here is half of the total sum of array !!!! think of it 
// striver video solution - https://www.youtube.com/watch?v=7win3dcgo3k&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=16

class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length ;
        int sum = 0 ;
        for(int i = 0 ; i< n ; i++){
            sum += nums[i];
        }
        if(sum % 2 != 0) return false ;
        int[][] dp = new int[n][sum+1] ;
        for(int i = 0 ; i < dp.length ;i++){
            for(int j = 0 ; j < dp[0].length ; j++){
                dp[i][j] = -1 ;
            }
        }
        return memoization(n-1 , sum/2 , nums , dp);
    }
    static boolean memoization(int i , int target , int[] arr , int[][] dp){
        if(dp[i][target] != -1) {
            return dp[i][target] == 1 ;
        }
        if(target == 0) return true ;
        if(i == 0 ) return arr[0] == target ;

        boolean notTake = memoization(i - 1 , target , arr , dp);
        boolean Take = false ;
        if(target >= arr[i]){
            Take = memoization(i - 1 , target - arr[i] , arr , dp);
        }

        dp[i][target] = (notTake || Take) ? 1 : 0;

        return notTake || Take;

    }
}
// below commented code is the solution for problem on geeeks for geeks ( subset sum problem )-
// link for that problem - https://www.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1







// memoization solution ----------------------------------------------------------------------------

// class Solution {
//     static Boolean isSubsetSum(int arr[], int sum) {
//         // code here
//         int n = arr.length ;
//         int[][] dp = new int[n][sum+1] ;
//         for(int i = 0 ; i < dp.length ;i++){
//             for(int j = 0 ; j < dp[0].length ; j++){
//                 dp[i][j] = -1 ;
//             }
//         }
//         return memoization(n-1 , sum , arr , dp);
//     }
//     static Boolean memoization(int i , int target , int[] arr , int[][] dp){
//         if(dp[i][target] != -1) {
//             return dp[i][target] == 1 ;
//         }
//         if(target == 0) return true ;
//         if(i == 0 ) return arr[0] == target ;

//         boolean notTake = memoization(i - 1 , target , arr , dp);
//         boolean Take = false ;
//         if(target >= arr[i]){
//             Take = memoization(i - 1 , target - arr[i] , arr , dp);
//         }

//         dp[i][target] = (notTake || Take) ? 1 : 0;

//         return notTake || Take;
//     }
// }


// tabulation solution ----------------------------------------------------------------------------


// class Solution {
//     static Boolean isSubsetSum(int arr[], int sum) {
//         // code here
//         int n = arr.length ;
//         boolean[][] dp = new boolean[n][sum+1] ;
//         for(int i = 0 ; i < n ; i++) dp[i][0] = true ;
//         if(arr[0] <= sum){
//             dp[0][arr[0]] = true;
//         }
//         for(int i = 1 ; i < n ; i++){
//             for(int target = 1 ; target <= sum ; target++ ){
//                 boolean notTake = dp[i - 1][target] ;
//                 boolean Take = false ;
//                 if(target >= arr[i]){
//                     Take = dp[i - 1][target - arr[i] ];
//                 }
//                 dp[i][target] = Take||notTake ;
//             }
//         }
//         return dp[n-1][sum] ;

//     }

// }


// space optimized solution --------------------------------------------------------------------------


// class Solution {
//     static Boolean isSubsetSum(int arr[], int sum) {
//         // code here
//         int n = arr.length ;
//         boolean[] curr = new boolean[sum+1] ;
//         boolean[] prev = new boolean[sum+1] ;

//         prev[0] = true ;
//         curr[0] = true ;
//         if(arr[0] <= sum){
//             prev[arr[0]] = true;
//         }

//         for(int i = 1 ; i < n ; i++){
//             Arrays.fill(curr, false);
//             curr[0] = true;
//             for(int target = 1 ; target <= sum ; target++ ){
//                 boolean notTake = prev[target] ;
//                 boolean Take = false ;
//                 if(target >= arr[i]){
//                     Take = prev[target - arr[i] ];
//                 }
//                 curr[target] = Take||notTake ;
//             }
//             prev = curr.clone();
//         }
//         return prev[sum] ;

//     }
// }