class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        boolean[][] visited = new boolean[n][m];
        int[][] distance = new int[n][m];
        Queue<int[]> queue = new LinkedList<>();

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (mat[row][col] == 0) {
                    visited[row][col] = true;
                    // distance[row][col] = 0 ;
                    queue.add(new int[] { row, col, 0 });
                }
            }
        }

        int[] dRow = { -1, 0, 1, 0 };
        int[] dCol = { 0, 1, 0, -1 };
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0];
            int col = cell[1];
            int step = cell[2];

            distance[row][col] = step;
            for (int i = 0; i < 4; i++) {
                int nr = row + dRow[i];
                int nc = col + dCol[i];
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc] && mat[nr][nc] == 1) {
                    visited[nr][nc] = true;
                    queue.add(new int[] { nr, nc, step + 1 });
                }
            }

        }
        return distance;
    }
}