class Solution {
    Integer[] dp ;
    public int numSquares(int n) {
        dp = new Integer[n+1];
        return f(n);
    }
    public int f(int n) {
        if (n == 0) {
            return 0;
        }
        if(dp[n] != null)  return dp[n];
        int ans = Integer.MAX_VALUE;
        for (int j = 1; j * j <= n; j++) {
            int square = j * j;
            ans = Math.min(ans, 1 + f(n - square));
        }
        return dp[n] = ans;
    }
}