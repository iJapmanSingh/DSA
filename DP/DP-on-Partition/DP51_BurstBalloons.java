// class Solution {
//     public int maxCoins(int[] nums) {
//         int n = nums.length ;
//         int[] arr = new int[n+2];
//         arr[0] = 1 ;
//         for(int i = 1 ; i <= n ; i++){
//             arr[i] = nums[i-1] ;
//         }
//         arr[n+1] = 1 ;

//         int[][] dp = new int[n+1][n+1];
//         for(int i = 0 ; i <= n ; i++){
//             for(int j = 0 ; j <= n ; j++){
//                 dp[i][j] = -1 ;
//             }
//         }

//         return f(1 , n , arr , dp);
//     }
//     public int f(int i , int j , int[] arr , int[][] dp){
//         if(i > j ) return 0 ;
//         if(dp[i][j] != -1) return dp[i][j] ;

//         int maxi = 0 ;


//         for(int ind = i ; ind <= j ; ind++){
//             int cost = arr[i-1]*arr[ind]*arr[j+1] + f(i , ind-1 , arr , dp) + f(ind+1 , j , arr , dp) ;
//             maxi = Math.max(maxi , cost);
//         } 
//         return dp[i][j] = maxi;
//     }

// }




//tabulation --------------------------------------------------------------------------------


class Solution {
    public int maxCoins(int[] nums) {

        int n = nums.length;
        int[] arr = new int[n + 2];

        arr[0] = 1;
        for (int i = 1; i <= n; i++) {
            arr[i] = nums[i - 1];
        }
        arr[n + 1] = 1;
        int[][] dp = new int[n + 2][n + 2];

        for (int i = n; i >= 1; i--) {
            for (int j = i; j <= n; j++) {
                int maxi = 0;
                for (int ind = i; ind <= j; ind++) {
                    int cost =  arr[i - 1] * arr[ind] * arr[j + 1] + dp[i][ind - 1] + dp[ind + 1][j];
                    maxi = Math.max(maxi, cost);
                }
                dp[i][j] = maxi;
            }
        }
        return dp[1][n];
    }
}