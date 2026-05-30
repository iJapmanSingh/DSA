// class Solution {
//     public int numDistinct(String s, String t) {
//         int n = s.length();
//         int m = t.length();
//         int[][] dp = new int[n][m] ;
//         for(int i = 0 ; i < n ; i++){
//             for(int j = 0 ; j < m ; j++){
//                 dp[i][j] = -1 ;
//             }
//         }
//         return memoization(n-1 , m-1 , s , t , dp);
//     }

//     public int memoization(int i , int j , String s , String t , int[][] dp){
//         //base cases 
//         if(j < 0) return 1 ;
//         if(i < 0) return 0 ;

//         if(dp[i][j] != -1) return dp[i][j] ;
//         if(s.charAt(i) == t.charAt(j)){
//             int case1 = memoization(i-1 , j-1 , s , t , dp);
//             int case2 = memoization(i-1 , j , s , t , dp);
//             return dp[i][j] =  case1 + case2 ;

//         }else{
//             return dp[i][j] = memoization(i-1 , j , s , t , dp);
//         }
//     }
// }

// class Solution {
//     public int numDistinct(String s, String t) {
//         int n = s.length();
//         int m = t.length();
//         int[][] dp = new int[n+1][m+1] ;

//         //base cases -
//         for(int i = 0 ; i <= n ; i++ ) dp[i][0] = 1 ;
//         for(int j = 1 ; j <= m ; j++ ) dp[0][j] = 0 ;

//         for(int i = 1 ; i <= n ; i++){
//             for(int j = 1 ; j<=m ; j++){
//                 if(s.charAt(i - 1) == t.charAt(j - 1)){
//                     int case1 = dp[i-1][j-1] ;
//                     int case2 = dp[i-1][j] ;
//                     dp[i][j] =  case1 + case2 ;

//                 }else{
//                     dp[i][j] = dp[i-1][j] ;
//                 }
//             }
//         }
//         return dp[n][m];
//     }
// }

class Solution {
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[] prev = new int[m + 1];
        int[] curr = new int[m + 1];

        //base cases -
        prev[0] = curr[0] = 1;

        for (int i = 1; i <= n; i++) {
            curr[0] = 1; // dp[i][0] = 1
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    curr[j] = prev[j - 1] + prev[j];
                } else {
                    curr[j] = prev[j];
                }
            }

            prev = curr.clone(); // <-- missing
        }
        return prev[m];
    }
}