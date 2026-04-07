// User function Template for Java

/*
we made an adjacecnyc list from edges 
we did topo sort and stored elements in stack
we made distance array 
pop from stack , go to its neighbours , add distance to weight , if distance of v is greater than distance of node + weigt -> add distance[node]+ weight in distance array of v

*/
class Solution {

    public int[] shortestPath(int V, int E, int[][] edges) {
        // Code here
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();

        for(int i = 0 ; i<V ; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            adj.get(u).add(new int[]{v , w});
        }

        //topo sort inside stack
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];
        for(int i = 0 ; i<V ; i++){
            if(!visited[i]){
                dfs(i , V  , visited , adj , stack);
            }
        }

        //initialize distance array 
        int[] dist = new int[V];
        for(int i= 0 ; i<V ; i++){
            dist[i] = (int)(1e9);
        }
        dist[0] = 0 ;
        while(!stack.isEmpty()){
            int node = stack.peek();
            stack.pop();
            for(int i = 0 ; i<adj.get(node).size() ; i++){
                int[] edge = adj.get(node).get(i);
                int v = edge[0];
                int wt = edge[1];

                if(dist[node] != (int)(1e9) && dist[node] + wt < dist[v]){
                    dist[v] = dist[node] + wt;
                }

            }

        }
        for(int i = 0; i < V; i++){
            if(dist[i] == (int)(1e9)){
                dist[i] = -1;
            }
        }
        return dist ;
    }


    public void dfs(int start , int V , boolean[] visited , ArrayList<ArrayList<int[]>> adj , Stack<Integer> stack){
        visited[start] = true ;
        for(int[] edge : adj.get(start)){
            int nei = edge[0];
            if(!visited[nei]){
                dfs(nei, V , visited, adj, stack);
            }
        }
        stack.push(start);
        return ;
    }

}