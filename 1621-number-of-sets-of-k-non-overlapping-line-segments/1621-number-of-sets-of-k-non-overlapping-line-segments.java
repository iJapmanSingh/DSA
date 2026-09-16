class Solution {
    Integer[][][] dp ;
    int MOD = 1000000007;
    public int numberOfSets(int n, int k) {
        dp = new Integer[n+1][k+1][2];
        return f(0 , k , 0 , n);
    }
    public int f(int index , int k , int isStarted , int n){
        if(k == 0) return 1 ;
        if(index == n) return 0 ;
        if(dp[index][k][isStarted] != null) return dp[index][k][isStarted];

        long ways = 0 ;
        if(isStarted == 1){
            ways = (ways + f(index , k - 1 , 0 , n)) % MOD;
            ways = (ways + f(index + 1 , k , 1 , n)) % MOD;
        }else{
            ways = (ways + f(index + 1 , k , 1 , n)) % MOD;
            ways = (ways + f(index + 1 , k , 0 , n)) % MOD;
        }
        return dp[index][k][isStarted] =  (int)ways;
    }
}