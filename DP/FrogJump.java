
// class Solution {
//     int minCost(int[] height) {

//         int n = height.length ;
//         int[] dp = new int[n+1];
//         for(int i = 0 ; i < dp.length ; i++) dp[i] = -1 ;

//         return DP(n-1 , height , dp);
//     }

//     public int DP(int i , int[] height , int[] dp){
//          //base case 
//         if(i == 0) return 0 ;
//         if(dp[i] != -1) return dp[i];

//         int left = DP(i-1 , height , dp) + Math.abs(height[i] - height[i-1]);
//         int right = Integer.MAX_VALUE ;
//         if(i > 1){
//             right = DP(i-2 , height , dp) + Math.abs(height[i] - height[i-2]);
//         }
//         return dp[i] =   Math.min(left , right);
//     }
// }



//Tabulation solution -------------------------------

// class Solution {
//     int minCost(int[] height) {
//         int n = height.length ;
//         int[] dp = new int[n];
//         for(int i = 0 ; i<dp.length; i++) dp[i] = -1 ;

//         dp[0] = 0 ;
//       for(int i = 1 ; i<n ; i++){
//             int fs = dp[i-1] + Math.abs(height[i] - height[i-1]);
//             int ss = Integer.MAX_VALUE;
//             if(i > 1){
//                 ss = dp[i-2] + Math.abs(height[i] - height[i-2]);
//             }
//             dp[i] = Math.min(fs , ss);
//         }
//         return dp[n-1];
//     }
// }




//Space optimized -----------------------------------------

class Solution {
    int minCost(int[] height) {
        int n = height.length ;

        int prev = 0 ;
        int prev2 = 0 ;
        for(int i = 1 ; i < n ; i++){
            int fs = prev + Math.abs(height[i] - height[i-1]);
            int ss = Integer.MAX_VALUE ;
            if(i > 1){
                ss = prev2 + Math.abs(height[i] - height[i-2]);
            }
            int curr = Math.min(fs ,ss);
            prev2 = prev ;
            prev = curr ;
        }
        return prev ;
    }
}
