class Solution {
    public int minDifference(int arr[]) {
        // code here
        int n = arr.length ;
        int sum = 0 ;
        for(int i = 0 ; i < arr.length ; i++) {
            sum = sum + arr[i] ;
        }

        boolean[][] dp = new boolean[n][sum+1] ;
        for(int i = 0 ; i < n ; i++) dp[i][0] = true ;
        if(arr[0] <= sum){
            dp[0][arr[0]] = true;
        }
        for(int i = 1 ; i < n ; i++){
            for(int target = 1 ; target <= sum ; target++ ){
                boolean notTake = dp[i - 1][target] ;
                boolean Take = false ;
                if(target >= arr[i]){
                    Take = dp[i - 1][target - arr[i] ];
                }
                dp[i][target] = Take||notTake ;
            }
        }

        int miniDiff = (int)1e9 ;
        for(int i = 0 ; i <= sum / 2 ; i++){
            if(dp[n-1][i] == true){
                miniDiff = Math.min(miniDiff , Math.abs((sum - i ) - i )) ;
            }
        }

        return miniDiff ;
    }
}



// ALGORITHM: Minimum Sum Partition using 0/1 Knapsack (Subset Sum DP)
//
// INTUITION:
// If total sum = S, and one subset has sum = i,
// then other subset has sum = (S - i)
// Minimize |S - i - i| = |S - 2i|, for all reachable i <= S/2
//
// STEP 1: Compute total sum S of the array
//
// STEP 2: Build DP table dp[n][S+1]
//         dp[i][j] = true if sum j is achievable using first i elements
//
// STEP 3: Base case
//         dp[i][0] = true for all i  (empty subset always gives sum 0)
//         dp[0][arr[0]] = true        (first element alone forms a subset)
//
// STEP 4: Fill DP (standard 0/1 knapsack)
//         For each element i and each target sum:
//           notTake = dp[i-1][target]
//           Take    = dp[i-1][target - arr[i]]  (if target >= arr[i])
//           dp[i][target] = Take OR notTake
//
// STEP 5: Find minimum difference
//         Iterate i from 0 to S/2 (only need left half by symmetry)
//         If dp[n-1][i] == true → valid partition found
//         Candidate difference = |S - 2i|, track minimum
//
// TIME:  O(n * S)  |  SPACE: O(n * S)