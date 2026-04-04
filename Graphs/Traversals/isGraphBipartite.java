
// https://leetcode.com/problems/is-graph-bipartite/. 

class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length ;
        int[] color = new int[n];
        for(int i = 0 ; i<n ; i++){
            color[i] = -1 ;
        }
        for(int i = 0 ; i<n ; i++){
            if(color[i] == -1){
                if(bfs(i , graph , color) == false){
                    return false ;
                }
            }
        }
        return true ;
    }
    public boolean bfs(int start , int[][] graph , int[] color){
        int n = graph.length ;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        color[start] = 0 ;

        while(!queue.isEmpty()){
            int node = queue.poll();
            for(int nei : graph[node]){
                if(color[nei] == -1){
                    color[nei] = 1 - color[node];
                    queue.add(nei);
                }
                else if(color[nei] == color[node]){
                    return false ;
                }
            }
        }
        return true ;
    }
}