/*
 * 947. Most Stones Removed with Same Row or Column
 * Difficulty: Medium
 * Approach: Union-Find (Disjoint Set Union) + HashMap
 *
 * ALGORITHM:
 * ---------------------------------------------------------
 * INTUITION:
 *   - Stones sharing a row or column are "connected"
 *   - From each connected component, only 1 stone must remain
 *   - Answer = total stones - number of connected components
 *
 * STEPS:
 *   1. Find maxRow and maxCol to size the DSU array
 *
 *   2. To avoid row/col index clash in DSU:
 *      - Row nodes are stored as-is:        nodeRow = row
 *      - Col nodes are offset:              nodeCol = col + maxRow + 1
 *      - DSU size = maxRow + maxCol + 2
 *
 *   3. For each stone [row, col]:
 *      - Union(nodeRow, nodeCol) → connects row and col in same component
 *      - Track both nodes in HashMap (stoneNodes) to know which indices are active
 *
 *   4. Count connected components:
 *      - Iterate over stoneNodes
 *      - If a node is its own parent → it is a component root → cnt++
 *
 *   5. Return n - cnt
 *      - Each component keeps 1 stone, rest are removed
 *
 * DISJOINT SET (DSU) OPERATIONS:
 *   - findParent(node) : Path compression for O(α) lookup
 *   - unionBySize(u,v) : Merge smaller tree into larger for balance
 *   - union(u,v)       : Merge by rank (alternative strategy)
 *
 * COMPLEXITY:
 *   - Time  : O(n * α(n)) — nearly O(n), α is inverse Ackermann
 *   - Space : O(maxRow + maxCol) for DSU + O(n) for HashMap
 * ---------------------------------------------------------
 */




public class DisjointSet {
    int[] parent;
    int[] rank;
    int[] size;

    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
            size[i] = 1;
        }
    }

    public int findParent(int node) {
        if (node == parent[node]) {
            return node;
        }
        return parent[node] = findParent(parent[node]);
    }

    public void union(int u, int v) {
        int pu = findParent(u);
        int pv = findParent(v);

        if (pu == pv)
            return;
        if (rank[pu] > rank[pv]) {
            parent[pv] = pu;
        } else if (rank[pv] > rank[pu]) {
            parent[pu] = pv;
        } else {
            parent[pu] = pv;
            rank[pv]++;
        }
    }

    public void unionBySize(int u, int v) {
        int pu = findParent(u);
        int pv = findParent(v);

        if (pu == pv)
            return;

        if (size[pu] < size[pv]) {
            parent[pu] = pv;
            size[pv] += size[pu];
        } else {
            parent[pv] = pu;
            size[pu] += size[pv];
        }
    }
}

class Solution {
    public int removeStones(int[][] stones) {
        int n = stones.length;
        int maxRow = 0;
        int maxCol = 0;
        for (int i = 0; i < n; i++) {
            maxRow = Math.max(maxRow, stones[i][0]);
            maxCol = Math.max(maxCol, stones[i][1]);
        }
        DisjointSet ds = new DisjointSet(maxRow + maxCol + 2);
        HashMap<Integer, Integer> stoneNodes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int nodeRow = stones[i][0];
            int nodeCol = stones[i][1] + maxRow + 1;

            ds.unionBySize(nodeRow, nodeCol);
            stoneNodes.put(nodeRow, 1);
            stoneNodes.put(nodeCol, 1);
        }
        int cnt = 0;

        // Iterate over all unique nodes that belong to stones
        for (Map.Entry<Integer, Integer> entry : stoneNodes.entrySet()) {
            // If this node is its own parent → it's a component root
            if (ds.findParent(entry.getKey()) == entry.getKey()) {
                cnt++;
            }
        }

        return n - cnt; // stones - number of components
    }
}