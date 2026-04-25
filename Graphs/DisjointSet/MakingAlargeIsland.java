/*

 * PROBLEM SUMMARY
 * ------------------------------------------------------------
 * Given an n x n binary grid, you can flip AT MOST one 0 → 1.
 * Return the size of the largest island after the flip.
 * Island = group of 1s connected 4-directionally.
 *
 * ------------------------------------------------------------
 * INTUITION
 * ------------------------------------------------------------
 * Brute force (flip every 0, run BFS/DFS) → O(n⁴), too slow.
 *
 * Better idea using DSU:
 *   STEP 1 → Connect all existing 1-cells into components
 *             using Union-Find (unionBySize).
 *             Each cell is encoded as: node = row * n + col
 *
 *   STEP 2 → For every 0-cell, check its 4 neighbors.
 *             Collect the DISTINCT component roots of those neighbors
 *             (use a HashSet to avoid double-counting same island).
 *             Sum up sizes of those distinct components + 1 (the flipped cell).
 *             Track the maximum.
 *
 *   STEP 3 → Edge case: if there are NO zeros in the grid
 *             (all 1s), the answer is n*n. Handle by also
 *             checking max component size over all cells.
 *
 * ------------------------------------------------------------
 * WHY HashSet IN STEP 2?
 * ------------------------------------------------------------
 * A 0-cell can be adjacent to two cells that belong to the
 * SAME island. Without a HashSet, you'd count that island twice.
 * Storing parent (root) of each neighbor deduplicates this.
 *
 * ------------------------------------------------------------
 * COMPLEXITY
 * ------------------------------------------------------------
 * Time  : O(n²  × α(n²)) ≈ O(n²)
 * Space : O(n²) for DSU arrays
 *
 * ------------------------------------------------------------
 * COMMON MISTAKES TO AVOID
 * ------------------------------------------------------------
 * 1. Forgetting to deduplicate neighbor components (HashSet).
 * 2. Using ds.size[node] instead of ds.size[ds.findParent(node)]
 *    — only the ROOT holds the correct size after merges.
 * 3. Not handling the all-1s grid case (Step 3 loop fixes this).
 * 4. Bounds check before accessing grid[nRow][nCol].
 *
 * ============================================================
 */



public class DisjointSet {
    int[] parent ;
    int[] rank ;
    int[] size ;

    public DisjointSet(int n){
        parent = new int[n];
        rank = new int[n];
        size = new int[n];

        for(int i = 0 ; i < n ; i++){
            parent[i] = i ;
            rank[i] = 0 ;
            size[i] = 1 ;
        }
    }
    public int findParent(int node){
        if(node == parent[node]){
            return node ;
        }
        return parent[node] = findParent(parent[node]) ;
    }

    public void union ( int u , int v){
        int pu = findParent(u);
        int pv = findParent(v);

        if(pu == pv) return ;
        if(rank[pu] > rank[pv]) {
            parent[pv] = pu;
        }else if(rank[pv] > rank[pu]){
            parent[pu] = pv ;
        }else{
            parent[pu] = pv ;
            rank[pv]++ ;
        }
    }

    public void unionBySize(int u, int v){
        int pu = findParent(u);
        int pv = findParent(v);

        if(pu == pv) return;

        if(size[pu] < size[pv]){
            parent[pu] = pv;
            size[pv] += size[pu];
        } else {
            parent[pv] = pu;
            size[pu] += size[pv];
        }
    }
}



class Solution {
    public int largestIsland(int[][] grid) {
        int n = grid.length ;

        DisjointSet ds = new DisjointSet(n*n);

        int[]dRow = {-1 , 0 , 1 , 0 };
        int[]dCol = {0 , 1 , 0 , -1 };

        for(int row = 0 ; row < n ; row++){
            for(int col = 0 ; col < n ; col++){
                if(grid[row][col] == 0) continue ;

                for(int idx = 0 ; idx < 4 ; idx++){
                    int nRow = row + dRow[idx];
                    int nCol = col + dCol[idx];

                    if(nRow >= 0 && nRow < n && nCol >= 0 && nCol < n && grid[nRow][nCol] == 1){
                        int node = row * n + col ;
                        int neiNode = nRow * n + nCol ;

                        ds.unionBySize(node , neiNode);
                    }
                }
            }
        }

        //step 2 
        int max = 0 ;
        for(int row = 0 ; row < n ; row++){
            for (int col = 0 ; col < n ; col++){
                if(grid[row][col] == 1) continue ;

                HashSet<Integer> components = new HashSet<>();

                for(int idx = 0 ; idx < 4 ; idx++){
                    int nRow = row + dRow[idx];
                    int nCol = col + dCol[idx];

                    if(nRow >= 0 && nRow < n && nCol >= 0 && nCol < n && grid[nRow][nCol] == 1){

                        int neiNode = nRow * n + nCol ;
                        components.add(ds.findParent(neiNode));
                    }
                }
                int size = 0 ;
                for(int parents : components){
                    size += ds.size[parents];
                }
                max = Math.max(max , size + 1);
            }
        }
        for(int cellNo = 0 ; cellNo < n*n ; cellNo++){
            max = Math.max(max , ds.size[ds.findParent(cellNo)]);
        }
        return max ;
    }
}


