/*
    treat start as the source , and once multiplied with array[i] , these will be further nodes 
*/



class Solution {
    int minimumMultiplications(int[] arr, int start, int end) {

        if(start == end) return 0 ;

        // Your code here
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start, 0});
        int[] dist = new int[100000];
        for(int i = 0 ; i < 100000 ; i++) dist[i] = (int)(1e9) ;
        dist[start] = 0 ;
        int mod = 100000 ;

        while(!q.isEmpty()){
            int[] curr = q.poll();
            int node = curr[0];
            int step = curr[1] ;

            for(int i : arr){
                int num = (i * node) % mod ;
                if(step + 1 < dist[num]){
                    dist[num] = step + 1 ;
                    if(num == end) return step + 1 ;
                    q.add(new int[]{num , step + 1}) ;
                }
            }
        }
        return -1 ;

    }
}