//Recursion solution ----------------------------------------------

// class Solution {
//     public int climbStairs(int n) {
//         //base case 
//         if(n <= 1) return 1 ;
//         return climbStairs(n-1) + climbStairs(n-2);

//     }
// }

//Dynamic Programming Solution ( memoization ) -------------------------------------------

// class Solution {
//     public int climbStairs(int n) {
//         int[] DP = new int[n+1];
//         return solve(n , DP);
//     } 
//     public int solve(int n , int[] DP){
//         if(n <= 1) return 1 ;
//         if(DP[n] != 0) return DP[n];

//         DP[n] = solve(n-1 , DP) + solve(n-2 , DP);
//         return DP[n];
//     }
// }

//Tabulation Solution --------------------------------------------------------

// class Solution {
//     public int climbStairs(int n) {

//         int[] DP = new int[n+1];
//         DP[0] = 1 ; 
//         DP[1] = 1 ;
//         for(int i = 2 ; i<=n ; i++){
//             DP[i] = DP[i-1] + DP[i-2];
//         }
//         return DP[n];
//     }
// }



// Space Optimized Tabulation Solution ------------------------------------------------ 

class Solution {
    public int climbStairs(int n) {

        if(n <= 1) return 1 ;

        int prev2 = 1 ;
        int prev = 1 ;
        for(int i = 2 ; i<= n ; i++){
            int curr = prev + prev2 ;
            prev2 = prev ;
            prev = curr ;
        }
        return prev;
    }
}