
class Solution {
    public int minCost(int n, int[] cuts) {
        int m = cuts.length;

        int[][] dp = new int[m+1][m+1];
        for(int i = 0 ; i <= m ; i++){
            for(int j= 0 ; j <= m ; j++){
                dp[i][j] = -1 ;
            }
        }

        int[] arr = new int[m + 2];
        arr[0] = 0; // add 0 at front
        for (int i = 0; i < m; i++) {
            arr[i + 1] = cuts[i];
        }
        arr[m + 1] = n; // add n at end
        Arrays.sort(arr);

        return f(1 , m , arr , dp);
    }
    public int f(int i , int j , int[] cuts , int[][] dp){
        if(i > j) return 0 ;
        int mini = (int)1e9 ;

        if(dp[i][j] != -1) return dp[i][j] ;

        for(int ind = i ; ind <= j ; ind++){
            int cost = cuts[j+1] - cuts[i-1] + f(i , ind - 1 , cuts , dp) + f(ind + 1 , j , cuts , dp);
            mini = Math.min(mini , cost) ;
        }
        return dp[i][j] = mini ;
    }
}