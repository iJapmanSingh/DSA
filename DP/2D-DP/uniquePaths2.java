/*
--------------------------- UNIQUE PATHS 2 : ALGORITHM ---------------------------

PROBLEM:
We are given a grid where:
0 = free cell
1 = obstacle

We need to count total unique paths from top-left (0,0)
to bottom-right (m-1,n-1).

Allowed moves:
-> Right
-> Down


===============================================================================
                                RECURSION IDEA
===============================================================================

To reach any cell (i,j),
robot can come only from:

1. Top cell    -> (i-1,j)
2. Left cell   -> (i,j-1)

Therefore:

paths(i,j) = paths(i-1,j) + paths(i,j-1)


===============================================================================
                            IMPORTANT CONDITIONS
===============================================================================

1. If indices go out of bounds:
       return 0

2. If current cell is obstacle:
       return 0

3. If we reach starting cell (0,0):
       return 1

Obstacle check MUST come before base case,
otherwise blocked starting cell may incorrectly return 1.


===============================================================================
                            MEMOIZATION APPROACH
===============================================================================

WHY MEMOIZATION?

In recursion,
same states are calculated again and again.

Example:
path(3,3) may call path(2,3) multiple times.

To avoid repeated calculations,
store answers in dp array.


--------------------------- MEMOIZATION STEPS ---------------------------

1. Create dp[][] and initialize with -1.

2. Recursive function:
       path(i,j)

3. Base cases:
       -> Out of bounds => 0
       -> Obstacle      => 0
       -> (0,0)         => 1

4. If answer already present in dp:
       return dp[i][j]

5. Compute:
       up   = path(i-1,j)
       left = path(i,j-1)

6. Store:
       dp[i][j] = up + left

7. Return dp[i][j]


--------------------------- MEMOIZATION COMPLEXITY ---------------------------

Time Complexity  : O(m * n)
Space Complexity : O(m * n) + recursion stack


===============================================================================
                            TABULATION APPROACH
===============================================================================

Instead of recursion,
we build answers from smaller states iteratively.


--------------------------- TABULATION STEPS ---------------------------

1. Create dp[][].

2. Traverse every cell of grid.

3. If obstacle:
       dp[i][j] = 0

4. Else if starting cell:
       dp[i][j] = 1

5. Else:
       up   = dp[i-1][j] (if exists)
       left = dp[i][j-1] (if exists)

       dp[i][j] = up + left

6. Final answer:
       dp[m-1][n-1]


--------------------------- TABULATION COMPLEXITY ---------------------------

Time Complexity  : O(m * n)
Space Complexity : O(m * n)


===============================================================================
                        SPACE OPTIMIZATION APPROACH
===============================================================================

OBSERVATION:

Current cell depends only on:
1. Upper row
2. Left cell

So full matrix is not required.


--------------------------- SPACE OPTIMIZATION STEPS ---------------------------

1. Use:
       prev[] -> previous row
       curr[] -> current row

2. Traverse grid row by row.

3. Apply same tabulation logic.

4. After completing current row:
       prev = curr

5. Final answer:
       prev[n-1]


--------------------------- SPACE OPTIMIZATION COMPLEXITY ---------------------------

Time Complexity  : O(m * n)
Space Complexity : O(n)

===============================================================================

Same solution as unique paths 1 , but just an extra base case condition ,
which is if arr[i][j] == 1  ---->  this is dead cell , we cant go by this , so return 0 ;


*/


// class Solution {
//     public int uniquePathsWithObstacles(int[][] obstacleGrid) {
//         int m = obstacleGrid.length ;
//         int n = obstacleGrid[0].length ;
//         int[][] dp = new int[m][n] ;
//         for(int i = 0 ; i< m ; i++){
//             for(int j = 0 ; j < n ; j++){
//                 dp[i][j] = -1 ;
//             }
//         }
//         return path(m-1 , n-1 , dp , obstacleGrid);
//     }
//     public int path(int m , int n , int[][] dp , int[][] obstacleGrid){
//         if(m < 0 || n < 0 || obstacleGrid[m][n] == 1) return 0 ;
//         if(m == 0 && n == 0) return 1 ;
//         if(dp[m][n] != -1) return dp[m][n] ;

//         int up = path(m - 1 , n , dp , obstacleGrid);
//         int left = path( m , n-1 , dp , obstacleGrid);
//         return dp[m][n] = up + left ;
//     }
// }






//tabulation solution -------------------------------------------

// class Solution {
//     public int uniquePathsWithObstacles(int[][] obstacleGrid) {
//         int m = obstacleGrid.length ;
//         int n = obstacleGrid[0].length ;
//         int[][] dp = new int[m][n] ;

//         for(int i = 0 ; i< m ; i++){
//             for(int j = 0 ; j < n ; j++){
//                 if(obstacleGrid[i][j] == 1) dp[i][j] = 0;
//                 else if(i == 0 && j == 0) dp[i][j] = 1 ;
//                 else{
//                     int up = 0;
//                     int left = 0;
//                     if(i > 0 ) up = dp[i-1][j];
//                     if(j > 0 ) left = dp[i][j-1];
//                     dp[i][j] = up + left ;
//                 }
//             }
//         }
//        return dp[m-1][n-1];
//     }
// }




//space optimization solution ---------------------------------------------------

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length ;
        int n = obstacleGrid[0].length ;

        int[] prev = new int[n] ;
        for(int i = 0 ; i< m ; i++){
            int[] curr = new int[n];
            for(int j = 0 ; j< n ; j++){

                if(obstacleGrid[i][j] == 1) curr[j] = 0;
                else if(i == 0 && j == 0) curr[j] = 1 ;
                else{
                    int up = 0;
                    int left = 0;
                    if(i > 0 ) up = prev[j] ;
                    if(j > 0 ) left = curr[j-1] ;
                    curr[j] = up + left ;
                }
            }
            prev = curr ;
        }
        return prev[n-1];
    }
}