class Solution {
    public static int longestBitonicSequence(int n, int[] nums) {
        // code here
        // dp1 is computed here
        //
        int[] dp1 = new int[n];
        for (int i = 0 ; i < n ; i++) {
            dp1[i] = 1 ;
        }
        for (int i = 0 ; i < n ; i++) {
            for (int prev = 0 ; prev < i ; prev++) {
                if (nums[prev] < nums[i]) {
                    dp1[i] = Math.max(1 + dp1[prev], dp1[i]);
                }
            }
        }

        // dp2 is computed here
        int[] dp2 = new int[n];
        for (int i = 0 ; i < n ; i++) {
            dp2[i] = 1 ;
        }
        for (int i = n - 1 ; i >= 0 ; i--) {
            for (int prev = n - 1 ; prev > i ; prev--) {
                if (nums[prev] < nums[i]) {
                    dp2[i] = Math.max(1 + dp2[prev], dp2[i]);
                }
            }
        }

        int maxi = 0 ;
        for (int i = 0 ; i < n ; i++) {
            if (dp1[i] > 1 && dp2[i] > 1) {
                maxi = Math.max(maxi, dp1[i] + dp2[i] - 1);
            }
        }
        return maxi ;
    }
}
