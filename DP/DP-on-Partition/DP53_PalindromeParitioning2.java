// class Solution {
//     public int minCut(String s) {
//         int n = s.length();
//         int[] dp = new int[n];
//         for(int i = 0 ; i < n ; i++){
//             dp[i] = -1 ;
//         }
//         return f(0 , n , s , dp) - 1;
//     }

//     public int f(int i , int n , String s , int[] dp){
//         if(i == n) return 0 ;
//         if(dp[i] != -1) return dp[i];

//         int minCost = (int)1e9 ;

// for(int j = i ; j < n ; j++){

//     if(isPalindrome(i , j , s )){
//         int cost = 1 + f(j+1 , n , s , dp);
//         minCost = Math.min(minCost , cost);
//     }
// }
//         return dp[i] = minCost ;
//     }

//     public boolean isPalindrome(int i , int j , String s){
//         while( i < j){
//             if(s.charAt(i) != s.charAt(j)) return false ;
//             i++ ;
//             j-- ;
//         }
//         return true ;
//     }
// }



class Solution {
    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];

        dp[n] = 0 ; // base case
        for(int i = n-1 ; i >= 0 ; i--){
            int minCost = (int)1e9 ;
            for(int j = i ; j < n ; j++){
                if(isPalindrome(i , j , s )){
                    int cost = 1 + dp[j+1];
                    minCost = Math.min(minCost , cost);
                }
            }
            dp[i] = minCost ;
        }
        return dp[0] - 1;
    }

    public boolean isPalindrome(int i , int j , String s){
        while( i < j){
            if(s.charAt(i) != s.charAt(j)) return false ;
            i++ ;
            j-- ;
        }
        return true ;
    }
}





/*
-------------------------------------------
PALINDROME PARTITIONING II (MIN CUTS)
-------------------------------------------

Goal:
Partition the string into palindrome substrings such that
the number of cuts is minimum.

Example:
"aab"

Possible partitions:
a | a | b   -> 2 cuts
aa | b      -> 1 cut   (minimum)

--------------------------------------------------
DP STATE
--------------------------------------------------

dp[i] = minimum number of palindrome PARTITIONS
needed for substring s[i...n-1].

Why partitions and not cuts?

Suppose at index i we choose a palindrome
s[i...j].

Then the remaining problem becomes:
s[j+1...n-1]

So:

dp[i]
= 1 (current palindrome partition)
+ dp[j+1] (partitions needed in remaining string)

Hence dp naturally stores number of partitions.

--------------------------------------------------
TRANSITION
--------------------------------------------------

For every possible ending index j:

if s[i...j] is palindrome:

    cost = 1 + dp[j+1]

Take minimum over all valid palindromic choices.

dp[i] = min(cost)

--------------------------------------------------
BASE CASE
--------------------------------------------------

dp[n] = 0

Reason:
Empty string requires 0 partitions.

--------------------------------------------------
ORDER OF COMPUTATION
--------------------------------------------------

Since dp[i] depends on dp[j+1]
(where j+1 > i),

compute from right to left:

i = n-1 -> 0

--------------------------------------------------
FINAL ANSWER
--------------------------------------------------

dp[0] gives minimum number of palindrome partitions.

But question asks minimum CUTS.

Relationship:

cuts = partitions - 1

Example:
aa | b

Partitions = 2
Cuts = 1

Therefore:

answer = dp[0] - 1

--------------------------------------------------
TIME COMPLEXITY
--------------------------------------------------

States: O(n)

For each state:
    Try all j from i to n-1  -> O(n)

Palindrome check:
    O(n)

Total:
O(n^3)

Can be optimized to O(n^2) by precomputing
palindrome information.
--------------------------------------------------
*/