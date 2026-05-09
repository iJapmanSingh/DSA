//memoization solution ----------------------------------------------------

// class Solution {
//     public int minPathSum(int[][] grid) {
//         int n = grid.length ;
//         int m = grid[0].length ;
//         int[][] dp = new int[n][m] ;
//         for(int i = 0 ; i< n ; i++){
//             for(int j = 0 ; j<m ; j++) {
//                 dp[i][j] = -1 ;
//             }
//         }
//         return path(n-1 , m-1 , dp , grid);
//     }
//     public int path(int i , int j , int[][] dp , int[][] grid){
//         //base case 
//         if(i == 0 && j == 0) return grid[i][j] ;
//         if(i < 0 || j < 0) return (int)1e9 ;
//         if(dp[i][j] != -1 ) return dp[i][j] ;
//         int up = grid[i][j] + path(i - 1 , j , dp , grid);
//         int left = grid[i][j] + path(i , j - 1 , dp , grid);

//         return dp[i][j] = Math.min(up , left) ;
//     }
// }


//tabulation solution --------------------------------------------------------------

// class Solution {
//     public int minPathSum(int[][] grid) {
//         int n = grid.length ;
//         int m = grid[0].length ;
//         int[][] dp = new int[n][m] ;
//         for(int i = 0 ; i< n ; i++){
//             for(int j = 0 ; j<m ; j++) {
//                 if(i == 0 && j == 0) dp[i][j] = grid[i][j] ;
//                 else{
//                     int up = (int)1e9 ;
//                     int left = (int)1e9 ;
//                     if(i > 0) up = grid[i][j] + dp[i-1][j];
//                     if(j > 0) left = grid[i][j] + dp[i][j-1];
//                     dp[i][j] = Math.min(up , left);
//                 }
//             }
//         }
//         return dp[n-1][m-1];
//     }
// }




// space optimization solution --------------------------------------------------------




/*
Algorithm (Space Optimized DP)

1. Create a previous row array `prev` of size m.
   -> It stores answers of the previous row.

2. Traverse the grid row by row.

3. For every row:
   -> Create a current row array `curr`.

4. For each cell (i, j):

   Base Case:
   -> If (i == 0 && j == 0),
      store grid[0][0].

   Transition:
   -> Take minimum path from:
        a) Up    -> prev[j]
        b) Left  -> curr[j-1]

   -> Add current grid value to the minimum path.

5. Store result in curr[j].

6. After completing one row:
   -> Move curr into prev.

7. Final answer will be stored in prev[m-1].

Time Complexity  : O(N * M)
Space Complexity : O(M)
*/





class Solution {
    public int minPathSum(int[][] grid) {
        int n = grid.length ;
        int m = grid[0].length ;
        int[] prev = new int[m];
        for(int i = 0 ; i< n ; i++){
            int[] curr = new int[m];
            for(int j = 0 ; j<m ; j++) {
                if(i == 0 && j == 0) curr[j] = grid[i][j] ;
                else{
                    int up = (int)1e9 ;
                    int left = (int)1e9 ;
                    if(i > 0) up = grid[i][j] + prev[j];
                    if(j > 0) left = grid[i][j] + curr[j-1];
                    curr[j] = Math.min(up , left);
                }
            }
            prev = curr ;
        }
        return prev[m-1];
    }
}