

class Solution {

    int shortestPath(int[][] grid, int[] source, int[] destination) {

        
        int n = grid.length ;
        int m = grid[0].length ;

        //make distance array and put infinite
        int[][] dist = new int[n][m];
        for(int i = 0 ; i<n ; i++){
            for(int j = 0 ; j<m ; j++){
                dist[i][j] = (int)(1e9);
            }
        }
        //make distance of source == 0 ;
        dist[source[0]][source[1]] = 0 ;

        //make a queue of integer array , array[0] = distance , array[1] = row , array[2] = column
        Queue<int[]> queue = new LinkedList<>();
        //add distance 0 and source into queue
        queue.add(new int[]{0 , source[0] , source[1]});


        //Edge case 1 - what if the source is destination
        if(source[0] == destination[0] && source[1] == destination[1]) {
            return 0;
        }

        int[] dRow = {-1 , 0 , 1 , 0 } ;
        int[] dCol = {0 , 1 , 0 , -1};
        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int distance = curr[0];
            int r = curr[1];
            int c = curr[2];

            for(int i = 0 ; i< 4 ; i++){
                int nRow = r + dRow[i];
                int nCol = c + dCol[i];

                if(nRow >=0 && nRow < n && nCol >= 0 && nCol < m && grid[nRow][nCol] == 1 && dist[nRow][nCol] > distance + 1){
                    dist[nRow][nCol] = distance + 1 ;

                    if(nRow == destination[0] && nCol == destination[1]){
                        return distance + 1 ;
                    }
                    queue.add(new int[]{distance + 1 , nRow , nCol});
                }
            }
        }
        return -1 ;

    }
}