import java.util.Arrays;

class Solution {

    int[] dp;

    public int racecar(int target) {

        dp = new int[target + 1];

        Arrays.fill(dp, -1);

        return solve(target);
    }

    private int solve(int target) {

        // Base case
        if (target == 0) {
            return 0;
        }

        // Already calculated
        if (dp[target] != -1) {
            return dp[target];
        }

        // Find largest n such that:
        // 2^n - 1 <= target
        int n = 1;

        while ((1 << n) - 1 < target) {
            n++;
        }

        int position = (1 << n) - 1;

        /*
         * CASE 1:
         * We hit the target exactly.
         *
         * Example:
         * target = 7
         * n = 3
         * 2^3 - 1 = 7
         *
         * So "AAA" is the answer.
         */
        if (position == target) {
            dp[target] = n;
            return n;
        }

        /*
         * CASE 2:
         *
         * We went past the target.
         *
         * Example:
         * target = 6
         *
         * AAA -> position 7
         * R   -> start moving backward
         *
         * Remaining distance = 7 - 6 = 1
         */
        int answer = n + 1 + solve(position - target);

        /*
         * CASE 3:
         *
         * We stop at position = 2^(n-1) - 1
         * and reverse BEFORE going all the way past target.
         *
         * We use:
         *
         * n-1 forward accelerations
         * 1 reverse
         * m backward accelerations
         * 1 reverse
         * solve remaining distance
         */
        int previousPosition = (1 << (n - 1)) - 1;

        for (int m = 0; m < n - 1; m++) {

            // Distance moved backward after m accelerations
            int backwardDistance = (1 << m) - 1;

            // Current position after moving backward
            int currentPosition =
                    previousPosition - backwardDistance;

            // Distance still required to reach target
            int remaining = target - currentPosition;

            /*
             * Cost:
             *
             * n-1 forward A's
             * + 1 reverse
             * + m backward A's
             * + 1 reverse
             * + solve(remaining)
             */
            int candidate =
                    (n - 1)
                    + 1
                    + m
                    + 1
                    + solve(remaining);

            answer = Math.min(answer, candidate);
        }

        dp[target] = answer;

        return answer;
    }
}