class Solution {
    public boolean search(int[] nums, int target) {

        int n = nums.length;
        int left = 0, right = n - 1;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            }
            else if (nums[mid] < nums[right]) {
                right = mid;
            }
            else {
                right--;
            }
        }

        int pivot = left;

        // Find the actual rotation point if duplicates
        // caused the minimum search to return index 0.
        if (pivot == 0) {
            for (int i = 0; i < n - 1; i++) {
                if (nums[i] > nums[i + 1]) {
                    pivot = i + 1;
                    break;
                }
            }
        }

        // Search in the right sorted half
        if (target >= nums[pivot] && target <= nums[n - 1]) {
            return binarySearch(nums, pivot, n - 1, target) != -1;
        }

        // Search in the left sorted half
        else {
            return binarySearch(nums, 0, pivot - 1, target) != -1;
        }
    }

    public int binarySearch(int[] nums, int left, int right, int target) {

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }
            else if (nums[mid] > target) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        return -1;
    }
}