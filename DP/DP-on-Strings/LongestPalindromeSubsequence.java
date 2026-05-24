//Similar to LCS , just think which is second string , and that is revese of s 
// because palindrom is same in s and reverse of s ( common , Longest , subsequence !!!!!)

class Solution {
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