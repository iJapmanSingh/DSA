class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

        HashMap<Integer, Integer> map = new HashMap<>();

        // Build frequency map for entire array
        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        // Case 1: k == n
        // There is only ONE subarray: the entire array.
        // Therefore every number appears in exactly one subarray.
        if (k == n) {
            int largest = Integer.MIN_VALUE;

            for (int i = 0; i < n; i++) {
                largest = Math.max(largest, nums[i]);
            }

            return largest;
        }

        // Case 2: k == 1
        // Every element itself is a subarray.
        // So we need the largest number whose frequency is exactly 1.
        if (k == 1) {
            int largest = -1;

            for (int i = 0; i < n; i++) {
                if (map.get(nums[i]) == 1) {
                    largest = Math.max(largest, nums[i]);
                }
            }

            return largest;
        }

        // Case 3: 1 < k < n
        // Only the first and last elements can belong
        // to exactly one subarray of size k.

        int answer = Math.max(
            map.get(nums[0]) == 1 ? nums[0] : -1,
            map.get(nums[n - 1]) == 1 ? nums[n - 1] : -1
        );

        return answer;
    }
}