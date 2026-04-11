class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        // Edge case: start blocked
        if(grid[0][0] == 1) return -1;

        // Edge case: single cell
        if(n == 1) return 1;

        int[][] dist = new int[n][n];
        for(int i = 0; i < n; i++){
            Arrays.fill(dist[i], (int)1e9);
        }

        dist[0][0] = 1;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1, 0, 0});

        int[] dRow = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dCol = {0, 1, 1, 1, 0, -1, -1, -1};

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int distance = curr[0];
            int r = curr[1];
            int c = curr[2];

            for(int i = 0; i < 8; i++){
                int nRow = r + dRow[i];
                int nCol = c + dCol[i];

                if(nRow >= 0 && nRow < n && nCol >= 0 && nCol < n
                        && grid[nRow][nCol] == 0
                        && dist[nRow][nCol] > distance + 1){

                    dist[nRow][nCol] = distance + 1;

                    if(nRow == n-1 && nCol == n-1){
                        return distance + 1;
                    }

                    queue.add(new int[]{distance + 1, nRow, nCol});
                }
            }
        }
        return -1;
    }
}