// ALGORITHM / INTUITION :

// 1. We are given price[i] = profit of rod piece of length (i + 1).
//    We need maximum profit by cutting rod of total length n.

// 2. Observe carefully:
//    - We can take same rod piece multiple times.
//    - Example: length 2 piece can be used again and again.
//    => This is the key property of UNBOUNDED KNAPSACK.

// 3. Mapping with Unbounded Knapsack:
//    --------------------------------
//    Rod piece length  -> item weight
//    Rod piece price   -> item value
//    Rod total length  -> bag capacity
//    Reuse allowed     -> unbounded knapsack

// 4. Decision at every index:
//    ------------------------
//    NOT TAKE:
//       move to previous index.
//
//    TAKE:
//       take current rod piece,
//       add its price,
//       reduce remaining rod length,
//       stay on same index because reuse is allowed.

// 5. Base Case Understanding:
//    ------------------------
//    If only length 1 rod piece is available,
//    then maximum profit = rodLength * price[0].

// 6. Transition:
//    ------------
//    dp[index][rodLength] = max(take , notTake)

// 7. Important Observation for 1D Optimization:
//    ------------------------------------------
//    Since current state depends on same row
//    (current index after taking),
//    we traverse rodLength from LEFT to RIGHT.




// memoization solution ------------------------------------------------------------------------------

// class Solution {
//     public int cutRod(int[] price) {
//         // code here

//         int n = price.length ;

//         int[][] dp = new int[n][n + 1] ;   //dp array of index * rodlength dimensions
//         for(int i = 0 ; i<n ; i++){
//             for(int j = 0 ; j <= n ; j++){
//                 dp[i][j] = -1  ;
//             }
//         }

//         return memoization(n-1 , n , price , dp);
//     }
//     public int memoization(int index , int rodLength , int[] price , int[][] dp){
//         //base case 
//         if(index == 0){
//             return rodLength * price[0]; 
//         }

//         if(dp[index][rodLength] != -1) return dp[index][rodLength] ;

//         int rodlen = index + 1 ;
//         int notTake = 0 + memoization(index - 1 , rodLength  , price , dp);
//         int take = Integer.MIN_VALUE ;
//         if(rodlen <= rodLength){
//             take = price[index] + memoization(index , rodLength - rodlen , price , dp);
//         }
//         return dp[index][rodLength] = Math.max(take , notTake);
//     }
// }

//tabulation ----------------------------------------------------------------------------------

// class Solution {
//     public int cutRod(int[] price) {
//         // code here

//         int n = price.length ;

//         int[][] dp = new int[n][n + 1] ;   //dp array of index * rodlength dimensions

//         for(int i = 0 ; i <= n ; i++) dp[0][i] = i*price[0] ;

//         for(int index = 1 ; index < n ; index++){
//             for(int rodLength = 0 ; rodLength <= n ; rodLength++){

//                 int rodlen = index + 1 ;
//                 int notTake = 0 + dp[index - 1][rodLength] ;
//                 int take = Integer.MIN_VALUE ;
//                 if(rodlen <= rodLength){
//                     take = price[index] + dp[index][rodLength - rodlen ] ;

//                 }

//                 dp[index][rodLength] = Math.max(take , notTake);
//             }
//         }
//         return dp[n-1][n] ;
//     }
// }


//space optimization ------------------------------------------------------------------------------------------------

// class Solution {
//     public int cutRod(int[] price) {
//         // code here

//         int n = price.length ;

//         int[] prev = new int[n+1] ;

//         for(int i = 0 ; i <= n ; i++) prev[i] = i*price[0] ;

//         for(int index = 1 ; index < n ; index++){
//             int[] curr = new int[n+1];
//             for(int rodLength = 0 ; rodLength <= n ; rodLength++){

//                 int rodlen = index + 1 ;
//                 int notTake = 0 + prev[rodLength] ;
//                 int take = Integer.MIN_VALUE ;
//                 if(rodlen <= rodLength){
//                     take = price[index] + curr[rodLength - rodlen ] ;

//                 }

//                 curr[rodLength] = Math.max(take , notTake);
//             }
//             prev = curr ;
//         }
//         return prev[n] ;
//     }
// }

// 1D space optimization ----------------------------------------------------------------------------


class Solution {
    public int cutRod(int[] price) {
        // code here

        int n = price.length ;

        int[] prev = new int[n+1] ;

        for(int i = 0 ; i <= n ; i++) prev[i] = i*price[0] ;

        for(int index = 1 ; index < n ; index++){

            for(int rodLength = 0 ; rodLength <= n ; rodLength++){

                int rodlen = index + 1 ;
                int notTake = 0 + prev[rodLength] ;
                int take = Integer.MIN_VALUE ;
                if(rodlen <= rodLength){
                    take = price[index] + prev[rodLength - rodlen ] ;

                }
                prev[rodLength] = Math.max(take , notTake);
            }
        }
        return prev[n] ;
    }
}
