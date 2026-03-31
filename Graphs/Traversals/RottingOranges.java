// Problem: Rotting Oranges
// Platform: LeetCode
// Link: https://leetcode.com/problems/rotting-oranges/
// Approach: BFS (Multi-source BFS)
// Time Complexity: O(N * M)
// Space Complexity: O(N * M)

class Solution {
    public int orangesRotting(int[][] grid) {
        int n = grid.length ;
        int m = grid[0].length ;
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0 ;

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j< m ; j++){
                if(grid[i][j] == 2){
                    queue.add(new int[]{i , j , 0});
                }else if(grid[i][j] == 1){
                    fresh++ ;
                }
            }
        }

        int maxTime = 0 ;
        int[] delrow = { - 1 , 0 , 1 , 0};
        int[] delcol = { 0 , 1 , 0 , -1 };
        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int t = curr[2];
            maxTime = Math.max(maxTime , t) ;

            for(int i = 0 ; i< 4 ; i++ ){
                int nr = r + delrow[i];
                int nc = c + delcol[i];
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && grid[nr][nc] == 1) {
                    grid[nr][nc] = 2;
                    fresh--;
                    queue.add(new int[]{nr, nc, t + 1});
                }
            }
        }
        return fresh == 0 ? maxTime : -1;
    }
}