// i have literally copied code of LPS and LCS 
// just we need to use the brain to find formula which is - (Length of String - Longest Palindrome Subsequence) 



class Solution {
    public int minInsertions(String s) {
        int n = s.length();
        int LPS = longestPalindromeSubseq(s);
        return n - LPS ;
    }

    public int longestPalindromeSubseq(String s) {

        String reversedS = new StringBuilder(s).reverse().toString();

        return longestCommonSubsequence(s , reversedS) ;
    }
    public static int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        int[][] dp = new int[n+1][m+1] ;     //did index shifting 

        //base case -
        for(int i = 0 ; i < m ; i++) dp[0][i] = 0;
        for(int i = 0 ; i < n ; i++) dp[i][0] = 0;

        for(int index1 = 1 ; index1 <= n ; index1++){
            for(int index2 = 1 ; index2 <= m ; index2++){

                if(text1.charAt(index1 - 1 ) == text2.charAt(index2 - 1)){
                    dp[index1][index2] =  1 + dp[index1 - 1][index2 - 1] ;
                }
                else dp[index1][index2] = Math.max(dp[index1 - 1][index2]  , dp [index1][index2 - 1]) ;
            }
        }
        return dp[n][m] ;
    }
}