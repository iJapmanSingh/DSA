/*
🔹 DATA STRUCTURES:
- Adjacency list → to store graph (u → {v, weight})
- Queue → stores {stops, node, cost}
- dist[] → stores minimum cost to reach each node

------------------------------------------------------------

🔥 CORE LOGIC (IMPORTANT):

1. Queue Elements:
   {stops, node, currentCost}
   → stops = number of edges used so far

2. While Loop (BFS traversal with constraint):
   - We process nodes level-wise (based on stops)
   - If stops > k → skip (invalid path)

3. Inside For Loop (Exploring neighbors):
   For each neighbor:
   - Try relaxing edge:
       if (currentCost + weight < dist[neighbor])
   - AND stops <= k (valid path)

   ✔ If valid:
      - Update dist[neighbor]
      - Push into queue with:
            stops + 1
            updated cost

------------------------------------------------------------

⚠️ IMPORTANT INSIGHT:
- We DO NOT use priority queue because:
  → We care about minimum cost WITHIN k stops
  → Not overall shortest path

- dist[] helps avoid unnecessary paths (pruning)

------------------------------------------------------------

⛔ EDGE CASE:
- If destination is unreachable → return -1

------------------------------------------------------------

💡 TIME COMPLEXITY:
O(K * E)  → since we traverse edges up to k levels

------------------------------------------------------------
*/




class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();

        for(int i = 0 ; i<n ; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] edge : flights){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            adj.get(u).add(new int[]{v , w});
        }

        int[] dist = new int[n];
        for(int i = 0 ; i< n ; i++){
            dist[i] = (int)(1e9);
        }
        dist[src] = 0 ;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0 , src , 0});

        while(!q.isEmpty()){
            int[] curr = q.poll();
            int stop = curr[0];
            int node = curr[1];
            int d = curr[2];

            if(stop > k) continue ;
            for(int[] nei : adj.get(node)){
                int nNode = nei[0];
                int weight = nei[1];

                if(d + weight  < dist[nNode ] && stop <= k){
                    dist[nNode] = d + weight ;
                    q.add(new int[]{stop + 1 ,  nNode , d + weight });
                }
            }
        }
        if(dist[dst] == (int)(1e9)) return -1 ;

        return dist[dst] ;
    }
}