class Solution {
    int n ; 
    int m ; 
    int maxEnergy ;
    char[][] grid ;
    int[][] litterId ;

    int[] dr = {-1 , 1 ,0 , 0};
    int[] dc = {0 , 0 ,-1 , 1};
    public int minMoves(String[] classroom, int energy) {
        n = classroom.length ;
        m = classroom[0].length();
        maxEnergy = energy ;

        grid = new char[n][m];
        for(int i = 0; i < n ; i++){
            for(int j = 0; j < m ; j++){
                grid[i][j] = classroom[i].charAt(j);
            }
        }

        //storing id of each litter cell
        litterId = new int[n][m];
        for(int i = 0 ; i < n ;i++){
            Arrays.fill(litterId[i] , -1);
        }

        int sr = 0 ; 
        int sc = 0 ;
        int litterCount = 0 ;
        //find S and give every L an ID 
        for(int i = 0; i < n ; i++){
            for(int j =0 ; j < m ; j++){
                if(grid[i][j] == 'S'){
                    sr = i ;
                    sc = j ;
                }
                if(grid[i][j] == 'L'){
                    litterId[i][j] = litterCount ;
                    litterCount++ ;
                }
            }
        }

        if(litterCount == 0){
            return 0 ;
        }
        return bfs(sr , sc , litterCount);
    }

    public int bfs(int sr , int sc , int litterCount){
        int allCollected = (1 << litterCount) - 1; 
        // state = row , col , energy , mask
        boolean[][][][] visited = new boolean[n][m][maxEnergy + 1][1 << litterCount];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr , sc , maxEnergy , 0});
        visited[sr][sc][maxEnergy][0] = true ;
        int moves = 0 ; 
        while(!queue.isEmpty()){
            int size = queue.size() ;
            for(int i = 0; i < size ; i++){
                int[] state = queue.poll();
                int r = state[0];
                int c = state[1];
                int currentEnergy = state[2];
                int mask = state[3] ;

                //have we collected all litter ? 
                if(mask == allCollected){
                    return moves ;
                }

                //no energy - cant move
                if(currentEnergy == 0){
                    continue ;
                }
                //trying all directions 
                for(int d = 0 ; d < 4 ; d++){
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if(nr < 0 || nr >= n || nc < 0 || nc >= m){
                        continue ;
                    }
                    if(grid[nr][nc] == 'X'){
                        continue; 
                    }
                    //moving costs 1 energy
                    int newEnergy = currentEnergy - 1 ;
                    //intitially nothing changes in mask 
                    int newMask = mask ;
                    //if cell contains litter 
                    if(grid[nr][nc] == 'L'){
                        int id = litterId[nr][nc] ;
                        //mark litter as collected
                        newMask = mask | (1 << id);
                    }

                    //reset energy
                    if(grid[nr][nc] == 'R'){
                        newEnergy = maxEnergy ;
                    }

                    //already visited this exact state
                    if(visited[nr][nc][newEnergy][newMask]){
                        continue ;
                    }
                    //mark visited 
                    visited[nr][nc][newEnergy][newMask] = true ;
                    //add new state to queue 
                    queue.offer(new int[]{nr , nc ,newEnergy , newMask});
                }
            }
            //move to next BFS level
            moves++ ;
        }
        return -1 ;
    }
}