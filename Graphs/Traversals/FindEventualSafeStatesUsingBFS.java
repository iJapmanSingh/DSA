//Now we are solving this question using Topological Sort(BFS)
//Simply reverse order of graph , add elements with indegree 0 in queue
//remove one by one and add to answer and reduce indegree of neighbours

class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length ;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i = 0 ; i < n ; i++){
            adj.add(new ArrayList<>());
        }
        for(int i = 0 ; i < n ; i++){
            for(int v : graph[i]){
                adj.get(v).add(i);
            }
        }
        // now , adj is the reverse of graph
        int[] indegree = new int[n];
        for(int i = 0 ; i < n ; i++){
            for(int node : adj.get(i)){
                indegree[node]++ ;
            }
        }
        //now we have indegrees also 
        // now lets add elements with indegree of 0s in the queue
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0 ; i<n ; i++){
            if(indegree[i] == 0){
                queue.add(i);
            }
        }
        //now we have added elements with indegree == 0 in the queue
        List<Integer> ans = new ArrayList<>();
        while(!queue.isEmpty()){
            int node = queue.poll();
            ans.add(node);
            for(int nei : adj.get(node)){
                indegree[nei]-- ;
                if(indegree[nei] == 0){
                    queue.add(nei);
                }
            }
        }
        Collections.sort(ans);
        return ans ;
    }
}