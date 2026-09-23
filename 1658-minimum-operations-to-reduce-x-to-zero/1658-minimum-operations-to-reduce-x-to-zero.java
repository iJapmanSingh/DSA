class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length ;
        int sum = 0;
        for(int i = 0 ; i < n; i++){
            sum += nums[i];
        }
        int k = sum - x ;

        if(k == 0){
            return n ;
        }

        int len = 0 ; 
        HashMap<Integer , Integer> map = new HashMap<>();
        map.put(0 , -1);
        int preSum = 0 ;
        
        for(int i =0 ; i < n;i++){
            preSum += nums[i];
            if(map.containsKey(preSum - k)){
                len = Math.max(len , i - map.get(preSum - k));
            }
            if(!map.containsKey(preSum)){
                map.put(preSum , i);
            }
        }

        if(len != 0){
            return n - len ;
        }
        return -1 ;
    }
}