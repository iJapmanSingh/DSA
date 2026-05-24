// again a variation of LCS , just think of formula 

class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int deletions = n - longestCommonSubsequence(word1 , word2) ;
        int insertions = m - longestCommonSubsequence(word1 , word2) ;

        return deletions + insertions ;
    }

    //exact code of LCS ---- this is tabulatin one , if want space optimization - check that problem - 1143
    public int longestCommonSubsequence(String text1, String text2) {
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