class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {

        int[][] dist = new int[n][n];
        for(int i = 0 ; i< n ; i++){
            for(int j = 0 ; j<n ; j++){
                dist[i][j] = (int)(1e9);
            }
        }
        for(int[] edge : edges){
            dist[edge[0]][edge[1]] = edge[2] ;
            dist[edge[1]][edge[0]] = edge[2] ;
        }
        for(int i = 0; i< n;  i++){
            dist[i][i] = 0 ;
        }

        //floyd warshall algo -

        for(int k = 0 ; k < n ; k++){
            for(int i = 0 ; i < n ; i++){
                for(int j = 0 ; j<n ; j++){
                    if(dist[i][k] != (int)(1e9) && dist[k][j] != (int)(1e9)){
                        dist[i][j] = Math.min(dist[i][j] , dist[i][k] + dist[k][j]);
                    }
                }
            }
        }


        int countCity = n ;
        int cityNo = -1 ;
        for(int city = 0 ; city < n ; city++){
            int cnt = 0 ;
            for(int adjCity = 0 ; adjCity < n ; adjCity++ ){
                if(dist[city][adjCity] <= distanceThreshold){
                    cnt++ ;
                }
            }
            if(cnt <= countCity){
                countCity = cnt ;
                cityNo = city ;
            }
        }
        return cityNo ;
    }
}