class Solution {
    public int[] dijkstra(int V, int[][] edges, int src) {
        // code here

        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();

        for(int i = 0 ; i<V ; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            adj.get(u).add(new int[]{v , w});
            adj.get(v).add(new int[]{u , w});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);   // compare distance

        int[] dist = new int[V];
        for(int i = 0 ; i<V ; i++)  dist[i] = (int)1e9 ;
        dist[src] = 0 ;

        pq.add(new int[]{0 , src});

        while(!pq.isEmpty()){
            int[] curr = pq.poll();

            int d = curr[0];
            int node = curr[1];

            if(d > dist[node]) continue ;

            for(int[] edge : adj.get(node)){
                int adjNode = edge[0];
                int weight = edge[1];

                if(d + weight < dist[adjNode]){
                    dist[adjNode] = d + weight ;
                    pq.add(new int[]{ dist[adjNode] , adjNode});
                }
            }
        }
        return dist ;

    }
}