/*
https://www.geeksforgeeks.org/problems/topological-sort/1
 this is topological sort using Kahn's Algorithm
 
        Kahn's Algorithm -
        1 - Find all nodes with indegree== 0 ;
        2 - Push them into queue
        3 - remove them one by one and add to result and reduce indegree of their neighbours
        4 - if any neighbour becomes indegree == 0 ; push to queue
        5 - repeat until queue is empty 
*/

class Solution {
    public ArrayList<Integer> topoSort(int V, int[][] edges) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0 ; i < V ; i++){
            adj.add(new ArrayList<>());
        }
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            adj.get(u).add(v);

        }

        Queue<Integer> queue = new LinkedList<>();

        int[] indegree = new int[V];
        for(int i = 0 ; i<V ; i++){
            for(int node : adj.get(i)){
                indegree[node]++ ;
            }
        }

        for(int i = 0 ; i<V ; i++){
            if(indegree[i] == 0){
                queue.add(i);
            }
        }

        ArrayList<Integer> topo = new ArrayList<>();

        while(!queue.isEmpty()){
            int node = queue.poll();
            topo.add(node);
            for(int nei : adj.get(node)){
                indegree[nei]-- ;
                if(indegree[nei] == 0 ){
                    queue.add(nei);
                }
            }
        }
        return topo ;
    }

}