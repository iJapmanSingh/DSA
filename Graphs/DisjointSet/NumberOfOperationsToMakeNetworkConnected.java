public class DisjointSet {
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
    public int makeConnected(int n, int[][] connections) {
        DisjointSet ds = new DisjointSet(n);
        int extraEdges = 0 ;
        for(int[] edge : connections){
            int u = edge[0];
            int v = edge[1];

            int pu = ds.findParent(u);
            int pv = ds.findParent(v);

            if(pu != pv){
                ds.union(u , v);
            }else{
                extraEdges++ ;
            }
        }

        int numberOfComponents = 0 ;
        for(int i = 0 ; i<n ; i++){
            if(ds.findParent(i) == i){
                numberOfComponents++ ;
            }
        }

        if(extraEdges >= numberOfComponents -1){
            return numberOfComponents -1  ;
        }
        return -1 ;
    }
}