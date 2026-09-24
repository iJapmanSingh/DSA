class Solution {
    public int smallestIndex(int[] nums) {
        int n = nums.length ;
        for(int i = 0 ; i < n ; i++){
            int temp = nums[i];
            int sum = 0 ;
            while(temp > 0){
                sum += temp % 10 ;
                temp /= 10 ;
            }
            if(sum == i){
                return i ;
            }
        }
        return -1 ;
    }
}