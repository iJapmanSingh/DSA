/*
Algorithm: House Robber II

1. If only one house exists, return its value.

2. Since houses are in a circle, first and last cannot be robbed together.
   So divide into two cases:
   a) Exclude first house → consider range [1 to n-1]
   b) Exclude last house  → consider range [0 to n-2]

3. For each case, apply House Robber I (linear DP):
   - Initialize:
       prev = nums[0]
       prev2 = 0
   - For each index i:
       take = nums[i] + prev2
       notTake = prev
       curr = max(take, notTake)
       Update: prev2 = prev, prev = curr

4. Return max(result of both cases)

Time Complexity: O(n)
Space Complexity: O(1)
*/



class Solution {
    public int rob(int[] nums) {
        int n = nums.length ;
        if(n == 1 ) return nums[0];
        int[] temp1 = new int[n-1];
        int[] temp2 = new int[n-1];
        for(int i = 0 ; i< n ; i++){
            if(i != 0) {
                temp1[i-1] = nums[i];
            }
            if(i != n - 1){
                temp2[i] = nums[i];
            }
        }
        return Math.max(robs(temp1) , robs(temp2));
    }

    public int robs(int[] nums) {
        int prev = nums[0];
        int prev2 = 0 ;
        for(int i = 1 ; i < nums.length ; i++){
            int take = nums[i] ;
            if(i > 1){
                take += prev2 ;
            }
            int notTake = prev ;
            int curr = Math.max(take , notTake);
            prev2 = prev ;
            prev = curr ;
        }
        return prev ;
    }
}