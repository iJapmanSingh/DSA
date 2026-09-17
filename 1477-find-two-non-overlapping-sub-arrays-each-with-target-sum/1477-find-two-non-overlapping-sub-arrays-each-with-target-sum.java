class Solution {
    Integer[][] dp ;
    int[] len ;
    int n ;
    public int minSumOfLengths(int[] arr, int target) {
        n = arr.length ;
        dp = new Integer[arr.length+1][3];
        len = new int[arr.length+1];
        Arrays.fill(len , -1);

        int low = 0 ;
        int sum = 0;
        for(int high = 0; high < n ; high++){
            sum += arr[high];
            while(sum > target){
                sum -= arr[low];
                low++ ;
            }
            if(sum == target){
                len[low] = high -low +1 ;
            }
        }

        int ans = f(0 , 2);
        if(ans == Integer.MAX_VALUE) return -1 ;
        return ans ;
    }
    public int f(int index , int total ){
        if(index >= n && total > 0){
            return Integer.MAX_VALUE ;
        }
        if(total == 0) return 0 ;
        if(dp[index][total] != null) return dp[index][total];
        
        int notTake = f(index + 1 , total);
        // Take
        int take = Integer.MAX_VALUE;

        if (len[index] != -1) {
            int next = f(index + len[index], total - 1);
            if (next != Integer.MAX_VALUE) {
                take = len[index] + next;
            }
        }
        return dp[index][total] = Math.min(take , notTake);

    }
}