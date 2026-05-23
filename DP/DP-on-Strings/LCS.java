// ---------------------------------------------------------------
// LONGEST COMMON SUBSEQUENCE (LCS) - MEMOIZATION APPROACH
// ---------------------------------------------------------------

// PROBLEM:
// Find length of longest subsequence common in both strings.
//
// Example:
// text1 = "abcde"
// text2 = "ace"
//
// LCS = "ace"
// answer = 3


// ---------------------------------------------------------------
// RECURSION THINKING
// ---------------------------------------------------------------

// Function meaning:
// memoization(i, j)
//
// -> Gives LCS length from:
// text1[0...i] and text2[0...j]
//
//
// At every position we have TWO MAIN CASES:
//
// ===============================================================
// CASE 1: CHARACTERS MATCH
// ===============================================================
//
// if(text1.charAt(i) == text2.charAt(j))
//
// Example:
// text1 = "abc"
//              i->c
//
// text2 = "adc"
//              j->c
//
// Since both characters are SAME,
// we INCLUDE this character in our answer.
//
// So:
// 1 + move both pointers backward
//
// return 1 + f(i-1, j-1)
//
//
// WHY?
// because this matching character definitely becomes part of LCS.



// ===============================================================
// CASE 2: CHARACTERS DO NOT MATCH
// ===============================================================
//
// Example:
// text1 = "abc"
//              i->c
//
// text2 = "ab"
//             j->b
//
// c != b
//
// We CANNOT take both together.
//
// So we try TWO possibilities:
//
// ---------------------------------------------------------------
// OPTION 1 -> Ignore character from text1
// ---------------------------------------------------------------
//
// move i backward
//
// f(i-1, j)
//
//
// ---------------------------------------------------------------
// OPTION 2 -> Ignore character from text2
// ---------------------------------------------------------------
//
// move j backward
//
// f(i, j-1)
//
//
// Take MAX of both because we want LONGEST subsequence.
//
// return max(f(i-1,j), f(i,j-1))



// ---------------------------------------------------------------
// BASE CASE
// ---------------------------------------------------------------
//
// if(i < 0 || j < 0)
//
// Means one string is exhausted.
//
// No common subsequence possible anymore.
//
// return 0



// ---------------------------------------------------------------
// DP / MEMOIZATION
// ---------------------------------------------------------------
//
// dp[i][j]
//
// Stores already calculated answer for:
//
// text1[0...i] and text2[0...j]
//
// Avoids repeated recursion calls.
//
// If dp already has answer:
//
// return dp[i][j]



// ---------------------------------------------------------------
// TIME COMPLEXITY
// ---------------------------------------------------------------
//
// Total states = n * m
//
// Each state solved once
//
// TC = O(n * m)
// SC = O(n * m) + recursion stack



// ---------------------------------------------------------------
// VERY IMPORTANT INTUITION
// ---------------------------------------------------------------
//
// MATCH:
// --------
// take character
// move BOTH pointers
//
// NOT MATCH:
// ------------
// try skipping one character from either string
// take maximum answer
//
// This is the CORE IDEA of LCS.
// ---------------------------------------------------------------







//memoization solution ----------------------------------------------------------------------------------------------------------------

// class Solution {
//     public int longestCommonSubsequence(String text1, String text2) {
//         int n = text1.length(); 
//         int m = text2.length(); 

//         int[][] dp = new int[n][m] ;
//         for(int i = 0 ; i < n ; i++){
//             for(int j = 0 ; j < m ; j++){
//                 dp[i][j] = -1 ; 
//             }
//         }

//         return memoization(n-1 , m-1 , text1 , text2 , dp);
//     }

//     public int memoization( int index1 , int index2 , String text1 , String text2 , int[][] dp){
//         //base case 
//         if(index1 < 0 || index2 < 0) return 0 ; 

//         if(dp[index1][index2] != -1) return dp[index1][index2] ;
//         if(text1.charAt(index1) == text2.charAt(index2)){
//             return 1 + memoization(index1 - 1 , index2 - 1 , text1 , text2  , dp);
//         }
//         return dp[index1][index2] = Math.max(memoization(index1 - 1 , index2 , text1 , text2 , dp) , memoization(index1 , index2 - 1 , text1 , text2 , dp));

//     }
// }








//tabulation solution ----------------------------------------------------------------------------------------------------------------


// class Solution {
//     public int longestCommonSubsequence(String text1, String text2) {
//         int n = text1.length(); 
//         int m = text2.length(); 

//         int[][] dp = new int[n+1][m+1] ;     //did index shifting 

//         //base case -
//         for(int i = 0 ; i < m ; i++) dp[0][i] = 0;
//         for(int i = 0 ; i < n ; i++) dp[i][0] = 0;

//         for(int index1 = 1 ; index1 <= n ; index1++){
//             for(int index2 = 1 ; index2 <= m ; index2++){

//                 if(text1.charAt(index1 - 1 ) == text2.charAt(index2 - 1)){
//                     dp[index1][index2] =  1 + dp[index1 - 1][index2 - 1] ;
//                 }
//                 else dp[index1][index2] = Math.max(dp[index1 - 1][index2]  , dp [index1][index2 - 1]) ;
//             }
//         }
//         return dp[n][m] ;

//     }
// }



//space optimized solution ----------------------------------------------------------------------------------------------------------------


class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        int[] prev = new int[m+1] ;

        //base case -
        for(int i = 0 ; i < m ; i++) prev[i] = 0;


        for(int index1 = 1 ; index1 <= n ; index1++){
            int[] curr = new int[m+1] ;
            for(int index2 = 1 ; index2 <= m ; index2++){

                if(text1.charAt(index1 - 1 ) == text2.charAt(index2 - 1)){
                    curr[index2] =  1 + prev[index2 - 1] ;
                }
                else curr[index2] = Math.max(prev[index2]  , curr[index2 - 1]) ;
            }
            prev = curr ;
        }
        return prev[m] ;

    }
}




// ---------------------------------------------------------------
// SPACE OPTIMIZATION - LCS
// ---------------------------------------------------------------

// OBSERVATION:
//
// In tabulation:
//
// dp[i][j] depends only on:
//
// 1. previous row
// 2. current row left value
//
// We NEVER need entire matrix.
//
// So instead of storing n*m matrix,
// we store only:
//
// prev[] -> previous row
// curr[] -> current row



// ---------------------------------------------------------------
// WHAT prev[] AND curr[] REPRESENT
// ---------------------------------------------------------------

// prev[j]
//
// Represents:
//
// answer for previous row
// dp[i-1][j]
//
//
// curr[j]
//
// Represents:
//
// current row answer
// dp[i][j]



// ---------------------------------------------------------------
// BASE CASE
// ---------------------------------------------------------------

// Initially previous row is all zero.
//
// WHY?
//
// Because dp[0][j] = 0
//
// meaning:
//
// empty string vs anything = 0
//
// Therefore:
//
// prev[] initialized with 0



// ---------------------------------------------------------------
// INDEX SHIFTING
// ---------------------------------------------------------------

// Same concept as tabulation.
//
// We use arrays of size m+1
//
// because:
//
// column 0 represents empty string.
//
// Therefore:
//
// actual string indexes become:
//
// text1.charAt(index1 - 1)
// text2.charAt(index2 - 1)


// ---------------------------------------------------------------
// MAIN TRANSITIONS
// ---------------------------------------------------------------

// ===============================================================
// CASE 1: MATCH
// ===============================================================
//
// if characters match:
//
// curr[index2]
//      = 1 + prev[index2-1]
//
//
// WHY?
//
// prev[index2-1]
//
// represents diagonal value:
//
// dp[i-1][j-1]
//
// Since current characters are same,
// include them in answer.


// ===============================================================
// CASE 2: NOT MATCH
// ===============================================================
//
// curr[index2]
//      = max(prev[index2], curr[index2-1])
//
//
// prev[index2]
//
// means top value:
// dp[i-1][j]
//
//
// curr[index2-1]
//
// means left value:
// dp[i][j-1]



// ---------------------------------------------------------------
// WHY curr[index2-1] INSTEAD OF prev[index2-1] ?
// ---------------------------------------------------------------

// Because:
//
// left cell belongs to CURRENT row.
//
// During row traversal,
// current row left values are already computed.
//
// So:
//
// curr[index2-1]
// represents dp[i][j-1]



// ---------------------------------------------------------------
// AFTER COMPLETING CURRENT ROW
// ---------------------------------------------------------------

// prev = curr
//
// Move current row to previous row
//
// because next iteration will need it.



// ---------------------------------------------------------------
// FINAL ANSWER
// ---------------------------------------------------------------
//
// prev[m]
//
// represents last completed row answer.
//
// i.e. dp[n][m]



// ---------------------------------------------------------------
// TIME AND SPACE
// ---------------------------------------------------------------
//
// TC = O(n*m)
//
// SC = O(m)
//
// Huge optimization from O(n*m)
// to O(m).
// ---------------------------------------------------------------