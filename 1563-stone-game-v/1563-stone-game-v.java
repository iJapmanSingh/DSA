class Solution {
    Integer[][] dp ;
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length ;
        dp = new Integer[n][n];
        return f(0 , n-1 , stoneValue);
    }
    public int f(int left , int right , int[] arr){
        if(left == right) return 0 ;
        int answer = 0 ;
        int totalSum = 0 ;
        if(dp[left][right] != null) return dp[left][right];
        for(int i = left ; i <= right ; i++){
            totalSum += arr[i];
        }

        int leftSum = 0 ;
        for(int cut = left ; cut < right ; cut++){
            leftSum += arr[cut];
            int rightSum = totalSum - leftSum ;
            if(leftSum < rightSum){
                answer = Math.max(answer , leftSum + f(left , cut , arr));
            }else if(leftSum > rightSum){
                answer = Math.max(answer , rightSum + f(cut+1 , right , arr));
            }else{
                answer = Math.max(answer , Math.max(leftSum + f(left , cut , arr) , rightSum + f(cut+1 , right , arr)));
            }
        }
        return dp[left][right] = answer ;
    }
}