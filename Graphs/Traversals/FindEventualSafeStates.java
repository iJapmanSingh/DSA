// https://leetcode.com/problems/find-eventual-safe-states/description/ 

class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int V = graph.length ;
        int[] visited = new int[V];
        int[] pathvis = new int[V];
        int[] check = new int[V] ;
        List<Integer> ans = new ArrayList<>();

        for(int i = 0 ; i<V ; i++){
            visited[i] = -1 ;
            pathvis[i] = -1 ;
        }
        for(int i = 0 ; i< V ; i++){
            if(visited[i] == -1){
                dfs(i , V , visited , pathvis , graph , check) ;
            }
        }
        for(int i = 0 ; i<V ; i++){
            if(check[i] == 1 ){
                ans.add(i);
            }
        }
        return ans ;
    }

    public boolean dfs(int start , int V , int[] visited , int[] pathvis , int[][] graph , int[] check){
        visited[start] = 1 ;
        pathvis[start] = 1 ;
        check[start] = 0 ;

        for(int nei : graph[start]){
            if(visited[nei] == -1){
                if(dfs(nei , V , visited , pathvis , graph , check) == true ){
                    return true ;
                }
            }else if(pathvis[nei] == 1){
                return true ;
            }
        }
        pathvis[start] = -1 ;
        check[start] = 1 ;
        return false ;
    }
} 