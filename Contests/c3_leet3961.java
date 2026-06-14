class Solution {
    public int getLength(int[] nums) {

        int n = nums.length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            HashMap<Integer, Integer> valueFreq = new HashMap<>();
            HashMap<Integer, Integer> freqCount = new HashMap<>();
            for (int j = i; j < n; j++) {

                int oldFreq = valueFreq.getOrDefault(nums[j], 0);
                if (oldFreq > 0) {
                    freqCount.put(oldFreq, freqCount.get(oldFreq) - 1);
                    if (freqCount.get(oldFreq) == 0) {
                        freqCount.remove(oldFreq);
                    }
                }
                int newFreq = oldFreq + 1;
                valueFreq.put(nums[j], newFreq);
                freqCount.put(newFreq , freqCount.getOrDefault(newFreq , 0) + 1);

                int windowLen = j - i + 1;
                if (isBalanced(i, j, nums, valueFreq , freqCount)) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    public boolean isBalanced(int start, int end, int[] nums, HashMap<Integer, Integer> valueFreq , HashMap<Integer, Integer> freqCount) {

        if(freqCount.size() > 2) return false ;
        if(valueFreq.size() == 1) return true ;


        if(freqCount.size() == 2){

            Iterator<Integer> it = freqCount.keySet().iterator();

            int a = it.next();
            int b = it.next();

            int mn = Math.min(a , b);
            int mx = Math.max(a , b);

            return (mx == 2*mn);
        }
        return false ;
    }
}