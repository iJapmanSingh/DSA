class Solution {
    public boolean isCycle(int V, int[][] edges) {
        //converting edges list to adjacency list -
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0 ; i < V ; i++){
            adj.add(new ArrayList<>());
        }
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        // now my adjacency list is created !!!!!!!!!!!!!!
        boolean[] visited = new boolean[V];
        for(int i = 0 ; i<V ; i++){
            visited[i] = false ;
        }
        for(int i = 0 ; i<V ; i++){
            if(!visited[i]){
                if(BFS(i , adj , visited)){
                    return true;
                }
            }
        }
        return false ;
    }

    public boolean BFS(int start , ArrayList<ArrayList<Integer>> adj , boolean[] visited){
        Queue<int[]> queue = new LinkedList<>();
        visited[start] = true ;
        queue.add(new int[]{start , -1}) ;

        while(!queue.isEmpty()){
            int[] cell = queue.poll();
            int node = cell[0];
            int parent = cell[1] ;

            for(int neighbour : adj.get(node)){
                if(visited[neighbour] == false){
                    visited[neighbour] = true ;
                    queue.add(new int[]{neighbour , node});
                }else if (parent != neighbour){
                    return true ;
                }
            }
        }
        return false ;
    }
}