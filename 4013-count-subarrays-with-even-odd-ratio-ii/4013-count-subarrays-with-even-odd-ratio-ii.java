class Solution {
    static class Fenwick {
        int[] tree;
        Fenwick(int n) {
            tree = new int[n + 1];
        }
        void add(int index, int value) {
            while (index < tree.length) {
                tree[index] += value;
                index += index & -index;
            }
        }
        int query(int index) {
            int sum = 0;
            while (index > 0) {
                sum += tree[index];
                index -= index & -index;
            }
            return sum;
        }
    }
    public long countRatioSubarrays(int[] nums, int a, int b) {
        int n = nums.length ;
        long[] prefix = new long[n + 1];
        for(int i =0 ; i < n ; i++){
            if(nums[i] % 2 == 0){
                prefix[i + 1] = prefix[i] + b;
            }else{
                prefix[i + 1] = prefix[i] - a;
            }
        }
        //cordinate compression
        long[] sorted = prefix.clone();
        Arrays.sort(sorted);
        int m = 0 ;
        for(long x : sorted){
            if(m == 0 || sorted[m-1] != x){
                sorted[m] = x ;
                m++;
            }
        }

        Fenwick fenwick = new Fenwick(m);
        long answer = 0 ;
        int previousCount = 0 ;

        for(long current : prefix){
            //find rank of current prefix sum
            int rank = lowerBound(sorted , m , current) + 1;

            //number of previous prefix sum < current
            int smaller = fenwick.query(rank - 1);
            
            //number of previous prefix sum >= current
            int greaterOrEqual = previousCount - smaller;

            answer += greaterOrEqual;

            //Insert current prefix sum
            fenwick.add(rank , 1);

            previousCount++ ;
        } 
        return answer ;
    }
    static int lowerBound(long[] arr, int size, long target) {

        int left = 0;
        int right = size;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}

    
    
