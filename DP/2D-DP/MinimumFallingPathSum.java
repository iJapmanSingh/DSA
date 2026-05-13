//memoization solution 
// we have variable starting and variable ending 
// so we will call recursion for every possible ending point and take global minimum 

// class Solution {
//     public int minFallingPathSum(int[][] matrix) {
//         int n = matrix.length ;
//         int m = matrix[0].length ;
//         int[][] dp = new int[n][m];
//         for(int i = 0 ; i < n ; i++){
//             for(int j = 0 ; j < m ; j++){
//                 dp[i][j] = Integer.MAX_VALUE ;
//             }
//         }
//         int ans = Integer.MAX_VALUE ;
//         for(int j = 0 ; j < m ; j++){
//             int value = recursion(n-1 , j , matrix , dp);
//             ans = Math.min(ans , value);
//         }
//         return ans ;
//     }
//     public int recursion(int i , int j , int[][] matrix , int[][] dp){
//         int n = matrix.length ;
//         int m = matrix[0].length ;
//         if(j < 0 || j >= m) return (int)1e9 ;
//         if(i == 0) return matrix[0][j] ;
//         if(dp[i][j] != Integer.MAX_VALUE) return dp[i][j] ;

//         int s = matrix[i][j] + recursion(i-1 , j , matrix , dp);
//         int ld = matrix[i][j] + recursion(i-1 , j-1 , matrix , dp);
//         int rd = matrix[i][j] + recursion(i-1 , j+1 , matrix , dp);

//         return dp[i][j] = Math.min(s , Math.min(ld , rd));

//     }
// }



//tabulation solution -------------------------------------------------------------------------------------

// class Solution {
//     public int minFallingPathSum(int[][] matrix) {
//         int n = matrix.length ;
//         int m = matrix[0].length ;
//         int[][] dp = new int[n][m];
//         for(int i = 0 ; i < n ; i++){
//             for(int j = 0 ; j < m ; j++){
//                 dp[i][j] = Integer.MAX_VALUE ;
//             }
//         }

//         for(int j = 0 ; j < m ; j++) dp[0][j] = matrix[0][j];

//         for(int i = 1 ; i < n ; i++){
//             for(int j = 0 ; j < m ; j++){
//                 int ul = Integer.MAX_VALUE ;
//                 int ur = Integer.MAX_VALUE ;

//                 int up = matrix[i][j] + dp[i-1][j];
//                 if(j > 0) ul = matrix[i][j] + dp[i-1][j-1];
//                 if(j < m-1) ur = matrix[i][j] + dp[i-1][j+1];

//                 dp[i][j] = Math.min(up , Math.min(ul , ur));

//             }
//         }
//         int minimum = dp[n-1][0];
//         for(int j = 1 ; j < m ; j++){
//             minimum = Math.min(minimum , dp[n-1][j]);
//         }
//         return minimum ;
//     }
// }

//space otimization -------------------------------------------------------------------------------

class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length ;
        int m = matrix[0].length ;

        int[] prev = new int[m];

        for(int j = 0 ; j < m ; j++) prev[j] = matrix[0][j];

        for(int i = 1 ; i < n ; i++){
            int[] curr = new int[m];
            for(int j = 0 ; j < m ; j++){

                int ul = Integer.MAX_VALUE ;
                int ur = Integer.MAX_VALUE ;

                int up = matrix[i][j] + prev[j];
                if(j > 0) ul = matrix[i][j] + prev[j-1];
                if(j < m-1) ur = matrix[i][j] + prev[j+1];

                curr[j] = Math.min(up , Math.min(ul , ur));
            }
            prev = curr ;
        }
        int minimum = prev[0];
        for(int j = 1 ; j < m ; j++){
            minimum = Math.min(minimum , prev[j]);
        }
        return minimum ;
    }
}