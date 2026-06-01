/*
=========================================
EDIT DISTANCE (Leetcode 72)
=========================================

PROBLEM:
Convert word1 -> word2 using minimum operations.

Allowed Operations:
1. Insert
2. Delete
3. Replace

--------------------------------------------------
RECURSION THINKING
--------------------------------------------------

State:
f(i,j) = minimum operations required to convert
word1[0...i] -> word2[0...j]

Base Cases:

1. i < 0
   word1 exhausted.
   Need to insert remaining characters of word2.

   return j + 1

2. j < 0
   word2 exhausted.
   Need to delete remaining characters of word1.

   return i + 1

--------------------------------------------------
CHOICES
--------------------------------------------------

If characters match:

word1[i] == word2[j]

No operation needed.

f(i,j) = f(i-1,j-1)

--------------------------------------------------

If characters do not match:

1. Insert

Insert word2[j] into word1.

word1 stays at i
word2 moves to j-1

1 + f(i,j-1)

--------------------------------------------------

2. Delete

Delete word1[i]

word1 moves to i-1
word2 stays at j

1 + f(i-1,j)

--------------------------------------------------

3. Replace

Replace word1[i] with word2[j]

Move both pointers

1 + f(i-1,j-1)

--------------------------------------------------

Take minimum of all three choices.

f(i,j) =
1 + min(
        f(i,j-1),      // insert
        f(i-1,j),      // delete
        f(i-1,j-1)     // replace
       )

--------------------------------------------------
MEMOIZATION
--------------------------------------------------

Store answers in dp[i][j].

Time  : O(n*m)
Space : O(n*m) + recursion stack

--------------------------------------------------
TABULATION
--------------------------------------------------

Index Shifting:

f(i,j)
↓
dp[i+1][j+1]

Table Size:

dp[n+1][m+1]

Base Cases:

dp[0][j] = j
(Need j insertions)

dp[i][0] = i
(Need i deletions)

Transition:

If chars match:

dp[i][j] = dp[i-1][j-1]

Else:

insert  = 1 + dp[i][j-1]
delete  = 1 + dp[i-1][j]
replace = 1 + dp[i-1][j-1]

dp[i][j] = min(insert, delete, replace)

Answer:

dp[n][m]

Time  : O(n*m)
Space : O(n*m)

--------------------------------------------------
SPACE OPTIMIZATION
--------------------------------------------------

Dependencies:

dp[i][j-1]   -> current row
dp[i-1][j]   -> previous row
dp[i-1][j-1] -> previous row

Only previous row is required.

Use:

prev[]
curr[]

Mapping:

dp[i][j-1]   -> curr[j-1]
dp[i-1][j]   -> prev[j]
dp[i-1][j-1] -> prev[j-1]

After each row:

prev = curr

Time  : O(n*m)
Space : O(m)

--------------------------------------------------
INTERVIEW KEYWORD
--------------------------------------------------

Whenever you see:

"Convert String A -> String B"
"Minimum Operations"
"Insert/Delete/Replace"

Think:

State -> f(i,j)

Match:
    move diagonally

Mismatch:
    Insert
    Delete
    Replace

Take minimum.
=========================================
*/



//recursion solution -----------------------------------------------------------------------------------

// class Solution {
//     public int minDistance(String word1, String word2) {
//         int n = word1.length();
//         int m = word2.length();
//         return memoization(n-1 , m-1 , word1 , word2 );
//     }

//     public int memoization(int i , int j , String word1 , String word2){
//         //base cases 
//         if(i < 0) return j+1 ;
//         if(j < 0) return i+1 ;
//         if(word1.charAt(i) == word2.charAt(j)) return 0 + memoization(i-1 , j-1 , word1 , word2);
//         //explore all paths

//         int insert = 1 + memoization( i , j-1 , word1 , word2);
//         int delete = 1 + memoization(i-1 , j , word1 , word2);
//         int replace = 1 + memoization(i-1 , j-1 , word1 , word2);

//         return Math.min(insert , Math.min(delete , replace));
//     }
// }


// memoization solution ---------------------------------------------------------------------------------

// class Solution {
//     public int minDistance(String word1, String word2) {
//         int n = word1.length();
//         int m = word2.length();

//         int[][] dp = new int[n][m] ;
//         for(int i = 0 ; i < n ; i++){
//             for(int j = 0 ; j < m ; j++){
//                 dp[i][j] = (int)1e9 ;
//             }
//         }


//         return memoization(n-1 , m-1 , word1 , word2 , dp);
//     }

//     public int memoization(int i , int j , String word1 , String word2 , int[][] dp){
//         //base cases 
//         if(i < 0) return j+1 ;
//         if(j < 0) return i+1 ;
//         if(word1.charAt(i) == word2.charAt(j)) return 0 + memoization(i-1 , j-1 , word1 , word2 , dp);
//         //explore all paths
//         if(dp[i][j] != (int)1e9) return dp[i][j] ;

//         int insert = 1 + memoization( i , j-1 , word1 , word2 , dp);
//         int delete = 1 + memoization(i-1 , j , word1 , word2 , dp);
//         int replace = 1 + memoization(i-1 , j-1 , word1 , word2 , dp);

//         return dp[i][j] = Math.min(insert , Math.min(delete , replace));
//     }
// }


//tabulation solution ------------------------------------------------------------------------

// class Solution {
//     public int minDistance(String word1, String word2) {
//         int n = word1.length();
//         int m = word2.length();

//         int[][] dp = new int[n+1][m+1] ;

//         //base cases - 
//         for(int j = 0 ; j <= m ; j++) dp[0][j] = j ;
//         for(int i = 0 ; i <= n ; i++) dp[i][0] = i ;

//         for(int i = 1 ; i <= n ; i++){
//             for(int j = 1 ; j <= m ; j++){
//                 if(word1.charAt(i-1) == word2.charAt(j-1)){
//                     dp[i][j] = 0 + dp[i-1][j-1] ;
//                 }else{
//                     int insert = 1 + dp[i][j-1] ;
//                     int delete = 1 + dp[i-1][j] ;
//                     int replace = 1 + dp[i-1][j-1] ;

//                     dp[i][j] = Math.min(insert , Math.min(delete , replace));
//                 }                
//             }
//         }
//         return dp[n][m] ;
//     }
// }



class Solution {
    public int minDistance(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();
        int[] prev = new int[m + 1];
        // base case for row 0
        for(int j = 0; j <= m; j++) {
            prev[j] = j;
        }
        for(int i = 1; i <= n; i++) {
            int[] curr = new int[m + 1];
            // base case for column 0
            curr[0] = i;
            for(int j = 1; j <= m; j++) {
                if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    curr[j] = prev[j - 1];
                }
                else {
                    int insert  = 1 + curr[j - 1];
                    int delete  = 1 + prev[j];
                    int replace = 1 + prev[j - 1];
                    curr[j] = Math.min(insert,
                            Math.min(delete, replace));
                }
            }
            prev = curr;
        }
        return prev[m];
    }
}