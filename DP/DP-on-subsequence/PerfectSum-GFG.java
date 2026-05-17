//this question is exactly similar to subarray sum problem on GFG , just slight adjustments 


class Solution {
    // Function to calculate the number of subsets with a given sum
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


//IMPORTANT NOTE ----> Below code is same code of subarray sum problem on GFG 



// memoization solution ----------------------------------------------------------------------------

// class Solution {
//     static Boolean isSubsetSum(int arr[], int sum) {
//         // code here
// int n = arr.length ;
// int[][] dp = new int[n][sum+1] ;
// for(int i = 0 ; i < dp.length ;i++){
//     for(int j = 0 ; j < dp[0].length ; j++){
//         dp[i][j] = -1 ;
//     }
// }
// return memoization(n-1 , sum , arr , dp);
//     }
// static Boolean memoization(int i , int target , int[] arr , int[][] dp){
//     if(dp[i][target] != -1) {
//         return dp[i][target] == 1 ;
//     }
//     if(target == 0) return true ;
//     if(i == 0 ) return arr[0] == target ;

//     boolean notTake = memoization(i - 1 , target , arr , dp);
//     boolean Take = false ;
//     if(target >= arr[i]){
//         Take = memoization(i - 1 , target - arr[i] , arr , dp);
//     }

//     dp[i][target] = (notTake || Take) ? 1 : 0;

//     return notTake || Take;
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