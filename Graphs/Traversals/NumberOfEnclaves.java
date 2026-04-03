class Solution {
    public int numEnclaves(int[][] grid) {
        int n = grid.length ;
        int m = grid[0].length ;
        boolean[][] visited = new boolean[n][m];

        for(int i = 0 ; i< m ; i++){
            //top row 
            if(grid [0][i] == 1 && !visited[0][i]){
                dfs(0 , i , grid  , visited);
            }
            //bottom row
            if(grid [n - 1][i] == 1 && !visited[n-1][i]){
                dfs(n-1 , i , grid  , visited);
            }
        }
        for(int i = 0 ; i<n ; i++){
            //first column
            if(grid [i][0] == 1 && !visited[i][0]){
                dfs(i , 0 , grid  , visited);
            }
            //last column
            if(grid [i][m-1] == 1 && !visited[i][m-1]){
                dfs(i , m-1 , grid  , visited);
            }
        }
        int count = 0 ;
        for(int i = 0 ; i< n ; i++){
            for (int j = 0 ; j< m ; j++){
                if(grid[i][j] == 1 && !visited[i][j]){
                    count++ ;

                }
            }
        }
        return count ;
    }
    public void dfs(int row , int col , int[][] grid , boolean[][] visited){
        int n = grid .length ;
        int m = grid [0].length ;
        int[] dRow = {-1 , 0 , 1 , 0};
        int[] dCol = {0 , 1 , 0 , -1};
        visited[row][col] = true ;
        for(int i = 0 ; i<4 ; i++){
            int nr = row + dRow[i];
            int nc = col + dCol[i];
            if(nr >= 0 && nr < n && nc >=0 && nc < m && !visited[nr][nc]  && grid [nr][nc] == 1){
                dfs(nr , nc , grid  , visited);
            }
        }
    }
}