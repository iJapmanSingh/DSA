/*
this is solved using kahn's Algorithm ( topo sort using BFS)
obviously if graph cant have topo sort that means it has a cycle 

If the graph has a cycle, then some courses depend on each other in a loop
(e.g., A → B → C → A), so they can never be completed.
Therefore, it is impossible to finish all courses.

Using Kahn’s Algorithm:

We process nodes with indegree = 0 (no prerequisites)
Gradually remove edges and reduce indegrees
If we are able to process all nodes, then:
👉 No cycle exists → All courses can be completed
If some nodes are left unprocessed, then:
👉 Cycle exists → All courses cannot be completed


Cycle → No Topo Sort → Cannot finish courses
*/

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // MAKING GRAPH FROM PREREQUISITES -
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        // initialize
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        // build graph
        for (int[] pre : prerequisites) {
            int a = pre[0];
            int b = pre[1];

            adj.get(b).add(a); // b → a
        }

        Queue<Integer> queue = new LinkedList<>();

        int[] indegree = new int[numCourses];
        for(int i = 0 ; i<numCourses ; i++){
            for(int node : adj.get(i)){
                indegree[node]++ ;
            }
        }

        for(int i = 0 ; i<numCourses ; i++){
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
        if(topo.size() == numCourses){
            return true ;
        }
        return false ;
    }
}