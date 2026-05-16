// ALGORITHM / THINKING
//
// 1. We need to find whether a subset exists whose sum == target.
//
// 2. At every index, we have only 2 choices:
//      a) Take current element
//      b) Do not take current element
//
// 3. Recursive Function:
//      f(i, target)
//      -> means:
//      "Can we form 'target' using elements from 0 to i ?"
//
// 4. Base Cases:
//
//      if(target == 0)
//          -> subset found
//          -> return true
//
//      if(i == 0)
//          -> only one element left
//          -> check whether arr[0] == target
//
// 5. Memoization:
//
//      if state already computed
//          return stored answer
//
//      dp[i][target] stores:
//          1  -> true
//          0  -> false
//         -1  -> not computed
//
// 6. Not Take:
//
//      ignore current element
//
//      notTake = f(i-1, target)
//
// 7. Take:
//
//      only possible if arr[i] <= target
//
//      take = f(i-1, target-arr[i])
//
// 8. Final Answer:
//
//      if any one is true
//
//      return take || notTake
//
// 9. Store answer in dp before returning.





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


class Solution {
    static Boolean isSubsetSum(int arr[], int sum) {
        // code here
        int n = arr.length ;
        boolean[] curr = new boolean[sum+1] ;
        boolean[] prev = new boolean[sum+1] ;

        prev[0] = true ;
        curr[0] = true ;
        if(arr[0] <= sum){
            prev[arr[0]] = true;
        }

        for(int i = 1 ; i < n ; i++){
            Arrays.fill(curr, false);
            curr[0] = true;
            for(int target = 1 ; target <= sum ; target++ ){
                boolean notTake = prev[target] ;
                boolean Take = false ;
                if(target >= arr[i]){
                    Take = prev[target - arr[i] ];
                }
                curr[target] = Take||notTake ;
            }
            prev = curr.clone();
        }
        return prev[sum] ;

    }
}