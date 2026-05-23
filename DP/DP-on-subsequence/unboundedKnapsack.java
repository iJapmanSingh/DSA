//memoization solution --------------------------------------------------------------------------------

// class Solution {
//     public int knapSack(int val[], int wt[], int capacity) {
//         // code here
//         int n = wt.length ;
//         int[][] dp = new int[n][capacity+1];
//         for(int i = 0 ; i < n ; i++){
//             for(int j = 0 ; j <= capacity ; j++){
//                 dp[i][j] = -1 ;
//             }
//         }
//         return memoization(n-1 , capacity , wt , val , dp);

//     }
//     public int memoization( int index , int capacity , int[] wt , int val[] , int[][] dp ){
//         //base case 
//         if(index == 0){
//             return (int)(capacity/wt[0]) * val[0] ;
//         }
//         if(dp[index][capacity] != -1 ) return dp[index][capacity];
//         int notTake = 0 + memoization(index - 1 , capacity , wt , val , dp);
//         int take = 0;
//         if(wt[index] <= capacity){
//             take = val[index] + memoization(index , capacity - wt[index] , wt , val , dp);
//         }
//         return dp[index][capacity] = Math.max(take , notTake);
//     }
// } 


// tabulation ------------------------------------------------------------------------------------------

// class Solution {
//     public int knapSack(int val[], int wt[], int capacity) {
//         // code here
//         int n = wt.length ;
//         int[][] dp = new int[n][capacity+1];
//         for(int i = 0 ; i < n ; i++){
//             for(int j = 0 ; j <= capacity ; j++){
//                 dp[i][j] = -1 ;
//             }
//         }
//         for(int i = 0 ; i <= capacity ; i++) dp[0][i] = (int)(i/wt[0]) * val[0] ;

//         for(int index = 1 ; index < n ; index++){
//             for(int j = 0 ; j <= capacity ; j++){
//                 int notTake = 0 + dp[index - 1][j] ;
//                 int take = 0;
//                 if(wt[index] <= j){
//                     take = val[index] + dp[index][j - wt[index]] ; 
//                 }
//                 dp[index][j] = Math.max(take , notTake);
//             }
//         }
//         return dp[n-1][capacity] ; 
//     }

// } 




//space optimized -------------------------------------------------------------------------------------------------------------------------


// class Solution {
//     public int knapSack(int val[], int wt[], int capacity) {
//         // code here
//         int n = wt.length ;

//         int[] prev = new int[capacity + 1];

//         for(int i = 0 ; i <= capacity ; i++) prev[i] = (int)(i/wt[0]) * val[0] ;

//         for(int index = 1 ; index < n ; index++){
//             int[] curr = new int[capacity + 1];
//             for(int j = 0 ; j <= capacity ; j++){
//                 int notTake = 0 + prev[j] ;
//                 int take = 0;
//                 if(wt[index] <= j){
//                     take = val[index] + curr[j - wt[index]] ; 
//                 }
//                 curr[j] = Math.max(take , notTake);
//             }
//             prev = curr ;
//         }
//         return prev[capacity] ; 
//     }
// } 


// 1D space optimized ---------------------------------------------------------------------

class Solution {
    public int knapSack(int val[], int wt[], int capacity) {
        // code here
        int n = wt.length ;

        int[] prev = new int[capacity + 1];

        for(int i = 0 ; i <= capacity ; i++) prev[i] = (int)(i/wt[0]) * val[0] ;

        for(int index = 1 ; index < n ; index++){

            for(int j = 0 ; j <= capacity ; j++){
                int notTake = 0 + prev[j] ;
                int take = 0;
                if(wt[index] <= j){
                    take = val[index] + prev[j - wt[index]] ;
                }
                prev[j] = Math.max(take , notTake);
            }

        }
        return prev[capacity] ;
    }
} 
