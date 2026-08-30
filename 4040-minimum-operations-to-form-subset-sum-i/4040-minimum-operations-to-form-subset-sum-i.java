class Solution {

    static final int INF = 1_000_000_000;

    Integer[][] dp;

    public int minOperations(int[] nums, int sum) {

        int n = nums.length;

        dp = new Integer[n + 1][sum + 1];

        int ans = f(0, nums, sum);

        return ans >= INF ? -1 : ans;
    }

    public int f(int index, int[] nums, int sum) {

        if (index == nums.length) {
            if (sum == 0) {
                return 0;
            }

            return INF;
        }

        if (dp[index][sum] != null) {
            return dp[index][sum];
        }

        // Don't take current element
        int dontTake = f(index + 1, nums, sum);

        int take = INF;

        int value = nums[index];
        int cost = 0;

        // -------------------------
        // DIVISION
        // -------------------------

        while (value > 0) {

            if (value <= sum) {
                take = Math.min(
                    take,
                    cost + f(index + 1, nums, sum - value)
                );
            }

            value /= 2;
            cost++;
        }

        // -------------------------
        // MULTIPLICATION
        // -------------------------

        value = nums[index] * 2;
        cost = 1;

        while (value <= sum) {

            take = Math.min(
                take,
                cost + f(index + 1, nums, sum - value)
            );

            value *= 2;
            cost++;
        }

        return dp[index][sum] = Math.min(take, dontTake);
    }
}


// class Solution {
//     static final int INF = 1000000000 ;
//     Integer[][][][] dp ;
//     public int minOperations(int[] nums, int sum) {
//         int max = sum;
//         for (int x : nums) {
//             max = Math.max(max, x);
//         }
//         int n = nums.length ;
//         dp = new Integer[n+1][max+1][sum+1][2] ;
//         int ans = f(0 , nums[0] , nums , sum , true);
//         return ans >= INF ? -1 : ans ;
//     }
//     public int f(int index , int currentValue , int[] nums , int sum , boolean canMultiply){
//         if(index == nums.length){
//             if(sum == 0) return 0 ;
//             return INF ;
//         }
//          int state = canMultiply ? 1 : 0;
//         if (dp[index][currentValue][sum][state] != null) {
//             return dp[index][currentValue][sum][state];
//         }
//         int multiply = INF ;
//         if(canMultiply && currentValue <= sum/2){
//             multiply = 1 + f(index , currentValue*2 , nums , sum , true);
//         }
//         int divide = INF ;
//         if(currentValue > 0){
//             divide = 1 + f(index ,currentValue/2 , nums , sum , false );
//         }
//         int take = INF ;
//         if(currentValue <= sum){
//             take = f(index + 1 , index + 1 < nums.length ? nums[index + 1] : 0, nums , sum - currentValue , true );
//         }
        
//         int dontTake = f(index + 1 ,index + 1 < nums.length ? nums[index + 1] : 0, nums , sum , true );
//         return dp[index][currentValue][sum][state] = Math.min(Math.min(multiply , divide) , Math.min(take , dontTake ));
//     }
// }