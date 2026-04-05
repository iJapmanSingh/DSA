
//   https://www.geeksforgeeks.org/problems/topological-sort/1

// this is topological sort using DFS and Stack 

class Solution {
    public ArrayList<Integer> topoSort(int V, int[][] edges) {
        // code here
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0 ; i < V ; i++){
            adj.add(new ArrayList<>());
        }
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            adj.get(u).add(v);

        }
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];
        for(int i = 0 ; i<V ; i++){
            if(!visited[i]){
                dfs(i , V  , visited , adj , stack);
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        while(!stack.isEmpty()){
            int element = stack.pop();
            result.add(element);
        }
        return result ;
    }

    public void dfs(int start , int V , boolean[] visited , ArrayList<ArrayList<Integer>> adj , Stack<Integer> stack){
        visited[start] = true ;
        for(int nei : adj.get(start)){
            if(!visited[nei]){
                dfs(nei , V , visited , adj , stack);
            }
        }
        stack.push(start);
        return ;
    }
}