/*

🚫 Brute Force Approach (Why not?)

* After each operation, run DFS/BFS to count islands
* Time Complexity → **O(Q × N × M)** ❌ (too slow)

---
⚡ Optimized Approach (Disjoint Set - Union Find)

We treat each land cell as a node and dynamically connect adjacent lands.

---

 🔑 Key Idea

* Initially all cells are water
* When land is added:

  * Increase island count
  * Check 4 directions (up, down, left, right)
  * If neighbor is land → union them
  * If union happens → decrease island count

---

To use DSU, convert 2D grid into 1D index:
index = row * cols + col

---

## 🔁 Algorithm Steps

For each operation (r, c):

1. If already visited:

   * Add current count to result
   * Continue

2. Mark as land:

   * visited[r][c] = true
   * count++

3. Check all 4 directions:

   * If adjacent cell is land:

     * If they belong to different components:

       * Union them
       * count--

4. Store count in result


## ⏱️ Time Complexity

* Each operation → nearly **O(1)** (amortized)
* Total → **O(Q × α(N))** ≈ **O(Q)**


## 🚀 Interview One-Liner

"Instead of recalculating islands every time, we use Disjoint Set to dynamically maintain connected components and update the island count efficiently."

---

*/

class DisjointSet {
    int[] parent ;
    int[] rank ;

    public DisjointSet(int n){
        parent = new int[n];
        rank = new int[n];

        for(int i = 0 ; i < n ; i++){
            parent[i] = i ;
            rank[i] = 0 ;
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
}

class Solution {

    public List<Integer> numOfIslands(int rows, int cols, int[][] operators) {

        DisjointSet ds = new DisjointSet(rows*cols);
        boolean[][] visited = new boolean[rows][cols];

        List<Integer> result = new ArrayList<>();
        int count = 0 ;

        int[]dRow = {-1 , 0 , 1 , 0 };
        int[]dCol = {0 , 1 , 0 , -1 };

        for(int i = 0 ; i < operators.length ; i++){
            int row = operators[i][0];
            int col = operators[i][1];

            int index = row * cols + col ;

            if(visited[row][col]){
                result.add(count);
                continue ;
            }

            visited[row][col] = true ;
            count++ ;

            for(int ind = 0 ; ind< 4 ; ind++){
                int adjRow = row + dRow[ind];
                int adjCol = col + dCol[ind];

                if(adjRow >= 0 && adjRow < rows && adjCol >= 0 && adjCol < cols && visited[adjRow][adjCol]){
                    int nIndex = adjRow * cols + adjCol ;
                    if(ds.findParent(index) != ds.findParent(nIndex)){
                        count-- ;
                        ds.union(index , nIndex);
                    }
                }
            }
            result.add(count);
        }
        return result ;
    }
}