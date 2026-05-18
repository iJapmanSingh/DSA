// this problem is similar to problem perfect sum on GFG ( DP 17 Striver lecture )
// just we need to think what can be target , once we found our target , code is exactly same 
// target in this question is = (totalSum - diff) / 2 
// Striver Video Solution -> https://www.youtube.com/watch?v=zoilQD1kYSg&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=19

// i havent done this question using Tabulation and space optimization , so remember to solve in future !!

class Solution {
    public int countPartitions(int[] arr, int diff) {
        // code here
        int totalSum = 0 ;
        int n = arr.length ;
        for(int i = 0 ; i < n ; i++) totalSum += arr[i] ;
        if(totalSum - diff < 0 || (totalSum - diff) % 2 != 0) return 0;
        return perfectSum(arr , (totalSum - diff) / 2) ;

    }
    public int perfectSum(int[] nums, int target) {
        // code here

        int n = nums.length ;
        int[][] dp = new int[n][target+1] ;
        for(int i = 0 ; i < dp.length ;i++){
            for(int j = 0 ; j < dp[0].length ; j++){
                dp[i][j] = -1 ;
            }
        }
        return memoization(n-1 , target , nums , dp);
    }
    static int memoization(int i , int target , int[] arr , int[][] dp){
        if(dp[i][target] != -1) {
            return dp[i][target] ;
        }

        if(i == 0){
            if(target == 0 && arr[0] == 0) return 2;
            if(target == 0 || arr[0] == target) return 1;
            return 0;
        }

        int notTake = memoization(i - 1 , target , arr , dp);
        int Take = 0 ;
        if(target >= arr[i]){
            Take = memoization(i - 1 , target - arr[i] , arr , dp);
        }

        return dp[i][target] = notTake + Take ;


    }
}