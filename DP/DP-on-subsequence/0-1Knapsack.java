// This is take/notTake recursion question , solved using DP , memoization , tabulation , space optimization and 1D space optimization 
// in case if you dont understand solution - make a DP tabulation table 
// pick pen and paper , make dp[n][W+1] table and start filling 
// you will know how to optimize , as we only need previous row , so we will take prev and curr 1 D arrays instead of 2D dp array 
// further , for 1D space optimization , we will realise that we can modify our prev row 
// instead of making new curr row and filling , we can start filling prev row from back , because we need front elements to make it 





//memoization

// class Solution {
//     public int knapsack(int W, int val[], int wt[]) {
//         // code here
//         int n = wt.length ;
//         int[][] dp = new int[n][W+1];
//         for(int i = 0 ; i < n ; i++){
//             for(int j = 0 ; j<= W ; j++){
//                 dp[i][j] = -1 ;
//             }
//         }
//         return memoization(n-1 , W , val , wt , dp);

//     }
//     public int memoization(int index , int W , int val[] , int wt[] , int[][] dp){
//         //base case 
//         if(index == 0){
//             //take 
//             if(W >= wt[index]) return val[index] ;
//             return 0 ;
//         }
//         if(dp[index][W] != -1 ) return dp[index][W] ;
//         int notTake = 0 + memoization(index - 1 , W , val , wt , dp) ;
//         int take = Integer.MIN_VALUE ;
//         if(W >= wt[index]){
//             take = val[index] + memoization(index - 1 , W-wt[index] , val , wt , dp);
//         }
//         return dp[index][W] = Math.max(notTake , take);
//     }
// }






// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// TABULATION APPROACH
// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

/*
dp[i][j] meaning:

Maximum value possible using items from 0...i
with bag capacity j.


STEP 1:
Create DP table:

Rows    -> items
Columns -> capacities

dp[n][W+1]


------------------------------------
STEP 2: BASE CASE
------------------------------------

When only first item exists (index 0):

If current capacity can hold wt[0],
then we can take that item.

So for every capacity >= wt[0]:

dp[0][j] = val[0]


Example:

wt[0] = 3
val[0] = 10

capacities:
0 1 2 3 4 5

dp row:
0 0 0 10 10 10


----------------------------------
STEP 3: TRANSITION
----------------------------------

For every item:

We have 2 choices:

1. NOT TAKE current item
2. TAKE current item


NOT TAKE:

Ignore current item.

notTake = dp[i-1][j]


TAKE:

Possible only if current capacity can hold item.

if(j >= wt[i])

Remaining capacity becomes:

j - wt[i]

So:

take = val[i] + dp[i-1][j - wt[i]]


FINAL ANSWER:

dp[i][j] = max(take, notTake)


--------------------------------
STEP 4: RETURN ANSWER
--------------------------------

dp[n-1][W]


-----------------------------
TIME COMPLEXITY
--------------------------

O(n * W)

--------------------------------
SPACE COMPLEXITY
-------------------------------

O(n * W)

*/



//tabulation
// class Solution {
//     public int knapsack(int W, int val[], int wt[]) {

//         int n = wt.length ;
//         int[][] dp = new int[n][W+1];

//         //base case 
//         for(int i = wt[0] ; i <= W ; i++) dp[0][i] = val[0] ;

//         for(int i = 1 ; i < n ; i++){
//             for(int j = 0 ; j <= W ; j++){
//                 int notTake = 0 + dp[i-1][j] ;
//                 int take = Integer.MIN_VALUE ;
//                 if(j >= wt[i]){
//                     take = val[i] + dp[i-1][j - wt[i]];
//                 }

//                 dp[i][j] = Math.max(notTake , take) ;
//             }
//         }
//         return dp[n-1][W] ;

//     }
// }







//space optimization 

// class Solution {
//     public int knapsack(int W, int val[], int wt[]) {

//         int n = wt.length ;
//         int[] prev = new int[W+1] ;


//         //base case 
//         for(int i = wt[0] ; i <= W ; i++) prev[i] = val[0] ;

//         for(int i = 1 ; i < n ; i++){
//             int[] curr = new int[W+1] ;
//             for(int j = 0 ; j <= W ; j++){
//                 int notTake = 0 + prev[j] ;
//                 int take = Integer.MIN_VALUE ;
//                 if(j >= wt[i]){
//                     take = val[i] + prev[j - wt[i]];
//                 }
//                 curr[j] = Math.max(notTake , take) ;
//             }
//             prev = curr ;
//         }
//         return prev[W] ;

//     }
// }















// --------------------------------------------------------------------------------------------------------------------
// 1D SPACE OPTIMIZATION
// --------------------------------------------------------------------------------------------------------------------

/*
Observation:

Current row depends ONLY on previous row.

So instead of storing full DP table,
we can store only one array.


------------------------------------------------------------
dp[j] meaning:
------------------------------------------------------------

Maximum value possible for capacity j.


------------------------------------------------------------
STEP 1: BASE CASE
------------------------------------------------------------

Fill capacities that can hold first item.

for(j = wt[0] to W)
    dp[j] = val[0]


------------------------------------------------------------
STEP 2: PROCESS REMAINING ITEMS
------------------------------------------------------------

For every item:

Try all capacities.


------------------------------------------------------------
MOST IMPORTANT PART:
Traverse BACKWARD
------------------------------------------------------------

for(j = W to 0)

WHY BACKWARD?


Because in 0/1 Knapsack,
one item can be taken only once.


If we traverse forward:

0 -> W

then current item may reuse itself again
using already updated values.

That becomes UNBOUNDED KNAPSACK.


Backward traversal ensures:

Current item only uses PREVIOUS ROW states.


------------------------------------------------------------
TRANSITION
------------------------------------------------------------

NOT TAKE:

notTake = dp[j]


TAKE:

Possible only if:

j >= wt[i]

Then:

take = val[i] + dp[j - wt[i]]


FINAL:

dp[j] = max(take, notTake)


------------------------------------------------------------
STEP 3: RETURN ANSWER
------------------------------------------------------------

dp[W]


------------------------------------------------------------
TIME COMPLEXITY
------------------------------------------------------------

O(n * W)


------------------------------------------------------------
SPACE COMPLEXITY
------------------------------------------------------------

O(W)


------------------------------------------------------------
IMPORTANT RULE
------------------------------------------------------------

0/1 Knapsack  -> traverse backward

Unbounded Knapsack -> traverse forward

*/



// 1-D space optimization

class Solution {
    public int knapsack(int W, int val[], int wt[]) {

        int n = wt.length ;
        int[] prev = new int[W+1] ;

        //base case 
        for(int i = wt[0] ; i <= W ; i++) prev[i] = val[0] ;

        for(int i = 1 ; i < n ; i++){
            for(int j = W ; j >= 0 ; j--){
                int notTake = 0 + prev[j] ;
                int take = Integer.MIN_VALUE ;
                if(j >= wt[i]){
                    take = val[i] + prev[j - wt[i]];
                }
                prev[j] = Math.max(notTake , take) ;
            }
        }
        return prev[W] ;

    }
}