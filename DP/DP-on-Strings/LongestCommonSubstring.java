//tabulation solution --
//similar problem to Longest Common Subsequence on leetcode - 1143 

class Solution {
    public int longCommSubstr(String s1, String s2) {
        // code here

        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n+1][m+1] ;     //did index shifting 

        //base case -
        for(int i = 0 ; i < m ; i++) dp[0][i] = 0;
        for(int i = 0 ; i < n ; i++) dp[i][0] = 0;

        int ans = 0 ;
        for(int index1 = 1 ; index1 <= n ; index1++){
            for(int index2 = 1 ; index2 <= m ; index2++){

                if(s1.charAt(index1 - 1 ) == s2.charAt(index2 - 1)){
                    dp[index1][index2] =  1 + dp[index1 - 1][index2 - 1] ;
                    ans = Math.max(ans , dp[index1][index2]);
                }
            }
        }
        return ans ;
    }
}
