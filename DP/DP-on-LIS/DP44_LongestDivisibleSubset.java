//similar to printing LIS , just sort array and change if condidtion 



class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {

        int n = nums.length;
        Arrays.sort(nums) ;
        int[] dp = new int[n];
        int[] hash = new int[n];
        Arrays.fill(dp, 1);

        int maxi = 1;
        int lastIndex = 0;

        for (int i = 0; i < n; i++) {
            hash[i] = i;
            for (int prev = 0; prev < i; prev++) {
                if (nums[i] % nums[prev] == 0 &&
                        dp[prev] + 1 > dp[i]) {

                    dp[i] = dp[prev] + 1;
                    hash[i] = prev;
                }
            }
            if (dp[i] > maxi) {
                maxi = dp[i];
                lastIndex = i;
            }
        }
        List<Integer> lis = new ArrayList<>();
        // start from last element of LIS
        lis.add(nums[lastIndex]);

        while (hash[lastIndex] != lastIndex) {
            lastIndex = hash[lastIndex];
            lis.add(nums[lastIndex]);
        }
        Collections.reverse(lis);
        return lis;
    }
}