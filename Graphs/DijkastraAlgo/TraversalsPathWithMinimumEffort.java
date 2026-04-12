/*
📌 ALGORITHM:

1. Initialize dist[n][m] with infinity
2. dist[0][0] = 0
3. Use min heap (PriorityQueue) storing {effort, r, c}
4. Push {0, 0, 0}

5. While PQ not empty:
    - Pop smallest effort cell
    - If reached destination → return effort

    - For all 4 directions:
        - Check valid cell
        - Compute newEffort
        - If newEffort < dist[nr][nc]:
            - Update dist
            - Push into PQ


⏱️ TIME COMPLEXITY:
O(N * M * log(N*M))

📦 SPACE COMPLEXITY:
O(N * M)

------------------------------------------------------------

PATTERN:
Grid + Minimize Maximum → Dijkstra / Binary Search

*/



class Solution {
    public int minimumEffortPath(int[][] heights) {
        int n = heights.length ;
        int m = heights[0].length ;

        int[][] dist = new int[n][m];
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j<m ; j++){
                dist[i][j] = (int)(1e9);
            }
        }
        dist[0][0] = 0 ;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.add(new int[]{0 , 0 , 0});

        int[] dRow = {-1 , 0 , 1 , 0 } ;
        int[] dCol = {0 , 1 , 0 , -1};

        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int diff = curr[0];
            int r = curr[1];
            int c = curr[2];

            if(r == n -1 && c == m-1 ) return diff ;

            for(int i = 0 ; i< 4 ; i++){
                int nRow = r + dRow[i];
                int nCol = c + dCol[i];

                if(nRow >=0 && nRow < n && nCol >= 0 && nCol < m){
                    int newEffort = Math.max(Math.abs(heights[r][c] - heights[nRow][nCol]) , diff);
                    if(newEffort < dist[nRow][nCol]){
                        dist[nRow][nCol] = newEffort ;
                        pq.add(new int[]{newEffort , nRow , nCol});
                    }
                }
            }
        }
        return 0 ;
    }
}
