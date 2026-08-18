class Solution {
    Integer[][] dp ;
    public int superEggDrop(int k, int n) {
        dp = new Integer[k+1][n+1];
        return f(k , n);
    }
    public int f(int k , int n ){
        if(n == 0 || n == 1) return n ;
        if(k == 1) return n ;
        if(dp[k][n] != null) return dp[k][n];
        int answer = Integer.MAX_VALUE;
        int low = 1 ;
        int high = n ;
        while(low <= high){
            int drop = low + (high - low)/2 ;
            //case 1 - breaks 
            int broke = f(k-1 , drop-1);
            int safe = f(k , n - drop);
            
            int wrost = Math.max(broke , safe);
            answer = Math.min(answer , 1 + wrost);

            if(broke > safe){
                high = drop -1 ;
            }else{
                low = drop + 1 ;
            }
        }
        return dp[k][n] = answer ;
    }
}