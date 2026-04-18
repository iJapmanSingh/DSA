class Solution {
    public int spanningTree(int V, int[][] edges) {
        // code here
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        for(int i= 0 ;  i< V ; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            adj.get(u).add(new int[]{v , wt});
            adj.get(v).add(new int[]{u , wt});
        }

        int[] visited = new int[V];
        for(int i= 0 ; i<V ; i++) visited[i] = 0 ;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        pq.add(new int[]{0 , 0});

        int sum = 0 ;

        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int weight = curr[0];
            int node = curr[1];

            if(visited[node] == 1) continue ;
            visited[node] = 1 ;
            sum += weight ;

            for(int[] it : adj.get(node)){
                int adjNode = it[0];
                int adjWt = it[1];

                if(visited[adjNode] == 0){
                    pq.add(new int[]{adjWt , adjNode});
                }
            }
        }
        return sum ;

    }
}
