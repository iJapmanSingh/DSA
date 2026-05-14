/*
---------------------------------------------
MEMOIZATION APPROACH (TOP DOWN DP)
---------------------------------------------

STATE:
dp[i][j1][j2]

i   -> current row
j1  -> column of robot1
j2  -> column of robot2

dp[i][j1][j2] stores:
maximum cherries collectable from row i
when robot1 is at j1 and robot2 is at j2

---------------------------------------------
BASE CASE:
---------------------------------------------

If we reach last row:

1. If both robots are on same cell:
   take cherries only once

2. Otherwise:
   take cherries from both cells

---------------------------------------------
TRANSITION:
---------------------------------------------

From one cell, each robot has 3 moves:

- left diagonal   (col - 1)
- down            (col)
- right diagonal  (col + 1)

So total combinations:
3 * 3 = 9 moves

For every move:
1. collect current cherries
2. move both robots to next row
3. take maximum among all possibilities

---------------------------------------------
INVALID CASE:
---------------------------------------------

If robot goes outside grid:
return very small value (-1e9)

This ensures invalid paths are never chosen.

---------------------------------------------
ANSWER:
---------------------------------------------

Start from:

robot1 -> (0, 0)
robot2 -> (0, m-1)

return f(0, 0, m-1)

---------------------------------------------
TIME COMPLEXITY:
---------------------------------------------

States:
n * m * m

Each state checks 9 moves

TC = O(n * m * m * 9)
   = O(n * m^2)

---------------------------------------------
SPACE COMPLEXITY:
---------------------------------------------

DP array:
O(n * m * m)

Recursion stack:
O(n)

Total:
O(n * m * m)
---------------------------------------------
*/



//memoization ------------------------------------------------------------------------------------------

// class Solution {
//     public int cherryPickup(int[][] grid) {
//         int n = grid.length ;
//         int m = grid[0].length ;
//         int[][][] dp = new int[n][m][m];
//         for(int i= 0 ; i < n ; i++){
//             for(int j = 0 ; j < m ; j++){
//                 for(int k = 0 ; k < m ; k++){
//                     dp[i][j][k] = -1 ;
//                 }
//             }
//         }

//         return memoization(0 , 0 , m - 1 ,grid , dp);
//     }
//     public int memoization(int i , int j1 , int j2 , int[][] grid , int[][][] dp){
//         int n = grid.length ;
//         int m = grid[0].length ;

//         if(i >= n || j1 < 0 || j1 >= m || j2 < 0 || j2 >= m ) return -(int)1e9 ;
//         if(i == n - 1 ){
//             if(j1 == j2) return grid[i][j1] ;
//             else{
//                 return grid[i][j1] + grid[i][j2] ;
//             }
//         }
//         if(dp[i][j1][j2] != -1) return dp[i][j1][j2] ;


//         int maxi = 0 ;
//         for(int dj1 = -1 ; dj1 <= 1 ; dj1++){
//             for(int dj2 = -1 ; dj2 <= 1 ; dj2++){
//                 if(j1 == j2){
//                     maxi = Math.max(maxi , (grid[i][j1] + memoization(i + 1 , j1 + dj1 , j2 + dj2 , grid , dp)));
//                 }else{
//                     maxi = Math.max(maxi , (grid[i][j1] + grid[i][j2] + memoization(i + 1 , j1 + dj1 , j2 + dj2 , grid , dp)));
//                 }
//             }
//         }
//         return dp[i][j1][j2] = maxi ;
//     }
// }


// ------------------------------------------------------------------------------------------------------------------------------------

/*
---------------------------------------------
TABULATION APPROACH (BOTTOM UP DP)
---------------------------------------------

STATE:
dp[i][j1][j2]

i   -> current row
j1  -> column of robot1
j2  -> column of robot2

dp[i][j1][j2] stores:
maximum cherries collectable starting
from row i with robot1 at j1 and
robot2 at j2

---------------------------------------------
BASE CASE:
---------------------------------------------

Fill the last row first.

If both robots are on same cell:
take cherries once

Else:
take cherries from both cells

---------------------------------------------
TRANSITION:
---------------------------------------------

Each robot has 3 possible moves:

- left diagonal
- down
- right diagonal

Total:
9 combinations

For every combination:
1. collect current cherries
2. add answer from next row
3. take maximum among all moves

---------------------------------------------
FILLING ORDER:
---------------------------------------------

Since current row depends on next row:

dp[i] depends on dp[i+1]

So fill from bottom to top.

Loop:
i = n-2 -> 0

---------------------------------------------
ANSWER:
---------------------------------------------

Final answer stored at:

dp[0][0][m-1]

because:
robot1 starts at column 0
robot2 starts at column m-1

---------------------------------------------
TIME COMPLEXITY:
---------------------------------------------

Loops:
n * m * m * 9

TC = O(n * m^2)

---------------------------------------------
SPACE COMPLEXITY:
---------------------------------------------

3D DP array:
O(n * m * m)

---------------------------------------------
OPTIMIZATION:
---------------------------------------------

Current row only depends on next row.

So we can optimize:
3D DP -> 2D DP

Space optimized complexity:
O(m * m)

---------------------------------------------
*/







//tabulation -----------------------------------------------------------------------------------

class Solution {
    public int cherryPickup(int[][] grid) {
        int n = grid.length ;
        int m = grid[0].length ;
        int[][][] dp = new int[n][m][m];
        for(int i= 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                for(int k = 0 ; k < m ; k++){
                    dp[i][j][k] = -1 ;
                }
            }
        }
        // base case
        for(int j1 = 0 ; j1 < m ; j1++) {
            for(int j2 = 0 ; j2 < m ; j2++) {

                if(j1 == j2)
                    dp[n-1][j1][j2] = grid[n-1][j1];
                else
                    dp[n-1][j1][j2] =
                            grid[n-1][j1] + grid[n-1][j2];
            }
        }

        for(int i = n-2 ; i >= 0 ; i--) {
            for(int j1 = 0 ; j1 < m ; j1++) {
                for(int j2 = 0 ; j2 < m ; j2++) {
                    int maxi = 0;
                    for(int d1 = -1 ; d1 <= 1 ; d1++) {
                        for(int d2 = -1 ; d2 <= 1 ; d2++) {
                            int nj1 = j1 + d1;
                            int nj2 = j2 + d2;

                            if(nj1 >= 0 && nj1 < m &&
                                    nj2 >= 0 && nj2 < m) {

                                int value;

                                if(j1 == j2)
                                    value = grid[i][j1];
                                else
                                    value = grid[i][j1]
                                            + grid[i][j2];

                                value += dp[i+1][nj1][nj2];

                                maxi = Math.max(maxi, value);
                            }
                        }
                    }

                    dp[i][j1][j2] = maxi;
                }
            }
        }

        return dp[0][0][m-1];
    }
}