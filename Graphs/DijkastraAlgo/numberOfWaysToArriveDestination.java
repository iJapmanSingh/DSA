/*


ALGORITHM: Dijkstra + Path Counting

2. Initialize:

   * dist[] array (long) → store shortest distance to each node
     initialize all with ∞ (Long.MAX_VALUE)
     dist[0] = 0

   * ways[] array → store number of ways to reach each node
     ways[0] = 1 (start node)

3. Use Min Heap (PriorityQueue):

   * Store {distance, node}
   * Always process node with smallest distance first

4. Apply Dijkstra:
   While PQ is not empty:
   - Pop {d, node}

   ```
   - If d > dist[node], skip (outdated entry)

   - For all neighbours:
        adjNode = neighbour node
        wt = edge weight

        CASE 1: Found shorter path
            if (d + wt < dist[adjNode]):
                dist[adjNode] = d + wt
                ways[adjNode] = ways[node]
                push into PQ

        CASE 2: Found another shortest path
            else if (d + wt == dist[adjNode]):
                ways[adjNode] += ways[node]
                take modulo (1e9 + 7)
   ```

5. Return:

   * ways[n-1] % mod

TIME COMPLEXITY:
O((V + E) log V)

SPACE COMPLEXITY:
O(V + E)

KEY IDEA:

* Track shortest distance using Dijkstra
* Simultaneously count number of shortest paths

IMPORTANT POINTS:

* Use long for distance (avoid overflow)
* Use modulo for ways
* Skip outdated PQ entries

*/





class Solution {
    public int countPaths(int n, int[][] roads) {


        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] road : roads){
            int u = road[0];
            int v = road[1];
            int t = road[2];

            adj.get(u).add(new int[]{v, t});
            adj.get(v).add(new int[]{u, t});
        }


        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;


        int[] ways = new int[n];
        ways[0] = 1;


        PriorityQueue<long[]> pq = new PriorityQueue<>(
                (a, b) -> Long.compare(a[0], b[0])
        );
        pq.add(new long[]{0, 0});

        int mod = (int)(1e9 + 7);

        // Step 5: Dijkstra
        while(!pq.isEmpty()){
            long[] curr = pq.poll();
            long d = curr[0];
            int node = (int) curr[1];

            // Skip outdated entries
            if(d > dist[node]) continue;

            for(int[] nei : adj.get(node)){
                int adjNode = nei[0];
                int wt = nei[1];

                // Found shorter path
                if(d + wt < dist[adjNode]){
                    dist[adjNode] = d + wt;
                    pq.add(new long[]{dist[adjNode], adjNode});
                    ways[adjNode] = ways[node];
                }
                // Found another shortest path
                else if(d + wt == dist[adjNode]){
                    ways[adjNode] = (ways[adjNode] + ways[node]) % mod;
                }
            }
        }

        return ways[n - 1];
    }
}