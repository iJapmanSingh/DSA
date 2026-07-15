class Solution {
    public void dfs(int node , int[] visited , ArrayList<ArrayList<Integer>> adj , Stack<Integer> stack){
        visited[node] = 1 ;
        for(Integer it : adj.get(node)){
            if(visited[it] == 0) {
                dfs(it , visited , adj , stack);
            }
        }
        stack.push(node);
    }
    public void dfs3(int node , int[] visited , ArrayList<ArrayList<Integer>> adjRev){
        visited[node] = 1 ;
        for(Integer it : adjRev.get(node)){

            if(visited[it] == 0){
                dfs3(it , visited, adjRev);
            }
        }
    }
    // Function to find number of strongly connected components in the graph
    public int kosaraju(int V, int[][] edges) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        // initialize
        for (int i = 0; i < V ; i++) {
            adj.add(new ArrayList<>());
        }
        // build graph
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            adj.get(a).add(b);
        }

        int[] visited = new int[V];
        Stack<Integer> stack = new Stack<Integer>();
        for(int i= 0 ; i < V ; i++){
            if(visited[i] == 0){
                dfs(i , visited , adj , stack);
            }
        }

        ArrayList<ArrayList<Integer>> adjRev = new ArrayList<>();
        for (int i = 0; i < V ; i++) {
            adjRev.add(new ArrayList<>());
        }
        for(int i = 0 ; i< V ; i++){
            visited[i] = 0 ;
            for(Integer it : adj.get(i)){
                adjRev.get(it).add(i);
            }
        }
        int scc = 0;
        while(!stack.isEmpty()){
            int node = stack.peek();
            stack.pop();
            if(visited[node] == 0){
                scc++ ;
                dfs3(node , visited , adjRev);
            }
        }
        return scc ;
    }
}

