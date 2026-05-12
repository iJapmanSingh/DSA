// ------------------------------------------------ALGORITHM------------------------------------------------
/*
Algorithm (Memoization / Top-Down DP)

1. Start recursion from top element (0,0).

2. At every cell (i,j), we have two choices:
   - Move down      -> (i+1, j)
   - Move diagonal  -> (i+1, j+1)

3. Recursively calculate minimum path sum for both paths
   and add current cell value.

4. Base Case:
   - If we reach last row, return that cell value.

5. Use DP array to store already computed states:
   - dp[i][j] stores minimum path sum starting from (i,j).
   - If already computed, return stored value directly.

6. Store and return:
   dp[i][j] = min(down, diagonal)

Time Complexity  : O(n²)
Reason: Each state (i,j) is computed only once.

Space Complexity : O(n²) + O(n)
O(n²) for DP array
O(n) recursion stack space
*/


//memoization solution---------------------------------------------------------------------------------------------

// class Solution {
//     public int minimumTotal(List<List<Integer>> triangle) {
//         int n = triangle.size();
//         int[][] dp = new int[n][n];
//         for (int i = 0; i < n; i++) {
//             Arrays.fill(dp[i], Integer.MAX_VALUE);
//         }
//         return recursion(0, 0, triangle, dp);

//     }

//     public int recursion(int i, int j, List<List<Integer>> triangle, int[][] dp) {
//         int n = triangle.size();

//         if (i == n - 1) return triangle.get(n - 1).get(j);

//         if (dp[i][j] != Integer.MAX_VALUE) return dp[i][j];

//         int down = triangle.get(i).get(j) + recursion(i + 1, j, triangle, dp);
//         int diagonal = triangle.get(i).get(j) + recursion(i + 1, j + 1, triangle, dp);

//         return dp[i][j] = Math.min(down, diagonal);
//     }
// }


//------------------------------------------------ALGORITHM------------------------------------------------
/*
Algorithm (Tabulation / Bottom-Up DP)

1. Create a DP array of size n x n.

2. Base Case:
   - Copy all elements of the last row of triangle
     into the last row of dp.
   - Because from last row, minimum path sum is the
     value itself.

3. Start filling DP table from second last row
   towards the top.

4. For every cell (i,j):
   - Find minimum path sum of:
       a) Down move      -> dp[i+1][j]
       b) Diagonal move  -> dp[i+1][j+1]

   - Add current triangle value.

   dp[i][j] =
       triangle[i][j] + min(dp[i+1][j], dp[i+1][j+1])

5. Continue until reaching top cell (0,0).

6. Final answer will be stored in dp[0][0].

Time Complexity  : O(n²)
Reason: Every cell is processed once.

Space Complexity : O(n²)
Reason: DP table is used to store results.
*/


// tabulation solution --------------------------------------------------------------------------------------------

// class Solution {
//     public int minimumTotal(List<List<Integer>> triangle) {
//         int n = triangle.size();
//         int[][] dp = new int[n][n];
//         for (int i = 0; i < n; i++) {
//             Arrays.fill(dp[i], Integer.MAX_VALUE);
//         }

//         for(int j = 0 ; j < n ; j++) dp[n-1][j] = triangle.get(n-1).get(j);  //base case
//         for(int i = n-2 ; i >= 0 ; i--){
//             for(int j = i ; j >= 0 ; j--){
//                 int d = triangle.get(i).get(j) + dp[i+1][j];
//                 int dg = triangle.get(i).get(j) + dp[i+1][j+1];

//                 dp[i][j] = Math.min( d , dg );
//             }
//         }
//         return dp[0][0];
//     }
// }



// --------------------------------------------ALGORITHM-----------------------------------------------------------
/*
Algorithm (Space Optimized DP)

1. Create two arrays:
   - front[] -> stores values of next row
   - curr[]  -> stores current row values

2. Base Case:
   - Copy last row of triangle into front[].
   - Because minimum path sum from last row is the
     value itself.

3. Traverse triangle from second last row
   towards the top.

4. For every cell (i,j):
   - Compute:
       down     = triangle[i][j] + front[j]
       diagonal = triangle[i][j] + front[j+1]

   - Store minimum in curr[j]

   curr[j] = min(down, diagonal)

5. After completing one row:
   - Copy curr into front
   - Because current row becomes next row
     for future calculations.

6. Continue until top row is processed.

7. Final answer will be stored in front[0].

Time Complexity  : O(n²)
Reason: Every cell is processed once.

Space Complexity : O(n)
Reason: Only two 1D arrays are used instead of
full DP table.
*/


// space optimized solution ---------------------------------------------------------------------------------

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        int[] front = new int[n];
        int[] curr = new int[n] ;

        for(int j = 0 ; j < n ; j++) front[j] = triangle.get(n-1).get(j);  //base case
        for(int i = n-2 ; i >= 0 ; i--){
            for(int j = i ; j >= 0 ; j--){
                int d = triangle.get(i).get(j) + front[j];
                int dg = triangle.get(i).get(j) + front[j+1];

                curr[j] = Math.min( d , dg );
            }
            front = curr.clone() ;
        }
        return front[0] ;
    }
}
